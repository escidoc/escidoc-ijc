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
import de.escidoc.core.client.interfaces.ContainerHandlerClientInterface;
import de.escidoc.core.client.rest.RestContainerHandlerClient;
import de.escidoc.core.client.soap.SoapContainerHandlerClient;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.common.Relations;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.common.properties.Properties;
import de.escidoc.core.resources.common.structmap.StructMap;
import de.escidoc.core.resources.common.versionhistory.VersionHistory;
import de.escidoc.core.resources.om.MemberList;
import de.escidoc.core.resources.om.container.Container;
import de.escidoc.core.resources.om.container.ContainerList;
import de.escidoc.core.resources.om.item.Item;

/**
 * This is the generic ContainerSoapContainerHandlerClient which binds the
 * transport specific classes. The transport specification is done via
 * properties configuration of the eSciDoc client.
 *
 * @author SWA
 *
 */
public class ContainerHandlerClient
    implements ContainerHandlerClientInterface<Container> {

    // Set SOAP as default transport protocol (for now :-()
    private TransportProtocol transport = TransportProtocol.SOAP;

    private SoapContainerHandlerClient soapContainerHandlerClient = null;

    private RestContainerHandlerClient restContainerHandlerClient = null;

    /**
     * Create ContainersoapContainerHandlerClient instance. The service protocol
     * (REST/SOAP/..) selected from the configuration. Default is SOAP.
     *
     * @throws ClientException
     *
     */
    public ContainerHandlerClient() throws EscidocException,
        InternalClientException, TransportException {
    }

    /**
     * See Interface for functional description.
     *
     * @param container
     * @return
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#create(de.escidoc.core.resources.interfaces.container.ContainerInterface)
     */
    public Container create(final Container container) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        String containerXml =
            Factory.getContainerMarshaller().marshalDocument(container);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapContainerHandlerClient().create(containerXml);
        }
        else {
            xml = getRestContainerHandlerClient().create(containerXml);

        }
        return Factory.getContainerMarshaller().unmarshalDocument(xml);
    }

    /**
     * See Interface for functional description.
     *
     * @param id
     * @return
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#retrieve(java.lang.String)
     */
    public Container retrieve(final String id) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapContainerHandlerClient().retrieve(id);
        }
        else {
            xml = getRestContainerHandlerClient().retrieve(id);
        }
        return Factory.getContainerMarshaller().unmarshalDocument(xml);
    }

    public Properties retrieveProperties(final String id)
        throws EscidocException, InternalClientException, TransportException {

        throw new InternalClientException("method not yet supported");
    }

    /**
     * See Interface for functional description.
     *
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#retrieveVersionHistory(java.lang.String)
     */
    public VersionHistory retrieveVersionHistory(final String id)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapContainerHandlerClient().retrieveVersionHistory(id);
        }
        else {
            xml = getRestContainerHandlerClient().retrieveVersionHistory(id);
        }
        return Factory.getVersionHistoryMarshaller().unmarshalDocument(xml);
    }

    /**
     * See Interface for functional description.
     *
     * @param id
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#delete(java.lang.String)
     */
    public void delete(final String id) throws EscidocException,
        InternalClientException, TransportException {

        if (getTransport() == TransportProtocol.SOAP) {
            getSoapContainerHandlerClient().delete(id);
        }
        else {
            getRestContainerHandlerClient().delete(id);
        }
    }

    /**
     * See Interface for functional description.
     *
     * @param container
     * @return
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#update(de.escidoc.core.resources.interfaces.container.ContainerInterface)
     */
    public Container update(final Container container) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        String containerXml =
            Factory.getContainerMarshaller().marshalDocument(container);
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapContainerHandlerClient().update(container.getObjid(),
                    containerXml);
        }
        else {
            xml =
                getRestContainerHandlerClient().update(container.getObjid(),
                    containerXml);
        }
        return Factory.getContainerMarshaller().unmarshalDocument(xml);
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
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#submit(java.lang.String,
     *      de.escidoc.core.resources.common.TaskParam)
     */
    public Result submit(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            Factory.getTaskParamMarshaller().marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapContainerHandlerClient().submit(id, taskParamString);
        }
        else {
            xml = getRestContainerHandlerClient().submit(id, taskParamString);
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
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#release(java.lang.String,
     *      de.escidoc.core.resources.common.TaskParam)
     */
    public Result release(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            Factory.getTaskParamMarshaller().marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapContainerHandlerClient().release(id, taskParamString);
        }
        else {
            xml = getRestContainerHandlerClient().release(id, taskParamString);
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
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#revise(java.lang.String,
     *      de.escidoc.core.resources.common.TaskParam)
     */
    public Result revise(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            Factory.getTaskParamMarshaller().marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapContainerHandlerClient().revise(id, taskParamString);
        }
        else {
            xml = getRestContainerHandlerClient().revise(id, taskParamString);
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
            xml = getSoapContainerHandlerClient().withdraw(id, taskParamString);
        }
        else {
            xml = getRestContainerHandlerClient().withdraw(id, taskParamString);
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
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#lock(java.lang.String,
     *      de.escidoc.core.resources.common.TaskParam)
     */
    public Result lock(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            Factory.getTaskParamMarshaller().marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapContainerHandlerClient().lock(id, taskParamString);
        }
        else {
            xml = getRestContainerHandlerClient().lock(id, taskParamString);
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
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#unlock(java.lang.String,
     *      de.escidoc.core.resources.common.TaskParam)
     */
    public Result unlock(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            Factory.getTaskParamMarshaller().marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapContainerHandlerClient().unlock(id, taskParamString);
        }
        else {
            xml = getRestContainerHandlerClient().unlock(id, taskParamString);
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
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#assignVersionPid(java.lang.String,
     *      de.escidoc.core.resources.common.TaskParam)
     */
    public Result assignVersionPid(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            Factory.getTaskParamMarshaller().marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapContainerHandlerClient().assignVersionPid(id,
                    taskParamString);
        }
        else {
            xml =
                getRestContainerHandlerClient().assignVersionPid(id,
                    taskParamString);
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
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#assignObjectPid(java.lang.String,
     *      de.escidoc.core.resources.common.TaskParam)
     */
    public Result assignObjectPid(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            Factory.getTaskParamMarshaller().marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapContainerHandlerClient().assignObjectPid(id,
                    taskParamString);
        }
        else {
            xml =
                getRestContainerHandlerClient().assignObjectPid(id,
                    taskParamString);
        }
        return Factory.getResultMarshaller().unmarshalDocument(xml);
    }

    /**
     *
     * @param id
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public Container addContentRelations(final String id, TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            Factory.getTaskParamMarshaller().marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapContainerHandlerClient().addContentRelations(id,
                    taskParamString);
        }
        else {
            xml =
                getRestContainerHandlerClient().addContentRelations(id,
                    taskParamString);
        }
        return Factory.getContainerMarshaller().unmarshalDocument(xml);
    }

    /**
     *
     */
    public Container removeContentRelations(final String id, TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            Factory.getTaskParamMarshaller().marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapContainerHandlerClient().removeContentRelations(id,
                    taskParamString);
        }
        else {
            xml =
                getRestContainerHandlerClient().removeContentRelations(id,
                    taskParamString);
        }
        return Factory.getContainerMarshaller().unmarshalDocument(xml);
    }

    /**
     *
     */
    public Container addMembers(final String id, TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            Factory.getTaskParamMarshaller().marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapContainerHandlerClient().addMembers(id, taskParamString);
        }
        else {
            xml =
                getRestContainerHandlerClient().addMembers(id, taskParamString);
        }
        return Factory.getContainerMarshaller().unmarshalDocument(xml);
    }

    /**
     *
     */
    public Container removeMembers(final String id, TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            Factory.getTaskParamMarshaller().marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapContainerHandlerClient().removeMembers(id,
                    taskParamString);
        }
        else {
            xml =
                getRestContainerHandlerClient().removeMembers(id,
                    taskParamString);
        }
        return Factory.getContainerMarshaller().unmarshalDocument(xml);
    }

    /**
     *
     */
    public Item createItem(final String id, final Item item)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String itemString = Factory.getItemMarshaller().marshalDocument(item);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapContainerHandlerClient().createItem(id, itemString);
        }
        else {
            xml = getRestContainerHandlerClient().createItem(id, itemString);
        }
        return Factory.getItemMarshaller().unmarshalDocument(xml);
    }

    /**
     * See Interface for functional description.
     *
     * @param id
     * @param container
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#createContainer(java.lang.String,
     *      java.lang.Object)
     */
    public Container createContainer(final String id, final Container container)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String containerString =
            Factory.getContainerMarshaller().marshalDocument(container);
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapContainerHandlerClient().createItem(id, containerString);
        }
        else {
            xml =
                getRestContainerHandlerClient().createItem(id, containerString);
        }
        return Factory.getContainerMarshaller().unmarshalDocument(xml);
    }

    /**
     *
     */
    public StructMap retrieveStructMap(final String id)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapContainerHandlerClient().retrieveStructMap(id);
        }
        else {
            xml = getRestContainerHandlerClient().retrieveStructMap(id);
        }
        return Factory.getStructMapMarshaller().unmarshalDocument(xml);
    }
/*
 * (non-Javadoc)
 * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#retrieveContainers(de.escidoc.core.resources.common.TaskParam)
 */
    public ContainerList retrieveContainers(final TaskParam taskParam)
    throws EscidocClientException, InternalClientException,
    TransportException {
        String taskParamString =
            Factory.getTaskParamMarshaller().marshalDocument(taskParam);
        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapContainerHandlerClient().retrieveContainers(taskParamString);
        }
        else {
            xml = getRestContainerHandlerClient().retrieveContainers(taskParamString);
        }
        return Factory.getContainerListMarshaller().unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#retrieveRelations(java.lang.String)
     */
    public Relations retrieveRelations(final String id) throws EscidocClientException, InternalClientException,
    TransportException {
        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapContainerHandlerClient().retrieveRelations(id);
        }
        else {
            xml = getRestContainerHandlerClient().retrieveRelations(id);
        }
        return Factory.getRelationsMarshaller().unmarshalDocument(xml);

    }
    /*
     * (non-Javadoc)
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#retrieveMembers(java.lang.String, de.escidoc.core.resources.common.TaskParam)
     */
    public MemberList retrieveMembers(final String id, final TaskParam taskParam) throws EscidocException,
    InternalClientException, TransportException{
    String xml = null;
    String taskParamString =
        Factory.getTaskParamMarshaller().marshalDocument(taskParam);
    if (getTransport() == TransportProtocol.SOAP) {

        xml = getSoapContainerHandlerClient().retrieveMembers(id, taskParamString);
    }
    else {
        xml = getRestContainerHandlerClient().retrieveMembers(id, taskParamString);
    }
    return Factory.getMemberListMarshaller().unmarshalDocument(xml);

}


    /**
     * See Interface for functional description.
     *
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#getLastModificationDate(java.lang.String)
     */
    public DateTime getLastModificationDate(final String id)
        throws EscidocException, InternalClientException, TransportException {

        DateTime lmd = null;
        if (getTransport() == TransportProtocol.SOAP) {
            lmd = getSoapContainerHandlerClient().getLastModificationDate(id);
        }
        else {
            lmd = getRestContainerHandlerClient().getLastModificationDate(id);
        }
        return lmd;
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
            getSoapContainerHandlerClient().setHandle(handle);
        }
        else {
            getRestContainerHandlerClient().setHandle(handle);
        }
    }

    /**
     * @return the soapContainerHandlerClient
     * @throws InternalClientException
     */
    public SoapContainerHandlerClient getSoapContainerHandlerClient()
        throws InternalClientException {

        if (this.soapContainerHandlerClient == null) {
            this.soapContainerHandlerClient = new SoapContainerHandlerClient();
        }
        return this.soapContainerHandlerClient;
    }

    /**
     * @return the soapContainerHandlerClient
     * @throws InternalClientException
     */
    public RestContainerHandlerClient getRestContainerHandlerClient()
        throws InternalClientException {

        if (this.restContainerHandlerClient == null) {
            this.restContainerHandlerClient = new RestContainerHandlerClient();
        }
        return this.restContainerHandlerClient;
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
        getSoapContainerHandlerClient().setServiceAddress(address);
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
