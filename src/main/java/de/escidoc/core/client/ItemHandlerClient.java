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
package de.escidoc.core.client;

import static de.escidoc.core.common.Precondition.checkNotNull;
import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.net.URL;
import java.util.List;
import java.util.Map;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.ItemHandlerClientInterface;
import de.escidoc.core.client.rest.RestItemHandlerClient;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.HttpInputStream;
import de.escidoc.core.resources.common.ContentStream;
import de.escidoc.core.resources.common.ContentStreams;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.Relations;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.common.versionhistory.VersionHistory;
import de.escidoc.core.resources.om.container.ContainerList;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.om.item.ItemProperties;
import de.escidoc.core.resources.om.item.component.Component;
import de.escidoc.core.resources.om.item.component.ComponentProperties;
import de.escidoc.core.resources.om.item.component.Components;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;

/**
 * This is the generic ItemClientHandler which binds the transport specific
 * classes. The transport specification is done via properties configuration of
 * the eSciDoc client.
 * 
 * @author SWA
 * 
 */
public class ItemHandlerClient extends AbstractHandlerClient<RestItemHandlerClient>
    implements ItemHandlerClientInterface {

    /**
     * 
     */
    public ItemHandlerClient() {
        super();
    }

    /**
     * 
     * @param serviceAddress
     */
    public ItemHandlerClient(final URL serviceAddress) {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @deprecated Use {@link ItemHandlerClient#ItemHandlerClient(URL)} instead.
     */
    @Deprecated
    public ItemHandlerClient(final String serviceAddress) {
        super(serviceAddress);
    }

    @Override
    protected RestItemHandlerClient getRestHandlerClientInstance() throws InternalClientException {
        return new RestItemHandlerClient(getServiceAddress());
    }

    /**
     * Create Item within the repository.
     * 
     * @param item
     *            The Item which is to create.
     * @return The created Item
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Item create(final Item item) throws EscidocException, InternalClientException, TransportException {

        checkNotNull(item);

        Marshaller<Item> m = MarshallerFactory.getInstance().getMarshaller(Item.class);

        String xml = getClient().create(m.marshalDocument(item));

        return m.unmarshalDocument(xml);
    }

    /**
     * Create Component.
     * 
     * @param id
     *            Objid of Item where Component will be part of.
     * @param component
     *            The Container which is to create.
     * @return The created Component
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Component createComponent(final String id, final Component component) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(id);
        checkNotNull(component);

        Marshaller<Component> m = MarshallerFactory.getInstance().getMarshaller(Component.class);

        String xml = getClient().create(m.marshalDocument(component));

        return m.unmarshalDocument(xml);
    }

    /**
     * Retrieve Item.
     * 
     * @param id
     *            Objid of Item
     * @return Item
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Item retrieve(final String id) throws EscidocException, InternalClientException, TransportException {

        String xml = getClient().retrieve(id);

        return MarshallerFactory.getInstance().getMarshaller(Item.class).unmarshalDocument(xml);
    }

    /**
     * Retrieve Items (Filter for Items).
     * 
     * @param request
     *            Filter parameter
     * @return SearchRetrieveResponseType
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public SearchRetrieveResponse retrieveItems(final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        String xml = getClient().retrieveItems(request);

        return MarshallerFactory.getInstance().getMarshaller(SearchRetrieveResponse.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.ItemHandlerClientInterface#
     * retrieveItemsAsList(gov.loc.www.zing.srw.SearchRetrieveRequestType)
     */
    @Override
    public List<Item> retrieveItemsAsList(final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        return getSearchRetrieveResponseAsList(Item.class, retrieveItems(request));
    }

    /**
     * Retrieve Items (Filter for Items).
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
    @Override
    public ExplainResponse retrieveItems(final ExplainRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        String xml = getClient().retrieveItems(request);

        return MarshallerFactory.getInstance().getMarshaller(ExplainResponse.class).unmarshalDocument(xml);
    }

    /**
     * Retrieve relations of Item.
     * 
     * @param id
     *            Objid of Item
     * @return Relations
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Relations retrieveRelations(final String id) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);

        String xml = getClient().retrieveRelations(id);

        return MarshallerFactory.getInstance().getMarshaller(Relations.class).unmarshalDocument(xml);
    }

    /**
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @Override
    public ContainerList retrieveParents(final String id) throws EscidocException, InternalClientException,
        TransportException {
        checkNotNull(id);

        String xml = getClient().retrieveParents(id);

        return MarshallerFactory.getInstance().getMarshaller(ContainerList.class).unmarshalDocument(xml);
    }

    /**
     * Retrieve version-history of Item.
     * 
     * @param id
     *            Objid of Item
     * @return VersionHistory
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public VersionHistory retrieveVersionHistory(final String id) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);

        String xml = getClient().retrieveVersionHistory(id);

        return MarshallerFactory.getInstance().getMarshaller(VersionHistory.class).unmarshalDocument(xml);
    }

    /**
     * Retrieve Item.
     * 
     * @param item
     *            Item
     * @return VersionHistory
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public VersionHistory retrieveVersionHistory(final Item item) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(item);

        return retrieveVersionHistory(item.getObjid());
    }

    /**
     * Delete Item.
     * 
     * @param id
     *            Objid of Item
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public void delete(final String id) throws EscidocException, InternalClientException, TransportException {

        checkNotNull(id);

        getClient().delete(id);
    }

    /**
     * Update Item.
     * 
     * @param item
     *            New Item representation
     * @return Item
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Item update(final Item item) throws EscidocException, InternalClientException, TransportException {

        checkNotNull(item);

        Marshaller<Item> m = MarshallerFactory.getInstance().getMarshaller(Item.class);

        String xml = getClient().update(item.getObjid(), m.marshalDocument(item));

        return m.unmarshalDocument(xml);
    }

    /*
     * Status methods
     */

    /**
     * Set status of Item to submitted.
     * 
     * @param id
     *            Objid of Item
     * @param taskParam
     *            Task parameter to submit Item.
     * @return Results
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Result submit(final String id, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);
        checkNotNull(taskParam);

        String xml = getClient().submit(id, marshalTaskParam(taskParam));

        return MarshallerFactory.getInstance().getMarshaller(Result.class).unmarshalDocument(xml);
    }

    /**
     * Set status of Item to submitted.
     * 
     * @param item
     *            Item
     * @param taskParam
     *            Task parameter to submit Item.
     * @return Results
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Result submit(final Item item, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(item);

        return submit(item.getObjid(), taskParam);
    }

    /**
     * Set status of Item to released.
     * 
     * @param id
     *            Objid of Item
     * @param taskParam
     *            Task parameter to release Item.
     * @return Results
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Result release(final String id, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);
        checkNotNull(taskParam);

        String xml = getClient().release(id, marshalTaskParam(taskParam));

        return MarshallerFactory.getInstance().getMarshaller(Result.class).unmarshalDocument(xml);
    }

    /**
     * Set status of Item to released.
     * 
     * @param item
     *            Item
     * @param taskParam
     *            Task parameter to release Item.
     * @return Results
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Result release(final Item item, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(item);

        return release(item.getObjid(), taskParam);
    }

    /**
     * Set status of Item to revised.
     * 
     * @param id
     *            Objid of Item
     * @param taskParam
     *            Task parameter to revise Item.
     * @return Results
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Result revise(final String id, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);
        checkNotNull(taskParam);

        String xml = getClient().revise(id, marshalTaskParam(taskParam));

        return MarshallerFactory.getInstance().getMarshaller(Result.class).unmarshalDocument(xml);
    }

    /**
     * Set status of Item to revised.
     * 
     * @param item
     *            Item
     * @param taskParam
     *            Task parameter to revise Item.
     * @return Results
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Result revise(final Item item, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(item);

        return revise(item.getObjid(), taskParam);
    }

    /**
     * Set status of Item to withdrawn.
     * 
     * @param id
     *            Objid of Item
     * @param taskParam
     *            Task parameter to withdraw Item.
     * @return Results
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Result withdraw(final String id, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(id);
        checkNotNull(taskParam);

        String xml = getClient().withdraw(id, marshalTaskParam(taskParam));

        return MarshallerFactory.getInstance().getMarshaller(Result.class).unmarshalDocument(xml);
    }

    /**
     * Set status of Item to withdrawn.
     * 
     * @param item
     *            Item
     * @param taskParam
     *            Task parameter to withdraw Item.
     * @return Results
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Result withdraw(final Item item, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(item);

        return withdraw(item.getObjid(), taskParam);
    }

    /**
     * Lock the Item.
     * 
     * @param id
     *            Objid of Container.
     * @param taskParam
     *            TaskParameter.
     * @return Result message of method.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Result lock(final String id, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);
        checkNotNull(taskParam);

        String xml = getClient().lock(id, marshalTaskParam(taskParam));

        return MarshallerFactory.getInstance().getMarshaller(Result.class).unmarshalDocument(xml);
    }

    /**
     * Lock the Container.
     * 
     * @param item
     *            Item.
     * @param taskParam
     *            TaskParameter.
     * @return Result message of method.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Result lock(final Item item, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(item);

        return lock(item.getObjid(), taskParam);
    }

    /**
     * Unlock the Container.
     * 
     * @param id
     *            Objid of Item.
     * @param taskParam
     *            TaskParameter.
     * @return Result message of method.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Result unlock(final String id, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);
        checkNotNull(taskParam);

        String xml = getClient().unlock(id, marshalTaskParam(taskParam));

        return MarshallerFactory.getInstance().getMarshaller(Result.class).unmarshalDocument(xml);
    }

    /**
     * Unlock the Container.
     * 
     * @param item
     *            Item.
     * @param taskParam
     *            TaskParameter.
     * @return Result message of method.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Result unlock(final Item item, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(item);

        return unlock(item.getObjid(), taskParam);
    }

    /*
     * Assign PID methods
     */

    /**
     * Assign a PID to the latest version of Item.
     * 
     * @param id
     *            Objid of Item.
     * @param taskParam
     *            TaskParameter.
     * @return Result message of method.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Result assignVersionPid(final String id, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(id);
        checkNotNull(taskParam);

        String xml = getClient().assignVersionPid(id, marshalTaskParam(taskParam));

        return MarshallerFactory.getInstance().getMarshaller(Result.class).unmarshalDocument(xml);
    }

    /**
     * Assign a PID to the latest version of Item.
     * 
     * @param item
     *            Item.
     * @param taskParam
     *            TaskParameter.
     * @return Result message of method.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Result assignVersionPid(final Item item, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(item);

        return assignVersionPid(item.getObjid(), taskParam);
    }

    /**
     * Assign an object PID to Item.
     * 
     * @param id
     *            Objid of Item.
     * @param taskParam
     *            TaskParameter.
     * @return Result message of method.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Result assignObjectPid(final String id, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(id);
        checkNotNull(taskParam);

        String xml = getClient().assignObjectPid(id, marshalTaskParam(taskParam));

        return MarshallerFactory.getInstance().getMarshaller(Result.class).unmarshalDocument(xml);
    }

    /**
     * Assign an object PID to Item.
     * 
     * @param item
     *            Item.
     * @param taskParam
     *            TaskParameter.
     * @return Result message of method.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Result assignObjectPid(final Item item, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(item);

        return assignObjectPid(item.getObjid(), taskParam);
    }

    /**
     * Assign a PID to the content of Item.
     * 
     * @param id
     *            Objid of Item.
     * @param componentId
     *            objid of Component
     * @param taskParam
     *            TaskParameter.
     * @return Result message of method.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Result assignContentPid(final String id, final String componentId, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        checkNotNull(id);
        checkNotNull(componentId);
        checkNotNull(taskParam);

        String xml = getClient().assignContentPid(id, componentId, marshalTaskParam(taskParam));

        return MarshallerFactory.getInstance().getMarshaller(Result.class).unmarshalDocument(xml);
    }

    /**
     * Assign a PID to the content of Item.
     * 
     * @param item
     *            Item.
     * @param componentId
     *            objid of Component
     * @param taskParam
     *            TaskParameter.
     * @return Result message of method.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Result assignContentPid(final Item item, final String componentId, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        checkNotNull(item);

        return assignContentPid(item.getObjid(), componentId, taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.PropertiesService#retrieveProperties
     * (java.lang.String)
     */
    @Override
    public ItemProperties retrieveProperties(final String id) throws EscidocException, InternalClientException,
        TransportException {

        String xml = getClient().retrieveProperties(id);

        return MarshallerFactory.getInstance().getMarshaller(ItemProperties.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.MdRecordService#retrieveMdRecords
     * (java.lang.String)
     */
    @Override
    public MetadataRecords retrieveMdRecords(final String id) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);

        String xml = getClient().retrieveMdRecords(id);

        return MarshallerFactory.getInstance().getMarshaller(MetadataRecords.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.MdRecordService#retrieveMdRecord
     * (java.lang.String, java.lang.String)
     */
    @Override
    public MetadataRecord retrieveMdRecord(final String id, final String mdRecordId) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(id);
        checkNotNull(mdRecordId);

        String xml = getClient().retrieveMdRecord(id, mdRecordId);

        return MarshallerFactory.getInstance().getMarshaller(MetadataRecord.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.base.ContentStreamService#
     * retrieveContentStreams(java.lang.String)
     */
    @Override
    public ContentStreams retrieveContentStreams(final String id) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);

        String xml = getClient().retrieveContentStreams(id);

        return MarshallerFactory.getInstance().getMarshaller(ContentStreams.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.base.ContentStreamService#
     * retrieveContentStream(java.lang.String, java.lang.String)
     */
    @Override
    public ContentStream retrieveContentStream(final String id, final String name) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(id);
        checkNotNull(name);

        String xml = getClient().retrieveContentStream(id, name);

        return MarshallerFactory.getInstance().getMarshaller(ContentStream.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.base.ContentStreamService#
     * retrieveContentStreamContent(java.lang.String, java.lang.String)
     */
    @Override
    public HttpInputStream retrieveContentStreamContent(final String id, final String name) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(id);
        checkNotNull(name);

        return getClient().retrieveContentStreamContent(id, name);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.ItemHandlerClientInterface#moveToContext
     * (java.lang.String, de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public Item moveToContext(final String id, final TaskParam param) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);
        checkNotNull(param);

        String xml = getClient().moveToContext(id, marshalTaskParam(param));

        return MarshallerFactory.getInstance().getMarshaller(Item.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.ItemHandlerClientInterface#moveToContext
     * (de.escidoc.core.resources.om.item.Item,
     * de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public Item moveToContext(final Item item, final TaskParam param) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(item);

        return moveToContext(item.getObjid(), param);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.ItemHandlerClientInterface#
     * retrieveComponents(java.lang.String)
     */
    @Override
    public Components retrieveComponents(final String id) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);

        String xml = getClient().retrieveComponents(id);

        return MarshallerFactory.getInstance().getMarshaller(Components.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.ItemHandlerClientInterface#
     * retrieveComponent(java.lang.String, java.lang.String)
     */
    @Override
    public Component retrieveComponent(final String itemId, final String componentId) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(itemId);
        checkNotNull(componentId);

        String xml = getClient().retrieveComponent(itemId, componentId);

        return MarshallerFactory.getInstance().getMarshaller(Component.class).unmarshalDocument(xml);
    }

    @Override
    public void deleteComponent(final String itemId, final String componentId) throws EscidocException,
        InternalClientException, TransportException {
        // TODO Auto-generated method stub

    }

    @Override
    public Component updateComponent(final String id, final String componentId, final String xmlData)
        throws EscidocException, InternalClientException, TransportException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.ItemHandlerClientInterface#
     * retrieveComponentProperties(java.lang.String, java.lang.String)
     */
    @Override
    public ComponentProperties retrieveComponentProperties(final String itemId, final String componentId)
        throws EscidocException, InternalClientException, TransportException {

        checkNotNull(itemId);
        checkNotNull(componentId);

        String xml = getClient().retrieveComponentProperties(itemId, componentId);

        return MarshallerFactory.getInstance().getMarshaller(ComponentProperties.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.ItemHandlerClientInterface#
     * retrieveComponentMdRecords(java.lang.String, java.lang.String)
     */
    @Override
    public MetadataRecords retrieveComponentMdRecords(final String itemId, final String componentId)
        throws EscidocException, InternalClientException, TransportException {

        checkNotNull(itemId);
        checkNotNull(componentId);

        String xml = getClient().retrieveComponentMdRecords(itemId, componentId);

        return MarshallerFactory.getInstance().getMarshaller(MetadataRecords.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.ItemHandlerClientInterface#
     * retrieveComponentMdRecord(java.lang.String, java.lang.String,
     * java.lang.String)
     */
    @Override
    public MetadataRecord retrieveComponentMdRecord(
        final String itemId, final String componentId, final String mdRecordId) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(itemId);
        checkNotNull(componentId);
        checkNotNull(mdRecordId);

        String xml = getClient().retrieveComponentMdRecord(itemId, componentId, mdRecordId);

        return MarshallerFactory.getInstance().getMarshaller(MetadataRecord.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.ItemHandlerClientInterface#retrieveContent
     * (java.lang.String, java.lang.String)
     */
    @Override
    public HttpInputStream retrieveContent(final String itemId, final String componentId) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(itemId);
        checkNotNull(componentId);

        return getClient().retrieveContent(itemId, componentId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.ItemHandlerClientInterface#retrieveContent
     * (java.lang.String, java.lang.String, java.lang.String,
     * de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public HttpInputStream retrieveContent(
        final String itemId, final String componentId, final String transformer, final Map<String, String[]> transParams)
        throws EscidocException, InternalClientException, TransportException {

        checkNotNull(itemId);
        checkNotNull(componentId);
        checkNotNull(transformer);

        return getClient().retrieveContent(itemId, componentId, transformer, transParams);
    }
}