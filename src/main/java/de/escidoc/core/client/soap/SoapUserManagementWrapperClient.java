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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger LOG = LoggerFactory.getLogger(SoapUserManagementWrapperClient.class);

    private UserManagementWrapper soapClient = null;

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public SoapUserManagementWrapperClient(final URL serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     * @deprecated Use
     *             {@link SoapUserManagementWrapperClient#SoapUserManagementWrapperClient(URL)}
     *             instead.
     */
    @Deprecated
    public SoapUserManagementWrapperClient(final String serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void logout() throws EscidocException, InternalClientException, TransportException {
        try {
            getClient().logout();
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    @Override
    public UserManagementWrapper getClient() throws InternalClientException {

        try {
            if (soapClient == null) {
                final UserManagementWrapperServiceLocator serviceLocator =
                    new UserManagementWrapperServiceLocator(getEngineConfig());
                final URL url = getHandlerServiceURL(serviceLocator.getUserManagementWrapperServiceAddress());
                soapClient = serviceLocator.getUserManagementWrapperService(url);
                registerPWCallback(soapClient);
            }
        }
        catch (final ServiceException e) {
            throw new InternalClientException(e.getMessage(), e);
        }
        return soapClient;
    }
}