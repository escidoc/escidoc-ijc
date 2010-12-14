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

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.util.Collection;

import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.om.contentRelation.ContentRelation;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;

/**
 * This class defines the signatures for the client handler wrapper classes
 * where the transport specific exceptions are mapped to internal client
 * internal exception.
 * 
 * @param <ContentRelation>
 *            ContentRelation
 * @author SWA
 * 
 */
public interface ContentRelationHandlerClientInterface
    extends ResourceHandlerInterface<ContentRelation> {

    /**
     * Lock resource.
     * 
     * @param id
     *            objid of resource
     * @param taskParam
     *            task param to lock
     * @return Result (contains important resource parameter)
     * 
     * @throws EscidocClientException
     *             Thrown in case of errors from framework.
     * @throws InternalClientException
     *             Thrown in case of client library internal errors.
     * @throws TransportException
     *             Thrown in case of transport errors.
     */
    Result lock(final String id, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * Lock resource.
     * 
     * @param contentRelation
     *            resource
     * @param taskParam
     *            task param to lock
     * @return Result (contains important resource parameter)
     * 
     * @throws EscidocClientException
     *             Thrown in case of errors from framework.
     * @throws InternalClientException
     *             Thrown in case of client library internal errors.
     * @throws TransportException
     *             Thrown in case of transport errors.
     */
    Result lock(final ContentRelation contentRelation, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * Unlock resource.
     * 
     * @param id
     *            objid of resource
     * @param taskParam
     *            task param to unlock
     * @return Result (contains important resource parameter)
     * 
     * @throws EscidocClientException
     *             Thrown in case of errors from framework.
     * @throws InternalClientException
     *             Thrown in case of client library internal errors.
     * @throws TransportException
     *             Thrown in case of transport errors.
     */
    Result unlock(final String id, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * Lock resource.
     * 
     * @param contentRelation
     *            resource
     * @param taskParam
     *            task param to lock
     * @return Result (contains important resource parameter)
     * 
     * @throws EscidocClientException
     *             Thrown in case of errors from framework.
     * @throws InternalClientException
     *             Thrown in case of client library internal errors.
     * @throws TransportException
     *             Thrown in case of transport errors.
     */
    Result unlock(
        final ContentRelation contentRelation, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /*
     * Status methods (ResourceStatusHandlerInterface could not be used, because
     * ContentRelation does not support withdraw)
     */

    /**
     * Release the Resource.
     * 
     * @param id
     *            objid of the resource.
     * @param taskParam
     *            TaskParam for Release.
     * @return Result (contains important resource parameter)
     * 
     * @throws EscidocClientException
     *             Thrown in case of errors from framework.
     * @throws InternalClientException
     *             Thrown in case of client library internal errors.
     * @throws TransportException
     *             Thrown in case of transport errors.
     */
    Result release(final String id, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * Release the Resource.
     * 
     * @param contentRelation
     *            resource
     * @param taskParam
     *            TaskParam for Release.
     * @return Result (contains important resource parameter)
     * 
     * @throws EscidocClientException
     *             Thrown in case of errors from framework.
     * @throws InternalClientException
     *             Thrown in case of client library internal errors.
     * @throws TransportException
     *             Thrown in case of transport errors.
     */
    Result release(final ContentRelation resource, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * Revise the Resource.
     * 
     * @param id
     *            objid of the resource.
     * @param taskParam
     *            TaskParam for Release.
     * @return Result (contains important resource parameter)
     * 
     * @throws EscidocClientException
     *             Thrown in case of errors from framework.
     * @throws InternalClientException
     *             Thrown in case of client library internal errors.
     * @throws TransportException
     *             Thrown in case of transport errors.
     */
    Result revise(final String id, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * Revise the Resource.
     * 
     * @param contentRelation
     *            resource.
     * @param taskParam
     *            TaskParam for Release.
     * @return Result (contains important resource parameter)
     * 
     * @throws EscidocClientException
     *             Thrown in case of errors from framework.
     * @throws InternalClientException
     *             Thrown in case of client library internal errors.
     * @throws TransportException
     *             Thrown in case of transport errors.
     */
    Result revise(
        final ContentRelation contentRelation, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * Submit the Resource.
     * 
     * @param id
     *            objid of resource.
     * @param taskParam
     *            TaskParam for Release.
     * @return Result (contains important resource parameter)
     * 
     * @throws EscidocClientException
     *             Thrown in case of errors from framework.
     * @throws InternalClientException
     *             Thrown in case of client library internal errors.
     * @throws TransportException
     *             Thrown in case of transport errors.
     */
    Result submit(final String id, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * Submit the Resource.
     * 
     * @param contentRelation
     *            resource.
     * @param taskParam
     *            TaskParam for Release.
     * @return Result (contains important resource parameter)
     * 
     * @throws EscidocClientException
     *             Thrown in case of errors from framework.
     * @throws InternalClientException
     *             Thrown in case of client library internal errors.
     * @throws TransportException
     *             Thrown in case of transport errors.
     */
    Result submit(final ContentRelation resource, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * Retrieve ContentRelation.
     * 
     * @param contentRelation
     *            ContentRelation class with values which ContentRelation is to
     *            retrieve (obji of ContentRelation is important factor).
     * @return ContentRelation
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    ContentRelation retrieve(final ContentRelation contentRelation)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * Retrieve ContentRelations (Filter for ContentRelations).
     * 
     * @param filter
     *            Filter parameter
     * @return SearchRetrieveResponseType
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    SearchRetrieveResponse retrieveContentRelations(
        final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * This is a convenience method to retrieve the resulting objects as a list.
     * Since it could happen, that binding of an object fails, this list will
     * not contain all objects, which could not be bounded. In case you wish to
     * have complete control over the results, you may use the method
     * {@link #retrieveContentRelations(SearchRetrieveRequestType)}, since you
     * can still work with the resulting DOM.
     * 
     * Usually binding of an object fails, if the server returns unexpected
     * record data.
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Collection<ContentRelation> retrieveContentRelationsAsList(
        final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * Retrieve ContentRelations (Filter for ContentRelations).
     * 
     * @param filter
     *            Filter parameter
     * @return ExplainRecord
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    ExplainResponse retrieveContentRelations(final ExplainRequestType filter)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * Assign Persistent Identifier for ContentRelation (object).
     * 
     * @param cr
     *            ContentRelation
     * @param taskParam
     *            Task parameter to provide PID parameter.
     * @return The updated ContentRelation.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    Result assignObjectPid(final ContentRelation cr, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException;
}
