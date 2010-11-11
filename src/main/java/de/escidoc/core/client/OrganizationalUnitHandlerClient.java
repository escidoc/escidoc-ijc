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
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.common.jibx.MarshallerFactory;
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
     * 
     */
    public OrganizationalUnitHandlerClient() {
        super();
    }

    /**
     * 
     * @param serviceAddress
     */
    public OrganizationalUnitHandlerClient(final String serviceAddress) {
        super(serviceAddress);
    }

    /**
     * See Interface for functional description.
     * 
     * @param organizationalUnit
     * @return
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface#create(de.escidoc.core.resources.interfaces.organizationalUnit.OrganizationalUnitInterface)
     */
    @Override
    public OrganizationalUnit create(final OrganizationalUnit organizationalUnit)
        throws EscidocException, InternalClientException, TransportException {

        if (organizationalUnit == null)
            throw new IllegalArgumentException(
                "organizationalUnit must not be null.");

        String xml = getMarshaller().marshalDocument(organizationalUnit);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().create(xml);
        }
        else {
            xml = getRestHandlerClient().create(xml);
        }
        return getMarshaller().unmarshalDocument(xml);
    }

    /**
     * See Interface for functional description.
     * 
     * @param id
     * @return
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface#retrieve(java.lang.String)
     */
    @Override
    public OrganizationalUnit retrieve(final String id)
        throws EscidocException, InternalClientException, TransportException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieve(id);
        }
        else {
            xml = getRestHandlerClient().retrieve(id);
        }
        return getMarshaller().unmarshalDocument(xml);
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
    @Override
    public OrganizationalUnit update(final OrganizationalUnit organizationalUnit)
        throws EscidocException, InternalClientException, TransportException {

        if (organizationalUnit == null)
            throw new IllegalArgumentException(
                "organizationalUnit must not be null.");

        String xml = getMarshaller().marshalDocument(organizationalUnit);
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapHandlerClient().update(organizationalUnit.getObjid(),
                    xml);
        }
        else {
            xml =
                getRestHandlerClient().update(organizationalUnit.getObjid(),
                    xml);
        }
        return getMarshaller().unmarshalDocument(xml);
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
     * @param id
     *            The identifier of the Organizational Unit.
     * @param parents
     *            The Parents object of the corresponding Organizational Unit.
     * @return The updated Parents object of the Organizational Unit.
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public Parents updateParents(final String id, final Parents parents)
        throws EscidocException, InternalClientException, TransportException {
        if (id == null)
            throw new IllegalArgumentException("id must not be null.");
        if (parents == null)
            throw new IllegalArgumentException("parents must not be null.");

        Marshaller<Parents> m =
            Factory.getMarshallerFactory(getTransport()).getMarshaller(
                MarshallerFactory.CLASS_PARENTS);
        String xml = m.marshalDocument(parents);

        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().updateParents(id, xml);
        }
        else {
            xml = getRestHandlerClient().updateParents(id, xml);
        }
        return m.unmarshalDocument(xml);
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
    @Override
    public Result open(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");
        if (taskParam == null)
            throw new IllegalArgumentException("taskParam must not be null.");

        String xml = marshalTaskParam(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().open(id, xml);
        }
        else {
            xml = getRestHandlerClient().open(id, xml);
        }
        return Factory
            .getMarshallerFactory(getTransport()).getMarshaller(Result.class)
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
    @Override
    public Result close(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");
        if (taskParam == null)
            throw new IllegalArgumentException("taskParam must not be null.");

        String xml = marshalTaskParam(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().close(id, xml);
        }
        else {
            xml = getRestHandlerClient().close(id, xml);
        }
        return Factory
            .getMarshallerFactory(getTransport()).getMarshaller(Result.class)
            .unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.ResourceHandlerInterface#retrieveProperties
     * (java.lang.String)
     */
    @Override
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
    @Override
    public void delete(final String id) throws EscidocException,
        InternalClientException, TransportException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");

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
    @Override
    @Deprecated
    public DateTime getLastModificationDate(final String id)
        throws EscidocException, InternalClientException, TransportException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");

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
     * de.escidoc.core.client.interfaces.ResourceHandlerInterface#assignObjectPid
     * (java.lang.String, de.escidoc.core.resources.common.TaskParam)
     */
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
    @Override
    public Parents retrieveParents(final String id) throws EscidocException,
        InternalClientException, TransportException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveParents(id);
        }
        else {
            xml = getRestHandlerClient().retrieveParents(id);
        }
        return Factory
            .getMarshallerFactory(getTransport())
            .getMarshaller(MarshallerFactory.CLASS_PARENTS)
            .unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface
     * #retrieveParentObjects(java.lang.String)
     */
    @Override
    public OrganizationalUnitList retrieveParentObjects(final String id)
        throws EscidocException, InternalClientException, TransportException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveParentObjects(id);
        }
        else {
            xml = getRestHandlerClient().retrieveParentObjects(id);
        }
        return Factory
            .getMarshallerFactory(getTransport())
            .getMarshaller(MarshallerFactory.CLASS_ORGANIZATIONAL_UNIT_LIST)
            .unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface
     * #retrieveChildObjects(java.lang.String)
     */
    @Override
    public OrganizationalUnitList retrieveChildObjects(final String id)
        throws EscidocException, InternalClientException, TransportException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveChildObjects(id);
        }
        else {
            xml = getRestHandlerClient().retrieveChildObjects(id);
        }
        return Factory
            .getMarshallerFactory(getTransport())
            .getMarshaller(MarshallerFactory.CLASS_ORGANIZATIONAL_UNIT_LIST)
            .unmarshalDocument(xml);
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
    @Override
    @Deprecated
    public OrganizationalUnitList retrieveOrganizationalUnits(
        final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        if (taskParam == null)
            throw new IllegalArgumentException("taskParam must not be null.");

        String xml = marshalTaskParam(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveOrganizationalUnits(xml);
        }
        else {
            xml = getRestHandlerClient().retrieveOrganizationalUnits(xml);
        }
        return Factory
            .getMarshallerFactory(getTransport())
            .getMarshaller(MarshallerFactory.CLASS_ORGANIZATIONAL_UNIT_LIST)
            .unmarshalDocument(xml);
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
    @Override
    public SearchRetrieveResponse retrieveOrganizationalUnits(
        final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        if (request == null)
            throw new IllegalArgumentException("request must not be null.");

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveOrganizationalUnits(request);
        }
        else {
            xml = getRestHandlerClient().retrieveOrganizationalUnits(request);
        }
        return Factory
            .getMarshallerFactory(getTransport())
            .getMarshaller(MarshallerFactory.CLASS_SEARCH_RETRIEVE_RESPONSE)
            .unmarshalDocument(xml);
    }

    /**
     * 
     */
    @Override
    public Collection<OrganizationalUnit> retrieveOrganizationalUnitsAsList(
        final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        if (request == null)
            throw new IllegalArgumentException("filter must not be null.");

        SearchRetrieveResponse response = retrieveOrganizationalUnits(request);
        Collection<OrganizationalUnit> results =
            new LinkedList<OrganizationalUnit>();

        for (Record<?> record : response.getRecords()) {
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
     * @param request
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
    public ExplainResponse retrieveOrganizationalUnits(
        final ExplainRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        if (request == null)
            throw new IllegalArgumentException("request must not be null.");

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveOrganizationalUnits(request);
        }
        else {
            xml = getRestHandlerClient().retrieveOrganizationalUnits(request);
        }
        return Factory
            .getMarshallerFactory(getTransport())
            .getMarshaller(MarshallerFactory.CLASS_EXPLAIN_RESPONSE)
            .unmarshalDocument(xml);
    }

    @Override
    public PathList retrievePathList(final String id) throws EscidocException,
        InternalClientException, TransportException {
        throw new InternalClientException("Method not yet supported.");
    }

    @Override
    protected SoapOrganizationalUnitHandlerClient getSoapHandlerClientInstance()
        throws InternalClientException {
        return new SoapOrganizationalUnitHandlerClient(getServiceAddress());
    }

    @Override
    protected RestOrganizationalUnitHandlerClient getRestHandlerClientInstance()
        throws InternalClientException {
        return new RestOrganizationalUnitHandlerClient(getServiceAddress());
    }

    /**
     * 
     * @return
     * @throws InternalClientException
     */
    private Marshaller<OrganizationalUnit> getMarshaller()
        throws InternalClientException {
        return Factory.getMarshallerFactory(getTransport()).getMarshaller(
            MarshallerFactory.CLASS_ORGANIZATIONAL_UNIT);
    }
}
