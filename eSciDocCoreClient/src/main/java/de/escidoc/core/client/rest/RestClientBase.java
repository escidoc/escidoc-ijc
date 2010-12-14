/**
 * 
 */
package de.escidoc.core.client.rest;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.cookie.CookiePolicy;

import de.escidoc.core.client.ClientBase;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.rest.serviceLocator.callback.RestCallbackHandler;

/**
 * @author MVO
 * 
 */
public abstract class RestClientBase extends ClientBase
    implements RestCallbackHandler {

    public static final String ESCIDOC_COOKIE_ENTRY = "escidocCookie";

    /**
     * Create an instance of client base using the default constructor.
     * 
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     */
    public RestClientBase() throws InternalClientException {
        super();
    }

    /**
     * Create ClientBase.
     * 
     * @param serviceAddress
     *            The service endpoint address.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     */
    public RestClientBase(final String serviceAddress)
        throws InternalClientException {
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
    public void handleHttpMethod(final HttpMethod method) {

        if (getHandle() == null || "".equals(getHandle())) {
            return;
        }
        method.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
        method.setRequestHeader("Cookie", ESCIDOC_COOKIE_ENTRY + "="
            + getHandle());
        method.setFollowRedirects(false);
    }
}