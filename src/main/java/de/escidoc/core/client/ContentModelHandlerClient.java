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
import de.escidoc.core.client.interfaces.ContentModelHandlerClientInterface;
import de.escidoc.core.client.rest.RestContentModelHandlerClient;
import de.escidoc.core.client.soap.SoapContentModelHandlerClient;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.HttpInputStream;
import de.escidoc.core.resources.cmm.ContentModel;
import de.escidoc.core.resources.cmm.ContentModelProperties;
import de.escidoc.core.resources.common.ContentStream;
import de.escidoc.core.resources.common.ContentStreams;
import de.escidoc.core.resources.common.versionhistory.VersionHistory;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;

/**
 * This is the generic ContentModelSoapContentModelHandlerClient which binds the
 * transport specific classes. The transport specification is done via
 * properties configuration of the eSciDoc client.
 * 
 * @author SWA
 * 
 */
public class ContentModelHandlerClient
    extends
    AbstractHandlerClient<SoapContentModelHandlerClient, RestContentModelHandlerClient>
    implements ContentModelHandlerClientInterface {

    /**
     * 
     */
    public ContentModelHandlerClient() {
        super();
    }

    /**
     * 
     * @param serviceAddress
     */
    public ContentModelHandlerClient(final URL serviceAddress) {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @deprecated Use
     *             {@link ContentModelHandlerClient#ContentModelHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public ContentModelHandlerClient(final String serviceAddress) {
        super(serviceAddress);
    }

    @Override
    protected SoapContentModelHandlerClient getSoapHandlerClientInstance()
        throws InternalClientException {
        return new SoapContentModelHandlerClient(getServiceAddress());
    }

    @Override
    protected RestContentModelHandlerClient getRestHandlerClientInstance()
        throws InternalClientException {
        return new RestContentModelHandlerClient(getServiceAddress());
    }

    /**
     * Create ContentModel in Repository.
     * 
     * @param contentModel
     *            The new ContentModel
     * @return The new created ContentModel.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public ContentModel create(final ContentModel contentModel)
        throws EscidocException, InternalClientException, TransportException {

        checkNotNull(contentModel);

        Marshaller<ContentModel> m =
            MarshallerFactory.getInstance().getMarshaller(ContentModel.class);

        String xml =
            getRestHandlerClient().create(m.marshalDocument(contentModel));

        return m.unmarshalDocument(xml);
    }

    /**
     * Retrieve ContentModel from Repository.
     * 
     * @param id
     *            Objid of the ContentModel
     * @return The ContentModel with the provided obid.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public ContentModel retrieve(final String id) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(id);

        String xml = getRestHandlerClient().retrieve(id);

        return MarshallerFactory
            .getInstance().getMarshaller(ContentModel.class)
            .unmarshalDocument(xml);
    }

    /**
     * Delete ContentModel from Repository.
     * 
     * @param id
     *            Objid of the ContentModel
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

        checkNotNull(id);

        getRestHandlerClient().delete(id);
    }

    /**
     * Update ContentModel.
     * 
     * @param contentModel
     *            New Representation of ContentModel
     * @return The updated ContentModel.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Override
    public ContentModel update(final ContentModel contentModel)
        throws EscidocException, InternalClientException, TransportException {

        checkNotNull(contentModel);

        Marshaller<ContentModel> m =
            MarshallerFactory.getInstance().getMarshaller(ContentModel.class);

        String xml =
            getRestHandlerClient().update(contentModel.getObjid(),
                m.marshalDocument(contentModel));

        return m.unmarshalDocument(xml);
    }

    @Override
    public ExplainResponse retrieveContentModels(
        final ExplainRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        String xml = getRestHandlerClient().retrieveContentModels(request);

        return MarshallerFactory
            .getInstance().getMarshaller(ExplainResponse.class)
            .unmarshalDocument(xml);
    }

    @Override
    public SearchRetrieveResponse retrieveContentModels(
        final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        String xml = getRestHandlerClient().retrieveContentModels(request);

        return MarshallerFactory
            .getInstance().getMarshaller(SearchRetrieveResponse.class)
            .unmarshalDocument(xml);
    }

    @Override
    public List<ContentModel> retrieveContentModelsAsList(
        final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        return getSearchRetrieveResponseAsList(ContentModel.class,
            retrieveContentModels(request));
    }

    @Override
    public ContentModelProperties retrieveProperties(final String id)
        throws EscidocException, InternalClientException, TransportException {

        checkNotNull(id);

        String xml = getRestHandlerClient().retrieveProperties(id);

        return MarshallerFactory
            .getInstance().getMarshaller(ContentModelProperties.class)
            .unmarshalDocument(xml);
    }

    @Override
    public VersionHistory retrieveVersionHistory(final String id)
        throws EscidocClientException, InternalClientException,
        TransportException {

        checkNotNull(id);

        String xml = getRestHandlerClient().retrieveVersionHistory(id);

        return MarshallerFactory
            .getInstance().getMarshaller(VersionHistory.class)
            .unmarshalDocument(xml);
    }

    @Override
    public VersionHistory retrieveVersionHistory(final ContentModel resource)
        throws EscidocClientException, InternalClientException,
        TransportException {

        checkNotNull(resource);

        return retrieveVersionHistory(resource.getObjid());
    }

    @Override
    public ContentStreams retrieveContentStreams(final String id)
        throws EscidocException, InternalClientException, TransportException {

        checkNotNull(id);

        String xml = getRestHandlerClient().retrieveContentStreams(id);

        return MarshallerFactory
            .getInstance().getMarshaller(ContentStreams.class)
            .unmarshalDocument(xml);
    }

    @Override
    public ContentStream retrieveContentStream(
        final String id, final String name) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(id);
        checkNotNull(name);

        String xml = getRestHandlerClient().retrieveContentStream(id, name);

        return MarshallerFactory
            .getInstance().getMarshaller(ContentStream.class)
            .unmarshalDocument(xml);
    }

    @Override
    public HttpInputStream retrieveContentStreamContent(
        final String id, final String name) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(id);
        checkNotNull(name);

        return getRestHandlerClient().retrieveContentStreamContent(id, name);
    }
}