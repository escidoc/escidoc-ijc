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
import de.escidoc.core.um.UserManagementWrapper;
import de.escidoc.core.um.UserManagementWrapperServiceLocator;

public class SoapUserManagementWrapperClient extends ClientBase {

    private UserManagementWrapper soapClient = null;

    public SoapUserManagementWrapperClient() throws InternalClientException {

        super();
    }

    public void logout() throws EscidocClientException,
        InternalClientException, TransportException {
        try {
            getClient().logout();
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
    }

    /**
     * @return Returns the soapClient.
     * @throws InternalClientException
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    @Override
    public UserManagementWrapper getClient() throws InternalClientException {

        try {
            if (soapClient == null) {
                UserManagementWrapperServiceLocator serviceLocator =
                    new UserManagementWrapperServiceLocator(getEngineConfig());
                String adress =
                    serviceLocator.getUserManagementWrapperServiceAddress();
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
                soapClient =
                    serviceLocator.getUserManagementWrapperService(url);
            }
        }
        catch (ServiceException e) {
            throw new InternalClientException(e.getMessage(), e);
        }
        return soapClient;
    }

    /**
     * Get the last-modification timestamp of the context.
     * 
     * @param id
     *            The id of the context.
     * @return The timestamp of the last modification of the context.
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
        // try {
        // result =
        // (Factory.getContextMarshaller()
        // .unmarshalDocument(getSoapClient().retrieve(id)))
        // .getLastModificationDate();
        // }
        // catch (Exception e) {
        // ExceptionMapper.map(e);
        // }
        return result;
    }

}
