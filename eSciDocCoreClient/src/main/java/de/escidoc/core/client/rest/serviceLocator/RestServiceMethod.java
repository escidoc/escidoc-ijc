package de.escidoc.core.client.rest.serviceLocator;

import static de.escidoc.core.common.Precondition.checkNotNull;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.rest.RestService;
import de.escidoc.core.client.rest.serviceLocator.callback.RestCallbackHandler;
import de.escidoc.core.common.URLUtility;
import de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException;
import de.escidoc.core.common.exceptions.remote.system.SystemException;
import de.escidoc.core.resources.HttpInputStream;

/**
 * HTTP Methods for REST.
 * 
 * @author SWA
 * 
 */
public abstract class RestServiceMethod implements RestService {

    private static final Logger LOG = LoggerFactory.getLogger(RestServiceMethod.class);

    private URL serviceAddress;

    private HttpClient client;

    private final Collection<RestCallbackHandler> callbackHandlers = new LinkedList<RestCallbackHandler>();

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
    public String put(final String path, final String content) throws SystemException, RemoteException {

        checkNotNull(content);

        final String checkedPath = checkPath(path);

        final HttpPut put = new HttpPut(this.serviceAddress + checkedPath);

        invokeCallbackHandlers(put);

        StringEntity entity;
        try {
            entity = new StringEntity(content, "UTF-8");
            entity.setContentType("text/xml");
        }
        catch (final UnsupportedEncodingException e1) {
            throw new SystemException(500, e1.getMessage(), "");
        }
        put.setEntity(entity);

        return executeRequest(put);
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

        checkNotNull(f);
        final String checkedPath = checkPath(path);

        final HttpPut put = new HttpPut(this.serviceAddress + checkedPath);

        invokeCallbackHandlers(put);

        put.setEntity(new FileEntity(f, "application/octet-stream"));

        return executeRequest(put);
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
    public String put(final String path, final InputStream ins) throws SystemException, RemoteException,
        FileNotFoundException {

        final String checkedPath = checkPath(path);

        final HttpPut put = new HttpPut(this.serviceAddress + checkedPath);
        invokeCallbackHandlers(put);

        put.setEntity(new InputStreamEntity(ins, -1));
        put.addHeader("Content-type", "application/octet-stream");

        return executeRequest(put);
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
    public String post(final String path, final String content) throws SystemException, RemoteException {

        final String checkedPath = checkPath(path);

        final HttpPost post = new HttpPost(this.serviceAddress + checkedPath);
        invokeCallbackHandlers(post);
        StringEntity entity;
        try {
            entity = new StringEntity(content, "UTF-8");
            entity.setContentType("text/xml");
        }
        catch (final UnsupportedEncodingException e1) {
            throw new SystemException(500, e1.getMessage(), "");
        }
        post.setEntity(entity);
        return executeRequest(post);
    }

    /**
     * Call HTTP GET method.
     * 
     * @param path
     * @return
     * @throws SystemException
     * @throws EscidocException
     */
    public String get(final String path) throws SystemException, RemoteException {

        final String checkedPath = checkPath(path);

        final HttpGet get = new HttpGet(this.serviceAddress + checkedPath);
        invokeCallbackHandlers(get);

        return executeRequest(get);
    }

    /**
     * @param path
     * @return
     * @throws SystemException
     * @throws RemoteException
     */
    public HttpInputStream getStream(final String path) throws SystemException, RemoteException {

        final String checkedPath = checkPath(path);

        final HttpGet get = new HttpGet(this.serviceAddress + checkedPath);
        invokeCallbackHandlers(get);
        HttpClientParams.setRedirecting(get.getParams(), true);

        HttpResponse response = null;

        try {
            response = getRestClient().execute(get);
            return new HttpInputStream(get, response);
        }
        catch (final IOException e) {
            throw new SystemException(HttpURLConnection.HTTP_INTERNAL_ERROR, null, e.getMessage());
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
    public String get(final String path, final Map<String, String[]> parameters) throws RemoteException {

        final String url = prepareUrl(path, parameters);

        try {
            return get(new URLCodec().encode(url));
        }
        catch (final EncoderException e) {
            throw new SystemException(HttpURLConnection.HTTP_INTERNAL_ERROR, e.getMessage(), e.toString());
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
    public String del(final String path) throws SystemException, RemoteException {

        final String checkedPath = checkPath(path);

        final HttpDelete del = new HttpDelete(this.serviceAddress + checkedPath);
        invokeCallbackHandlers(del);

        return executeRequest(del);
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
    public void decideStatusCode(final HttpResponse response, final String body) throws SystemException,
        RemoteException {

        if (response.getStatusLine().getStatusCode() / 100 != 2) {
            final Header header = response.getFirstHeader("Location");
            String redirectLocation = null;
            if (header != null) {
                redirectLocation = header.getValue();
            }

            ExceptionMapper.constructEscidocException(body, response.getStatusLine().getStatusCode(), redirectLocation);
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
        for (final RestCallbackHandler handler : callbackHandlers) {
            handler.handleHttpMethod(method);
        }
    }

    /**
     * 
     * @return
     */
    protected synchronized HttpClient getRestClient() {
        if (this.client == null) {
            this.client = HttpClientFactory.getHttpClient();
        }
        return this.client;
    }

    /**
     * Convert InputStream into String.
     * 
     * @param is
     *            InputStream
     * @return String
     * @throws IOException
     */
    private String convertStreamToString(final InputStream is, final Header contentEncoding) throws IOException {

        if (is == null) {
            return null;
        }

        String encoding = "UTF-8";
        if (contentEncoding != null) {
            encoding = contentEncoding.getValue();
        }

        final StringBuilder sb = new StringBuilder();
        final char[] buffer = new char[1024];
        int read = -1;

        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(is, encoding));

            while ((read = reader.read(buffer)) != -1) {
                sb.append(buffer, 0, read);
            }
        }
        finally {
            try {
                is.close();
            }
            catch (final IOException e) {
                LOG.debug("Unable to close InputStream: " + e.getMessage(), e);
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
    private String prepareUrl(final String path, final Map<String, String[]> parameters) {

        StringBuffer result;
        if (path == null)
            result = new StringBuffer();
        else
            result = new StringBuffer(path);

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
            filter12 += "&maximumRecords=" + String.valueOf(filter.getMaximumRecords());
        }
        if (filter.getStartRecord() != null) {
            filter12 += "&startRecord=" + String.valueOf(filter.getStartRecord());
        }
        if (filter.getQuery() != null) {
            filter12 += "&query=";
            try {
                filter12 += URLEncoder.encode(filter.getQuery(), "UTF-8");

            }
            catch (final UnsupportedEncodingException e) {
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

        final StringBuilder result = new StringBuilder();

        for (final Entry<String, String[]> entry : params.entrySet()) {
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
     * @param put
     * @return
     * @throws SystemException
     * @throws RemoteException
     */
    private final String executeRequest(final HttpUriRequest request) throws SystemException, RemoteException {

        String result = null;
        HttpResponse response = null;
        try {
            try {
                response = getRestClient().execute(request);
                if (response.getEntity() != null) {
                    final InputStream in = response.getEntity().getContent();
                    result = convertStreamToString(in, response.getEntity().getContentEncoding());
                }
            }
            catch (final IOException e) {
                throw new SystemException(HttpURLConnection.HTTP_INTERNAL_ERROR, null, e.getMessage());
            }
            decideStatusCode(response, result);
        }
        finally {
            closeConnection(request, response);
        }
        return result;
    }

    /**
     * 
     * @param request
     * @param response
     */
    private final void closeConnection(final HttpUriRequest request, final HttpResponse response) {
        if (response != null && response.getEntity() != null) {
            try {
                response.getEntity().consumeContent();
            }
            catch (final IOException e) {
                if (LOG.isDebugEnabled())
                    LOG.debug("Unable to close HttpResponse.");
            }
        }
        if (request != null) {
            request.abort();
        }
        getRestClient().getConnectionManager().closeExpiredConnections();
    }

    /**
     * @param path
     * @return
     */
    private final String checkPath(final String path) {
        if (path.startsWith("?"))
            return path;
        if (!path.startsWith("/"))
            return "/" + path;
        return path;
    }

    /**
     * @param username
     * @param password
     * @return
     * @throws SystemException
     * @throws AuthenticationException
     */
    public String authenticate(final String username, final String password) throws SystemException,
        AuthenticationException {

        final String loginPath = checkPath("/aa/login?target=");
        final String authPath = checkPath("/aa/j_spring_security_check");

        final HttpGet get = new HttpGet(this.serviceAddress + loginPath);
        final HttpGet redirected = new HttpGet(this.serviceAddress + loginPath);
        final HttpPost post = new HttpPost(this.serviceAddress + authPath);

        final List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("j_username", username));
        formparams.add(new BasicNameValuePair("j_password", password));

        HttpClientParams.setRedirecting(get.getParams(), false);
        HttpClientParams.setRedirecting(redirected.getParams(), false);
        HttpClientParams.setRedirecting(post.getParams(), false);

        HttpResponse response = null;
        String cookie = null;
        String handle = null;
        int statusCode = 0;
        String statusMsg = null;

        try {
            response = getRestClient().execute(get);
            cookie = checkCookie(response, "Set-Cookie");
            closeConnection(get, response);

            post.setHeader("Cookie", cookie);
            post.setEntity(new UrlEncodedFormEntity(formparams, "UTF-8"));
            response = getRestClient().execute(post);
            cookie = checkCookie(response, "Set-Cookie");
            closeConnection(post, response);

            redirected.setHeader("Cookie", cookie);
            response = getRestClient().execute(redirected);
            cookie = checkCookie(response, "Set-Cookie");
            statusCode = response.getStatusLine().getStatusCode();
            statusMsg = response.getStatusLine().getReasonPhrase();
            closeConnection(redirected, response);
        }
        catch (final IOException e) {
            throw new SystemException(HttpURLConnection.HTTP_INTERNAL_ERROR, null, e.getMessage());
        }
        finally {
            closeConnection(get, response);
            closeConnection(post, response);
            closeConnection(redirected, response);
        }

        if (cookie != null && cookie.toLowerCase().startsWith("escidoccookie=")) {
            final String[] parts = cookie.split(";");
            handle = parts[0].split("=")[1];
        }

        if (handle == null) {
            throw new AuthenticationException(statusCode, "Authorization failed.", statusMsg, this.serviceAddress
                + "/aa/login");
        }
        return handle;
    }

    /**
     * @param response
     * @param name
     * @return
     * @throws SystemException
     */
    private String checkCookie(final HttpResponse response, final String name) throws SystemException {
        final Header[] cookies = response.getHeaders(name);

        if (cookies == null || cookies.length < 1)
            return null;
        else
            return cookies[0].getValue();
    }
}