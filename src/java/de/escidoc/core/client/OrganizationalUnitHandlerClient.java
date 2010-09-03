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

import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface;
import de.escidoc.core.client.rest.RestOrganizationalUnitHandlerClient;
import de.escidoc.core.client.soap.SoapOrganizationalUnitHandlerClient;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.common.properties.Properties;
import de.escidoc.core.resources.oum.OrganizationalUnit;
import de.escidoc.core.resources.oum.OrganizationalUnitList;
import de.escidoc.core.resources.oum.PathList;
import de.escidoc.core.resources.sb.explain.ExplainData;
import de.escidoc.core.resources.sb.srw.SearchRetrieveResponseType;

/**
 * This is the generic OrganizationalUnitClientHandler which binds the transport
 * specific classes. The transport specification is done via properties
 * configuration of the eSciDoc client.
 * 
 * @author SWA
 * 
 */
public class OrganizationalUnitHandlerClient extends AbstractHandlerClient
    implements OrganizationalUnitHandlerClientInterface<OrganizationalUnit> {

    private SoapOrganizationalUnitHandlerClient soapOrganizationalUnitHandlerClient =
        null;

    private RestOrganizationalUnitHandlerClient restOrganizationalUnitHandlerClient =
        null;

    /**
     * Create OrganizationalUnitClientHandler instance. The service protocol
     * (REST/SOAP/..) selected from the configuration. Default is SOAP.
     * 
     * @throws InternalClientException
     * 
     */
    public OrganizationalUnitHandlerClient() throws InternalClientException {

    }

    /**
     * See Interface for functional description.
     * 
     * @param organizationalUnit
     * @return
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface#create(de.escidoc.core.resources.interfaces.organizationalUnit.OrganizationalUnitInterface)
     */
    public OrganizationalUnit create(final OrganizationalUnit organizationalUnit)
        throws EscidocException, InternalClientException, TransportException {

        String orgUnitString = Factory.getMarshallerFactory(getTransport())
        	.getOrganizationalUnitMarshaller().marshalDocument(organizationalUnit);
        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapOrganizationalUnitHandlerClient().create(orgUnitString);
        }
        else {
            xml =
                getRestOrganizationalUnitHandlerClient().create(orgUnitString);
        }
        return Factory.getMarshallerFactory(getTransport())
        	.getOrganizationalUnitMarshaller().unmarshalDocument(xml);
    }

    /**
     * See Interface for functional description.
     * 
     * @param id
     * @return
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface#retrieve(java.lang.String)
     */
    public OrganizationalUnit retrieve(final String id)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapOrganizationalUnitHandlerClient().retrieve(id);
        }
        else {
            xml = getRestOrganizationalUnitHandlerClient().retrieve(id);
        }
        return Factory.getMarshallerFactory(getTransport())
        	.getOrganizationalUnitMarshaller().unmarshalDocument(xml);
    }

    /**
     * See Interface for functional description.
     * 
     * @param organizationalUnit
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.interfaces.CrudHandlerInterface#update(java.lang.Object)
     */
    public OrganizationalUnit update(final OrganizationalUnit organizationalUnit)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String orgUnitString = Factory.getMarshallerFactory(getTransport())
        	.getOrganizationalUnitMarshaller().marshalDocument(organizationalUnit);
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapOrganizationalUnitHandlerClient().update(
                    organizationalUnit.getObjid(), orgUnitString);
        }
        else {
            xml =
                getRestOrganizationalUnitHandlerClient().update(
                    organizationalUnit.getObjid(), orgUnitString);
        }
        return Factory.getMarshallerFactory(getTransport())
        	.getOrganizationalUnitMarshaller().unmarshalDocument(xml);
    }

    /**
     * See Interface for functional description.
     * 
     * @param id
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface#open(java.lang.String,
     *      de.escidoc.core.resources.common.TaskParam)
     */
    public Result open(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString = Factory.getMarshallerFactory(getTransport())
        	.getTaskParamMarshaller().marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapOrganizationalUnitHandlerClient().open(id,
                    taskParamString);
        }
        else {
            xml =
                getRestOrganizationalUnitHandlerClient().open(id,
                    taskParamString);
        }
        return Factory.getMarshallerFactory(getTransport())
        	.getResultMarshaller().unmarshalDocument(xml);
    }

    /**
     * See Interface for functional description.
     * 
     * @param id
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface#close(java.lang.String,
     *      de.escidoc.core.resources.common.TaskParam)
     */
    public Result close(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            Factory.getMarshallerFactory(getTransport()).getTaskParamMarshaller().marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapOrganizationalUnitHandlerClient().close(id,
                    taskParamString);
        }
        else {
            xml =
                getRestOrganizationalUnitHandlerClient().close(id,
                    taskParamString);
        }
        return Factory.getMarshallerFactory(getTransport()).getResultMarshaller().unmarshalDocument(xml);
    }

    /**
     *
     */
    public Properties retrieveProperties(final String id)
        throws EscidocException, InternalClientException, TransportException {

        throw new InternalClientException("method not yet supported");
    }

    /**
     * See Interface for functional description.
     * 
     * @param id
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface#delete(java.lang.String)
     */
    public void delete(final String id) throws EscidocException,
        InternalClientException, TransportException {

        if (getTransport() == TransportProtocol.SOAP) {
            getSoapOrganizationalUnitHandlerClient().delete(id);
        }
        else {
            getRestOrganizationalUnitHandlerClient().delete(id);
        }
    }

    /**
     * See Interface for functional description.
     * 
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface#getLastModificationDate(java.lang.String)
     */
    public DateTime getLastModificationDate(final String id)
        throws EscidocException, InternalClientException, TransportException {

        return getSoapOrganizationalUnitHandlerClient()
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

        if (getTransport() == TransportProtocol.SOAP) {
            return getSoapOrganizationalUnitHandlerClient().login(
                serviceAddress, username, password);
        }
        else {
            return getRestOrganizationalUnitHandlerClient().login(
                serviceAddress, username, password);
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
            return getSoapOrganizationalUnitHandlerClient().getHandle();
        }
        else {
            return getRestOrganizationalUnitHandlerClient().getHandle();
        }
    }

    /**
     * See Interface for functional description.
     * 
     * @param handle
     * @throws InternalClientException
     * @see de.escidoc.core.client.interfaces.BaseClientHandlerInterface#setHandle(java.lang.String)
     */
    public void setHandle(final String handle) throws InternalClientException {

        if (getTransport() == TransportProtocol.SOAP) {
            getSoapOrganizationalUnitHandlerClient().setHandle(handle);
        }
        else {
            getRestOrganizationalUnitHandlerClient().setHandle(handle);
        }
    }

    /**
     * @return the SoapOrganizationalUnitClientHandler
     * @throws InternalClientException
     * @throws InternalClientException
     */
    public SoapOrganizationalUnitHandlerClient getSoapOrganizationalUnitHandlerClient()
        throws InternalClientException {

        if (this.soapOrganizationalUnitHandlerClient == null) {
            this.soapOrganizationalUnitHandlerClient =
                new SoapOrganizationalUnitHandlerClient();
        }
        return this.soapOrganizationalUnitHandlerClient;
    }

    /**
     * @return the RestOrganizationalUnitClientHandler
     * @throws InternalClientException
     * @throws InternalClientException
     */
    public RestOrganizationalUnitHandlerClient getRestOrganizationalUnitHandlerClient()
        throws InternalClientException {

        if (this.restOrganizationalUnitHandlerClient == null) {
            this.restOrganizationalUnitHandlerClient =
                new RestOrganizationalUnitHandlerClient();
        }
        return this.restOrganizationalUnitHandlerClient;
    }

    public Result assignObjectPid(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        throw new InternalClientException("Method not yet supported.");
    }

    // public StructMap retrieveParents(final String id, final String xml)
    // throws EscidocException, InternalClientException, TransportException {
    // throw new InternalClientException("Method not yet supported.");
    // }
    //
    // public StructMap updateParents(final String id, final String xml)
    // throws EscidocException, InternalClientException, TransportException {
    //
    // throw new InternalClientException("Method not yet supported.");
    // }

    public OrganizationalUnitList retrieveParentObjects(final String id)
        throws EscidocException, InternalClientException, TransportException {
        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapOrganizationalUnitHandlerClient().retrieveParentObjects(
                    id);
        }
        else {
            xml =
                getRestOrganizationalUnitHandlerClient().retrieveParentObjects(
                    id);
        }
        return Factory.getMarshallerFactory(getTransport()).getOrganizationalUnitListMarshaller().unmarshalDocument(xml);
    }

    public OrganizationalUnitList retrieveChildObjects(final String id)
        throws EscidocException, InternalClientException, TransportException {
        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapOrganizationalUnitHandlerClient().retrieveChildObjects(
                    id);
        }
        else {
            xml =
                getRestOrganizationalUnitHandlerClient().retrieveChildObjects(
                    id);
        }
        return Factory.getMarshallerFactory(getTransport()).getOrganizationalUnitListMarshaller().unmarshalDocument(xml);
    }

    /**
     * Retrieve Organizational Units via filter from framework.
     * 
     * @param taskParam
     *            Expression of Filter language.
     * @return ContainerList
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Deprecated
    public OrganizationalUnitList retrieveOrganizationalUnits(
        final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        String taskParamString =
            Factory.getMarshallerFactory(getTransport()).getTaskParamMarshaller().marshalDocument(taskParam);
        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapOrganizationalUnitHandlerClient()
                    .retrieveOrganizationalUnits(taskParamString);
        }
        else {
            xml =
                getRestOrganizationalUnitHandlerClient()
                    .retrieveOrganizationalUnits(taskParamString);
        }
        return Factory.getMarshallerFactory(getTransport()).getOrganizationalUnitListMarshaller().unmarshalDocument(xml);
    }


    /**
     * Retrieve Organizational Units (Filter for Organizational Units).
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
    public SearchRetrieveResponseType retrieveOrganizationalUnits(
        final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapOrganizationalUnitHandlerClient().retrieveOrganizationalUnits(filter);
        }
        else {
            xml = getRestOrganizationalUnitHandlerClient().retrieveOrganizationalUnits(filter);
        }
        return Factory.getMarshallerFactory(getTransport()).getFilterResponseMarshaller().unmarshalDocument(xml);
    }

    /**
     * Retrieve Organizational Units (Filter for Organizational Units).
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
    public ExplainData retrieveOrganizationalUnits(final ExplainRequestType filter)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapOrganizationalUnitHandlerClient().retrieveOrganizationalUnits(filter);
        }
        else {
            xml = getRestOrganizationalUnitHandlerClient().retrieveOrganizationalUnits(filter);
        }
        return Factory.getMarshallerFactory(getTransport()).getExplainRecordMarshaller().unmarshalDocument(xml);
    }


    public PathList retrievePathList(final String id) throws EscidocException,
        InternalClientException, TransportException {
        throw new InternalClientException("Method not yet supported.");
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
        getSoapOrganizationalUnitHandlerClient().setServiceAddress(address);
    }
}
