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
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.util.List;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.base.CrudService;
import de.escidoc.core.client.interfaces.base.HandlerService;
import de.escidoc.core.client.interfaces.base.MdRecordService;
import de.escidoc.core.client.interfaces.base.OpenCloseService;
import de.escidoc.core.client.interfaces.base.PropertiesService;
import de.escidoc.core.resources.oum.OrganizationalUnit;
import de.escidoc.core.resources.oum.OrganizationalUnitList;
import de.escidoc.core.resources.oum.OrganizationalUnitProperties;
import de.escidoc.core.resources.oum.Parents;
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
    extends HandlerService, CrudService<OrganizationalUnit>,
    OpenCloseService<OrganizationalUnit>,
    PropertiesService<OrganizationalUnit, OrganizationalUnitProperties>,
    MdRecordService {

    /**
     * 
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    SearchRetrieveResponse retrieveParentObjects(final String id)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    List<OrganizationalUnit> retrieveParentObjectsAsList(final String id)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param ou
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    SearchRetrieveResponse retrieveParentObjects(final OrganizationalUnit ou)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * @param ou
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    List<OrganizationalUnit> retrieveParentObjectsAsList(OrganizationalUnit ou)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    OrganizationalUnitList retrieveSuccessors(final String id)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param ou
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    OrganizationalUnitList retrieveSuccessors(final OrganizationalUnit ou)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    SearchRetrieveResponse retrieveChildObjects(final String id)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * @param ou
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public List<OrganizationalUnit> retrieveChildObjectsAsList(
        final OrganizationalUnit ou) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    List<OrganizationalUnit> retrieveChildObjectsAsList(final String id)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param ou
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    SearchRetrieveResponse retrieveChildObjects(final OrganizationalUnit ou)
        throws EscidocException, InternalClientException, TransportException;

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

    /**
     * 
     * @param ou
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    PathList retrievePathList(final OrganizationalUnit ou)
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
     * This is a convenience method to retrieve the resulting objects as a list.
     * Since it could happen, that binding of an object fails, this list will
     * not contain all objects, which could not be bounded. In case you wish to
     * have complete control over the results, you may use the method
     * {@link #retrieveOrganizationalUnits(SearchRetrieveRequestType)}, since
     * you can still work with the resulting DOM.
     * 
     * Usually binding of an object fails, if the server returns unexpected
     * record data.
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    List<OrganizationalUnit> retrieveOrganizationalUnitsAsList(
        final SearchRetrieveRequestType filter) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * Updates the parents of an Organizational Unit.<br/>
     * <br/>
     * Preconditions:
     * <ul>
     * <li>The Organizational Unit must exist.</li>
     * <li>The public-status is "opened".</li>
     * </ul>
     * 
     * @param id
     * @param parents
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Parents updateParents(final String id, final Parents parents)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * Updates the parents of an Organizational Unit.<br/>
     * <br/>
     * Preconditions:
     * <ul>
     * <li>The Organizational Unit must exist.</li>
     * <li>The public-status is "opened".</li>
     * </ul>
     * <br/>
     * <br/>
     * The supplied Parents object must have a last modification date
     * definition. This method does the following routine:<br/>
     * <br/>
     * <ul>
     * <li>If the last modification date exists in <code>parents</code>
     * <ul>
     * <li>keep this value</li>
     * </ul>
     * </li>
     * <li>Otherwise
     * <ul>
     * <li>If the Parents object of the <code>ou</code> has no last modification
     * date
     * <ul>
     * <li>Use the last modification date from the <code>ou</code></li>
     * </ul>
     * </li>
     * <li>Otherwise
     * <ul>
     * <li>Use the last modification date from the Parents object</li>
     * </ul>
     * </li>
     * </ul>
     * </li>
     * </ul>
     * 
     * @param id
     *            The identifier of the Organizational Unit.
     * @param parents
     *            The Parents object of the corresponding Organizational Unit.
     * @return The updated Parents object of the Organizational Unit.
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Parents updateParents(final OrganizationalUnit ou, final Parents parents)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Parents retrieveParents(final String id) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * 
     * @param ou
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Parents retrieveParents(final OrganizationalUnit ou)
        throws EscidocException, InternalClientException, TransportException;
}