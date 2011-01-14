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

import java.net.URL;
import java.util.List;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.ContentRelationHandlerClientInterface;
import de.escidoc.core.client.rest.RestContentRelationHandlerClient;
import de.escidoc.core.client.soap.SoapContentRelationHandlerClient;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.om.contentRelation.ContentRelation;
import de.escidoc.core.resources.om.contentRelation.ContentRelationProperties;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;

/**
 * This is the generic ContentRelationSoapHandlerClient which binds the
 * transport specific classes. The transport specification is done via
 * properties configuration of the eSciDoc client.
 * 
 * @author SWA
 * 
 */
public class ContentRelationHandlerClient
    extends
    AbstractHandlerClient<SoapContentRelationHandlerClient, RestContentRelationHandlerClient>
    implements ContentRelationHandlerClientInterface {

    /**
     * 
     */
    public ContentRelationHandlerClient() {
        super();
    }

    /**
     * 
     * @param serviceAddress
     */
    public ContentRelationHandlerClient(final URL serviceAddress) {
        super(serviceAddress);
    }

    @Override
    protected SoapContentRelationHandlerClient getSoapHandlerClientInstance()
        throws InternalClientException {
        return new SoapContentRelationHandlerClient(getServiceAddress());
    }

    @Override
    protected RestContentRelationHandlerClient getRestHandlerClientInstance()
        throws InternalClientException {
        return new RestContentRelationHandlerClient(getServiceAddress());
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
    @Override
    public ContentRelation create(final ContentRelation contentRelation)
        throws EscidocException, InternalClientException, TransportException {

        String contentRelationXml =
            MarshallerFactory
                .getInstance(getTransport())
                .getMarshaller(ContentRelation.class)
                .marshalDocument(contentRelation);

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().create(contentRelationXml);
        }
        else {
            xml = getRestHandlerClient().create(contentRelationXml);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(ContentRelation.class)
            .unmarshalDocument(xml);
    }

    /**
     * @param contentRelation
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @deprecated Signature convention: Use
     *             {@link ContentRelationHandlerClient#retrieve(String)}
     *             instead.
     */
    @Deprecated
    public ContentRelation retrieve(final ContentRelation contentRelation)
        throws EscidocException, InternalClientException, TransportException {

        return retrieve(contentRelation.getObjid());
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
    @Override
    public ContentRelation retrieve(final String id) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieve(id);
        }
        else {
            xml = getRestHandlerClient().retrieve(id);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(ContentRelation.class)
            .unmarshalDocument(xml);
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
    // @Override
    // public OrganizationalUnitProperties retrieveProperties(final String id)
    // throws EscidocException, InternalClientException, TransportException {
    //
    // throw new InternalClientException("method not yet supported");
    // }

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
    @Override
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
    @Override
    public ContentRelation update(final ContentRelation contentRelation)
        throws EscidocException, InternalClientException, TransportException {

        String contentRelationXml =
            MarshallerFactory
                .getInstance(getTransport())
                .getMarshaller(ContentRelation.class)
                .marshalDocument(contentRelation);
        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapHandlerClient().update(contentRelation.getObjid(),
                    contentRelationXml);
        }
        else {
            xml =
                getRestHandlerClient().update(contentRelation.getObjid(),
                    contentRelationXml);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(ContentRelation.class)
            .unmarshalDocument(xml);
    }

    /**
     * Assign Persistent Identifier for ContentRelation (object).
     * 
     * @param cr
     *            ContentRelation
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
    @Override
    public Result assignObjectPid(
        final ContentRelation cr, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        return assignObjectPid(cr.getObjid(), taskParam);
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
    @Override
    public Result assignObjectPid(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            MarshallerFactory
                .getInstance(getTransport()).getMarshaller(TaskParam.class)
                .marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().assignObjectPid(id, taskParamString);
        }
        else {
            xml = getRestHandlerClient().assignObjectPid(id, taskParamString);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Result.class)
            .unmarshalDocument(xml);
    }

    /**
     * Lock the ContentRelation.
     * 
     * @param cr
     *            ContentRelation.
     * @param taskParam
     *            TaskParameter.
     * @return Result message of method.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Result lock(final ContentRelation cr, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        return lock(cr.getObjid(), taskParam);
    }

    /**
     * Lock the ContentRelation.
     * 
     * @param id
     *            Objid of ContentRelation.
     * @param taskParam
     *            TaskParameter.
     * @return Result message of method.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Result lock(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            MarshallerFactory
                .getInstance(getTransport()).getMarshaller(TaskParam.class)
                .marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().lock(id, taskParamString);
        }
        else {
            xml = getRestHandlerClient().lock(id, taskParamString);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Result.class)
            .unmarshalDocument(xml);
    }

    /**
     * Unlock the ContentRelation.
     * 
     * @param cr
     *            ContentRelation.
     * @param taskParam
     *            TaskParameter.
     * @return Result message of method.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Result unlock(final ContentRelation cr, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        return unlock(cr.getObjid(), taskParam);
    }

    /**
     * Unlock the ContentRelation.
     * 
     * @param id
     *            Objid of ContentRelation.
     * @param taskParam
     *            TaskParameter.
     * @return Result message of method.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Result unlock(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            MarshallerFactory
                .getInstance(getTransport()).getMarshaller(TaskParam.class)
                .marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().unlock(id, taskParamString);
        }
        else {
            xml = getRestHandlerClient().unlock(id, taskParamString);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Result.class)
            .unmarshalDocument(xml);
    }

    /*
     * Status methods
     */

    /**
     * Set ContentRelation to status submit.
     * 
     * @param id
     *            Objid of ContentRelation.
     * @param taskParam
     *            TaskParameter for submit.
     * @return Result message of method.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Result submit(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            MarshallerFactory
                .getInstance(getTransport()).getMarshaller(TaskParam.class)
                .marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().submit(id, taskParamString);
        }
        else {
            xml = getRestHandlerClient().submit(id, taskParamString);
        }

        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Result.class)
            .unmarshalDocument(xml);
    }

    /**
     * Set ContentRelation to status submit.
     * 
     * @param contentRelation
     *            The ContentRelation.
     * @param taskParam
     *            TaskParameter for submit.
     * @return Result message of method.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Result submit(
        final ContentRelation contentRelation, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        return submit(contentRelation.getObjid(), taskParam);
    }

    /**
     * Set ContentRelation to status released.
     * 
     * @param id
     *            Objid of ContentRelation.
     * @param taskParam
     *            TaskParameter for release.
     * @return Result message of method.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Result release(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            MarshallerFactory
                .getInstance(getTransport()).getMarshaller(TaskParam.class)
                .marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().release(id, taskParamString);
        }
        else {
            xml = getRestHandlerClient().release(id, taskParamString);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Result.class)
            .unmarshalDocument(xml);
    }

    /**
     * Set ContentRelation to status released.
     * 
     * @param contentRelation
     *            The ContentRelation.
     * @param taskParam
     *            TaskParameter for release.
     * @return Result message of method.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Result release(
        final ContentRelation contentRelation, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        return release(contentRelation.getObjid(), taskParam);
    }

    /**
     * Set ContentRelation to status revised.
     * 
     * @param id
     *            Objid of ContentRelation.
     * @param taskParam
     *            TaskParameter for revised.
     * @return Result message of method.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Result revise(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            MarshallerFactory
                .getInstance(getTransport()).getMarshaller(TaskParam.class)
                .marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().revise(id, taskParamString);
        }
        else {
            xml = getRestHandlerClient().revise(id, taskParamString);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Result.class)
            .unmarshalDocument(xml);
    }

    /**
     * Set ContentRelation to status revised.
     * 
     * @param contentRelation
     *            The ContentRelation.
     * @param taskParam
     *            TaskParameter for revise.
     * @return Result message of method.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Result revise(
        final ContentRelation contentRelation, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        return revise(contentRelation.getObjid(), taskParam);
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
    @Override
    public SearchRetrieveResponse retrieveContentRelations(
        final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveContentRelations(filter);
        }
        else {
            xml = getRestHandlerClient().retrieveContentRelations(filter);
        }
        return MarshallerFactory
            .getInstance(getTransport())
            .getMarshaller(SearchRetrieveResponse.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.ContentRelationHandlerClientInterface
     * #retrieveContentRelationsAsList
     * (gov.loc.www.zing.srw.SearchRetrieveRequestType)
     */
    @Override
    public List<ContentRelation> retrieveContentRelationsAsList(
        final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException {

        return getSearchRetrieveResponseAsList(ContentRelation.class,
            retrieveContentRelations(filter));
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
    @Override
    public ExplainResponse retrieveContentRelations(
        final ExplainRequestType filter) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveContentRelations(filter);
        }
        else {
            xml = getRestHandlerClient().retrieveContentRelations(filter);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(ExplainResponse.class)
            .unmarshalDocument(xml);
    }

    @Override
    public ContentRelationProperties retrieveProperties(final String id)
        throws EscidocException, InternalClientException, TransportException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ContentRelationProperties retrieveProperties(
        final ContentRelation resource) throws EscidocException,
        InternalClientException, TransportException {
        // TODO Auto-generated method stub
        return null;
    }
}
