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

import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;

/**
 * Resource Status change Method.
 * 
 * @author SWA
 * @param <T>
 */
interface ResourceStatusHandlerInterface<T> extends ResourceHandlerInterface<T> {

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

    /**
     * 
     * @param id
     * @param taskParam
     * @return Result
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    Result revise(final String id, final TaskParam taskParam)
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
    Result revise(final T resource, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * 
     * @param id
     * @param taskParam
     * @return Result
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    Result submit(final String id, final TaskParam taskParam)
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
    Result submit(final T resource, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * 
     * @param id
     * @param taskParam
     * @return Result
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    Result withdraw(final String id, final TaskParam taskParam)
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
    Result withdraw(final T resource, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

}
