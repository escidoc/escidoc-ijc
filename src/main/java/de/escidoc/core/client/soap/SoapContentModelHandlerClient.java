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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.cmm.ContentModelHandler;
import de.escidoc.core.cmm.ContentModelHandlerServiceLocator;

/**
 * 
 * @author SWA
 * 
 */
public class SoapContentModelHandlerClient extends SoapClientBase {

    private static final Logger LOG = LoggerFactory.getLogger(SoapContentModelHandlerClient.class);

    private ContentModelHandler soapClient = null;

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public SoapContentModelHandlerClient(final URL serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     * @deprecated Use
     *             {@link SoapContentModelHandlerClient#SoapContentModelHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public SoapContentModelHandlerClient(final String serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param contentModel
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContentModelHandlerInterface#create(java.lang.String)
     */
    public String create(final String contentModel) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().create(contentModel);
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
     * @see de.escidoc.core.om.service.interfaces.ContentModelHandlerInterface#delete(java.lang.String)
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
     * @see de.escidoc.core.om.service.interfaces.ContentModelHandlerInterface#retrieve(java.lang.String)
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
     * @param contentModel
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContentModelHandlerInterface#update(java.lang.String,
     *      java.lang.String)
     */
    public String update(final String id, final String contentModel) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().update(id, contentModel);
        }
        catch (final Exception e) {
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
    public String retrieveContentModels(final ExplainRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        evalRequest(request);

        return retrieveContentModels(getEscidoc12Filter(request));
    }

    /**
     * 
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveContentModels(final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        evalRequest(request, true);

        return retrieveContentModels(getEscidoc12Filter(request));
    }

    /**
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveContentModels(final HashMap<String, String[]> filter) throws EscidocException,
        InternalClientException, TransportException {
        String result = null;
        try {
            result = getClient().retrieveContentModels(filter);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @return Returns the soapClient.
     * @throws InternalClientException
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    @Override
    public ContentModelHandler getClient() throws InternalClientException {

        try {
            if (soapClient == null) {
                final ContentModelHandlerServiceLocator serviceLocator =
                    new ContentModelHandlerServiceLocator(getEngineConfig());
                final URL url = getHandlerServiceURL(serviceLocator.getContentModelHandlerServiceAddress());
                soapClient = serviceLocator.getContentModelHandlerService(url);
                registerPWCallback(soapClient);
            }
        }
        catch (final ServiceException e) {
            throw new InternalClientException(e.getMessage(), e);
        }
        return soapClient;
    }
}
