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
package de.escidoc.core.client.rest;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.net.URL;
import java.util.HashMap;

import org.apache.log4j.Logger;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.ContainerHandler;
import de.escidoc.core.client.rest.serviceLocator.ContainerRestServiceLocator;

/**
 * REST Handler for Item.
 * 
 * @author SWA
 * 
 */
public class RestContainerHandlerClient extends RestClientBase {

    private static final Logger LOG = Logger.getLogger(RestContainerHandlerClient.class);

    private ContainerHandler restClient;

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public RestContainerHandlerClient(final URL serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     * @deprecated Use
     *             {@link RestContainerHandlerClient#RestContainerHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public RestContainerHandlerClient(final String serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * Add a Content Relation to the Container.
     * 
     * @param id
     *            Objid of Container
     * @param taskParam
     *            taskParameter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String addContentRelations(final String id, final String taskParam) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().addContentRelations(id, taskParam);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * Create Container.
     * 
     * @param containerXml
     *            XML representation of the to create Container
     * @return XML representation of the created Container
     * 
     * @throws EscidocException
     *             Thrown if the eSciDoc framework throws an exception
     * @throws InternalClientException
     *             Thrown if an exception happens on client layer
     * @throws TransportException
     *             Thrown if a failure on transport level occurs
     * @see de.escidoc.core.om.service.interfaces.ContainerHandlerInterface#create(java.lang.String)
     */
    public String create(final String containerXml) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().create(containerXml);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContainerHandlerInterface#delete(java.lang.String)
     */
    public void delete(final String id) throws EscidocException, InternalClientException, TransportException {

        try {
            getClient().delete(id);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
    }

    /**
     * 
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContainerHandlerInterface#retrieve(java.lang.String)
     */
    public String retrieve(final String id) throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieve(id);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @param item
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContainerHandlerInterface#update(java.lang.String,
     *      java.lang.String)
     */
    public String update(final String id, final String item) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().update(id, item);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContainerHandlerInterface#release(java.lang.String,
     *      java.lang.String)
     */
    public String release(final String id, final String taskParam) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().release(id, taskParam);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContainerHandlerInterface#revise(java.lang.String,java.lang.String)
     */
    public String revise(final String id, final String taskParam) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().revise(id, taskParam);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContainerHandlerInterface#submit(java.lang.String,
     *      java.lang.String)
     */
    public String submit(final String id, final String taskParam) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().submit(id, taskParam);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContainerHandlerInterface#submit(java.lang.String,
     *      java.lang.String)
     */
    public String withdraw(final String id, final String taskParam) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().withdraw(id, taskParam);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContainerHandlerInterface#assignVersionPid(java.lang.String,
     *      java.lang.String)
     */
    public String assignVersionPid(final String id, final String taskParam) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().assignVersionPid(id, taskParam);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContainerHandlerInterface#assignObjectPid(java.lang.String,
     *      java.lang.String)
     */
    public String assignObjectPid(final String id, final String taskParam) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().assignObjectPid(id, taskParam);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContainerHandlerInterface#retrieveVersionHistory(java.lang.String)
     */
    public String retrieveVersionHistory(final String id) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().retrieveVersionHistory(id);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContainerHandlerInterface#lock(java.lang.String,
     *      java.lang.String)
     */
    public String lock(final String id, final String taskParam) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().lock(id, taskParam);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContainerHandlerInterface#unlock(java.lang.String,
     *      java.lang.String)
     */
    public String unlock(final String id, final String taskParam) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().unlock(id, taskParam);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @param id
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String removeContentRelations(final String id, final String taskParam) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().removeContentRelations(id, taskParam);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @param id
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String addMembers(final String id, final String taskParam) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().addMembers(id, taskParam);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @param id
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String removeMembers(final String id, final String taskParam) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().removeMembers(id, taskParam);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @param id
     * @param itemXml
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String createItem(final String id, final String itemXml) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().createItem(id, itemXml);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * Create Container.
     * 
     * @param id
     *            objid of Container
     * @param containerXml
     *            XML of to create Container (as member of the Container)
     * @return The created Container
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String createContainer(final String id, final String containerXml) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().createContainer(id, containerXml);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveStructMap(final String id) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().retrieveStructMap(id);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveMembers(final String contextId, final SearchRetrieveRequestType filter)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveMembers(contextId, filter);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveMembers(final String contextId, final ExplainRequestType filter) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveMembers(contextId, filter);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveContainers(final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException {

        evalRequest(filter, true);

        String result = null;
        try {
            result = getClient().retrieveContainers(filter);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @Deprecated
    public String retrieveContainers(final HashMap<String, String[]> filter) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveContainers(filter);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveContainers(final ExplainRequestType filter) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().retrieveContainers(filter);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * Retrieve Container Relations via REST.
     * 
     * @param id
     *            Objid of Container.
     * @return XML representation of Relation.
     * 
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public String retrieveRelations(final String id) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().retrieveRelations(id);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @param containerId
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveParents(final String containerId) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().retrieveParents(containerId);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @return Returns the restClient.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    @Override
    public ContainerHandler getClient() throws InternalClientException {

        if (this.restClient == null) {

            final ContainerRestServiceLocator serviceLocator = new ContainerRestServiceLocator();
            serviceLocator.registerRestCallbackHandler(this);
            serviceLocator.setServiceAddress(getServiceAddress());
            this.restClient = serviceLocator;
        }
        return this.restClient;
    }
}
