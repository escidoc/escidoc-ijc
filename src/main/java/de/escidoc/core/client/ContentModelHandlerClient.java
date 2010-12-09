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

import org.joda.time.DateTime;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.ContentModelHandlerClientInterface;
import de.escidoc.core.client.rest.RestContentModelHandlerClient;
import de.escidoc.core.client.soap.SoapContentModelHandlerClient;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.cmm.ContentModel;

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
    public ContentModelHandlerClient(final String serviceAddress) {
        super(serviceAddress);
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
    public ContentModel create(final ContentModel contentModel)
        throws EscidocException, InternalClientException, TransportException {

        String contentModelXml =
            MarshallerFactory.getInstance(getTransport())
                .getMarshaller(ContentModel.class).marshalDocument(contentModel);
        contentModelXml =
            contentModelXml
                .substring("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                    .length());
        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().create(contentModelXml);
        }
        else {
            xml = getRestHandlerClient().create(contentModelXml);
        }
        return MarshallerFactory.getInstance(getTransport()).getMarshaller(ContentModel.class)
            .unmarshalDocument(xml);
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
    public ContentModel retrieve(final String id) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieve(id);
        }
        else {
            xml = getRestHandlerClient().retrieve(id);
        }
        return MarshallerFactory.getInstance(getTransport()).getMarshaller(ContentModel.class)
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
    public ContentModel update(final ContentModel contentModel)
        throws EscidocException, InternalClientException, TransportException {

        String contentModelXml =
            MarshallerFactory.getInstance(getTransport())
                .getMarshaller(ContentModel.class).marshalDocument(contentModel);
        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapHandlerClient().update(contentModel.getObjid(),
                    contentModelXml);
        }
        else {
            xml =
                getRestHandlerClient().update(contentModel.getObjid(),
                    contentModelXml);
        }
        return MarshallerFactory.getInstance(getTransport()).getMarshaller(ContentModel.class)
            .unmarshalDocument(xml);
    }

    /**
     * See Interface for functional description.
     * 
     * @param id
     *            Id of ContentModel.
     * @return LastModificationDate of this ContentModel.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Deprecated
    public DateTime getLastModificationDate(final String id)
        throws EscidocException, InternalClientException, TransportException {

        if (getTransport() == TransportProtocol.SOAP)
            return getSoapHandlerClient().getLastModificationDate(id);
        else
            return getRestHandlerClient().getLastModificationDate(id);
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

}
