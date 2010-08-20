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
import de.escidoc.core.client.interfaces.PolicyDecisionPointHandlerClientInterface;
import de.escidoc.core.client.soap.SoapPolicyDecisionPointHandlerClient;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.aa.pdp.Requests;
import de.escidoc.core.resources.aa.pdp.RequestsResults;

/**
 * This is the generic PolicyDecisionPointHandlerClient which binds the
 * transport specific classes. The transport specification is done via
 * properties configuration of the eSciDoc client.
 * 
 * @author SWA
 * 
 */
public class PolicyDecisionPointHandlerClient
    implements PolicyDecisionPointHandlerClientInterface {

    private SoapPolicyDecisionPointHandlerClient soapPolicyDecisionPointHandlerClient =
        null;

    private Authentication auth = null;

    /**
     * Create PolicyDecisionPointHandlerClient instance. The service protocol
     * (REST/SOAP/..) selected from the configuration. Default is SOAP.
     * 
     * @throws ClientException
     * 
     */
    public PolicyDecisionPointHandlerClient() throws EscidocException,
        InternalClientException, TransportException {

        // read service protocol from config or set as default SOAP
        this.soapPolicyDecisionPointHandlerClient =
            new SoapPolicyDecisionPointHandlerClient();
    }

    /**
     * See Interface for functional description.
     * 
     * @param container
     * @return
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#create(de.escidoc.core.resources.interfaces.container.ContainerInterface)
     */
    public RequestsResults evaluate(Requests requests) throws EscidocException,
        InternalClientException, TransportException {

        String xml =
            getSoapPolicyDecisionPointHandlerClient().evaluate(
                Factory.getMarshallerFactory(TransportProtocol.SOAP)
                	.getRequestsMarshaller().marshalDocument((Requests) requests));
        return Factory.getMarshallerFactory(TransportProtocol.SOAP)
        	.getRequestsResultsMarshaller().unmarshalDocument(xml);
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

        return getSoapPolicyDecisionPointHandlerClient()
            .getLastModificationDate(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.CrudHandlerInterface#login(java.lang
     * .String, java.lang.String, java.lang.String)
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

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.CrudHandlerInterface#logout()
     */
    @Deprecated
    public void logout() throws EscidocException, InternalClientException,
        TransportException {

        setHandle("");
    }

    /**
     * See Interface for functional description.
     * 
     * @param handle
     * @see de.escidoc.core.client.interfaces.BaseClientHandlerInterface#setHandle(java.lang.String)
     */
    public void setHandle(final String handle) {

        getSoapPolicyDecisionPointHandlerClient().setHandle(handle);
    }

    /**
     * @return the soapContainerHandlerClient
     */
    public SoapPolicyDecisionPointHandlerClient getSoapPolicyDecisionPointHandlerClient() {
        return soapPolicyDecisionPointHandlerClient;
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
        getSoapPolicyDecisionPointHandlerClient().setServiceAddress(address);
    }

}
