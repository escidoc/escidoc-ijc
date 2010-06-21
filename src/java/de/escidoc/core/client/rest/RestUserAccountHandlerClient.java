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
import de.escidoc.core.client.interfaces.UserAccountHandler;
import de.escidoc.core.client.rest.serviceLocator.UserAccountRestServiceLocator;
import de.escidoc.core.common.jibx.Factory;

/**
 * REST Handler for User Account.
 * 
 * @author SWA
 * 
 */
public class RestUserAccountHandlerClient extends ClientBase {

    private final Logger logger =
        Logger.getLogger(RestUserAccountHandlerClient.class.getName());

    private UserAccountHandler restClient = null;

    /**
     * 
     * @throws InternalClientException
     */
    public RestUserAccountHandlerClient() throws InternalClientException {

        super();
    }

    /**
     * 
     * @param resourceXml
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.UserAccountHandlerInterface#create(java.lang.String)
     */
    public String create(final String resourceXml) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().create(resourceXml);
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
     * @see de.escidoc.core.om.service.interfaces.UserAccountHandlerInterface#delete(java.lang.String)
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
     * @see de.escidoc.core.om.service.interfaces.UserAccountHandlerInterface#retrieve(java.lang.String)
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
     * @param context
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.UserAccountHandlerInterface#update(java.lang.String,
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
     * 
     * @param id
     * @param context
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.UserAccountHandlerInterface#update(java.lang.String,
     *      java.lang.String)
     */
    public void updatePassword(final String id, final String taskParam)
        throws EscidocException, InternalClientException, TransportException {

        try {
            getClient().updatePassword(id, taskParam);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
    }

    /**
     * 
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.UserAccountHandlerInterface#retrieveUserAccounts(java.lang.String)
     */
    @Deprecated
    public String retrieveUserAccounts(final String taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveUserAccounts(taskParam);
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
    public String retrieveUserAccounts(final SearchRetrieveRequestType filter)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveUserAccounts(filter);
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
    public String retrieveUserAccounts(final ExplainRequestType filter)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveUserAccounts(filter);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * 
     * @param context
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.UserAccountHandlerInterface
     *      #createGrant(java.lang.String, java.lang.String)
     */
    public String createGrant(final String objid, final String resourceXml)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().createGrant(objid, resourceXml);
        }
        catch (Exception e) {
            logger.debug(e);
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * 
     * @param objid
     * @param preferenceXML
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String createPreference(
        final String objid, final String preferenceXML)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().createPreference(objid, preferenceXML);
        }
        catch (Exception e) {
            logger.debug(e);
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * 
     * @param objid
     * @param key
     * 
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void deletePreference(final String objid, final String key)
        throws EscidocException, InternalClientException, TransportException {

        try {
            getClient().deletePreference(objid, key);
        }
        catch (Exception e) {
            logger.debug(e);
            ExceptionMapper.map(e);
        }
    }

    /**
     * Update User Account Preference.
     * 
     * @param objid
     * @param preferenceXML
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String updatePreference(
        final String objid, final String key, final String preferenceXML)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().updatePreference(objid, key, preferenceXML);
        }
        catch (Exception e) {
            logger.debug(e);
            ExceptionMapper.map(e);
        }
        return result;
    }

    public String updatePreferences(
        final String objid, final String preferencesXML)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().updatePreferences(objid, preferencesXML);
        }
        catch (Exception e) {
            logger.debug(e);
            ExceptionMapper.map(e);
        }
        return result;
    }

    public String retrievePreference(final String objid, final String key)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrievePreference(objid, key);
        }
        catch (Exception e) {
            logger.debug(e);
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * Retrieve preferences of User Account.
     * 
     * @param objid
     *            objid of user account
     * @return XML representation of preferences of User Account.
     * 
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrievePreferences(final String objid)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrievePreferences(objid);
        }
        catch (Exception e) {
            logger.debug(e);
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * Retrieve account details of current user.
     * 
     * @return The XML representation of the current used (logged in) user.
     * 
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveCurrentUser() throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveCurrentUser();
        }
        catch (Exception e) {
            logger.debug(e);
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
    public DateTime getLastModificationDate(final String id)
        throws EscidocException, InternalClientException, TransportException {

        DateTime result = null;
        try {
            result =
                (Factory.getUserAccountMarshaller()
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
    public UserAccountHandler getClient() throws InternalClientException {

        if (restClient == null) {

            UserAccountRestServiceLocator serviceLocator =
                new UserAccountRestServiceLocator();

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
