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

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.StagingHandlerClientInterface;
import de.escidoc.core.client.rest.RestStagingHandlerClient;
import de.escidoc.core.client.soap.SoapClientBase;

/**
 * Handler for Staging Service. REST only!
 * 
 * @author SWA
 * 
 */
public class StagingHandlerClient
    extends AbstractHandlerClient<SoapClientBase, RestStagingHandlerClient>
    implements StagingHandlerClientInterface {

    /**
     * 
     */
    public StagingHandlerClient() {
        super();
    }

    /**
     * 
     * @param serviceAddress
     */
    public StagingHandlerClient(final URL serviceAddress) {
        super(serviceAddress);
    }

    /**
     * Upload a resource to the Staging Service.
     * 
     * @param in
     *            InputStream
     * @return URL of resource URL of the uploaded resource
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public URL upload(final File f) throws EscidocException,
        InternalClientException, TransportException {
        return getRestHandlerClient().upload(f);
    }

    /**
     * Upload a resource to the Staging Service.
     * 
     * @param in
     *            InputStream
     * @return URL of resource URL of the uploaded resource
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public URL upload(final InputStream ins) throws EscidocException,
        InternalClientException, TransportException {
        return getRestHandlerClient().upload(ins);
    }

    /**
     * SOAP not supported.
     */
    @Override
    protected SoapClientBase getSoapHandlerClientInstance()
        throws InternalClientException {
        return null;
    }

    @Override
    protected RestStagingHandlerClient getRestHandlerClientInstance()
        throws InternalClientException {
        return new RestStagingHandlerClient(getServiceAddress());
    }

    @Override
    public void setTransport(final TransportProtocol transport) {
        // ignore specified TransportProtocol
        super.setTransport(TransportProtocol.REST);
    }

    @Override
    public TransportProtocol getTransport() {
        return TransportProtocol.REST;
    }
}
