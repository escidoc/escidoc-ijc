/**
 * 
 */
package de.escidoc.core.client.rest;

import java.net.URL;

import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.params.HttpClientParams;

import de.escidoc.core.client.ClientBase;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.rest.serviceLocator.callback.RestCallbackHandler;

/**
 * @author MVO
 * 
 */
public abstract class RestClientBase extends ClientBase implements RestCallbackHandler {

    public static final String ESCIDOC_COOKIE_ENTRY = "escidocCookie";

    /**
     * Create ClientBase.
     * 
     * @param serviceAddress
     *            The service endpoint address.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     */
    public RestClientBase(final URL serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     * @deprecated Use {@link RestClientBase#RestClientBase(URL)} instead.
     */
    @Deprecated
    public RestClientBase(final String serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * Adds cookie escidocCookie storing the eSciDoc user handle as the content
     * of the cookie escidocCookie to to the provided http method object.<br>
     * The adding is skipped, if the current user handle is <code>null</code> or
     * equals to an empty <code>String</code>.<br/>
     * 
     * @param method
     *            The http method object to add the cookie to.
     */
    @Override
    public void handleHttpMethod(final HttpRequestBase method) {

        if (getHandle() == null || "".equals(getHandle())) {
            return;
        }

        // method.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
        // method.getParams().setParameter(ClientPNames.COOKIE_POLICY,
        // CookiePolicy.ACCEPT_NONE);

        // HttpClientParams.setCookiePolicy(method.getParams(), cookiePolicy);

        method.addHeader("Cookie", ESCIDOC_COOKIE_ENTRY + "=" + getHandle());

        // method.setFollowRedirects(false);
        // method
        // .getParams().setParameter("http.protocol.handle-redirects", false);
        HttpClientParams.setRedirecting(method.getParams(), false);
    }
}