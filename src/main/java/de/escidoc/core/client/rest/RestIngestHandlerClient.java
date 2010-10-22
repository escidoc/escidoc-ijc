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
package de.escidoc.core.client.rest;

import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.rest.serviceLocator.IngestRestServiceLocator;
import de.escidoc.core.om.IngestHandler;

/**
 * REST Handler for Ingest.
 * 
 * @author SWA
 * 
 */
public class RestIngestHandlerClient extends RestClientBase {

    private final Logger logger = Logger
        .getLogger(RestIngestHandlerClient.class.getName());

    private IngestHandler restClient = null;

    /**
     * 
     * @throws InternalClientException
     */
    public RestIngestHandlerClient() throws InternalClientException {
        super();
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public RestIngestHandlerClient(final String serviceAddress)
        throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * Ingest and Resource.
     * 
     * @param resourceXml
     *            XML representation of eSciDoc resources (Item, Container,
     *            Context, ..)
     * @return objid of the ingested resource
     * @throws EscidocException
     *             Thrown in case of eSciDoc framework failures.
     * @throws InternalClientException
     *             Thrown in case of client internal failures.
     * @throws TransportException
     *             Thrown in case of failures on transport level.
     */
    public String ingest(final String resourceXml) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().ingest(resourceXml);
        }
        catch (Exception e) {
            logger.debug(e);
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * Get the last-modification timestamp of the ingest.
     * 
     * @param id
     *            The id of the ingest.
     * @return The timestamp of the last modification of the ingest.
     * @throws EscidocException
     *             Thrown in case of eSciDoc framework failures.
     * @throws InternalClientException
     *             Thrown in case of client internal failures.
     * @throws TransportException
     *             Thrown in case of failures on transport level.
     */
    @Override
    public DateTime getLastModificationDate(final String id)
        throws EscidocException, InternalClientException, TransportException {

        DateTime result = null;
        return result;
    }

    /**
     * Get REST client for ingest.
     * 
     * @return Returns the restClient.
     * @throws InternalClientException
     *             Thrown if getting instance of ingest client failed.
     */
    @Override
    public IngestHandler getClient() throws InternalClientException {
        if (restClient == null) {
            IngestRestServiceLocator serviceLocator =
                new IngestRestServiceLocator();
            serviceLocator.registerRestCallbackHandler(this);

            try {
                serviceLocator.setServiceAddress(getServiceAddress());
            }
            catch (MalformedURLException e) {
                throw new InternalClientException(e);
            }
            restClient = serviceLocator;
        }
        return this.restClient;
    }
}
