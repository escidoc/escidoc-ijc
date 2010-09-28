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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.rest.RestUserAccountHandlerClient;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.util.Template;

/**
 * Test User Account Preferences via REST interface.
 * 
 * @author SWA
 * 
 */
public class UserAccountPreferencesTestRest {

    /**
     * Test to create and retrieve user account preferences.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testPreferences01() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        RestUserAccountHandlerClient uahc = new RestUserAccountHandlerClient();
        uahc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        uahc.setHandle(auth.getHandle());

        // create User Account
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

        // create Preference
        String preferenceXml =
            EscidocClientTestBase.getXmlFileAsString(Template
                .load("/rest/aa/user_account/" + "useraccount_preference.xml"));

        String createXml = uahc.createPreference(objidLmd[0], preferenceXml);
        assertTrue("Missing preferencce",
            createXml.contains("KeyForTestCreate"));
        assertTrue("Missing preferencce",
            createXml.contains("ValueForTestCreate"));

        // retrieve
        String preferencesXml = uahc.retrievePreferences(objidLmd[0]);
        assertTrue("Missing preferencce",
            preferencesXml.contains("KeyForTestCreate"));
        assertTrue("Missing preferencce",
            preferencesXml.contains("ValueForTestCreate"));
    }

    /**
     * Test to update an user account preferences.
     * 
     * @throws Exception
     *             If behavior is not as expected.
     */
    @Test
    public void testUpdatePreference01() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        RestUserAccountHandlerClient uahc = new RestUserAccountHandlerClient();
        uahc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        uahc.setHandle(auth.getHandle());

        // create User Account
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

        // create Preference
        String preferenceXml =
            EscidocClientTestBase.getXmlFileAsString(Template
                .load("/rest/aa/user_account/" + "useraccount_preference.xml"));

        uahc.createPreference(objidLmd[0], preferenceXml);
        String createdXml = uahc.retrievePreferences(objidLmd[0]);

        // update Preference
        String newPreferencesXml =
            createdXml.replace("ValueForTestCreate", "ValueForTestUpdate");

        String updatedXml =
            uahc.updatePreferences(objidLmd[0], newPreferencesXml);
        assertTrue("Missing preferencce",
            updatedXml.contains("KeyForTestCreate"));
        assertTrue("Missing preferencce",
            updatedXml.contains("ValueForTestUpdate"));

        // retrieve
        String preferencesXml = uahc.retrievePreferences(objidLmd[0]);
        assertTrue("Missing preferencce",
            preferencesXml.contains("KeyForTestCreate"));
        assertTrue("Missing preferencce",
            preferencesXml.contains("ValueForTestUpdate"));
    }

    /**
     * Test to update an user account preferences.
     * 
     * @throws Exception
     *             If behavior is not as expected.
     */
    @Test
    public void testDeletePreference01() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        RestUserAccountHandlerClient uahc = new RestUserAccountHandlerClient();
        uahc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        uahc.setHandle(auth.getHandle());

        // create User Account
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

        // create Preference
        String preferenceXml =
            EscidocClientTestBase.getXmlFileAsString(Template
                .load("/rest/aa/user_account/" + "useraccount_preference.xml"));

        uahc.createPreference(objidLmd[0], preferenceXml);

        uahc.deletePreference(objidLmd[0], "KeyForTestCreate");

        String updatedXml = uahc.retrievePreferences(objidLmd[0]);
        assertFalse("Missing preferencce",
            updatedXml.contains("KeyForTestCreate"));
        assertFalse("Missing preferencce",
            updatedXml.contains("ValueForTestUpdate"));

    }
}
