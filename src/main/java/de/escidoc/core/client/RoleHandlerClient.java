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

import static de.escidoc.core.common.Precondition.checkNotNull;
import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.net.URL;
import java.util.List;

import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.RoleHandlerClientInterface;
import de.escidoc.core.client.rest.RestRoleHandlerClient;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.aa.role.Role;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;

/**
 * This is the generic RoleContainerHandlerClient which binds the transport
 * specific classes. The transport specification is done via properties
 * configuration of the eSciDoc client.
 * 
 * @author SWA
 * 
 */
public class RoleHandlerClient extends AbstractHandlerClient<RestRoleHandlerClient>
    implements RoleHandlerClientInterface {

    /**
     * 
     */
    public RoleHandlerClient() {
        super();
    }

    /**
     * 
     * @param serviceAddress
     */
    public RoleHandlerClient(final URL serviceAddress) {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @deprecated Use {@link RoleHandlerClient#RoleHandlerClient(URL)} instead.
     */
    @Deprecated
    public RoleHandlerClient(final String serviceAddress) {
        super(serviceAddress);
    }

    /**
     * Create a role.
     * 
     * @param role
     *            Role which is to create
     * @return Role
     * @throws EscidocClientException
     */
    @Override
    public Role create(final Role role) throws EscidocException, InternalClientException, TransportException {

        checkNotNull(role);

        Marshaller<Role> m = MarshallerFactory.getInstance().getMarshaller(Role.class);

        String xml = getClient().create(m.marshalDocument(role));

        return m.unmarshalDocument(xml);
    }

    /**
     * See Interface for functional description.
     * 
     * @param id
     * @return
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#retrieve(java.lang.String)
     */
    @Override
    public Role retrieve(final String id) throws EscidocException, InternalClientException, TransportException {

        checkNotNull(id);

        String xml = getClient().retrieve(id);

        return MarshallerFactory.getInstance().getMarshaller(Role.class).unmarshalDocument(xml);
    }

    /**
     * See Interface for functional description.
     * 
     * @param id
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#delete(java.lang.String)
     */
    @Override
    public void delete(final String id) throws EscidocException, InternalClientException, TransportException {

        checkNotNull(id);

        getClient().delete(id);

    }

    /**
     * See Interface for functional description.
     * 
     * @param container
     * @return
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#update(de.escidoc.core.resources.interfaces.container.ContainerInterface)
     */
    @Override
    public Role update(final Role role) throws EscidocException, InternalClientException, TransportException {

        Marshaller<Role> m = MarshallerFactory.getInstance().getMarshaller(Role.class);

        String xml = getClient().update(role.getObjid(), m.marshalDocument(role));

        return m.unmarshalDocument(xml);
    }

    /**
     * Retrieve Roles (Filter for Roles).
     * 
     * @param request
     *            Filter parameter
     * @return SearchRetrieveResponseType
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public SearchRetrieveResponse retrieveRoles(final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(request);

        String xml = getClient().retrieveRoles(request);

        return MarshallerFactory.getInstance().getMarshaller(SearchRetrieveResponse.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.RoleHandlerClientInterface#
     * retrieveRolesAsList(gov.loc.www.zing.srw.SearchRetrieveRequestType)
     */
    @Override
    public List<Role> retrieveRolesAsList(final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        return getSearchRetrieveResponseAsList(Role.class, retrieveRoles(request));
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
    @Override
    public ExplainResponse retrieveRoles(final ExplainRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(request);

        String xml = getClient().retrieveRoles(request);

        return MarshallerFactory.getInstance().getMarshaller(ExplainResponse.class).unmarshalDocument(xml);
    }

    @Override
    protected RestRoleHandlerClient getRestHandlerClientInstance() throws InternalClientException {
        return new RestRoleHandlerClient(getServiceAddress());
    }
}