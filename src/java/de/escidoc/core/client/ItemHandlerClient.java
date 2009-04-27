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

import de.escidoc.core.client.exceptions.EscidocClientException;
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
     * See Interface for functional description.
     *
     * @param item
     * @return
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.ItemHandlerClientInterface#create(de.escidoc.core.resources.interfaces.item.ItemInterface)
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
     * See Interface for functional description.
     *
     * @param id
     *            The item id.
     * @param component
     *            The new component.
     * @return
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.ItemHandlerClientInterface#createComponent(de.escidoc.core.resources.interfaces.item.ItemInterface)
     */
    public Component createComponent(final String id, final Component component)
        throws EscidocClientException, InternalClientException,
        TransportException {

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
     * See Interface for functional description.
     *
     * @param id
     * @return
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.ItemHandlerClientInterface#retrieve(java.lang.String)
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
     *
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
     * See Interface for functional description.
     *
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.interfaces.ItemHandlerClientInterface#retrieveItems(de.escidoc.core.resources.common.TaskParam)
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
     *
     * @param id
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    public Relations retrieveRelations(final String id) throws EscidocClientException, InternalClientException,
    TransportException {
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
     * See Interface for functional description.
     *
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.interfaces.ItemHandlerClientInterface#retrieveVersionHistory(java.lang.String)
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
     * See Interface for functional description.
     *
     * @param id
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.ItemHandlerClientInterface#delete(java.lang.String)
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
     * See Interface for functional description.
     *
     * @param item
     * @return
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.ItemHandlerClientInterface#update(de.escidoc.core.resources.interfaces.item.ItemInterface)
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
     * See Interface for functional description.
     *
     * @param id
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.interfaces.ItemHandlerClientInterface#submit(java.lang.String,
     *      de.escidoc.core.resources.common.TaskParam)
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
     * See Interface for functional description.
     *
     * @param id
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.interfaces.ItemHandlerClientInterface#release(java.lang.String,
     *      de.escidoc.core.resources.common.TaskParam)
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
     * See Interface for functional description.
     *
     * @param id
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.interfaces.ItemHandlerClientInterface#revise(java.lang.String,
     *      de.escidoc.core.resources.common.TaskParam)
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
     *
     * @param id
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
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
     * See Interface for functional description.
     *
     * @param id
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.interfaces.ItemHandlerClientInterface#lock(java.lang.String,
     *      de.escidoc.core.resources.common.TaskParam)
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
     * See Interface for functional description.
     *
     * @param id
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.interfaces.ItemHandlerClientInterface#unlock(java.lang.String,
     *      de.escidoc.core.resources.common.TaskParam)
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

    /*
     * Assign PID methods
     */

    /**
     * See Interface for functional description.
     *
     * @param id
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.interfaces.ItemHandlerClientInterface#assignVersionPid(java.lang.String,
     *      de.escidoc.core.resources.common.TaskParam)
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
     * See Interface for functional description.
     *
     * @param id
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.interfaces.ItemHandlerClientInterface#assignObjectPid(java.lang.String,
     *      de.escidoc.core.resources.common.TaskParam)
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
     * See Interface for functional description.
     *
     * @param id
     * @param componentId
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.interfaces.ItemHandlerClientInterface#assignContentPid(java.lang.String,
     *      java.lang.String, de.escidoc.core.resources.common.TaskParam)
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
     * See Interface for functional description.
     *
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.interfaces.ItemHandlerClientInterface#getLastModificationDate(java.lang.String)
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
     * See Interface for functional description.
     *
     * @param handle
     * @throws InternalClientException
     * @see de.escidoc.core.client.interfaces.BaseClientHandlerInterface#setHandle(java.lang.String)
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
