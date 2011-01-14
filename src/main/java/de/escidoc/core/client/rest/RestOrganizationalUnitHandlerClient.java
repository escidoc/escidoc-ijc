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

import org.apache.log4j.Logger;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.OrganizationalUnitHandler;
import de.escidoc.core.client.rest.serviceLocator.OrganizationalUnitRestServiceLocator;

/**
 * REST Handler for OrganizationalUnit.
 * 
 * @author SWA
 * 
 */
public class RestOrganizationalUnitHandlerClient extends RestClientBase {

    private final Logger LOG = Logger
        .getLogger(RestOrganizationalUnitHandlerClient.class);

    private OrganizationalUnitHandler restClient = null;

    /**
     * 
     * @throws InternalClientException
     */
    public RestOrganizationalUnitHandlerClient() throws InternalClientException {
        super();
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public RestOrganizationalUnitHandlerClient(final URL serviceAddress)
        throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     * @deprecated Use
     *             {@link RestOrganizationalUnitHandlerClient#RestOrganizationalUnitHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public RestOrganizationalUnitHandlerClient(final String serviceAddress)
        throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param organizationalUnit
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.OrganizationalUnitHandlerInterface#create(java.lang.String)
     */
    public String create(final String organizationalUnit)
        throws EscidocException, InternalClientException, TransportException {

        if (organizationalUnit == null)
            throw new IllegalArgumentException(
                "organizationalUnit must not be null.");

        String result = null;
        try {
            result = getClient().create(organizationalUnit);
        }
        catch (Exception e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug(e.getMessage(), e);
            }
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * 
     * @param id
     *            objid of Organizational Unit
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.OrganizationalUnitHandlerInterface#delete(java.lang.String)
     */
    public void delete(final String id) throws EscidocException,
        InternalClientException, TransportException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");

        try {
            getClient().delete(id);
        }
        catch (Exception e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug(e.getMessage(), e);
            }
            ExceptionMapper.map(e);
        }
    }

    /**
     * 
     * @param id
     *            objid of Organizational Unit
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.OrganizationalUnitHandlerInterface#retrieve(java.lang.String)
     */
    public String retrieve(final String id) throws EscidocException,
        InternalClientException, TransportException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");

        String result = null;
        try {
            result = getClient().retrieve(id);
        }
        catch (Exception e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug(e.getMessage(), e);
            }
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * 
     * @param id
     *            objid of Organizational Unit
     * @param ouXml
     *            XML representation of Organizational Unit with updated values
     * @return updated XML representation of Organizational Unit
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.OrganizationalUnitHandlerInterface#update(java.lang.String,
     *      java.lang.String)
     */
    public String update(final String id, final String ouXml)
        throws EscidocException, InternalClientException, TransportException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");
        if (ouXml == null)
            throw new IllegalArgumentException("ouXml must not be null.");

        String result = null;
        try {
            result = getClient().update(id, ouXml);
        }
        catch (Exception e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug(e.getMessage(), e);
            }
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * Updates the parents of an Organizational Unit.<br/>
     * <br/>
     * Preconditions:
     * <ul>
     * <li>The Organizational Unit must exist.</li>
     * <li>The public-status is "opened".</li>
     * </ul>
     * 
     * @param ouId
     *            The identifier of the Organizational Unit.
     * @param xmlOfParents
     *            The XML representation of the parents.
     * @return The XML representation of the updated parents.
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String updateParents(final String ouId, final String xmlOfParents)
        throws EscidocException, InternalClientException, TransportException {

        if (ouId == null)
            throw new IllegalArgumentException("ouId must not be null.");
        if (xmlOfParents == null)
            throw new IllegalArgumentException("xmlOfParents must not be null.");

        String result = null;
        try {
            result = getClient().updateParents(ouId, xmlOfParents);
        }
        catch (Exception e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug(e.getMessage(), e);
            }
            ExceptionMapper.map(e);
        }
        return result;
    }

    // /**
    // * Update the md-records of an Organizational Unit.
    // *
    // * @param ouId
    // * The identifier of the Organizational Unit.
    // * @param mdRecordsXml
    // * The XML representation of the md-records.
    // * @return The XML representation of the updated md-records.
    // * @throws EscidocException
    // * @throws InternalClientException
    // * @throws TransportException
    // */
    // public String updateMdRecords(final String ouId, final String
    // mdRecordsXml)
    // throws EscidocException, InternalClientException, TransportException {
    //
    // String result = null;
    // try {
    // result = getClient().updateMdRecords(ouId, mdRecordsXml);
    // }
    // catch (Exception e) {
    // ExceptionMapper.map(e);
    // }
    // return result;
    // }

    /**
     * Retrieve the OrganizationalUnits that are subordinated to this
     * OrganizationalUnit.
     * 
     * @param id
     *            The identifier of the Organizational Unit.
     * 
     * @return The XML representation of the list of child Organizational Units
     *         corresponding to XML-schema "organizational-unit-list.xsd".
     * 
     * @throws EscidocException
     *             e
     * @throws InternalClientException
     *             e
     * @throws TransportException
     *             e
     * @see de.escidoc.core.om.service.interfaces.OrganizationalUnitHandlerInterface#retrieveChildObjects(java.lang.String)
     */
    public String retrieveChildObjects(final String id)
        throws EscidocException, InternalClientException, TransportException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");

        String result = null;
        try {
            result = getClient().retrieveChildObjects(id);
        }
        catch (Exception e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug(e.getMessage(), e);
            }
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * 
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.OrganizationalUnitHandlerInterface#retrieveOrganizationalUnits(java.lang.String)
     */
    @Deprecated
    public String retrieveOrganizationalUnits(
        final HashMap<String, String[]> filter) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveOrganizationalUnits(filter);
        }
        catch (Exception e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug(e.getMessage(), e);
            }
            ExceptionMapper.map(e);
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
    public String retrieveOrganizationalUnits(
        final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException {

        evalRequest(filter, true);

        String result = null;
        try {
            result = getClient().retrieveOrganizationalUnits(filter);
        }
        catch (Exception e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug(e.getMessage(), e);
            }
            ExceptionMapper.map(e);
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
    public String retrieveOrganizationalUnits(final ExplainRequestType filter)
        throws EscidocException, InternalClientException, TransportException {

        if (filter == null)
            throw new IllegalArgumentException("filter must not be null.");

        String result = null;
        try {
            result = getClient().retrieveOrganizationalUnits(filter);
        }
        catch (Exception e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug(e.getMessage(), e);
            }
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * Retrieve all Organizational Units objects to that this OrganizationalUnit
     * is subordinated.
     * 
     * @param id
     *            The identifier of the Organizational Unit.
     * 
     * @return The XML representation of the list of parent Organizational Units
     *         corresponding to XML-schema "organizational-unit-list.xsd".
     * 
     * @throws EscidocException
     *             e
     * @throws InternalClientException
     *             e
     * @throws TransportException
     *             e
     * @see de.escidoc.core.om.service.interfaces.OrganizationalUnitHandlerInterface#retrieveParentObjects(java.lang.String)
     */
    public String retrieveParentObjects(final String id)
        throws EscidocException, InternalClientException, TransportException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");

        String result = null;
        try {
            result = getClient().retrieveParentObjects(id);
        }
        catch (Exception e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug(e.getMessage(), e);
            }
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * Retrieve all Organizational Units references to that this
     * OrganizationalUnit is subordinated.
     * 
     * @param id
     *            The identifier of the Organizational Unit.
     * 
     * @return The XML representation of the list of parent Organizational Units
     *         corresponding to XML-schema "organizational-unit-list.xsd".
     * 
     * @throws EscidocException
     *             e
     * @throws InternalClientException
     *             e
     * @throws TransportException
     *             e
     * @see de.escidoc.core.om.service.interfaces.OrganizationalUnitHandlerInterface#retrieveParentObjects(java.lang.String)
     */
    public String retrieveParents(final String id) throws EscidocException,
        InternalClientException, TransportException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");

        String result = null;
        try {
            result = getClient().retrieveParents(id);
        }
        catch (Exception e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug(e.getMessage(), e);
            }
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * Retrieve the pathList of an OrganizationalUnit. This is a list of all
     * paths from a given organizational unit to all its top level
     * organizational units. Each path contains references to all organizational
     * units of that path.
     * 
     * @param id
     *            The identifier of the Organizational Unit.
     * 
     * @return The XML representation of the path list of that
     *         OrganizationalUnit corresponding to XMLschema
     *         "organizational-unit-path-list.xsd"
     * 
     * @throws EscidocException
     *             e
     * @throws InternalClientException
     *             e
     * @throws TransportException
     *             e
     * @see de.escidoc.core.om.service.interfaces.OrganizationalUnitHandlerInterface#retrievePathList(java.lang.String)
     */
    public String retrievePathList(final String id) throws EscidocException,
        InternalClientException, TransportException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");

        String result = null;
        try {
            result = getClient().retrievePathList(id);
        }
        catch (Exception e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug(e.getMessage(), e);
            }
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String open(final String id, final String taskParam)
        throws EscidocException, InternalClientException, TransportException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");
        if (taskParam == null)
            throw new IllegalArgumentException("taskParam must not be null.");

        String result = null;
        try {
            result = getClient().open(id, taskParam);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String close(final String id, final String taskParam)
        throws EscidocException, InternalClientException, TransportException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");
        if (taskParam == null)
            throw new IllegalArgumentException("taskParam must not be null.");

        String result = null;
        try {
            result = getClient().close(id, taskParam);
        }
        catch (Exception e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug(e.getMessage(), e);
            }
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * @return Returns the restClient.
     * @throws InternalClientException
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    @Override
    public OrganizationalUnitHandler getClient() throws InternalClientException {

        if (restClient == null) {

            OrganizationalUnitRestServiceLocator serviceLocator =
                new OrganizationalUnitRestServiceLocator();
            serviceLocator.registerRestCallbackHandler(this);
            serviceLocator.setServiceAddress(getServiceAddress());
            restClient = serviceLocator;
        }
        return this.restClient;
    }
}