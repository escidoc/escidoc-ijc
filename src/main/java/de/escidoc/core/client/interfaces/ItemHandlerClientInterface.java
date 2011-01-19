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
import java.util.Map;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.base.ContentStreamService;
import de.escidoc.core.client.interfaces.base.CrudService;
import de.escidoc.core.client.interfaces.base.HandlerService;
import de.escidoc.core.client.interfaces.base.LockingService;
import de.escidoc.core.client.interfaces.base.MdRecordService;
import de.escidoc.core.client.interfaces.base.PropertiesService;
import de.escidoc.core.client.interfaces.base.RelationsService;
import de.escidoc.core.client.interfaces.base.ResourceStatusService;
import de.escidoc.core.client.interfaces.base.VersionPidService;
import de.escidoc.core.client.interfaces.base.VersionableResourceService;
import de.escidoc.core.resources.HttpInputStream;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.om.item.ItemProperties;
import de.escidoc.core.resources.om.item.component.Component;
import de.escidoc.core.resources.om.item.component.ComponentProperties;
import de.escidoc.core.resources.om.item.component.Components;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;

/**
 * This class defines the signatures for the client handler wrapper classes
 * where the transport specific exceptions are mapped to internal client
 * internal exception.
 * 
 * @author SWA
 */
public interface ItemHandlerClientInterface
    extends HandlerService, CrudService<Item>,
    VersionableResourceService<Item>, ResourceStatusService<Item>,
    LockingService<Item>, VersionPidService<Item>,
    PropertiesService<Item, ItemProperties>, MdRecordService,
    ContentStreamService, RelationsService {

    /**
     * 
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    SearchRetrieveResponse retrieveItems(SearchRetrieveRequestType request)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    ExplainResponse retrieveItems(ExplainRequestType request)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    List<Item> retrieveItemsAsList(SearchRetrieveRequestType request)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * @param id
     * @param param
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Item moveToContext(final String id, final TaskParam param)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param item
     * @param param
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Item moveToContext(final Item item, final TaskParam param)
        throws EscidocException, InternalClientException, TransportException;

    /*
     * COMPONENTS
     */

    /**
     * 
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Components retrieveComponents(final String id) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * 
     * @param id
     * @param component
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Component createComponent(final String id, Component component)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param itemId
     * @param componentId
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Component retrieveComponent(final String itemId, final String componentId)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param itemId
     * @param componentId
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    void deleteComponent(final String itemId, final String componentId)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param id
     * @param componentId
     * @param xmlData
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Component updateComponent(
        final String id, final String componentId, final String xmlData)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param itemId
     * @param componentId
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    ComponentProperties retrieveComponentProperties(
        final String itemId, final String componentId) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * 
     * @param itemId
     * @param componentId
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    MetadataRecords retrieveComponentMdRecords(
        final String itemId, final String componentId) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * 
     * @param id
     * @param componentId
     * @param mdRecordId
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    MetadataRecord retrieveComponentMdRecord(
        final String id, final String componentId, final String mdRecordId)
        throws EscidocException, InternalClientException, TransportException;

    /*
     * CONTENT
     */

    /**
     * 
     * @param id
     * @param contentId
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    HttpInputStream retrieveContent(final String id, final String componentId)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param id
     * @param contentId
     * @param transformer
     * @param transParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    HttpInputStream retrieveContent(
        final String id, final String contentId, final String transformer,
        final Map<String, String[]> transParams) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * 
     * @param id
     * @param componentId
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Result assignContentPid(
        final String id, final String componentId, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param item
     * @param componentId
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Result assignContentPid(
        final Item item, final String componentId, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException;
}