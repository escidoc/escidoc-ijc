/**
 * 
 */
package de.escidoc.core.client.interfaces.base;

import java.net.URL;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.InternalClientException;

/**
 * @author MVO
 * 
 */
public interface HandlerService {

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
     * 
     * @return the Service Address.
     */
    URL getServiceAddress();

    /**
     * Set the Transport Protocol.
     * 
     * @param tp
     *            The Enum containing the TransportProtocol to be used).
     * @deprecated All main HandlerClient implementations (except SOAPHCs) are
     *             using only {@link TransportProtocol#REST} now.
     */
    @Deprecated
    void setTransport(final TransportProtocol tp);

    /**
     * Get the Transport Protocol.
     * 
     * @return The Enum containing the TransportProtocol to be used).
     */
    TransportProtocol getTransport();
}
