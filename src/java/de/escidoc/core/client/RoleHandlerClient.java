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
import de.escidoc.core.client.interfaces.RoleHandlerClientInterface;
import de.escidoc.core.client.soap.SoapRoleHandlerClient;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.aa.role.Role;
import de.escidoc.core.resources.aa.role.Roles;
import de.escidoc.core.resources.common.TaskParam;

/**
 * This is the generic ContainerSoapContainerHandlerClient which binds the
 * transport specific classes. The transport specification is done via
 * properties configuration of the eSciDoc client.
 *
 * @author SWA
 *
 */
public class RoleHandlerClient implements RoleHandlerClientInterface<Role> {

    private SoapRoleHandlerClient soapRoleHandlerClient = null;

    /**
     * Create ContainersoapContainerHandlerClient instance. The service protocol
     * (REST/SOAP/..) selected from the configuration. Default is SOAP.
     *
     * @throws ClientException
     *
     */
    public RoleHandlerClient() throws EscidocException,
        InternalClientException, TransportException {

        // read service protocol from config or set as default SOAP
        this.soapRoleHandlerClient = new SoapRoleHandlerClient();
    }

    /**
     * See Interface for functional description.
     *
     * @param container
     * @return
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#create(de.escidoc.core.resources.interfaces.container.ContainerInterface)
     */
    public Role create(final Role role) throws EscidocException,
        InternalClientException, TransportException {

        String xml =
            getSoapRoleHandlerClient().create(
                Factory.getRoleMarshaller().marshalDocument((Role) role));
        return Factory.getRoleMarshaller().unmarshalDocument(xml);
    }

    /**
     * See Interface for functional description.
     *
     * @param id
     * @return
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#retrieve(java.lang.String)
     */
    public Role retrieve(final String id) throws EscidocException,
        InternalClientException, TransportException {

        return Factory.getRoleMarshaller().unmarshalDocument(
            getSoapRoleHandlerClient().retrieve(id));
    }

    /**
     * See Interface for functional description.
     *
     * @param id
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#delete(java.lang.String)
     */
    public void delete(final String id) throws EscidocException,
        InternalClientException, TransportException {

        getSoapRoleHandlerClient().delete(id);
    }

    /**
     * See Interface for functional description.
     *
     * @param container
     * @return
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#update(de.escidoc.core.resources.interfaces.container.ContainerInterface)
     */
    public Role update(final Role role) throws EscidocException,
        InternalClientException, TransportException {

        String xml =
            getSoapRoleHandlerClient().update(((Role) role).getObjid(),
                Factory.getRoleMarshaller().marshalDocument((Role) role));
        return Factory.getRoleMarshaller().unmarshalDocument(xml);
    }

    //
    // Subresource - grant
    //

    public Roles retrieveRoles(final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException {
        String xml =
            getSoapRoleHandlerClient().retrieveRoles(
                Factory.getTaskParamMarshaller().marshalDocument(taskParam));
        return Factory.getRoleListMarshaller().unmarshalDocument(xml);

    }

    /**
     * See Interface for functional description.
     *
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#getLastModificationDate(java.lang.String)
     */
    public DateTime getLastModificationDate(final String id)
        throws EscidocException, InternalClientException, TransportException {

        return getSoapRoleHandlerClient().getLastModificationDate(id);
    }

    /**
     * See Interface for functional description.
     *
     * @param handle
     * @see de.escidoc.core.client.interfaces.BaseClientHandlerInterface#setHandle(java.lang.String)
     */
    public void setHandle(final String handle) {

        getSoapRoleHandlerClient().setHandle(handle);
    }

    /**
     * @return the soapContainerHandlerClient
     */
    public SoapRoleHandlerClient getSoapRoleHandlerClient() {
        return soapRoleHandlerClient;
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
        getSoapRoleHandlerClient().setServiceAddress(address);
    }

    /* (non-Javadoc)
     * @see de.escidoc.core.client.interfaces.CrudHandlerInterface#setTransport(de.escidoc.core.client.TransportProtocol)
     */
    public void setTransport(TransportProtocol tp) {
        // Purposely not implemented
    }

}
