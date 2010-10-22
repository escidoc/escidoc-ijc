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

import java.net.URL;

import javax.xml.rpc.ServiceException;

import org.joda.time.DateTime;

import de.escidoc.core.aa.UserManagementWrapper;
import de.escidoc.core.aa.UserManagementWrapperServiceLocator;
import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;

/**
 * SOAP Handler for User Management.
 * 
 * @author SWA
 * 
 */
public class SoapUserManagementWrapperClient extends SoapClientBase {

    private UserManagementWrapper soapClient = null;

    /**
     * 
     * @throws InternalClientException
     */
    public SoapUserManagementWrapperClient() throws InternalClientException {
        super();
    }

    /**
     * 
     * @throws InternalClientException
     */
    public SoapUserManagementWrapperClient(final String serviceAddress)
        throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void logout() throws EscidocException, InternalClientException,
        TransportException {
        try {
            getClient().logout();
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
    }

    /**
     * TODO ?
     * 
     * @param in0
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void initHandleExpiryTimestamp(String in0) throws EscidocException,
        InternalClientException, TransportException {
        try {
            getClient().initHandleExpiryTimestamp(in0);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    public UserManagementWrapper getClient() throws InternalClientException {

        try {
            if (soapClient == null) {
                UserManagementWrapperServiceLocator serviceLocator =
                    new UserManagementWrapperServiceLocator(getEngineConfig());
                URL url =
                    getHandlerServiceURL(serviceLocator
                        .getUserManagementWrapperServiceAddress());
                soapClient =
                    serviceLocator.getUserManagementWrapperService(url);
                registerPWCallback(soapClient);
            }
        }
        catch (ServiceException e) {
            throw new InternalClientException(e.getMessage(), e);
        }
        return soapClient;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.ClientBase#getLastModificationDate(java.lang.String
     * )
     */
    public DateTime getLastModificationDate(final String id)
        throws EscidocException, InternalClientException, TransportException {

        return null;
    }

}
