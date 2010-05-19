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

/**
 * This is the generic ItemClientHandler which binds the transport specific
 * classes. The transport specification is done via properties configuration of
 * the eSciDoc client.
 * 
 * @author SWA
 * 
 */
public class ItemHandlerClient implements ItemHandlerClientInterface<Item> {

    // Set SOAP as default transport protocol (for now :-()
    private TransportProtocol transport = TransportProtocol.SOAP;

    private SoapItemHandlerClient soapItemHandlerClient = null;

    private RestItemHandlerClient restItemHandlerClient = null;

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
        String itemString = Factory.getItemMarshaller().marshalDocument(item);

        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapItemHandlerClient().create(itemString);
        }
        else {
            xml = getRestItemHandlerClient().create(itemString);
        }
        return Factory.getItemMarshaller().unmarshalDocument(xml);
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
            Factory.getComponentMarshaller().marshalDocument(component);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapItemHandlerClient().create(componentString);
        }
        else {
            xml = getRestItemHandlerClient().create(componentString);
        }
        return Factory.getComponentMarshaller().unmarshalDocument(xml);
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
        String xml = null;
        String componentString =
            Factory.getComponentMarshaller().marshalDocument(component);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapItemHandlerClient().create(componentString);
        }
        else {
            xml = getRestItemHandlerClient().create(componentString);
        }
        return Factory.getComponentMarshaller().unmarshalDocument(xml);
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
            itemString = getSoapItemHandlerClient().retrieve(id);
        }
        else {
            itemString = getRestItemHandlerClient().retrieve(id);
        }
        return Factory.getItemMarshaller().unmarshalDocument(itemString);
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

        String itemString = null;
        if (getTransport() == TransportProtocol.SOAP) {
            itemString = getSoapItemHandlerClient().retrieve(item.getObjid());
        }
        else {
            itemString = getRestItemHandlerClient().retrieve(item.getObjid());
        }
        return Factory.getItemMarshaller().unmarshalDocument(itemString);
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
        // properties = getSoapItemHandlerClient().retrieveProperties(id);
        // }
        // else {
        // properties = getRestItemHandlerClient().retrieveProperties(id);
        // }
        // return
        // Factory.getPropertiesMarshaller().unmarshalDocument(properties);

        throw new InternalClientException("method not yet supported");
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
    public ItemList retrieveItems(final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String taskParamString =
            Factory.getTaskParamMarshaller().marshalDocument(taskParam);
        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapItemHandlerClient().retrieveItems(taskParamString);
        }
        else {
            xml = getRestItemHandlerClient().retrieveItems(taskParamString);
        }
        return Factory.getItemListMarshaller().unmarshalDocument(xml);
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
            xml = getSoapItemHandlerClient().retrieveRelations(id);
        }
        else {
            xml = getRestItemHandlerClient().retrieveRelations(id);
        }
        return Factory.getRelationsMarshaller().unmarshalDocument(xml);

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
            versionHistory =
                getSoapItemHandlerClient().retrieveVersionHistory(id);
        }
        else {
            versionHistory =
                getRestItemHandlerClient().retrieveVersionHistory(id);
        }
        return Factory.getVersionHistoryMarshaller().unmarshalDocument(
            versionHistory);
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
            getSoapItemHandlerClient().delete(id);
        }
        else {
            getRestItemHandlerClient().delete(id);
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
        String itemString = Factory.getItemMarshaller().marshalDocument(item);
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapItemHandlerClient().update(item.getObjid(), itemString);
        }
        else {
            xml =
                getRestItemHandlerClient().update(item.getObjid(), itemString);
        }

        return Factory.getItemMarshaller().unmarshalDocument(xml);
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
            Factory.getTaskParamMarshaller().marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapItemHandlerClient().submit(id, taskParamString);
        }
        else {
            xml = getRestItemHandlerClient().submit(id, taskParamString);
        }
        return Factory.getResultMarshaller().unmarshalDocument(xml);
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
            Factory.getTaskParamMarshaller().marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapItemHandlerClient().release(id, taskParamString);
        }
        else {
            xml = getRestItemHandlerClient().release(id, taskParamString);
        }
        return Factory.getResultMarshaller().unmarshalDocument(xml);
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
            Factory.getTaskParamMarshaller().marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapItemHandlerClient().revise(id, taskParamString);
        }
        else {
            xml = getRestItemHandlerClient().revise(id, taskParamString);
        }
        return Factory.getResultMarshaller().unmarshalDocument(xml);
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
            Factory.getTaskParamMarshaller().marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapItemHandlerClient().withdraw(id, taskParamString);
        }
        else {
            xml = getRestItemHandlerClient().withdraw(id, taskParamString);
        }
        return Factory.getResultMarshaller().unmarshalDocument(xml);
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
            Factory.getTaskParamMarshaller().marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapItemHandlerClient().lock(id, taskParamString);
        }
        else {
            xml = getRestItemHandlerClient().lock(id, taskParamString);
        }
        return Factory.getResultMarshaller().unmarshalDocument(xml);
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
            Factory.getTaskParamMarshaller().marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapItemHandlerClient().unlock(id, taskParamString);
        }
        else {
            xml = getRestItemHandlerClient().unlock(id, taskParamString);
        }
        return Factory.getResultMarshaller().unmarshalDocument(xml);
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
            Factory.getTaskParamMarshaller().marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapItemHandlerClient()
                    .assignVersionPid(id, taskParamString);
        }
        else {
            xml =
                getRestItemHandlerClient()
                    .assignVersionPid(id, taskParamString);
        }

        return Factory.getResultMarshaller().unmarshalDocument(xml);
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
            Factory.getTaskParamMarshaller().marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapItemHandlerClient().assignObjectPid(id, taskParamString);
        }
        else {
            xml =
                getRestItemHandlerClient().assignObjectPid(id, taskParamString);
        }
        return Factory.getResultMarshaller().unmarshalDocument(xml);
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
            Factory.getTaskParamMarshaller().marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapItemHandlerClient().assignContentPid(id, componentId,
                    taskParamString);
        }
        else {
            xml =
                getRestItemHandlerClient().assignContentPid(id, componentId,
                    taskParamString);
        }
        return Factory.getResultMarshaller().unmarshalDocument(xml);
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
            return getSoapItemHandlerClient().getLastModificationDate(id);
        }
        else {
            return getRestItemHandlerClient().getLastModificationDate(id);
        }
    }

    /**
     * Login.
     * 
     * @param serviceAddress
     *            URL of framework
     * @param username
     *            Username/ID
     * @param password
     *            Password
     * @return Login-Handle.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Deprecated
    public String login(
        final String serviceAddress, final String username,
        final String password) throws EscidocException,
        InternalClientException, TransportException {

        if (getTransport() == TransportProtocol.SOAP) {
            return getSoapItemHandlerClient().login(serviceAddress, username,
                password);
        }
        else {
            return getRestItemHandlerClient().login(serviceAddress, username,
                password);
        }
    }

    /**
     * Logout.
     * 
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Deprecated
    public void logout() throws EscidocException, InternalClientException,
        TransportException {

        setHandle("");
    }

    /**
     * Get Login-Handle.
     * 
     * @return Login-Handle
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     */
    public String getHandle() throws InternalClientException {

        if (getTransport() == TransportProtocol.SOAP) {
            return getSoapItemHandlerClient().getHandle();
        }
        else {
            return getRestItemHandlerClient().getHandle();
        }
    }

    /**
     * Set Login-Handle.
     * 
     * @param handle
     *            Login-Handle
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     */
    public void setHandle(final String handle) throws InternalClientException {

        if (getTransport() == TransportProtocol.SOAP) {
            getSoapItemHandlerClient().setHandle(handle);
        }
        else {
            getRestItemHandlerClient().setHandle(handle);
        }
    }

    /**
     * Set the service endpoint address.
     * 
     * @param address
     *            URL of the service endpoint.
     * @throws InternalClientException
     *             Thrown if URL is not valid.
     */
    public void setServiceAddress(final String address)
        throws InternalClientException {

        if (getTransport() == TransportProtocol.SOAP) {
            getSoapItemHandlerClient().setServiceAddress(address);
        }
        else {
            getRestItemHandlerClient().setServiceAddress(address);
        }
    }

    /**
     * Get the SOAP handler.
     * 
     * @return SoapItemHandlerClient
     * @throws InternalClientException
     *             Thrown if creating instance of SoapItemHandlerClient failed.
     */
    public SoapItemHandlerClient getSoapItemHandlerClient()
        throws InternalClientException {
        if (this.soapItemHandlerClient == null) {
            this.soapItemHandlerClient = new SoapItemHandlerClient();
        }
        return this.soapItemHandlerClient;
    }

    /**
     * Get the REST handler.
     * 
     * @return RestItemHandlerClient
     * @throws InternalClientException
     *             Thrown if creating instance of RestItemHandlerClient failed.
     */
    public RestItemHandlerClient getRestItemHandlerClient()
        throws InternalClientException {
        if (this.restItemHandlerClient == null) {
            this.restItemHandlerClient = new RestItemHandlerClient();
        }
        return this.restItemHandlerClient;
    }

    /**
     * Set the Transport Protocol (REST/SOAP).
     * 
     * @param tp
     *            The transport protocol.
     */
    public void setTransport(final TransportProtocol tp) {
        this.transport = tp;
    }

    /**
     * Set the Transport Protocol (REST/SOAP).
     * 
     * @return The used transport protocol.
     */
    public TransportProtocol getTransport() {
        return this.transport;
    }

}
