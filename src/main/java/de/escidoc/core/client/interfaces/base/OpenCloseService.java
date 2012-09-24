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
public interface OpenCloseService<T> {

    /**
     * Changes the state of the resource to <i>open</i>.
     * 
     * @param id
     *            objid of the resource
     * @param taskParam
     *            Task parameter
     * @return result of open method
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Result open(final String id, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException;

    /**
     * Changes the state of the resource to <i>open</i>.
     * 
     * @param resource
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Result open(final T resource, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException;

    /**
     * Changes the state of the resource to <i>close</i>.
     * 
     * @param id
     *            objid of the resource
     * @param taskParam
     *            Task parameter
     * @return result of close method
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Result close(final String id, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException;

    /**
     * 
     * @param resource
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Result close(final T resource, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException;
}
