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

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.net.URL;
import java.util.List;

import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.ContainerHandlerClientInterface;
import de.escidoc.core.client.rest.RestContainerHandlerClient;
import de.escidoc.core.client.soap.SoapContainerHandlerClient;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.common.Relations;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.common.structmap.StructMap;
import de.escidoc.core.resources.common.versionhistory.VersionHistory;
import de.escidoc.core.resources.om.container.Container;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;

/**
 * This is the generic ContainerSoapContainerHandlerClient which binds the
 * transport specific classes. The transport specification is done via
 * properties configuration of the eSciDoc client.
 * 
 * @author SWA
 * 
 */
public class ContainerHandlerClient
    extends
    AbstractHandlerClient<SoapContainerHandlerClient, RestContainerHandlerClient>
    implements ContainerHandlerClientInterface {

    /**
     * 
     */
    public ContainerHandlerClient() {
        super();
    }

    /**
     * 
     * @param serviceAddress
     */
    public ContainerHandlerClient(final URL serviceAddress) {
        super(serviceAddress);
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
    @Override
    public Container create(final Container container) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        String containerXml =
            MarshallerFactory
                .getInstance(getTransport()).getMarshaller(Container.class)
                .marshalDocument(container);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().create(containerXml);
        }
        else {
            xml = getRestHandlerClient().create(containerXml);

        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Container.class)
            .unmarshalDocument(xml);
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
    @Override
    public Container retrieve(final String id) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieve(id);
        }
        else {
            xml = getRestHandlerClient().retrieve(id);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Container.class)
            .unmarshalDocument(xml);
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
    // @Override
    // public OrganizationalUnitProperties retrieveProperties(final String id)
    // throws EscidocException, InternalClientException, TransportException {
    //
    // throw new InternalClientException("method not yet supported");
    // }

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
    @Override
    public VersionHistory retrieveVersionHistory(final String id)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveVersionHistory(id);
        }
        else {
            xml = getRestHandlerClient().retrieveVersionHistory(id);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(VersionHistory.class)
            .unmarshalDocument(xml);
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
    @Override
    public void delete(final String id) throws EscidocException,
        InternalClientException, TransportException {

        if (getTransport() == TransportProtocol.SOAP) {
            getSoapHandlerClient().delete(id);
        }
        else {
            getRestHandlerClient().delete(id);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.CrudService#update(java.lang.Object
     * )
     */
    @Override
    public Container update(final Container container) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        String containerXml =
            MarshallerFactory
                .getInstance(getTransport()).getMarshaller(Container.class)
                .marshalDocument(container);
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapHandlerClient().update(container.getObjid(),
                    containerXml);
        }
        else {
            xml =
                getRestHandlerClient().update(container.getObjid(),
                    containerXml);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Container.class)
            .unmarshalDocument(xml);
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
    @Override
    public Result submit(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString = marshalTaskParam(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().submit(id, taskParamString);
        }
        else {
            xml = getRestHandlerClient().submit(id, taskParamString);
        }

        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Result.class)
            .unmarshalDocument(xml);
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
    @Override
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
    @Override
    public Result release(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString = marshalTaskParam(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().release(id, taskParamString);
        }
        else {
            xml = getRestHandlerClient().release(id, taskParamString);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Result.class)
            .unmarshalDocument(xml);
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
    @Override
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
    @Override
    public Result revise(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString = marshalTaskParam(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().revise(id, taskParamString);
        }
        else {
            xml = getRestHandlerClient().revise(id, taskParamString);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Result.class)
            .unmarshalDocument(xml);
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
    @Override
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
    @Override
    public Result withdraw(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString = marshalTaskParam(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().withdraw(id, taskParamString);
        }
        else {
            xml = getRestHandlerClient().withdraw(id, taskParamString);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Result.class)
            .unmarshalDocument(xml);
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
    @Override
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
    @Override
    public Result lock(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString = marshalTaskParam(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().lock(id, taskParamString);
        }
        else {
            xml = getRestHandlerClient().lock(id, taskParamString);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Result.class)
            .unmarshalDocument(xml);
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
    @Override
    public Result unlock(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString = marshalTaskParam(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().unlock(id, taskParamString);
        }
        else {
            xml = getRestHandlerClient().unlock(id, taskParamString);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Result.class)
            .unmarshalDocument(xml);
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
    @Override
    public Result assignVersionPid(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString = marshalTaskParam(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().assignVersionPid(id, taskParamString);
        }
        else {
            xml = getRestHandlerClient().assignVersionPid(id, taskParamString);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Result.class)
            .unmarshalDocument(xml);
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
    @Override
    public Result assignObjectPid(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString = marshalTaskParam(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().assignObjectPid(id, taskParamString);
        }
        else {
            xml = getRestHandlerClient().assignObjectPid(id, taskParamString);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Result.class)
            .unmarshalDocument(xml);
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
    @Override
    public Container addContentRelations(
        final String id, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        String taskParamString = marshalTaskParam(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapHandlerClient().addContentRelations(id, taskParamString);
        }
        else {
            xml =
                getRestHandlerClient().addContentRelations(id, taskParamString);
        }

        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Container.class)
            .unmarshalDocument(xml);
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
    @Override
    public Container removeContentRelations(
        final String id, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        String taskParamString = marshalTaskParam(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapHandlerClient().removeContentRelations(id,
                    taskParamString);
        }
        else {
            xml =
                getRestHandlerClient().removeContentRelations(id,
                    taskParamString);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Container.class)
            .unmarshalDocument(xml);
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
    @Override
    public Result addMembers(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString = marshalTaskParam(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().addMembers(id, taskParamString);
        }
        else {
            xml = getRestHandlerClient().addMembers(id, taskParamString);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Result.class)
            .unmarshalDocument(xml);
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
    @Override
    public Result removeMembers(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString = marshalTaskParam(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().removeMembers(id, taskParamString);
        }
        else {
            xml = getRestHandlerClient().removeMembers(id, taskParamString);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Result.class)
            .unmarshalDocument(xml);
    }

    /**
     * Create Item as member of the Container.
     * 
     * @param id
     *            Objid of Container.
     * @param item
     *            The new Item which is to create and simultaneously to set as
     *            member of the Container.
     * @return Updated Container.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Item createItem(final String id, final Item item)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String itemString =
            MarshallerFactory
                .getInstance(getTransport()).getMarshaller(Item.class)
                .marshalDocument(item);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().createItem(id, itemString);
        }
        else {
            xml = getRestHandlerClient().createItem(id, itemString);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Item.class)
            .unmarshalDocument(xml);
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
    @Override
    public Container createContainer(final String id, final Container container)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String containerString =
            MarshallerFactory
                .getInstance(getTransport()).getMarshaller(Container.class)
                .marshalDocument(container);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().createContainer(id, containerString);
        }
        else {
            xml = getRestHandlerClient().createContainer(id, containerString);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Container.class)
            .unmarshalDocument(xml);
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
    @Override
    public StructMap retrieveStructMap(final String id)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveStructMap(id);
        }
        else {
            xml = getRestHandlerClient().retrieveStructMap(id);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(StructMap.class)
            .unmarshalDocument(xml);
    }

    /**
     * Retrieve Containers (Filter for Containers).
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
    @Override
    public SearchRetrieveResponse retrieveContainers(
        final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveContainers(request);
        }
        else {
            xml = getRestHandlerClient().retrieveContainers(request);
        }
        return MarshallerFactory
            .getInstance(getTransport())
            .getMarshaller(SearchRetrieveResponse.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#
     * retrieveContainersAsList(gov.loc.www.zing.srw.SearchRetrieveRequestType)
     */
    @Override
    public List<Container> retrieveContainersAsList(
        final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException {

        return getSearchRetrieveResponseAsList(Container.class,
            retrieveContainers(filter));
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#
     * retrieveContainers(gov.loc.www.zing.srw.ExplainRequestType)
     */
    @Override
    public ExplainResponse retrieveContainers(final ExplainRequestType filter)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveContainers(filter);
        }
        else {
            xml = getRestHandlerClient().retrieveContainers(filter);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(ExplainResponse.class)
            .unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#
     * retrieveRelations(java.lang.String)
     */
    @Override
    public Relations retrieveRelations(final String id)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveRelations(id);
        }
        else {
            xml = getRestHandlerClient().retrieveRelations(id);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Relations.class)
            .unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#
     * retrieveMembers(de.escidoc.core.resources.om.container.Container,
     * gov.loc.www.zing.srw.SearchRetrieveRequestType)
     */
    @Override
    public SearchRetrieveResponse retrieveMembers(
        final Container container, final SearchRetrieveRequestType filter)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapHandlerClient().retrieveMembers(container.getObjid(),
                    filter);
        }
        else {
            xml =
                getRestHandlerClient().retrieveMembers(container.getObjid(),
                    filter);
        }
        return MarshallerFactory
            .getInstance(getTransport())
            .getMarshaller(SearchRetrieveResponse.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#
     * retrieveMembers(de.escidoc.core.resources.om.container.Container,
     * gov.loc.www.zing.srw.ExplainRequestType)
     */
    @Override
    public ExplainResponse retrieveMembers(
        final Container container, final ExplainRequestType filter)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapHandlerClient().retrieveMembers(container.getObjid(),
                    filter);
        }
        else {
            xml =
                getRestHandlerClient().retrieveMembers(container.getObjid(),
                    filter);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(ExplainResponse.class)
            .unmarshalDocument(xml);
    }

    @Override
    public VersionHistory retrieveVersionHistory(final Container resource)
        throws EscidocClientException, InternalClientException,
        TransportException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Result lock(final Container obj, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Result unlock(final Container obj, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Result assignVersionPid(
        final Container resource, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Result assignObjectPid(
        final Container resource, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public StructMap retrieveStructMap(final Container container)
        throws EscidocException, InternalClientException, TransportException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Container createContainer(
        final Container container, final Container subContainer)
        throws EscidocException, InternalClientException, TransportException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Item createItem(final Container container, final Item item)
        throws EscidocException, InternalClientException, TransportException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Container addContentRelations(
        final Container container, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Container removeContentRelations(
        final Container container, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Result addMembers(
        final Container container, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Result removeMembers(
        final Container container, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Relations retrieveRelations(final Container container)
        throws EscidocClientException, InternalClientException,
        TransportException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SearchRetrieveResponse retrieveMembers(
        final String id, final SearchRetrieveRequestType filter)
        throws EscidocException, InternalClientException, TransportException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ExplainResponse retrieveMembers(
        final String id, final ExplainRequestType filter)
        throws EscidocException, InternalClientException, TransportException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected SoapContainerHandlerClient getSoapHandlerClientInstance()
        throws InternalClientException {
        return new SoapContainerHandlerClient(getServiceAddress());
    }

    @Override
    protected RestContainerHandlerClient getRestHandlerClientInstance()
        throws InternalClientException {
        return new RestContainerHandlerClient(getServiceAddress());
    }
}