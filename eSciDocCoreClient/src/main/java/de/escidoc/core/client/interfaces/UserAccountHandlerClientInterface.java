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

import java.util.Collection;

import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.resources.aa.useraccount.Attribute;
import de.escidoc.core.resources.aa.useraccount.Attributes;
import de.escidoc.core.resources.aa.useraccount.Grant;
import de.escidoc.core.resources.aa.useraccount.Grants;
import de.escidoc.core.resources.aa.useraccount.Preference;
import de.escidoc.core.resources.aa.useraccount.Preferences;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.aa.useraccount.UserAccounts;
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
    extends CrudHandlerInterface<UserAccount> {

    void updatePassword(final String userId, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    void activate(final String userId, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    void deactivate(final String userId, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    UserAccount retrieveCurrentUser() throws EscidocClientException,
        InternalClientException, TransportException;

    /*
     * Filter
     */

    @Deprecated
    UserAccounts retrieveUserAccounts(final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    ExplainResponse retrieveUserAccounts(final ExplainRequestType explain)
        throws EscidocException, InternalClientException, TransportException;

    SearchRetrieveResponse retrieveUserAccounts(
        final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException;

    Collection<UserAccount> retrieveUserAccountsAsList(
        final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException;

    /*
     * Subresource current grants
     */

    Grants retrieveCurrentGrants(final String userId)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /*
     * Subresource grants
     */

    Grant createGrant(final String userId, final Grant grant)
        throws EscidocClientException, InternalClientException,
        TransportException;

    void revokeGrant(
        final String userId, final String grantId, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    Grant retrieveGrant(final String userId, final String grantId)
        throws EscidocClientException, InternalClientException,
        TransportException;

    ExplainResponse retrieveGrants(final ExplainRequestType filter)
        throws EscidocException, InternalClientException, TransportException;

    SearchRetrieveResponse retrieveGrants(final SearchRetrieveRequestType filter)
        throws EscidocException, InternalClientException, TransportException;

    /*
     * Attributes
     */

    Attribute createAttribute(final String userId, final Attribute attribute)
        throws EscidocClientException, InternalClientException,
        TransportException;

    Attribute retrieveAttribute(final String userId, final String attributeId)
        throws EscidocClientException, InternalClientException,
        TransportException;

    Attributes retrieveAttributes(final String userId)
        throws EscidocClientException, InternalClientException;

    Attribute updateAttribute(final String userId, final Attribute attribute)
        throws EscidocClientException, InternalClientException,
        TransportException;

    void deleteAttribute(final String userId, final Attribute attribute)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /*
     * Preferences
     */

    Preference createPreference(final String userId, final Preference preference)
        throws EscidocClientException, InternalClientException,
        TransportException;

    Preference retrievePreference(final String userId, final String name)
        throws EscidocClientException, InternalClientException,
        TransportException;

    Preferences retrievePreferences(final String userId)
        throws EscidocClientException, InternalClientException,
        TransportException;

    Preference updatePreference(final String userId, final Preference preference)
        throws EscidocClientException, InternalClientException,
        TransportException;

    void deletePreference(final String userId, final Preference preference)
        throws EscidocClientException, InternalClientException,
        TransportException;

    void deletePreference(final String userId, final String name)
        throws EscidocClientException, InternalClientException,
        TransportException;
}
