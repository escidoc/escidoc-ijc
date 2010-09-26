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

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.ContentRelationHandlerClientInterface;
import de.escidoc.core.client.rest.RestContentRelationHandlerClient;
import de.escidoc.core.client.soap.SoapContentRelationHandlerClient;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.common.properties.Properties;
import de.escidoc.core.resources.om.contentRelation.ContentRelation;
import de.escidoc.core.resources.sb.Record;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;
import de.escidoc.core.resources.sb.search.records.ContentRelationRecord;

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
    public ContentRelation create(final ContentRelation contentRelation)
        throws EscidocException, InternalClientException, TransportException {

        String contentRelationXml =
            Factory
                .getMarshallerFactory(getTransport())
                .getContentRelationMarshaller()
                .marshalDocument(contentRelation);

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().create(contentRelationXml);
        }
        else {
            xml = getRestHandlerClient().create(contentRelationXml);
        }
        return Factory
            .getMarshallerFactory(getTransport())
            .getContentRelationMarshaller().unmarshalDocument(xml);
    }

    @Override
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
    public ContentRelation retrieve(final String id) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieve(id);
        }
        else {
            xml = getRestHandlerClient().retrieve(id);
        }
        return Factory
            .getMarshallerFactory(getTransport())
            .getContentRelationMarshaller().unmarshalDocument(xml);
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
    public Properties retrieveProperties(final String id)
        throws EscidocException, InternalClientException, TransportException {

        throw new InternalClientException("method not yet supported");
    }

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
    public ContentRelation update(final ContentRelation contentRelation)
        throws EscidocException, InternalClientException, TransportException {

        String contentRelationXml =
            Factory
                .getMarshallerFactory(getTransport())
                .getContentRelationMarshaller()
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
        return Factory
            .getMarshallerFactory(getTransport())
            .getContentRelationMarshaller().unmarshalDocument(xml);
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
    public Result assignObjectPid(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            Factory
                .getMarshallerFactory(getTransport()).getTaskParamMarshaller()
                .marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().assignObjectPid(id, taskParamString);
        }
        else {
            xml = getRestHandlerClient().assignObjectPid(id, taskParamString);
        }
        return Factory
            .getMarshallerFactory(getTransport()).getResultMarshaller()
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
    public Result lock(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            Factory
                .getMarshallerFactory(getTransport()).getTaskParamMarshaller()
                .marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().lock(id, taskParamString);
        }
        else {
            xml = getRestHandlerClient().lock(id, taskParamString);
        }
        return Factory
            .getMarshallerFactory(getTransport()).getResultMarshaller()
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
    public Result unlock(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            Factory
                .getMarshallerFactory(getTransport()).getTaskParamMarshaller()
                .marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().unlock(id, taskParamString);
        }
        else {
            xml = getRestHandlerClient().unlock(id, taskParamString);
        }
        return Factory
            .getMarshallerFactory(getTransport()).getResultMarshaller()
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
    public Result submit(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            Factory
                .getMarshallerFactory(getTransport()).getTaskParamMarshaller()
                .marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().submit(id, taskParamString);
        }
        else {
            xml = getRestHandlerClient().submit(id, taskParamString);
        }

        return Factory
            .getMarshallerFactory(getTransport()).getResultMarshaller()
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
    public Result release(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            Factory
                .getMarshallerFactory(getTransport()).getTaskParamMarshaller()
                .marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().release(id, taskParamString);
        }
        else {
            xml = getRestHandlerClient().release(id, taskParamString);
        }
        return Factory
            .getMarshallerFactory(getTransport()).getResultMarshaller()
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
    public Result revise(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            Factory
                .getMarshallerFactory(getTransport()).getTaskParamMarshaller()
                .marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().revise(id, taskParamString);
        }
        else {
            xml = getRestHandlerClient().revise(id, taskParamString);
        }
        return Factory
            .getMarshallerFactory(getTransport()).getResultMarshaller()
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
    public SearchRetrieveResponse retrieveContentRelations(
        final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException {

        evalRequest(filter);

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveContentRelations(filter);
        }
        else {
            xml = getRestHandlerClient().retrieveContentRelations(filter);
        }
        return Factory
            .getMarshallerFactory(getTransport())
            .getSearchRetrieveResponseMarshaller().unmarshalDocument(xml);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Collection<ContentRelation> retrieveContentRelationsAsList(
        final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException {
        SearchRetrieveResponse response = retrieveContentRelations(filter);
        Collection<ContentRelation> results = new LinkedList<ContentRelation>();

        for (Record record : response.getRecords()) {
            if (record instanceof ContentRelationRecord) {
                ContentRelationRecord cRecord = (ContentRelationRecord) record;
                ContentRelation result = cRecord.getRecordData();
                if (result != null) {
                    results.add(result);
                }
            }
        }
        return results;
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
        return Factory
            .getMarshallerFactory(getTransport())
            .getExplainResponseMarshaller().unmarshalDocument(xml);
    }

    /**
     * See Interface for functional description.
     * 
     * @param id
     *            Id of ContentRelation.
     * @return LastModificationDate of this ContentRelation.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public DateTime getLastModificationDate(final String id)
        throws EscidocException, InternalClientException, TransportException {

        return getSoapHandlerClient().getLastModificationDate(id);
    }

    /**
     * Login.
     * 
     * @param serviceAddress
     *            URL of framework
     * @param username
     *            Username/ID
     * @param password
     *            Password
     * @return Login-Handle.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
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

    /**
     * Logout.
     * 
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Deprecated
    public void logout() throws EscidocException, InternalClientException,
        TransportException {

        setHandle("");
    }

    @Override
    protected SoapContentRelationHandlerClient getSoapHandlerClientInstance()
        throws InternalClientException {
        return new SoapContentRelationHandlerClient();
    }

    @Override
    protected RestContentRelationHandlerClient getRestHandlerClientInstance()
        throws InternalClientException {
        return new RestContentRelationHandlerClient();
    }
}
