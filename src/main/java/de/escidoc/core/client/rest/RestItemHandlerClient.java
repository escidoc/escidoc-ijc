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
import java.util.Map;

import org.apache.log4j.Logger;

import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.ItemHandler;
import de.escidoc.core.client.rest.serviceLocator.ItemRestServiceLocator;
import de.escidoc.core.resources.HttpInputStream;

/**
 * REST Handler for Item.
 * 
 * @author SWA
 * 
 */
public class RestItemHandlerClient extends RestClientBase {

    private static final Logger LOG = Logger.getLogger(RestItemHandlerClient.class.getName());

    private ItemHandler restClient = null;

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public RestItemHandlerClient(final URL serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     * @deprecated Use {@link RestItemHandlerClient#RestItemHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public RestItemHandlerClient(final String serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * Create an Item.
     * 
     * @param item
     *            XML representation of to create Item.
     * @return eSciDoc XML representation of just created Item.
     * @throws EscidocException
     *             Thrown in case of eSciDoc framework failures.
     * @throws InternalClientException
     *             Thrown in case of client internal failures.
     * @throws TransportException
     *             Thrown in case of failures on transport level.
     */
    public String create(final String item) throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().create(item);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @param itemId
     * @param componentXml
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String createComponent(final String itemId, final String componentXml) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().createComponent(itemId, componentXml);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * Delete an Item.
     * 
     * @param id
     *            Objid of the to delete Item.
     * @throws EscidocException
     *             Thrown in case of eSciDoc framework failures.
     * @throws InternalClientException
     *             Thrown in case of client internal failures.
     * @throws TransportException
     *             Thrown in case of failures on transport level.
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
     * @param itemId
     * @param componentId
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void deleteComponent(final String itemId, final String componentId) throws EscidocException,
        InternalClientException, TransportException {

        try {
            getClient().deleteComponent(itemId, componentId);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
    }

    /**
     * Retrieve an Item from repository.
     * 
     * @param id
     *            objid of the Item which is to retrieve.
     * @return XML representation if the Item.
     * @throws EscidocException
     *             Thrown in case of eSciDoc framework failures.
     * @throws InternalClientException
     *             Thrown in case of client internal failures.
     * @throws TransportException
     *             Thrown in case of failures on transport level.
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
     * Update an Item.
     * 
     * @param id
     *            Objid of the Item.
     * @param item
     *            XML representation of the Item containing the updated values.
     * @return XML representation of the updated Item.
     * @throws EscidocException
     *             Thrown in case of eSciDoc framework failures.
     * @throws InternalClientException
     *             Thrown in case of client internal failures.
     * @throws TransportException
     *             Thrown in case of failures on transport level.
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
     * @param itemId
     * @param componentId
     * @param componentXml
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String updateComponent(final String itemId, final String componentId, final String componentXml)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().updateComponent(itemId, componentId, componentXml);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * Release an Item.
     * 
     * @param id
     *            Objid of the Item.
     * @param taskParam
     *            Task parameter to release the Item.
     * @return XML representation of the Item
     * @throws EscidocException
     *             Thrown in case of eSciDoc framework failures.
     * @throws InternalClientException
     *             Thrown in case of client internal failures.
     * @throws TransportException
     *             Thrown in case of failures on transport level.
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
     * Revise an Item.
     * 
     * @param id
     *            Objid of the Item.
     * @param taskParam
     *            Task parameter to revise the Item.
     * @return XML representation of the Item
     * @throws EscidocException
     *             Thrown in case of eSciDoc framework failures.
     * @throws InternalClientException
     *             Thrown in case of client internal failures.
     * @throws TransportException
     *             Thrown in case of failures on transport level.
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
     * @return XML representation of the Item
     * @throws EscidocException
     *             Thrown in case of eSciDoc framework failures.
     * @throws InternalClientException
     *             Thrown in case of client internal failures.
     * @throws TransportException
     *             Thrown in case of failures on transport level.
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
     * @return XML representation of the Item
     * @throws EscidocException
     *             Thrown in case of eSciDoc framework failures.
     * @throws InternalClientException
     *             Thrown in case of client internal failures.
     * @throws TransportException
     *             Thrown in case of failures on transport level.
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
     * @return XML representation of the Item
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ItemHandlerInterface#assignVersionPid(java.lang.String,
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
     * @return XML representation of the Item
     * @throws EscidocException
     *             Thrown in case of eSciDoc framework failures.
     * @throws InternalClientException
     *             Thrown in case of client internal failures.
     * @throws TransportException
     *             Thrown in case of failures on transport level.
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
     * @param componentId
     * @param taskParam
     * @return XML representation of the Item
     * @throws EscidocException
     *             Thrown in case of eSciDoc framework failures.
     * @throws InternalClientException
     *             Thrown in case of client internal failures.
     * @throws TransportException
     *             Thrown in case of failures on transport level.
     */
    public String assignContentPid(final String id, final String componentId, final String taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().assignContentPid(id, componentId, taskParam);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * 
     * @param taskParam
     * @return
     * @throws EscidocException
     *             Thrown in case of eSciDoc framework failures.
     * @throws InternalClientException
     *             Thrown in case of client internal failures.
     * @throws TransportException
     *             Thrown in case of failures on transport level.
     */
    @Deprecated
    public String retrieveItems(final HashMap<String, String[]> taskParam) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveItems(taskParam);
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
    public String retrieveItems(final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException {

        evalRequest(filter, true);

        String result = null;
        try {
            result = getClient().retrieveItems(filter);
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
    public String retrieveItems(final ExplainRequestType filter) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().retrieveItems(filter);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveComponents(final String id) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().retrieveComponents(id);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @param id
     * @param componentId
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveComponent(final String id, final String componentId) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveComponent(id, componentId);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @param id
     * @param componentId
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveComponentProperties(final String id, final String componentId) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveComponentProperties(id, componentId);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @param id
     * @param componentId
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveComponentMdRecords(final String id, final String componentId) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveComponentMdRecords(id, componentId);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @param id
     * @param componentId
     * @param mdRecordId
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveComponentMdRecord(final String id, final String componentId, final String mdRecordId)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveComponentMdRecord(id, componentId, mdRecordId);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @param itemId
     * @param componentId
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public HttpInputStream retrieveContent(final String itemId, final String componentId) throws EscidocException,
        InternalClientException, TransportException {

        HttpInputStream result = null;
        try {
            result = getClient().retrieveContent(itemId, componentId);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @param itemId
     * @param componentId
     * @param transformer
     * @param transParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public HttpInputStream retrieveContent(
        final String itemId, final String componentId, final String transformer, final Map<String, String[]> transParams)
        throws EscidocException, InternalClientException, TransportException {

        HttpInputStream result = null;
        try {
            result = getClient().retrieveContent(itemId, componentId, transformer, transParams);
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
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
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
     * 
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ItemHandlerInterface#retrieveVersionHistory(java.lang.String)
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
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveParents(final String id) throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveParents(id);
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
    public String moveToContext(final String id, final String taskParam) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().moveToContext(id, taskParam);
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
     * @see de.escidoc.core.om.service.interfaces.ItemHandlerInterface#lock(java.lang.String,
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
     * @see de.escidoc.core.om.service.interfaces.ItemHandlerInterface#unlock(java.lang.String,
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
     * 
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveProperties(final String id) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().retrieveProperties(id);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveMdRecords(final String id) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().retrieveMdRecords(id);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @param id
     * @param mdRecordId
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveMdRecord(final String id, final String mdRecordId) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveMdRecord(id, mdRecordId);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveContentStreams(final String id) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().retrieveContentStreams(id);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @param id
     * @param name
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveContentStream(final String id, final String name) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveContentStream(id, name);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @param id
     * @param name
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public HttpInputStream retrieveContentStreamContent(final String id, final String name) throws EscidocException,
        InternalClientException, TransportException {

        HttpInputStream stream = null;
        try {
            stream = getClient().retrieveContentStreamContent(id, name);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return stream;
    }

    /**
     * @return Returns the restClient.
     * @throws InternalClientException
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    @Override
    public ItemHandler getClient() throws InternalClientException {

        if (restClient == null) {

            final ItemRestServiceLocator serviceLocator = new ItemRestServiceLocator();
            serviceLocator.registerRestCallbackHandler(this);
            serviceLocator.setServiceAddress(getServiceAddress());
            restClient = serviceLocator;
        }
        return this.restClient;
    }
}