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
import de.escidoc.core.om.ContentRelationHandler;
import de.escidoc.core.om.ContentRelationHandlerServiceLocator;

/**
 * Content Relation SOAP handler.
 * 
 * @author SWA
 * 
 */
public class SoapContentRelationHandlerClient extends SoapClientBase {

    private static final Logger LOG = LoggerFactory.getLogger(SoapContentRelationHandlerClient.class);

    private ContentRelationHandler soapClient = null;

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public SoapContentRelationHandlerClient(final URL serviceAddress) throws InternalClientException {
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
    public SoapContentRelationHandlerClient(final String serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param id
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContentRelationHandlerInterface#assignObjectPid(java.lang.String,
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
     * @param contentRelation
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContentRelationHandlerInterface#create(java.lang.String)
     */
    public String create(final String contentRelation) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().create(contentRelation);
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
     * @see de.escidoc.core.om.service.interfaces.ContentRelationHandlerInterface#delete(java.lang.String)
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
     * @see de.escidoc.core.om.service.interfaces.ContentRelationHandlerInterface#retrieve(java.lang.String)
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
     * @param context
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContentRelationHandlerInterface#update(java.lang.String,
     *      java.lang.String)
     */
    public String update(final String id, final String context) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().update(id, context);
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
     * @see de.escidoc.core.om.service.interfaces.ContentRelationHandlerInterface#lock(java.lang.String,
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
     * @see de.escidoc.core.om.service.interfaces.ContentRelationHandlerInterface#unlock(java.lang.String,
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
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContentRelationHandlerInterface#release(java.lang.String,
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
     * @see de.escidoc.core.om.service.interfaces.ContentRelationHandlerInterface#revise(java.lang.String,java.lang.String)
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
     * @see de.escidoc.core.om.service.interfaces.ContentRelationHandlerInterface#submit(java.lang.String,
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
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveContentRelations(final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        evalRequest(request, true);

        return retrieveContentRelations(getEscidoc12Filter(request));
    }

    /**
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveContentRelations(final ExplainRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        evalRequest(request);

        return retrieveContentRelations(getEscidoc12Filter(request));
    }

    /**
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveContentRelations(final HashMap<String, String[]> filter) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveContentRelations(filter);
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
    public ContentRelationHandler getClient() throws InternalClientException {

        try {
            if (soapClient == null) {
                final ContentRelationHandlerServiceLocator serviceLocator =
                    new ContentRelationHandlerServiceLocator(getEngineConfig());
                final URL url = getHandlerServiceURL(serviceLocator.getContentRelationHandlerServiceAddress());
                soapClient = serviceLocator.getContentRelationHandlerService(url);
                registerPWCallback(soapClient);
            }
        }
        catch (final ServiceException e) {
            throw new InternalClientException(e.getMessage(), e);
        }
        return soapClient;
    }
}
