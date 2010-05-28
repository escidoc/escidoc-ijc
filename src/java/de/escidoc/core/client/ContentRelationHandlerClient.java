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

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import org.joda.time.DateTime;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.ContentRelationHandlerClientInterface;
import de.escidoc.core.client.rest.RestContentRelationHandlerClient;
import de.escidoc.core.client.soap.SoapContentRelationHandlerClient;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.common.properties.Properties;
import de.escidoc.core.resources.om.contentRelation.ContentRelation;
import de.escidoc.core.resources.sb.explain.ExplainRecord;
import de.escidoc.core.resources.sb.srw.SearchRetrieveResponseType;

/**
 * This is the generic ContentRelationSoapHandlerClient which binds the
 * transport specific classes. The transport specification is done via
 * properties configuration of the eSciDoc client.
 * 
 * @author SWA
 * 
 */
public class ContentRelationHandlerClient
    implements ContentRelationHandlerClientInterface<ContentRelation> {

    // Set SOAP as default transport protocol (for now :-()
    private TransportProtocol transport = TransportProtocol.SOAP;

    private SoapContentRelationHandlerClient soapContentRelationHandlerClient =
        null;

    private RestContentRelationHandlerClient restContentRelationHandlerClient =
        null;

    /**
     * Create ContentRelationSoapHandlerClient instance. The service protocol
     * (REST/SOAP/..) selected from the configuration. Default is SOAP.
     * 
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public ContentRelationHandlerClient() throws EscidocException,
        InternalClientException, TransportException {

    }

    /**
     * Create ContentRelation in Repository.
     * 
     * @param contentRelation
     *            The new ContentRelation
     * @return The new created ContentRelation.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public ContentRelation create(final ContentRelation contentRelation)
        throws EscidocException, InternalClientException, TransportException {

        String contentRelationXml =
            Factory.getContentRelationMarshaller().marshalDocument(
                contentRelation);
        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapContentRelationHandlerClient()
                    .create(contentRelationXml);
        }
        else {
            xml =
                getRestContentRelationHandlerClient()
                    .create(contentRelationXml);
        }
        return Factory.getContentRelationMarshaller().unmarshalDocument(xml);
    }

    /**
     * Retrieve ContentRelation from Repository.
     * 
     * @param id
     *            Objid of the ContentRelation
     * @return The ContentRelation with the provided obid.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public ContentRelation retrieve(final String id) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapContentRelationHandlerClient().retrieve(id);
        }
        else {
            xml = getRestContentRelationHandlerClient().retrieve(id);
        }
        return Factory.getContentRelationMarshaller().unmarshalDocument(xml);
    }

    /**
     * Retrieve Properties of ContentRelation from Repository.
     * 
     * @param id
     *            Objid of the ContentRelation
     * @return The Properties of the ContentRelation.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public Properties retrieveProperties(final String id)
        throws EscidocException, InternalClientException, TransportException {

        throw new InternalClientException("method not yet supported");
    }

    /**
     * Delete ContentRelation from Repository.
     * 
     * @param id
     *            Objid of the ContentRelation
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
            getSoapContentRelationHandlerClient().delete(id);
        }
        else {
            getRestContentRelationHandlerClient().delete(id);
        }
    }

    /**
     * Update ContentRelation.
     * 
     * @param contentRelation
     *            New Representation of ContentRelation
     * @return The updated ContentRelation.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public ContentRelation update(final ContentRelation contentRelation)
        throws EscidocException, InternalClientException, TransportException {

        String contentRelationXml =
            Factory.getContentRelationMarshaller().marshalDocument(
                contentRelation);
        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapContentRelationHandlerClient().update(
                    contentRelation.getObjid(), contentRelationXml);
        }
        else {
            xml =
                getRestContentRelationHandlerClient().update(
                    contentRelation.getObjid(), contentRelationXml);
        }
        return Factory.getContentRelationMarshaller().unmarshalDocument(xml);
    }

    /**
     * Assign Persistent Identifier for ContentRelation (object).
     * 
     * @param id
     *            Objid of the ContentRelation
     * @param taskParam
     *            Task parameter to provide PID parameter.
     * @return The updated ContentRelation.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public Result assignObjectPid(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        // String xml =
        // getSoapContentRelationHandlerClient().assignObjectPid(id,
        // Factory.getTaskParamMarshaller().marshalDocument(taskParam));
        // return Factory.getResultMarshaller().unmarshalDocument(xml);
        throw new InternalClientException(
            "ContentRelation does currently no PID assignment.");
    }

    /**
     * Retrieve ContentRelations (Filter for ContentRelations).
     * 
     * @param filter
     *            Filter parameter
     * @return SearchRetrieveResponseType
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public SearchRetrieveResponseType retrieveContentRelations(
        final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapContentRelationHandlerClient().retrieveContentRelations(
                    filter);
        }
        else {
            xml =
                getRestContentRelationHandlerClient().retrieveContentRelations(
                    filter);
        }
        return Factory.getFilterResponseMarshaller().unmarshalDocument(xml);
    }

    /**
     * Retrieve ContentRelations (Filter for ContentRelations).
     * 
     * @param filter
     *            Filter parameter
     * @return ExplainRecord
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public ExplainRecord retrieveContentRelations(
        final ExplainRequestType filter) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapContentRelationHandlerClient().retrieveContentRelations(
                    filter);
        }
        else {
            xml =
                getRestContentRelationHandlerClient().retrieveContentRelations(
                    filter);
        }
        return Factory.getExplainRecordMarshaller().unmarshalDocument(xml);
    }

    /**
     * See Interface for functional description.
     * 
     * @param id
     *            Id of ContentRelation.
     * @return LastModificationDate of this ContentRelation.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public DateTime getLastModificationDate(final String id)
        throws EscidocException, InternalClientException, TransportException {

        return getSoapContentRelationHandlerClient()
            .getLastModificationDate(id);
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
            return getSoapContentRelationHandlerClient().login(serviceAddress,
                username, password);
        }
        else {
            return getRestContentRelationHandlerClient().login(serviceAddress,
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
            return getSoapContentRelationHandlerClient().getHandle();
        }
        else {
            return getRestContentRelationHandlerClient().getHandle();
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
            getSoapContentRelationHandlerClient().setHandle(handle);
        }
        else {
            getRestContentRelationHandlerClient().setHandle(handle);
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
        getSoapContentRelationHandlerClient().setServiceAddress(address);
    }

    /**
     * Get the SOAP handler.
     * 
     * @return SoapContentRelationHandlerClient
     * @throws InternalClientException
     *             Thrown if creating instance of
     *             SoapContentRelationHandlerClient failed.
     */
    public SoapContentRelationHandlerClient getSoapContentRelationHandlerClient()
        throws InternalClientException {
        if (this.soapContentRelationHandlerClient == null) {
            this.soapContentRelationHandlerClient =
                new SoapContentRelationHandlerClient();
        }
        return this.soapContentRelationHandlerClient;
    }

    /**
     * Get the REST handler.
     * 
     * @return RestContentRelationHandlerClient
     * @throws InternalClientException
     *             Thrown if creating instance of
     *             RestContentRelationHandlerClient failed.
     */
    public RestContentRelationHandlerClient getRestContentRelationHandlerClient()
        throws InternalClientException {
        if (this.restContentRelationHandlerClient == null) {
            this.restContentRelationHandlerClient =
                new RestContentRelationHandlerClient();
        }
        return this.restContentRelationHandlerClient;
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
