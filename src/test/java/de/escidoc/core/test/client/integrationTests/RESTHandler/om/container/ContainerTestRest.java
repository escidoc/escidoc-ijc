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
package de.escidoc.core.test.client.integrationTests.RESTHandler.om.container;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.rest.RestContainerHandlerClient;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.util.Template;

/**
 * Test methods for Container on REST interface of client lib.
 * 
 * @author SWA
 * 
 */
public class ContainerTestRest {

    private Authentication auth;

    private RestContainerHandlerClient cc;

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.getDefaultInfrastructureURL(),
                EscidocClientTestBase.SYSTEM_ADMIN_USER, EscidocClientTestBase.SYSTEM_ADMIN_PASSWORD);
        cc = new RestContainerHandlerClient(auth.getServiceAddress());
        cc.setHandle(auth.getHandle());
    }

    @After
    public void post() throws Exception {
        auth.logout();
    }

    /**
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testRetrieveContainer01() throws Exception {
        cc.retrieve(EscidocClientTestBase.getStaticContainerId());
    }

    /**
     * Test successful creating a Container.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateContainer01() throws Exception {
        // get a valid container
        String container = cc.retrieve(EscidocClientTestBase.getStaticContainerId());

        // create a new one (on basis of the retrieved)
        cc.create(container);
    }

    /**
     * Test creating a Container from template file.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    @Ignore("TODO: replace fixed IDs of context and content-model in XML")
    public void testCreateContainer02() throws Exception {
        String containerXml =
            EscidocClientTestBase.getXmlFileAsString(Template.load("/rest/om/container/0.9/container01.xml"));

        // create a new Item (on basis of the valid)
        String createdContainerXml = cc.create(containerXml);

        // FIXME asserts
    }

}
