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
     * Create ContainerSoapHandlerClient instance. The service protocol
     * (REST/SOAP/..) selected from the configuration. Default is SOAP.
     * 
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public ContainerHandlerClient() throws EscidocException,
        InternalClientException, TransportException {
    }

    /**
     * Create Container within the repository.
     * 
     * @param container
     *            The Container which is to create.
     * @return The created Container
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
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
     * Retrieve Container from Repository.
     * 
     * @param id
     *            Objid of the Container
     * @return The Container with the provided obid.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
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

    /**
     * Retrieve Container Properties from Repository.
     * 
     * @param id
     *            Objid of Container
     * @return Container Properties.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public Properties retrieveProperties(final String id)
        throws EscidocException, InternalClientException, TransportException {

        throw new InternalClientException("method not yet supported");
    }

    /**
     * Get Version History from Container.
     * 
     * @param id
     *            Objid of Container.
     * @return Version History
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
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
     * Delete Container.
     * 
     * @param id
     *            Objid of Container.
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
     *            The Container which is to store within Repository.
     * @return Updated Container
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
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
     * Set Container to status submit.
     * 
     * @param id
     *            Objid of Container.
     * @param taskParam
     *            TaskParameter for submit.
     * @return Result message of method.
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
            xml = getSoapContainerHandlerClient().submit(id, taskParamString);
        }
        else {
            xml = getRestContainerHandlerClient().submit(id, taskParamString);
        }

        return Factory.getResultMarshaller().unmarshalDocument(xml);
    }

    /**
     * Set Container to status submit.
     * 
     * @param container
     *            The Container.
     * @param taskParam
     *            TaskParameter for submit.
     * @return Result message of method.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public Result submit(final Container container, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        return submit(container.getObjid(), taskParam);
    }

    /**
     * Set Container to status released.
     * 
     * @param id
     *            Objid of Container.
     * @param taskParam
     *            TaskParameter for release.
     * @return Result message of method.
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
            xml = getSoapContainerHandlerClient().release(id, taskParamString);
        }
        else {
            xml = getRestContainerHandlerClient().release(id, taskParamString);
        }
        return Factory.getResultMarshaller().unmarshalDocument(xml);
    }

    /**
     * Set Container to status released.
     * 
     * @param container
     *            The Container.
     * @param taskParam
     *            TaskParameter for release.
     * @return Result message of method.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public Result release(final Container container, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        return release(container.getObjid(), taskParam);
    }

    /**
     * Set Container to status revised.
     * 
     * @param id
     *            Objid of Container.
     * @param taskParam
     *            TaskParameter for revised.
     * @return Result message of method.
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
            xml = getSoapContainerHandlerClient().revise(id, taskParamString);
        }
        else {
            xml = getRestContainerHandlerClient().revise(id, taskParamString);
        }
        return Factory.getResultMarshaller().unmarshalDocument(xml);
    }

    /**
     * Set Container to status revised.
     * 
     * @param container
     *            The Container.
     * @param taskParam
     *            TaskParameter for revise.
     * @return Result message of method.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public Result revise(final Container container, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        return revise(container.getObjid(), taskParam);
    }

    /**
     * Set Container to status withdrawn.
     * 
     * @param id
     *            Objid of Container.
     * @param taskParam
     *            TaskParameter for withdraw.
     * @return Result message of method.
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
            xml = getSoapContainerHandlerClient().withdraw(id, taskParamString);
        }
        else {
            xml = getRestContainerHandlerClient().withdraw(id, taskParamString);
        }
        return Factory.getResultMarshaller().unmarshalDocument(xml);
    }

    /**
     * Set Container to status withdrawn.
     * 
     * @param container
     *            The Container.
     * @param taskParam
     *            TaskParameter for withdraw.
     * @return Result message of method.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public Result withdraw(final Container container, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        return withdraw(container.getObjid(), taskParam);
    }

    /**
     * Lock the Container.
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
            xml = getSoapContainerHandlerClient().lock(id, taskParamString);
        }
        else {
            xml = getRestContainerHandlerClient().lock(id, taskParamString);
        }
        return Factory.getResultMarshaller().unmarshalDocument(xml);
    }

    /**
     * Unlock the Container.
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
     * Assign a PID to the latest version of Container.
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
     * Assign a object PID to the Container.
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
     * Add Content Relation.
     * 
     * @param id
     *            Objid of Container.
     * @param taskParam
     *            TaskParameter.
     * @return Updated Container.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public Container addContentRelations(
        final String id, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

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
     * Remove Content Relation.
     * 
     * @param id
     *            Objid of Container.
     * @param taskParam
     *            TaskParameter.
     * @return Updated Container.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public Container removeContentRelations(
        final String id, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

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
     * Add Members to Container.
     * 
     * @param id
     *            Objid of Container.
     * @param taskParam
     *            TaskParameter.
     * @return Updated Container.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public Result addMembers(final String id, final TaskParam taskParam)
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
        return Factory.getResultMarshaller().unmarshalDocument(xml);
    }

    /**
     * Remove Member from Container.
     * 
     * @param id
     *            Objid of Container.
     * @param taskParam
     *            TaskParameter.
     * @return Updated Container.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public Result removeMembers(final String id, final TaskParam taskParam)
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
        return Factory.getResultMarshaller().unmarshalDocument(xml);
    }

    /**
     * Create Item as member of the Container.
     * 
     * @param id
     *            Objid of Container.
     * @param item
     *            The new Item which is to create and simultaniously to set as
     *            member of the Container.
     * @return Updated Container.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
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
     * Create Container as member of the Container.
     * 
     * @param id
     *            Objid of Container.
     * @param container
     *            The new Container which is to create and simultaniously to set
     *            as member of the Container.
     * @return Updated Container.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public Container createContainer(final String id, final Container container)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String containerString =
            Factory.getContainerMarshaller().marshalDocument(container);
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapContainerHandlerClient().createContainer(id, containerString);
        }
        else {
            xml =
                getRestContainerHandlerClient().createContainer(id, containerString);
        }
        return Factory.getContainerMarshaller().unmarshalDocument(xml);
    }

    /**
     * Retrieve struct-map of Container from framework.
     * 
     * @param id
     *            Id of Container.
     * @return StructMap
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
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

    /**
     * Retrieve Containers via filter from framework.
     * 
     * @param taskParam
     *            Expression of Filter language.
     * @return ContainerList
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public ContainerList retrieveContainers(final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String taskParamString =
            Factory.getTaskParamMarshaller().marshalDocument(taskParam);
        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapContainerHandlerClient().retrieveContainers(
                    taskParamString);
        }
        else {
            xml =
                getRestContainerHandlerClient().retrieveContainers(
                    taskParamString);
        }
        return Factory.getContainerListMarshaller().unmarshalDocument(xml);
    }

    /**
     * Retrieve relations.
     * 
     * @param id
     *            Id of Container.
     * @return Relations of the Container.
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
            xml = getSoapContainerHandlerClient().retrieveRelations(id);
        }
        else {
            xml = getRestContainerHandlerClient().retrieveRelations(id);
        }
        return Factory.getRelationsMarshaller().unmarshalDocument(xml);

    }

    /**
     * Retrieve Members.
     * 
     * @param id
     *            Id of Container.
     * @param taskParam
     *            filter
     * @return Members of the Container.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public MemberList retrieveMembers(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            Factory.getTaskParamMarshaller().marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {

            xml =
                getSoapContainerHandlerClient().retrieveMembers(id,
                    taskParamString);
        }
        else {
            xml =
                getRestContainerHandlerClient().retrieveMembers(id,
                    taskParamString);
        }
        return Factory.getMemberListMarshaller().unmarshalDocument(xml);

    }

    /**
     * Get last-modification-date of Container.
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
    public String login(
        final String serviceAddress, final String username,
        final String password) throws EscidocException,
        InternalClientException, TransportException {

        if (getTransport() == TransportProtocol.SOAP) {
            return getSoapContainerHandlerClient().login(serviceAddress,
                username, password);
        }
        else {
            return getRestContainerHandlerClient().login(serviceAddress,
                username, password);
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
    public void logout() throws EscidocException, InternalClientException,
        TransportException {

        setHandle("");
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
            getSoapContainerHandlerClient().setHandle(handle);
        }
        else {
            getRestContainerHandlerClient().setHandle(handle);
        }
    }

    /**
     * Get SOAP Container Handler.
     * 
     * @return the soapContainerHandlerClient
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     */
    public SoapContainerHandlerClient getSoapContainerHandlerClient()
        throws InternalClientException {

        if (this.soapContainerHandlerClient == null) {
            this.soapContainerHandlerClient = new SoapContainerHandlerClient();
        }
        return this.soapContainerHandlerClient;
    }

    /**
     * Get REST Container Handler.
     * 
     * @return the soapContainerHandlerClient
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
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

        if (getTransport() == TransportProtocol.SOAP) {
            getSoapContainerHandlerClient().setServiceAddress(address);
        }
        else {
            getRestContainerHandlerClient().setServiceAddress(address);
        }
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
