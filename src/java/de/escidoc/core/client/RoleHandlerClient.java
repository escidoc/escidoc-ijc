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

import java.util.Collection;
import java.util.LinkedList;

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
import de.escidoc.core.resources.sb.Record;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;
import de.escidoc.core.resources.sb.search.records.RoleRecord;

/**
 * This is the generic RoleContainerHandlerClient which binds the transport
 * specific classes. The transport specification is done via properties
 * configuration of the eSciDoc client.
 * 
 * @author SWA
 * 
 */
public class RoleHandlerClient extends AbstractHandlerClient
	<SoapRoleHandlerClient, RestRoleHandlerClient>
		implements RoleHandlerClientInterface {

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
        String roleString = Factory.getMarshallerFactory(getTransport())
        	.getRoleMarshaller().marshalDocument(role);

        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().create(roleString);
        }
        else {
            xml = getRestHandlerClient().create(roleString);
        }

        return Factory.getMarshallerFactory(getTransport())
        	.getRoleMarshaller().unmarshalDocument(xml);
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
            roleString = getSoapHandlerClient().retrieve(id);
        }
        else {
            roleString = getRestHandlerClient().retrieve(id);
        }
        return Factory.getMarshallerFactory(getTransport()).getRoleMarshaller()
        	.unmarshalDocument(roleString);
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
            getSoapHandlerClient().delete(id);
        }
        else {
            getRestHandlerClient().delete(id);
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
        String roleString = Factory.getMarshallerFactory(getTransport())
        	.getRoleMarshaller().marshalDocument(role);
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapHandlerClient().update(role.getObjid(), roleString);
        }
        else {
            xml =
                getRestHandlerClient().update(role.getObjid(), roleString);
        }

        return Factory.getMarshallerFactory(getTransport()).getRoleMarshaller()
        	.unmarshalDocument(xml);
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

        String taskParamString = Factory.getMarshallerFactory(getTransport())
        	.getTaskParamMarshaller().marshalDocument(taskParam);
        String xml = null;

        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveRoles(taskParamString);
        }
        else {
            xml = getRestHandlerClient().retrieveRoles(taskParamString);
        }
        return Factory.getMarshallerFactory(getTransport())
        	.getRoleListMarshaller().unmarshalDocument(xml);
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
    public SearchRetrieveResponse retrieveRoles(
        final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException {

    	evalFilter(filter);
    	
        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveRoles(filter);
        }
        else {
            xml = getRestHandlerClient().retrieveRoles(filter);
        }
        return Factory.getMarshallerFactory(getTransport())
        	.getSearchRetrieveResponseMarshaller().unmarshalDocument(xml);
    }
    
    @SuppressWarnings("rawtypes")
	public Collection<Role> retrieveRolesAsList(
            final SearchRetrieveRequestType filter) throws EscidocException,
            InternalClientException, TransportException {
    	
    	SearchRetrieveResponse response = retrieveRoles(filter);
    	Collection<Role> results = new LinkedList<Role>();
    	
    	for (Record record : response.getRecords()) {
			if(record instanceof RoleRecord) {
				Role result = ((RoleRecord)record).getRecordData();
				results.add(result);
			}
		}
    	return results;
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
    public ExplainResponse retrieveRoles(final ExplainRequestType filter)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveRoles(filter);
        }
        else {
            xml = getRestHandlerClient().retrieveRoles(filter);
        }
        return Factory.getMarshallerFactory(getTransport())
        	.getExplainResponseMarshaller().unmarshalDocument(xml);
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
            return getSoapHandlerClient().getLastModificationDate(id);
        }
        else {
            return getRestHandlerClient().getLastModificationDate(id);
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
            return getSoapHandlerClient().login(serviceAddress, username,
                password);
        }
        else {
            return getRestHandlerClient().login(serviceAddress, username,
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

	@Override
	protected SoapRoleHandlerClient getSoapHandlerClientInstance()
			throws InternalClientException {
		return new SoapRoleHandlerClient();
	}

	@Override
	protected RestRoleHandlerClient getRestHandlerClientInstance()
			throws InternalClientException {
		return new RestRoleHandlerClient();
	}
}
