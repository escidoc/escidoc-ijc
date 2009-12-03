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
public class IngestHandlerClient implements IngestHandlerInterface {

    private TransportProtocol tp = TransportProtocol.SOAP;

    private RestIngestHandlerClient restIngestHandlerClient = null;

    private SoapIngestHandlerClient soapIngestHandlerClient = null;

    /**
     * Create ContainersoapContainerHandlerClient instance. The service protocol
     * (REST/SOAP/..) selected from the configuration. Default is SOAP.
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
            return getSoapIngestHandlerClient().ingest(resourceXml);
        }
        else {
            return getRestIngestHandlerClient().ingest(resourceXml);
        }
    }

    /**
     * Gets the ingest handler client for the soap representation.
     * 
     * @return an instance of SoapIngestHandlerClient
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     */
    private SoapIngestHandlerClient getSoapIngestHandlerClient()
        throws InternalClientException {
        synchronized (this) {
            if (soapIngestHandlerClient == null) {
                soapIngestHandlerClient = new SoapIngestHandlerClient();
                // soapIngestHandlerClient.setHandle(getHandle());
                // soapIngestHandlerClient.setServiceAddress(this.serviceAddress);
            }
            return soapIngestHandlerClient;
        }
    }

    /**
     * Get ingest client handler for REST.
     * 
     * @return RestIngestHandlerClient
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     */
    private RestIngestHandlerClient getRestIngestHandlerClient()
        throws InternalClientException {
        synchronized (this) {
            if (restIngestHandlerClient == null) {
                restIngestHandlerClient = new RestIngestHandlerClient();
                // restIngestHandlerClient.setHandle(getHandle());
                // restIngestHandlerClient.setServiceAddress(getServiceAddress());
            }
            return restIngestHandlerClient;
        }
    }

    // /**
    // * Returns the service address.
    // *
    // * @return the String containing the service address
    // */
    // private String getServiceAddress() {
    // return this.serviceAddress;
    //
    // }

    // /**
    // * Returns the Authentication handle.
    // *
    // * @return the handle
    // */
    // private String getHandle() {
    // return this.handle;
    // }

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
    public String login(
        final String serviceAddress, final String username,
        final String password) throws EscidocException,
        InternalClientException, TransportException {

        if (getTransport() == TransportProtocol.SOAP) {
            return getSoapIngestHandlerClient().login(serviceAddress, username,
                password);
        }
        else {
            return getRestIngestHandlerClient().login(serviceAddress, username,
                password);
        }
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
    public void logout() throws EscidocException, InternalClientException,
        TransportException {

        setHandle("");
    }

    /**
     * Set Login-Handle.
     * 
     * @param handle
     *            Login-Handle
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     */
    public void setHandle(final String handle) throws InternalClientException {

        if (getTransport() == TransportProtocol.SOAP) {
            getSoapIngestHandlerClient().setHandle(handle);
        }
        else {
            getRestIngestHandlerClient().setHandle(handle);
        }
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

        if (getTransport() == TransportProtocol.SOAP) {
            getSoapIngestHandlerClient().setServiceAddress(address);
        }
        else {
            getRestIngestHandlerClient().setServiceAddress(address);
        }

    }

    /**
     * Set the Transport Protocol (REST/SOAP).
     * 
     * @return The used transport protocol.
     */
    public TransportProtocol getTransport() {
        return this.tp;
    }

    /**
     * Set the Transport Protocol (REST/SOAP).
     * 
     * @param transportProtocol
     *            The transport protocol.
     */
    public void setTransport(final TransportProtocol transportProtocol) {
        this.tp = transportProtocol;
    }

}
