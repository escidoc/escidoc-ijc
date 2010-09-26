/**
 * 
 */
package de.escidoc.core.client.soap;

import de.escidoc.core.client.ClientBase;
import de.escidoc.core.client.exceptions.InternalClientException;

/**
 * @author MVO
 *
 */
public abstract class SoapClientBase extends ClientBase {

	/**
     * Create an instance of client base using the default constructor.
     * 
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     */
    public SoapClientBase() throws InternalClientException {
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
    public SoapClientBase(final String serviceAddress)
        throws InternalClientException {
        super(serviceAddress);
    }
}
