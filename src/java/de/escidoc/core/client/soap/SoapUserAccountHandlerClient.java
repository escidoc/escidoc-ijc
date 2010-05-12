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
package de.escidoc.core.client.soap;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.rpc.ServiceException;

import org.joda.time.DateTime;

import de.escidoc.core.client.ClientBase;
import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.um.UserAccountHandler;
import de.escidoc.core.um.UserAccountHandlerServiceLocator;

/**
 * SOAP Handler for User Account.
 * 
 * @author SWA
 * 
 */
public class SoapUserAccountHandlerClient extends ClientBase {

    private UserAccountHandler soapClient = null;

    public SoapUserAccountHandlerClient() throws InternalClientException {

        super();
    }

    /**
     * 
     * @param userAccount
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.UserAccountHandlerInterface#create(java.lang.String)
     */
    public String create(final String userAccount) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().create(userAccount);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.UserAccountHandlerInterface#delete(java.lang.String)
     */
    public void delete(final String id) throws EscidocException,
        InternalClientException, TransportException {

        try {
            getClient().delete(id);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
    }

    /**
     * 
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.UserAccountHandlerInterface#retrieve(java.lang.String)
     */
    public String retrieve(final String id) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieve(id);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @param userAccount
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.UserAccountHandlerInterface#update(java.lang.String,
     *      java.lang.String)
     */
    public String update(final String id, final String userAccount)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().update(id, userAccount);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    public void updatePassword(final String userId, final String taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException {
        try {
            getClient().updatePassword(userId, taskParam);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
    }

    public void activate(final String userId, final String taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException {
        try {
            getClient().activate(userId, taskParam);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
    }

    public void deactivate(final String userId, final String taskParam)
        throws EscidocClientException, InternalClientException,
        TransportException {
        try {
            getClient().deactivate(userId, taskParam);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
    }

    public String retrieveCurrentUser() throws EscidocClientException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveCurrentUser();
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    //
    // Subresource - current grants
    //

    public String retrieveCurrentGrants(final String userId)
        throws EscidocClientException, InternalClientException,
        TransportException {
        String result = null;
        try {
            result = getClient().retrieveCurrentGrants(userId);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    //
    // Subresource - grant
    //

    public String retrieveUserAccounts(final String filter)
        throws EscidocClientException, InternalClientException,
        TransportException {
        String result = null;
        try {
            result = getClient().retrieveUserAccounts(filter);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * Get the last-modification timestamp of the User Account.
     * 
     * @param id
     *            The id of the User Account.
     * @return The timestamp of the last modification of the User Account.
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.ClientBase#getLastModificationDate(java.lang.String)
     */
    @Override
    public DateTime getLastModificationDate(final String id)
        throws EscidocException, InternalClientException, TransportException {

        DateTime result = null;
        try {
            result =
                (Factory.getUserAccountMarshaller()
                    .unmarshalDocument(getClient().retrieve(id)))
                    .getLastModificationDate();
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * @return Returns the soapClient.
     * @throws InternalClientException
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    @Override
    public UserAccountHandler getClient() throws InternalClientException {

        try {
            if (soapClient == null) {
                UserAccountHandlerServiceLocator serviceLocator =
                    new UserAccountHandlerServiceLocator(getEngineConfig());
                String adress =
                    serviceLocator.getUserAccountHandlerServiceAddress();
                URL url = null;
                try {
                    url = new URL(adress);
                }
                catch (MalformedURLException e) {
                    throw new InternalClientException(e);
                }
                String path = url.getFile();
                adress = getServiceAddress() + path;

                try {
                    url = new URL(adress);
                }
                catch (MalformedURLException e) {
                    throw new ServiceException(e);
                }
                soapClient = serviceLocator.getUserAccountHandlerService(url);
            }
        }
        catch (ServiceException e) {
            throw new InternalClientException(e.getMessage(), e);
        }
        return soapClient;
    }

}
