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

import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.TocHandlerClientInterface;
import de.escidoc.core.client.soap.SoapTocHandlerClient;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.common.properties.Properties;
import de.escidoc.core.resources.common.versionhistory.VersionHistory;
import de.escidoc.core.resources.om.toc.Toc;

/**
 * This is the generic TocClientHandler which binds the transport specific
 * classes. The transport specification is done via properties configuration of
 * the eSciDoc client.
 *
 * @author SWA
 *
 */
public class TocHandlerClient implements TocHandlerClientInterface<Toc> {

    private SoapTocHandlerClient soapTocHandlerClient = null;

    /**
     * Create TocClientHandler instance. The service protocol (REST/SOAP/..)
     * selected from the configuration. Default is SOAP.
     *
     * @throws InternalClientException
     *
     */
    public TocHandlerClient() throws InternalClientException {

        // read service protocol from config or set as default SOAP
        this.soapTocHandlerClient = new SoapTocHandlerClient();
    }

    /**
     * See Interface for functional description.
     *
     * @param toc
     * @return
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.TocHandlerClientInterface#create(de.escidoc.core.resources.interfaces.toc.TocInterface)
     */
    public Toc create(final Toc toc) throws EscidocException,
        InternalClientException, TransportException {

        String xml =
            getSoapTocHandlerClient().create(
                Factory.getTocMarshaller().marshalDocument((Toc) toc));
        return Factory.getTocMarshaller().unmarshalDocument(xml);
    }

    /**
     * See Interface for functional description.
     *
     * @param id
     * @return
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.TocHandlerClientInterface#retrieve(java.lang.String)
     */
    public Toc retrieve(final String id) throws EscidocException,
        InternalClientException, TransportException {

        return Factory.getTocMarshaller().unmarshalDocument(
            getSoapTocHandlerClient().retrieve(id));
    }

    /**
     * See Interface for functional description.
     *
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.interfaces.TocHandlerClientInterface#retrieveVersionHistory(java.lang.String)
     */
    public VersionHistory retrieveVersionHistory(final String id)
        throws EscidocException, InternalClientException, TransportException {

        return Factory.getVersionHistoryMarshaller().unmarshalDocument(
            getSoapTocHandlerClient().retrieveVersionHistory(id));
    }

    /**
     * See Interface for functional description.
     *
     * @param id
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.TocHandlerClientInterface#delete(java.lang.String)
     */
    public void delete(final String id) throws EscidocException,
        InternalClientException, TransportException {

        getSoapTocHandlerClient().delete(id);
    }

    /**
     * See Interface for functional description.
     *
     * @param toc
     * @return
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.TocHandlerClientInterface#update(de.escidoc.core.resources.interfaces.toc.TocInterface)
     */
    public Toc update(final Toc toc) throws EscidocException,
        InternalClientException, TransportException {

        String xml =
            getSoapTocHandlerClient().update(((Toc) toc).getObjid(),
                Factory.getTocMarshaller().marshalDocument((Toc) toc));
        return Factory.getTocMarshaller().unmarshalDocument(xml);
    }

    /*
     * Status methods
     */

    /**
     * See Interface for functional description.
     *
     * @param id
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.interfaces.TocHandlerClientInterface#submit(java.lang.String,
     *      de.escidoc.core.resources.common.TaskParam)
     */
    public Result submit(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml =
            getSoapTocHandlerClient().submit(id,
                Factory.getTaskParamMarshaller().marshalDocument(taskParam));
        return Factory.getResultMarshaller().unmarshalDocument(xml);
    }

    /**
     * See Interface for functional description.
     *
     * @param id
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.interfaces.TocHandlerClientInterface#release(java.lang.String,
     *      de.escidoc.core.resources.common.TaskParam)
     */
    public Result release(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml =
            getSoapTocHandlerClient().release(id,
                Factory.getTaskParamMarshaller().marshalDocument(taskParam));
        return Factory.getResultMarshaller().unmarshalDocument(xml);
    }

    /**
     * See Interface for functional description.
     *
     * @param id
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.interfaces.TocHandlerClientInterface#revise(java.lang.String,
     *      de.escidoc.core.resources.common.TaskParam)
     */
    public Result revise(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml =
            getSoapTocHandlerClient().revise(id,
                Factory.getTaskParamMarshaller().marshalDocument(taskParam));
        return Factory.getResultMarshaller().unmarshalDocument(xml);
    }

    /**
     *
     * @param id
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public Result withdraw(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml =
            getSoapTocHandlerClient().withdraw(id,
                Factory.getTaskParamMarshaller().marshalDocument(taskParam));
        return Factory.getResultMarshaller().unmarshalDocument(xml);
    }

    public Properties retrieveProperties(final String id)
        throws EscidocException, InternalClientException, TransportException {

        throw new InternalClientException("method not yet supported");
    }

    /*
     * Assign PID methods
     */

    /**
     * See Interface for functional description.
     *
     * @param id
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.interfaces.TocHandlerClientInterface#assignVersionPid(java.lang.String,
     *      de.escidoc.core.resources.common.TaskParam)
     */
    public Result assignVersionPid(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        throw new InternalClientException("method not yet supported");
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
     * @see de.escidoc.core.client.interfaces.TocHandlerClientInterface#assignObjectPid(java.lang.String,
     *      de.escidoc.core.resources.common.TaskParam)
     */
    public Result assignObjectPid(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        throw new InternalClientException("method not yet supported");

        // String xml = getSoapTocHandlerClient().assignObjectPid(id,
        // Factory.getTaskParamMarshaller().marshalDocument(taskParam));
        // return Factory.getResultMarshaller().unmarshalDocument(xml);
    }

    /**
     * See Interface for functional description.
     *
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.interfaces.TocHandlerClientInterface#getLastModificationDate(java.lang.String)
     */
    public DateTime getLastModificationDate(final String id)
        throws EscidocException, InternalClientException, TransportException {

        return getSoapTocHandlerClient().getLastModificationDate(id);
    }

    /**
     * See Interface for functional description.
     *
     * @param handle
     * @see de.escidoc.core.client.interfaces.BaseClientHandlerInterface#setHandle(java.lang.String)
     */
    public void setHandle(final String handle) {

        getSoapTocHandlerClient().setHandle(handle);
    }

    /**
     * @return the soapTocClientHandler
     * @throws InternalClientException
     */
    public SoapTocHandlerClient getSoapTocHandlerClient() {

        return soapTocHandlerClient;
    }

    /**
     * Set the service endpoint address.
     *
     * @param address
     *            URL of the service endpoint.
     * @throws InternalClientException
     *             Thrown if URL is not valid.
     */
    public void setServiceAddress(final String address)
        throws InternalClientException {
        getSoapTocHandlerClient().setServiceAddress(address);
    }

    /* (non-Javadoc)
     * @see de.escidoc.core.client.interfaces.CrudHandlerInterface#setTransport(de.escidoc.core.client.TransportProtocol)
     */
    public void setTransport(TransportProtocol tp) {
        // Purposely not implemented
    }


}
