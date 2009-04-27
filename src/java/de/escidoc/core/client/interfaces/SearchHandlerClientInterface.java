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
import gov.loc.www.zing.srw.ScanResponseType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;
import gov.loc.www.zing.srw.SearchRetrieveResponseType;
import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.resources.sb.explain.ExplainRecord;
import de.escidoc.core.resources.sb.wrapper.explain.ExplainResponse;
import de.escidoc.core.resources.sb.wrapper.search.SearchResponse;

/**
 * This class defines the signatures for the client handler wrapper classes
 * where the transport specific exceptions are mapped to internal client
 * internal exception.
 * 
 * @author SWA
 * 
 */
public interface SearchHandlerClientInterface {
    /**
     * @param request
     * @param database
     *            database used for search 
     *            if null, database "escidoc_all" will
     *            be used for search
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    ExplainResponseType explain(
        final ExplainRequestType request, final String database)
        throws EscidocClientException, InternalClientException,
        TransportException;
    
    
    /**
     * 
     * @param request
     * @param database
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
     * @param request
     * @param database
     *            database used for search 
     *            if null, database "escidoc_all" will
     *            be used for search
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    SearchResponse search2(
        final SearchRetrieveRequestType request, final String database)
        throws EscidocClientException, InternalClientException,
        TransportException;
    /**
     * @param request
     * @param database
     *            database used for search 
     *            if null, database "escidoc_all" will
     *            be used for search
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
     * @param database
     *            database used for search 
     *            if null, database "escidoc_all" will
     *            be used for search
     * @return
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    ScanResponseType scan(final ScanRequestType request, final String database)
        throws EscidocClientException, InternalClientException,
        TransportException;

}
