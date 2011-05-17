/**
 * 
 */
package de.escidoc.core.client.interfaces.base;

import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;

/**
 * 
 * @author MVO
 * 
 * @param <T>
 */
public interface Updatable<T> {

    /**
     * Update the resource in the repository.
     * 
     * @param resource
     *            The class representation of the resource, which must provide
     *            the objid and therefore exist in the infrastructure.
     * @return The updated created resource as class representation.
     * 
     * @throws EscidocClientException
     *             Thrown in case of errors from framework.
     * @throws InternalClientException
     *             Thrown in case of client library internal errors.
     * @throws TransportException
     *             Thrown in case of transport errors.
     */
    T update(T resource) throws EscidocException, InternalClientException, TransportException;

    /**
     * Update the resource in the repository.
     * 
     * @param id
     *            the objid of the resource.
     * @param resource
     *            The class representation of the resource.
     * @return The updated created resource as class representation.
     * 
     * @throws EscidocClientException
     *             Thrown in case of errors from framework.
     * @throws InternalClientException
     *             Thrown in case of client library internal errors.
     * @throws TransportException
     *             Thrown in case of transport errors.
     */
    T update(String id, T resource) throws EscidocException, InternalClientException, TransportException;
}
