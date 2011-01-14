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
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.base.HandlerService;
import de.escidoc.core.resources.aa.actions.UnsecuredActions;

/**
 * This interface defines the signatures for the client handler wrapper classes
 * where the transport specific exceptions are mapped to internal client
 * exception.
 * 
 * @author SWA
 * 
 */
public interface ActionHandlerClientInterface extends HandlerService {

    /**
     * 
     * @param contextId
     * @param actions
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    UnsecuredActions create(
        final String contextId, final UnsecuredActions actions)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param contextId
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    UnsecuredActions retrieve(final String contextId) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * 
     * @param contextId
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    void delete(final String contextId) throws EscidocException,
        TransportException, InternalClientException;

}
