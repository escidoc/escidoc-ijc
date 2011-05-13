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

import static de.escidoc.core.common.Precondition.checkNotNull;
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
import de.escidoc.core.om.ContextHandler;
import de.escidoc.core.om.ContextHandlerServiceLocator;

/**
 * Context SOAP handler.
 * 
 * @author SWA
 * 
 */
public class SoapContextHandlerClient extends SoapClientBase {

    private static final Logger LOG = Logger.getLogger(SoapContextHandlerClient.class);

    private ContextHandler soapClient = null;

    /**
     * 
     * @throws InternalClientException
     */
    public SoapContextHandlerClient() throws InternalClientException {
        super();
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public SoapContextHandlerClient(final URL serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     * @deprecated Use
     *             {@link SoapContextHandlerClient#SoapContextHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public SoapContextHandlerClient(final String serviceAddress) throws InternalClientException {
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
    public String create(final String context) throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().create(context);
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
     * @see de.escidoc.core.om.service.interfaces.ContextHandlerInterface#delete(java.lang.String)
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
     * @see de.escidoc.core.om.service.interfaces.ContextHandlerInterface#retrieve(java.lang.String)
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
     * @param context
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContextHandlerInterface#update(java.lang.String,
     *      java.lang.String)
     */
    public String update(final String id, final String context) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().update(id, context);
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
     * @see de.escidoc.core.om.service.interfaces.ContextHandlerInterface#lock(java.lang.String)
     */
    public String open(final String id, final String taskParam) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().open(id, taskParam);
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
     * @see de.escidoc.core.om.service.interfaces.ContextHandlerInterface#unlock(java.lang.String)
     */
    public String close(final String id, final String taskParam) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().close(id, taskParam);
        }
        catch (Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @param name
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveAdminDescriptor(final String id, final String name) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveAdminDescriptor(id, name);
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
    public String retrieveAdminDescriptors(final String id) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().retrieveAdminDescriptors(id);
        }
        catch (Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * 
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveContexts(final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        evalRequest(request, true);

        return retrieveContexts(getEscidoc12Filter(request));
    }

    /**
     * 
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveContexts(final ExplainRequestType request) throws EscidocException, InternalClientException,
        TransportException {

        evalRequest(request);

        return retrieveContexts(getEscidoc12Filter(request));
    }

    /**
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveContexts(final HashMap<String, String[]> filter) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveContexts(filter);
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
    public String retrieveMembers(final String id, final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        evalRequest(request, true);

        return retrieveMembers(id, getEscidoc12Filter(request));
    }

    /**
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveMembers(final String id, final HashMap<String, String[]> filter) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(id);

        String result = null;
        try {
            result = getClient().retrieveMembers(id, filter);
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
    public String retrieveMembers(final String id, final ExplainRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        evalRequest(request);

        return retrieveMembers(id, getEscidoc12Filter(request));
    }

    /**
     * @return Returns the soapClient.
     * @throws InternalClientException
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    @Override
    public ContextHandler getClient() throws InternalClientException {

        if (soapClient == null) {
            ContextHandlerServiceLocator serviceLocator = new ContextHandlerServiceLocator(getEngineConfig());
            URL url = getHandlerServiceURL(serviceLocator.getContextHandlerServiceAddress());
            try {
                soapClient = serviceLocator.getContextHandlerService(url);
            }
            catch (ServiceException e) {
                throw new InternalClientException(e.getMessage(), e);
            }
            registerPWCallback(soapClient);
        }

        return soapClient;
    }
}