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
import de.escidoc.core.client.exceptions.EscidocClientException;
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

    private final Logger logger = Logger
        .getLogger(RestUserAccountHandlerClient.class.getName());

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
            logger.debug(e);
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
            logger.debug(e);
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
            logger.debug(e);
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
            logger.debug(e);
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
            logger.debug(e);
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
            logger.debug(e);
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
            logger.debug(e);
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * Filter for Grants
     * 
     * @param taskParam
     * @return XML representation of Grant list
     * 
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @Deprecated
    public String retrieveGrants(final String taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveGrants(taskParam);
        }
        catch (Exception e) {
            logger.debug(e);
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
    public String retrieveGrants(final SearchRetrieveRequestType filter)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveGrants(filter);
        }
        catch (Exception e) {
            logger.debug(e);
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
    public String retrieveGrants(final ExplainRequestType filter)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveGrants(filter);
        }
        catch (Exception e) {
            logger.debug(e);
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * Create Grant.
     * 
     * @param objid
     *            The objid of the user
     * @param resourceXml
     *            XML represtation of Grant
     * @return XML representation of the created Grant
     * 
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
     * Revoke Grant.
     * 
     * @param objid
     *            The objid of the User Account
     * @param grantId
     *            The objid of the Grant
     * @param taskParam
     *            The task parameter
     * 
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void revokeGrant(
        final String objid, final String grantId, final String taskParam)
        throws EscidocException, InternalClientException, TransportException {

        try {
            getClient().revokeGrant(objid, grantId, taskParam);
        }
        catch (Exception e) {
            logger.debug(e);
            ExceptionMapper.map(e);
        }
    }

    /**
     * Create a preference.
     * 
     * @param id
     *            Objid of user account
     * @param preferenceXml
     *            XML representation of preference
     * @return XML representation of created preference
     * 
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
     * Delete a preference.
     * 
     * @param id
     *            Objid of user account
     * @param key
     *            key of preference
     * @return XML representation of updated preference
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
     * Update a preference.
     * 
     * @param id
     *            Objid of user account
     * @param key
     *            key of preference
     * @param value
     *            value of preference
     * @return XML representation of updated preference
     * 
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
     * Create an attribute.
     * 
     * @param id
     *            Objid of user account
     * @param attributeXml
     *            XML representation of attribute
     * @return XML representation of created attribute
     * 
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String createAttribute(final String objid, final String preferenceXML)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().createAttribute(objid, preferenceXML);
        }
        catch (Exception e) {
            logger.debug(e);
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * Delete an attribute.
     * 
     * @param id
     *            Objid of user account
     * @param attributeId
     *            Objid of attribute
     * 
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void deleteAttribute(final String objid, final String attributeId)
        throws EscidocException, InternalClientException, TransportException {

        try {
            getClient().deleteAttribute(objid, attributeId);
        }
        catch (Exception e) {
            logger.debug(e);
            ExceptionMapper.map(e);
        }
    }

    /**
     * Update an attribute.
     * 
     * @param id
     *            Objid of user account
     * @param attributeId
     *            objid of attribute
     * @param value
     *            value of attribute
     * @return XML representation of updated attribute
     * 
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String updateAttribute(
        final String objid, final String attributeId, final String preferenceXML)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result =
                getClient().updateAttribute(objid, attributeId, preferenceXML);
        }
        catch (Exception e) {
            logger.debug(e);
            ExceptionMapper.map(e);
        }
        return result;
    }

    // public String updateAttributes(
    // final String objid, final String preferencesXML)
    // throws EscidocException, InternalClientException, TransportException {
    //
    // String result = null;
    // try {
    // result = getClient().updateAttributes(objid, preferencesXML);
    // }
    // catch (Exception e) {
    // logger.debug(e);
    // ExceptionMapper.map(e);
    // }
    // return result;
    // }

    /**
     * Retrieve an attribute.
     * 
     * @param id
     *            Objid of user account
     * @param attributeId
     *            Objid of attribute
     * @return XML representation of attribute
     * 
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveAttribute(final String objid, final String attributeId)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveAttribute(objid, attributeId);
        }
        catch (Exception e) {
            logger.debug(e);
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * Retrieve attributes.
     * 
     * @param id
     *            Objid of user account
     * @return XML representation of attributes
     * 
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveAttributes(final String objid)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveAttributes(objid);
        }
        catch (Exception e) {
            logger.debug(e);
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * Retrieve an attribute.
     * 
     * @param id
     *            Objid of user account
     * @param attributeName
     *            Name of attribute
     * @return XML representation with list of attributes with given name for
     *         the selected user account.
     * 
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveNamedAttributes(
        final String objid, final String attributeName)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveNamedAttributes(objid, attributeName);
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
     * Retrieve Grants of the current used.
     * 
     * @param userId
     *            The objid of the user
     * @return The Grants of the current user
     * 
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveCurrentGrants(final String userId)
        throws EscidocClientException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().retrieveCurrentGrants(userId);
        }
        catch (Exception e) {
            logger.debug(e);
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * Retrieve Grant of the current used by id.
     * 
     * @param userId
     *            The objid of the user
     * @param grantId
     *            The objid of the Grant
     * @return The Grant
     * 
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveGrant(final String userId, final String grantId)
        throws EscidocClientException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().retrieveGrant(userId, grantId);
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
