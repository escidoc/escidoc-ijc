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
     * Adds cookie escidocCookie storing the eSciDoc user handle as the content
     * of the cookie escidocCookie to to the provided HTTP method object.<br>
     * The adding is skipped, if the current user handle is <tt>null</tt> or
     * equals to an empty <tt>String</tt>.<br/>
     * 
     * @param method
     *            The HTTP method object to add the cookie to.
     */
    @Override
    public void handleHttpMethod(final HttpRequestBase method) {
        HttpClientParams.setRedirecting(method.getParams(), false);

        if (getHandle() == null || "".equals(getHandle())) {
            return;
        }
        method.addHeader("Cookie", ESCIDOC_COOKIE_ENTRY + "=" + getHandle());
    }
}