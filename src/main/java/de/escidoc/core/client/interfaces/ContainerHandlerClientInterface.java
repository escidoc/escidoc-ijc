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
import de.escidoc.core.client.interfaces.base.LockingService;
import de.escidoc.core.client.interfaces.base.RelationsService;
import de.escidoc.core.client.interfaces.base.ResourceStatusService;
import de.escidoc.core.client.interfaces.base.VersionPidService;
import de.escidoc.core.client.interfaces.base.VersionableResourceService;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.common.structmap.StructMap;
import de.escidoc.core.resources.om.GenericVersionableResource;
import de.escidoc.core.resources.om.container.Container;
import de.escidoc.core.resources.om.container.ContainerList;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;

/**
 * This class defines the signatures for the client handler wrapper classes
 * where the transport specific exceptions are mapped to internal client
 * internal exception.
 * 
 * @author SWA
 * 
 */
public interface ContainerHandlerClientInterface
    extends HandlerService, CrudService<Container>,
    VersionableResourceService<Container>, LockingService<Container>,
    VersionPidService<Container>, ResourceStatusService<Container>,
    RelationsService {

    /**
     * 
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    SearchRetrieveResponse retrieveContainers(SearchRetrieveRequestType request)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    ExplainResponse retrieveContainers(ExplainRequestType request)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    List<Container> retrieveContainersAsList(SearchRetrieveRequestType request)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    StructMap retrieveStructMap(final String id) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * 
     * @param container
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    StructMap retrieveStructMap(final Container container)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    ContainerList retrieveParents(String id) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * Creates a container as a sub-resource of this container.
     * 
     * @param container
     * @param subContainer
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Container createContainer(
        final Container container, final Container subContainer)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * Creates a container as a sub-resource of this container.
     * 
     * @param containerId
     * @param container
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Container createContainer(
        final String containerId, final Container container)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * Creates an item as a sub-resource of this container.
     * 
     * @param id
     * @param item
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Item createItem(final String id, final Item item) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * Creates an item as a sub-resource of this container.
     * 
     * @param container
     * @param item
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Item createItem(final Container container, final Item item)
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
    Container addContentRelations(final String id, TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param container
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Container addContentRelations(final Container container, TaskParam taskParam)
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
    Container removeContentRelations(final String id, TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param container
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Container removeContentRelations(
        final Container container, TaskParam taskParam)
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
    Result addMembers(final String id, TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param container
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Result addMembers(final Container container, TaskParam taskParam)
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
    Result removeMembers(final String id, TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param container
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Result removeMembers(final Container container, TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * Retrieve Members (Filter for Members).
     * 
     * @param id
     *            The ID of the Container where the filter should operate on
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
    SearchRetrieveResponse retrieveMembers(
        final String id, final SearchRetrieveRequestType filter)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * @param id
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    List<GenericVersionableResource> retrieveMembersAsList(
        final String id, final SearchRetrieveRequestType filter)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * @param container
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    List<GenericVersionableResource> retrieveMembersAsList(
        final Container container, final SearchRetrieveRequestType filter)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * Retrieve Members (Filter for Members).
     * 
     * @param container
     *            The Container where the filter should operate on
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
    SearchRetrieveResponse retrieveMembers(
        final Container container, final SearchRetrieveRequestType filter)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * Retrieve Members (Filter for Members).
     * 
     * @param id
     *            The ID of the Container where the filter should operate on
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
    ExplainResponse retrieveMembers(
        final String id, final ExplainRequestType filter)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * Retrieve Members (Filter for Members).
     * 
     * @param container
     *            The Container where the filter should operate on
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
    ExplainResponse retrieveMembers(
        final Container container, final ExplainRequestType filter)
        throws EscidocException, InternalClientException, TransportException;
}
