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
import de.escidoc.core.resources.common.Relations;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.common.structmap.StructMap;
import de.escidoc.core.resources.om.MemberList;
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
    extends VersionableResourceHandlerInterface<Container> {

    /*
     * lock methods
     */

    Result lock(final String id, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    Result unlock(final String id, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /*
     * Assign PID methods
     */
    Result assignVersionPid(final String id, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /*
     * sub-resources (coming later)
     */

    // ContainerList retrieveContainers(final TaskParam taskParam)
    // throws EscidocException, InternalClientException,
    // TransportException;
    /**
     * 
     */
    StructMap retrieveStructMap(final String id) throws EscidocException,
        InternalClientException, TransportException;

    Container createContainer(
        final String containerId, final Container container)
        throws EscidocException, InternalClientException, TransportException;

    Item createItem(final String itemId, final Item item)
        throws EscidocException, InternalClientException, TransportException;

    Container addContentRelations(final String id, TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException;

    Container removeContentRelations(final String id, TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException;

    Result addMembers(final String id, TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException;

    Result removeMembers(final String id, TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException;

    @Deprecated
    ContainerList retrieveContainers(final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    SearchRetrieveResponse retrieveContainers(
        final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * This is a convenience method to retrieve the resulting objects as a list.
     * Since it could happen, that binding of an object fails, this list will
     * not contain all objects, which could not be bounded. In case you wish to
     * have complete control over the results, you may use the method
     * {@link #retrieveContainers(SearchRetrieveRequestType)}, since you can
     * still work with the resulting DOM.
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
    Collection<Container> retrieveContainersAsList(
        final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    ExplainResponse retrieveContainers(final ExplainRequestType filter)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param id
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    Relations retrieveRelations(final String id) throws EscidocClientException,
        InternalClientException, TransportException;

    /**
     * Retrieve Members (Filter for Members).
     * 
     * @param id
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

    /**
     * 
     * @param id
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @Deprecated
    MemberList retrieveMembers(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException;
}
