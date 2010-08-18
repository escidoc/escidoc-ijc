/*
 * CDDL HEADER START
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License, Version 1.0 only
 * (the "License").  You may not use this file except in compliance
 * with the License.
 *
 * You can obtain a copy of the license at license/ESCIDOC.LICENSE
 * or http://www.escidoc.de/license.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at license/ESCIDOC.LICENSE.
 * If applicable, add the following below this CDDL HEADER, with the
 * fields enclosed by brackets "[]" replaced with your own identifying
 * information: Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 */

/*
 * Copyright 2006-2008 Fachinformationszentrum Karlsruhe Gesellschaft
 * fuer wissenschaftlich-technische Information mbH and Max-Planck-
 * Gesellschaft zur Foerderung der Wissenschaft e.V.
 * All rights reserved.  Use is subject to license terms.
 */
package de.escidoc.core.client.interfaces;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;

/**
 * Basic Create, Retrieve, Update, Delete Handler.
 * 
 * @author SWA
 * @param <T>
 */
public interface CrudHandlerInterface<T> {

    /**
     * Login at eSciDoc framework.
     * 
     * @param serviceAddress
     *            URL of framework.
     * @param username
     *            Name of user.
     * @param password
     *            Password.
     * @return eSciDoc Authentication handle.
     * 
     * @throws EscidocClientException
     *             Thrown in case of errors from framework.
     * @throws InternalClientException
     *             Thrown in case of client library internal errors.
     * @throws TransportException
     *             Thrown in case of transport errors.
     */
    @Deprecated
    String login(
        final String serviceAddress, final String username,
        final String password) throws EscidocClientException,
        InternalClientException, TransportException;

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
     * Create the resource in the repository.
     * 
     * @param resource
     *            The class representation of the resource.
     * @return The new created resource as class representation.
     * 
     * @throws EscidocClientException
     *             Thrown in case of errors from framework.
     * @throws InternalClientException
     *             Thrown in case of client library internal errors.
     * @throws TransportException
     *             Thrown in case of transport errors.
     */
    T create(T resource) throws EscidocClientException,
        InternalClientException, TransportException;

    /**
     * Update the resource in the repository.
     * 
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
    T update(T resource) throws EscidocClientException,
        InternalClientException, TransportException;

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
    T retrieve(final String id) throws EscidocClientException,
        InternalClientException, TransportException;

    /**
     * Delete the resource in the repository.
     * 
     * @param id
     *            The id of the resource.
     * 
     * @throws EscidocClientException
     *             Thrown in case of errors from framework.
     * @throws InternalClientException
     *             Thrown in case of client library internal errors.
     * @throws TransportException
     *             Thrown in case of transport errors.
     */
    void delete(final String id) throws EscidocClientException,
        InternalClientException, TransportException;

    /**
     * Serializes the resource to its xml representation.
     * 
     * @return xml representation.
     */
    String toString();

}
