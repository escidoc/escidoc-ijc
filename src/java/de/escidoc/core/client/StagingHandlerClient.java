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
import de.escidoc.core.client.interfaces.StagingHandlerInterface;
import de.escidoc.core.client.rest.RestStagingHandlerClient;

/**
 * Handler for Staging Service.
 * 
 * @author SWA
 * 
 */
public class StagingHandlerClient implements StagingHandlerInterface {

    private TransportProtocol tp = TransportProtocol.REST;

    private RestStagingHandlerClient restStagingHandlerClient;

    /**
     * Create StagingHandlerClient instance. The service protocol (REST/SOAP/..)
     * selected from the configuration. Default is SOAP.
     * 
     * @throws ClientException
     * 
     */
    public StagingHandlerClient() throws EscidocException,
        InternalClientException, TransportException {

        // read service protocol from config or set as default SOAP
        this.restStagingHandlerClient = new RestStagingHandlerClient();
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
    public URL upload(final File f) throws EscidocException,
        InternalClientException, TransportException {

        if (getTransport() == TransportProtocol.SOAP) {
            throw new InternalClientException(
                "Staging Service does not support SOAP");
        }

        return getRestStagingHandlerClient().upload(f);
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
    public URL upload(final InputStream ins) throws EscidocException,
        InternalClientException, TransportException {

        if (getTransport() == TransportProtocol.SOAP) {
            throw new InternalClientException(
                "Staging Service does not support SOAP");
        }

        return getRestStagingHandlerClient().upload(ins);
    }

    /**
     * Get the REST handler.
     * 
     * @return RestStagingHandlerClient
     * @throws InternalClientException
     *             Thrown if creating instance of RestStagingHandlerClient
     *             failed.
     */
    public RestStagingHandlerClient getRestStagingHandlerClient()
        throws InternalClientException {

        if (this.restStagingHandlerClient == null) {
            this.restStagingHandlerClient = new RestStagingHandlerClient();
        }
        return this.restStagingHandlerClient;
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

        if (getTransport() == TransportProtocol.SOAP) {
            throw new InternalClientException(
                "Staging Service does not support SOAP");
        }
        else {
            return getRestStagingHandlerClient().login(serviceAddress,
                username, password);
        }
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
     * Get Login-Handle.
     * 
     * @return Login-Handle
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     */
    public String getHandle() throws InternalClientException {

        if (getTransport() == TransportProtocol.SOAP) {
            throw new InternalClientException(
                "Staging Service does not support SOAP");
        }
        else {
            return getRestStagingHandlerClient().getHandle();
        }
    }

    /**
     * See Interface for functional description.
     * 
     * @param handle
     * @see de.escidoc.core.client.interfaces.BaseClientHandlerInterface#setHandle(java.lang.String)
     */
    public void setHandle(final String handle) throws InternalClientException {

        if (getTransport() == TransportProtocol.SOAP) {
            throw new InternalClientException(
                "Staging Service does not support SOAP");
        }
        else {
            getRestStagingHandlerClient().setHandle(handle);
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
            throw new InternalClientException(
                "Staging Service does not support SOAP");
        }
        else {
            getRestStagingHandlerClient().setServiceAddress(address);
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
