/**
 * 
 */
package de.escidoc.core.client.interfaces.base;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;

/**
 * @author MVO
 * 
 */
public interface VersionPidService<T> extends ObjectPidService<T> {

    /**
     * 
     * @param resource
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Result assignVersionPid(final T resource, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param id
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Result assignVersionPid(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException;
}
