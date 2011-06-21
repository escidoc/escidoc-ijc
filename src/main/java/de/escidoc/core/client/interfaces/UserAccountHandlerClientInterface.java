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
package de.escidoc.core.client.interfaces;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.util.List;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.base.CrudService;
import de.escidoc.core.client.interfaces.base.HandlerService;
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
 * This class defines the signatures for the client handler wrapper classes
 * where the transport specific exceptions are mapped to internal client
 * internal exception.
 * 
 * @author SWA
 * 
 */
public interface UserAccountHandlerClientInterface extends HandlerService, CrudService<UserAccount> {

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
    void updatePassword(final String userId, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * 
     * @param user
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    void updatePassword(final UserAccount user, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * 
     * @param userId
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    void activate(final String userId, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException;

    /**
     * 
     * @param user
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    void activate(final UserAccount user, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException;

    /**
     * 
     * @param userId
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    void deactivate(final String userId, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException;

    /**
     * 
     * @param user
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    void deactivate(final UserAccount user, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * 
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    UserAccount retrieveCurrentUser() throws EscidocException, InternalClientException, TransportException;

    /*
     * Filter
     */

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
    ExplainResponse retrieveUserAccounts(final ExplainRequestType explain) throws EscidocException,
        InternalClientException, TransportException;

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
    SearchRetrieveResponse retrieveUserAccounts(final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    List<UserAccount> retrieveUserAccountsAsList(final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException;

    /*
     * Subresource current grants
     */

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
    Grants retrieveCurrentGrants(final String userId) throws EscidocException, InternalClientException,
        TransportException;

    /**
     * @param user
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Grants retrieveCurrentGrants(final UserAccount user) throws EscidocException, InternalClientException,
        TransportException;

    /*
     * Subresource grants
     */

    /**
     * @param userId
     * @param grant
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Grant createGrant(final String userId, final Grant grant) throws EscidocException, InternalClientException,
        TransportException;

    /**
     * @param user
     * @param grant
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Grant createGrant(final UserAccount user, final Grant grant) throws EscidocException, InternalClientException,
        TransportException;

    /**
     * @param userId
     * @param grantId
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    void revokeGrant(final String userId, final String grantId, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * @param user
     * @param grantId
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    void revokeGrant(final UserAccount user, final String grantId, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException;

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
    Grant retrieveGrant(final String userId, final String grantId) throws EscidocException, InternalClientException,
        TransportException;

    /**
     * 
     * @param user
     * @param grantId
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Grant retrieveGrant(final UserAccount user, final String grantId) throws EscidocException, InternalClientException,
        TransportException;

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
    ExplainResponse retrieveGrants(final ExplainRequestType filter) throws EscidocException, InternalClientException,
        TransportException;

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
    SearchRetrieveResponse retrieveGrants(final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    List<Grant> retrieveGrantsAsList(final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException;

    /*
     * Attributes
     */

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
    Attribute createAttribute(final String userId, final Attribute attribute) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * @param user
     * @param attribute
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Attribute createAttribute(final UserAccount user, final Attribute attribute) throws EscidocException,
        InternalClientException, TransportException;

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
    Attribute retrieveAttribute(final String userId, final String attributeId) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * 
     * @param user
     * @param attributeId
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Attribute retrieveAttribute(final UserAccount user, final String attributeId) throws EscidocException,
        InternalClientException, TransportException;

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
    Attributes retrieveAttributes(final String userId) throws EscidocException, InternalClientException,
        TransportException;

    /**
     * 
     * @param user
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     */
    Attributes retrieveAttributes(final UserAccount user) throws EscidocException, InternalClientException,
        TransportException;

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
    Attribute updateAttribute(final String userId, final Attribute attribute) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * Update Attribute of User Account.
     * 
     * @param userId
     * @param attributeId
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
    Attribute updateAttribute(final String userId, final String attributeId, final Attribute attribute)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * Update Attribute of User Account.
     * 
     * @param user
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
    Attribute updateAttribute(final UserAccount user, final Attribute attribute) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * Update Attribute of User Account.
     * 
     * @param user
     * @param attributeId
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
    Attribute updateAttribute(final UserAccount user, final String attributeId, final Attribute attribute)
        throws EscidocException, InternalClientException, TransportException;

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
    void deleteAttribute(final String userId, final String attributeId) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * 
     * @param user
     * @param attribute
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    void deleteAttribute(final UserAccount user, final String attributeId) throws EscidocException,
        InternalClientException, TransportException;

    /*
     * Preferences
     */

    /**
     * @param userId
     * @param preference
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Preference createPreference(final String userId, final Preference preference) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * @param user
     * @param preference
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Preference createPreference(final UserAccount user, final Preference preference) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * @param userId
     * @param name
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Preference retrievePreference(final String userId, final String name) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * @param user
     * @param name
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Preference retrievePreference(final UserAccount user, final String name) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * @param userId
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Preferences retrievePreferences(final String userId) throws EscidocException, InternalClientException,
        TransportException;

    /**
     * @param user
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Preferences retrievePreferences(final UserAccount user) throws EscidocException, InternalClientException,
        TransportException;

    /**
     * @param userId
     * @param preference
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Preference updatePreference(final String userId, final Preference preference) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * @param user
     * @param preference
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Preference updatePreference(final UserAccount user, final Preference preference) throws EscidocException,
        InternalClientException, TransportException;

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
    void deletePreference(final String userId, final String name) throws EscidocException, InternalClientException,
        TransportException;

    /**
     * @param user
     * @param name
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    void deletePreference(final UserAccount user, final String name) throws EscidocException, InternalClientException,
        TransportException;

    /**
     * @param userId
     * @param attrName
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Attributes retrieveNamedAttributes(String userId, String attrName) throws EscidocException,
        InternalClientException, TransportException;
}