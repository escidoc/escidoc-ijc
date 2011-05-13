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
package de.escidoc.core.client.soap;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.net.URL;
import java.util.HashMap;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.om.ContainerHandler;
import de.escidoc.core.om.ContainerHandlerServiceLocator;

/**
 * SOAP Handler for Container.
 * 
 * @author SWA
 * 
 */
public class SoapContainerHandlerClient extends SoapClientBase {

    private static final Logger LOG = Logger.getLogger(SoapContainerHandlerClient.class);

    private ContainerHandler soapClient = null;

    /**
     * 
     * @throws InternalClientException
     */
    public SoapContainerHandlerClient() throws InternalClientException {
        super();
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public SoapContainerHandlerClient(final URL serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     * @deprecated Use
     *             {@link SoapContainerHandlerClient#SoapContainerHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public SoapContainerHandlerClient(final String serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param container
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContainerHandlerInterface#create(java.lang.String)
     */
    public String create(final String container) throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().create(container);
        }
        catch (Exception e) {
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
        catch (Exception e) {
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
        catch (Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @param container
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContainerHandlerInterface#update(java.lang.String,
     *      java.lang.String)
     */
    public String update(final String id, final String container) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().update(id, container);
        }
        catch (Exception e) {
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
        catch (Exception e) {
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
        catch (Exception e) {
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
        catch (Exception e) {
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
        catch (Exception e) {
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
        catch (Exception e) {
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
        catch (Exception e) {
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
        catch (Exception e) {
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
        catch (Exception e) {
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
        catch (Exception e) {
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
        catch (Exception e) {
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
     */
    public String addContentRelations(final String id, final String taskParam) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().addContentRelations(id, taskParam);
        }
        catch (Exception e) {
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
     */
    public String removeContentRelations(final String id, final String taskParam) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().removeContentRelations(id, taskParam);
        }
        catch (Exception e) {
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
     */
    public String addMembers(final String id, final String taskParam) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().addMembers(id, taskParam);
        }
        catch (Exception e) {
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
     */
    public String removeMembers(final String id, final String taskParam) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().removeMembers(id, taskParam);
        }
        catch (Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @param itemXml
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String createContainer(final String id, final String itemXml) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().createContainer(id, itemXml);
        }
        catch (Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * 
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
        catch (Exception e) {
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
    public String retrieveContainers(final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        evalRequest(request, true);
        return retrieveContainers(getEscidoc12Filter(request));
    }

    /**
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveContainers(final ExplainRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        evalRequest(request);
        return retrieveContainers(getEscidoc12Filter(request));
    }

    /**
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveContainers(final HashMap<String, String[]> filter) throws EscidocException,
        InternalClientException, TransportException {

        if (filter == null)
            throw new IllegalArgumentException("filter must not be null.");

        String result = null;
        try {
            result = getClient().retrieveContainers(filter);
        }
        catch (Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * Retrieve Container Relations via SOAP.
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
        catch (Exception e) {
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

        return retrieveMembers(contextId, getEscidoc12Filter(filter));
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

        return retrieveMembers(contextId, getEscidoc12Filter(filter));
    }

    /**
     * 
     * @param contextId
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveMembers(final String contextId, final HashMap<String, String[]> filter)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveMembers(contextId, filter);
        }
        catch (Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * See Interface for functional description.
     * 
     * @return Returns the soapClient.
     * @throws InternalClientException
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    @Override
    public ContainerHandler getClient() throws InternalClientException {

        try {
            if (soapClient == null) {
                ContainerHandlerServiceLocator serviceLocator = new ContainerHandlerServiceLocator(getEngineConfig());
                URL url = getHandlerServiceURL(serviceLocator.getContainerHandlerServiceAddress());
                soapClient = serviceLocator.getContainerHandlerService(url);
                registerPWCallback(soapClient);
            }
        }
        catch (ServiceException e) {
            throw new InternalClientException(e.getMessage(), e);
        }
        return soapClient;
    }
}