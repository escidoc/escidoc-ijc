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
public interface LockingService<T> {

    /**
     * Lock the resource.
     * 
     * @param id
     *            the id of the resource to lock.
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Result lock(final String id, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException;

    /**
     * Lock the resource.
     * 
     * @param obj
     *            the resource to lock, which has to exists in the
     *            infrastructure by providing the objid.
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Result lock(final T resource, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException;

    /**
     * Unlock the resource.
     * 
     * @param id
     *            the id of the resource to unlock.
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Result unlock(final String id, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException;

    /**
     * Unlock the resource.
     * 
     * @param obj
     *            the resource to unlock, which has to exists in the
     *            infrastructure by providing the objid.
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Result unlock(final T resource, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException;
}
