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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import javax.xml.rpc.ServiceException;

import org.joda.time.DateTime;

import de.escidoc.core.client.ClientBase;
import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.um.UserAccountHandler;
import de.escidoc.core.um.UserAccountHandlerServiceLocator;

/**
 * SOAP Handler for User Account.
 * 
 * @author SWA
 * 
 */
public class SoapUserAccountHandlerClient extends ClientBase {

    private UserAccountHandler soapClient = null;

    public SoapUserAccountHandlerClient() throws InternalClientException {

        super();
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
    public String create(final String userAccount) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().create(userAccount);
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
     * @param userAccount
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.UserAccountHandlerInterface#update(java.lang.String,
     *      java.lang.String)
     */
    public String update(final String id, final String userAccount)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().update(id, userAccount);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
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
    public void updatePassword(final String userId, final String taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException {
        try {
            getClient().updatePassword(userId, taskParam);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
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
    public void activate(final String userId, final String taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException {
        try {
            getClient().activate(userId, taskParam);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
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
    public void deactivate(final String userId, final String taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException {
        try {
            getClient().deactivate(userId, taskParam);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
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
    public String retrieveCurrentUser() throws EscidocClientException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveCurrentUser();
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
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
    public String retrieveCurrentGrants(final String userId)
        throws EscidocClientException, InternalClientException,
        TransportException {
        String result = null;
        try {
            result = getClient().retrieveCurrentGrants(userId);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @param preferencesXml
     * 
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String createPreference(final String id, final String preferenceXml)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().createPreference(id, preferenceXml);
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
     */
    public String retrievePreferences(final String id) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrievePreferences(id);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @param key
     * 
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrievePreference(final String id, final String key)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrievePreference(id, key);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @param key
     * @param value
     * 
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String updatePreference(
        final String id, final String key, final String value)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().updatePreference(id, key, value);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @param preferencesXml
     * 
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String updatePreferences(final String id, final String preferencesXml)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().updatePreferences(id, preferencesXml);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @param key
     * 
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void deletePreference(final String id, final String key)
        throws EscidocException, InternalClientException, TransportException {

        try {
            getClient().deletePreference(id, key);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
    }

    /**
     * Filter for Grants.
     * 
     * @param filter
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    @Deprecated
    public String retrieveUserAccounts(final String filter)
        throws EscidocClientException, InternalClientException,
        TransportException {

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
    public String retrieveUserAccounts(final SearchRetrieveRequestType filter)
        throws EscidocException, InternalClientException, TransportException {

        return filterUserAccounts(getEscidoc12Filter(filter));
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

        return filterUserAccounts(getEscidoc12Filter(filter));
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
    public String createGrant(final String id, final String grantXml)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().createGrant(id, grantXml);
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
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    @Deprecated
    public String retrieveGrants(final String filter)
        throws EscidocClientException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().retrieveGrants(filter);
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
    public String retrieveGrants(final SearchRetrieveRequestType filter)
        throws EscidocException, InternalClientException, TransportException {

        return filterGrants(getEscidoc12Filter(filter));
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

        return filterGrants(getEscidoc12Filter(filter));
    }

    /**
     * Get the last-modification timestamp of the User Account.
     * 
     * @param id
     *            The id of the User Account.
     * @return The timestamp of the last modification of the User Account.
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

        try {
            if (soapClient == null) {
                UserAccountHandlerServiceLocator serviceLocator =
                    new UserAccountHandlerServiceLocator(getEngineConfig());
                String adress =
                    serviceLocator.getUserAccountHandlerServiceAddress();
                URL url = null;
                try {
                    url = new URL(adress);
                }
                catch (MalformedURLException e) {
                    throw new InternalClientException(e);
                }
                String path = url.getFile();
                adress = getServiceAddress() + path;

                try {
                    url = new URL(adress);
                }
                catch (MalformedURLException e) {
                    throw new ServiceException(e);
                }
                soapClient = serviceLocator.getUserAccountHandlerService(url);
            }
        }
        catch (ServiceException e) {
            throw new InternalClientException(e.getMessage(), e);
        }
        return soapClient;
    }

    /**
     * generic filter method request for user accounts.
     * 
     * @param escidoc12Filter
     *            data structure for eSciDoc 1.2 filter
     * @return filter response
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    private String filterUserAccounts(
        final HashMap<String, String[]> escidoc12Filter)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveUserAccounts(escidoc12Filter);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;

    }

    /**
     * Generic filter method request for grants.
     * 
     * @param escidoc12Filter
     *            data structure for eSciDoc 1.2 filter
     * @return filter response
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    private String filterGrants(final HashMap<String, String[]> escidoc12Filter)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveGrants(escidoc12Filter);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;

    }

}
