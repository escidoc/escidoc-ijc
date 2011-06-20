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

import de.escidoc.core.aa.UserAccountHandler;
import de.escidoc.core.aa.UserAccountHandlerServiceLocator;
import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;

/**
 * SOAP Handler for User Account.
 * 
 * @author SWA
 * 
 */
public class SoapUserAccountHandlerClient extends SoapClientBase {

    private static final Logger LOG = Logger.getLogger(SoapUserAccountHandlerClient.class);

    private UserAccountHandler soapClient = null;

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public SoapUserAccountHandlerClient(final URL serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     * @deprecated Use
     *             {@link SoapUserAccountHandlerClient#SoapUserAccountHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public SoapUserAccountHandlerClient(final String serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param userAccount
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.UserAccountHandlerInterface#create(java.lang.String)
     */
    public String create(final String userAccount) throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().create(userAccount);
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
     * @param userAccount
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.UserAccountHandlerInterface#update(java.lang.String,
     *      java.lang.String)
     */
    public String update(final String id, final String userAccount) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().update(id, userAccount);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * Update password for User Account. Be aware that update password works
     * only for the users managed within the eSciDoc infrastructure internal
     * database. Update for Shibboleth provided user is impossible.
     * 
     * @param userId
     * @param taskParam
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void updatePassword(final String userId, final String taskParam) throws EscidocClientException,
        InternalClientException, TransportException {
        try {
            getClient().updatePassword(userId, taskParam);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
    }

    /**
     * Activate User Account.
     * 
     * @param userId
     * @param taskParam
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void activate(final String userId, final String taskParam) throws EscidocClientException,
        InternalClientException, TransportException {
        try {
            getClient().activate(userId, taskParam);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
    }

    /**
     * Deactivate User Account
     * 
     * @param userId
     * @param taskParam
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void deactivate(final String userId, final String taskParam) throws EscidocClientException,
        InternalClientException, TransportException {
        try {
            getClient().deactivate(userId, taskParam);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
    }

    /**
     * Retrieve details of the current used user.
     * 
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveCurrentUser() throws EscidocClientException, InternalClientException, TransportException {

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
     * Retrieve Grants of the user that are current valid.
     * 
     * @param userId
     *            The objid of the user
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveCurrentGrants(final String userId) throws EscidocClientException, InternalClientException,
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
     * Retrieve Grants of the user that are current valid.
     * 
     * @param userId
     *            The objid of the user
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveGrant(final String userId, final String grantId) throws EscidocClientException,
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
    public String createPreference(final String id, final String preferenceXml) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().createPreference(id, preferenceXml);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * Retrieve preferences.
     * 
     * @param id
     *            Objid of user account
     * @return XML representation of preferences
     * 
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrievePreferences(final String id) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().retrievePreferences(id);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * Retrieve an preference.
     * 
     * @param id
     *            Objid of user account
     * @param key
     *            preference key
     * @return XML representation of preference
     * 
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrievePreference(final String id, final String key) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrievePreference(id, key);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * Update a preference.
     * 
     * @param id
     *            Objid of user account
     * @param key
     *            key of
     * @param value
     *            new value of preference
     * @return XML representation of updated preference
     * 
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String updatePreference(final String id, final String key, final String value) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().updatePreference(id, key, value);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * Update preferences.
     * 
     * @param id
     *            Objid of user account
     * @param preferenceXml
     *            XML representation of preferences
     * @return XML representation of updated preferences
     * 
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String updatePreferences(final String id, final String preferencesXml) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().updatePreferences(id, preferencesXml);
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
    public void deletePreference(final String id, final String key) throws EscidocException, InternalClientException,
        TransportException {

        try {
            getClient().deletePreference(id, key);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
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
    public String createAttribute(final String id, final String attributeXml) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().createAttribute(id, attributeXml);
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
    public String retrieveAttributes(final String id) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().retrieveAttributes(id);
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
     * @param attributeId
     *            Objid of attribute
     * @return XML representation of attribute
     * 
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveAttribute(final String id, final String attributeId) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveAttribute(id, attributeId);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * Update an attribute.
     * 
     * @param id
     *            Objid of user account
     * @param attributeId
     *            Objid of attribute
     * @param value
     *            value of attribute
     * @return XML representation of updated attribute
     * 
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String updateAttribute(final String id, final String attributeId, final String value)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().updateAttribute(id, attributeId, value);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    // /**
    // *
    // * @param id
    // * @param attributesXml
    // *
    // * @return
    // * @throws EscidocException
    // * @throws InternalClientException
    // * @throws TransportException
    // */
    // public String updateAttributes(final String id, final String
    // attributesXml)
    // throws EscidocException, InternalClientException, TransportException {
    //
    // String result = null;
    // try {
    // result = getClient().updateAttributes(id,preferencesXml);
    // }
    // catch (Exception e) {
    // ExceptionMapper.map(e);
    // }
    // return result;
    // }

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
    public void deleteAttribute(final String id, final String attributeId) throws EscidocException,
        InternalClientException, TransportException {

        try {
            getClient().deleteAttribute(id, attributeId);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
    }

    /**
     * Retrieve attributes by name.
     * 
     * @param id
     *            Objid of user account
     * @param name
     *            name of attribute
     * @return XML representation with list of attributes with given name for
     *         the selected user account.
     * 
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveNamedAttributes(final String id, final String attributeName) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveNamedAttributes(id, attributeName);
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
    public String retrieveUserAccounts(final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        evalRequest(request, true);

        return retrieveUserAccounts(getEscidoc12Filter(request));
    }

    /**
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveUserAccounts(final HashMap<String, String[]> filter) throws EscidocException,
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
        return retrieveUserAccounts(getEscidoc12Filter(filter));
    }

    /**
     * Create Grant.
     * 
     * @param id
     *            The objid of the user account
     * @param grantXml
     *            The XMl representation of the Grant
     * @return The XMl representation of the created Grant
     * 
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String createGrant(final String id, final String grantXml) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().createGrant(id, grantXml);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * Revoke Grant.
     * 
     * @param id
     *            The objid of the user account
     * @param grantId
     *            The objid of the to delete Grant
     * 
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void revokeGrant(final String id, final String grantId, final String taskParam) throws EscidocException,
        InternalClientException, TransportException {

        try {
            getClient().revokeGrant(id, grantId, taskParam);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
    }

    /**
     * 
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveGrants(final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        evalRequest(request, true);

        return retrieveGrants(getEscidoc12Filter(request));
    }

    /**
     * 
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveGrants(final ExplainRequestType request) throws EscidocException, InternalClientException,
        TransportException {

        evalRequest(request);

        return retrieveGrants(getEscidoc12Filter(request));
    }

    /**
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveGrants(final HashMap<String, String[]> filter) throws EscidocException,
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
     * @return Returns the soapClient.
     * @throws InternalClientException
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    @Override
    public UserAccountHandler getClient() throws InternalClientException {
        if (soapClient == null) {
            final UserAccountHandlerServiceLocator serviceLocator =
                new UserAccountHandlerServiceLocator(getEngineConfig());
            final URL url = getHandlerServiceURL(serviceLocator.getUserAccountHandlerServiceAddress());
            try {
                soapClient = serviceLocator.getUserAccountHandlerService(url);
            }
            catch (final ServiceException e) {
                throw new InternalClientException(e.getMessage(), e);
            }
            registerPWCallback(soapClient);
        }
        return soapClient;
    }
}