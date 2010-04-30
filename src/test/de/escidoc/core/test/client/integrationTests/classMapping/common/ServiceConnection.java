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
 * Copyright 2006-2010 Fachinformationszentrum Karlsruhe Gesellschaft
 * fuer wissenschaftlich-technische Information mbH and Max-Planck-
 * Gesellschaft zur Foerderung der Wissenschaft e.V.  
 * All rights reserved.  Use is subject to license terms.
 */
package de.escidoc.core.test.client.integrationTests.classMapping.common;

import static org.junit.Assert.assertNotNull;
import de.escidoc.core.client.ContainerHandlerClient;
import de.escidoc.core.client.ContextHandlerClient;
import de.escidoc.core.client.ItemHandlerClient;
import de.escidoc.core.resources.om.container.Container;
import de.escidoc.core.resources.om.context.Context;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test the Service Connection.
 * 
 * @author SWA
 * 
 */
public class ServiceConnection {

    private static final String ITEM_ID = "escidoc:ex5";

    private static final String CONTAINER_ID = "escidoc:ex7";

    private static final String CONTEXT_ID = "escidoc:ex1";

    /**
     * Test if retrieve of an Item from a non http://localhost:8080 service is
     * possible.
     * 
     * @throws Exception
     *             Thrown if retrieve failed.
     */
    public void testItem01() throws Exception {

        ItemHandlerClient cc = new ItemHandlerClient();
        cc.login(EscidocClientTestBase.DEFAULT_SERVICE_URL,
            Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        Item item = cc.retrieve(ITEM_ID);
        String lmd = item.getLastModificationDateAsString();
        assertNotNull("Item timestamp is null", lmd);

    }

    /**
     * Test if retrieve of an Container from a non http://localhost:8080 service
     * is possible.
     * 
     * @throws Exception
     *             Thrown if retrieve failed.
     */
    public void testContainer01() throws Exception {

        ContainerHandlerClient cc = new ContainerHandlerClient();
        cc.login(EscidocClientTestBase.DEFAULT_SERVICE_URL,
            Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        Container container = cc.retrieve(CONTAINER_ID);
        String lmd = container.getLastModificationDateAsString();
        assertNotNull("Container timestamp is null", lmd);

    }

    /**
     * Test if retrieve of an Context from a non http://localhost:8080 service
     * is possible.
     * 
     * @throws Exception
     *             Thrown if retrieve failed.
     */
    public void testContext01() throws Exception {

        ContextHandlerClient cc = new ContextHandlerClient();
        cc.login(EscidocClientTestBase.DEFAULT_SERVICE_URL,
            Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        Context context = cc.retrieve(CONTEXT_ID);
        String lmd = context.getLastModificationDateAsString();
        assertNotNull("Context timestamp is null", lmd);

    }

}
