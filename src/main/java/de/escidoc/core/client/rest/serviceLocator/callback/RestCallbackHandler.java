/**
 * 
 */
package de.escidoc.core.client.rest.serviceLocator.callback;

import org.apache.commons.httpclient.HttpMethod;

import de.escidoc.core.client.rest.serviceLocator.RestServiceMethod;

/**
 * An implementation of this interface set content for each HTTP method before
 * the HTTP method is being executed. Such handlers can be registered to an
 * implementation of the {@link RestServiceMethod}. The first handler, which got
 * registered, will be the first one, which is being called. Note, that each
 * successive handler may be able to overwrite content set by each preceding
 * handler.
 * 
 * @author MVO
 * 
 */
public interface RestCallbackHandler {

    /**
     * Adds related content to the provided HHTP method object.
     * 
     * @param method
     *            The HTTP method object to add the cookie to.
     */
    void handleHttpMethod(final HttpMethod method);
}
