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
package de.escidoc.core.client;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import org.joda.time.DateTime;

import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface;
import de.escidoc.core.client.rest.RestUserAccountHandlerClient;
import de.escidoc.core.client.soap.SoapUserAccountHandlerClient;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.aa.useraccount.Grant;
import de.escidoc.core.resources.aa.useraccount.Grants;
import de.escidoc.core.resources.aa.useraccount.Preference;
import de.escidoc.core.resources.aa.useraccount.Preferences;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.aa.useraccount.UserAccounts;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.sb.explain.ExplainRecord;
import de.escidoc.core.resources.sb.srw.SearchRetrieveResponseType;

/**
 * This is the generic ContainerSoapContainerHandlerClient which binds the
 * transport specific classes. The transport specification is done via
 * properties configuration of the eSciDoc client.
 * 
 * @author SWA
 * 
 */
public class UserAccountHandlerClient
    implements UserAccountHandlerClientInterface<UserAccount> {

    // Set SOAP as default transport protocol (for now :-()
    private TransportProtocol transport = TransportProtocol.SOAP;

    private SoapUserAccountHandlerClient soapUserAccountHandlerClient = null;

    private RestUserAccountHandlerClient restUserAccountHandlerClient = null;

    private Authentication auth = null;

    /**
     * Create UserAccountHandlerClient instance. The service protocol
     * (REST/SOAP/..) selected from the configuration. Default is SOAP.
     * 
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public UserAccountHandlerClient() throws EscidocException,
        InternalClientException, TransportException {

        // read service protocol from config or set as default SOAP
        this.soapUserAccountHandlerClient = new SoapUserAccountHandlerClient();
    }

    /**
     * See Interface for functional description.
     * 
     * @param userAccount
     * @return
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public UserAccount create(final UserAccount userAccount)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String userAccountString =
            Factory.getUserAccountMarshaller().marshalDocument(userAccount);

        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapUserAccountHandlerClient().create(userAccountString);
        }
        else {
            xml = getRestUserAccountHandlerClient().create(userAccountString);

        }
        return Factory.getUserAccountMarshaller().unmarshalDocument(xml);
    }

    /**
     * See Interface for functional description.
     * 
     * @param id
     * @return
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public UserAccount retrieve(final String id) throws EscidocException,
        InternalClientException, TransportException {

        return Factory.getUserAccountMarshaller().unmarshalDocument(
            getSoapUserAccountHandlerClient().retrieve(id));
    }

    /**
     * See Interface for functional description.
     * 
     * @param id
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public void delete(final String id) throws EscidocException,
        InternalClientException, TransportException {

        getSoapUserAccountHandlerClient().delete(id);
    }

    /**
     * See Interface for functional description.
     * 
     * @param container
     * @return
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public UserAccount update(final UserAccount userAccount)
        throws EscidocException, InternalClientException, TransportException {

        String xml =
            getSoapUserAccountHandlerClient().update(
                ((UserAccount) userAccount).getObjid(),
                Factory.getUserAccountMarshaller().marshalDocument(
                    (UserAccount) userAccount));
        return Factory.getUserAccountMarshaller().unmarshalDocument(xml);
    }

    /**
     * Update password for User Account. Be aware that update password works
     * only for the users managed within the eSciDoc infrastructure internal
     * database. Update for Shibboleth provided user is impossible.
     * 
     * @param userId
     * @param taskParam
     * 
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public void updatePassword(final String userId, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException {
        getSoapUserAccountHandlerClient().updatePassword(userId,
            Factory.getTaskParamMarshaller().marshalDocument(taskParam));
    }

    /**
     * Activate User Account.
     * 
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public void activate(final String userId, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException {
        getSoapUserAccountHandlerClient().activate(userId,
            Factory.getTaskParamMarshaller().marshalDocument(taskParam));
    }

    /**
     * Deactivate User Account.
     * 
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public void deactivate(final String userId, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException {
        getSoapUserAccountHandlerClient().deactivate(userId,
            Factory.getTaskParamMarshaller().marshalDocument(taskParam));
    }

    /**
     * Retrieve details of current used User Account.
     * 
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public UserAccount retrieveCurrentUser() throws EscidocClientException,
        InternalClientException, TransportException {

        String xml = getSoapUserAccountHandlerClient().retrieveCurrentUser();
        return Factory.getUserAccountMarshaller().unmarshalDocument(xml);
    }

    /**
     * Create Grant for User Account
     * 
     * @param userId
     *            The objid of the User Account
     * @param grant
     *            The new Grant
     * @return The created Grant
     * 
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public Grant createGrant(final String userId, final Grant grant)
        throws EscidocClientException, InternalClientException,
        TransportException {

        String grantXml = Factory.getGrantMarshaller().marshalDocument(grant);

        if (getTransport() == TransportProtocol.SOAP) {
            grantXml =
                getSoapUserAccountHandlerClient().createGrant(userId, grantXml);
        }
        else {
            grantXml =
                getRestUserAccountHandlerClient().createGrant(userId, grantXml);
        }

        return Factory.getGrantMarshaller().unmarshalDocument(grantXml);
    }

    /**
     * Revoke Grant for User Account.
     * 
     * @param userId
     *            The objid of the User Account
     * @param grantId
     *            The objid of the Grant
     * @param taskParam
     *            The task parameter
     * 
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public void revokeGrant(
        final String userId, final String grantId, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException {

        String taskParamXml =
            Factory.getTaskParamMarshaller().marshalDocument(taskParam);

        if (getTransport() == TransportProtocol.SOAP) {
            getSoapUserAccountHandlerClient().revokeGrant(userId, grantId,
                taskParamXml);
        }
        else {
            getRestUserAccountHandlerClient().revokeGrant(userId, grantId,
                taskParamXml);
        }
    }

    /**
     * Create Preference of User Account.
     * 
     * @return The created Preference
     * 
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public Preference createPreference(
        final String userId, final Preference preference)
        throws EscidocClientException, InternalClientException,
        TransportException {

        String preferenceXml =
            Factory.getPreferenceMarshaller().marshalDocument(preference);

        if (getTransport() == TransportProtocol.SOAP) {
            preferenceXml =
                getSoapUserAccountHandlerClient().createPreference(userId,
                    preferenceXml);
        }
        else {
            preferenceXml =
                getRestUserAccountHandlerClient().createPreference(userId,
                    preferenceXml);
        }

        return Factory.getPreferenceMarshaller().unmarshalDocument(
            preferenceXml);
    }

    /**
     * Retrieve Preference of User Account.
     * 
     * @param userId
     *            The objid of the user
     * @param name
     *            The name of the Preference
     * @return The Preference
     * 
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public Preference retrievePreference(final String userId, final String name)
        throws EscidocClientException, InternalClientException,
        TransportException {

        String preference;
        if (getTransport() == TransportProtocol.SOAP) {
            preference =
                getSoapUserAccountHandlerClient().retrievePreference(userId,
                    name);
        }
        else {
            preference =
                getRestUserAccountHandlerClient().retrievePreference(userId,
                    name);
        }

        return Factory.getPreferenceMarshaller().unmarshalDocument(preference);
    }

    /**
     * Retrieve Preferences of User Account.
     * 
     * @return The Preference
     * 
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public Preferences retrievePreferences(final String userId)
        throws EscidocClientException, InternalClientException,
        TransportException {

        String preferences;
        if (getTransport() == TransportProtocol.SOAP) {
            preferences =
                getSoapUserAccountHandlerClient().retrievePreferences(userId);
        }
        else {
            preferences =
                getRestUserAccountHandlerClient().retrievePreferences(userId);
        }

        return Factory
            .getPreferencesMarshaller().unmarshalDocument(preferences);
    }

    /**
     * Update Preference of User Account.
     * 
     * @param userId
     * @param preference
     * @return The updated Preference
     * 
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public Preference updatePreference(
        final String userId, final Preference preference)
        throws EscidocClientException, InternalClientException,
        TransportException {

        String preferenceXml =
            Factory.getPreferenceMarshaller().marshalDocument(preference);

        if (getTransport() == TransportProtocol.SOAP) {
            preferenceXml =
                getSoapUserAccountHandlerClient().updatePreference(userId,
                    preference.getName(), preferenceXml);
        }
        else {
            preferenceXml =
                getRestUserAccountHandlerClient().updatePreference(userId,
                    preference.getName(), preferenceXml);
        }

        return Factory.getPreferenceMarshaller().unmarshalDocument(
            preferenceXml);
    }

    /**
     * Delete Preference of User Account.
     * 
     * @param userId
     *            The objid of the user
     * @param preference
     *            The preference (where at least the name has to be set)
     * 
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public void deletePreference(
        final String userId, final Preference preference)
        throws EscidocClientException, InternalClientException,
        TransportException {

        deletePreference(userId, preference.getName());
    }

    /**
     * Delete Preference of User Account.
     * 
     * @param userId
     *            The objid of the user
     * @param name
     *            The name of the preference
     * 
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public void deletePreference(final String userId, final String name)
        throws EscidocClientException, InternalClientException,
        TransportException {

        if (getTransport() == TransportProtocol.SOAP) {
            getSoapUserAccountHandlerClient().deletePreference(userId, name);
        }
        else {
            getRestUserAccountHandlerClient().createPreference(userId, name);
        }
    }

    /**
     * Retrieve Grants of current User Account.
     * 
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public Grants retrieveCurrentGrants(final String userId)
        throws EscidocClientException, InternalClientException,
        TransportException {

        String grantsXml = null;

        if (getTransport() == TransportProtocol.SOAP) {
            grantsXml =
                getSoapUserAccountHandlerClient().retrieveCurrentGrants(userId);
        }
        else {
            grantsXml =
                getRestUserAccountHandlerClient().retrieveCurrentGrants(userId);
        }

        return Factory.getGrantsMarshaller().unmarshalDocument(grantsXml);
    }

    /**
     * Retrieve a Grant of current User Account by id.
     * 
     * @param userId
     *            The objid of the User Account
     * @param grantId
     *            The objid of the Grant
     * @return The Grant
     * 
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public Grant retrieveGrant(final String userId, final String grantId)
        throws EscidocClientException, InternalClientException,
        TransportException {

        String grantXml = null;

        if (getTransport() == TransportProtocol.SOAP) {
            grantXml =
                getSoapUserAccountHandlerClient()
                    .retrieveGrant(userId, grantId);
        }
        else {
            grantXml =
                getRestUserAccountHandlerClient()
                    .retrieveGrant(userId, grantId);
        }

        return Factory.getGrantMarshaller().unmarshalDocument(grantXml);
    }

    /**
     * Retrieve User Accounts (Filter for User Accounts).
     * 
     * @param taskParam
     *            Filter parameter
     * @return UserAccounts
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Deprecated
    public UserAccounts retrieveUserAccounts(final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException {
        String xml =
            getSoapUserAccountHandlerClient().retrieveUserAccounts(
                Factory.getTaskParamMarshaller().marshalDocument(taskParam));
        return Factory.getUserAccountListMarshaller().unmarshalDocument(xml);

    }

    /**
     * Retrieve UserAccounts (Filter for UserAccounts).
     * 
     * @param filter
     *            Filter parameter
     * @return SearchRetrieveResponseType
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public SearchRetrieveResponseType retrieveUserAccounts(
        final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapUserAccountHandlerClient().retrieveUserAccounts(filter);
        }
        else {
            xml =
                getRestUserAccountHandlerClient().retrieveUserAccounts(filter);
        }
        return Factory.getFilterResponseMarshaller().unmarshalDocument(xml);
    }

    /**
     * Retrieve UserAccounts (Filter for UserAccounts).
     * 
     * @param filter
     *            Filter parameter
     * @return ExplainRecord
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public ExplainRecord retrieveUserAccounts(final ExplainRequestType filter)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapUserAccountHandlerClient().retrieveUserAccounts(filter);
        }
        else {
            xml =
                getRestUserAccountHandlerClient().retrieveUserAccounts(filter);
        }
        return Factory.getExplainRecordMarshaller().unmarshalDocument(xml);
    }

    /**
     * Retrieve Grants (Filter for Grants).
     * 
     * @param taskParam
     *            Filter parameter
     * @return Grants
     * 
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Deprecated
    public Grants retrieveGrants(final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapUserAccountHandlerClient()
                    .retrieveGrants(
                        Factory.getTaskParamMarshaller().marshalDocument(
                            taskParam));
        }
        else {
            xml =
                getRestUserAccountHandlerClient()
                    .retrieveGrants(
                        Factory.getTaskParamMarshaller().marshalDocument(
                            taskParam));
        }
        return Factory.getGrantsMarshaller().unmarshalDocument(xml);
    }

    /**
     * Retrieve Grants (Filter for Grants).
     * 
     * @param filter
     *            Filter parameter
     * @return SearchRetrieveResponseType
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public SearchRetrieveResponseType retrieveGrants(
        final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapUserAccountHandlerClient().retrieveGrants(filter);
        }
        else {
            xml = getRestUserAccountHandlerClient().retrieveGrants(filter);
        }
        return Factory.getFilterResponseMarshaller().unmarshalDocument(xml);
    }

    /**
     * Retrieve Grants (Filter for Grants).
     * 
     * @param filter
     *            Filter parameter
     * @return ExplainRecord
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public ExplainRecord retrieveGrants(final ExplainRequestType filter)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapUserAccountHandlerClient().retrieveGrants(filter);
        }
        else {
            xml = getRestUserAccountHandlerClient().retrieveGrants(filter);
        }
        return Factory.getExplainRecordMarshaller().unmarshalDocument(xml);
    }

    /**
     * See Interface for functional description.
     * 
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public DateTime getLastModificationDate(final String id)
        throws EscidocException, InternalClientException, TransportException {

        return getSoapUserAccountHandlerClient().getLastModificationDate(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.CrudHandlerInterface#login(java.lang
     * .String, java.lang.String, java.lang.String)
     */
    @Deprecated
    public String login(
        final String serviceAddress, final String username,
        final String password) throws EscidocException,
        InternalClientException, TransportException {

        setServiceAddress(serviceAddress);

        if (this.auth == null) {
            try {
                auth = new Authentication(serviceAddress, username, password);
            }
            catch (EscidocClientException e) {
                throw new InternalClientException("Login failed.", e);
            }
        }

        String handle = this.auth.getHandle();
        setHandle(handle);

        return handle;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.CrudHandlerInterface#logout()
     */
    @Deprecated
    public void logout() throws EscidocException, InternalClientException,
        TransportException {

        setHandle("");
    }

    /**
     * Get Login-Handle.
     * 
     * @return Login-Handle
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     */
    public String getHandle() throws InternalClientException {

        if (getTransport() == TransportProtocol.SOAP) {
            return getSoapUserAccountHandlerClient().getHandle();
        }
        else {
            return getRestUserAccountHandlerClient().getHandle();
        }
    }

    /**
     * See Interface for functional description.
     * 
     * @param handle
     * @throws InternalClientException
     * @see de.escidoc.core.client.interfaces.BaseClientHandlerInterface#setHandle(java.lang.String)
     */
    public void setHandle(final String handle) throws InternalClientException {

        if (getTransport() == TransportProtocol.SOAP) {
            getSoapUserAccountHandlerClient().setHandle(handle);
        }
        else {
            getRestUserAccountHandlerClient().setHandle(handle);
        }
    }

    /**
     * @return the soapContainerHandlerClient
     */
    public SoapUserAccountHandlerClient getSoapUserAccountHandlerClient() {
        return soapUserAccountHandlerClient;
    }

    /**
     * Set the service endpoint address.
     * 
     * @param address
     *            URL of the service endpoint.
     * @throws InternalClientException
     *             Thrown if URL is not valid.
     */
    public void setServiceAddress(final String address)
        throws InternalClientException {

        if (getTransport() == TransportProtocol.SOAP) {
            getSoapUserAccountHandlerClient().setServiceAddress(address);
        }
        else {
            getRestUserAccountHandlerClient().setServiceAddress(address);
        }
    }

    /**
     * Get the SOAP handler.
     * 
     * @return SoapUserAccountHandlerClient
     * @throws InternalClientException
     *             Thrown if creating instance of SoapItemHandlerClient failed.
     */
    public SoapUserAccountHandlerClient getSoapItemHandlerClient()
        throws InternalClientException {
        if (this.soapUserAccountHandlerClient == null) {
            this.soapUserAccountHandlerClient =
                new SoapUserAccountHandlerClient();
        }
        return this.soapUserAccountHandlerClient;
    }

    /**
     * Get the REST handler.
     * 
     * @return RestUserAccountHandlerClient
     * @throws InternalClientException
     *             Thrown if creating instance of RestAccountHandlerClient
     *             failed.
     */
    public RestUserAccountHandlerClient getRestUserAccountHandlerClient()
        throws InternalClientException {
        if (this.restUserAccountHandlerClient == null) {
            this.restUserAccountHandlerClient =
                new RestUserAccountHandlerClient();
        }
        return this.restUserAccountHandlerClient;
    }

    /**
     * Set the Transport Protocol (REST/SOAP).
     * 
     * @param tp
     *            The transport protocol.
     */
    public void setTransport(final TransportProtocol tp) {
        this.transport = tp;
    }

    /**
     * Set the Transport Protocol (REST/SOAP).
     * 
     * @return The used transport protocol.
     */
    public TransportProtocol getTransport() {
        return this.transport;
    }

}
