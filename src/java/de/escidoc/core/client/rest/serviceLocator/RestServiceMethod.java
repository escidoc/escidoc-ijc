package de.escidoc.core.client.rest.serviceLocator;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

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
     * @param content
     * @return
     * @throws SystemException
     */
    public String put(final String path, final String content)
        throws SystemException {

        String result = null;
        PutMethod put = new PutMethod(this.serviceAddress + path);
        PWCallback.addEscidocUserHandleCokie(put);
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
                result = put.getResponseBodyAsString();
            }
            catch (HttpException e) {
                throw new SystemException(e.getReasonCode(), e.getMessage(), e
                    .toString());
            }
            catch (IOException e) {
                throw new SystemException(500, e.getMessage(), e.toString());
            }
            decideStatusCode(put);
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
     */
    public String post(final String path, final String content)
        throws SystemException {

        String result = null;
        PostMethod post = new PostMethod(this.serviceAddress + path);
        PWCallback.addEscidocUserHandleCokie(post);
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
                result = post.getResponseBodyAsString();
            }
            catch (HttpException e) {
                throw new SystemException(e.getReasonCode(), e.getMessage(), e
                    .toString());
            }
            catch (IOException e) {
                throw new SystemException(500, e.getMessage(), e.toString());
            }
            decideStatusCode(post);
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
     */
    public String get(final String path) throws SystemException {

        String result = null;
        GetMethod get = new GetMethod(this.serviceAddress + path);
        PWCallback.addEscidocUserHandleCokie(get);

        try {
            try {
                getRestClient().executeMethod(get);
                result = get.getResponseBodyAsString();
            }
            catch (HttpException e) {
                throw new SystemException(e.getReasonCode(), e.getMessage(), e
                    .toString());
            }
            catch (IOException e) {
                throw new SystemException(500, e.getMessage(), e.toString());
            }
            decideStatusCode(get);
        }
        finally {
            get.releaseConnection();
        }
        return result;
    }

    /**
     * Call HTTP DEL Method.
     *
     * @param path
     * @return
     * @throws SystemException
     */
    public String del(final String path) throws SystemException {

        String result = null;
        DeleteMethod del = new DeleteMethod(this.serviceAddress + path);
        PWCallback.addEscidocUserHandleCokie(del);

        try {
            try {
                getRestClient().executeMethod(del);
                result = del.getResponseBodyAsString();
            }
            catch (HttpException e) {
                throw new SystemException(e.getReasonCode(), e.getMessage(), e
                    .toString());
            }
            catch (IOException e) {
                throw new SystemException(500, e.getMessage(), e.toString());
            }
            decideStatusCode(del);
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
     * @param method the HttpMethodBase which is being executed.
     * @throws SystemException
     */
    public void decideStatusCode(HttpMethodBase method) throws SystemException {
        if (method.getStatusCode() / 100 != 2) {
            throw new SystemException(method.getStatusCode(), method
                .getStatusLine().toString(), method.getStatusText());
        }

    }

}
