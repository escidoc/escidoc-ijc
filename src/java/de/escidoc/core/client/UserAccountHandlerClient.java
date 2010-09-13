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
import de.escidoc.core.common.jibx.Factory;
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
import de.escidoc.core.resources.sb.search.records.UserAccountRecord;

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

    private Authentication auth = null;

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
            Factory
                .getMarshallerFactory(getTransport())
                .getUserAccountMarshaller().marshalDocument(userAccount);

        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().create(userAccountString);
        }
        else {
            xml = getRestHandlerClient().create(userAccountString);

        }
        return Factory
            .getMarshallerFactory(getTransport()).getUserAccountMarshaller()
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
    public UserAccount retrieve(final String id) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieve(id);
        }
        else {
            xml = getRestHandlerClient().retrieve(id);
        }
        return Factory
            .getMarshallerFactory(getTransport()).getUserAccountMarshaller()
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
    public UserAccount update(final UserAccount userAccount)
        throws EscidocException, InternalClientException, TransportException {

        String requestXML =
            Factory
                .getMarshallerFactory(getTransport())
                .getUserAccountMarshaller().marshalDocument(userAccount);
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
        return Factory
            .getMarshallerFactory(getTransport()).getUserAccountMarshaller()
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
    public UserAccount retrieveCurrentUser() throws EscidocClientException,
        InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveCurrentUser();
        }
        else {
            xml = getRestHandlerClient().retrieveCurrentUser();
        }
        return Factory
            .getMarshallerFactory(getTransport()).getUserAccountMarshaller()
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
    public Grant createGrant(final String userId, final Grant grant)
        throws EscidocClientException, InternalClientException,
        TransportException {

        String grantXml =
            Factory
                .getMarshallerFactory(getTransport()).getGrantMarshaller()
                .marshalDocument(grant);

        if (getTransport() == TransportProtocol.SOAP) {
            grantXml = getSoapHandlerClient().createGrant(userId, grantXml);
        }
        else {
            grantXml = getRestHandlerClient().createGrant(userId, grantXml);
        }

        return Factory
            .getMarshallerFactory(getTransport()).getGrantMarshaller()
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
    public Preference createPreference(
        final String userId, final Preference preference)
        throws EscidocClientException, InternalClientException,
        TransportException {

        String preferenceXml =
            Factory
                .getMarshallerFactory(getTransport()).getPreferenceMarshaller()
                .marshalDocument(preference);

        if (getTransport() == TransportProtocol.SOAP) {
            preferenceXml =
                getSoapHandlerClient().createPreference(userId, preferenceXml);
        }
        else {
            preferenceXml =
                getRestHandlerClient().createPreference(userId, preferenceXml);
        }

        return Factory
            .getMarshallerFactory(getTransport()).getPreferenceMarshaller()
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

        return Factory
            .getMarshallerFactory(getTransport()).getPreferenceMarshaller()
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

        return Factory
            .getMarshallerFactory(getTransport()).getPreferencesMarshaller()
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
    public Preference updatePreference(
        final String userId, final Preference preference)
        throws EscidocClientException, InternalClientException,
        TransportException {

        String preferenceXml =
            Factory
                .getMarshallerFactory(getTransport()).getPreferenceMarshaller()
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

        return Factory
            .getMarshallerFactory(getTransport()).getPreferenceMarshaller()
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
    public Attribute createAttribute(
        final String userId, final Attribute attribute)
        throws EscidocClientException, InternalClientException,
        TransportException {

        String attributeXml =
            Factory
                .getMarshallerFactory(getTransport()).getAttributeMarshaller()
                .marshalDocument(attribute);

        if (getTransport() == TransportProtocol.SOAP) {
            attributeXml =
                getSoapHandlerClient().createAttribute(userId, attributeXml);
        }
        else {
            attributeXml =
                getRestHandlerClient().createAttribute(userId, attributeXml);
        }

        return Factory
            .getMarshallerFactory(getTransport()).getAttributeMarshaller()
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

        return Factory
            .getMarshallerFactory(getTransport()).getAttributeMarshaller()
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

        return Factory
            .getMarshallerFactory(getTransport()).getAttributesMarshaller()
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
    public Attribute updateAttribute(
        final String userId, final Attribute attribute)
        throws EscidocClientException, InternalClientException,
        TransportException {

        String attributeXml =
            Factory
                .getMarshallerFactory(getTransport()).getAttributeMarshaller()
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

        return Factory
            .getMarshallerFactory(getTransport()).getAttributeMarshaller()
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

        return Factory
            .getMarshallerFactory(getTransport()).getGrantsMarshaller()
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

        return Factory
            .getMarshallerFactory(getTransport()).getGrantMarshaller()
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
        return Factory
            .getMarshallerFactory(getTransport())
            .getUserAccountListMarshaller().unmarshalDocument(xml);

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
        return Factory
            .getMarshallerFactory(getTransport())
            .getSearchRetrieveResponseMarshaller().unmarshalDocument(xml);
    }

    /**
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @SuppressWarnings("rawtypes")
    public Collection<UserAccount> retrieveUserAccountsAsList(
        final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException {

        SearchRetrieveResponse res = retrieveUserAccounts(filter);
        Collection<UserAccount> results = new LinkedList<UserAccount>();

        for (Record record : res.getRecords()) {
            if (record instanceof UserAccountRecord) {
                UserAccountRecord uRecord = (UserAccountRecord) record;
                UserAccount result = uRecord.getRecordData();
                if (result != null)
                    results.add(result);
            }
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
    public ExplainResponse retrieveUserAccounts(final ExplainRequestType filter)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveUserAccounts(filter);
        }
        else {
            xml = getRestHandlerClient().retrieveUserAccounts(filter);
        }
        return Factory
            .getMarshallerFactory(getTransport())
            .getExplainResponseMarshaller().unmarshalDocument(xml);
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
        return Factory
            .getMarshallerFactory(getTransport()).getGrantsMarshaller()
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
        return Factory
            .getMarshallerFactory(getTransport())
            .getSearchRetrieveResponseMarshaller().unmarshalDocument(xml);
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
    public ExplainResponse retrieveGrants(final ExplainRequestType filter)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveGrants(filter);
        }
        else {
            xml = getRestHandlerClient().retrieveGrants(filter);
        }
        return Factory
            .getMarshallerFactory(getTransport())
            .getExplainResponseMarshaller().unmarshalDocument(xml);
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

        if (getTransport() == TransportProtocol.SOAP) {
            return getSoapHandlerClient().getLastModificationDate(id);
        }
        else {
            return getRestHandlerClient().getLastModificationDate(id);
        }
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

    @Override
    protected SoapUserAccountHandlerClient getSoapHandlerClientInstance()
        throws InternalClientException {
        return new SoapUserAccountHandlerClient();
    }

    @Override
    protected RestUserAccountHandlerClient getRestHandlerClientInstance()
        throws InternalClientException {
        return new RestUserAccountHandlerClient();
    }

}
