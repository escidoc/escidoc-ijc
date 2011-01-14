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
import java.rmi.RemoteException;
import java.util.HashMap;

import org.apache.log4j.Logger;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.ContentModelHandler;
import de.escidoc.core.client.rest.serviceLocator.ContentModelRestServiceLocator;
import de.escidoc.core.common.exceptions.remote.system.SystemException;

/**
 * REST Handler for Content Model.
 * 
 * @author SWA
 * 
 */
public class RestContentModelHandlerClient extends RestClientBase {

	private static final Logger log = Logger
			.getLogger(RestContentModelHandlerClient.class);

    private ContentModelHandler restClient;

    /**
     * 
     * @throws InternalClientException
     */
    public RestContentModelHandlerClient() throws InternalClientException {
        super();
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public RestContentModelHandlerClient(final URL serviceAddress)
        throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     * @deprecated Use
     *             {@link RestContentModelHandlerClient#RestContentModelHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public RestContentModelHandlerClient(final String serviceAddress)
        throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param contentModel
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContextHandlerInterface#create(java.lang.String)
     */
    public String create(final String contentModel) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().create(contentModel);
        }
        catch (Exception e) {
            ExceptionMapper.map(e, log);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContextHandlerInterface#delete(java.lang.String)
     */
    public void delete(final String id) throws EscidocException,
        InternalClientException, TransportException {

        try {
            getClient().delete(id);
        }
        catch (Exception e) {
            ExceptionMapper.map(e, log);
        }
    }

    /**
     * 
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContextHandlerInterface#retrieve(java.lang.String)
     */
    public String retrieve(final String id) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieve(id);
        }
        catch (Exception e) {
            ExceptionMapper.map(e, log);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @param contentModel
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContextHandlerInterface#update(java.lang.String,
     *      java.lang.String)
     */
    public String update(final String id, final String contentModel)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().update(id, contentModel);
        }
        catch (Exception e) {
            ExceptionMapper.map(e, log);
        }
        return result;
    }

    /**
     * 
     * @param parameterMap
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveContentModels(final HashMap<String, String[]> filter)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveContentModels(filter);
        }
        catch (Exception e) {
            ExceptionMapper.map(e, log);
        }
        return result;
    }

    /**
     * 
     * @param request
     * @return
     * @throws SystemException
     * @throws RemoteException
     */
    public String retrieveContentModels(final SearchRetrieveRequestType request)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveContentModels(request);
        }
        catch (Exception e) {
            ExceptionMapper.map(e, log);
        }
        return result;
    }

    /**
     * 
     * @param request
     * @return
     * @throws SystemException
     * @throws RemoteException
     */
    public String retrieveContentModels(final ExplainRequestType request)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveContentModels(request);
        }
        catch (Exception e) {
            ExceptionMapper.map(e, log);
        }
        return result;
    }

    /**
     * @return Returns the restClient.
     * @throws InternalClientException
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    @Override
    public ContentModelHandler getClient() throws InternalClientException {

        if (restClient == null) {

            ContentModelRestServiceLocator serviceLocator =
                new ContentModelRestServiceLocator();
            serviceLocator.registerRestCallbackHandler(this);
            serviceLocator.setServiceAddress(getServiceAddress());
            restClient = serviceLocator;
        }
        return this.restClient;
    }
}
