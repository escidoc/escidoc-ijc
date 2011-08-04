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
import de.escidoc.core.resources.ResourceType;
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.AbstractHandlerClient#getRestHandlerClientInstance
     * ()
     */
    @Override
    protected RestItemHandlerClient getRestHandlerClientInstance() throws InternalClientException {
        return new RestItemHandlerClient(getServiceAddress());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Createable#create(java.lang.Object
     * )
     */
    @Override
    public Item create(final Item item) throws EscidocException, InternalClientException, TransportException {

        checkNotNull(item);

        final Marshaller<Item> m = MarshallerFactory.getInstance().getMarshaller(Item.class);

        final String xml = getClient().create(m.marshalDocument(item));

        return m.unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.ItemHandlerClientInterface#createComponent
     * (java.lang.String, de.escidoc.core.resources.om.item.component.Component)
     */
    @Override
    public Component createComponent(final String id, final Component component) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(id);
        checkNotNull(component);

        final Marshaller<Component> m = MarshallerFactory.getInstance().getMarshaller(Component.class);

        return m.unmarshalDocument(getClient().createComponent(id, m.marshalDocument(component)));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Retrievable#retrieve(java.lang
     * .String)
     */
    @Override
    public Item retrieve(final String id) throws EscidocException, InternalClientException, TransportException {

        final String xml = getClient().retrieve(id);

        return MarshallerFactory.getInstance().getMarshaller(Item.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.ItemHandlerClientInterface#retrieveItems
     * (gov.loc.www.zing.srw.SearchRetrieveRequestType)
     */
    @Override
    public SearchRetrieveResponse retrieveItems(final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        final String xml = getClient().retrieveItems(request);

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

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.ItemHandlerClientInterface#retrieveItems
     * (gov.loc.www.zing.srw.ExplainRequestType)
     */
    @Override
    public ExplainResponse retrieveItems(final ExplainRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        final String xml = getClient().retrieveItems(request);

        return MarshallerFactory.getInstance().getMarshaller(ExplainResponse.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.RelationsService#retrieveRelations
     * (java.lang.String)
     */
    @Override
    public Relations retrieveRelations(final String id) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);

        final String xml = getClient().retrieveRelations(id);
        final Relations relations =
            MarshallerFactory.getInstance().getMarshaller(Relations.class).unmarshalDocument(xml);
        relations.generateXLinkHref(ResourceType.ITEM.getPath(id));
        return relations;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.ItemHandlerClientInterface#retrieveParents
     * (java.lang.String)
     * 
     * FIXME Delete method or change return type to SearchRetrieveResponse
     */
    @Override
    public ContainerList retrieveParents(final String id) throws EscidocException, InternalClientException,
        TransportException {
        checkNotNull(id);

        final String xml = getClient().retrieveParents(id);

        return MarshallerFactory.getInstance().getMarshaller(ContainerList.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.base.VersionableResourceService#
     * retrieveVersionHistory(java.lang.String)
     */
    @Override
    public VersionHistory retrieveVersionHistory(final String id) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);

        final String xml = getClient().retrieveVersionHistory(id);

        return MarshallerFactory.getInstance().getMarshaller(VersionHistory.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.base.VersionableResourceService#
     * retrieveVersionHistory(java.lang.Object)
     */
    @Override
    public VersionHistory retrieveVersionHistory(final Item item) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(item);

        return retrieveVersionHistory(item.getObjid());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Deletable#delete(java.lang.String)
     */
    @Override
    public void delete(final String id) throws EscidocException, InternalClientException, TransportException {

        checkNotNull(id);

        getClient().delete(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Updatable#update(java.lang.Object)
     */
    @Override
    public Item update(final Item item) throws EscidocException, InternalClientException, TransportException {

        checkNotNull(item);

        final Marshaller<Item> m = MarshallerFactory.getInstance().getMarshaller(Item.class);

        return m.unmarshalDocument(getClient().update(item.getObjid(), m.marshalDocument(item)));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Updatable#update(java.lang.String,
     * java.lang.Object)
     */
    @Override
    public Item update(final String id, final Item item) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);
        checkNotNull(item);

        final Marshaller<Item> m = MarshallerFactory.getInstance().getMarshaller(Item.class);

        return m.unmarshalDocument(getClient().update(id, m.marshalDocument(item)));
    }

    /*
     * Status methods
     */

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Submittable#submit(java.lang.String
     * , de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public Result submit(final String id, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);
        checkNotNull(taskParam);

        final String xml = getClient().submit(id, marshalTaskParam(taskParam));

        return MarshallerFactory.getInstance().getMarshaller(Result.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Submittable#submit(java.lang.Object
     * , de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public Result submit(final Item item, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(item);

        return submit(item.getObjid(), taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Releasable#release(java.lang.String
     * , de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public Result release(final String id, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);
        checkNotNull(taskParam);

        final String xml = getClient().release(id, marshalTaskParam(taskParam));

        return MarshallerFactory.getInstance().getMarshaller(Result.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Releasable#release(java.lang.Object
     * , de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public Result release(final Item item, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(item);

        return release(item.getObjid(), taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Revisable#revise(java.lang.String,
     * de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public Result revise(final String id, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);
        checkNotNull(taskParam);

        final String xml = getClient().revise(id, marshalTaskParam(taskParam));

        return MarshallerFactory.getInstance().getMarshaller(Result.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Revisable#revise(java.lang.Object,
     * de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public Result revise(final Item item, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(item);

        return revise(item.getObjid(), taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Withdrawable#withdraw(java.lang
     * .String, de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public Result withdraw(final String id, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(id);
        checkNotNull(taskParam);

        final String xml = getClient().withdraw(id, marshalTaskParam(taskParam));

        return MarshallerFactory.getInstance().getMarshaller(Result.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Withdrawable#withdraw(java.lang
     * .Object, de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public Result withdraw(final Item item, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(item);

        return withdraw(item.getObjid(), taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.LockingService#lock(java.lang.
     * String, de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public Result lock(final String id, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);
        checkNotNull(taskParam);

        final String xml = getClient().lock(id, marshalTaskParam(taskParam));

        return MarshallerFactory.getInstance().getMarshaller(Result.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.LockingService#lock(java.lang.
     * Object, de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public Result lock(final Item item, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(item);

        return lock(item.getObjid(), taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.LockingService#unlock(java.lang
     * .String, de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public Result unlock(final String id, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);
        checkNotNull(taskParam);

        final String xml = getClient().unlock(id, marshalTaskParam(taskParam));

        return MarshallerFactory.getInstance().getMarshaller(Result.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.LockingService#unlock(java.lang
     * .Object, de.escidoc.core.resources.common.TaskParam)
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.VersionPidService#assignVersionPid
     * (java.lang.String, de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public Result assignVersionPid(final String id, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(id);
        checkNotNull(taskParam);

        final String xml = getClient().assignVersionPid(id, marshalTaskParam(taskParam));

        return MarshallerFactory.getInstance().getMarshaller(Result.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.VersionPidService#assignVersionPid
     * (java.lang.Object, de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public Result assignVersionPid(final Item item, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(item);

        return assignVersionPid(item.getObjid(), taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.ObjectPidService#assignObjectPid
     * (java.lang.String, de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public Result assignObjectPid(final String id, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(id);
        checkNotNull(taskParam);

        final String xml = getClient().assignObjectPid(id, marshalTaskParam(taskParam));

        return MarshallerFactory.getInstance().getMarshaller(Result.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.ObjectPidService#assignObjectPid
     * (java.lang.Object, de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public Result assignObjectPid(final Item item, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(item);

        return assignObjectPid(item.getObjid(), taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.ItemHandlerClientInterface#assignContentPid
     * (java.lang.String, java.lang.String,
     * de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public Result assignContentPid(final String id, final String componentId, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        checkNotNull(id);
        checkNotNull(componentId);
        checkNotNull(taskParam);

        final String xml = getClient().assignContentPid(id, componentId, marshalTaskParam(taskParam));

        return MarshallerFactory.getInstance().getMarshaller(Result.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.ItemHandlerClientInterface#assignContentPid
     * (de.escidoc.core.resources.om.item.Item, java.lang.String,
     * de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public Result assignContentPid(final Item item, final String componentId, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        checkNotNull(item);

        return assignContentPid(item.getObjid(), componentId, taskParam);
    }

    @Override
    public Result addContentRelations(final String itemId, final String taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(itemId);
        checkNotNull(taskParam);

        final String xml = getClient().addContentRelations(itemId, taskParam);
        return MarshallerFactory.getInstance().getMarshaller(Result.class).unmarshalDocument(xml);
    }

    @Override
    public Result addContentRelations(final Item item, final String taskParam) throws EscidocException,
        InternalClientException, TransportException {
        checkNotNull(item);

        return addContentRelations(item.getObjid(), taskParam);
    }

    @Override
    public Result removeContentRelations(final String itemId, final String taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(itemId);
        checkNotNull(taskParam);

        final String xml = getClient().addContentRelations(itemId, taskParam);
        return MarshallerFactory.getInstance().getMarshaller(Result.class).unmarshalDocument(xml);
    }

    @Override
    public Result removeContentRelations(final Item item, final String taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(item);

        return removeContentRelations(item.getObjid(), taskParam);
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

        final String xml = getClient().retrieveProperties(id);

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

        final Marshaller<MetadataRecords> m = MarshallerFactory.getInstance().getMarshaller(MetadataRecords.class);

        return m.unmarshalDocument(getClient().retrieveMdRecords(id));
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

        final Marshaller<MetadataRecord> m = MarshallerFactory.getInstance().getMarshaller(MetadataRecord.class);

        return m.unmarshalDocument(getClient().retrieveMdRecord(id, mdRecordId));
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

        final Marshaller<ContentStreams> m = MarshallerFactory.getInstance().getMarshaller(ContentStreams.class);

        return m.unmarshalDocument(getClient().retrieveContentStreams(id));
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

        final Marshaller<ContentStream> m = MarshallerFactory.getInstance().getMarshaller(ContentStream.class);

        return m.unmarshalDocument(getClient().retrieveContentStream(id, name));
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

        final String xml = getClient().moveToContext(id, marshalTaskParam(param));

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

        final Marshaller<Components> m = MarshallerFactory.getInstance().getMarshaller(Components.class);

        return m.unmarshalDocument(getClient().retrieveComponents(id));
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

        final Marshaller<Component> m = MarshallerFactory.getInstance().getMarshaller(Component.class);

        return m.unmarshalDocument(getClient().retrieveComponent(itemId, componentId));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.ItemHandlerClientInterface#deleteComponent
     * (java.lang.String, java.lang.String)
     */
    @Override
    public void deleteComponent(final String itemId, final String componentId) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(itemId);
        checkNotNull(componentId);

        getClient().deleteComponent(itemId, componentId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.ItemHandlerClientInterface#updateComponent
     * (java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Component updateComponent(final String itemId, final String componentId, final Component component)
        throws EscidocException, InternalClientException, TransportException {

        checkNotNull(itemId);
        checkNotNull(componentId);
        checkNotNull(component);

        final Marshaller<Component> m = MarshallerFactory.getInstance().getMarshaller(Component.class);

        return m.unmarshalDocument(getClient().updateComponent(itemId, componentId, m.marshalDocument(component)));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.ItemHandlerClientInterface#updateComponent
     * (java.lang.String, de.escidoc.core.resources.om.item.component.Component)
     */
    @Override
    public Component updateComponent(final String itemId, final Component component) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(itemId);
        checkNotNull(component);

        return updateComponent(itemId, component.getObjid(), component);
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

        final Marshaller<ComponentProperties> m =
            MarshallerFactory.getInstance().getMarshaller(ComponentProperties.class);

        return m.unmarshalDocument(getClient().retrieveComponentProperties(itemId, componentId));
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

        final String xml = getClient().retrieveComponentMdRecords(itemId, componentId);

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

        final String xml = getClient().retrieveComponentMdRecord(itemId, componentId, mdRecordId);

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