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
import de.escidoc.core.client.interfaces.ContextHandlerClientInterface;
import de.escidoc.core.client.rest.RestContextHandlerClient;
import de.escidoc.core.client.soap.SoapContextHandlerClient;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.om.GenericVersionableResource;
import de.escidoc.core.resources.om.context.AdminDescriptor;
import de.escidoc.core.resources.om.context.AdminDescriptors;
import de.escidoc.core.resources.om.context.Context;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;

/**
 * This is the generic ContextSoapHandlerClient which binds the transport
 * specific classes. The transport specification is done via properties
 * configuration of the eSciDoc client.
 * 
 * @author SWA
 * 
 */
public class ContextHandlerClient
    extends
    AbstractHandlerClient<SoapContextHandlerClient, RestContextHandlerClient>
    implements ContextHandlerClientInterface {

    /**
     * 
     */
    public ContextHandlerClient() {
        super();
    }

    /**
     * 
     * @param serviceAddress
     */
    public ContextHandlerClient(final URL serviceAddress) {
        super(serviceAddress);
    }

    /**
     * Create Context in Repository.
     * 
     * @param context
     *            The new Context
     * @return The new created Context.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Context create(final Context context) throws EscidocException,
        InternalClientException, TransportException {

        String contextXml =
            MarshallerFactory
                .getInstance(getTransport()).getMarshaller(Context.class)
                .marshalDocument(context);
        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().create(contextXml);
        }
        else {
            xml = getRestHandlerClient().create(contextXml);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Context.class)
            .unmarshalDocument(xml);
    }

    /**
     * Retrieve Context from Repository.
     * 
     * @param id
     *            Objid of the Context
     * @return The Context with the provided obid.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Context retrieve(final String id) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieve(id);
        }
        else {
            xml = getRestHandlerClient().retrieve(id);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Context.class)
            .unmarshalDocument(xml);
    }

    /**
     * Retrieve Properties of Context from Repository.
     * 
     * @param id
     *            Objid of the Context
     * @return The Properties of the Context.
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
     * Delete Context from Repository.
     * 
     * @param id
     *            Objid of the Context
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
     * Update Context.
     * 
     * @param context
     *            New Representation of Context
     * @return The updated Context.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Context update(final Context context) throws EscidocException,
        InternalClientException, TransportException {

        String contextXml =
            MarshallerFactory
                .getInstance(getTransport()).getMarshaller(Context.class)
                .marshalDocument(context);
        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().update(context.getObjid(), contextXml);
        }
        else {
            xml = getRestHandlerClient().update(context.getObjid(), contextXml);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Context.class)
            .unmarshalDocument(xml);
    }

    /**
     * Open Context.
     * 
     * @param id
     *            Objid of the Context
     * @param taskParam
     *            Task parameter to set Context status to open.
     * @return The updated Context.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Result open(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            MarshallerFactory
                .getInstance(getTransport()).getMarshaller(TaskParam.class)
                .marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().open(id, taskParamString);
        }
        else {
            xml = getRestHandlerClient().open(id, taskParamString);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Result.class)
            .unmarshalDocument(xml);
    }

    /**
     * Close Context.
     * 
     * @param id
     *            Objid of the Context
     * @param taskParam
     *            Task parameter to set Context status to close.
     * @return The updated Context.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public Result close(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            MarshallerFactory
                .getInstance(getTransport()).getMarshaller(TaskParam.class)
                .marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().close(id, taskParamString);
        }
        else {
            xml = getRestHandlerClient().close(id, taskParamString);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Result.class)
            .unmarshalDocument(xml);
    }

    /**
     * Retrieve Admin Descriptors from Context.
     * 
     * @param id
     *            Objid of the Context
     * @return The ADminDescriptors of Context.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public AdminDescriptors retrieveAdminDescriptors(final String id)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveAdminDescriptors(id);
        }
        else {
            xml = getRestHandlerClient().retrieveAdminDescriptors(id);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(AdminDescriptors.class)
            .unmarshalDocument(xml);
    }

    /**
     * Retrieve Admin Descriptor from Context.
     * 
     * @param id
     *            Objid of the Context
     * @param name
     *            Name of AdminDescriptor
     * @return AdminDescriptor with provided name
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public AdminDescriptor retrieveAdminDescriptor(
        final String id, final String name) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveAdminDescriptor(id, name);
        }
        else {
            xml = getRestHandlerClient().retrieveAdminDescriptor(id, name);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(AdminDescriptor.class)
            .unmarshalDocument(xml);
    }

    /**
     * Retrieve Contexts (Filter for Contexts).
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
    public SearchRetrieveResponse retrieveContexts(
        final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveContexts(filter);
        }
        else {
            xml = getRestHandlerClient().retrieveContexts(filter);
        }
        return MarshallerFactory
            .getInstance(getTransport())
            .getMarshaller(SearchRetrieveResponse.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.ContextHandlerClientInterface#
     * retrieveContextsAsList(gov.loc.www.zing.srw.SearchRetrieveRequestType)
     */
    @Override
    public List<Context> retrieveContextsAsList(
        final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException {

        return getSearchRetrieveResponseAsList(Context.class,
            retrieveContexts(filter));
    }

    /**
     * Retrieve Contexts (Filter for Contexts).
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
    public ExplainResponse retrieveContexts(final ExplainRequestType filter)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveContexts(filter);
        }
        else {
            xml = getRestHandlerClient().retrieveContexts(filter);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(ExplainResponse.class)
            .unmarshalDocument(xml);
    }

    /**
     * 
     * @param id
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @Override
    public SearchRetrieveResponse retrieveMembers(
        final String id, final SearchRetrieveRequestType filter)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;

        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveMembers(id, filter);
        }
        else {
            xml = getRestHandlerClient().retrieveMembers(id, filter);
        }

        return MarshallerFactory
            .getInstance(getTransport())
            .getMarshaller(SearchRetrieveResponse.class).unmarshalDocument(xml);
    }

    /**
     * 
     * @param id
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @Override
    public List<GenericVersionableResource> retrieveMembersAsList(
        final String id, final SearchRetrieveRequestType filter)
        throws EscidocException, InternalClientException, TransportException {

        return getSearchRetrieveResponseAsList(
            GenericVersionableResource.class, retrieveMembers(id, filter));
    }

    /**
     * 
     * @param id
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @Override
    public ExplainResponse retrieveMembers(
        final String id, final ExplainRequestType filter)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;

        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveMembers(id, filter);
        }
        else {
            xml = getRestHandlerClient().retrieveMembers(id, filter);
        }

        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(ExplainResponse.class)
            .unmarshalDocument(xml);
    }

    @Override
    protected SoapContextHandlerClient getSoapHandlerClientInstance()
        throws InternalClientException {
        return new SoapContextHandlerClient(getServiceAddress());
    }

    @Override
    protected RestContextHandlerClient getRestHandlerClientInstance()
        throws InternalClientException {
        return new RestContextHandlerClient(getServiceAddress());
    }

    @Override
    public Result open(final Context resource, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Result close(final Context resource, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AdminDescriptors retrieveAdminDescriptors(final Context context)
        throws EscidocException, InternalClientException, TransportException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AdminDescriptor retrieveAdminDescriptor(
        final Context context, final String name) throws EscidocException,
        InternalClientException, TransportException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SearchRetrieveResponse retrieveMembers(
        final Context context, final SearchRetrieveRequestType request)
        throws EscidocException, InternalClientException, TransportException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<GenericVersionableResource> retrieveMembersAsList(
        final Context context, final SearchRetrieveRequestType filter)
        throws EscidocException, InternalClientException, TransportException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ExplainResponse retrieveMembers(
        final Context context, final ExplainRequestType request)
        throws EscidocException, InternalClientException, TransportException {
        // TODO Auto-generated method stub
        return null;
    }
}
