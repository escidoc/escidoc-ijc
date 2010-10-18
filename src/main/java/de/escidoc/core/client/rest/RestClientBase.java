/**
 * 
 */
package de.escidoc.core.client.rest;

import de.escidoc.core.client.ClientBase;
import de.escidoc.core.client.exceptions.InternalClientException;

/**
 * @author MVO
 * 
 */
public abstract class RestClientBase extends ClientBase {

    protected boolean followRedirects = false;

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
     * 
     * @param followRedirects
     */
    public void setFollowRedirects(final boolean followRedirects) {
        this.followRedirects = followRedirects;
    }
}
