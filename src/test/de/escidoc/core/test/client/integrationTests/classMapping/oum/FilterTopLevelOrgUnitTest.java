package de.escidoc.core.test.client.integrationTests.classMapping.oum;

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

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.OrganizationalUnitHandlerClient;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface;
import de.escidoc.core.resources.common.Filter;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.oum.OrganizationalUnit;
import de.escidoc.core.resources.oum.OrganizationalUnitList;
import de.escidoc.core.resources.oum.Parent;
import de.escidoc.core.resources.oum.Parents;
import de.escidoc.core.test.client.AbstractParameterizedTestBase;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test filtering Top Level OrganizationalUnit.
 * 
 * @author CHH
 * 
 */
public class FilterTopLevelOrgUnitTest extends AbstractParameterizedTestBase {

    private static final String IS_TOP_LEVEL = "true";

    private static final String TOP_LEVEL_ORGANIZATIONAL_UNITS =
        "top-level-organizational-units";

    private final OrganizationalUnitHandlerClientInterface orgUnitClient =
        new OrganizationalUnitHandlerClient();

    private Collection<OrganizationalUnit> rootOrgUnits;

    public FilterTopLevelOrgUnitTest(final TransportProtocol transport) {
        super(transport);
    }

    @Before
    public void setUp() throws EscidocClientException {
        authentificateAsSysAdmin();
        orgUnitClient.setTransport(transport);
    }

    private void authentificateAsSysAdmin() throws EscidocClientException {
        final Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);
        orgUnitClient.setHandle(auth.getHandle());
        orgUnitClient
            .setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
    }

    @Test
    public void shouldReturnOrgUnitWithNoParent() throws EscidocException,
        InternalClientException, TransportException {
        // TODO create one new org unit, to ensure one org unit exist.
        assertThatOrgUnitExist();
        assertThatOrgUnitsDoNotHaveParent();
    }

    private void assertThatOrgUnitExist() throws EscidocException,
        InternalClientException, TransportException {
        final OrganizationalUnitList rootOrgUnitList = getTopLevelOrgUnits();
        assertTrue("RootOrgUnitList is null.", rootOrgUnitList != null);

        rootOrgUnits = rootOrgUnitList.getOrganizationalUnits();
        assertTrue("Root Org Units is empty.", rootOrgUnits.size() > 0);
    }

    private void assertThatOrgUnitsDoNotHaveParent() {
        for (final OrganizationalUnit organizationalUnit : rootOrgUnits) {
            final Parents parents = organizationalUnit.getParents();
            final Collection<Parent> parentRef = parents.getParentRef();
            assertNull("Root org unit can not have parent", parentRef);
        }
    }

    private OrganizationalUnitList getTopLevelOrgUnits()
        throws EscidocException, InternalClientException, TransportException {
        return orgUnitClient
            .retrieveOrganizationalUnits(createTaskParamWithTopLevelFilter());
    }

    private TaskParam createTaskParamWithTopLevelFilter() {
        final Collection<Filter> filters = TaskParam.filtersFactory();
        filters.add(createTopLevelFilter());
        final TaskParam taskParam = new TaskParam();
        taskParam.setFilters(filters);
        return taskParam;
    }

    private Filter createTopLevelFilter() {
        final Filter filter = new Filter();
        filter.setName(TOP_LEVEL_ORGANIZATIONAL_UNITS);
        filter.setValue(IS_TOP_LEVEL);
        filter.setIds(Collections.singletonList(""));
        return filter;
    }
}