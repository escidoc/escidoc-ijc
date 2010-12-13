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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.StagingHandler;
import de.escidoc.core.client.rest.serviceLocator.StagingRestServiceLocator;

/**
 * REST Handler for Staging Service.
 * 
 * @author SWA
 * 
 */
public class RestStagingHandlerClient extends RestClientBase {

    private final static Logger LOG = Logger
        .getLogger(RestStagingHandlerClient.class);

    private StagingHandler restClient = null;

    /**
     * 
     * @throws InternalClientException
     */
    public RestStagingHandlerClient() throws InternalClientException {
        super();
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public RestStagingHandlerClient(final String serviceAddress)
        throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * Create a file on Staging Service.
     * 
     */
    public URL upload(final File f) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().upload(f);
        }
        catch (Exception e) {
            LOG.debug(e.getMessage(), e);
            ExceptionMapper.map(e);
        }

        if (result == null)
            return null;

        // extract URL from response
        try {
            return extractStagingUrl(result);
        }
        catch (Exception e) {
            throw new InternalClientException(e);
        }
    }

    /**
     * Create a file on Staging Service.
     * 
     */
    public URL upload(final InputStream ins) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().upload(ins);
        }
        catch (Exception e) {
            LOG.debug(e.getMessage(), e);
            ExceptionMapper.map(e);
        }

        if (result == null)
            return null;

        // extract URL from response
        try {
            return extractStagingUrl(result);
        }
        catch (Exception e) {
            throw new InternalClientException(e);
        }
    }

    @Override
    @Deprecated
    public DateTime getLastModificationDate(final String id)
        throws EscidocException, InternalClientException, TransportException {

        throw new InternalClientException("Not supported");
    }

    /**
     * @return Returns the restClient.
     * @throws InternalClientException
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    @Override
    public StagingHandler getClient() throws InternalClientException {

        if (restClient == null) {

            StagingRestServiceLocator serviceLocator =
                new StagingRestServiceLocator();
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

    /**
     * 
     * @param xml
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static URL extractStagingUrl(final String xml)
        throws ParserConfigurationException, SAXException, IOException {

        String stagingHref;
        Element rootElement;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(xml.getBytes()));

        rootElement = doc.getDocumentElement();
        stagingHref = rootElement.getAttribute("xlink:href");
        String baseUrl = rootElement.getAttribute("xml:base");

        return new URL(baseUrl + stagingHref);
    }

}
