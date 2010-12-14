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

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.IngestHandlerInterface;
import de.escidoc.core.client.rest.RestIngestHandlerClient;
import de.escidoc.core.client.soap.SoapIngestHandlerClient;

/**
 * Representation independent client for the ingest handlers.
 * 
 * @author KST
 * 
 */
public class IngestHandlerClient
    extends
    AbstractHandlerClient<SoapIngestHandlerClient, RestIngestHandlerClient>
    implements IngestHandlerInterface {

    /**
     * 
     */
    public IngestHandlerClient() {
        super();
    }

    /**
     * 
     * @param serviceAddress
     */
    public IngestHandlerClient(final String serviceAddress) {
        super(serviceAddress);
    }

    /**
     * Returns the XML presentation of a resource.
     * 
     * @param resourceXml
     *            XML representation of a resource.
     * @return XML representation for ingest
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public String ingest(final String resourceXml) throws EscidocException,
        InternalClientException, TransportException {

        if (getTransport() == TransportProtocol.SOAP) {
            return getSoapHandlerClient().ingest(resourceXml);
        }
        else {
            return getRestHandlerClient().ingest(resourceXml);
        }
    }

    @Override
    protected SoapIngestHandlerClient getSoapHandlerClientInstance()
        throws InternalClientException {
        return new SoapIngestHandlerClient(getServiceAddress());
    }

    @Override
    protected RestIngestHandlerClient getRestHandlerClientInstance()
        throws InternalClientException {
        return new RestIngestHandlerClient(getServiceAddress());
    }
}
