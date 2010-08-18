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

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.ContentModelHandlerClientInterface;
import de.escidoc.core.client.rest.RestContentModelHandlerClient;
import de.escidoc.core.client.soap.SoapContentModelHandlerClient;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.cmm.ContentModel;

/**
 * This is the generic ContentModelSoapContentModelHandlerClient which binds the
 * transport specific classes. The transport specification is done via
 * properties configuration of the eSciDoc client.
 * 
 * @author SWA
 * 
 */
public class ContentModelHandlerClient
    implements ContentModelHandlerClientInterface<ContentModel> {

    // Set SOAP as default transport protocol (for now :-()
    private TransportProtocol transport = TransportProtocol.SOAP;

    private SoapContentModelHandlerClient soapContentModelHandlerClient = null;

    private RestContentModelHandlerClient restContentModelHandlerClient = null;

    /**
     * Create ContentModelSoapHandlerClient instance. The service protocol
     * (REST/SOAP/..) selected from the configuration. Default is SOAP.
     * 
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public ContentModelHandlerClient() throws EscidocException,
        InternalClientException, TransportException {

    }

    /**
     * Create ContentModel in Repository.
     * 
     * @param contentModel
     *            The new ContentModel
     * @return The new created ContentModel.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public ContentModel create(final ContentModel contentModel)
        throws EscidocException, InternalClientException, TransportException {

        String contentModelXml =
            Factory.getContentModelMarshaller().marshalDocument(contentModel);
        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapContentModelHandlerClient().create(contentModelXml);
        }
        else {
            xml = getRestContentModelHandlerClient().create(contentModelXml);
        }
        return Factory.getContentModelMarshaller().unmarshalDocument(xml);
    }

    /**
     * Retrieve ContentModel from Repository.
     * 
     * @param id
     *            Objid of the ContentModel
     * @return The ContentModel with the provided obid.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public ContentModel retrieve(final String id) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapContentModelHandlerClient().retrieve(id);
        }
        else {
            xml = getRestContentModelHandlerClient().retrieve(id);
        }
        return Factory.getContentModelMarshaller().unmarshalDocument(xml);
    }

    /**
     * Delete ContentModel from Repository.
     * 
     * @param id
     *            Objid of the ContentModel
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public void delete(final String id) throws EscidocException,
        InternalClientException, TransportException {

        if (getTransport() == TransportProtocol.SOAP) {
            getSoapContentModelHandlerClient().delete(id);
        }
        else {
            getRestContentModelHandlerClient().delete(id);
        }
    }

    /**
     * Update ContentModel.
     * 
     * @param contentModel
     *            New Representation of ContentModel
     * @return The updated ContentModel.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public ContentModel update(final ContentModel contentModel)
        throws EscidocException, InternalClientException, TransportException {

        String contentModelXml =
            Factory.getContentModelMarshaller().marshalDocument(contentModel);
        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapContentModelHandlerClient().update(
                    contentModel.getObjid(), contentModelXml);
        }
        else {
            xml =
                getRestContentModelHandlerClient().update(
                    contentModel.getObjid(), contentModelXml);
        }
        return Factory.getContentModelMarshaller().unmarshalDocument(xml);
    }

    /**
     * See Interface for functional description.
     * 
     * @param id
     *            Id of ContentModel.
     * @return LastModificationDate of this ContentModel.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public DateTime getLastModificationDate(final String id)
        throws EscidocException, InternalClientException, TransportException {

        return getSoapContentModelHandlerClient().getLastModificationDate(id);
    }

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
    @Deprecated
    public String login(
        final String serviceAddress, final String username,
        final String password) throws EscidocException,
        InternalClientException, TransportException {

        if (getTransport() == TransportProtocol.SOAP) {
            return getSoapContentModelHandlerClient().login(serviceAddress,
                username, password);
        }
        else {
            return getRestContentModelHandlerClient().login(serviceAddress,
                username, password);
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
            return getSoapContentModelHandlerClient().getHandle();
        }
        else {
            return getRestContentModelHandlerClient().getHandle();
        }
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
            getSoapContentModelHandlerClient().setHandle(handle);
        }
        else {
            getRestContentModelHandlerClient().setHandle(handle);
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
        getSoapContentModelHandlerClient().setServiceAddress(address);
    }

    /**
     * Get the SOAP handler.
     * 
     * @return SoapContentModelHandlerClient
     * @throws InternalClientException
     *             Thrown if creating instance of SoapContentModelHandlerClient
     *             failed.
     */
    public SoapContentModelHandlerClient getSoapContentModelHandlerClient()
        throws InternalClientException {
        if (this.soapContentModelHandlerClient == null) {
            this.soapContentModelHandlerClient =
                new SoapContentModelHandlerClient();
        }
        return this.soapContentModelHandlerClient;
    }

    /**
     * Get the REST handler.
     * 
     * @return RestContentModelHandlerClient
     * @throws InternalClientException
     *             Thrown if creating instance of RestContentModelHandlerClient
     *             failed.
     */
    public RestContentModelHandlerClient getRestContentModelHandlerClient()
        throws InternalClientException {
        if (this.restContentModelHandlerClient == null) {
            this.restContentModelHandlerClient =
                new RestContentModelHandlerClient();
        }
        return this.restContentModelHandlerClient;
    }

    /**
     * Set the Transport Protocol (REST/SOAP).
     * 
     * @param tp
     *            The transport protocol.
     */
    public void setTransport(final TransportProtocol tp) {
        this.transport = tp;
    }

    /**
     * Set the Transport Protocol (REST/SOAP).
     * 
     * @return The used transport protocol.
     */
    public TransportProtocol getTransport() {
        return this.transport;
    }

}
