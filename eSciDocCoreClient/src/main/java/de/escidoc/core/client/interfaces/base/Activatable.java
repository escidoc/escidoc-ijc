/**
 * 
 */
package de.escidoc.core.client.interfaces.base;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.resources.common.TaskParam;

/**
 * @author MVO
 * 
 */
public interface Activatable<T> {

    /**
     * @param groupId
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    void activate(final String groupId, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException;

    /**
     * @param resource
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    void activate(final T resource, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException;

    /**
     * @param groupId
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    void deactivate(final String groupId, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException;

    /**
     * @param resource
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    void deactivate(final T resource, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException;
}
