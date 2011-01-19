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

import de.escidoc.core.client.exceptions.EscidocClientException;
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
public interface UserAccountHandlerClientInterface
    extends HandlerService, CrudService<UserAccount> {

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
    void updatePassword(final String userId, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * 
     * @param user
     * @param taskParam
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    void updatePassword(final UserAccount user, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * 
     * @param userId
     * @param taskParam
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    void activate(final String userId, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * 
     * @param user
     * @param taskParam
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    void activate(final UserAccount user, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * 
     * @param userId
     * @param taskParam
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    void deactivate(final String userId, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * 
     * @param user
     * @param taskParam
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    void deactivate(final UserAccount user, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * 
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    UserAccount retrieveCurrentUser() throws EscidocClientException,
        InternalClientException, TransportException;

    /*
     * Filter
     */

    /**
     * @param explain
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    ExplainResponse retrieveUserAccounts(final ExplainRequestType explain)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    SearchRetrieveResponse retrieveUserAccounts(
        final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    List<UserAccount> retrieveUserAccountsAsList(
        final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException;

    /*
     * Subresource current grants
     */

    /**
     * @param userId
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    Grants retrieveCurrentGrants(final String userId)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * @param user
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    Grants retrieveCurrentGrants(final UserAccount user)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /*
     * Subresource grants
     */

    /**
     * @param userId
     * @param grant
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    Grant createGrant(final String userId, final Grant grant)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * @param user
     * @param grant
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    Grant createGrant(final UserAccount user, final Grant grant)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * @param userId
     * @param grantId
     * @param taskParam
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    void revokeGrant(
        final String userId, final String grantId, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * @param user
     * @param grantId
     * @param taskParam
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    void revokeGrant(
        final UserAccount user, final String grantId, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * 
     * @param userId
     * @param grantId
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    Grant retrieveGrant(final String userId, final String grantId)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * 
     * @param user
     * @param grantId
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    Grant retrieveGrant(final UserAccount user, final String grantId)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    ExplainResponse retrieveGrants(final ExplainRequestType filter)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    SearchRetrieveResponse retrieveGrants(final SearchRetrieveRequestType filter)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    List<Grant> retrieveGrantsAsList(final SearchRetrieveRequestType filter)
        throws EscidocException, InternalClientException, TransportException;

    /*
     * Attributes
     */

    /**
     * @param userId
     * @param attribute
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    Attribute createAttribute(final String userId, final Attribute attribute)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * @param user
     * @param attribute
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    Attribute createAttribute(final UserAccount user, final Attribute attribute)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * @param userId
     * @param attributeId
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    Attribute retrieveAttribute(final String userId, final String attributeId)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * 
     * @param user
     * @param attributeId
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    Attribute retrieveAttribute(final UserAccount user, final String attributeId)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * 
     * @param userId
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     */
    Attributes retrieveAttributes(final String userId)
        throws EscidocClientException, InternalClientException;

    /**
     * 
     * @param user
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     */
    Attributes retrieveAttributes(final UserAccount user)
        throws EscidocClientException, InternalClientException;

    /**
     * 
     * @param userId
     * @param attribute
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    Attribute updateAttribute(final String userId, final Attribute attribute)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * 
     * @param user
     * @param attribute
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    Attribute updateAttribute(final UserAccount user, final Attribute attribute)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * 
     * @param userId
     * @param attribute
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    void deleteAttribute(final String userId, final String attributeId)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * 
     * @param user
     * @param attribute
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    void deleteAttribute(final UserAccount user, final String attributeId)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /*
     * Preferences
     */

    /**
     * @param userId
     * @param preference
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    Preference createPreference(final String userId, final Preference preference)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * @param user
     * @param preference
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    Preference createPreference(
        final UserAccount user, final Preference preference)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * @param userId
     * @param name
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    Preference retrievePreference(final String userId, final String name)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * @param user
     * @param name
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    Preference retrievePreference(final UserAccount user, final String name)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * @param userId
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    Preferences retrievePreferences(final String userId)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * @param user
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    Preferences retrievePreferences(final UserAccount user)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * @param userId
     * @param preference
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    Preference updatePreference(final String userId, final Preference preference)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * @param user
     * @param preference
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    Preference updatePreference(
        final UserAccount user, final Preference preference)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * @param userId
     * @param name
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    void deletePreference(final String userId, final String name)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * @param user
     * @param name
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    void deletePreference(final UserAccount user, final String name)
        throws EscidocClientException, InternalClientException,
        TransportException;
}