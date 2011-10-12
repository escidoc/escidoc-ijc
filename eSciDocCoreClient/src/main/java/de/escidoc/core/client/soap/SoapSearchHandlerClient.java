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

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.ExplainResponseType;
import gov.loc.www.zing.srw.ScanRequestType;
import gov.loc.www.zing.srw.ScanResponseType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;
import gov.loc.www.zing.srw.SearchRetrieveResponseType;
import gov.loc.www.zing.srw.service.ExplainPort;
import gov.loc.www.zing.srw.service.SRWPort;
import gov.loc.www.zing.srw.service.SRWSampleServiceLocator;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Remote;

import javax.xml.rpc.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.common.configuration.ConfigurationProvider;

/**
 * SOAP Handler for Search requests.
 * 
 * @author SWA, MVO
 * 
 */
public class SoapSearchHandlerClient extends SoapClientBase {

    private static final Logger LOG = LoggerFactory.getLogger(SoapSearchHandlerClient.class);

    private SRWPort searchSoapClient = null;

    private ExplainPort explainSoapClient = null;

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public SoapSearchHandlerClient(final URL serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     * @deprecated Use
     *             {@link SoapSearchHandlerClient#SoapSearchHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public SoapSearchHandlerClient(final String serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param request
     * @param database
     *            database used for search if null, database "escidoc_all" will
     *            be used for search
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    public ExplainResponseType explain(final ExplainRequestType request, final String database)
        throws EscidocClientException, InternalClientException, TransportException {

        evalRequest(request);

        ExplainResponseType result = null;
        try {
            result = getExplainClient(database).explainOperation(request);

        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * 
     * @param request
     * @param database
     *            database used for search if null, database "escidoc_all" will
     *            be used for search
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    public SearchRetrieveResponseType search(final SearchRetrieveRequestType request, final String database)
        throws EscidocClientException, InternalClientException, TransportException {

        evalRequest(request, false);

        SearchRetrieveResponseType result = null;

        try {
            result = getSearchClient(database).searchRetrieveOperation(request);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * 
     * @param request
     * @param database
     *            database used for search if null, database "escidoc_all" will
     *            be used for search
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    public ScanResponseType scan(final ScanRequestType request, final String database) throws EscidocClientException,
        InternalClientException, TransportException {

        evalRequest(request, false);

        ScanResponseType result = null;
        try {
            result = getSearchClient(database).scanOperation(request);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * 
     * @param database
     *            database used for search if null, database "escidoc_all" will
     *            be used for search
     * @return
     * @throws InternalClientException
     */
    public ExplainPort getExplainClient(final String database) throws InternalClientException {
        try {
            if (explainSoapClient == null) {
                final SRWSampleServiceLocator service = new SRWSampleServiceLocator();
                if (database != null) {
                    final URL url = getHandlerServiceURL(service.getExplainSOAPAddress(), database);
                    explainSoapClient = service.getExplainSOAP(url);
                }
                else {
                    explainSoapClient = service.getExplainSOAP();
                }
                registerPWCallback(explainSoapClient);
            }
        }
        catch (final ServiceException e) {
            throw new InternalClientException(e.getMessage(), e);
        }
        return explainSoapClient;
    }

    /**
     * 
     * @param database
     *            database used for search if null, database "escidoc_all" will
     *            be used for search
     * @return
     * @throws InternalClientException
     */
    public SRWPort getSearchClient(final String database) throws InternalClientException {

        if (searchSoapClient == null) {
            final SRWSampleServiceLocator service = new SRWSampleServiceLocator();

            String db = database;
            if (db == null) {
                db = ConfigurationProvider.getInstance().getProperty(ConfigurationProvider.PROP_SEARCH_DATABASE);
            }
            try {
                if (db == null) {
                    searchSoapClient = service.getSRW();
                }
                else {
                    final URL url = getHandlerServiceURL(service.getSRWAddress(), db);
                    searchSoapClient = service.getSRW(url);
                }
            }
            catch (final ServiceException e) {
                throw new InternalClientException(e.getMessage(), e);
            }
            registerPWCallback(searchSoapClient);
        }

        return searchSoapClient;
    }

    /**
     * 
     * @param handlerServiceAddress
     * @param database
     * @return
     * @throws InternalClientException
     */
    protected URL getHandlerServiceURL(final String handlerServiceAddress, final String database)
        throws InternalClientException {
        // address="http://localhost:8080/srw/search/escidoc_all?wsdl";
        URL url;
        try {
            url = new URL(handlerServiceAddress);
        }
        catch (final MalformedURLException e) {
            throw new InternalClientException(e);
        }
        String path = url.getFile();

        final int index = path.lastIndexOf('/');
        path = path.substring(0, index + 1);
        try {
            url = new URL(getServiceAddress() + path + database);
        }
        catch (final MalformedURLException e) {
            throw new InternalClientException(e);
        }
        return url;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    @Override
    public Remote getClient() throws InternalClientException {

        throw new InternalClientException("The method is not supported");
    }
}