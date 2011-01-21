package de.escidoc.core.client.rest.serviceLocator;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.log4j.Logger;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.rest.RestService;
import de.escidoc.core.client.rest.serviceLocator.callback.RestCallbackHandler;
import de.escidoc.core.common.URLUtility;
import de.escidoc.core.common.configuration.ConfigurationProvider;
import de.escidoc.core.common.exceptions.remote.system.SystemException;
import de.escidoc.core.resources.HttpInputStream;

/**
 * HTTP Methods for REST.
 * 
 * @author SWA
 * 
 */
public abstract class RestServiceMethod implements RestService {

    private static final Logger LOG = Logger.getLogger(RestServiceMethod.class);

    private URL serviceAddress;

    // private MultiThreadedHttpConnectionManager connectionManager;
    private ThreadSafeClientConnManager connectionManager;

    private HttpClient client;

    private final Collection<RestCallbackHandler> callbackHandlers =
        new LinkedList<RestCallbackHandler>();

    /**
     * Set the address of the service.
     * 
     * @param address
     *            The URL to the service (http://localhost:8080)
     * @throws MalformedURLException
     *             Thrown if the service address is not a valid URL.
     */
    @Override
    public void setServiceAddress(final URL address) {
        this.serviceAddress = URLUtility.unifyAddress(address);
    }

    /**
     * Call HTTP PUT method.
     * 
     * @param path
     *            path of resource
     * @param content
     *            Content (which is to send as request body)
     * @return Response body as String
     * @throws SystemException
     *             Thrown if request failed.
     * @throws EscidocException
     */
    public String put(final String path, final String content)
        throws SystemException, RemoteException {

        String result = null;
        HttpPut put = new HttpPut(this.serviceAddress + path);

        invokeCallbackHandlers(put);

        if (content != null) {
            StringEntity entity;
            try {
                entity = new StringEntity(content, "UTF-8");
                entity.setContentType("text/xml");
            }
            catch (UnsupportedEncodingException e1) {
                throw new SystemException(500, e1.getMessage(), "");
            }
            put.setEntity(entity);
        }

        HttpResponse response = null;
        try {
            try {
                response = getRestClient().execute(put);
                InputStream in = response.getEntity().getContent();
                result = convertStreamToString(in);
            }
            catch (IOException e) {
                throw new SystemException(500, null, e.getMessage());
            }
            decideStatusCode(response, result);
        }
        finally {
            closeConnection(put, response);
        }
        return result;
    }

    /**
     * Call HTTP PUT method.
     * 
     * @param path
     *            path of resource
     * @param ins
     *            InputStream of content
     * @return Response body as String
     * @throws IOException
     * @throws EscidocException
     */
    public String put(final String path, final File f) throws IOException {

        String result = null;
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(f);
            result = put(path, fin);
        }
        catch (IOException e) {
            throw e;
        }
        finally {
            if (fin != null)
                try {
                    fin.close();
                }
                catch (IOException e) {
                    LOG.debug("Unable to close FileInputStream.", e);
                }
        }

        return result;

    }

    /**
     * Call HTTP PUT method.
     * 
     * @param path
     *            path of resource
     * @param ins
     *            InputStream of content
     * @return Response body as String
     * @throws SystemException
     *             Thrown if request failed.
     * @throws FileNotFoundException
     * @throws EscidocException
     */
    public String put(final String path, final InputStream ins)
        throws SystemException, RemoteException, FileNotFoundException {

        String result = null;
        HttpPut put = new HttpPut(this.serviceAddress + path);
        invokeCallbackHandlers(put);

        InputStreamEntity entity = new InputStreamEntity(ins, -1);
        put.setEntity(entity);

        HttpResponse response = null;
        try {
            try {
                put.addHeader("Content-type", "application/octet-stream");
                response = getRestClient().execute(put);
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode / 100 != 2) {
                    throw new RemoteException("Upload failed. "
                        + response.getStatusLine().getReasonPhrase());
                }
                InputStream in = response.getEntity().getContent();
                result = convertStreamToString(in);
            }
            catch (IOException e) {
                throw new SystemException(
                    HttpURLConnection.HTTP_INTERNAL_ERROR, null, e.getMessage());
            }
            decideStatusCode(response, result);
        }
        finally {
            closeConnection(put, response);
        }
        return result;
    }

    /**
     * Call HTTP POST method.
     * 
     * @param path
     * @param content
     * @return
     * @throws SystemException
     * @throws EscidocException
     */
    public String post(final String path, final String content)
        throws SystemException, RemoteException {

        String result = null;
        HttpPost post = new HttpPost(this.serviceAddress + path);
        invokeCallbackHandlers(post);
        StringEntity entity;
        try {
            entity = new StringEntity(content, "UTF-8");
            entity.setContentType("text/xml");
        }
        catch (UnsupportedEncodingException e1) {
            throw new SystemException(500, e1.getMessage(), "");
        }
        post.setEntity(entity);

        HttpResponse response = null;
        try {
            try {
                response = getRestClient().execute(post);
                InputStream in = response.getEntity().getContent();
                result = convertStreamToString(in);
            }
            catch (IOException e) {
                throw new SystemException(
                    HttpURLConnection.HTTP_INTERNAL_ERROR, null, e.getMessage());
            }
            decideStatusCode(response, result);
        }
        finally {
            closeConnection(post, response);
        }
        return result;
    }

    /**
     * Call HTTP GET method.
     * 
     * @param path
     * @return
     * @throws SystemException
     * @throws EscidocException
     */
    public String get(final String path) throws SystemException,
        RemoteException {

        String result = null;
        HttpGet get = new HttpGet(this.serviceAddress + path);
        invokeCallbackHandlers(get);

        HttpResponse response = null;
        try {
            try {
                response = getRestClient().execute(get);
                InputStream in = response.getEntity().getContent();
                result = convertStreamToString(in);
            }
            catch (IOException e) {
                throw new SystemException(
                    HttpURLConnection.HTTP_INTERNAL_ERROR, null, e.getMessage());
            }
            decideStatusCode(response, result);
        }
        finally {
            closeConnection(get, response);
        }
        return result;
    }

    /**
     * @param path
     * @return
     * @throws SystemException
     * @throws RemoteException
     */
    public HttpInputStream getStream(final String path) throws SystemException,
        RemoteException {

        HttpGet get = new HttpGet(this.serviceAddress + path);
        invokeCallbackHandlers(get);

        HttpResponse response = null;

        try {
            response = getRestClient().execute(get);
            return new HttpInputStream(get, response);
        }
        catch (IOException e) {
            throw new SystemException(HttpURLConnection.HTTP_INTERNAL_ERROR,
                null, e.getMessage());
        }
    }

    /**
     * Call HTTP GET to a filter method.
     * 
     * @param path
     *            path to the filter method
     * @param parameters
     *            map containing the request parameters as key - value pairs
     * 
     * @return encoded GET URL
     * @throws RemoteException
     *             thrown if an internal error occurred
     * @throws EscidocException
     */
    public String get(final String path, final Map<String, String[]> parameters)
        throws RemoteException {

        String url = prepareUrl(path, parameters);

        try {
            return get(new URLCodec().encode(url));
        }
        catch (EncoderException e) {
            throw new SystemException(HttpURLConnection.HTTP_INTERNAL_ERROR,
                e.getMessage(), e.toString());
        }
    }

    /**
     * Call HTTP DEL Method.
     * 
     * @param path
     * @return Status Code
     * @throws SystemException
     * @throws EscidocException
     */
    public String del(final String path) throws SystemException,
        RemoteException {

        String result = null;
        HttpDelete del = new HttpDelete(this.serviceAddress + path);
        invokeCallbackHandlers(del);

        HttpResponse response = null;
        try {
            try {
                response = getRestClient().execute(del);
                InputStream in = response.getEntity().getContent();
                result = convertStreamToString(in);
            }
            catch (IOException e) {
                throw new SystemException(
                    HttpURLConnection.HTTP_INTERNAL_ERROR, null, e.getMessage());
            }
            decideStatusCode(response, result);
        }
        finally {
            closeConnection(del, response);
        }
        return result;
    }

    /**
     * Decide on status code. If the http status code is not one of 2xx, then
     * throw a SystemException with the respective status code, status line and
     * status text.
     * 
     * @param response
     *            the HttpMethodBase which is being executed.
     * @throws SystemException
     */
    public void decideStatusCode(final HttpResponse response, final String body)
        throws SystemException, RemoteException {

        if (response.getStatusLine().getStatusCode() / 100 != 2) {
            Header header = response.getFirstHeader("Location");
            String redirectLocation = null;
            if (header != null) {
                redirectLocation = header.getValue();
            }

            ExceptionMapper.constructEscidocException(body, response
                .getStatusLine().getStatusCode(), redirectLocation);
        }

    }

    /**
     * Registers an implementation of the {@link RestCallbackHandler} interface
     * to this instance. All chained handlers will be called to set content for
     * each HTTP method before the HTTP method is being executed. The first
     * handler, which got registered, will be the first one, which is being
     * called. Note, that each successive handler may be able to overwrite
     * content set by each preceding handler.<br/>
     * If the specified handler is <code>null</code>, it will be ignored.
     * 
     * @param handler
     *            the handler to register.
     */
    @Override
    public void registerRestCallbackHandler(final RestCallbackHandler handler) {
        if (handler != null) {
            callbackHandlers.add(handler);
        }
    }

    /**
     * Calls the registered callback handlers to add their cookies to the HTTP
     * method.
     * 
     * @param method
     */
    private void invokeCallbackHandlers(final HttpRequestBase method) {
        for (RestCallbackHandler handler : callbackHandlers) {
            handler.handleHttpMethod(method);
        }
    }
    
    /**
     * 
     * @return
     */
    private synchronized HttpClient getRestClient() {

        if (this.client == null) {
        	HttpParams params = new BasicHttpParams();
        	
        	try {
        		ConfigurationProvider cp = ConfigurationProvider.getInstance();
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
	        		
	        		try {
	        			HttpRoute httpRoute = new HttpRoute(getHttpHostFromString(data));
	        			params.setParameter(ConfigurationProvider.HTTP_ROUTE_FORCED_ROUTE, httpRoute);
	        		}
        			catch (NumberFormatException e) {
	        			// TODO: proper exception handling
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
	        			// TODO: proper exception handling
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
						// TODO: proper exception handling
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
           				// TODO: proper exception handling
           			}
           			j++;
				}
        		if (defaultHeaders.size() > 0) {
        			params.setParameter(ConfigurationProvider.HTTP_DEFAULT_HEADERS, defaultHeaders);
        		}

        		// custom format for InetAddress [<hostname>/]<ipAddress>
        		if (cp.getProperty(ConfigurationProvider.HTTP_ROUTE_LOCAL_ADDRESS) != null) {
	        		String data = cp.getProperty(ConfigurationProvider.HTTP_ROUTE_LOCAL_ADDRESS);

	        		String hostname = "";
	        		String[] tmp = data.split("/");
	        		try {
		        		if (tmp.length > 1) {
		        			hostname = tmp[0];
		        			tmp[0] = tmp[1];
		        		}
		        		tmp = tmp[0].split("\\.");
		        		byte[] ipaddr = new byte[tmp.length];
	        			for (int k = 0; k < tmp.length; k++) {
		        			ipaddr[k] = Byte.parseByte(tmp[k]);
		        		}
		        		InetAddress inetAddress = InetAddress.getByAddress(hostname, ipaddr);
						params.setParameter(ConfigurationProvider.HTTP_ROUTE_LOCAL_ADDRESS, inetAddress);
					}
	        		catch (UnknownHostException e) {
						// TODO: proper exception handling
					}
	        		catch (NumberFormatException e) {
	        			// TODO: proper exception handling
	        		}
	        		catch (IndexOutOfBoundsException e) {
	        			// TODO: proper exception handling
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
	        		catch (IndexOutOfBoundsException e) {
	        			// TODO: proper exception handling
	        		}
	        		catch (NumberFormatException e) {
	        			// TODO: proper exception handling
	        		}
	        	}
	        	
	        	// custom format for HttpHost [<scheme>://]<host>[:<port>]
	        	if (cp.getProperty(ConfigurationProvider.HTTP_ROUTE_DEFAULT_PROXY) != null) {
	        		String data = cp.getProperty(ConfigurationProvider.HTTP_ROUTE_DEFAULT_PROXY);
	        		try {
	        			params.setParameter(ConfigurationProvider.HTTP_ROUTE_DEFAULT_PROXY, getHttpHostFromString(data));
	        		}
	        		catch (NumberFormatException e) {
	        			// TODO: proper exception handling
	        		}
	        	
	        	}
	        	
	        	if (cp.getProperty(ConfigurationProvider.HTTP_VIRTUAL_HOST) != null) {
	        		String data = cp.getProperty(ConfigurationProvider.HTTP_VIRTUAL_HOST);
	        		
	        		try {
	        			params.setParameter(ConfigurationProvider.HTTP_VIRTUAL_HOST, getHttpHostFromString(data));
	        		}
	        		catch (NumberFormatException e) {
	        			// TODO: proper exception handling
	        		}
	        	}
	        	
	        	if (cp.getProperty(ConfigurationProvider.HTTP_DEFAULT_HOST) != null) {
	        		String data = cp.getProperty(ConfigurationProvider.HTTP_DEFAULT_HOST);
	        		
	        		try {
	        			params.setParameter(ConfigurationProvider.HTTP_DEFAULT_HOST, getHttpHostFromString(data));
	        		}
        			catch (NumberFormatException e) {
	        			// TODO: proper exception handling
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
	        	if (cp.getProperty(ConfigurationProvider.HTTP_SOCKET_TIMEOUT) != null) {
	        		try {
	        			params.setParameter(ConfigurationProvider.HTTP_SOCKET_TIMEOUT, Integer.parseInt(cp.getProperty(ConfigurationProvider.HTTP_SOCKET_TIMEOUT)));
	        		}
	        		catch (NumberFormatException e) {
	        			// TODO: proper exception handling
	        		}
	        	}
	        	
	        	if (cp.getProperty(ConfigurationProvider.HTTP_SOCKET_LINGER) != null) {
	        		try {
	        			params.setParameter(ConfigurationProvider.HTTP_SOCKET_LINGER, Integer.parseInt(cp.getProperty(ConfigurationProvider.HTTP_SOCKET_LINGER)));
	        		}
	        		catch (NumberFormatException e) {
	        			// TODO: proper exception handling
	        		}
	        	}
	        	
	        	if (cp.getProperty(ConfigurationProvider.HTTP_SOCKET_BUFFER_SIZE) != null) {
	        		try {
	        			params.setParameter(ConfigurationProvider.HTTP_SOCKET_BUFFER_SIZE, Integer.parseInt(cp.getProperty(ConfigurationProvider.HTTP_SOCKET_BUFFER_SIZE)));
	        		}
	        		catch (NumberFormatException e) {
	        			// TODO: proper exception handling
	        		}
	        	}
	        	
	        	if (cp.getProperty(ConfigurationProvider.HTTP_CONNECTION_TIMEOUT) != null) {
	        		try {
	        			params.setParameter(ConfigurationProvider.HTTP_CONNECTION_TIMEOUT, Integer.parseInt(cp.getProperty(ConfigurationProvider.HTTP_CONNECTION_TIMEOUT)));
	        		}
	        		catch (NumberFormatException e) {
	        			// TODO: proper exception handling
	        		}
	        	}
	        	
	        	if (cp.getProperty(ConfigurationProvider.HTTP_CONNECTION_MAX_LINE_LENGTH) != null) {
	        		try {
	        			params.setParameter(ConfigurationProvider.HTTP_CONNECTION_MAX_LINE_LENGTH, Integer.parseInt(cp.getProperty(ConfigurationProvider.HTTP_CONNECTION_MAX_LINE_LENGTH)));
	        		}
	        		catch (NumberFormatException e) {
	        			// TODO: proper exception handling
	        		}
	        	}
	        	
	        	if (cp.getProperty(ConfigurationProvider.HTTP_CONNECTION_MAX_HEADER_COUNT) != null) {
	        		try {
	        			params.setParameter(ConfigurationProvider.HTTP_CONNECTION_MAX_HEADER_COUNT, Integer.parseInt(cp.getProperty(ConfigurationProvider.HTTP_CONNECTION_MAX_HEADER_COUNT)));
	        		}
	        		catch (NumberFormatException e) {
	        			// TODO: proper exception handling
	        		}
	        	}
	        	
	        	if (cp.getProperty(ConfigurationProvider.HTTP_CONNECTION_MAX_STATUS_LINE_GARBAGE) != null) {
	        		try {
	        			params.setParameter(ConfigurationProvider.HTTP_CONNECTION_MAX_STATUS_LINE_GARBAGE, Integer.parseInt(cp.getProperty(ConfigurationProvider.HTTP_CONNECTION_MAX_STATUS_LINE_GARBAGE)));
	        		}
	        		catch (NumberFormatException e) {
	        			// TODO: proper exception handling
	        		}
	        	}
	        	
	        	if (cp.getProperty(ConfigurationProvider.HTTP_CONN_MANAGER_MAX_TOTAL) != null) {
	        		try {
	        			params.setParameter(ConfigurationProvider.HTTP_CONN_MANAGER_MAX_TOTAL, Integer.parseInt(cp.getProperty(ConfigurationProvider.HTTP_CONN_MANAGER_MAX_TOTAL)));
	        		}
	        		catch (NumberFormatException e) {
	        			// TODO: proper exception handling
	        		}
	        	}
	        	
	        	if (cp.getProperty(ConfigurationProvider.HTTP_PROTOCOL_MAX_REDIRECTS) != null) {
	        		try {
	        			params.setParameter(ConfigurationProvider.HTTP_PROTOCOL_MAX_REDIRECTS, Integer.parseInt(cp.getProperty(ConfigurationProvider.HTTP_PROTOCOL_MAX_REDIRECTS)));
	        		}
	        		catch (NumberFormatException e) {
	        			// TODO: proper exception handling
	        		}
	        	}
	        	
	        	// long parameters
	        	if (cp.getProperty(ConfigurationProvider.HTTP_CONN_MANAGER_TIMEOUT) != null) {
	        		try {
	        			params.setParameter(ConfigurationProvider.HTTP_CONN_MANAGER_TIMEOUT, Long.parseLong(cp.getProperty(ConfigurationProvider.HTTP_CONN_MANAGER_TIMEOUT)));
	        		}
	        		catch (NumberFormatException e) {
	        			// TODO: proper exception handling
	        		}
	        	}
	        	
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
        		// TODO: proper exception handling
        	}
            
            this.client =
                new DefaultHttpClient(getConnectionManager(params), params);
        }
        return this.client;
    }
    
    private HttpHost getHttpHostFromString(String input) throws NumberFormatException {
    	String scheme = null;
    	String hostname;
		int port = -1;
		
		String[] tmp = input.split("://");
		if (tmp.length > 1) {
			scheme = tmp[0];
			tmp[0] = tmp[1];
		}

		tmp = tmp[0].split(":");
		hostname = tmp[0];
		
		if (tmp.length > 1) {
			port = Integer.parseInt(tmp[1]);
		}
		
		return new HttpHost(hostname, port, scheme);
    }

    /**
     * 
     * @return
     */
    private synchronized ThreadSafeClientConnManager getConnectionManager(
        final HttpParams params) {
        if (this.connectionManager == null) {
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme("http", PlainSocketFactory
                .getSocketFactory(), 80));

            this.connectionManager =
                new ThreadSafeClientConnManager(params, schemeRegistry);
        }
        return this.connectionManager;
    }

    /**
     * Convert inputstream into String.
     * 
     * @param is
     *            InputStream
     * @return String
     */
    private String convertStreamToString(final InputStream is) {

        if (is == null) {
            return null;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        }
        catch (IOException e) {
            LOG.debug("An error occured while reading from stream.", e);
        }
        finally {
            try {
                is.close();
            }
            catch (IOException e) {
                LOG.debug("Unable to close InputStream.");
            }
        }

        return sb.toString();
    }

    /**
     * Add a parameter map from an Map to an URL.
     * 
     * @param path
     *            The plain path
     * @param parameters
     *            The parameter map
     * @return URL with URL parameters (The URL is not URL encoded!)
     */
    private String prepareUrl(
        final String path, final Map<String, String[]> parameters) {

        StringBuffer result = new StringBuffer(path);

        if (parameters != null) {
            result.append('?');

            boolean isFirstParameter = true;

            for (final Entry<String, String[]> entry : parameters.entrySet()) {

                if (entry.getValue() != null && entry.getValue().length > 0) {
                    if (!isFirstParameter) {
                        result.append('&');
                    }
                    result.append(entry.getKey());
                    result.append('=');
                    result.append(entry.getValue()[0]);
                }
                isFirstParameter = false;
            }
        }
        return result.toString();
    }

    /**
     * Converts a SRW SearchRetrieveRequest to the data structure for filter
     * requests for eSciDoc (version 1.2).
     * 
     * @param filter
     *            SRW SearchRetrieveRequest
     * @return data structure for filter requests for eSciDoc (version 1.2)
     */
    protected String getEscidoc12Filter(final SearchRetrieveRequestType filter) {

        String filter12 = "?operation=searchRetrieve";

        if (filter.getMaximumRecords() != null) {
            filter12 +=
                "&maximumRecords=" + String.valueOf(filter.getMaximumRecords());
        }
        if (filter.getStartRecord() != null) {
            filter12 +=
                "&startRecord=" + String.valueOf(filter.getStartRecord());
        }
        if (filter.getQuery() != null) {
            filter12 += "&query=";
            try {
                filter12 += URLEncoder.encode(filter.getQuery(), "UTF-8");

            }
            catch (UnsupportedEncodingException e) {
                // This should never happen.
            }
        }
        if (filter.getVersion() != null) {
            filter12 += "&version=" + filter.getVersion();
        }
        if (filter.getRecordPacking() != null) {
            filter12 += "&recordPacking=" + filter.getRecordPacking();
        }

        return filter12;
    }

    /**
     * Converts a SRW ExplainRequest to the data structure for filter requests
     * for eSciDoc (version 1.2).
     * 
     * @param filter
     *            SRW ExplainRequest
     * @return data structure for filter requests for eSciDoc (version 1.2)
     */
    protected String getEscidoc12Filter(final ExplainRequestType filter) {

        String filter12 = "?operation=explain";

        if (filter.getVersion() != null) {
            filter12 += "&version=" + filter.getVersion();
        }

        return filter12;
    }

    /**
     * @param params
     * @return
     */
    protected String getGetParams(final Map<String, String[]> params) {

        if (params == null)
            return null;

        StringBuilder result = new StringBuilder();

        for (Entry<String, String[]> entry : params.entrySet()) {
            for (int i = 0; i < entry.getValue().length; i++) {
                result.append("&");
                result.append(entry.getKey());
                result.append("=");
                result.append(entry.getValue()[i]);
            }
        }

        if (result.length() > 0)
            return result.substring(1);

        return null;
    }

    /**
     * 
     * @param request
     * @param response
     */
    private final void closeConnection(
        final HttpRequestBase request, final HttpResponse response) {
        if (response != null && response.getEntity() != null) {
            try {
                response.getEntity().consumeContent();
            }
            catch (IOException e) {
                if (LOG.isDebugEnabled())
                    LOG.debug("Unable to close HttpResponse.");
            }
        }
        else if (request != null) {
            request.abort();
        }
    }
}