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

import org.joda.time.DateTime;

import de.escidoc.core.aa.RoleHandler;
import de.escidoc.core.aa.RoleHandlerServiceLocator;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.common.jibx.Factory;

/**
 * SOAP Handler for Roles.
 * 
 * @author SWA
 * 
 */
public class SoapRoleHandlerClient extends SoapClientBase {

    private RoleHandler soapClient = null;

    /**
     * 
     * @throws InternalClientException
     */
    public SoapRoleHandlerClient() throws InternalClientException {
        super();
    }

    /**
     * 
     * @throws InternalClientException
     */
    public SoapRoleHandlerClient(final String serviceAddress)
        throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param context
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContextHandlerInterface#create(java.lang.String)
     */
    public String create(final String context) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().create(context);
        }
        catch (Exception e) {
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
     * @see de.escidoc.core.om.service.interfaces.ContextHandlerInterface#delete(java.lang.String)
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
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContextHandlerInterface#retrieve(java.lang.String)
     */
    @Deprecated
    public String retrieveRoles(final String taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveRoles(taskParam);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
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
    public String retrieveRoles(final SearchRetrieveRequestType filter)
        throws EscidocException, InternalClientException, TransportException {

        evalRequest(filter, true);
        return filterRoles(getEscidoc12Filter(filter));
    }

    /**
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveRoles(final ExplainRequestType filter)
        throws EscidocException, InternalClientException, TransportException {

        evalRequest(filter);
        return filterRoles(getEscidoc12Filter(filter));
    }

    /**
     * 
     * @param id
     * @param context
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContextHandlerInterface#update(java.lang.String,
     *      java.lang.String)
     */
    public String update(final String id, final String context)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().update(id, context);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * Get the last-modification timestamp of the context.
     * 
     * @param id
     *            The id of the context.
     * @return The timestamp of the last modification of the context.
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.ClientBase#getLastModificationDate(java.lang.String)
     */
    @Override
    @Deprecated
    public DateTime getLastModificationDate(final String id)
        throws EscidocException, InternalClientException, TransportException {

        DateTime result = null;
        try {
            result =
                (Factory
                    .getMarshallerFactory(TransportProtocol.SOAP)
                    .getContextMarshaller().unmarshalDocument(getClient()
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
    public RoleHandler getClient() throws InternalClientException {

        try {
            if (soapClient == null) {
                RoleHandlerServiceLocator serviceLocator =
                    new RoleHandlerServiceLocator(getEngineConfig());
                URL url =
                    getHandlerServiceURL(serviceLocator
                        .getRoleHandlerServiceAddress());
                soapClient = serviceLocator.getRoleHandlerService(url);
                registerPWCallback(soapClient);
            }
        }
        catch (ServiceException e) {
            throw new InternalClientException(e.getMessage(), e);
        }
        return soapClient;
    }

    /**
     * generic filter method request.
     * 
     * @param escidoc12Filter
     *            data structure for eSciDoc 1.2 filter
     * @return filter response
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    private String filterRoles(final HashMap<String, String[]> escidoc12Filter)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveRoles(escidoc12Filter);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

}
