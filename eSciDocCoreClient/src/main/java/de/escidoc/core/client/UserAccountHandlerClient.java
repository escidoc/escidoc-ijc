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

import java.util.Collection;
import java.util.LinkedList;

import org.joda.time.DateTime;

import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface;
import de.escidoc.core.client.rest.RestUserAccountHandlerClient;
import de.escidoc.core.client.soap.SoapUserAccountHandlerClient;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.aa.useraccount.Attribute;
import de.escidoc.core.resources.aa.useraccount.Attributes;
import de.escidoc.core.resources.aa.useraccount.Grant;
import de.escidoc.core.resources.aa.useraccount.Grants;
import de.escidoc.core.resources.aa.useraccount.Preference;
import de.escidoc.core.resources.aa.useraccount.Preferences;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.aa.useraccount.UserAccounts;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.sb.Record;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;

/**
 * This is the generic ContainerSoapContainerHandlerClient which binds the
 * transport specific classes. The transport specification is done via
 * properties configuration of the eSciDoc client.
 * 
 * @author SWA
 * 
 */
public class UserAccountHandlerClient
    extends
    AbstractHandlerClient<SoapUserAccountHandlerClient, RestUserAccountHandlerClient>
    implements UserAccountHandlerClientInterface {

    /**
     * 
     */
    public UserAccountHandlerClient() {
        super();
    }

    /**
     * 
     * @param serviceAddress
     */
    public UserAccountHandlerClient(final String serviceAddress) {
        super(serviceAddress);
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
    @Override
    public UserAccount create(final UserAccount userAccount)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String userAccountString =
            MarshallerFactory
                .getInstance(getTransport()).getMarshaller(UserAccount.class)
                .marshalDocument(userAccount);

        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().create(userAccountString);
        }
        else {
            xml = getRestHandlerClient().create(userAccountString);

        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(UserAccount.class)
            .unmarshalDocument(xml);
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
    @Override
    public UserAccount retrieve(final String id) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieve(id);
        }
        else {
            xml = getRestHandlerClient().retrieve(id);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(UserAccount.class)
            .unmarshalDocument(xml);
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
    @Override
    public void delete(final String id) throws EscidocException,
        InternalClientException, TransportException {

        if (getTransport() == TransportProtocol.SOAP) {
            getSoapHandlerClient().delete(id);
        }
        else {
            getRestHandlerClient().delete(id);
        }
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
    @Override
    public UserAccount update(final UserAccount userAccount)
        throws EscidocException, InternalClientException, TransportException {

        String requestXML =
            MarshallerFactory
                .getInstance(getTransport()).getMarshaller(UserAccount.class)
                .marshalDocument(userAccount);
        String xml = null;

        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapHandlerClient().update(userAccount.getObjid(),
                    requestXML);
        }
        else {
            xml =
                getRestHandlerClient().update(userAccount.getObjid(),
                    requestXML);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(UserAccount.class)
            .unmarshalDocument(xml);
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
    @Override
    public void updatePassword(final String userId, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException {

        String param = marshalTaskParam(taskParam);

        if (getTransport() == TransportProtocol.SOAP) {
            getSoapHandlerClient().updatePassword(userId, param);
        }
        else {
            getRestHandlerClient().updatePassword(userId, param);
        }
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
    @Override
    public void activate(final String userId, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException {

        String param = marshalTaskParam(taskParam);

        if (getTransport() == TransportProtocol.SOAP) {
            getSoapHandlerClient().activate(userId, param);
        }
        else {
            getRestHandlerClient().activate(userId, param);
        }
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
    @Override
    public void deactivate(final String userId, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException {

        String param = marshalTaskParam(taskParam);

        if (getTransport() == TransportProtocol.SOAP) {
            getSoapHandlerClient().deactivate(userId, param);
        }
        else {
            getRestHandlerClient().deactivate(userId, param);
        }
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
    @Override
    public UserAccount retrieveCurrentUser() throws EscidocClientException,
        InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveCurrentUser();
        }
        else {
            xml = getRestHandlerClient().retrieveCurrentUser();
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(UserAccount.class)
            .unmarshalDocument(xml);
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
    @Override
    public Grant createGrant(final String userId, final Grant grant)
        throws EscidocClientException, InternalClientException,
        TransportException {

        String grantXml =
            MarshallerFactory
                .getInstance(getTransport()).getMarshaller(Grant.class)
                .marshalDocument(grant);

        if (getTransport() == TransportProtocol.SOAP) {
            grantXml = getSoapHandlerClient().createGrant(userId, grantXml);
        }
        else {
            grantXml = getRestHandlerClient().createGrant(userId, grantXml);
        }

        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Grant.class)
            .unmarshalDocument(grantXml);
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
    @Override
    public void revokeGrant(
        final String userId, final String grantId, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException {

        String taskParamXml = marshalTaskParam(taskParam);

        if (getTransport() == TransportProtocol.SOAP) {
            getSoapHandlerClient().revokeGrant(userId, grantId, taskParamXml);
        }
        else {
            getRestHandlerClient().revokeGrant(userId, grantId, taskParamXml);
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
    @Override
    public Preference createPreference(
        final String userId, final Preference preference)
        throws EscidocClientException, InternalClientException,
        TransportException {

        String preferenceXml =
            MarshallerFactory
                .getInstance(getTransport()).getMarshaller(Preference.class)
                .marshalDocument(preference);

        if (getTransport() == TransportProtocol.SOAP) {
            preferenceXml =
                getSoapHandlerClient().createPreference(userId, preferenceXml);
        }
        else {
            preferenceXml =
                getRestHandlerClient().createPreference(userId, preferenceXml);
        }

        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Preference.class)
            .unmarshalDocument(preferenceXml);
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
    @Override
    public Preference retrievePreference(final String userId, final String name)
        throws EscidocClientException, InternalClientException,
        TransportException {

        String preference;
        if (getTransport() == TransportProtocol.SOAP) {
            preference =
                getSoapHandlerClient().retrievePreference(userId, name);
        }
        else {
            preference =
                getRestHandlerClient().retrievePreference(userId, name);
        }

        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Preference.class)
            .unmarshalDocument(preference);
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
    @Override
    public Preferences retrievePreferences(final String userId)
        throws EscidocClientException, InternalClientException,
        TransportException {

        String preferences;
        if (getTransport() == TransportProtocol.SOAP) {
            preferences = getSoapHandlerClient().retrievePreferences(userId);
        }
        else {
            preferences = getRestHandlerClient().retrievePreferences(userId);
        }

        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Preferences.class)
            .unmarshalDocument(preferences);
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
    @Override
    public Preference updatePreference(
        final String userId, final Preference preference)
        throws EscidocClientException, InternalClientException,
        TransportException {

        String preferenceXml =
            MarshallerFactory
                .getInstance(getTransport()).getMarshaller(Preference.class)
                .marshalDocument(preference);

        if (getTransport() == TransportProtocol.SOAP) {
            preferenceXml =
                getSoapHandlerClient().updatePreference(userId,
                    preference.getName(), preferenceXml);
        }
        else {
            preferenceXml =
                getRestHandlerClient().updatePreference(userId,
                    preference.getName(), preferenceXml);
        }

        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Preference.class)
            .unmarshalDocument(preferenceXml);
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
    @Override
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
    @Override
    public void deletePreference(final String userId, final String name)
        throws EscidocClientException, InternalClientException,
        TransportException {

        if (getTransport() == TransportProtocol.SOAP) {
            getSoapHandlerClient().deletePreference(userId, name);
        }
        else {
            getRestHandlerClient().deletePreference(userId, name);
        }
    }

    /**
     * Create Attribute of User Account.
     * 
     * @return The created Attribute
     * 
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Attribute createAttribute(
        final String userId, final Attribute attribute)
        throws EscidocClientException, InternalClientException,
        TransportException {

        String attributeXml =
            MarshallerFactory
                .getInstance(getTransport()).getMarshaller(Attribute.class)
                .marshalDocument(attribute);

        if (getTransport() == TransportProtocol.SOAP) {
            attributeXml =
                getSoapHandlerClient().createAttribute(userId, attributeXml);
        }
        else {
            attributeXml =
                getRestHandlerClient().createAttribute(userId, attributeXml);
        }

        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Attribute.class)
            .unmarshalDocument(attributeXml);
    }

    /**
     * Retrieve attribute of User Account.
     * 
     * @param userId
     *            The objid of the user
     * @param attributeId
     *            The objid of the attribute
     * @return The attribute
     * 
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Attribute retrieveAttribute(
        final String userId, final String attributeId)
        throws EscidocClientException, InternalClientException,
        TransportException {

        String attribute;
        if (getTransport() == TransportProtocol.SOAP) {
            attribute =
                getSoapHandlerClient().retrieveAttribute(userId, attributeId);
        }
        else {
            attribute =
                getRestHandlerClient().retrieveAttribute(userId, attributeId);
        }

        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Attribute.class)
            .unmarshalDocument(attribute);
    }

    /**
     * Retrieve Attributes of User Account.
     * 
     * @return The Attribute
     * 
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Attributes retrieveAttributes(final String userId)
        throws EscidocClientException, InternalClientException,
        TransportException {

        String attributes;
        if (getTransport() == TransportProtocol.SOAP) {
            attributes = getSoapHandlerClient().retrieveAttributes(userId);
        }
        else {
            attributes = getRestHandlerClient().retrieveAttributes(userId);
        }

        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Attributes.class)
            .unmarshalDocument(attributes);
    }

    /**
     * Update Attribute of User Account.
     * 
     * @param userId
     * @param attribute
     * @return The updated Attribute
     * 
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Attribute updateAttribute(
        final String userId, final Attribute attribute)
        throws EscidocClientException, InternalClientException,
        TransportException {

        String attributeXml =
            MarshallerFactory
                .getInstance(getTransport()).getMarshaller(Attribute.class)
                .marshalDocument(attribute);

        if (getTransport() == TransportProtocol.SOAP) {
            attributeXml =
                getSoapHandlerClient().updateAttribute(userId,
                    attribute.getObjid(), attributeXml);
        }
        else {
            attributeXml =
                getRestHandlerClient().updateAttribute(userId,
                    attribute.getObjid(), attributeXml);
        }

        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Attribute.class)
            .unmarshalDocument(attributeXml);
    }

    /**
     * Delete Attribute of User Account.
     * 
     * @param userId
     *            The objid of the user
     * @param attribute
     *            The attribute (where at least the name has to be set)
     * 
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public void deleteAttribute(final String userId, final Attribute attribute)
        throws EscidocClientException, InternalClientException,
        TransportException {

        deleteAttribute(userId, attribute.getObjid());
    }

    /**
     * Delete Attribute of User Account.
     * 
     * @param userId
     *            The objid of the user
     * @param attributeId
     *            The objid of the attribute
     * 
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public void deleteAttribute(final String userId, final String attributeId)
        throws EscidocClientException, InternalClientException,
        TransportException {

        if (getTransport() == TransportProtocol.SOAP) {
            getSoapHandlerClient().deleteAttribute(userId, attributeId);
        }
        else {
            getRestHandlerClient().deleteAttribute(userId, attributeId);
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
    @Override
    public Grants retrieveCurrentGrants(final String userId)
        throws EscidocClientException, InternalClientException,
        TransportException {

        String grantsXml = null;

        if (getTransport() == TransportProtocol.SOAP) {
            grantsXml = getSoapHandlerClient().retrieveCurrentGrants(userId);
        }
        else {
            grantsXml = getRestHandlerClient().retrieveCurrentGrants(userId);
        }

        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Grants.class)
            .unmarshalDocument(grantsXml);
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
    @Override
    public Grant retrieveGrant(final String userId, final String grantId)
        throws EscidocClientException, InternalClientException,
        TransportException {

        String grantXml = null;

        if (getTransport() == TransportProtocol.SOAP) {
            grantXml = getSoapHandlerClient().retrieveGrant(userId, grantId);
        }
        else {
            grantXml = getRestHandlerClient().retrieveGrant(userId, grantId);
        }

        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Grant.class)
            .unmarshalDocument(grantXml);
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
    @Override
    @Deprecated
    public UserAccounts retrieveUserAccounts(final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapHandlerClient().retrieveUserAccounts(
                    marshalTaskParam(taskParam));
        }
        else {
            xml =
                getRestHandlerClient().retrieveUserAccounts(
                    marshalTaskParam(taskParam));
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(UserAccounts.class)
            .unmarshalDocument(xml);

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
    @Override
    public SearchRetrieveResponse retrieveUserAccounts(
        final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveUserAccounts(filter);
        }
        else {
            xml = getRestHandlerClient().retrieveUserAccounts(filter);
        }
        return MarshallerFactory
            .getInstance(getTransport())
            .getMarshaller(SearchRetrieveResponse.class).unmarshalDocument(xml);
    }

    /**
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @Override
    @SuppressWarnings("rawtypes")
    public Collection<UserAccount> retrieveUserAccountsAsList(
        final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException {

        SearchRetrieveResponse res = retrieveUserAccounts(filter);
        Collection<UserAccount> results = new LinkedList<UserAccount>();

        for (Record record : res.getRecords()) {
            UserAccount result =
                getSRWResourceRecordData(record, UserAccount.class);
            if (result != null)
                results.add(result);
        }

        return results;
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
    @Override
    public ExplainResponse retrieveUserAccounts(final ExplainRequestType request)
        throws EscidocException, InternalClientException, TransportException {

        if (request == null)
            throw new IllegalArgumentException("request must not be null.");

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveUserAccounts(request);
        }
        else {
            xml = getRestHandlerClient().retrieveUserAccounts(request);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(ExplainResponse.class)
            .unmarshalDocument(xml);
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
                getSoapHandlerClient().retrieveGrants(
                    marshalTaskParam(taskParam));
        }
        else {
            xml =
                getRestHandlerClient().retrieveGrants(
                    marshalTaskParam(taskParam));
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Grants.class)
            .unmarshalDocument(xml);
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
    @Override
    public SearchRetrieveResponse retrieveGrants(
        final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveGrants(filter);
        }
        else {
            xml = getRestHandlerClient().retrieveGrants(filter);
        }
        return MarshallerFactory
            .getInstance(getTransport())
            .getMarshaller(SearchRetrieveResponse.class).unmarshalDocument(xml);
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
    @Override
    public ExplainResponse retrieveGrants(final ExplainRequestType filter)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveGrants(filter);
        }
        else {
            xml = getRestHandlerClient().retrieveGrants(filter);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(ExplainResponse.class)
            .unmarshalDocument(xml);
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
    @Deprecated
    public DateTime getLastModificationDate(final String id)
        throws EscidocException, InternalClientException, TransportException {

        if (getTransport() == TransportProtocol.SOAP) {
            return getSoapHandlerClient().getLastModificationDate(id);
        }
        else {
            return getRestHandlerClient().getLastModificationDate(id);
        }
    }

    @Override
    protected SoapUserAccountHandlerClient getSoapHandlerClientInstance()
        throws InternalClientException {
        return new SoapUserAccountHandlerClient(getServiceAddress());
    }

    @Override
    protected RestUserAccountHandlerClient getRestHandlerClientInstance()
        throws InternalClientException {
        return new RestUserAccountHandlerClient(getServiceAddress());
    }

}
