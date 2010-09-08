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
package de.escidoc.core.test.client.integrationTests.classMapping.om.context;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ContextHandlerClient;
import de.escidoc.core.client.interfaces.ContextHandlerClientInterface;
import de.escidoc.core.resources.common.Filter;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.om.context.ContextList;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test the Context Filter. These are tests of the old (deprecated) filters.
 * 
 * @author SWA
 * 
 */
public class ContextFilterTest {

    /**
     * Test retrieving all Contexts through filter request.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testRetrieveContexts() throws Exception {

        TaskParam filterParam = new TaskParam();
        Collection<Filter> filters = TaskParam.filtersFactory();
        filters.add(new Filter());

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContextHandlerClientInterface ic = new ContextHandlerClient();
        ic.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        ic.setHandle(auth.getHandle());

        ContextList contextList = ic.retrieveContexts(filterParam);

        assertTrue("At least one Context should be there", contextList
            .getContexts().size() > 0);
    }

}
