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
 * Representation independent client for the ingest handlers
 *
 * @author KST
 *
 */
public class IngestHandlerClient implements IngestHandlerInterface {

    private TransportProtocol tp = TransportProtocol.SOAP;

    private String serviceAddress;

    private String handle;

    private RestIngestHandlerClient restIngestHandlerClient = null;

    private SoapIngestHandlerClient soapIngestHandlerClient = null;

    /*
     * (non-Javadoc)
     *
     * @see
     * de.escidoc.core.client.interfaces.IngestHandlerInterface#ingest(java.
     * lang.String)
     */
    public String ingest(final String resourceXml) throws EscidocException,
        InternalClientException, TransportException {
        String result = null;
        if (getTransportProtocol() == TransportProtocol.SOAP) {
            return getSoapIngestHandlerClient().ingest(resourceXml);
        }
        else {
            return getRestIngestHandlerClient().ingest(resourceXml);
        }
    }

    /**
     * Returns the transport protocol
     * @return the transport protocol
     */
    private TransportProtocol getTransportProtocol() {
        return this.tp;
    }

    /**
     * Gets the ingest handler client for the soap representation
     *
     * @return an instance of SoapIngestHandlerClient
     * @throws InternalClientException
     */
    private SoapIngestHandlerClient getSoapIngestHandlerClient()
        throws InternalClientException {
        synchronized (this) {
            if (soapIngestHandlerClient == null) {
                soapIngestHandlerClient = new SoapIngestHandlerClient();
                soapIngestHandlerClient.setHandle(getHandle());
                soapIngestHandlerClient.setServiceAddress(this.serviceAddress);
            }
            return soapIngestHandlerClient;
        }
    }

    /**
     *
     * @return
     * @throws InternalClientException
     */
    private RestIngestHandlerClient getRestIngestHandlerClient()
        throws InternalClientException {
        synchronized (this) {
            if (restIngestHandlerClient == null) {
                restIngestHandlerClient = new RestIngestHandlerClient();
                restIngestHandlerClient.setHandle(getHandle());
                restIngestHandlerClient.setServiceAddress(getServiceAddress());
            }
            return restIngestHandlerClient;
        }
    }

    /**
     * Returns the service address
     * @return the String containing the service address
     */
    private String getServiceAddress() {
        return this.serviceAddress;

    }

    /**
     * Returns the handle
     * @return the handle
     */
    private String getHandle() {
        return this.handle;
    }

    /* (non-Javadoc)
     * @see de.escidoc.core.client.interfaces.IngestHandlerInterface#setHandle(java.lang.String)
     */
    public void setHandle(String handle) throws InternalClientException {
        this.handle = handle;

    }

    /* (non-Javadoc)
     * @see de.escidoc.core.client.interfaces.IngestHandlerInterface#setServiceAddress(java.lang.String)
     */
    public void setServiceAddress(String address)
        throws InternalClientException {
        this.serviceAddress = address;

    }

    /* (non-Javadoc)
     * @see de.escidoc.core.client.interfaces.IngestHandlerInterface#setTransport(de.escidoc.core.client.TransportProtocol)
     */
    public void setTransport(TransportProtocol tp) {
        this.tp = tp;
    }

}
