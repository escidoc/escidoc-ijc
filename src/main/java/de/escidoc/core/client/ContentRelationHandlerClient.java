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

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.ContentRelationHandlerClientInterface;
import de.escidoc.core.client.rest.RestContentRelationHandlerClient;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.om.contentRelation.ContentRelation;
import de.escidoc.core.resources.om.contentRelation.ContentRelationProperties;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;

/**
 * @author SWA
 * 
 */
public class ContentRelationHandlerClient extends AbstractHandlerClient<RestContentRelationHandlerClient>
    implements ContentRelationHandlerClientInterface {

    /**
     * 
     * @param serviceAddress
     */
    public ContentRelationHandlerClient(final URL serviceAddress) {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @deprecated Use
     *             {@link ContentRelationHandlerClient#ContentRelationHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public ContentRelationHandlerClient(final String serviceAddress) {
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
    protected RestContentRelationHandlerClient getRestHandlerClientInstance() throws InternalClientException {
        return new RestContentRelationHandlerClient(getServiceAddress());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Createable#create(java.lang.Object
     * )
     */
    @Override
    public ContentRelation create(final ContentRelation contentRelation) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(contentRelation);

        final Marshaller<ContentRelation> m = MarshallerFactory.getInstance().getMarshaller(ContentRelation.class);

        final String xml = getClient().create(m.marshalDocument(contentRelation));
        return m.unmarshalDocument(xml);
    }

    /**
     * @param contentRelation
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @Deprecated
    public ContentRelation retrieve(final ContentRelation contentRelation) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(contentRelation);

        return retrieve(contentRelation.getObjid());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Retrievable#retrieve(java.lang
     * .String)
     */
    @Override
    public ContentRelation retrieve(final String id) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);

        final String xml = getClient().retrieve(id);

        return MarshallerFactory.getInstance().getMarshaller(ContentRelation.class).unmarshalDocument(xml);
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
    public ContentRelation update(final ContentRelation contentRelation) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(contentRelation);

        return update(contentRelation.getObjid(), contentRelation);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Updatable#update(java.lang.String,
     * java.lang.Object)
     */
    @Override
    public ContentRelation update(final String id, final ContentRelation contentRelation) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(id);
        checkNotNull(contentRelation);

        final Marshaller<ContentRelation> m = MarshallerFactory.getInstance().getMarshaller(ContentRelation.class);

        final String xml = getClient().update(id, m.marshalDocument(contentRelation));

        return m.unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.ObjectPidService#assignObjectPid
     * (java.lang.Object, de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public Result assignObjectPid(final ContentRelation cr, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(cr);

        return assignObjectPid(cr.getObjid(), taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.ObjectPidService#assignObjectPid
     * (java.lang.String, de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public Result assignObjectPid(final String id, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(id);
        checkNotNull(taskParam);

        final String xml = getClient().assignObjectPid(id, marshalTaskParam(taskParam));

        return MarshallerFactory.getInstance().getMarshaller(Result.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.LockingService#lock(java.lang.
     * Object, de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public Result lock(final ContentRelation cr, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(cr);

        return lock(cr.getObjid(), taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.LockingService#lock(java.lang.
     * String, de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public Result lock(final String id, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);
        checkNotNull(taskParam);

        final String xml = getClient().lock(id, marshalTaskParam(taskParam));

        return MarshallerFactory.getInstance().getMarshaller(Result.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.LockingService#unlock(java.lang
     * .Object, de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public Result unlock(final ContentRelation cr, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(cr);

        return unlock(cr.getObjid(), taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.LockingService#unlock(java.lang
     * .String, de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public Result unlock(final String id, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);
        checkNotNull(taskParam);

        final String xml = getClient().unlock(id, marshalTaskParam(taskParam));

        return MarshallerFactory.getInstance().getMarshaller(Result.class).unmarshalDocument(xml);
    }

    /*
     * Status methods
     */

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Submittable#submit(java.lang.String
     * , de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public Result submit(final String id, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);
        checkNotNull(taskParam);

        final String xml = getClient().submit(id, marshalTaskParam(taskParam));

        return MarshallerFactory.getInstance().getMarshaller(Result.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Submittable#submit(java.lang.Object
     * , de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public Result submit(final ContentRelation contentRelation, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(contentRelation);

        return submit(contentRelation.getObjid(), taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Releasable#release(java.lang.String
     * , de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public Result release(final String id, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);
        checkNotNull(taskParam);

        final String xml = getClient().release(id, marshalTaskParam(taskParam));

        return MarshallerFactory.getInstance().getMarshaller(Result.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Releasable#release(java.lang.Object
     * , de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public Result release(final ContentRelation contentRelation, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(contentRelation);

        return release(contentRelation.getObjid(), taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Revisable#revise(java.lang.String,
     * de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public Result revise(final String id, final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);
        checkNotNull(taskParam);

        final String xml = getClient().revise(id, marshalTaskParam(taskParam));

        return MarshallerFactory.getInstance().getMarshaller(Result.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Revisable#revise(java.lang.Object,
     * de.escidoc.core.resources.common.TaskParam)
     */
    @Override
    public Result revise(final ContentRelation contentRelation, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(contentRelation);

        return revise(contentRelation.getObjid(), taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.ContentRelationHandlerClientInterface
     * #retrieveContentRelations(gov.loc.www.zing.srw.SearchRetrieveRequestType)
     */
    @Override
    public SearchRetrieveResponse retrieveContentRelations(final SearchRetrieveRequestType filter)
        throws EscidocException, InternalClientException, TransportException {

        final String xml = getClient().retrieveContentRelations(filter);

        return MarshallerFactory.getInstance().getMarshaller(SearchRetrieveResponse.class).unmarshalDocument(xml);
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
    public List<ContentRelation> retrieveContentRelationsAsList(final SearchRetrieveRequestType filter)
        throws EscidocException, InternalClientException, TransportException {

        return getSearchRetrieveResponseAsList(ContentRelation.class, retrieveContentRelations(filter));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.ContentRelationHandlerClientInterface
     * #retrieveContentRelations(gov.loc.www.zing.srw.ExplainRequestType)
     */
    @Override
    public ExplainResponse retrieveContentRelations(final ExplainRequestType filter) throws EscidocException,
        InternalClientException, TransportException {

        final String xml = getClient().retrieveContentRelations(filter);

        return MarshallerFactory.getInstance().getMarshaller(ExplainResponse.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.PropertiesService#retrieveProperties
     * (java.lang.String)
     */
    @Override
    public ContentRelationProperties retrieveProperties(final String id) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(id);

        final String xml = getClient().retrieveProperties(id);

        return MarshallerFactory.getInstance().getMarshaller(ContentRelationProperties.class).unmarshalDocument(xml);
    }
}