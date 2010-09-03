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
import de.escidoc.core.client.interfaces.RoleHandlerClientInterface;
import de.escidoc.core.client.rest.RestRoleHandlerClient;
import de.escidoc.core.client.soap.SoapRoleHandlerClient;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.aa.role.Role;
import de.escidoc.core.resources.aa.role.Roles;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.sb.explain.ExplainData;
import de.escidoc.core.resources.sb.srw.SearchRetrieveResponseType;

/**
 * This is the generic RoleContainerHandlerClient which binds the transport
 * specific classes. The transport specification is done via properties
 * configuration of the eSciDoc client.
 * 
 * @author SWA
 * 
 */
public class RoleHandlerClient extends AbstractHandlerClient 
		implements RoleHandlerClientInterface<Role> {

    private SoapRoleHandlerClient soapRoleHandlerClient = null;

    private RestRoleHandlerClient restRoleHandlerClient = null;

    /**
     * Create RoleHandlerClient instance. The service protocol (REST/SOAP/..)
     * selected from the configuration. Default is SOAP.
     * 
     * @throws ClientException
     * 
     */
    public RoleHandlerClient() throws EscidocException,
        InternalClientException, TransportException {

        // read service protocol from config or set as default SOAP
        this.soapRoleHandlerClient = new SoapRoleHandlerClient();
    }

    /**
     * Create a role.
     * 
     * @param role
     *            Role which is to create
     * @return Role
     * @throws EscidocClientException
     */
    public Role create(final Role role) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        String roleString = Factory.getMarshallerFactory(getTransport()).getRoleMarshaller().marshalDocument(role);

        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapRoleHandlerClient().create(roleString);
        }
        else {
            xml = getRestRoleHandlerClient().create(roleString);
        }

        return Factory.getMarshallerFactory(getTransport()).getRoleMarshaller().unmarshalDocument(xml);
    }

    /**
     * See Interface for functional description.
     * 
     * @param id
     * @return
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#retrieve(java.lang.String)
     */
    public Role retrieve(final String id) throws EscidocException,
        InternalClientException, TransportException {

        String roleString = null;
        if (getTransport() == TransportProtocol.SOAP) {
            roleString = getSoapRoleHandlerClient().retrieve(id);
        }
        else {
            roleString = getRestRoleHandlerClient().retrieve(id);
        }
        return Factory.getMarshallerFactory(getTransport()).getRoleMarshaller().unmarshalDocument(roleString);
    }

    /**
     * See Interface for functional description.
     * 
     * @param id
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#delete(java.lang.String)
     */
    public void delete(final String id) throws EscidocException,
        InternalClientException, TransportException {

        if (getTransport() == TransportProtocol.SOAP) {
            getSoapRoleHandlerClient().delete(id);
        }
        else {
            getRestRoleHandlerClient().delete(id);
        }
    }

    /**
     * See Interface for functional description.
     * 
     * @param container
     * @return
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#update(de.escidoc.core.resources.interfaces.container.ContainerInterface)
     */
    public Role update(final Role role) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        String roleString = Factory.getMarshallerFactory(getTransport()).getRoleMarshaller().marshalDocument(role);
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapRoleHandlerClient().update(role.getObjid(), roleString);
        }
        else {
            xml =
                getRestRoleHandlerClient().update(role.getObjid(), roleString);
        }

        return Factory.getMarshallerFactory(getTransport()).getRoleMarshaller().unmarshalDocument(xml);
    }

    /**
     * Retrieve Roles (Filter for Roles).
     * 
     * @param taskParam
     *            Filter parameter
     * @return Roles
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Deprecated
    public Roles retrieveRoles(final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException {

        String taskParamString =
            Factory.getMarshallerFactory(getTransport()).getTaskParamMarshaller().marshalDocument(taskParam);
        String xml = null;

        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapRoleHandlerClient().retrieveRoles(taskParamString);
        }
        else {
            xml = getRestRoleHandlerClient().retrieveRoles(taskParamString);
        }
        return Factory.getMarshallerFactory(getTransport()).getRoleListMarshaller().unmarshalDocument(xml);

    }

    /**
     * Retrieve Roles (Filter for Roles).
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
    public SearchRetrieveResponseType retrieveRoles(
        final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapRoleHandlerClient().retrieveRoles(filter);
        }
        else {
            xml = getRestRoleHandlerClient().retrieveRoles(filter);
        }
        return Factory.getMarshallerFactory(getTransport()).getFilterResponseMarshaller().unmarshalDocument(xml);
    }

    /**
     * Retrieve Roles (Filter for Roles).
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
    public ExplainData retrieveRoles(final ExplainRequestType filter)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapRoleHandlerClient().retrieveRoles(filter);
        }
        else {
            xml = getRestRoleHandlerClient().retrieveRoles(filter);
        }
        return Factory.getMarshallerFactory(getTransport()).getExplainRecordMarshaller().unmarshalDocument(xml);
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
    public DateTime getLastModificationDate(final String id)
        throws EscidocException, InternalClientException, TransportException {

        if (getTransport() == TransportProtocol.SOAP) {
            return getSoapRoleHandlerClient().getLastModificationDate(id);
        }
        else {
            return getRestRoleHandlerClient().getLastModificationDate(id);
        }
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
            return getSoapRoleHandlerClient().login(serviceAddress, username,
                password);
        }
        else {
            return getRestRoleHandlerClient().login(serviceAddress, username,
                password);
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
            return getSoapRoleHandlerClient().getHandle();
        }
        else {
            return getRestRoleHandlerClient().getHandle();
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
            getSoapRoleHandlerClient().setHandle(handle);
        }
        else {
            getRestRoleHandlerClient().setHandle(handle);
        }
    }

    /**
     * Get the REST handler.
     * 
     * @return RestRoleHandlerClient
     * @throws InternalClientException
     *             Thrown if creating instance of RestRoleHandlerClient failed.
     */
    public RestRoleHandlerClient getRestRoleHandlerClient()
        throws InternalClientException {

        if (this.restRoleHandlerClient == null) {
            this.restRoleHandlerClient = new RestRoleHandlerClient();
        }
        return this.restRoleHandlerClient;
    }

    /**
     * Get the SOAP handler.
     * 
     * @return SoapRoleHandlerClient
     * @throws InternalClientException
     *             Thrown if creating instance of SoapRoleHandlerClient failed.
     */
    public SoapRoleHandlerClient getSoapRoleHandlerClient()
        throws InternalClientException {

        if (this.soapRoleHandlerClient == null) {
            this.soapRoleHandlerClient = new SoapRoleHandlerClient();
        }

        return this.soapRoleHandlerClient;
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
            getSoapRoleHandlerClient().setServiceAddress(address);
        }
        else {
            getRestRoleHandlerClient().setServiceAddress(address);
        }
    }
}
