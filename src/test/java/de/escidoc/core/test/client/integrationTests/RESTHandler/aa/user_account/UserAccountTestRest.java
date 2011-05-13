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
package de.escidoc.core.test.client.integrationTests.RESTHandler.aa.user_account;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.application.notfound.UserAccountNotFoundException;
import de.escidoc.core.client.rest.RestUserAccountHandlerClient;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.util.Template;

/**
 * Test User Account via REST interface.
 * 
 * @author SWA
 * 
 */
public class UserAccountTestRest {

    private Authentication auth;

    private RestUserAccountHandlerClient uahc;

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(
                EscidocClientTestBase.getDefaultInfrastructureURL(),
                EscidocClientTestBase.SYSTEM_ADMIN_USER,
                EscidocClientTestBase.SYSTEM_ADMIN_PASSWORD);
        uahc = new RestUserAccountHandlerClient(auth.getServiceAddress());
        uahc.setHandle(auth.getHandle());
    }

    @After
    public void post() throws Exception {
        auth.logout();
    }

    /**
     * Test to create and retrieve user account.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testCreateAndRetrieveSuccessfulUserAccount() throws Exception {
        // load XML template of organizational unit
        String resourceXml =
            EscidocClientTestBase.getXmlFileAsString(Template
                .load("/rest/aa/user_account/"
                    + "escidoc_useraccount_for_create.xml"));

        // prepare template
        resourceXml =
            resourceXml.replace("###EMAIL###", System.nanoTime()
                + "-test@escidoc.org");
        resourceXml =
            resourceXml.replace("###NAME###", System.nanoTime() + "-test");
        resourceXml =
            resourceXml.replace("###LOGIN###", System.nanoTime() + "-test");

        String cAccountXml = uahc.create(resourceXml);

        String[] objidLmd =
            EscidocClientTestBase.obtainObjidAndLmd(cAccountXml);

        uahc.retrieve(objidLmd[0]);
    }

    /**
     * Test to delete an user account.
     * 
     * @throws Exception
     *             If behavior is not as expected.
     */
    @Test(expected = UserAccountNotFoundException.class)
    public void testDeleteUserAccount() throws Exception {
        // load XML template of organizational unit
        String resourceXml =
            EscidocClientTestBase.getXmlFileAsString(Template
                .load("/rest/aa/user_account/"
                    + "escidoc_useraccount_for_create.xml"));

        // prepare template
        resourceXml =
            resourceXml.replace("###NAME###", System.nanoTime() + "-test");
        resourceXml =
            resourceXml.replace("###LOGIN###", System.nanoTime() + "-test");

        String cAccountXml = uahc.create(resourceXml);

        // delete
        String[] objidLmd =
            EscidocClientTestBase.obtainObjidAndLmd(cAccountXml);

        uahc.delete(objidLmd[0]);

        // test retrieve
        uahc.retrieve(objidLmd[0]);
    }

    /**
     * Test to create a user account with null as content.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test(expected = InternalClientException.class)
    public void methodCallWithNull() throws Exception {
        uahc.create(null);
    }

}
