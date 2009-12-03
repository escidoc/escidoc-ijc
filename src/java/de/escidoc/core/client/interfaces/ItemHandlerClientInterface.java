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
import de.escidoc.core.resources.common.Relations;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.om.item.ItemList;
import de.escidoc.core.resources.om.item.component.Component;

/**
 * This class defines the signatures for the client handler wrapper classes
 * where the transport specific exceptions are mapped to internal client
 * internal exception.
 * 
 * @author SWA
 * @param <Item>
 */
public interface ItemHandlerClientInterface<Item>
    extends VersionableResourceHandlerInterface<Item> {

    /*
     * lock methods
     */

    Result lock(final String id, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    Result lock(final Item item, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    Result unlock(final String id, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    Result unlock(final Item item, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    ItemList retrieveItems(final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /*
     * Assign PID methods
     */
    Result assignContentPid(
        final String id, final String componentId, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    Result assignContentPid(
        final Item item, final String componentId, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    Result assignVersionPid(final String id, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    Result assignVersionPid(final Item item, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /*
     * sub-resources (comming later)
     */
    Component createComponent(final String id, Component component)
        throws EscidocClientException, InternalClientException,
        TransportException;

    Component createComponent(final Item item, final Component component)
        throws EscidocClientException, InternalClientException,
        TransportException;

    Relations retrieveRelations(final String id) throws EscidocClientException,
        InternalClientException, TransportException;
}
