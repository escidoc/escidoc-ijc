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

import java.io.IOException;

import org.joda.time.DateTime;

import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.ActionHandlerClientInterface;
import de.escidoc.core.client.soap.SoapActionHandlerClient;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.aa.actions.UnsecuredActions;

/**
 * This is the generic ContainerSoapContainerHandlerClient which binds the
 * transport specific classes. The transport specification is done via
 * properties configuration of the eSciDoc client.
 * 
 * @author SWA
 * 
 */
public class ActionHandlerClient implements ActionHandlerClientInterface {

    private SoapActionHandlerClient soapActionHandlerClient = null;

    private Authentication auth = null;

    /**
     * Create ActionHandlerClient instance. The service protocol
     * (REST/SOAP/..) selected from the configuration. Default is SOAP.
     * 
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public ActionHandlerClient() throws EscidocException,
        InternalClientException, TransportException {

        // read service protocol from config or set as default SOAP
        this.soapActionHandlerClient = new SoapActionHandlerClient();
    }

    /**
     * Create unsecured actions.
     * 
     * @param contextId
     *            Objid of Context.
     * @param actions
     *            UnsecuredActions
     * @return UnsecuredActions
     * 
     * @throws EscidocClientException
     *             Thrown if an exception from framework is received.
     */
    public UnsecuredActions createUnsecuredActions(
        final String contextId, final UnsecuredActions actions)
        throws EscidocClientException {

        String xml =
            getSoapActionHandlerClient().createUnsecuredActions(
                contextId,
                Factory.getUnsecuredActionsMarshaller().marshalDocument(
                    (UnsecuredActions) actions));
        return Factory.getUnsecuredActionsMarshaller().unmarshalDocument(xml);

    }

    /**
     * Retrieve unsecured actions.
     * 
     * @param contextId
     *            Objid of Context.
     * @return UnsecuredActions
     * 
     * @throws EscidocClientException
     *             Thrown if an exception from framework is received.
     */
    public UnsecuredActions retrieveUnsecuredActions(final String contextId)
        throws EscidocClientException {
        String xml =
            getSoapActionHandlerClient().retrieveUnsecuredActions(contextId);
        return Factory.getUnsecuredActionsMarshaller().unmarshalDocument(xml);
    }

    /**
     * Delete unsecured actions.
     * 
     * @param contextId
     *            Objid of Context.
     * 
     * @throws EscidocClientException
     *             Thrown if an exception from framework is received.
     */
    public void deleteUnsecuredActions(final String contextId)
        throws EscidocClientException {
        getSoapActionHandlerClient().deleteUnsecuredActions(contextId);
    }

    /**
     * See Interface for functional description.
     * 
     * @param id
     *            Objid
     * @return last-modification-date of resource.
     * @throws EscidocClientException
     *             Thrown if an exception from framework is received.
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#getLastModificationDate(java.lang.String)
     */
    public DateTime getLastModificationDate(final String id)
        throws EscidocClientException {

        return getSoapActionHandlerClient().getLastModificationDate(id);
    }

    /**
     * Login.
     * 
     * @param serviceAddress
     *            URL of framework
     * @param username
     *            Username/ID
     * @param password
     *            Password
     * @return Login-Handle.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
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

    /**
     * Logout.
     * 
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Deprecated
    public void logout() throws EscidocException, InternalClientException,
        TransportException {

        setHandle("");
    }

    /**
     * Set Login-Handle.
     * 
     * @param handle
     *            Login-Handle
     */
    public void setHandle(final String handle) {

        getSoapActionHandlerClient().setHandle(handle);
    }

    /**
     * Get SOAP Action Handler Client.
     * 
     * @return the soapContainerHandlerClient
     */
    public SoapActionHandlerClient getSoapActionHandlerClient() {
        return soapActionHandlerClient;
    }

    /**
     * Set the service endpoint address.
     * 
     * @param address
     *            URL of the service endpoint.
     * @throws InternalClientException
     *             Thrown if URL is not valid.
     */
    public void setServiceAddress(final String address)
        throws InternalClientException {
        getSoapActionHandlerClient().setServiceAddress(address);
    }

}
