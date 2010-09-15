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

import java.util.Collection;
import java.util.LinkedList;

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
import de.escidoc.core.resources.oum.Parents;
import de.escidoc.core.resources.oum.PathList;
import de.escidoc.core.resources.sb.Record;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;
import de.escidoc.core.resources.sb.search.records.OrganizationalUnitRecord;

/**
 * This is the generic OrganizationalUnitClientHandler which binds the transport
 * specific classes. The transport specification is done via properties
 * configuration of the eSciDoc client.
 * 
 * @author SWA
 * 
 */
public class OrganizationalUnitHandlerClient
    extends
    AbstractHandlerClient<SoapOrganizationalUnitHandlerClient, RestOrganizationalUnitHandlerClient>
    implements OrganizationalUnitHandlerClientInterface {

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

        String orgUnitString =
            Factory
                .getMarshallerFactory(getTransport())
                .getOrganizationalUnitMarshaller()
                .marshalDocument(organizationalUnit);
        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().create(orgUnitString);
        }
        else {
            xml = getRestHandlerClient().create(orgUnitString);
        }
        return Factory
            .getMarshallerFactory(getTransport())
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
            xml = getSoapHandlerClient().retrieve(id);
        }
        else {
            xml = getRestHandlerClient().retrieve(id);
        }
        return Factory
            .getMarshallerFactory(getTransport())
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
        String orgUnitString =
            Factory
                .getMarshallerFactory(getTransport())
                .getOrganizationalUnitMarshaller()
                .marshalDocument(organizationalUnit);
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapHandlerClient().update(organizationalUnit.getObjid(),
                    orgUnitString);
        }
        else {
            xml =
                getRestHandlerClient().update(organizationalUnit.getObjid(),
                    orgUnitString);
        }
        return Factory
            .getMarshallerFactory(getTransport())
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
        String taskParamString = marshalTaskParam(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().open(id, taskParamString);
        }
        else {
            xml = getRestHandlerClient().open(id, taskParamString);
        }
        return Factory
            .getMarshallerFactory(getTransport()).getResultMarshaller()
            .unmarshalDocument(xml);
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
        String taskParamString = marshalTaskParam(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().close(id, taskParamString);
        }
        else {
            xml = getRestHandlerClient().close(id, taskParamString);
        }
        return Factory
            .getMarshallerFactory(getTransport()).getResultMarshaller()
            .unmarshalDocument(xml);
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
            getSoapHandlerClient().delete(id);
        }
        else {
            getRestHandlerClient().delete(id);
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

        return getSoapHandlerClient().getLastModificationDate(id);
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
    public Result assignObjectPid(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        throw new InternalClientException("Method not yet supported.");
    }

    /**
     * 
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public Parents retrieveParents(final String id) throws EscidocException,
        InternalClientException, TransportException {
        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveParents(id);
        }
        else {
            xml = getRestHandlerClient().retrieveParents(id);
        }
        return Factory
            .getMarshallerFactory(getTransport()).getParentsMarshaller()
            .unmarshalDocument(xml);
    }

    // public StructMap updateParents(final String id, final String xml)
    // throws EscidocException, InternalClientException, TransportException {
    //
    // throw new InternalClientException("Method not yet supported.");
    // }

    public OrganizationalUnitList retrieveParentObjects(final String id)
        throws EscidocException, InternalClientException, TransportException {
        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveParentObjects(id);
        }
        else {
            xml = getRestHandlerClient().retrieveParentObjects(id);
        }
        return Factory
            .getMarshallerFactory(getTransport())
            .getOrganizationalUnitListMarshaller().unmarshalDocument(xml);
    }

    @Override
    public OrganizationalUnitList retrieveChildObjects(final String id)
        throws EscidocException, InternalClientException, TransportException {
        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveChildObjects(id);
        }
        else {
            xml = getRestHandlerClient().retrieveChildObjects(id);
        }
        return Factory
            .getMarshallerFactory(getTransport())
            .getOrganizationalUnitListMarshaller().unmarshalDocument(xml);
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

        String taskParamString = marshalTaskParam(taskParam);
        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapHandlerClient().retrieveOrganizationalUnits(
                    taskParamString);
        }
        else {
            xml =
                getRestHandlerClient().retrieveOrganizationalUnits(
                    taskParamString);
        }
        return Factory
            .getMarshallerFactory(getTransport())
            .getOrganizationalUnitListMarshaller().unmarshalDocument(xml);
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
    public SearchRetrieveResponse retrieveOrganizationalUnits(
        final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException {

        evalRequest(filter);

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveOrganizationalUnits(filter);
        }
        else {
            xml = getRestHandlerClient().retrieveOrganizationalUnits(filter);
        }
        return Factory
            .getMarshallerFactory(getTransport())
            .getSearchRetrieveResponseMarshaller().unmarshalDocument(xml);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Collection<OrganizationalUnit> retrieveOrganizationalUnitsAsList(
        final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException {

        SearchRetrieveResponse response = retrieveOrganizationalUnits(filter);
        Collection<OrganizationalUnit> results =
            new LinkedList<OrganizationalUnit>();

        for (Record record : response.getRecords()) {
            if (record instanceof OrganizationalUnitRecord) {
                OrganizationalUnitRecord oRecord =
                    (OrganizationalUnitRecord) record;
                OrganizationalUnit result = oRecord.getRecordData();
                if (result != null) {
                    results.add(result);
                }
            }
        }
        return results;
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
    public ExplainResponse retrieveOrganizationalUnits(
        final ExplainRequestType filter) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveOrganizationalUnits(filter);
        }
        else {
            xml = getRestHandlerClient().retrieveOrganizationalUnits(filter);
        }
        return Factory
            .getMarshallerFactory(getTransport())
            .getExplainResponseMarshaller().unmarshalDocument(xml);
    }

    @Override
    public PathList retrievePathList(final String id) throws EscidocException,
        InternalClientException, TransportException {
        throw new InternalClientException("Method not yet supported.");
    }

    @Override
    protected SoapOrganizationalUnitHandlerClient getSoapHandlerClientInstance()
        throws InternalClientException {
        return new SoapOrganizationalUnitHandlerClient();
    }

    @Override
    protected RestOrganizationalUnitHandlerClient getRestHandlerClientInstance()
        throws InternalClientException {
        return new RestOrganizationalUnitHandlerClient();
    }

}
