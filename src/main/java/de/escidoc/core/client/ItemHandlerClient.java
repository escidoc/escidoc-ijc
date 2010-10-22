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

import java.util.Collection;
import java.util.LinkedList;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import org.joda.time.DateTime;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.ItemHandlerClientInterface;
import de.escidoc.core.client.rest.RestItemHandlerClient;
import de.escidoc.core.client.soap.SoapItemHandlerClient;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.common.Relations;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.common.properties.Properties;
import de.escidoc.core.resources.common.versionhistory.VersionHistory;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.om.item.ItemList;
import de.escidoc.core.resources.om.item.component.Component;
import de.escidoc.core.resources.sb.Record;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;
import de.escidoc.core.resources.sb.search.records.ItemRecord;

/**
 * This is the generic ItemClientHandler which binds the transport specific
 * classes. The transport specification is done via properties configuration of
 * the eSciDoc client.
 * 
 * @author SWA
 * 
 */
public class ItemHandlerClient
    extends AbstractHandlerClient<SoapItemHandlerClient, RestItemHandlerClient>
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
    public ItemHandlerClient(final String serviceAddress) {
        super(serviceAddress);
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
    public Item create(final Item item) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        String itemString =
            Factory
                .getMarshallerFactory(getTransport()).getItemMarshaller()
                .marshalDocument(item);

        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().create(itemString);
        }
        else {
            xml = getRestHandlerClient().create(itemString);
        }
        return Factory
            .getMarshallerFactory(getTransport()).getItemMarshaller()
            .unmarshalDocument(xml);
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
    public Component createComponent(final String id, final Component component)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String componentString =
            Factory
                .getMarshallerFactory(getTransport()).getComponentMarshaller()
                .marshalDocument(component);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().create(componentString);
        }
        else {
            xml = getRestHandlerClient().create(componentString);
        }
        return Factory
            .getMarshallerFactory(getTransport()).getComponentMarshaller()
            .unmarshalDocument(xml);
    }

    /**
     * Create Component.
     * 
     * @param item
     *            Item where Component will be part of.
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
    public Component createComponent(final Item item, final Component component)
        throws EscidocException, InternalClientException, TransportException {

        // FIXME seems no relation to Item required
        // String xml = null;
        // String componentString = Factory.getMarshallerFactory(getTransport())
        // .getComponentMarshaller().marshalDocument(component);
        // if (getTransport() == TransportProtocol.SOAP) {
        // xml = getSoapHandlerClient().create(componentString);
        // }
        // else {
        // xml = getRestHandlerClient().create(componentString);
        // }
        // return Factory.getMarshallerFactory(getTransport())
        // .getComponentMarshaller().unmarshalDocument(xml);
        throw new InternalClientException("Method not yet supported.");
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
    public Item retrieve(final String id) throws EscidocException,
        InternalClientException, TransportException {

        String itemString = null;
        if (getTransport() == TransportProtocol.SOAP) {
            itemString = getSoapHandlerClient().retrieve(id);
        }
        else {
            itemString = getRestHandlerClient().retrieve(id);
        }
        return Factory
            .getMarshallerFactory(getTransport()).getItemMarshaller()
            .unmarshalDocument(itemString);
    }

    /**
     * Retrieve Item.
     * 
     * @param item
     *            Item class with values which Item is to retrieve (obji of Item
     *            is important factor).
     * @return Item
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public Item retrieve(final Item item) throws EscidocException,
        InternalClientException, TransportException {
        
        return retrieve(item.getObjid());
    }

    /**
     * Retrieve properties of Item.
     * 
     * @param id
     *            Objid of Item
     * @return Properties of Item
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level. FIXME
     *             implement this method
     */
    public Properties retrieveProperties(final String id)
        throws EscidocException, InternalClientException, TransportException {

        // String properties = null;
        // if (getTransport() == TransportProtocol.SOAP) {
        // properties = getSoapHandlerClient().retrieveProperties(id);
        // }
        // else {
        // properties = getRestHandlerClient().retrieveProperties(id);
        // }
        // return
        // Factory.getPropertiesMarshaller().unmarshalDocument(properties);

        throw new InternalClientException("Method not yet supported.");
    }

    /**
     * Retrieve Items (Filter for Items).
     * 
     * @param taskParam
     *            Filter parameter
     * @return ItemmList
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Deprecated
    public ItemList retrieveItems(final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String taskParamString =
            Factory
                .getMarshallerFactory(getTransport()).getTaskParamMarshaller()
                .marshalDocument(taskParam);
        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveItems(taskParamString);
        }
        else {
            xml = getRestHandlerClient().retrieveItems(taskParamString);
        }
        return Factory
            .getMarshallerFactory(getTransport()).getItemListMarshaller()
            .unmarshalDocument(xml);
    }

    /**
     * Retrieve Items (Filter for Items).
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
    public SearchRetrieveResponse retrieveItems(
        final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException {

        evalRequest(filter);

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveItems(filter);
        }
        else {
            xml = getRestHandlerClient().retrieveItems(filter);
        }
        return Factory
            .getMarshallerFactory(getTransport())
            .getSearchRetrieveResponseMarshaller().unmarshalDocument(xml);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Collection<Item> retrieveItemsAsList(
        final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException {

        SearchRetrieveResponse response = retrieveItems(filter);
        Collection<Item> items = new LinkedList<Item>();

        for (Record record : response.getRecords()) {
            if (record instanceof ItemRecord) {
                ItemRecord itemRecord = (ItemRecord) record;
                Item item = itemRecord.getRecordData();
                if (item != null) {
                    items.add(item);
                }
            }
        }
        return items;
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
    public ExplainResponse retrieveItems(final ExplainRequestType filter)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveItems(filter);
        }
        else {
            xml = getRestHandlerClient().retrieveItems(filter);
        }

        return Factory
            .getMarshallerFactory(getTransport())
            .getExplainResponseMarshaller().unmarshalDocument(xml);
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
    public Relations retrieveRelations(final String id)
        throws EscidocException, InternalClientException, TransportException {
        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveRelations(id);
        }
        else {
            xml = getRestHandlerClient().retrieveRelations(id);
        }
        return Factory
            .getMarshallerFactory(getTransport()).getRelationsMarshaller()
            .unmarshalDocument(xml);

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
    public VersionHistory retrieveVersionHistory(final String id)
        throws EscidocException, InternalClientException, TransportException {

        String versionHistory = null;
        if (getTransport() == TransportProtocol.SOAP) {
            versionHistory = getSoapHandlerClient().retrieveVersionHistory(id);
        }
        else {
            versionHistory = getRestHandlerClient().retrieveVersionHistory(id);
        }
        return Factory
            .getMarshallerFactory(getTransport()).getVersionHistoryMarshaller()
            .unmarshalDocument(versionHistory);
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
    public VersionHistory retrieveVersionHistory(final Item item)
        throws EscidocException, InternalClientException, TransportException {

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
    public void delete(final String id) throws EscidocException,
        InternalClientException, TransportException {

        if (getTransport() == TransportProtocol.SOAP) {
            getSoapHandlerClient().delete(id);
        }
        else {
            getRestHandlerClient().delete(id);
        }
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
    public Item update(final Item item) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        String itemString =
            Factory
                .getMarshallerFactory(getTransport()).getItemMarshaller()
                .marshalDocument(item);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().update(item.getObjid(), itemString);
        }
        else {
            xml = getRestHandlerClient().update(item.getObjid(), itemString);
        }

        return Factory
            .getMarshallerFactory(getTransport()).getItemMarshaller()
            .unmarshalDocument(xml);
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
    public Result submit(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            Factory
                .getMarshallerFactory(getTransport()).getTaskParamMarshaller()
                .marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().submit(id, taskParamString);
        }
        else {
            xml = getRestHandlerClient().submit(id, taskParamString);
        }
        return Factory
            .getMarshallerFactory(getTransport()).getResultMarshaller()
            .unmarshalDocument(xml);
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
    public Result submit(final Item item, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

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
    public Result release(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            Factory
                .getMarshallerFactory(getTransport()).getTaskParamMarshaller()
                .marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().release(id, taskParamString);
        }
        else {
            xml = getRestHandlerClient().release(id, taskParamString);
        }
        return Factory
            .getMarshallerFactory(getTransport()).getResultMarshaller()
            .unmarshalDocument(xml);
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
    public Result release(final Item item, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

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
    public Result revise(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            Factory
                .getMarshallerFactory(getTransport()).getTaskParamMarshaller()
                .marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().revise(id, taskParamString);
        }
        else {
            xml = getRestHandlerClient().revise(id, taskParamString);
        }
        return Factory
            .getMarshallerFactory(getTransport()).getResultMarshaller()
            .unmarshalDocument(xml);
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
    public Result revise(final Item item, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

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
    public Result withdraw(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            Factory
                .getMarshallerFactory(getTransport()).getTaskParamMarshaller()
                .marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().withdraw(id, taskParamString);
        }
        else {
            xml = getRestHandlerClient().withdraw(id, taskParamString);
        }
        return Factory
            .getMarshallerFactory(getTransport()).getResultMarshaller()
            .unmarshalDocument(xml);
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
    public Result withdraw(final Item item, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

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
    public Result lock(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            Factory
                .getMarshallerFactory(getTransport()).getTaskParamMarshaller()
                .marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().lock(id, taskParamString);
        }
        else {
            xml = getRestHandlerClient().lock(id, taskParamString);
        }
        return Factory
            .getMarshallerFactory(getTransport()).getResultMarshaller()
            .unmarshalDocument(xml);
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
    public Result lock(final Item item, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

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
    public Result unlock(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            Factory
                .getMarshallerFactory(getTransport()).getTaskParamMarshaller()
                .marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().unlock(id, taskParamString);
        }
        else {
            xml = getRestHandlerClient().unlock(id, taskParamString);
        }
        return Factory
            .getMarshallerFactory(getTransport()).getResultMarshaller()
            .unmarshalDocument(xml);
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
    public Result unlock(final Item item, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

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
    public Result assignVersionPid(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            Factory
                .getMarshallerFactory(getTransport()).getTaskParamMarshaller()
                .marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().assignVersionPid(id, taskParamString);
        }
        else {
            xml = getRestHandlerClient().assignVersionPid(id, taskParamString);
        }

        return Factory
            .getMarshallerFactory(getTransport()).getResultMarshaller()
            .unmarshalDocument(xml);
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
    public Result assignVersionPid(final Item item, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

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
    public Result assignObjectPid(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            Factory
                .getMarshallerFactory(getTransport()).getTaskParamMarshaller()
                .marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().assignObjectPid(id, taskParamString);
        }
        else {
            xml = getRestHandlerClient().assignObjectPid(id, taskParamString);
        }
        return Factory
            .getMarshallerFactory(getTransport()).getResultMarshaller()
            .unmarshalDocument(xml);
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
    public Result assignObjectPid(final Item item, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

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
    public Result assignContentPid(
        final String id, final String componentId, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            Factory
                .getMarshallerFactory(getTransport()).getTaskParamMarshaller()
                .marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapHandlerClient().assignContentPid(id, componentId,
                    taskParamString);
        }
        else {
            xml =
                getRestHandlerClient().assignContentPid(id, componentId,
                    taskParamString);
        }
        return Factory
            .getMarshallerFactory(getTransport()).getResultMarshaller()
            .unmarshalDocument(xml);
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
    public Result assignContentPid(
        final Item item, final String componentId, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        return assignContentPid(item.getObjid(), componentId, taskParam);
    }

    /**
     * Get last-modification-date of Item.
     * 
     * @param id
     *            Objid
     * @return last-modification-date of resource.
     * 
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public DateTime getLastModificationDate(final String id)
        throws EscidocException, InternalClientException, TransportException {

        if (getTransport() == TransportProtocol.SOAP) {
            return getSoapHandlerClient().getLastModificationDate(id);
        }
        else {
            return getRestHandlerClient().getLastModificationDate(id);
        }
    }

    @Override
    protected SoapItemHandlerClient getSoapHandlerClientInstance()
        throws InternalClientException {
        return new SoapItemHandlerClient(getServiceAddress());
    }

    @Override
    protected RestItemHandlerClient getRestHandlerClientInstance()
        throws InternalClientException {
        return new RestItemHandlerClient(getServiceAddress());
    }
}
