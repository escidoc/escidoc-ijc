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

import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import de.escidoc.core.client.ClientBase;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.rest.serviceLocator.ContentRelationRestServiceLocator;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.client.interfaces.ContentRelationHandler;

/**
 * REST Handler for ContentRelation.
 * 
 * @author SWA
 * 
 */
public class RestContentRelationHandlerClient extends ClientBase {

    private final Logger logger =
        Logger.getLogger(RestContentRelationHandlerClient.class.getName());

    private ContentRelationHandler restClient = null;

    public RestContentRelationHandlerClient() throws InternalClientException {

        super();
    }

    /**
     * 
     * @param id
     * @param taskParam
     * @return XML representation of the ContentRelation
     * @throws EscidocException
     *             Thrown in case of eSciDoc framework failures.
     * @throws InternalClientException
     *             Thrown in case of client internal failures.
     * @throws TransportException
     *             Thrown in case of failures on transport level.
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
     * @param contentRelation
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContentRelationHandlerInterface#create(java.lang.String)
     */
    public String create(final String contentRelation) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().create(contentRelation);
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
     * @see de.escidoc.core.om.service.interfaces.ContentRelationHandlerInterface#delete(java.lang.String)
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
     * @see de.escidoc.core.om.service.interfaces.ContentRelationHandlerInterface#retrieve(java.lang.String)
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
     * @param contentRelation
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContentRelationHandlerInterface#update(java.lang.String,
     *      java.lang.String)
     */
    public String update(final String id, final String contentRelation)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().update(id, contentRelation);
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
     * @see de.escidoc.core.om.service.interfaces.ContentRelationHandlerInterface#lock(java.lang.String,
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
     * @see de.escidoc.core.om.service.interfaces.ContentRelationHandlerInterface#unlock(java.lang.String,
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

    /**
     * Release an ContentRelation.
     * 
     * @param id
     *            Objid of the ContentRelation.
     * @param taskParam
     *            Task parameter to release the ContentRelation.
     * @return XML representation of the ContentRelation
     * @throws EscidocException
     *             Thrown in case of eSciDoc framework failures.
     * @throws InternalClientException
     *             Thrown in case of client internal failures.
     * @throws TransportException
     *             Thrown in case of failures on transport level.
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
     * Revise an ContentRelation.
     * 
     * @param id
     *            Objid of the ContentRelation.
     * @param taskParam
     *            Task parameter to revise the ContentRelation.
     * @return XML representation of the ContentRelation
     * @throws EscidocException
     *             Thrown in case of eSciDoc framework failures.
     * @throws InternalClientException
     *             Thrown in case of client internal failures.
     * @throws TransportException
     *             Thrown in case of failures on transport level.
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
     * @return XML representation of the ContentRelation
     * @throws EscidocException
     *             Thrown in case of eSciDoc framework failures.
     * @throws InternalClientException
     *             Thrown in case of client internal failures.
     * @throws TransportException
     *             Thrown in case of failures on transport level.
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
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveContentRelations(
        final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveContentRelations(filter);
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
    public String retrieveContentRelations(final ExplainRequestType filter)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveContentRelations(filter);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * Get the last-modification timestamp of the contentRelation.
     * 
     * @param id
     *            The id of the contentRelation.
     * @return The timestamp of the last modification of the contentRelation.
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
                (Factory.getContentRelationMarshaller()
                    .unmarshalDocument(getClient().retrieve(id)))
                    .getLastModificationDate();
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
    public ContentRelationHandler getClient() throws InternalClientException {

        if (restClient == null) {

            ContentRelationRestServiceLocator serviceLocator =
                new ContentRelationRestServiceLocator();

            try {
                serviceLocator.setServiceAddress(getServiceAddress());
            }
            catch (MalformedURLException e) {
                throw new InternalClientException(e);
            }
            restClient = serviceLocator;
        }
        return this.restClient;
    }
}
