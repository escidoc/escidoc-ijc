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
public class ContentModelHandlerClient extends AbstractHandlerClient<RestContentModelHandlerClient>
    implements ContentModelHandlerClientInterface {

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

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.AbstractHandlerClient#getRestHandlerClientInstance
     * ()
     */
    @Override
    protected RestContentModelHandlerClient getRestHandlerClientInstance() throws InternalClientException {
        return new RestContentModelHandlerClient(getServiceAddress());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Createable#create(java.lang.Object
     * )
     */
    @Override
    public ContentModel create(final ContentModel contentModel) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(contentModel);

        final Marshaller<ContentModel> m = MarshallerFactory.getInstance().getMarshaller(ContentModel.class);

        final String xml = getClient().create(m.marshalDocument(contentModel));

        return m.unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Retrievable#retrieve(java.lang
     * .String)
     */
    @Override
    public ContentModel retrieve(final String id) throws EscidocException, InternalClientException, TransportException {

        checkNotNull(id);

        final String xml = getClient().retrieve(id);

        return MarshallerFactory.getInstance().getMarshaller(ContentModel.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Deletable#delete(java.lang.String)
     */
    @Override
    public void delete(final String id) throws EscidocException, InternalClientException, TransportException {

        checkNotNull(id);

        getClient().delete(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Updatable#update(java.lang.Object)
     */
    @Override
    public ContentModel update(final ContentModel contentModel) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(contentModel);

        return update(contentModel.getObjid(), contentModel);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Updatable#update(java.lang.String,
     * java.lang.Object)
     */
    @Override
    public ContentModel update(final String id, final ContentModel contentModel) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(id);
        checkNotNull(contentModel);

        final Marshaller<ContentModel> m = MarshallerFactory.getInstance().getMarshaller(ContentModel.class);

        final String xml = getClient().update(id, m.marshalDocument(contentModel));

        return m.unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.ContentModelHandlerClientInterface#
     * retrieveContentModels(gov.loc.www.zing.srw.ExplainRequestType)
     */
    @Override
    public ExplainResponse retrieveContentModels(final ExplainRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        final String xml = getClient().retrieveContentModels(request);

        return MarshallerFactory.getInstance().getMarshaller(ExplainResponse.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.ContentModelHandlerClientInterface#
     * retrieveContentModels(gov.loc.www.zing.srw.SearchRetrieveRequestType)
     */
    @Override
    public SearchRetrieveResponse retrieveContentModels(final SearchRetrieveRequestType request)
        throws EscidocException, InternalClientException, TransportException {

        final String xml = getClient().retrieveContentModels(request);

        return MarshallerFactory.getInstance().getMarshaller(SearchRetrieveResponse.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.ContentModelHandlerClientInterface#
     * retrieveContentModelsAsList
     * (gov.loc.www.zing.srw.SearchRetrieveRequestType)
     */
    @Override
    public List<ContentModel> retrieveContentModelsAsList(final SearchRetrieveRequestType request)
        throws EscidocException, InternalClientException, TransportException {

        return getSearchRetrieveResponseAsList(ContentModel.class, retrieveContentModels(request));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.PropertiesService#retrieveProperties
     * (java.lang.String)
     */
    @Override
    public ContentModelProperties retrieveProperties(final String id) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);

        final String xml = getClient().retrieveProperties(id);

        return MarshallerFactory.getInstance().getMarshaller(ContentModelProperties.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.base.VersionableResourceService#
     * retrieveVersionHistory(java.lang.String)
     */
    @Override
    public VersionHistory retrieveVersionHistory(final String id) throws EscidocClientException,
        InternalClientException, TransportException {

        checkNotNull(id);

        final String xml = getClient().retrieveVersionHistory(id);

        return MarshallerFactory.getInstance().getMarshaller(VersionHistory.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.base.VersionableResourceService#
     * retrieveVersionHistory(java.lang.Object)
     */
    @Override
    public VersionHistory retrieveVersionHistory(final ContentModel resource) throws EscidocClientException,
        InternalClientException, TransportException {

        checkNotNull(resource);

        return retrieveVersionHistory(resource.getObjid());
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.base.ContentStreamService#
     * retrieveContentStreams(java.lang.String)
     */
    @Override
    public ContentStreams retrieveContentStreams(final String id) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);

        final String xml = getClient().retrieveContentStreams(id);

        return MarshallerFactory.getInstance().getMarshaller(ContentStreams.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.base.ContentStreamService#
     * retrieveContentStream(java.lang.String, java.lang.String)
     */
    @Override
    public ContentStream retrieveContentStream(final String id, final String name) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(id);
        checkNotNull(name);

        final String xml = getClient().retrieveContentStream(id, name);

        return MarshallerFactory.getInstance().getMarshaller(ContentStream.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.base.ContentStreamService#
     * retrieveContentStreamContent(java.lang.String, java.lang.String)
     */
    @Override
    public HttpInputStream retrieveContentStreamContent(final String id, final String name) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(id);
        checkNotNull(name);

        return getClient().retrieveContentStreamContent(id, name);
    }
}