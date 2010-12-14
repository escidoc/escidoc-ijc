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

import org.joda.time.DateTime;

import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.ActionHandlerClientInterface;
import de.escidoc.core.client.soap.SoapActionHandlerClient;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.aa.actions.UnsecuredActions;

/**
 * This is the generic ContainerSoapContainerHandlerClient which binds the
 * transport specific classes. The transport specification is done via
 * properties configuration of the eSciDoc client.
 * 
 * FIXME: Documentation invalid. TODO: SOAP only?
 * 
 * @author SWA
 * 
 */
public class ActionHandlerClient implements ActionHandlerClientInterface {

    private SoapActionHandlerClient soapActionHandlerClient = null;

    /**
     * Create ActionHandlerClient instance. The service protocol (REST/SOAP/..)
     * selected from the configuration. Default is SOAP.
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
        this.soapActionHandlerClient = new SoapActionHandlerClient();
    }

    /**
     * Create ActionHandlerClient instance. The service protocol (REST/SOAP/..)
     * selected from the configuration. Default is SOAP.
     * 
     * @param serviceAddress
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public ActionHandlerClient(final String serviceAddress)
        throws EscidocException, InternalClientException, TransportException {
        this.soapActionHandlerClient =
            new SoapActionHandlerClient(serviceAddress);
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
    @Override
    public UnsecuredActions createUnsecuredActions(
        final String contextId, final UnsecuredActions actions)
        throws EscidocClientException {

        Marshaller<UnsecuredActions> m =
            MarshallerFactory
                .getInstance(TransportProtocol.SOAP).getMarshaller(
                    UnsecuredActions.class);
        String xml =
            getSoapActionHandlerClient().createUnsecuredActions(contextId,
                m.marshalDocument(actions));
        return m.unmarshalDocument(xml);
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
    @Override
    public UnsecuredActions retrieveUnsecuredActions(final String contextId)
        throws EscidocClientException {
        String xml =
            getSoapActionHandlerClient().retrieveUnsecuredActions(contextId);
        return MarshallerFactory
            .getInstance(TransportProtocol.SOAP)
            .getMarshaller(UnsecuredActions.class).unmarshalDocument(xml);
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
    @Override
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
    @Deprecated
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

        return getSoapActionHandlerClient().login(serviceAddress, username,
            password);
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
     * 
     * @return The handle used for authentication by this client.
     */
    public String getHandle() {
        return getSoapActionHandlerClient().getHandle();
    }

    /**
     * 
     * @return The serviceAddress of this client.
     */
    public String getServiceAddress() {
        return getSoapActionHandlerClient().getServiceAddress();
    }

    /**
     * Get SOAP Action Handler Client.
     * 
     * @return the soapContainerHandlerClient
     */
    public SoapActionHandlerClient getSoapActionHandlerClient() {
        return soapActionHandlerClient;
    }

}
