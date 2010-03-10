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
package de.escidoc.core.test.client.classMapping.oum;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.Test;

import de.escidoc.core.client.OrganizationalUnitHandlerClient;
import de.escidoc.core.client.exceptions.application.notfound.OrganizationalUnitNotFoundException;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.common.Filter;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.oum.OrganizationalUnit;
import de.escidoc.core.resources.oum.OrganizationalUnitList;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * 
 * @author SWA
 * 
 */
public class OrganizationalUnitHandlerClientTest extends EscidocClientTestBase {

    private final Logger logger =
        Logger.getLogger(OrganizationalUnitHandlerClientTest.class.getName());

    /**
     * Test if the right exception is thrown if a non existing OrganizationUnit
     * is retrieved.
     * 
     * @throws Exception
     *             Thrown if not the right exception is caught.
     */
    @Test
    public void testRetrieveUnknown() throws Exception {
        try {

            OrganizationalUnitHandlerClient ic =
                new OrganizationalUnitHandlerClient();
            ic.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);
            ic.retrieve(INVALID_RESOURCE_ID);
            fail("Missing Exception");
        }
        catch (OrganizationalUnitNotFoundException e) {
            logger
                .debug("Caught right exception OrganizationUnitNotFoundException");
        }
        catch (Exception e) {
            logger.debug(e);
            fail("Wrong exception caught: " + e.getMessage());
        }
    }

    /**
     * Test retrieving existing OrganizationUnit.
     * 
     * @throws Exception
     *             Thrown if retrieve and/or marshalling failed.
     */
    @Test
    public void testRetrieve01() throws Exception {
        try {
            OrganizationalUnitHandlerClient ic =
                new OrganizationalUnitHandlerClient();
            ic.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);
            OrganizationalUnit organizationUnit =
                ic.retrieve(EXAMPLE_ORGANIZATIONAL_UNIT_ID);
            logger.debug(Factory
                .getOrganizationalUnitMarshaller().marshalDocument(
                    (OrganizationalUnit) organizationUnit));
        }
        catch (Exception e) {
            logger.debug(e);
            fail("Wrong exception caught: " + e.getMessage());
        }
    }

    /**
     * Test retrieving existing OrganizationUnit.
     * 
     * @throws Exception
     *             Thrown if retrieve and/or marshalling failed.
     */
    @Test
    public void testRetrieveUpdate() throws Exception {
        try {
            OrganizationalUnitHandlerClient ic =
                new OrganizationalUnitHandlerClient();
            ic.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);
            OrganizationalUnit organizationUnit =
                ic.retrieve(EXAMPLE_ORGANIZATIONAL_UNIT_ID);
            ic.update(organizationUnit);
            logger.debug(Factory
                .getOrganizationalUnitMarshaller().marshalDocument(
                    (OrganizationalUnit) organizationUnit));
        }
        catch (Exception e) {
            logger.debug(e);
            fail("Wrong exception caught: " + e.getMessage());
        }
    }

    /**
     * Test retrieving child organizational units.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testRetrieveChildObjects() throws Exception {
        OrganizationalUnitHandlerClient ic =
            new OrganizationalUnitHandlerClient();

        logger.debug(ic
            .retrieveChildObjects(EXAMPLE_ORGANIZATIONAL_UNIT_ID)
            .getOrganizationalUnits());
    }

    /**
     * Test retrieving organizational units through filter request.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testRetrieveOrganizationalUnits() throws Exception {
        TaskParam filterParam = new TaskParam();
        Collection<Filter> filters = TaskParam.filtersFactory();

        filters.add(getFilter(
            "http://escidoc.de/core/01/structural-relations/created-by",
            "escidoc:exuser1", null));
        filterParam.setFilters(filters);
        logger.debug("Call retrieveOrganizationalUnits with filter "
            + Factory.getTaskParamMarshaller().marshalDocument(filterParam));
        OrganizationalUnitHandlerClient ic =
            new OrganizationalUnitHandlerClient();
        OrganizationalUnitList ouList =
            ic.retrieveOrganizationalUnits(filterParam);

        assertTrue("result list is empty, try another filter", ouList
            .getOrganizationalUnits().size() != 0);
    }

    /**
     * Test retrieving parent organizational units.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testRetrieveParentObjects() throws Exception {
        OrganizationalUnitHandlerClient ic =
            new OrganizationalUnitHandlerClient();

        logger.debug(ic
            .retrieveParentObjects(EXAMPLE_ORGANIZATIONAL_UNIT_ID)
            .getOrganizationalUnits());
    }

    /**
     * Prepare and Filter class from the parameter collection.
     * 
     * @param name
     *            name of the filter criteria
     * @param value
     *            value of the filter criteria
     * @param ids
     *            list of ids to filter
     * 
     * @return filter
     */
    private Filter getFilter(
        final String name, final String value, final Collection<String> ids) {

        Filter filter = new Filter();
        filter.setName(name);
        filter.setValue(value);
        filter.setIds(ids);
        return filter;
    }
}
