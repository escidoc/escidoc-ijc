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

import java.util.List;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.base.CrudService;
import de.escidoc.core.client.interfaces.base.HandlerService;
import de.escidoc.core.client.interfaces.base.OpenCloseService;
import de.escidoc.core.resources.VersionableResource;
import de.escidoc.core.resources.om.context.AdminDescriptor;
import de.escidoc.core.resources.om.context.AdminDescriptors;
import de.escidoc.core.resources.om.context.Context;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;

/**
 * This class defines the signatures for the client handler wrapper classes
 * where the transport specific exceptions are mapped to internal client
 * internal exception.
 * 
 * @param <Context>
 *            Context
 * @author SWA
 * 
 */
public interface ContextHandlerClientInterface extends HandlerService, CrudService<Context>, OpenCloseService<Context> {

    /**
     * 
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    SearchRetrieveResponse retrieveContexts(SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * 
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    ExplainResponse retrieveContexts(ExplainRequestType request) throws EscidocException, InternalClientException,
        TransportException;

    /**
     * 
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    List<Context> retrieveContextsAsList(SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * 
     * @param contextId
     *            objid of Context.
     * @return AdminDescriptors
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    AdminDescriptors retrieveAdminDescriptors(final String contextId) throws EscidocException, InternalClientException,
        TransportException;

    /**
     * 
     * @param context
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    AdminDescriptors retrieveAdminDescriptors(final Context context) throws EscidocException, InternalClientException,
        TransportException;

    /**
     * Retrieve Admin Descriptor from Context.
     * 
     * @param contextId
     *            Objid of the Context
     * @param name
     *            Name of AdminDescriptor
     * @return AdminDescriptor with provided name
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    AdminDescriptor retrieveAdminDescriptor(final String contextId, final String name) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * 
     * @param context
     * @param name
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    AdminDescriptor retrieveAdminDescriptor(final Context context, final String name) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * 
     * @param id
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    SearchRetrieveResponse retrieveMembers(final String id, final SearchRetrieveRequestType request)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param context
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    SearchRetrieveResponse retrieveMembers(final Context context, final SearchRetrieveRequestType request)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * This is a convenience method to retrieve the resulting objects as a list.
     * Since it could happen, that binding of an object fails, this list will
     * not contain all objects, which could not be bounded. In case you wish to
     * have complete control over the results, you may use the method
     * {@link #searchMembers(SearchRetrieveRequestType)}, since you can still
     * work with the resulting DOM.
     * 
     * Usually binding of an object fails, if the server returns unexpected
     * record data.
     * 
     * @param id
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    List<VersionableResource> retrieveMembersAsList(final String id, final SearchRetrieveRequestType filter)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * This is a convenience method to retrieve the resulting objects as a list.
     * Since it could happen, that binding of an object fails, this list will
     * not contain all objects, which could not be bounded. In case you wish to
     * have complete control over the results, you may use the method
     * {@link #searchMembers(SearchRetrieveRequestType)}, since you can still
     * work with the resulting DOM.
     * 
     * Usually binding of an object fails, if the server returns unexpected
     * record data.
     * 
     * @param context
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    List<VersionableResource> retrieveMembersAsList(final Context context, final SearchRetrieveRequestType filter)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param id
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    ExplainResponse retrieveMembers(final String id, final ExplainRequestType request) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * 
     * @param context
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    ExplainResponse retrieveMembers(final Context context, final ExplainRequestType request) throws EscidocException,
        InternalClientException, TransportException;
}