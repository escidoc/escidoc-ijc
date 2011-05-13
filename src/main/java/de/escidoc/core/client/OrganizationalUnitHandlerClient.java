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
import de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface;
import de.escidoc.core.client.rest.RestOrganizationalUnitHandlerClient;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.oum.OrganizationalUnit;
import de.escidoc.core.resources.oum.OrganizationalUnitList;
import de.escidoc.core.resources.oum.OrganizationalUnitProperties;
import de.escidoc.core.resources.oum.Parents;
import de.escidoc.core.resources.oum.PathList;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;

/**
 * This is the generic OrganizationalUnitClientHandler which binds the transport
 * specific classes. The transport specification is done via properties
 * configuration of the eSciDoc client.
 * 
 * @author SWA
 * 
 */
public class OrganizationalUnitHandlerClient extends AbstractHandlerClient<RestOrganizationalUnitHandlerClient>
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
    public OrganizationalUnitHandlerClient(final URL serviceAddress) {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @deprecated Use
     *             {@link OrganizationalUnitHandlerClient#OrganizationalUnitHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public OrganizationalUnitHandlerClient(final String serviceAddress) {
        super(serviceAddress);
    }

    @Override
    protected RestOrganizationalUnitHandlerClient getRestHandlerClientInstance() throws InternalClientException {
        return new RestOrganizationalUnitHandlerClient(getServiceAddress());
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
    public OrganizationalUnit create(final OrganizationalUnit organizationalUnit) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(organizationalUnit);

        Marshaller<OrganizationalUnit> m = MarshallerFactory.getInstance().getMarshaller(OrganizationalUnit.class);

        String xml = getClient().create(m.marshalDocument(organizationalUnit));

        return m.unmarshalDocument(xml);
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
    public OrganizationalUnit retrieve(final String id) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);

        String xml = getClient().retrieve(id);

        return MarshallerFactory.getInstance().getMarshaller(OrganizationalUnit.class).unmarshalDocument(xml);
    }

    /**
     * See Interface for functional description.
     * 
     * @param organizationalUnit
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.interfaces.client.base.CRUDService#update(java.lang.Object)
     */
    @Override
    public OrganizationalUnit update(final OrganizationalUnit organizationalUnit) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(organizationalUnit);

        Marshaller<OrganizationalUnit> m = MarshallerFactory.getInstance().getMarshaller(OrganizationalUnit.class);

        String xml = getClient().update(organizationalUnit.getObjid(), m.marshalDocument(organizationalUnit));

        return m.unmarshalDocument(xml);
    }

    @Override
    public Parents updateParents(final OrganizationalUnit ou, final Parents parents) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(ou);
        checkNotNull(parents);

        // check last modification date
        if (parents.getLastModificationDate() == null) {
            if (ou.getParents() == null || ou.getParents().getLastModificationDate() == null) {
                parents.setLastModificationDate(ou.getLastModificationDate());
            }
            else if (ou.getParents() != null) {
                parents.setLastModificationDate(ou.getParents().getLastModificationDate());
            }
        }

        Marshaller<Parents> m = MarshallerFactory.getInstance().getMarshaller(Parents.class);

        String xml = getClient().updateParents(ou.getObjid(), m.marshalDocument(parents));

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
    public Result open(final String id, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);
        checkNotNull(taskParam);

        String xml = getClient().open(id, marshalTaskParam(taskParam));

        return MarshallerFactory.getInstance().getMarshaller(Result.class).unmarshalDocument(xml);
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
    public Result close(final String id, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);
        checkNotNull(taskParam);

        String xml = getClient().close(id, marshalTaskParam(taskParam));

        return MarshallerFactory.getInstance().getMarshaller(Result.class).unmarshalDocument(xml);
    }

    /**
     * See Interface for functional description.
     * 
     * @param id
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface#delete(java.lang.String)
     */
    @Override
    public void delete(final String id) throws EscidocException, InternalClientException, TransportException {

        checkNotNull(id);

        getClient().delete(id);
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
    public Parents retrieveParents(final String id) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);

        String xml = getClient().retrieveParents(id);

        return MarshallerFactory.getInstance().getMarshaller(Parents.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface
     * #retrieveParentObjects(de.escidoc.core.resources.oum.OrganizationalUnit)
     */
    @Override
    public SearchRetrieveResponse retrieveParentObjects(final OrganizationalUnit ou) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(ou);
        return retrieveParentObjects(ou.getObjid());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface
     * #retrieveParentObjects(java.lang.String)
     */
    @Override
    public SearchRetrieveResponse retrieveParentObjects(final String id) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(id);

        String xml = getClient().retrieveParentObjects(id);

        return MarshallerFactory.getInstance().getMarshaller(SearchRetrieveResponse.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface
     * #retrieveParentObjects(de.escidoc.core.resources.oum.OrganizationalUnit)
     */
    @Override
    public List<OrganizationalUnit> retrieveParentObjectsAsList(final OrganizationalUnit ou) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(ou);

        return retrieveParentObjectsAsList(ou.getObjid());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface
     * #retrieveParentObjectsAsList(java.lang.String)
     */
    @Override
    public List<OrganizationalUnit> retrieveParentObjectsAsList(final String id) throws EscidocException,
        InternalClientException, TransportException {

        return getSearchRetrieveResponseAsList(OrganizationalUnit.class, retrieveParentObjects(id));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface
     * #retrieveChildObjects(de.escidoc.core.resources.oum.OrganizationalUnit)
     */
    @Override
    public SearchRetrieveResponse retrieveChildObjects(final OrganizationalUnit ou) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(ou);

        return retrieveChildObjects(ou.getObjid());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface
     * #retrieveChildObjects(java.lang.String)
     */
    @Override
    public SearchRetrieveResponse retrieveChildObjects(final String id) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(id);

        String xml = getClient().retrieveChildObjects(id);

        return MarshallerFactory.getInstance().getMarshaller(SearchRetrieveResponse.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface
     * #
     * retrieveChildObjectsAsList(de.escidoc.core.resources.oum.OrganizationalUnit
     * )
     */
    @Override
    public List<OrganizationalUnit> retrieveChildObjectsAsList(final OrganizationalUnit ou) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(ou);

        return getSearchRetrieveResponseAsList(OrganizationalUnit.class, retrieveChildObjects(ou.getObjid()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface
     * #retrieveChildObjectsAsList(java.lang.String)
     */
    @Override
    public List<OrganizationalUnit> retrieveChildObjectsAsList(final String id) throws EscidocException,
        InternalClientException, TransportException {

        return getSearchRetrieveResponseAsList(OrganizationalUnit.class, retrieveChildObjects(id));
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
    public SearchRetrieveResponse retrieveOrganizationalUnits(final SearchRetrieveRequestType request)
        throws EscidocException, InternalClientException, TransportException {

        checkNotNull(request);

        String xml = getClient().retrieveOrganizationalUnits(request);

        return MarshallerFactory.getInstance().getMarshaller(SearchRetrieveResponse.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface
     * #retrieveOrganizationalUnitsAsList(gov.loc.www.zing.srw.
     * SearchRetrieveRequestType)
     */
    @Override
    public List<OrganizationalUnit> retrieveOrganizationalUnitsAsList(final SearchRetrieveRequestType request)
        throws EscidocException, InternalClientException, TransportException {

        return getSearchRetrieveResponseAsList(OrganizationalUnit.class, retrieveOrganizationalUnits(request));
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
    public ExplainResponse retrieveOrganizationalUnits(final ExplainRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(request);

        String xml = getClient().retrieveOrganizationalUnits(request);

        return MarshallerFactory.getInstance().getMarshaller(ExplainResponse.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface
     * #retrievePathList(java.lang.String)
     */
    @Override
    public PathList retrievePathList(final String id) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);

        String xml = getClient().retrievePathList(id);

        return MarshallerFactory.getInstance().getMarshaller(PathList.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.OpenCloseService#open(java.lang
     * .Object, de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public Result open(final OrganizationalUnit resource, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(resource);

        return open(resource.getObjid(), taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.OpenCloseService#close(java.lang
     * .Object, de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public Result close(final OrganizationalUnit resource, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(resource);

        return close(resource.getObjid(), taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.PropertiesService#retrieveProperties
     * (java.lang.String)
     */
    @Override
    public OrganizationalUnitProperties retrieveProperties(final String id) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(id);

        String xml = getClient().retrieveProperties(id);

        return MarshallerFactory.getInstance().getMarshaller(OrganizationalUnitProperties.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.MdRecordService#retrieveMdRecords
     * (java.lang.String)
     */
    @Override
    public MetadataRecords retrieveMdRecords(final String id) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);

        String xml = getClient().retrieveMdRecords(id);

        return MarshallerFactory.getInstance().getMarshaller(MetadataRecords.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.MdRecordService#retrieveMdRecord
     * (java.lang.String, java.lang.String)
     */
    @Override
    public MetadataRecord retrieveMdRecord(final String id, final String mdRecordId) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(id);
        checkNotNull(mdRecordId);

        String xml = getClient().retrieveMdRecord(id, mdRecordId);

        return MarshallerFactory.getInstance().getMarshaller(MetadataRecord.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface
     * #retrieveSuccessors(java.lang.String)
     */
    @Override
    public OrganizationalUnitList retrieveSuccessors(final String id) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);

        String xml = getClient().retrieveSuccessors(id);

        return MarshallerFactory.getInstance().getMarshaller(OrganizationalUnitList.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface
     * #retrieveSuccessors(de.escidoc.core.resources.oum.OrganizationalUnit)
     */
    @Override
    public OrganizationalUnitList retrieveSuccessors(final OrganizationalUnit ou) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(ou);

        return retrieveSuccessors(ou.getObjid());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface
     * #retrievePathList(de.escidoc.core.resources.oum.OrganizationalUnit)
     */
    @Override
    public PathList retrievePathList(final OrganizationalUnit ou) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(ou);

        return retrievePathList(ou.getObjid());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface
     * #updateParents(java.lang.String, de.escidoc.core.resources.oum.Parents)
     */
    @Override
    public Parents updateParents(final String id, final Parents parents) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(id);
        checkNotNull(parents);

        Marshaller<Parents> m = MarshallerFactory.getInstance().getMarshaller(Parents.class);

        String xml = getClient().updateParents(id, m.marshalDocument(parents));

        return m.unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface
     * #retrieveParents(de.escidoc.core.resources.oum.OrganizationalUnit)
     */
    @Override
    public Parents retrieveParents(final OrganizationalUnit ou) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(ou);

        return retrieveParents(ou.getObjid());
    }
}