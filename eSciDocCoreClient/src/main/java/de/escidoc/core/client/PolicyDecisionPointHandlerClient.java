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
import de.escidoc.core.client.rest.RestPolicyDecisionPointHandlerClient;
import de.escidoc.core.client.soap.SoapPolicyDecisionPointHandlerClient;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.aa.pdp.Requests;
import de.escidoc.core.resources.aa.pdp.Results;

/**
 * This is the generic PolicyDecisionPointHandlerClient which binds the
 * transport specific classes. The transport specification is done via
 * properties configuration of the eSciDoc client.
 * 
 * @author SWA
 * 
 */
public class PolicyDecisionPointHandlerClient
    extends
    AbstractHandlerClient<SoapPolicyDecisionPointHandlerClient, RestPolicyDecisionPointHandlerClient>
    implements PolicyDecisionPointHandlerClientInterface {

    /**
     * 
     */
    public PolicyDecisionPointHandlerClient() {
        super();
    }

    /**
     * 
     * @param serviceAddress
     */
    public PolicyDecisionPointHandlerClient(final String serviceAddress) {
        super(serviceAddress);
    }

    /**
     * See Interface for functional description.
     * 
     * @param container
     * @return
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#create(de.escidoc.core.resources.interfaces.container.ContainerInterface)
     */
    @Override
    public Results evaluate(final Requests requests) throws EscidocException,
        InternalClientException, TransportException {

        if (requests == null)
            throw new IllegalArgumentException("requests must not be null.");

        String xml =
            MarshallerFactory
                .getInstance(getTransport()).getMarshaller(Requests.class)
                .marshalDocument(requests);

        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().evaluate(xml);
        }
        else {
            xml = getRestHandlerClient().evaluate(xml);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Results.class)
            .unmarshalDocument(xml);
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
    @Deprecated
    public DateTime getLastModificationDate(final String id)
        throws EscidocException, InternalClientException, TransportException {

        if (getTransport() == TransportProtocol.SOAP) {
            return getSoapHandlerClient().getLastModificationDate(id);
        }
        else {
            return getRestHandlerClient().getLastModificationDate(id);
        }
    }

    @Override
    protected SoapPolicyDecisionPointHandlerClient getSoapHandlerClientInstance()
        throws InternalClientException {
        return new SoapPolicyDecisionPointHandlerClient(getServiceAddress());
    }

    @Override
    protected RestPolicyDecisionPointHandlerClient getRestHandlerClientInstance()
        throws InternalClientException {
        return new RestPolicyDecisionPointHandlerClient(getServiceAddress());
    }
}
