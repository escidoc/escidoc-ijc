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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.handler.UserAccountHandler;
import de.escidoc.core.client.rest.serviceLocator.UserAccountRestServiceLocator;

/**
 * REST Handler for User Account.
 * 
 * @author SWA
 * 
 */
public class RestUserAccountHandlerClient extends RestClientBase {

    private static final Logger LOG = LoggerFactory.getLogger(RestUserAccountHandlerClient.class.getName());

    private UserAccountHandler restClient = null;

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public RestUserAccountHandlerClient(final URL serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * Activate User Account.
     * 
     * @param userId
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void activate(final String userId, final String taskParam) throws EscidocException, InternalClientException,
        TransportException {
        try {
            getClient().activate(userId, taskParam);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
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
    public String create(final String resourceXml) throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().create(resourceXml);
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
     * @see de.escidoc.core.om.service.interfaces.UserAccountHandlerInterface#delete(java.lang.String)
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
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void deactivate(final String id, final String taskParam) throws EscidocException, InternalClientException,
        TransportException {

        try {
            getClient().deactivate(id, taskParam);
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
     * @see de.escidoc.core.om.service.interfaces.UserAccountHandlerInterface#retrieve(java.lang.String)
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
     * @param userAccountXml
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.UserAccountHandlerInterface#update(java.lang.String,
     *      java.lang.String)
     */
    public String update(final String id, final String userAccountXml) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().update(id, userAccountXml);
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
     * @see de.escidoc.core.om.service.interfaces.UserAccountHandlerInterface#update(java.lang.String,
     *      java.lang.String)
     */
    public void updatePassword(final String id, final String taskParam) throws EscidocException,
        InternalClientException, TransportException {

        try {
            getClient().updatePassword(id, taskParam);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
    }

    /**
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveUserAccounts(final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveUserAccounts(filter);
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
    public String retrieveUserAccounts(final ExplainRequestType filter) throws EscidocException,
        InternalClientException, TransportException {

        evalRequest(filter);

        String result = null;
        try {
            result = getClient().retrieveUserAccounts(filter);
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
    public String retrieveGrants(final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveGrants(filter);
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
    public String retrieveGrants(final ExplainRequestType filter) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().retrieveGrants(filter);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
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
    public String createGrant(final String objid, final String resourceXml) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().createGrant(objid, resourceXml);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
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
    public void revokeGrant(final String objid, final String grantId, final String taskParam) throws EscidocException,
        InternalClientException, TransportException {

        try {
            getClient().revokeGrant(objid, grantId, taskParam);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
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
    public String createPreference(final String objid, final String preferenceXML) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().createPreference(objid, preferenceXML);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
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
    public void deletePreference(final String objid, final String key) throws EscidocException,
        InternalClientException, TransportException {

        try {
            getClient().deletePreference(objid, key);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
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
    public String updatePreference(final String objid, final String key, final String preferenceXML)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().updatePreference(objid, key, preferenceXML);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    public String updatePreferences(final String objid, final String preferencesXML) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().updatePreferences(objid, preferencesXML);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    public String retrievePreference(final String objid, final String key) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrievePreference(objid, key);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
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
    public String retrievePreferences(final String objid) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().retrievePreferences(objid);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
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
    public String createAttribute(final String objid, final String preferenceXML) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().createAttribute(objid, preferenceXML);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
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
    public void deleteAttribute(final String objid, final String attributeId) throws EscidocException,
        InternalClientException, TransportException {

        try {
            getClient().deleteAttribute(objid, attributeId);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
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
    public String updateAttribute(final String objid, final String attributeId, final String attributeXML)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().updateAttribute(objid, attributeId, attributeXML);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
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
    public String retrieveAttribute(final String objid, final String attributeId) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveAttribute(objid, attributeId);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
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
    public String retrieveAttributes(final String objid) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().retrieveAttributes(objid);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
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
    public String retrieveNamedAttributes(final String objid, final String attributeName) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveNamedAttributes(objid, attributeName);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
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
    public String retrieveCurrentUser() throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveCurrentUser();
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
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
    public String retrieveCurrentGrants(final String userId) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().retrieveCurrentGrants(userId);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
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
    public String retrieveGrant(final String userId, final String grantId) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveGrant(userId, grantId);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @return Returns the restClient.
     * @throws InternalClientException
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    @Override
    public UserAccountHandler getClient() throws InternalClientException {

        if (restClient == null) {

            final UserAccountRestServiceLocator serviceLocator = new UserAccountRestServiceLocator();
            serviceLocator.registerRestCallbackHandler(this);
            serviceLocator.setServiceAddress(getServiceAddress());
            restClient = serviceLocator;
        }
        return this.restClient;
    }
}
