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
import de.escidoc.core.client.interfaces.ContextHandlerClientInterface;
import de.escidoc.core.client.rest.RestContextHandlerClient;
import de.escidoc.core.client.soap.SoapContextHandlerClient;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.common.properties.Properties;
import de.escidoc.core.resources.om.MemberList;
import de.escidoc.core.resources.om.context.AdminDescriptor;
import de.escidoc.core.resources.om.context.AdminDescriptors;
import de.escidoc.core.resources.om.context.Context;

/**
 * This is the generic ContextSoapContextHandlerClient which binds the transport
 * specific classes. The transport specification is done via properties
 * configuration of the eSciDoc client.
 * 
 * @author SWA
 * 
 */
public class ContextHandlerClient
    implements ContextHandlerClientInterface<Context> {

    // Set SOAP as default transport protocol (for now :-()
    private TransportProtocol transport = TransportProtocol.SOAP;

    private SoapContextHandlerClient soapContextHandlerClient = null;

    private RestContextHandlerClient restContextHandlerClient = null;

    /**
     * Create ContextsoapContextHandlerClient instance. The service protocol
     * (REST/SOAP/..) selected from the configuration. Default is SOAP.
     * 
     * @throws ClientException
     * 
     */
    public ContextHandlerClient() throws EscidocException,
        InternalClientException, TransportException {

    }

    /**
     * See Interface for functional description.
     * 
     * @param context
     * @return
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.ContextHandlerClientInterface#create(de.escidoc.core.resources.interfaces.container.ContextInterface)
     */
    public Context create(final Context context) throws EscidocException,
        InternalClientException, TransportException {

        String contextXml =
            Factory.getContextMarshaller().marshalDocument(context);
        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapContextHandlerClient().create(contextXml);
        }
        else {
            xml = getRestContextHandlerClient().create(contextXml);
        }
        return Factory.getContextMarshaller().unmarshalDocument(xml);
    }

    /**
     * See Interface for functional description.
     * 
     * @param id
     * @return
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.ContextHandlerClientInterface#retrieve(java.lang.String)
     */
    public Context retrieve(final String id) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapContextHandlerClient().retrieve(id);
        }
        else {
            xml = getRestContextHandlerClient().retrieve(id);
        }
        return Factory.getContextMarshaller().unmarshalDocument(xml);
    }

    public Properties retrieveProperties(final String id)
        throws EscidocException, InternalClientException, TransportException {

        throw new InternalClientException("method not yet supported");
    }

    /**
     * See Interface for functional description.
     * 
     * @param id
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.ContextHandlerClientInterface#delete(java.lang.String)
     */
    public void delete(final String id) throws EscidocException,
        InternalClientException, TransportException {

        if (getTransport() == TransportProtocol.SOAP) {
            getSoapContextHandlerClient().delete(id);
        }
        else {
            getRestContextHandlerClient().delete(id);
        }
    }

    /**
     * See Interface for functional description.
     * 
     * @param context
     * @return
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.ContextHandlerClientInterface#update(de.escidoc.core.resources.interfaces.container.ContextInterface)
     */
    public Context update(final Context context) throws EscidocException,
        InternalClientException, TransportException {

        String contextXml =
            Factory.getContextMarshaller().marshalDocument(context);
        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapContextHandlerClient().update(context.getObjid(),
                    contextXml);
        }
        else {
            xml =
                getRestContextHandlerClient().update(context.getObjid(),
                    contextXml);
        }
        return Factory.getContextMarshaller().unmarshalDocument(xml);
    }

    /**
     * See Interface for functional description.
     * 
     * @param id
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.interfaces.ContextHandlerClientInterface#open(java.lang.String)
     */
    public Result open(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            Factory.getTaskParamMarshaller().marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapContextHandlerClient().open(id, taskParamString);
        }
        else {
            xml = getRestContextHandlerClient().open(id, taskParamString);
        }
        return Factory.getResultMarshaller().unmarshalDocument(xml);
    }

    /**
     * See Interface for functional description.
     * 
     * @param id
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.interfaces.ContextHandlerClientInterface#unlock(java.lang.String,
     *      de.escidoc.core.resources.common.TaskParam)
     */
    public Result close(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        String taskParamString =
            Factory.getTaskParamMarshaller().marshalDocument(taskParam);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapContextHandlerClient().close(id, taskParamString);
        }
        else {
            xml = getRestContextHandlerClient().close(id, taskParamString);
        }
        return Factory.getResultMarshaller().unmarshalDocument(xml);
    }

    /**
     * 
     */
    public Result assignObjectPid(final String id, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        // String xml = getSoapContextHandlerClient().assignObjectPid(id,
        // Factory.getTaskParamMarshaller().marshalDocument(taskParam));
        // return Factory.getResultMarshaller().unmarshalDocument(xml);
        throw new InternalClientException(
            "Context does currently no PID assignment.");
    }

    public AdminDescriptors retrieveAdminDescriptors(final String id)
        throws EscidocException, InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapContextHandlerClient().retrieveAdminDescriptors(id);
        }
        else {
            xml = getRestContextHandlerClient().retrieveAdminDescriptors(id);
        }
        return Factory
            .getAdminDescriptorListMarshaller().unmarshalDocument(xml);
    }

    public AdminDescriptor retrieveAdminDescriptor(
        final String id, final String name) throws EscidocException,
        InternalClientException, TransportException {

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapContextHandlerClient().retrieveAdminDescriptor(id, name);
        }
        else {
            xml =
                getRestContextHandlerClient().retrieveAdminDescriptor(id, name);
        }
        return Factory.getAdminDescriptorMarshaller().unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * @see de.escidoc.core.client.interfaces.ContextHandlerClientInterface#retrieveMembers(java.lang.String, de.escidoc.core.resources.common.TaskParam)
     */
    public MemberList retrieveMembers(final String id, final TaskParam taskParam) throws EscidocException,
    InternalClientException, TransportException{
    String xml = null;
    String taskParamString =
        Factory.getTaskParamMarshaller().marshalDocument(taskParam);
    if (getTransport() == TransportProtocol.SOAP) {
   
        xml = getSoapContextHandlerClient().retrieveMembers(id, taskParamString);
    }
    else {
        xml = getRestContextHandlerClient().retrieveMembers(id, taskParamString);
    }
    return Factory.getMemberListMarshaller().unmarshalDocument(xml);
    
}
    /**
     * See Interface for functional description.
     * 
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.interfaces.ContextHandlerClientInterface#getLastModificationDate(java.lang.String)
     */
    public DateTime getLastModificationDate(final String id)
        throws EscidocException, InternalClientException, TransportException {

        return getSoapContextHandlerClient().getLastModificationDate(id);
    }

    /**
     * See Interface for functional description.
     * 
     * @param handle
     * @throws InternalClientException
     * @see de.escidoc.core.client.interfaces.BaseClientHandlerInterface#setHandle(java.lang.String)
     */
    public void setHandle(final String handle) throws InternalClientException {

        if (getTransport() == TransportProtocol.SOAP) {
            getSoapContextHandlerClient().setHandle(handle);
        }
        else {
            getRestContextHandlerClient().setHandle(handle);
        }
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
        getSoapContextHandlerClient().setServiceAddress(address);
    }

    /**
     * Get the SOAP handler.
     * 
     * @return SoapContextHandlerClient
     * @throws InternalClientException
     *             Thrown if creating instance of SoapContextHandlerClient
     *             failed.
     */
    public SoapContextHandlerClient getSoapContextHandlerClient()
        throws InternalClientException {
        if (this.soapContextHandlerClient == null) {
            this.soapContextHandlerClient = new SoapContextHandlerClient();
        }
        return this.soapContextHandlerClient;
    }

    /**
     * Get the REST handler.
     * 
     * @return RestContextHandlerClient
     * @throws InternalClientException
     *             Thrown if creating instance of RestContextHandlerClient
     *             failed.
     */
    public RestContextHandlerClient getRestContextHandlerClient()
        throws InternalClientException {
        if (this.restContextHandlerClient == null) {
            this.restContextHandlerClient = new RestContextHandlerClient();
        }
        return this.restContextHandlerClient;
    }

    /**
     * Set the Transport Protocol (REST/SOAP).
     * 
     * @param tp
     *            The transport protocol.
     */
    public void setTransport(final TransportProtocol tp) {
        this.transport = tp;
    }

    /**
     * Set the Transport Protocol (REST/SOAP).
     * 
     * @return The used transport protocol.
     */
    public TransportProtocol getTransport() {
        return this.transport;
    }

}
