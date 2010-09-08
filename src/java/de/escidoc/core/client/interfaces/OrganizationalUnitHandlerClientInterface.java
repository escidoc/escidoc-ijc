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

import java.util.Collection;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.oum.OrganizationalUnit;
import de.escidoc.core.resources.oum.OrganizationalUnitList;
import de.escidoc.core.resources.oum.PathList;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;

/**
 * Interface for Organizational Unit Handler.
 * 
 * @author SWA
 * 
 */
public interface OrganizationalUnitHandlerClientInterface
    extends ResourceHandlerInterface<OrganizationalUnit> {

    /**
     * 
     * @param contextId
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Result open(final String contextId, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param contextId
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Result close(final String contextId, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException;

    // StructMap retrieveParents(final String id, final String xml)
    // throws EscidocException, InternalClientException, TransportException;
    //
    // StructMap updateParents(final String id, final String xml)
    // throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     */
    OrganizationalUnitList retrieveParentObjects(final String id)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    OrganizationalUnitList retrieveChildObjects(final String id)
        throws EscidocException, InternalClientException, TransportException;

    @Deprecated
    OrganizationalUnitList retrieveOrganizationalUnits(final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException;
    
    /**
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    ExplainResponse retrieveOrganizationalUnits(final ExplainRequestType filter)
    	throws EscidocException, InternalClientException, TransportException;
    
    /**
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    SearchRetrieveResponse retrieveOrganizationalUnits(
        final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException;
    
    /**
     * This is a convenience method to retrieve the resulting items as a list.
     * Since it could happen, that binding of an item fails, this list
     * will not contain all items, which could not be bounded.
     * In case you wish to have complete control over the results, you may use
     * the method retrieveOrganizationalUnits(final SearchRetrieveRequestType filter),
     * since you can still work with the resulting DOM.
     * 
     * Usually binding of an item will only fail, if the server returns
     * unexpected record data.
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Collection<OrganizationalUnit> retrieveOrganizationalUnitsAsList(
            final SearchRetrieveRequestType filter) throws EscidocException,
            InternalClientException, TransportException;

    /**
     * 
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    PathList retrievePathList(final String id) throws EscidocException,
        InternalClientException, TransportException;

    // updateMdRecords

}
