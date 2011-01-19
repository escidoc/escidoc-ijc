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

import java.net.URL;

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
 * 
 * 
 * @author SWA
 * 
 */
public class ActionHandlerClient implements ActionHandlerClientInterface {

    private SoapActionHandlerClient soapActionHandlerClient = null;

    /**
     * Creates an ActionHandlerClient instance.
     * 
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public ActionHandlerClient() throws InternalClientException {
        this.soapActionHandlerClient = new SoapActionHandlerClient();
    }

    /**
     * Creates an ActionHandlerClient instance.
     * 
     * @param serviceAddress
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     */
    public ActionHandlerClient(final URL serviceAddress)
        throws InternalClientException {

        this.soapActionHandlerClient =
            new SoapActionHandlerClient(serviceAddress);
    }

    /**
     * Creates an ActionHandlerClient instance.
     * 
     * @param serviceAddress
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @deprecated Use {@link ActionHandlerClient#ActionHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public ActionHandlerClient(final String serviceAddress)
        throws InternalClientException {

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
     * @throws EscidocException
     * @throws TransportException
     * @throws InternalClientException
     * 
     * @deprecated Naming convention: Use
     *             {@link ActionHandlerClient#create(String, UnsecuredActions)}
     */
    @Deprecated
    public UnsecuredActions createUnsecuredActions(
        final String contextId, final UnsecuredActions actions)
        throws EscidocException, InternalClientException, TransportException {

        return create(contextId, actions);
    }

    /**
     * Retrieve unsecured actions.
     * 
     * @param contextId
     *            Objid of Context.
     * @return UnsecuredActions
     * @throws TransportException
     * @throws InternalClientException
     * @throws EscidocException
     * @deprecated Naming convention: Use
     *             {@link ActionHandlerClient#retrieve(String)} instead.
     */
    @Deprecated
    public UnsecuredActions retrieveUnsecuredActions(final String contextId)
        throws EscidocException, InternalClientException, TransportException {

        return retrieve(contextId);
    }

    /**
     * Delete unsecured actions.
     * 
     * @param contextId
     *            Objid of Context.
     * @throws InternalClientException
     * @throws TransportException
     * @throws EscidocException
     * @deprecated Naming convention: Use
     *             {@link ActionHandlerClient#delete(String)} instead.
     */
    @Deprecated
    public void deleteUnsecuredActions(final String contextId)
        throws EscidocException, TransportException, InternalClientException {

        delete(contextId);
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
     * Set Login-Handle.
     * 
     * @param handle
     *            Login-Handle
     */
    @Override
    public void setHandle(final String handle) {
        getSoapActionHandlerClient().setHandle(handle);
    }

    /**
     * 
     * @return The handle used for authentication by this client.
     */
    @Override
    public String getHandle() {
        return getSoapActionHandlerClient().getHandle();
    }

    /**
     * 
     * @return The serviceAddress of this client.
     */
    @Override
    public URL getServiceAddress() {
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

    /**
     * @param tp
     */
    @Override
    public void setTransport(final TransportProtocol tp) {
        // ignore

    }

    @Override
    public TransportProtocol getTransport() {
        return TransportProtocol.SOAP;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.ActionHandlerClientInterface#create
     * (java.lang.String, de.escidoc.core.resources.aa.actions.UnsecuredActions)
     */
    @Override
    public UnsecuredActions create(
        final String contextId, final UnsecuredActions actions)
        throws EscidocException, InternalClientException, TransportException {

        Marshaller<UnsecuredActions> m =
            MarshallerFactory
                .getInstance(TransportProtocol.SOAP).getMarshaller(
                    UnsecuredActions.class);
        String xml =
            getSoapActionHandlerClient().createUnsecuredActions(contextId,
                m.marshalDocument(actions));
        return m.unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.ActionHandlerClientInterface#retrieve
     * (java.lang.String)
     */
    @Override
    public UnsecuredActions retrieve(final String contextId)
        throws EscidocException, InternalClientException, TransportException {
        String xml =
            getSoapActionHandlerClient().retrieveUnsecuredActions(contextId);
        return MarshallerFactory
            .getInstance(TransportProtocol.SOAP)
            .getMarshaller(UnsecuredActions.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.ActionHandlerClientInterface#delete
     * (java.lang.String)
     */
    @Override
    public void delete(final String contextId) throws EscidocException,
        TransportException, InternalClientException {

        getSoapActionHandlerClient().deleteUnsecuredActions(contextId);
    }
}