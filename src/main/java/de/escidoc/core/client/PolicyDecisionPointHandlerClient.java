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

import static de.escidoc.core.common.Precondition.checkNotNull;

import java.net.URL;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.PolicyDecisionPointHandlerClientInterface;
import de.escidoc.core.client.rest.RestPolicyDecisionPointHandlerClient;
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
public class PolicyDecisionPointHandlerClient extends AbstractHandlerClient<RestPolicyDecisionPointHandlerClient>
    implements PolicyDecisionPointHandlerClientInterface {

    /**
     * 
     * @param serviceAddress
     */
    public PolicyDecisionPointHandlerClient(final URL serviceAddress) {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @deprecated Use
     *             {@link PolicyDecisionPointHandlerClient#PolicyDecisionPointHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public PolicyDecisionPointHandlerClient(final String serviceAddress) {
        super(serviceAddress);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.PolicyDecisionPointHandlerClientInterface
     * #evaluate(de.escidoc.core.resources.aa.pdp.Requests)
     */
    @Override
    public Results evaluate(final Requests requests) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(requests);

        String xml = MarshallerFactory.getInstance().getMarshaller(Requests.class).marshalDocument(requests);

        xml = getClient().evaluate(xml);

        return MarshallerFactory.getInstance().getMarshaller(Results.class).unmarshalDocument(xml);
    }

    @Override
    protected RestPolicyDecisionPointHandlerClient getRestHandlerClientInstance() throws InternalClientException {
        return new RestPolicyDecisionPointHandlerClient(getServiceAddress());
    }
}