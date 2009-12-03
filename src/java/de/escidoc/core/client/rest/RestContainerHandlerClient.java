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

import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import de.escidoc.core.client.ClientBase;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.rest.serviceLocator.ContainerRestServiceLocator;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.om.ContainerHandler;

/**
 * REST Handler for Item.
 * 
 * @author SWA
 * 
 */
public class RestContainerHandlerClient extends ClientBase {

    private final Logger logger =
        Logger.getLogger(RestContainerHandlerClient.class.getName());

    private ContainerHandler restClient = null;

    public RestContainerHandlerClient() throws InternalClientException {

        super();
    }

    public String addContentRelations(final String id, final String taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().addContentRelations(id, taskParam);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * 
     * @param item
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ItemHandlerInterface#create(java.lang.String)
     */
    public String create(final String item) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().create(item);
        }
        catch (Exception e) {
            logger.debug(e);
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ItemHandlerInterface#delete(java.lang.String)
     */
    public void delete(final String id) throws EscidocException,
        InternalClientException, TransportException {

        try {
            getClient().delete(id);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
    }

    /**
     * 
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ItemHandlerInterface#retrieve(java.lang.String)
     */
    public String retrieve(final String id) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieve(id);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
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
     * @see de.escidoc.core.om.service.interfaces.ItemHandlerInterface#update(java.lang.String,
     *      java.lang.String)
     */
    public String update(final String id, final String item)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().update(id, item);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
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
     * @see de.escidoc.core.om.service.interfaces.ItemHandlerInterface#release(java.lang.String,
     *      java.lang.String)
     */
    public String release(final String id, final String taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().release(id, taskParam);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
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
     * @see de.escidoc.core.om.service.interfaces.ItemHandlerInterface#revise(java.lang.String,java.lang.String)
     */
    public String revise(final String id, final String taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().revise(id, taskParam);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
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
     * @see de.escidoc.core.om.service.interfaces.ItemHandlerInterface#submit(java.lang.String,
     *      java.lang.String)
     */
    public String submit(final String id, final String taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().submit(id, taskParam);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
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
     * @see de.escidoc.core.om.service.interfaces.ItemHandlerInterface#submit(java.lang.String,
     *      java.lang.String)
     */
    public String withdraw(final String id, final String taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().withdraw(id, taskParam);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
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
     * @see de.escidoc.core.om.service.interfaces.ItemHandlerInterface#assignVersionPid(java.lang.String,
     *      java.lang.String)
     */
    public String assignVersionPid(final String id, final String taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().assignVersionPid(id, taskParam);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
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
     * @see de.escidoc.core.om.service.interfaces.ItemHandlerInterface#assignObjectPid(java.lang.String,
     *      java.lang.String)
     */
    public String assignObjectPid(final String id, final String taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().assignObjectPid(id, taskParam);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
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
     * @see de.escidoc.core.om.service.interfaces.ItemHandlerInterface#retrieveVersionHistory(java.lang.String)
     */
    public String retrieveVersionHistory(final String id)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveVersionHistory(id);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
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
     * @see de.escidoc.core.om.service.interfaces.ItemHandlerInterface#lock(java.lang.String,
     *      java.lang.String)
     */
    public String lock(final String id, final String taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().lock(id, taskParam);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
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
     * @see de.escidoc.core.om.service.interfaces.ItemHandlerInterface#unlock(java.lang.String,
     *      java.lang.String)
     */
    public String unlock(final String id, final String taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().unlock(id, taskParam);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    public String removeContentRelations(final String id, final String taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().removeContentRelations(id, taskParam);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    public String addMembers(final String id, final String taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().addMembers(id, taskParam);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    public String removeMembers(final String id, final String taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().removeMembers(id, taskParam);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    public String createItem(final String id, final String itemXml)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().createItem(id, itemXml);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
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
    public String retrieveStructMap(final String id) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveStructMap(id);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveMembers(final String id, final String filter)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveMembers(id, filter);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * 
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ItemHandlerInterface#retrieveItems(java.lang.String)
     */
    public String retrieveContainers(final String taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveContainers(taskParam);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
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
    public String retrieveRelations(final String id)
        throws EscidocException, InternalClientException,
        TransportException {
        
        String result = null;
        try {
            result = getClient().retrieveRelations(id);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * Get the last-modification timestamp of the item.
     * 
     * @param id
     *            The id of the item.
     * @return The timestamp of the last modification of the item.
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.ClientBase#getLastModificationDate(java.lang.String)
     */
    @Override
    public DateTime getLastModificationDate(final String id)
        throws EscidocException, InternalClientException, TransportException {

        DateTime result = null;
        try {
            result =
                (Factory.getItemMarshaller().unmarshalDocument(getClient()
                    .retrieve(id))).getLastModificationDate();
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * @return Returns the soapClient.
     * @throws InternalClientException
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    @Override
    public ContainerHandler getClient() throws InternalClientException {

        if (this.restClient == null) {

            ContainerRestServiceLocator serviceLocator =
                new ContainerRestServiceLocator();

            try {
                serviceLocator.setServiceAddress(getServiceAddress());
            }
            catch (MalformedURLException e) {
                throw new InternalClientException(e);
            }
            this.restClient = serviceLocator;
        }
        return this.restClient;
    }
}
