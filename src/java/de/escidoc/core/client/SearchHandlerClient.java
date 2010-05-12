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
package de.escidoc.core.client;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.ExplainResponseType;
import gov.loc.www.zing.srw.ScanRequestType;
import gov.loc.www.zing.srw.ScanResponseType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;
import gov.loc.www.zing.srw.SearchRetrieveResponseType;
import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.SearchHandlerClientInterface;
import de.escidoc.core.client.soap.SoapSearchHandlerClient;
import de.escidoc.core.resources.sb.wrapper.explain.ExplainResponse;
import de.escidoc.core.resources.sb.wrapper.search.SearchResponse;

/**
 * This is the generic SearchHandlerClient which binds the
 * transport specific classes. The transport specification is done via
 * properties configuration of the eSciDoc client.
 * 
 * @author SWA
 * 
 */
public class SearchHandlerClient implements SearchHandlerClientInterface {

    private SoapSearchHandlerClient soapSearchHandlerClient = null;

    /**
     * Create SearchHandlerClient instance. The service protocol
     * (REST/SOAP/..) selected from the configuration. Default is SOAP.
     * 
     * @throws ClientException
     * 
     */
    public SearchHandlerClient() throws EscidocException,
        InternalClientException, TransportException {

        // read service protocol from config or set as default SOAP
        this.soapSearchHandlerClient = new SoapSearchHandlerClient();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.SearchHandlerClientInterface#explain
     * (gov.loc.www.zing.srw.ExplainRequestType, java.lang.String)
     */
    public ExplainResponseType explain(
        final ExplainRequestType request, final String database)
        throws EscidocClientException, InternalClientException,
        TransportException {
        return getSoapSearchHandlerClient().explain(request, database);
    }

    public ExplainResponse explain2(
        final ExplainRequestType request, final String database)
        throws EscidocClientException, InternalClientException,
        TransportException {
        ExplainResponseType result =
            getSoapSearchHandlerClient().explain(request, database);
        return new ExplainResponse(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.SearchHandlerClientInterface#search
     * (gov.loc.www.zing.srw.SearchRetrieveRequestType, java.lang.String)
     */
    public SearchRetrieveResponseType search(
        final SearchRetrieveRequestType request, final String database)
        throws EscidocClientException, InternalClientException,
        TransportException {
        return getSoapSearchHandlerClient().search(request, database);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.SearchHandlerClientInterface#search2
     * (gov.loc.www.zing.srw.SearchRetrieveRequestType, java.lang.String)
     */
    public SearchResponse search2(
        final SearchRetrieveRequestType request, final String database)
        throws EscidocClientException, InternalClientException,
        TransportException {
        SearchRetrieveResponseType result =
            getSoapSearchHandlerClient().search(request, database);
        return new SearchResponse(result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.SearchHandlerClientInterface#scan(gov
     * .loc.www.zing.srw.ScanRequestType, java.lang.String)
     */
    public ScanResponseType scan(
        final ScanRequestType request, final String database)
        throws EscidocClientException, InternalClientException,
        TransportException {
        return getSoapSearchHandlerClient().scan(request, database);
    }

    /**
     * @return the soapContainerHandlerClient
     */
    public SoapSearchHandlerClient getSoapSearchHandlerClient() {
        return soapSearchHandlerClient;
    }

    /**
     * Set the service endpoint address.
     * 
     * @param address
     *            URL of the service endpoint.
     * @throws InternalClientException
     *             Thrown if URL is not valid.
     */
    public void setServiceAddress(final String address)
        throws InternalClientException {
        getSoapSearchHandlerClient().setServiceAddress(address);
    }

}
