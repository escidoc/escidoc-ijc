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

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.om.IngestHandler;
import de.escidoc.core.om.IngestHandlerServiceLocator;

/**
 * SOAP Handler for Item.
 * 
 * @author SWA
 * 
 */
public class SoapIngestHandlerClient extends SoapClientBase {

    private IngestHandler soapClient = null;

    /**
     * 
     * @throws InternalClientException
     */
    public SoapIngestHandlerClient() throws InternalClientException {
        super();
    }

    /**
     * 
     * @throws InternalClientException
     */
    public SoapIngestHandlerClient(final String serviceAddress)
        throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param resourceXml
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String ingest(String resourceXml) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().ingest(resourceXml);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;

    }

    /**
     * Get the last-modification timestamp of the item.
     * 
     * @param id
     *            The id of the item.
     * @return The timestamp of the last modification of the item.
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.ClientBase#getLastModificationDate(java.lang.String)
     */
    @Override
    @Deprecated
    public DateTime getLastModificationDate(final String id)
        throws EscidocException, InternalClientException, TransportException {

        DateTime result = null;
        return result;
    }

    /**
     * @return Returns the soapClient.
     * @throws InternalClientException
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    @Override
    public IngestHandler getClient() throws InternalClientException {

        try {
            if (soapClient == null) {
                IngestHandlerServiceLocator serviceLocator =
                    new IngestHandlerServiceLocator(getEngineConfig());
                URL url =
                    getHandlerServiceURL(serviceLocator
                        .getIngestHandlerServiceAddress());
                soapClient = serviceLocator.getIngestHandlerService(url);
                registerPWCallback(soapClient);
            }
        }
        catch (ServiceException e) {
            throw new InternalClientException(e.getMessage(), e);
        }
        return soapClient;
    }
}
