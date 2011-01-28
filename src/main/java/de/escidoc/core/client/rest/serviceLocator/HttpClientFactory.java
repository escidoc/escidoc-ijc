package de.escidoc.core.client.rest.serviceLocator;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpHost;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.log4j.Logger;

import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.common.configuration.ConfigurationProvider;

public class HttpClientFactory {
	
	private static final Logger LOG = Logger.getLogger(HttpClientFactory.class);
	
	private static Pattern HTTP_HOST_PATTERN = Pattern.compile("^(?:(.*)://)?([^:]*)(?::(\\d*))?$");
	private static Pattern INET_ADDRESS_PATTERN = Pattern.compile("^(?:(.*)/)?(\\d*)\\.(\\d*)\\.(\\d*)\\.(\\d*)$");
	
	private static HttpClient client;
    private static ThreadSafeClientConnManager connectionManager;
	private static HttpParams params;
	private static ConfigurationProvider cp;
	
	public static synchronized HttpClient getHttpClient() {
		if (client == null) {
			createHttpClient();
		}
		return client;
	}

	private static void createHttpClient() {
		params = new BasicHttpParams();
		
    	try {
    		cp = ConfigurationProvider.getInstance();
    		/* questions:
    		 * http.route.forced-route: 
    		 * How to pass HttpRoute? Expecting HttpHost for now.
    		 * 
    		 * http.conn-manager.max-per-route:
    		 * How to pass ConnPerRoute?
    		 * Expecting int as defaultMaxPerRoute for now.
    		 */
    		
    		
        	// custom custom... HttpRoute can be constructed from HttpHost...
        	if (cp.getProperty(ConfigurationProvider.HTTP_ROUTE_FORCED_ROUTE) != null) {
        		String data = cp.getProperty(ConfigurationProvider.HTTP_ROUTE_FORCED_ROUTE);
       			HttpHost httpHost = getHttpHostFromString(data);
       			if (httpHost != null) {
       				HttpRoute httpRoute = new HttpRoute(httpHost);
       				params.setParameter(ConfigurationProvider.HTTP_ROUTE_FORCED_ROUTE, httpRoute);
       			}
        	}
        	
        	// custom custom... ConnPerRouteBean can be constructed using an integer as default max per route
        	if (cp.getProperty(ConfigurationProvider.HTTP_CONN_MANAGER_MAX_PER_ROUTE) != null) {
        		try {
	        		int maxPerRoute = Integer.parseInt(cp.getProperty(ConfigurationProvider.HTTP_CONN_MANAGER_MAX_PER_ROUTE));
	        		ConnPerRouteBean connPerRouteBean = new ConnPerRouteBean(maxPerRoute);
        			params.setParameter(ConfigurationProvider.HTTP_CONN_MANAGER_MAX_PER_ROUTE, connPerRouteBean);
        		}
        		catch (NumberFormatException e) {
        			LOG.debug("Could not load property: " + ConfigurationProvider.HTTP_CONN_MANAGER_MAX_PER_ROUTE + " (property ignored)", e);
        		}
        	}
        	
    		// Collection of Strings adhering to SimpleDateFormat
    		// consecutively numbered properties
    		// <SimpleDateFormatCompliantString>
    		int i = 0;
    		LinkedList<String> datePatterns = new LinkedList<String>();
    		while (cp.getProperty(ConfigurationProvider.HTTP_PROTOCOL_COOKIE_DATEPATTERNS + i) != null) {
    			String data = cp.getProperty(ConfigurationProvider.HTTP_PROTOCOL_COOKIE_DATEPATTERNS + i);
    			try {
					new SimpleDateFormat(data);
					datePatterns.add(data);
				}
				catch (IllegalArgumentException e) {
					// Pattern does not comply to SimpleDateFormat
					LOG.debug("Could not load property: " + ConfigurationProvider.HTTP_PROTOCOL_COOKIE_DATEPATTERNS + i + " (property ignored)", e);
				}
				i++;
    		}
    		if (datePatterns.size() > 0) {
    			params.setParameter(ConfigurationProvider.HTTP_PROTOCOL_COOKIE_DATEPATTERNS, datePatterns);
    		}
    		
    		// Collection of Header-Objects
    		// consecutively numbered properties
    		// <headerName>:<headerValue>
    		int j = 0;
    		LinkedList<BasicHeader> defaultHeaders = new LinkedList<BasicHeader>();
    		
    		while (cp.getProperty(ConfigurationProvider.HTTP_DEFAULT_HEADERS + j) != null) {
    			String data = cp.getProperty(ConfigurationProvider.HTTP_DEFAULT_HEADERS + j);
   				String[] tmp = data.split(":");
   				try {
   					defaultHeaders.add(new BasicHeader(tmp[0], tmp[1]));
   				}
       			catch (IndexOutOfBoundsException e) {
       				LOG.debug("Could not load property: " + ConfigurationProvider.HTTP_DEFAULT_HEADERS + j + " (property ignored)", e);
       			}
       			j++;
			}
    		if (defaultHeaders.size() > 0) {
    			params.setParameter(ConfigurationProvider.HTTP_DEFAULT_HEADERS, defaultHeaders);
    		}

    		// custom format for InetAddress [<hostname>/]<ipAddress>
    		if (cp.getProperty(ConfigurationProvider.HTTP_ROUTE_LOCAL_ADDRESS) != null) {
        		String data = cp.getProperty(ConfigurationProvider.HTTP_ROUTE_LOCAL_ADDRESS);

        		Matcher m = INET_ADDRESS_PATTERN.matcher(data);
        		if (m.find()) {
        			String hostname = m.group(1);
            		byte[] ipaddr = new byte[4];
            		ipaddr[0] = Byte.parseByte(m.group(2));
            		ipaddr[1] = Byte.parseByte(m.group(3));
            		ipaddr[2] = Byte.parseByte(m.group(4));
            		ipaddr[3] = Byte.parseByte(m.group(5));
        		
					try {
						InetAddress inetAddress = InetAddress.getByAddress(hostname, ipaddr);
						params.setParameter(ConfigurationProvider.HTTP_ROUTE_LOCAL_ADDRESS, inetAddress);
					} catch (UnknownHostException e) {
						LOG.debug("Could not load property: " + ConfigurationProvider.HTTP_ROUTE_LOCAL_ADDRESS + " (property ignored)");
					}
        		}
        		else {
        			LOG.debug("Could not load property: " + ConfigurationProvider.HTTP_ROUTE_LOCAL_ADDRESS + " (property ignored)");
        		}
    		}
    		
        	// custom format for ProtocolVersion <protocol>/<major>.<minor>
        	if (cp.getProperty(ConfigurationProvider.HTTP_PROTOCOL_VERSION) != null) {
        		String data = cp.getProperty(ConfigurationProvider.HTTP_PROTOCOL_VERSION);
        		
        		try {
        			String protocol = data.substring(0, data.indexOf("/"));
        			int major = Integer.parseInt(data.substring(data.indexOf("/") + 1, data.indexOf(".")));
        			int minor = Integer.parseInt(data.substring(data.indexOf(".") + 1, data.length()));
        			ProtocolVersion pv = new ProtocolVersion(protocol, major, minor);
        			params.setParameter(ConfigurationProvider.HTTP_PROTOCOL_VERSION, pv);
        		}
        		catch (NumberFormatException e) {
        			LOG.debug("Could not load property: " + ConfigurationProvider.HTTP_PROTOCOL_VERSION + " (property ignored)", e);
        		}
        	}
        	
        	// custom format for HttpHost [<scheme>://]<host>[:<port>]
        	if (cp.getProperty(ConfigurationProvider.HTTP_ROUTE_DEFAULT_PROXY) != null) {
        		String data = cp.getProperty(ConfigurationProvider.HTTP_ROUTE_DEFAULT_PROXY);
        		HttpHost httpHost = getHttpHostFromString(data);
        		if (httpHost != null) {
        			params.setParameter(ConfigurationProvider.HTTP_ROUTE_DEFAULT_PROXY, httpHost);
        		}
        	}
        	
        	if (cp.getProperty(ConfigurationProvider.HTTP_VIRTUAL_HOST) != null) {
        		String data = cp.getProperty(ConfigurationProvider.HTTP_VIRTUAL_HOST);
        		HttpHost httpHost = getHttpHostFromString(data);
        		if (httpHost != null) {
        			params.setParameter(ConfigurationProvider.HTTP_VIRTUAL_HOST, httpHost);
        		}
        	}
        	
        	if (cp.getProperty(ConfigurationProvider.HTTP_DEFAULT_HOST) != null) {
        		String data = cp.getProperty(ConfigurationProvider.HTTP_DEFAULT_HOST);
        		HttpHost httpHost = getHttpHostFromString(data);
        		if (httpHost != null) {
        			params.setParameter(ConfigurationProvider.HTTP_DEFAULT_HOST, httpHost);
        		}
        	}
        	
        	// string parameters
        	if (cp.getProperty(ConfigurationProvider.HTTP_PROTOCOL_ELEMENT_CHARSET) != null) {
        		params.setParameter(ConfigurationProvider.HTTP_PROTOCOL_ELEMENT_CHARSET, cp.getProperty(ConfigurationProvider.HTTP_PROTOCOL_ELEMENT_CHARSET));
        	}
        	
        	if (cp.getProperty(ConfigurationProvider.HTTP_PROTOCOL_CONTENT_CHARSET) != null) {
        		params.setParameter(ConfigurationProvider.HTTP_PROTOCOL_CONTENT_CHARSET, cp.getProperty(ConfigurationProvider.HTTP_PROTOCOL_CONTENT_CHARSET));
        	}
        	
        	if (cp.getProperty(ConfigurationProvider.HTTP_USERAGENT) != null) {
        		params.setParameter(ConfigurationProvider.HTTP_USERAGENT, cp.getProperty(ConfigurationProvider.HTTP_USERAGENT));
        	}
        	
        	if (cp.getProperty(ConfigurationProvider.HTTP_PROTOCOL_STRICT_TRANSFER_ENCODING) != null) {
        		params.setParameter(ConfigurationProvider.HTTP_PROTOCOL_STRICT_TRANSFER_ENCODING, cp.getProperty(ConfigurationProvider.HTTP_PROTOCOL_STRICT_TRANSFER_ENCODING));
        	}
        	
        	if (cp.getProperty(ConfigurationProvider.HTTP_PROTOCOL_EXPECT_CONTINUE) != null) {
        		params.setParameter(ConfigurationProvider.HTTP_PROTOCOL_EXPECT_CONTINUE, cp.getProperty(ConfigurationProvider.HTTP_PROTOCOL_EXPECT_CONTINUE));
        	}
        	
        	if (cp.getProperty(ConfigurationProvider.HTTP_PROTOCOL_WAIT_FOR_CONTINUE) != null) {
        		params.setParameter(ConfigurationProvider.HTTP_PROTOCOL_WAIT_FOR_CONTINUE, cp.getProperty(ConfigurationProvider.HTTP_PROTOCOL_WAIT_FOR_CONTINUE));
        	}
        	
        	if (cp.getProperty(ConfigurationProvider.HTTP_PROTOCOL_COOKIE_POLICY) != null) {
        		params.setParameter(ConfigurationProvider.HTTP_PROTOCOL_COOKIE_POLICY, cp.getProperty(ConfigurationProvider.HTTP_PROTOCOL_COOKIE_POLICY));
        	}
        	
        	if (cp.getProperty(ConfigurationProvider.HTTP_AUTH_CREDENTIAL_CHARSET) != null) {
        		params.setParameter(ConfigurationProvider.HTTP_AUTH_CREDENTIAL_CHARSET, cp.getProperty(ConfigurationProvider.HTTP_AUTH_CREDENTIAL_CHARSET));
        	}
        	
        	if (cp.getProperty(ConfigurationProvider.HTTP_CONNECTION_MANAGER_FACTORY_CLASS_NAME) != null) {
        		params.setParameter(ConfigurationProvider.HTTP_CONNECTION_MANAGER_FACTORY_CLASS_NAME, cp.getProperty(ConfigurationProvider.HTTP_CONNECTION_MANAGER_FACTORY_CLASS_NAME));
        	}
        	
        	// int parameters
        	setIntProperty(ConfigurationProvider.HTTP_SOCKET_TIMEOUT);
        	setIntProperty(ConfigurationProvider.HTTP_SOCKET_LINGER);
        	setIntProperty(ConfigurationProvider.HTTP_SOCKET_BUFFER_SIZE);
        	setIntProperty(ConfigurationProvider.HTTP_CONNECTION_TIMEOUT);
        	setIntProperty(ConfigurationProvider.HTTP_CONNECTION_MAX_LINE_LENGTH);
        	setIntProperty(ConfigurationProvider.HTTP_CONNECTION_MAX_HEADER_COUNT);
        	setIntProperty(ConfigurationProvider.HTTP_CONNECTION_MAX_STATUS_LINE_GARBAGE);
        	setIntProperty(ConfigurationProvider.HTTP_CONN_MANAGER_MAX_TOTAL);
        	setIntProperty(ConfigurationProvider.HTTP_PROTOCOL_MAX_REDIRECTS);
        	
        	// long parameters
        	setLongProperty(ConfigurationProvider.HTTP_CONN_MANAGER_TIMEOUT);
        	
        	// boolean parameters
        	if (cp.getProperty(ConfigurationProvider.HTTP_TCP_NODELAY) != null) {
       			params.setParameter(ConfigurationProvider.HTTP_TCP_NODELAY, Boolean.parseBoolean(cp.getProperty(ConfigurationProvider.HTTP_TCP_NODELAY)));
        	}
        	
        	if (cp.getProperty(ConfigurationProvider.HTTP_CONNECTION_STALECHECK) != null) {
       			params.setParameter(ConfigurationProvider.HTTP_CONNECTION_STALECHECK, Boolean.parseBoolean(cp.getProperty(ConfigurationProvider.HTTP_CONNECTION_STALECHECK)));
        	}
        	
        	if (cp.getProperty(ConfigurationProvider.HTTP_PROTOCOL_SINGLE_COOKIE_HEADER) != null) {
       			params.setParameter(ConfigurationProvider.HTTP_PROTOCOL_SINGLE_COOKIE_HEADER, Boolean.parseBoolean(cp.getProperty(ConfigurationProvider.HTTP_PROTOCOL_SINGLE_COOKIE_HEADER)));
        	}
        	
        	if (cp.getProperty(ConfigurationProvider.HTTP_PROTOCOL_HANDLE_AUTHENTICATION) != null) {
       			params.setParameter(ConfigurationProvider.HTTP_PROTOCOL_HANDLE_AUTHENTICATION, Boolean.parseBoolean(cp.getProperty(ConfigurationProvider.HTTP_PROTOCOL_HANDLE_AUTHENTICATION)));
        	}
        	
        	if (cp.getProperty(ConfigurationProvider.HTTP_PROTOCOL_HANDLE_REDIRECTS) != null) {
       			params.setParameter(ConfigurationProvider.HTTP_PROTOCOL_HANDLE_REDIRECTS, Boolean.parseBoolean(cp.getProperty(ConfigurationProvider.HTTP_PROTOCOL_HANDLE_REDIRECTS)));
        	}
        	
        	if (cp.getProperty(ConfigurationProvider.HTTP_PROTOCOL_REJECT_RELATIVE_REDIRECT) != null) {
       			params.setParameter(ConfigurationProvider.HTTP_PROTOCOL_REJECT_RELATIVE_REDIRECT, Boolean.parseBoolean(cp.getProperty(ConfigurationProvider.HTTP_PROTOCOL_REJECT_RELATIVE_REDIRECT)));
        	}
        	
        	if (cp.getProperty(ConfigurationProvider.HTTP_PROTOCOL_ALLOW_CIRCULAR_REDIRECTS) != null) {
       			params.setParameter(ConfigurationProvider.HTTP_PROTOCOL_ALLOW_CIRCULAR_REDIRECTS, Boolean.parseBoolean(cp.getProperty(ConfigurationProvider.HTTP_PROTOCOL_ALLOW_CIRCULAR_REDIRECTS)));
        	}
    	}
    	catch (InternalClientException e) {
    		// Error during ConfigurationProvider.getInstance()
    		LOG.debug("Error getting Instance of ConfigurationProvider. No properties loaded.", e);
    	}
        
        client = new DefaultHttpClient(getConnectionManager(params), params);
    }
	
	private static HttpHost getHttpHostFromString(String input) {
		HttpHost httpHost = null;
		
		Matcher m = HTTP_HOST_PATTERN.matcher(input);
		if (m.find()) {
			String scheme = m.group(1);
			String hostname = m.group(2);
			int port = -1;
			if (m.group(3) != null) {
				try {
					port = Integer.parseInt(m.group(3));
				}
				catch (NumberFormatException e) {
					LOG.debug("Could not load property: " + input + " (property ignored)", e);
				}
			}
			httpHost = new HttpHost(hostname, port, scheme);
		}
		else {
			LOG.debug("Could not load property: " + input + " (property ignored)");
		}

		return httpHost;
	}
	
	private static void setIntProperty(String propertyName) {
		if (cp.getProperty(propertyName) != null) {
			try {
				params.setParameter(propertyName, Integer.parseInt(cp.getProperty(propertyName)));
			}
			catch (NumberFormatException e) {
				LOG.debug("Could not load property: " + propertyName + " (property ignored)", e);
			}
		}
	}
	
	private static void setLongProperty(String propertyName) {
		if (cp.getProperty(propertyName) != null) {
			try {
				params.setParameter(propertyName, Long.parseLong(cp.getProperty(propertyName)));
			}
			catch (NumberFormatException e) {
				LOG.debug("Could not load property: " + propertyName + " (property ignored)", e);
			}
		}
	}

	private static ThreadSafeClientConnManager getConnectionManager(final HttpParams params) {
        if (connectionManager == null) {
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));

            connectionManager = new ThreadSafeClientConnManager(params, schemeRegistry);
        }
        return connectionManager;
    }
	
}