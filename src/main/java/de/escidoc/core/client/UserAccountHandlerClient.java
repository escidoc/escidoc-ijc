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

import static de.escidoc.core.common.Precondition.checkNotNull;
import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.net.URL;
import java.util.List;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface;
import de.escidoc.core.client.rest.RestUserAccountHandlerClient;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.aa.useraccount.Attribute;
import de.escidoc.core.resources.aa.useraccount.Attributes;
import de.escidoc.core.resources.aa.useraccount.Grant;
import de.escidoc.core.resources.aa.useraccount.Grants;
import de.escidoc.core.resources.aa.useraccount.Preference;
import de.escidoc.core.resources.aa.useraccount.Preferences;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.common.TaskParam;
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
public class UserAccountHandlerClient extends AbstractHandlerClient<RestUserAccountHandlerClient>
    implements UserAccountHandlerClientInterface {

    /**
     * 
     * @param serviceAddress
     */
    public UserAccountHandlerClient(final URL serviceAddress) {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @deprecated Use
     *             {@link UserAccountHandlerClient#UserAccountHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public UserAccountHandlerClient(final String serviceAddress) {
        super(serviceAddress);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.AbstractHandlerClient#getRestHandlerClientInstance
     * ()
     */
    @Override
    protected RestUserAccountHandlerClient getRestHandlerClientInstance() throws InternalClientException {
        return new RestUserAccountHandlerClient(getServiceAddress());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Createable#create(java.lang.Object
     * )
     */
    @Override
    public UserAccount create(final UserAccount userAccount) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(userAccount);

        final Marshaller<UserAccount> m = MarshallerFactory.getInstance().getMarshaller(UserAccount.class);

        final String xml = getClient().create(m.marshalDocument(userAccount));

        return m.unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Retrievable#retrieve(java.lang
     * .String)
     */
    @Override
    public UserAccount retrieve(final String id) throws EscidocException, InternalClientException, TransportException {

        checkNotNull(id);

        final String xml = getClient().retrieve(id);

        return MarshallerFactory.getInstance().getMarshaller(UserAccount.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Deletable#delete(java.lang.String)
     */
    @Override
    public void delete(final String id) throws EscidocException, InternalClientException, TransportException {

        checkNotNull(id);

        getClient().delete(id);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Updatable#update(java.lang.Object)
     */
    @Override
    public UserAccount update(final UserAccount userAccount) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(userAccount);

        return update(userAccount.getObjid(), userAccount);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Updatable#update(java.lang.String,
     * java.lang.Object)
     */
    @Override
    public UserAccount update(final String id, final UserAccount userAccount) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(id);
        checkNotNull(userAccount);

        final Marshaller<UserAccount> m = MarshallerFactory.getInstance().getMarshaller(UserAccount.class);

        final String xml = getClient().update(id, m.marshalDocument(userAccount));

        return m.unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * updatePassword(java.lang.String,
     * de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public void updatePassword(final String userId, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(userId);
        checkNotNull(taskParam);

        getClient().updatePassword(userId, marshalTaskParam(taskParam));

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#activate
     * (java.lang.String, de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public void activate(final String userId, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(userId);
        checkNotNull(taskParam);

        getClient().activate(userId, marshalTaskParam(taskParam));

    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * deactivate(java.lang.String, de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public void deactivate(final String userId, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(userId);
        checkNotNull(taskParam);

        getClient().deactivate(userId, marshalTaskParam(taskParam));

    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * retrieveCurrentUser()
     */
    @Override
    public UserAccount retrieveCurrentUser() throws EscidocException, InternalClientException, TransportException {

        final String xml = getClient().retrieveCurrentUser();

        return MarshallerFactory.getInstance().getMarshaller(UserAccount.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * createGrant(java.lang.String,
     * de.escidoc.core.resources.aa.useraccount.Grant)
     */
    @Override
    public Grant createGrant(final String userId, final Grant grant) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(userId);
        checkNotNull(grant);

        final Marshaller<Grant> m = MarshallerFactory.getInstance().getMarshaller(Grant.class);

        final String xml = getClient().createGrant(userId, m.marshalDocument(grant));

        return m.unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * revokeGrant(java.lang.String, java.lang.String,
     * de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public void revokeGrant(final String userId, final String grantId, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        checkNotNull(userId);
        checkNotNull(grantId);
        checkNotNull(taskParam);

        getClient().revokeGrant(userId, grantId, marshalTaskParam(taskParam));

    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * createPreference(java.lang.String,
     * de.escidoc.core.resources.aa.useraccount.Preference)
     */
    @Override
    public Preference createPreference(final String userId, final Preference preference) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(userId);
        checkNotNull(preference);

        final Marshaller<Preference> m = MarshallerFactory.getInstance().getMarshaller(Preference.class);

        final String xml = getClient().createPreference(userId, m.marshalDocument(preference));

        return m.unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * retrievePreference(java.lang.String, java.lang.String)
     */
    @Override
    public Preference retrievePreference(final String userId, final String name) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(userId);
        checkNotNull(name);

        final String xml = getClient().retrievePreference(userId, name);

        return MarshallerFactory.getInstance().getMarshaller(Preference.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * retrievePreferences(java.lang.String)
     */
    @Override
    public Preferences retrievePreferences(final String userId) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(userId);

        final String xml = getClient().retrievePreferences(userId);

        return MarshallerFactory.getInstance().getMarshaller(Preferences.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * updatePreference(java.lang.String,
     * de.escidoc.core.resources.aa.useraccount.Preference)
     */
    @Override
    public Preference updatePreference(final String userId, final Preference preference) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(userId);
        checkNotNull(preference);

        final Marshaller<Preference> m = MarshallerFactory.getInstance().getMarshaller(Preference.class);

        final String xml = getClient().updatePreference(userId, preference.getName(), m.marshalDocument(preference));

        return m.unmarshalDocument(xml);
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
     * @deprecated Signature convention: Use
     *             {@link UserAccountHandlerClient#deletePreference(String, String)}
     *             or
     *             {@link UserAccountHandlerClient#deletePreference(UserAccount, String)}
     *             instead.
     */
    @Deprecated
    public void deletePreference(final String userId, final Preference preference) throws EscidocException,
        InternalClientException, TransportException {

        deletePreference(userId, preference.getName());
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * deletePreference(java.lang.String, java.lang.String)
     */
    @Override
    public void deletePreference(final String userId, final String name) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(userId);
        checkNotNull(name);

        getClient().deletePreference(userId, name);

    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * createAttribute(java.lang.String,
     * de.escidoc.core.resources.aa.useraccount.Attribute)
     */
    @Override
    public Attribute createAttribute(final String userId, final Attribute attribute) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(userId);
        checkNotNull(attribute);

        final Marshaller<Attribute> m = MarshallerFactory.getInstance().getMarshaller(Attribute.class);

        final String xml = getClient().createAttribute(userId, m.marshalDocument(attribute));

        return m.unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * retrieveAttribute(java.lang.String, java.lang.String)
     */
    @Override
    public Attribute retrieveAttribute(final String userId, final String attributeId) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(userId);
        checkNotNull(attributeId);

        final String xml = getClient().retrieveAttribute(userId, attributeId);

        return MarshallerFactory.getInstance().getMarshaller(Attribute.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * retrieveAttributes(java.lang.String)
     */
    @Override
    public Attributes retrieveAttributes(final String userId) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(userId);

        final String xml = getClient().retrieveAttributes(userId);

        return MarshallerFactory.getInstance().getMarshaller(Attributes.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * updateAttribute(java.lang.String, java.lang.String,
     * de.escidoc.core.resources.aa.useraccount.Attribute)
     */
    @Override
    public Attribute updateAttribute(final String userId, final String attributeId, final Attribute attribute)
        throws EscidocException, InternalClientException, TransportException {

        checkNotNull(userId);
        checkNotNull(attributeId);
        checkNotNull(attribute);

        final Marshaller<Attribute> m = MarshallerFactory.getInstance().getMarshaller(Attribute.class);

        final String xml = getClient().updateAttribute(userId, attributeId, m.marshalDocument(attribute));

        return m.unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * updateAttribute(java.lang.String,
     * de.escidoc.core.resources.aa.useraccount.Attribute)
     */
    @Override
    public Attribute updateAttribute(final String userId, final Attribute attribute) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(attribute);

        return updateAttribute(userId, attribute.getObjid(), attribute);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * deleteAttribute(java.lang.String, java.lang.String)
     */
    @Override
    public void deleteAttribute(final String userId, final String attributeId) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(userId);
        checkNotNull(attributeId);

        getClient().deleteAttribute(userId, attributeId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * retrieveCurrentGrants(java.lang.String)
     */
    @Override
    public Grants retrieveCurrentGrants(final String userId) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(userId);

        final String xml = getClient().retrieveCurrentGrants(userId);

        return MarshallerFactory.getInstance().getMarshaller(Grants.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * retrieveGrant(java.lang.String, java.lang.String)
     */
    @Override
    public Grant retrieveGrant(final String userId, final String grantId) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(userId);
        checkNotNull(grantId);

        final String xml = getClient().retrieveGrant(userId, grantId);

        return MarshallerFactory.getInstance().getMarshaller(Grant.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * retrieveUserAccounts(gov.loc.www.zing.srw.SearchRetrieveRequestType)
     */
    @Override
    public SearchRetrieveResponse retrieveUserAccounts(final SearchRetrieveRequestType request)
        throws EscidocException, InternalClientException, TransportException {

        checkNotNull(request);

        final String xml = getClient().retrieveUserAccounts(request);

        return MarshallerFactory.getInstance().getMarshaller(SearchRetrieveResponse.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * retrieveUserAccountsAsList
     * (gov.loc.www.zing.srw.SearchRetrieveRequestType)
     */
    @Override
    public List<UserAccount> retrieveUserAccountsAsList(final SearchRetrieveRequestType request)
        throws EscidocException, InternalClientException, TransportException {

        return getSearchRetrieveResponseAsList(UserAccount.class, retrieveUserAccounts(request));
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * retrieveUserAccounts(gov.loc.www.zing.srw.ExplainRequestType)
     */
    @Override
    public ExplainResponse retrieveUserAccounts(final ExplainRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(request);

        final String xml = getClient().retrieveUserAccounts(request);

        return MarshallerFactory.getInstance().getMarshaller(ExplainResponse.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * retrieveGrants(gov.loc.www.zing.srw.SearchRetrieveRequestType)
     */
    @Override
    public SearchRetrieveResponse retrieveGrants(final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(request);

        final String xml = getClient().retrieveGrants(request);

        return MarshallerFactory.getInstance().getMarshaller(SearchRetrieveResponse.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * retrieveGrants(gov.loc.www.zing.srw.ExplainRequestType)
     */
    @Override
    public ExplainResponse retrieveGrants(final ExplainRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(request);

        final String xml = getClient().retrieveGrants(request);

        return MarshallerFactory.getInstance().getMarshaller(ExplainResponse.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * updatePassword(de.escidoc.core.resources.aa.useraccount.UserAccount,
     * de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public void updatePassword(final UserAccount user, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(user);

        updatePassword(user.getObjid(), taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#activate
     * (de.escidoc.core.resources.aa.useraccount.UserAccount,
     * de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public void activate(final UserAccount user, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(user);

        activate(user.getObjid(), taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * deactivate(de.escidoc.core.resources.aa.useraccount.UserAccount,
     * de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public void deactivate(final UserAccount user, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(user);

        deactivate(user.getObjid(), taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * retrieveCurrentGrants
     * (de.escidoc.core.resources.aa.useraccount.UserAccount)
     */
    @Override
    public Grants retrieveCurrentGrants(final UserAccount user) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(user);

        return retrieveCurrentGrants(user.getObjid());
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * createGrant(de.escidoc.core.resources.aa.useraccount.UserAccount,
     * de.escidoc.core.resources.aa.useraccount.Grant)
     */
    @Override
    public Grant createGrant(final UserAccount user, final Grant grant) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(user);

        return createGrant(user.getObjid(), grant);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * revokeGrant(de.escidoc.core.resources.aa.useraccount.UserAccount,
     * java.lang.String, de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public void revokeGrant(final UserAccount user, final String grantId, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        checkNotNull(user);

        revokeGrant(user.getObjid(), grantId, taskParam);

    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * retrieveGrant(de.escidoc.core.resources.aa.useraccount.UserAccount,
     * java.lang.String)
     */
    @Override
    public Grant retrieveGrant(final UserAccount user, final String grantId) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(user);

        return retrieveGrant(user.getObjid(), grantId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * retrieveGrantsAsList(gov.loc.www.zing.srw.SearchRetrieveRequestType)
     */
    @Override
    public List<Grant> retrieveGrantsAsList(final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        return getSearchRetrieveResponseAsList(Grant.class, retrieveGrants(request));
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * createAttribute(de.escidoc.core.resources.aa.useraccount.UserAccount,
     * de.escidoc.core.resources.aa.useraccount.Attribute)
     */
    @Override
    public Attribute createAttribute(final UserAccount user, final Attribute attribute) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(user);

        return createAttribute(user.getObjid(), attribute);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * retrieveAttribute(de.escidoc.core.resources.aa.useraccount.UserAccount,
     * java.lang.String)
     */
    @Override
    public Attribute retrieveAttribute(final UserAccount user, final String attributeId) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(user);

        return retrieveAttribute(user.getObjid(), attributeId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * retrieveAttributes(de.escidoc.core.resources.aa.useraccount.UserAccount)
     */
    @Override
    public Attributes retrieveAttributes(final UserAccount user) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(user);

        return retrieveAttributes(user.getObjid());
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * updateAttribute(de.escidoc.core.resources.aa.useraccount.UserAccount,
     * de.escidoc.core.resources.aa.useraccount.Attribute)
     */
    @Override
    public Attribute updateAttribute(final UserAccount user, final Attribute attribute) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(user);
        checkNotNull(attribute);

        return updateAttribute(user.getObjid(), attribute.getObjid(), attribute);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * updateAttribute(de.escidoc.core.resources.aa.useraccount.UserAccount,
     * de.escidoc.core.resources.aa.useraccount.Attribute)
     */
    @Override
    public Attribute updateAttribute(final UserAccount user, final String attributeId, final Attribute attribute)
        throws EscidocException, InternalClientException, TransportException {

        checkNotNull(user);

        return updateAttribute(user.getObjid(), attributeId, attribute);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * createPreference(de.escidoc.core.resources.aa.useraccount.UserAccount,
     * de.escidoc.core.resources.aa.useraccount.Preference)
     */
    @Override
    public Preference createPreference(final UserAccount user, final Preference preference) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(user);

        return createPreference(user.getObjid(), preference);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * retrievePreference(de.escidoc.core.resources.aa.useraccount.UserAccount,
     * java.lang.String)
     */
    @Override
    public Preference retrievePreference(final UserAccount user, final String name) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(user);

        return retrievePreference(user.getObjid(), name);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * retrievePreferences(de.escidoc.core.resources.aa.useraccount.UserAccount)
     */
    @Override
    public Preferences retrievePreferences(final UserAccount user) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(user);

        return retrievePreferences(user.getObjid());
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * updatePreference(de.escidoc.core.resources.aa.useraccount.UserAccount,
     * de.escidoc.core.resources.aa.useraccount.Preference)
     */
    @Override
    public Preference updatePreference(final UserAccount user, final Preference preference) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(user);

        return updatePreference(user.getObjid(), preference);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * deletePreference(de.escidoc.core.resources.aa.useraccount.UserAccount,
     * java.lang.String)
     */
    @Override
    public void deletePreference(final UserAccount user, final String name) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(user);

        deletePreference(user.getObjid(), name);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * deleteAttribute(de.escidoc.core.resources.aa.useraccount.UserAccount,
     * java.lang.String)
     */
    @Override
    public void deleteAttribute(final UserAccount user, final String attributeId) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(user);

        deleteAttribute(user.getObjid(), attributeId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface#
     * retrieveNamedAttributes(java.lang.String, java.lang.String)
     */
    @Override
    public Attributes retrieveNamedAttributes(final String userId, final String attrName) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(userId);
        checkNotNull(attrName);

        final String xml = getClient().retrieveNamedAttributes(userId, attrName);

        return MarshallerFactory.getInstance().getMarshaller(Attributes.class).unmarshalDocument(xml);
    }
}