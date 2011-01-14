/**
 * 
 */
package de.escidoc.core.client.interfaces.base;

import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;

/**
 * @author MVO
 * 
 */
public interface Releasable<T> {

    /**
     * Release the Resource.
     * 
     * @param id
     *            Id of the resource.
     * @param taskParam
     *            TaskParam for Release.
     * @return Result.
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    Result release(final String id, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * 
     * @param resource
     * @param taskParam
     * @return Result
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    Result release(final T resource, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;
}
