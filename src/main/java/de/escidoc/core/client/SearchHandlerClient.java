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

import static de.escidoc.core.common.Precondition.checkNotNull;
import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.ScanRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.net.URL;

import org.apache.axis.types.NonNegativeInteger;
import org.apache.axis.types.PositiveInteger;
import org.apache.axis.types.URI;
import org.apache.axis.types.URI.MalformedURIException;

import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.SearchHandlerClientInterface;
import de.escidoc.core.client.rest.RestSearchHandlerClient;
import de.escidoc.core.common.jibx.MarshallerFactory;
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
public class SearchHandlerClient extends AbstractHandlerClient<RestSearchHandlerClient>
    implements SearchHandlerClientInterface {

    /**
     * 
     * @param serviceAddress
     */
    public SearchHandlerClient(final URL serviceAddress) {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @deprecated Use {@link SearchHandlerClient#SearchHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public SearchHandlerClient(final String serviceAddress) {
        super(serviceAddress);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.SearchHandlerClientInterface#explain
     * (gov.loc.www.zing.srw.ExplainRequestType, java.lang.String)
     */
    @Override
    public ExplainResponse explain(final ExplainRequestType request, final String database)
        throws EscidocClientException, InternalClientException, TransportException {

        checkNotNull(request);

        final String xml = getClient().explain(request, database);

        return MarshallerFactory.getInstance().getMarshaller(ExplainResponse.class).unmarshalDocument(xml);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.SearchHandlerClientInterface#search
     * (java.lang.String, java.lang.String)
     */
    @Override
    public SearchRetrieveResponse search(final String query, final String database) throws EscidocClientException,
        InternalClientException, TransportException {

        return search(query, null, null, null, null, null, database);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.SearchHandlerClientInterface#search
     * (java.lang.String, java.lang.Integer, java.lang.Integer,
     * java.lang.String, java.lang.String)
     */
    @Override
    public SearchRetrieveResponse search(
        final String query, final Integer startRecord, final Integer maximumRecords, final String sortKeys,
        final String database) throws EscidocClientException, InternalClientException, TransportException {

        return search(query, startRecord, maximumRecords, sortKeys, null, null, database);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.SearchHandlerClientInterface#search
     * (java.lang.String, java.lang.Integer, java.lang.Integer,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public SearchRetrieveResponse search(
        final String query, final Integer startRecord, final Integer maximumRecords, final String sortKeys,
        final String stylesheetURI, final String version, final String database) throws EscidocClientException,
        InternalClientException, TransportException {

        final SearchRetrieveRequestType request = new SearchRetrieveRequestType();

        request.setQuery(query == null ? null : query);
        request.setVersion(version == null ? "1.1" : version);
        request.setRecordPacking("string");
        request.setSortKeys(sortKeys);

        if (stylesheetURI != null) {
            try {
                request.setStylesheet(new URI(stylesheetURI));
            }
            catch (final MalformedURIException e) {
                throw new InternalClientException(e);
            }
        }
        if (maximumRecords != null && maximumRecords.intValue() >= 0) {
            request.setMaximumRecords(new NonNegativeInteger(Integer.toString(maximumRecords.intValue(), 10)));
        }
        if (startRecord != null && startRecord.intValue() >= 1) {
            request.setStartRecord(new PositiveInteger(Integer.toString(startRecord.intValue(), 10)));
        }

        return search(request, database);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.SearchHandlerClientInterface#search
     * (gov.loc.www.zing.srw.SearchRetrieveRequestType, java.lang.String)
     */
    @Override
    public SearchRetrieveResponse search(final SearchRetrieveRequestType request, final String database)
        throws EscidocClientException, InternalClientException, TransportException {

        checkNotNull(request);

        final String xml = getClient().search(request, database);

        return MarshallerFactory.getInstance().getMarshaller(SearchRetrieveResponse.class).unmarshalDocument(xml);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.SearchHandlerClientInterface#scan(gov
     * .loc.www.zing.srw.ScanRequestType, java.lang.String)
     */
    @Override
    public ScanResponse scan(final ScanRequestType request, final String database) throws EscidocClientException,
        InternalClientException, TransportException {

        checkNotNull(request);

        return MarshallerFactory.getInstance().getMarshaller(ScanResponse.class).unmarshalDocument(
            getClient().scan(request, database));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.AbstractHandlerClient#getRestHandlerClientInstance
     * ()
     */
    @Override
    protected RestSearchHandlerClient getRestHandlerClientInstance() throws InternalClientException {
        return new RestSearchHandlerClient(getServiceAddress());
    }
}