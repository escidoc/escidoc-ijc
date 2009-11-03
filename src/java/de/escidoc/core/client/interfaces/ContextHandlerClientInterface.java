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

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.om.MemberList;
import de.escidoc.core.resources.om.context.AdminDescriptor;
import de.escidoc.core.resources.om.context.AdminDescriptors;

/**
 * This class defines the signatures for the client handler wrapper classes
 * where the transport specific exceptions are mapped to internal client
 * internal exception.
 * 
 * @param <Context>
 *            Context
 * @author SWA
 * 
 */
public interface ContextHandlerClientInterface<Context>
    extends ResourceHandlerInterface<Context> {

    /**
     * Open Context.
     * 
     * @param contextId
     *            objid of Context
     * @param taskParam
     *            Task parameter
     * @return result of open method
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Result open(final String contextId, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * Close Context.
     * 
     * @param contextId
     *            objid of Context.
     * @param taskParam
     *            Task parameter
     * @return result of close method
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Result close(final String contextId, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException;

    /*
     * Sub resource methods
     */

    /**
     * 
     * @param contextId
     *            objid of Context.
     * @return AdminDescriptors
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    AdminDescriptors retrieveAdminDescriptors(final String contextId)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param contextId
     *            objid of Context.
     * @param name
     *            Name of admin descriptor
     * @return AdminDescriptor
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    AdminDescriptor retrieveAdminDescriptor(
        final String contextId, final String name) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * 
     * @param id
     *            objid of Context.
     * @param taskParam
     * @return MemberList
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    MemberList retrieveMembers(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException;

}
