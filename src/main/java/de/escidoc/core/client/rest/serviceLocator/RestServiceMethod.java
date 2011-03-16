package de.escidoc.core.client.rest.serviceLocator;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.FileRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.log4j.Logger;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.rest.RestService;
import de.escidoc.core.client.rest.serviceLocator.callback.RestCallbackHandler;
import de.escidoc.core.common.exceptions.remote.system.SystemException;

/**
 * HTTP Methods for REST.
 * 
 * @author SWA
 * 
 */
public abstract class RestServiceMethod implements RestService {

    private static final Logger LOG = Logger.getLogger(RestServiceMethod.class);

    private String serviceAddress;

    private MultiThreadedHttpConnectionManager connectionManager;

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
    public void setServiceAddress(final String address)
        throws MalformedURLException {

        URL url = new URL(address);
        this.serviceAddress = unifyAddress(url.toString());
    }

    /**
     * Call HTTP PUT method.
     * 
     * @param path
     *            path of resource
     * @param xmlContent
     *            Content (which is to send as request body)
     * @return Response body as String
     * @throws SystemException
     *             Thrown if request failed.
     * @throws EscidocException
     */
    public String put(final String path, final String xmlContent)
        throws RemoteException {

        if (xmlContent == null)
            throw new IllegalArgumentException(
                "Specified xmlContent must not be null.");

        PutMethod put = new PutMethod(this.serviceAddress + path);

        invokeCallbackHandlers(put);

        try {
            put.setRequestEntity(new StringRequestEntity(xmlContent,
                "text/xml", "UTF-8"));
        }
        catch (UnsupportedEncodingException e1) {
            throw new SystemException(500, e1.getMessage(), "");
        }

        return executePut(put);
    }

    /**
     * @param path
     * @param f
     * @return
     * @throws RemoteException
     */
    public String put(final String path, final File file)
        throws RemoteException {

        if (file == null)
            throw new IllegalArgumentException(
                "Specified file must not be null.");

        PutMethod put = new PutMethod(this.serviceAddress + path);

        invokeCallbackHandlers(put);

        put.setRequestEntity(new FileRequestEntity(file,
            "application/octet-stream"));

        return executePut(put);
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
        throws RemoteException {

        if (ins == null)
            throw new IllegalArgumentException(
                "Specified InputStream must not be null.");

        PutMethod put = new PutMethod(this.serviceAddress + path);
        invokeCallbackHandlers(put);

        put.setRequestEntity(new InputStreamRequestEntity(ins,
            "application/octet-stream"));

        return executePut(put);
    }

    /**
     * @param put
     * @return
     * @throws RemoteException
     */
    private String executePut(final PutMethod put) throws RemoteException {
        String result = null;
        int statusCode = -1;

        try {
            try {
                statusCode = getRestClient().executeMethod(put);
                // TODO do we need this here
                // if (statusCode / 100 != 2) {
                // throw new RemoteException("Upload failed. "
                // + put.getStatusText() + "; "
                // + new String(put.getResponseBody()));
                // }
                InputStream in = put.getResponseBodyAsStream();
                result = convertStreamToString(in, put.getResponseCharSet());
            }
            catch (HttpException e) {
                statusCode = statusCode == -1 ? e.getReasonCode() : statusCode;
                throw new SystemException(statusCode, null, e.getMessage());
            }
            catch (IOException e) {
                throw new SystemException(
                    HttpURLConnection.HTTP_INTERNAL_ERROR, null, e.getMessage());
            }
            decideStatusCode(put, result);
        }
        finally {
            put.releaseConnection();
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
        PostMethod post = new PostMethod(this.serviceAddress + path);
        invokeCallbackHandlers(post);
        RequestEntity entity;
        try {
            entity = new StringRequestEntity(content, "text/xml", "UTF-8");
        }
        catch (UnsupportedEncodingException e1) {
            throw new SystemException(500, e1.getMessage(), "");
        }
        post.setRequestEntity(entity);

        try {
            try {
                getRestClient().executeMethod(post);
                InputStream in = post.getResponseBodyAsStream();
                result = convertStreamToString(in, post.getResponseCharSet());
            }
            catch (HttpException e) {
                throw new SystemException(e.getReasonCode(), null,
                    e.getMessage());
            }
            catch (IOException e) {
                throw new SystemException(
                    HttpURLConnection.HTTP_INTERNAL_ERROR, null, e.getMessage());
            }
            decideStatusCode(post, result);
        }
        finally {
            post.releaseConnection();
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
        GetMethod get = new GetMethod(this.serviceAddress + path);
        invokeCallbackHandlers(get);

        try {
            try {
                getRestClient().executeMethod(get);
                InputStream in = get.getResponseBodyAsStream();
                result = convertStreamToString(in, get.getResponseCharSet());
            }
            catch (HttpException e) {
                throw new SystemException(e.getReasonCode(), null,
                    e.getMessage());
            }
            catch (IOException e) {
                throw new SystemException(
                    HttpURLConnection.HTTP_INTERNAL_ERROR, null, e.getMessage());
            }
            decideStatusCode(get, result);
        }
        finally {
            get.releaseConnection();
        }
        return result;
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
        DeleteMethod del = new DeleteMethod(this.serviceAddress + path);
        invokeCallbackHandlers(del);

        try {
            try {
                getRestClient().executeMethod(del);
                InputStream in = del.getResponseBodyAsStream();

                result = convertStreamToString(in, del.getResponseCharSet());
            }
            catch (HttpException e) {
                throw new SystemException(e.getReasonCode(), null,
                    e.getMessage());
            }
            catch (IOException e) {
                throw new SystemException(
                    HttpURLConnection.HTTP_INTERNAL_ERROR, null, e.getMessage());
            }
            decideStatusCode(del, result);
        }
        finally {
            del.releaseConnection();
        }
        return result;
    }

    /**
     * Decide on status code. If the http status code is not one of 2xx, then
     * throw a SystemException with the respective status code, status line and
     * status text.
     * 
     * @param method
     *            the HttpMethodBase which is being executed.
     * @throws SystemException
     */
    public void decideStatusCode(final HttpMethodBase method, final String body)
        throws SystemException, RemoteException {

        if (method.getStatusCode() / 100 != 2) {
            Header header = method.getResponseHeader("Location");
            String redirectLocation = null;
            if (header != null) {
                redirectLocation = header.getValue();
            }

            ExceptionMapper.constructEscidocException(body,
                method.getStatusCode(), redirectLocation);
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
    private void invokeCallbackHandlers(final HttpMethodBase method) {
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
            this.client = new HttpClient(getConnectionManager());

            if (isProxyRequired()) {

                String host = System.getProperty("http.proxyHost");
                Integer port =
                    Integer
                        .parseInt(System.getProperty("http.proxyPort", "80"));

                if (host != null) {

                    LOG.debug("Using proxy for service: " + serviceAddress);
                    LOG.debug("http.proxyHost: " + host);
                    LOG.debug("http.proxyPort: " + port);

                    client.getHostConfiguration().setProxy(host, port);

                    String user = System.getProperty("http.proxyUser");
                    String pass = System.getProperty("http.proxyPassword");

                    if (user != null && pass != null) {
                        HttpState state = new HttpState();
                        state.setProxyCredentials(new AuthScope(host, port),
                            new UsernamePasswordCredentials(user, pass));
                        client.setState(state);
                    }
                }
            }
        }
        return this.client;
    }

    /**
     * @return true if and only if the serviceAddress does not match the
     *         nonProxyHosts setting.
     */
    private boolean isProxyRequired() {

        String nonProxyHosts = System.getProperty("http.nonProxyHosts");
        if (nonProxyHosts != null && !nonProxyHosts.trim().equals("")) {

            nonProxyHosts = nonProxyHosts.replaceAll("\\.", "\\\\.");
            nonProxyHosts = nonProxyHosts.replaceAll("\\?", "\\\\?");
            nonProxyHosts = nonProxyHosts.replaceAll("\\*", "\\.\\*\\?");

            Pattern p = Pattern.compile(nonProxyHosts);
            Matcher m = p.matcher(serviceAddress);
            if (m.find()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 
     * @return
     */
    private synchronized MultiThreadedHttpConnectionManager getConnectionManager() {
        if (this.connectionManager == null) {
            this.connectionManager = new MultiThreadedHttpConnectionManager();
        }
        return this.connectionManager;
    }

    /**
     * Convert inputstream into String.
     * 
     * @param is
     *            InputStream
     * @return String
     * @throws IOException
     */
    private String convertStreamToString(
        final InputStream is, final String responseEnc) throws IOException {

        if (is == null) {
            return null;
        }

        String encoding = "UTF-8";
        if (responseEnc != null)
            encoding = responseEnc;

        StringBuilder sb = new StringBuilder();
        char[] buffer = new char[1024];
        int read = -1;

        try {
            BufferedReader reader =
                new BufferedReader(new InputStreamReader(is, encoding));

            while ((read = reader.read(buffer)) != -1) {
                sb.append(buffer, 0, read);
            }
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
     * Unify URL (with trailing slash).
     * 
     * @param address
     *            The address (URL)
     * @return address with slash at the end.
     */
    private String unifyAddress(final String address) {

        String tmpServUrl = address;
        if (tmpServUrl.endsWith("/")) {
            tmpServUrl = tmpServUrl.substring(0, tmpServUrl.length() - 1);
        }

        return tmpServUrl;
    }
}
