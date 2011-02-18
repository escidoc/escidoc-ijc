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
public interface Retrievable<T> {

    /**
     * Retrieve the resource in the repository.
     * 
     * @param id
     *            The id of the resource.
     * @return The new created resource as class representation.
     * 
     * @throws EscidocClientException
     *             Thrown in case of errors from framework.
     * @throws InternalClientException
     *             Thrown in case of client library internal errors.
     * @throws TransportException
     *             Thrown in case of transport errors.
     */
    T retrieve(final String id) throws EscidocException,
        InternalClientException, TransportException;
}