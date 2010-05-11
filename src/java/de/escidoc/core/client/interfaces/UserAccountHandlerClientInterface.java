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

import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.resources.aa.useraccount.Grants;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.aa.useraccount.UserAccounts;
import de.escidoc.core.resources.common.TaskParam;

/**
 * This class defines the signatures for the client handler wrapper classes
 * where the transport specific exceptions are mapped to internal client
 * internal exception.
 * 
 * @author SWA
 * 
 */
public interface UserAccountHandlerClientInterface<T>
    extends CrudHandlerInterface<T> {

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

    //
    // Subresource - current grants
    //

    Grants retrieveCurrentGrants(final String userId)
        throws EscidocClientException, InternalClientException,
        TransportException;

    //
    // Subresource - grant
    //

    UserAccounts retrieveUserAccounts(final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

}
