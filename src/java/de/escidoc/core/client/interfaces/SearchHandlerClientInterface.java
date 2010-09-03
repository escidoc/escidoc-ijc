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
package de.escidoc.core.client.interfaces;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.ExplainResponseType;
import gov.loc.www.zing.srw.ScanRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;
import gov.loc.www.zing.srw.SearchRetrieveResponseType;
import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.common.configuration.ConfigurationProvider;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.scan.ScanResponse;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;

/**
 * This class defines the signatures for the client handler wrapper classes
 * where the transport specific exceptions are mapped to internal client
 * internal exception.
 * 
 * @author SWA, MVO
 * 
 */
public interface SearchHandlerClientInterface {
    
	/**
     * @param request
     * @param database The database to perform the search on. If database is 
     * null, the default value will be determined from 
     * {@link ConfigurationProvider}.
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    ExplainResponseType explain(
        final ExplainRequestType request, final String soapDatabase)
        throws EscidocClientException, InternalClientException,
        TransportException;
    
    /**
     * 
     * @param request
     * @param database The database to perform the search on. If database is 
     * null, the default value will be determined from 
     * {@link ConfigurationProvider}.
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    ExplainResponse explain2(
        final ExplainRequestType request, final String database)
        throws EscidocClientException, InternalClientException,
        TransportException;
    
    /**
     * 
     * @param query The query to perform.
     * @param database The database to perform the search on. If database is
	 * null, the default value will be determined from
	 * {@link ConfigurationProvider}.
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    SearchRetrieveResponse search(final String query, final String database)
    	throws EscidocClientException, InternalClientException,
    		TransportException;
    
    /**
     * 
     * @param query The query to perform.
     * @param startRecord A positive integer. {1, 2, ...}
     * @param maximumRecords A non negative integer. {0, 1, ...} 
     * @param sortKeys The keys to be used to sort the results. See explain 
     * plan for available sortKeys.
     * @param database The database to perform the search on. If database is 
     * null, the default value will be determined from 
     * {@link ConfigurationProvider}.
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    SearchRetrieveResponse search(final String query, 
    		final Integer startRecord, final Integer maximumRecords,
			final String sortKeys, final String database)
    	throws EscidocClientException, InternalClientException,
    		TransportException;
    
    /**
     * 
     * @param query The query to perform.
     * @param startRecord A positive integer. {1, 2, ...}
     * @param maximumRecords A non negative integer. {0, 1, ...} 
     * @param sortKeys The keys to be used to sort the results. See explain 
     * plan for available sortKeys.
     * @param stylesheetURI The stylesheet URI, which will be used for the 
     * result.
     * @param version
     * @param database The database to perform the search on. If database is 
     * null, the default value will be determined from 
     * {@link ConfigurationProvider}.
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    SearchRetrieveResponse search(final String query, 
    		final Integer startRecord, final Integer maximumRecords,
			final String sortKeys, final String stylesheetURI,
			final String version, final String database)
    	throws EscidocClientException, InternalClientException,
    		TransportException;
    
    /**
     * @param request
     * @param database The database to perform the search on. If database is 
     * null, the default value will be determined from 
     * {@link ConfigurationProvider}.
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    SearchRetrieveResponse search2(
        final SearchRetrieveRequestType request, final String database)
        throws EscidocClientException, InternalClientException,
        TransportException;
    
    /**
     * @param request
     * @param database The database to perform the search on. If database is 
     * null, the default value will be determined from 
     * {@link ConfigurationProvider}.
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    SearchRetrieveResponseType search(
        final SearchRetrieveRequestType request, final String database)
        throws EscidocClientException, InternalClientException,
        TransportException;

    /**
     * @param request
     * @param database The database to perform the search on. If database is 
     * null, the default value will be determined from 
     * {@link ConfigurationProvider}.
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    ScanResponse scan(final ScanRequestType request, final String database)
        throws EscidocClientException, InternalClientException,
        TransportException;

}
