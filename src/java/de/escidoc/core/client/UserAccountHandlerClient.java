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

import java.io.IOException;

import org.joda.time.DateTime;

import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface;
import de.escidoc.core.client.rest.RestUserAccountHandlerClient;
import de.escidoc.core.client.soap.SoapUserAccountHandlerClient;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.aa.useraccount.Grants;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.aa.useraccount.UserAccounts;
import de.escidoc.core.resources.common.TaskParam;

/**
 * This is the generic ContainerSoapContainerHandlerClient which binds the
 * transport specific classes. The transport specification is done via
 * properties configuration of the eSciDoc client.
 * 
 * @author SWA
 * 
 */
public class UserAccountHandlerClient
    implements UserAccountHandlerClientInterface<UserAccount> {

    // Set SOAP as default transport protocol (for now :-()
    private TransportProtocol transport = TransportProtocol.SOAP;

    private SoapUserAccountHandlerClient soapUserAccountHandlerClient = null;

    private RestUserAccountHandlerClient restUserAccountHandlerClient = null;

    private Authentication auth = null;

    /**
     * Create ContainersoapContainerHandlerClient instance. The service protocol
     * (REST/SOAP/..) selected from the configuration. Default is SOAP.
     * 
     * @throws ClientException
     * 
     */
    public UserAccountHandlerClient() throws EscidocException,
        InternalClientException, TransportException {

        // read service protocol from config or set as default SOAP
        this.soapUserAccountHandlerClient = new SoapUserAccountHandlerClient();
    }

    /**
     * See Interface for functional description.
     * 
     * @param container
     * @return
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#create(de.escidoc.core.resources.interfaces.container.ContainerInterface)
     */
    public UserAccount create(final UserAccount userAccount)
        throws EscidocException, InternalClientException, TransportException {

        String xml =
            getSoapUserAccountHandlerClient().create(
                Factory.getUserAccountMarshaller().marshalDocument(
                    (UserAccount) userAccount));
        return Factory.getUserAccountMarshaller().unmarshalDocument(xml);
    }

    /**
     * See Interface for functional description.
     * 
     * @param id
     * @return
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#retrieve(java.lang.String)
     */
    public UserAccount retrieve(final String id) throws EscidocException,
        InternalClientException, TransportException {

        return Factory.getUserAccountMarshaller().unmarshalDocument(
            getSoapUserAccountHandlerClient().retrieve(id));
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

        getSoapUserAccountHandlerClient().delete(id);
    }

    /**
     * See Interface for functional description.
     * 
     * @param container
     * @return
     * @throws EscidocClientException
     * @see de.escidoc.core.client.interfaces.ContainerHandlerClientInterface#update(de.escidoc.core.resources.interfaces.container.ContainerInterface)
     */
    public UserAccount update(final UserAccount userAccount)
        throws EscidocException, InternalClientException, TransportException {

        String xml =
            getSoapUserAccountHandlerClient().update(
                ((UserAccount) userAccount).getObjid(),
                Factory.getUserAccountMarshaller().marshalDocument(
                    (UserAccount) userAccount));
        return Factory.getUserAccountMarshaller().unmarshalDocument(xml);
    }

    public void updatePassword(final String userId, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException {
        getSoapUserAccountHandlerClient().updatePassword(userId,
            Factory.getTaskParamMarshaller().marshalDocument(taskParam));
    }

    public void activate(final String userId, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException {
        getSoapUserAccountHandlerClient().activate(userId,
            Factory.getTaskParamMarshaller().marshalDocument(taskParam));
    }

    public void deactivate(final String userId, final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException {
        getSoapUserAccountHandlerClient().deactivate(userId,
            Factory.getTaskParamMarshaller().marshalDocument(taskParam));
    }

    //
    // Subresource - current grants
    //

    public Grants retrieveCurrentGrants(final String userId)
        throws EscidocClientException, InternalClientException,
        TransportException {
        return Factory.getGrantsMarshaller().unmarshalDocument(
            getSoapUserAccountHandlerClient().retrieveCurrentGrants(userId));
    }

    //
    // Subresource - grant
    //

    public UserAccounts retrieveUserAccounts(final TaskParam taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException {
        String xml =
            getSoapUserAccountHandlerClient().retrieveUserAccounts(
                Factory.getTaskParamMarshaller().marshalDocument(taskParam));
        return Factory.getUserAccountListMarshaller().unmarshalDocument(xml);

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

        return getSoapUserAccountHandlerClient().getLastModificationDate(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.CrudHandlerInterface#login(java.lang
     * .String, java.lang.String, java.lang.String)
     */
    public String login(
        final String serviceAddress, final String username,
        final String password) throws EscidocException,
        InternalClientException, TransportException {

        setServiceAddress(serviceAddress);

        if (this.auth == null) {
            try {
                auth = new Authentication(serviceAddress, username, password);
            }
            catch (IOException e) {
                throw new InternalClientException("Login failed.", e);
            }
        }

        String handle = this.auth.getAuthHandle();
        setHandle(handle);

        return handle;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.CrudHandlerInterface#logout()
     */
    public void logout() throws EscidocException, InternalClientException,
        TransportException {

        setHandle("");
    }

    /**
     * Get Login-Handle.
     * 
     * @return Login-Handle
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     */
    public String getHandle() throws InternalClientException {

        if (getTransport() == TransportProtocol.SOAP) {
            return getSoapUserAccountHandlerClient().getHandle();
        }
        else {
            return getRestUserAccountHandlerClient().getHandle();
        }
    }

    /**
     * See Interface for functional description.
     * 
     * @param handle
     * @see de.escidoc.core.client.interfaces.BaseClientHandlerInterface#setHandle(java.lang.String)
     */
    public void setHandle(final String handle) {

        getSoapUserAccountHandlerClient().setHandle(handle);
    }

    /**
     * @return the soapContainerHandlerClient
     */
    public SoapUserAccountHandlerClient getSoapUserAccountHandlerClient() {
        return soapUserAccountHandlerClient;
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

        if (getTransport() == TransportProtocol.SOAP) {
            getSoapUserAccountHandlerClient().setServiceAddress(address);
        }
        else {
            getRestUserAccountHandlerClient().setServiceAddress(address);
        }
    }

    /**
     * Get the SOAP handler.
     * 
     * @return SoapUserAccountHandlerClient
     * @throws InternalClientException
     *             Thrown if creating instance of SoapItemHandlerClient failed.
     */
    public SoapUserAccountHandlerClient getSoapItemHandlerClient()
        throws InternalClientException {
        if (this.soapUserAccountHandlerClient == null) {
            this.soapUserAccountHandlerClient =
                new SoapUserAccountHandlerClient();
        }
        return this.soapUserAccountHandlerClient;
    }

    /**
     * Get the REST handler.
     * 
     * @return RestUserAccountHandlerClient
     * @throws InternalClientException
     *             Thrown if creating instance of RestAccountHandlerClient
     *             failed.
     */
    public RestUserAccountHandlerClient getRestUserAccountHandlerClient()
        throws InternalClientException {
        if (this.restUserAccountHandlerClient == null) {
            this.restUserAccountHandlerClient =
                new RestUserAccountHandlerClient();
        }
        return this.restUserAccountHandlerClient;
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
