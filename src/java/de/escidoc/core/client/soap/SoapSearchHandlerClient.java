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

import org.joda.time.DateTime;

import de.escidoc.core.client.ClientBase;
import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;

/**
 * SOAP Handler for Search requests.
 * 
 * @author SWA
 * 
 */
public class SoapSearchHandlerClient extends ClientBase {

    private SRWPort searchSoapClient = null;

    private ExplainPort explainSoapClient = null;

    public SoapSearchHandlerClient() throws InternalClientException {

        super();
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
    public ExplainResponseType explain(
        final ExplainRequestType request, final String database)
        throws EscidocClientException, InternalClientException,
        TransportException {

        ExplainResponseType result = null;
        try {
        	result = getExplainClient(database).explainOperation(request);
            
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
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
    public SearchRetrieveResponseType search(
        final SearchRetrieveRequestType request, final String database)
        throws EscidocClientException, InternalClientException,
        TransportException {

        SearchRetrieveResponseType result = null;
        try {
            result = getSearchClient(database).searchRetrieveOperation(request);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
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
    public ScanResponseType scan(
        final ScanRequestType request, final String database)
        throws EscidocClientException, InternalClientException,
        TransportException {

        ScanResponseType result = null;
        try {
            result = getSearchClient(database).scanOperation(request);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * 
     * @param database
     *            database used for search if null, database "escidoc_all" will
     *            be used for search
     * @return
     * @throws ServiceException
     */
    public ExplainPort getExplainClient(final String database)
        throws ServiceException, InternalClientException {

        this.explainSoapClient = null;

        // Vector mappings = new Vector();
        // addBeanMapping(ExplainResponseType.class, mappings);
        // addBeanMapping(ExplainRequestType.class, mappings);

        SRWSampleServiceLocator service = new SRWSampleServiceLocator();
        if (database != null) {
            URL url;
            // String
            // adress="http://localhost:8080/srw/search/escidoc_all?wsdl";
            String adress = service.getExplainSOAPAddress();

            try {
                url = new URL(adress);
            }
            catch (MalformedURLException e) {
                throw new InternalClientException(e);
            }
            String path = url.getFile();

            int index = path.lastIndexOf("/");
            path = path.substring(0, index + 1);
            try {
                url = new URL(getServiceAddress() + path + database);
            }
            catch (MalformedURLException e) {
                throw new ServiceException(e);
            }
            this.explainSoapClient = service.getExplainSOAP(url);
        }
        else {
            this.explainSoapClient = service.getExplainSOAP();
        }

        return this.explainSoapClient;
    }

    /**
     * 
     * @param database
     *            database used for search if null, database "escidoc_all" will
     *            be used for search
     * @return
     * @throws ServiceException
     */
    public SRWPort getSearchClient(final String database)
        throws ServiceException, InternalClientException {

        this.searchSoapClient = null;

        SRWSampleServiceLocator service = new SRWSampleServiceLocator();
        if (database != null) {
            URL url;
            // String
            // adress="http://localhost:8080/srw/search/escidoc_all?wsdl";
            String adress = service.getSRWAddress();

            try {
                url = new URL(adress);
            }
            catch (MalformedURLException e) {
                throw new InternalClientException(e);
            }
            String path = url.getFile();

            int index = path.lastIndexOf("/");
            path = path.substring(0, index + 1);
            try {
                url = new URL(getServiceAddress() + path + database);
            }
            catch (MalformedURLException e) {
                throw new ServiceException(e);
            }
            this.searchSoapClient = service.getSRW(url);
        }
        else {
            this.searchSoapClient = service.getSRW();
        }

        return searchSoapClient;
    }

    public Remote getClient() throws InternalClientException {

        throw new InternalClientException("The method is not supported");
    }

    public DateTime getLastModificationDate(final String id)
        throws EscidocException, InternalClientException, TransportException {

        throw new InternalClientException("The method is not supported");
    }

}
