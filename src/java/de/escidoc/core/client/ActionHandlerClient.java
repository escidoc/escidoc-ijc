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
import de.escidoc.core.client.soap.SoapPolicyDecisionPointHandlerClient;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.aa.actions.UnsecuredActions;
import de.escidoc.core.resources.aa.pdp.Requests;
import de.escidoc.core.resources.aa.pdp.RequestsResults;

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

    /**
     * Create ContainersoapContainerHandlerClient instance. The service protocol
     * (REST/SOAP/..) selected from the configuration. Default is SOAP.
     * 
     * @throws ClientException
     * 
     */
    public ActionHandlerClient() throws EscidocException,
        InternalClientException, TransportException {

        // read service protocol from config or set as default SOAP
        this.soapActionHandlerClient = new SoapActionHandlerClient();
    }

    /**
     * 
     */
    public UnsecuredActions createUnsecuredActions(
        final String contextId, final UnsecuredActions actions)
        throws EscidocClientException, InternalClientException,
        TransportException {
        String xml =
            getSoapActionHandlerClient().createUnsecuredActions(
                contextId,
                Factory.getUnsecuredActionsMarshaller().marshalDocument(
                    (UnsecuredActions) actions));
        return Factory.getUnsecuredActionsMarshaller().unmarshalDocument(xml);

    }

    /**
     * 
     */
    public UnsecuredActions retrieveUnsecuredActions(final String contextId)
        throws EscidocClientException, InternalClientException,
        TransportException {
        String xml =
            getSoapActionHandlerClient().retrieveUnsecuredActions(contextId);
        return Factory.getUnsecuredActionsMarshaller().unmarshalDocument(xml);
    }

    /**
     * 
     */
    public void deleteUnsecuredActions(final String contextId)
        throws EscidocClientException, InternalClientException,
        TransportException {
        getSoapActionHandlerClient().deleteUnsecuredActions(contextId);

    }

    /**
     * See Interface for functional description.
     * 
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#getLastModificationDate(java.lang.String)
     */
    public DateTime getLastModificationDate(final String id)
        throws EscidocException, InternalClientException, TransportException {

        return getSoapActionHandlerClient().getLastModificationDate(id);
    }

    /**
     * See Interface for functional description.
     * 
     * @param handle
     * @see de.escidoc.core.client.interfaces.BaseClientHandlerInterface#setHandle(java.lang.String)
     */
    public void setHandle(final String handle) {

        getSoapActionHandlerClient().setHandle(handle);
    }

    /**
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
