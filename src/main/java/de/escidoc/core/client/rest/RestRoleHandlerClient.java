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
package de.escidoc.core.client.rest;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.net.URL;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.RoleHandler;
import de.escidoc.core.client.rest.serviceLocator.RoleRestServiceLocator;

/**
 * REST Handler for Role.
 * 
 * @author SWA
 * 
 */
public class RestRoleHandlerClient extends RestClientBase {

    private static final Logger LOG = LoggerFactory.getLogger(RestRoleHandlerClient.class.getName());

    private RoleHandler restClient = null;

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public RestRoleHandlerClient(final URL serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     * @deprecated Use {@link RestRoleHandlerClient#RestRoleHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public RestRoleHandlerClient(final String serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * Create a Role.
     * 
     * @param role
     *            XML representation of to create Role.
     * @return eSciDoc XML representation of just created Role.
     * @throws EscidocException
     *             Thrown in case of eSciDoc framework failures.
     * @throws InternalClientException
     *             Thrown in case of client internal failures.
     * @throws TransportException
     *             Thrown in case of failures on transport level.
     */
    public String create(final String role) throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().create(role);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * Delete an Role.
     * 
     * @param id
     *            Objid of the to delete Role.
     * @throws EscidocException
     *             Thrown in case of eSciDoc framework failures.
     * @throws InternalClientException
     *             Thrown in case of client internal failures.
     * @throws TransportException
     *             Thrown in case of failures on transport level.
     */
    public void delete(final String id) throws EscidocException, InternalClientException, TransportException {

        try {
            getClient().delete(id);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
    }

    /**
     * Retrieve an Role from repository.
     * 
     * @param id
     *            objid of the Role which is to retrieve.
     * @return XML representation if the Role.
     * @throws EscidocException
     *             Thrown in case of eSciDoc framework failures.
     * @throws InternalClientException
     *             Thrown in case of client internal failures.
     * @throws TransportException
     *             Thrown in case of failures on transport level.
     */
    public String retrieve(final String id) throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieve(id);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * Update an Role.
     * 
     * @param id
     *            Objid of the Role.
     * @param roleXml
     *            XML representation of the Role containing the updated values.
     * @return XML representation of the updated Role.
     * @throws EscidocException
     *             Thrown in case of eSciDoc framework failures.
     * @throws InternalClientException
     *             Thrown in case of client internal failures.
     * @throws TransportException
     *             Thrown in case of failures on transport level.
     */
    public String update(final String id, final String roleXml) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().update(id, roleXml);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * 
     * @param taskParam
     * @return
     * @throws EscidocException
     *             Thrown in case of eSciDoc framework failures.
     * @throws InternalClientException
     *             Thrown in case of client internal failures.
     * @throws TransportException
     *             Thrown in case of failures on transport level.
     */
    @Deprecated
    public String retrieveRoles(final HashMap<String, String[]> taskParam) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveRoles(taskParam);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveRoles(final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException {

        evalRequest(filter, true);

        String result = null;
        try {
            result = getClient().retrieveRoles(filter);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveRoles(final ExplainRequestType filter) throws EscidocException, InternalClientException,
        TransportException {

        evalRequest(filter);

        String result = null;
        try {
            result = getClient().retrieveRoles(filter);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @return Returns the restClient.
     * @throws InternalClientException
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    @Override
    public RoleHandler getClient() throws InternalClientException {

        if (restClient == null) {

            final RoleRestServiceLocator serviceLocator = new RoleRestServiceLocator();
            serviceLocator.registerRestCallbackHandler(this);
            serviceLocator.setServiceAddress(getServiceAddress());
            restClient = serviceLocator;
        }
        return this.restClient;
    }
}