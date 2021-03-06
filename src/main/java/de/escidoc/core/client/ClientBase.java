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
import java.rmi.Remote;
import java.util.HashMap;

import org.joda.time.DateTime;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.common.configuration.ConfigurationProvider;

/**
 * Client Handler Base.
 * 
 * @author SWA
 * 
 */
public abstract class ClientBase {

    private URL serviceAddress;

    private String handle;

    /**
     * Create ClientBase.
     * 
     * @param serviceAddress
     *            The service endpoint address.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     */
    public ClientBase(final URL serviceAddress) throws InternalClientException {
        setServiceAddress(serviceAddress);
    }

    /**
     * Get the address of the service endpoint.
     * 
     * @return address of service endpoint.
     */
    public URL getServiceAddress() {
        return this.serviceAddress;
    }

    /**
     * Set the address of the service endpoint.
     * 
     * @param address
     *            Address of service endpoint.
     * @throws InternalClientException
     *             Thrown if address is not a valid URL.
     */
    private void setServiceAddress(final URL address) throws InternalClientException {
        checkNotNull(address);
        this.serviceAddress = address;
    }

    /**
     * Get configuration.
     * 
     * @return ConfigurationProvider
     * @throws InternalClientException
     *             Thrown if loading of configuration failed.
     */
    protected ConfigurationProvider getConfiguration() throws InternalClientException {

        return ConfigurationProvider.getInstance();
    }

    /**
     * Get client.
     * 
     * @return Remote
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     */
    public abstract Remote getClient() throws InternalClientException;

    /**
     * Get the last modification date of the resource.
     * 
     * @param id
     *            object id
     * @return the last modification date of the resource since the latest
     *         request.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Deprecated
    public DateTime getLastModificationDate(final String id) throws EscidocException, InternalClientException,
        TransportException {
        throw new UnsupportedOperationException("Method no longer supported.");
    }

    /**
     * Get Authentication Handle.
     * 
     * @return the handle to set
     */
    public String getHandle() {

        return this.handle;
    }

    /**
     * Set Authentication Handle.
     * 
     * @param handle
     *            the handle to set
     */
    public void setHandle(final String handle) {

        this.handle = handle;
    }

    /**
     * Converts a SRW SearchRetrieveRequest to the data structure for filter
     * requests for eSciDoc (version 1.2).
     * 
     * @param filter
     *            SRW SearchRetrieveRequest
     * @return data structure for filter requests for eSciDoc (version 1.2)
     */
    protected HashMap<String, String[]> getEscidoc12Filter(final SearchRetrieveRequestType filter) {

        final HashMap<String, String[]> filter12 = new HashMap<String, String[]>();

        if (filter.getMaximumRecords() != null) {
            filter12.put("maximumRecords", new String[] { String.valueOf(filter.getMaximumRecords()) });
        }
        if (filter.getStartRecord() != null) {
            filter12.put("startRecord", new String[] { String.valueOf(filter.getStartRecord()) });
        }
        if (filter.getQuery() != null) {
            filter12.put("query", new String[] { filter.getQuery() });
        }
        if (filter.getVersion() != null) {
            filter12.put("version", new String[] { filter.getVersion() });
        }
        filter12.put("operation", new String[] { "searchRetrieve" });

        return filter12;
    }

    /**
     * Converts a SRW ExplainRequest to the data structure for filter requests
     * for eSciDoc (version 1.2).
     * 
     * @param filter
     *            SRW ExplainRequest
     * @return data structure for filter requests for eSciDoc (version 1.2)
     */
    protected HashMap<String, String[]> getEscidoc12Filter(final ExplainRequestType filter) {

        final HashMap<String, String[]> filter12 = new HashMap<String, String[]>();

        if (filter.getVersion() != null) {
            filter12.put("version", new String[] { filter.getVersion() });
        }
        filter12.put("operation", new String[] { "explain" });

        return filter12;
    }

    /**
     * This method validates the specified filter and sets default values to the
     * filter if and only if the tested values are null or empty.
     * 
     * Currently checked values:
     * <ul>
     * <li>version (default: "1.1")</li>
     * <li>recordPacking (default: "string")</li>
     * </ul>
     * 
     * @param request
     */
    protected void evalRequest(final SearchRetrieveRequestType request, final boolean nullQueryAllowed) {

        if (request == null)
            throw new IllegalArgumentException("Request must not be null.");
        if (!nullQueryAllowed && request.getQuery() == null)
            throw new IllegalArgumentException("Query must not be null.");

        if (request.getVersion() == null || request.getVersion().isEmpty()) {
            request.setVersion("1.1");
        }
        if (request.getRecordPacking() == null || request.getRecordPacking().isEmpty()) {
            request.setRecordPacking("string");
        }
    }

    /**
     * 
     * @param request
     */
    protected void evalRequest(final ScanRequestType request, final boolean nullQueryAllowed) {

        if (request == null)
            throw new IllegalArgumentException("Request must not be null.");
        if (!nullQueryAllowed && request.getScanClause() == null)
            throw new IllegalArgumentException("Query must not be null.");

        if (request.getVersion() == null || request.getVersion().isEmpty()) {
            request.setVersion("1.1");
        }
    }

    /**
     * This method validates the specified filter and sets default values to the
     * filter if and only if the tested values are null or empty.
     * 
     * Currently checked values:
     * <ul>
     * <li>version (default: "1.1")</li>
     * <li>recordPacking (default: "string")</li>
     * </ul>
     * 
     * @param request
     */
    protected void evalRequest(final ExplainRequestType request) {
        if (request == null)
            throw new IllegalArgumentException("Request must not be null.");

        if (request.getVersion() == null || request.getVersion().isEmpty()) {
            request.setVersion("1.1");
        }
        if (request.getRecordPacking() == null || request.getRecordPacking().isEmpty()) {
            request.setRecordPacking("string");
        }
    }
}
