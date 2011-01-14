/**
 * 
 */
package de.escidoc.core.client.rest;

import java.net.URL;

import de.escidoc.core.client.rest.serviceLocator.callback.RestCallbackHandler;

/**
 * @author MVO
 * 
 */
public interface RestService {

    /**
     * Set the address of the service.
     * 
     * @param address
     *            The URL to the service (http://localhost:8080)
     */
    public void setServiceAddress(final URL address);

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
    public void registerRestCallbackHandler(RestCallbackHandler handler);
}
