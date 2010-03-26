package de.escidoc.core.client.rest.serviceLocator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Map;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.soap.security.PWCallback;
import de.escidoc.core.common.exceptions.remote.system.SystemException;

/**
 * HTTP Methods for REST.
 * 
 * @author SWA
 * 
 */
public class RestServiceMethod {

    private String serviceAddress = null;

    private MultiThreadedHttpConnectionManager connectionManager = null;

    private HttpClient client = null;

    /**
     * Set the address of the service.
     * 
     * @param address
     *            The URL to the service (http://localhost:8080)
     * @throws MalformedURLException
     *             Thrown if the service address is not a valid URL.
     */
    public void setServiceAddress(final String address)
        throws MalformedURLException {

        URL url = new URL(address);
        this.serviceAddress = url.toString();
    }

    private synchronized HttpClient getRestClient() {

        if (this.client == null) {
            this.client = new HttpClient(getConnectionManager());
        }
        return this.client;
    }

    private synchronized MultiThreadedHttpConnectionManager getConnectionManager() {
        if (this.connectionManager == null) {
            this.connectionManager = new MultiThreadedHttpConnectionManager();
        }
        return this.connectionManager;
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
        PutMethod put = new PutMethod(this.serviceAddress + path);
        PWCallback.addEscidocUserHandleCookie(put);
        RequestEntity entity;
        try {
            entity = new StringRequestEntity(content, "text/xml", "UTF-8");
        }
        catch (UnsupportedEncodingException e1) {
            throw new SystemException(500, e1.getMessage(), "");
        }
        put.setRequestEntity(entity);

        try {
            try {
                getRestClient().executeMethod(put);
                InputStream in = put.getResponseBodyAsStream();
                result = convertStreamToString(in);
            }
            catch (HttpException e) {
                throw new SystemException(e.getReasonCode(), e.getMessage(), e
                    .toString());
            }
            catch (IOException e) {
                throw new SystemException(500, e.getMessage(), e.toString());
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
        PWCallback.addEscidocUserHandleCookie(post);
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
                result = convertStreamToString(in);
            }
            catch (HttpException e) {
                throw new SystemException(e.getReasonCode(), e.getMessage(), e
                    .toString());
            }
            catch (IOException e) {
                throw new SystemException(500, e.getMessage(), e.toString());
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
        PWCallback.addEscidocUserHandleCookie(get);

        try {
            try {
                getRestClient().executeMethod(get);
                InputStream in = get.getResponseBodyAsStream();
                result = convertStreamToString(in);
            }
            catch (HttpException e) {
                throw new SystemException(e.getReasonCode(), e.getMessage(), e
                    .toString());
            }
            catch (IOException e) {
                throw new SystemException(500, e.getMessage(), e.toString());
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
            throw new SystemException(HttpURLConnection.HTTP_INTERNAL_ERROR, e
                .getMessage(), e.toString());
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
        PWCallback.addEscidocUserHandleCookie(del);

        try {
            try {
                getRestClient().executeMethod(del);
                InputStream in = del.getResponseBodyAsStream();
                result = convertStreamToString(in);
            }
            catch (HttpException e) {
                throw new SystemException(e.getReasonCode(), e.getMessage(), e
                    .toString());
            }
            catch (IOException e) {
                throw new SystemException(500, e.getMessage(), e.toString());
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
            // throw new SystemException(method.getStatusCode(), method
            // .getStatusLine().toString(), method.getStatusText());

            ExceptionMapper.constructEscidocException(method.getStatusCode(),
                method.getStatusLine().toString(), body);
        }

    }

    /**
     * Convert inputstream into String.
     * 
     * @param is
     *            InputStream
     * @return String
     */
    private String convertStreamToString(final InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                is.close();
            }
            catch (IOException e) {
                e.printStackTrace();
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

            for (final String parameter : parameters.keySet()) {
                final String[] values = parameters.get(parameter);

                if ((values != null) && (values.length > 0)) {
                    if (!isFirstParameter) {
                        result.append('&');
                    }
                    result.append(parameter);
                    result.append('=');
                    result.append(values[0]);
                }
                isFirstParameter = false;
            }
        }
        return result.toString();
    }

}
