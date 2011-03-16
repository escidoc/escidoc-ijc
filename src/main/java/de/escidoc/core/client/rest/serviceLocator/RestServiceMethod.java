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
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.log4j.Logger;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.rest.RestService;
import de.escidoc.core.client.rest.serviceLocator.callback.RestCallbackHandler;
import de.escidoc.core.common.URLUtility;
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

        checkNotNull(content);

        String checkedPath = checkPath(path);

        HttpPut put = new HttpPut(this.serviceAddress + checkedPath);

        invokeCallbackHandlers(put);

        StringEntity entity;
        try {
            entity = new StringEntity(content, "UTF-8");
            entity.setContentType("text/xml");
        }
        catch (UnsupportedEncodingException e1) {
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
        String checkedPath = checkPath(path);

        HttpPut put = new HttpPut(this.serviceAddress + checkedPath);

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
    public String put(final String path, final InputStream ins)
        throws SystemException, RemoteException, FileNotFoundException {

        String checkedPath = checkPath(path);

        HttpPut put = new HttpPut(this.serviceAddress + checkedPath);
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
    public String post(final String path, final String content)
        throws SystemException, RemoteException {

        String checkedPath = checkPath(path);

        HttpPost post = new HttpPost(this.serviceAddress + checkedPath);
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
    public String get(final String path) throws SystemException,
        RemoteException {

        String checkedPath = checkPath(path);

        HttpGet get = new HttpGet(this.serviceAddress + checkedPath);
        invokeCallbackHandlers(get);

        return executeRequest(get);
    }

    /**
     * @param path
     * @return
     * @throws SystemException
     * @throws RemoteException
     */
    public HttpInputStream getStream(final String path) throws SystemException,
        RemoteException {

        String checkedPath = checkPath(path);

        HttpGet get = new HttpGet(this.serviceAddress + checkedPath);
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

        String checkedPath = checkPath(path);

        HttpDelete del = new HttpDelete(this.serviceAddress + checkedPath);
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
            this.client = HttpClientFactory.getHttpClient();
        }
        return this.client;
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
        final InputStream is, final Header contentEncoding) throws IOException {

        if (is == null) {
            return null;
        }

        String encoding = "UTF-8";
        if (contentEncoding != null) {
            encoding = contentEncoding.getValue();
        }

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
    private String prepareUrl(
        final String path, final Map<String, String[]> parameters) {

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
     * @param put
     * @return
     * @throws SystemException
     * @throws RemoteException
     */
    private final String executeRequest(final HttpUriRequest request)
        throws SystemException, RemoteException {

        String result = null;
        HttpResponse response = null;
        try {
            try {
                response = getRestClient().execute(request);
                if (response.getEntity() != null) {
                    InputStream in = response.getEntity().getContent();
                    result =
                        convertStreamToString(in, response
                            .getEntity().getContentEncoding());
                }
            }
            catch (IOException e) {
                throw new SystemException(
                    HttpURLConnection.HTTP_INTERNAL_ERROR, null, e.getMessage());
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
    private final void closeConnection(
        final HttpUriRequest request, final HttpResponse response) {
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
}