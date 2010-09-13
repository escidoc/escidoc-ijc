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
import gov.loc.www.zing.srw.SearchRetrieveRequestType;
import gov.loc.www.zing.srw.SearchRetrieveResponseType;

import org.apache.axis.types.NonNegativeInteger;
import org.apache.axis.types.PositiveInteger;
import org.apache.axis.types.URI;
import org.apache.axis.types.URI.MalformedURIException;

import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.SearchHandlerClientInterface;
import de.escidoc.core.client.rest.RestSearchHandlerClient;
import de.escidoc.core.client.soap.SoapSearchHandlerClient;
import de.escidoc.core.common.configuration.ConfigurationProvider;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.scan.ScanResponse;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;

/**
 * This is the generic SearchHandlerClient which binds the transport specific
 * classes. The transport specification is done via properties configuration of
 * the eSciDoc client.
 * 
 * @author SWA
 * 
 */
public class SearchHandlerClient
    extends
    AbstractHandlerClient<SoapSearchHandlerClient, RestSearchHandlerClient>
    implements SearchHandlerClientInterface {

    /**
     * Does not support REST protocol!
     */
    @Override
    @Deprecated
    public ExplainResponseType explain(
        final ExplainRequestType request, final String database)
        throws EscidocClientException, InternalClientException,
        TransportException {

        if (getTransport() == TransportProtocol.SOAP) {
            return getSoapHandlerClient().explain(request, database);
        }
        return null;
        // FIXME
        // else {
        // return getRestHandlerClient().explain(request, database);
        // }
    }

    @Override
    public ExplainResponse explain2(
        final ExplainRequestType request, final String database)
        throws EscidocClientException, InternalClientException,
        TransportException {

        if (getTransport() == TransportProtocol.SOAP) {
            return ExplainResponse.createExplainResponse(getSoapHandlerClient()
                .explain(request, database));
        }
        else {
            return Factory
                .getMarshallerFactory(TransportProtocol.REST)
                .getExplainResponseMarshaller()
                .unmarshalDocument(
                    getRestHandlerClient().explain(request, database));
        }
    }

    @Override
    public SearchRetrieveResponse search(
        final String query, final String database)
        throws EscidocClientException, InternalClientException,
        TransportException {
        return search(query, null, null, null, null, null, database);
    }

    @Override
    public SearchRetrieveResponse search(
        final String query, final Integer startRecord,
        final Integer maximumRecords, final String sortKeys,
        final String database) throws EscidocClientException,
        InternalClientException, TransportException {
        return search(query, startRecord, maximumRecords, sortKeys, null, null,
            database);
    }

    @Override
    public SearchRetrieveResponse search(
        final String query, final Integer startRecord,
        final Integer maximumRecords, final String sortKeys,
        final String stylesheetURI, final String version, final String database)
        throws EscidocClientException, InternalClientException,
        TransportException {
        SearchRetrieveRequestType request = new SearchRetrieveRequestType();
        request.setQuery((query == null) ? "" : query);
        request.setVersion((version == null) ? "1.1" : version);
        request.setRecordPacking("string");
        request.setSortKeys(sortKeys);

        if (stylesheetURI != null) {
            try {
                request.setStylesheet(new URI(stylesheetURI));
            }
            catch (MalformedURIException e) {
                throw new InternalClientException(e);
            }
        }
        if (maximumRecords != null && maximumRecords.intValue() >= 0) {
            request.setMaximumRecords(new NonNegativeInteger(Integer.toString(
                maximumRecords.intValue(), 10)));
        }
        if (startRecord != null && startRecord.intValue() >= 1) {
            request.setStartRecord(new PositiveInteger(Integer.toString(
                startRecord.intValue(), 10)));
        }

        return search2(request, database);
    }

    /**
     * Does not support REST protocol!
     */
    @Deprecated
    @Override
    public SearchRetrieveResponseType search(
        final SearchRetrieveRequestType request, final String database)
        throws EscidocClientException, InternalClientException,
        TransportException {

        evalRequest(request);

        return getSoapHandlerClient().search(request, database);
    }

    @Override
    public SearchRetrieveResponse search2(
        final SearchRetrieveRequestType request, final String database)
        throws EscidocClientException, InternalClientException,
        TransportException {

        if (request.getQuery() == null)
            request.setQuery("");

        if (getTransport() == TransportProtocol.SOAP) {
            String db = database;
            // TODO general solution in SOAP client
            if (db == null) {
                db =
                    ConfigurationProvider.getInstance().getProperty(
                        ConfigurationProvider.PROP_SEARCH_DATABASE);
                db = db.substring(db.lastIndexOf('/'));
            }
            return SearchRetrieveResponse
                .createSearchRetrieveResponse(getSoapHandlerClient().search(
                    request, db));
        }
        else {
            String xml = getRestHandlerClient().search(request, database);
            return Factory
                .getMarshallerFactory(getTransport())
                .getSearchRetrieveResponseMarshaller().unmarshalDocument(xml);
        }
    }

    @Override
    public ScanResponse scan(
        final ScanRequestType request, final String database)
        throws EscidocClientException, InternalClientException,
        TransportException {

        if (request.getScanClause() == null)
            request.setScanClause("");

        if (getTransport() == TransportProtocol.SOAP) {
            return ScanResponse.createScanResponse(getSoapHandlerClient().scan(
                request, database));
        }
        else {
            return Factory
                .getMarshallerFactory(TransportProtocol.REST)
                .getScanResponseMarshaller()
                .unmarshalDocument(
                    getRestHandlerClient().scan(request, database));
        }
    }

    @Override
    protected SoapSearchHandlerClient getSoapHandlerClientInstance()
        throws InternalClientException {
        return new SoapSearchHandlerClient();
    }

    @Override
    protected RestSearchHandlerClient getRestHandlerClientInstance()
        throws InternalClientException {
        return new RestSearchHandlerClient();
    }
}
