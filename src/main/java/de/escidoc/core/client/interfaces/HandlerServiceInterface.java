/**
 * 
 */
package de.escidoc.core.client.interfaces;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.InternalClientException;

/**
 * @author MVO
 *
 */
public interface HandlerServiceInterface {

    /**
     * Set the Authentication Handle.
     * 
     * @return handle The Authentication Handle.
     * @throws InternalClientException
     *             Thrown if getting failed.
     */
    String getHandle() throws InternalClientException;

    /**
     * Set the Authentication Handle.
     * 
     * @param handle
     *            The Authentication Handle.
     * @throws InternalClientException
     *             Thrown if setting failed.
     */
    void setHandle(final String handle) throws InternalClientException;

    /**
     * Set the Service Address.
     * 
     * @param address
     *            The String containing the Service Address of eSciDocCore).
     * @throws InternalClientException
     *             Thrown if setting failed.
     */
    void setServiceAddress(final String address) throws InternalClientException;

    /**
     * Set the Transport Protocol.
     * 
     * @param tp
     *            The Enum containing the TransportProtocol to be used).
     */
    void setTransport(final TransportProtocol tp);
    
    /**
     * Get the Transport Protocol.
     * 
     * @return
     *          The Enum containing the TransportProtocol to be used).
     */
    TransportProtocol getTransport();
}