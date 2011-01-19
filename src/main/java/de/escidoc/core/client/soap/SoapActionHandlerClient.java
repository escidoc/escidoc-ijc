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

import org.apache.log4j.Logger;

import de.escidoc.core.aa.ActionHandler;
import de.escidoc.core.aa.ActionHandlerServiceLocator;
import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.rest.RestAdminHandlerClient;

/**
 * SOAP Handler for Action.
 * 
 * @author SWA
 * 
 */
public class SoapActionHandlerClient extends SoapClientBase {

	private static final Logger LOG = Logger
			.getLogger(SoapActionHandlerClient.class);

    private ActionHandler soapClient = null;

    /**
     * 
     * @throws InternalClientException
     */
    public SoapActionHandlerClient() throws InternalClientException {
        super();
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public SoapActionHandlerClient(final URL serviceAddress)
        throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     * @deprecated Use
     *             {@link SoapActionHandlerClient#SoapActionHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public SoapActionHandlerClient(final String serviceAddress)
        throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param contextId
     * @param actions
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String createUnsecuredActions(
        final String contextId, final String actions) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().createUnsecuredActions(contextId, actions);
        }
        catch (Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * 
     * @param contextId
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveUnsecuredActions(final String contextId)
        throws EscidocException, InternalClientException, TransportException {
        String result = null;
        try {
            result = getClient().retrieveUnsecuredActions(contextId);
        }
        catch (Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * 
     * @param contextId
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void deleteUnsecuredActions(final String contextId)
        throws EscidocException, InternalClientException, TransportException {
        try {
            getClient().deleteUnsecuredActions(contextId);
        }
        catch (Exception e) {
            ExceptionMapper.map(e, LOG);
        }
    }

    /**
     * @return Returns the soapClient.
     * @throws InternalClientException
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    @Override
    public ActionHandler getClient() throws InternalClientException {

        try {
            if (soapClient == null) {
                ActionHandlerServiceLocator serviceLocator =
                    new ActionHandlerServiceLocator(getEngineConfig());
                URL url =
                    getHandlerServiceURL(serviceLocator
                        .getActionHandlerServiceAddress());
                soapClient = serviceLocator.getActionHandlerService(url);
                registerPWCallback(soapClient);
            }
        }
        catch (ServiceException e) {
            throw new InternalClientException(e.getMessage(), e);
        }
        return soapClient;
    }
}