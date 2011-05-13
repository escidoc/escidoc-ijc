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

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.rest.RestUserAccountHandlerClient;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.util.Template;

/**
 * Test User Account Grants via REST interface.
 * 
 * @author SWA
 * 
 */
public class GrantsTest {

    private Authentication auth;

    private RestUserAccountHandlerClient uahc;

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.getDefaultInfrastructureURL(),
                EscidocClientTestBase.SYSTEM_ADMIN_USER, EscidocClientTestBase.SYSTEM_ADMIN_PASSWORD);
        uahc = new RestUserAccountHandlerClient(auth.getServiceAddress());
        uahc.setHandle(auth.getHandle());
    }

    @After
    public void post() throws Exception {
        auth.logout();
    }

    /**
     * Test to create and retrieve a grant.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testCreateGrant01() throws Exception {
        // create User Account
        String resourceXml =
            EscidocClientTestBase.getXmlFileAsString(Template.load("/rest/aa/user_account/"
                + "escidoc_useraccount_for_create.xml"));

        // prepare template
        resourceXml = resourceXml.replace("###EMAIL###", System.nanoTime() + "-test@escidoc.org");
        resourceXml = resourceXml.replace("###NAME###", System.nanoTime() + "-test");
        resourceXml = resourceXml.replace("###LOGIN###", System.nanoTime() + "-test");

        String cAccountXml = uahc.create(resourceXml);

        String[] objidLmd = EscidocClientTestBase.obtainObjidAndLmd(cAccountXml);

        // create Grant
        final String role = "/aa/role/escidoc:role-system-administrator";
        String grant =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<grants:grant\n"
                + " xmlns:grants=\"http://www.escidoc.de/schemas/grants/0.5\""
                + " xmlns:prop=\"http://escidoc.de/core/01/properties/\""
                + " xmlns:srel=\"http://escidoc.de/core/01/structural-relations/\""
                + " xmlns:xlink=\"http://www.w3.org/1999/xlink\">\n"

                + " <grants:properties>\n" + "   <srel:role xlink:href=\"" + role + "\" />\n"
                + " </grants:properties>\n"

                + " </grants:grant>";

        String createdGrant = uahc.createGrant(objidLmd[0], grant);

        assertTrue("Missing role in grant", createdGrant.contains(role));

        String[] grantObjLmd = EscidocClientTestBase.obtainObjidAndLmd(createdGrant);

        String grants = uahc.retrieveCurrentGrants(objidLmd[0]);
        assertTrue("Missing role", grants.contains(grantObjLmd[0]));

    }

    /**
     * Test to create and retrieve a grant with an assigned-on reference. An
     * Item is created which is set as scope for the role.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testCreateGrant02() throws Exception {
        // create User Account
        String resourceXml =
            EscidocClientTestBase.getXmlFileAsString(Template.load("/rest/aa/user_account/"
                + "escidoc_useraccount_for_create.xml"));

        // prepare template
        resourceXml = resourceXml.replace("###EMAIL###", System.nanoTime() + "-test@escidoc.org");
        resourceXml = resourceXml.replace("###NAME###", System.nanoTime() + "-test");
        resourceXml = resourceXml.replace("###LOGIN###", System.nanoTime() + "-test");

        String cAccountXml = uahc.create(resourceXml);

        String[] objidLmd = EscidocClientTestBase.obtainObjidAndLmd(cAccountXml);

        // create Grant
        final String role = "/aa/role/escidoc:role-system-administrator";
        String grant =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<grants:grant\n"
                + " xmlns:grants=\"http://www.escidoc.de/schemas/grants/0.5\""
                + " xmlns:prop=\"http://escidoc.de/core/01/properties/\""
                + " xmlns:srel=\"http://escidoc.de/core/01/structural-relations/\""
                + " xmlns:xlink=\"http://www.w3.org/1999/xlink\">\n"

                + " <grants:properties>\n" + "   <srel:role xlink:href=\"" + role + "\" />\n"
                + " </grants:properties>\n"

                + " </grants:grant>";

        String createdGrant = uahc.createGrant(objidLmd[0], grant);

        assertTrue("Missing role in grant", createdGrant.contains(role));

        String[] grantObjLmd = EscidocClientTestBase.obtainObjidAndLmd(createdGrant);

        String grants = uahc.retrieveCurrentGrants(objidLmd[0]);
        assertTrue("Missing role", grants.contains(grantObjLmd[0]));

    }

    /**
     * Test to revoke a grant.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testDeleteGrant01() throws Exception {
        // create User Account
        String resourceXml =
            EscidocClientTestBase.getXmlFileAsString(Template.load("/rest/aa/user_account/"
                + "escidoc_useraccount_for_create.xml"));

        // prepare template
        resourceXml = resourceXml.replace("###EMAIL###", System.nanoTime() + "-test@escidoc.org");
        resourceXml = resourceXml.replace("###NAME###", System.nanoTime() + "-test");
        resourceXml = resourceXml.replace("###LOGIN###", System.nanoTime() + "-test");

        String cAccountXml = uahc.create(resourceXml);

        String[] objidLmd = EscidocClientTestBase.obtainObjidAndLmd(cAccountXml);

        // create Grant
        String grant =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<grants:grant\n"
                + " xmlns:grants=\"http://www.escidoc.de/schemas/grants/0.5\""
                + " xmlns:prop=\"http://escidoc.de/core/01/properties/\""
                + " xmlns:srel=\"http://escidoc.de/core/01/structural-relations/\""
                + " xmlns:xlink=\"http://www.w3.org/1999/xlink\">\n"

                + " <grants:properties>\n"
                + "   <srel:role xlink:href=\"/aa/role/escidoc:role-system-administrator\" />\n"
                + " </grants:properties>\n"

                + " </grants:grant>";

        String createdGrant = uahc.createGrant(objidLmd[0], grant);
        String[] grantObjLmd = EscidocClientTestBase.obtainObjidAndLmd(createdGrant);

        // delete just created Grant
        String taskParam =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<param last-modification-date=\"" + grantObjLmd[1]
                + "\">\n" + "   <revocation-mark>Test revocation</revocation-mark>\n" + "</param>";

        uahc.revokeGrant(objidLmd[0], grantObjLmd[0], taskParam);

        String revokedGrant = uahc.retrieveGrant(objidLmd[0], grantObjLmd[0]);
        assertTrue("Missing revoke statement", revokedGrant.contains("revoked-by"));
    }
}
