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
package de.escidoc.core.test.client.integrationTests.classMapping.aa.user_account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.UserAccountHandlerClient;
import de.escidoc.core.client.exceptions.application.notfound.PreferenceNotFoundException;
import de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface;
import de.escidoc.core.resources.aa.useraccount.Preference;
import de.escidoc.core.resources.aa.useraccount.Preferences;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.aa.useraccount.UserAccountProperties;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test User Account Preferences with class mapping.
 * 
 * @author SWA
 * 
 */
public class UserAccountPreferencesTest {

    private Authentication auth;

    private UserAccountHandlerClientInterface uahc;

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.getDefaultInfrastructureURL(),
                EscidocClientTestBase.SYSTEM_ADMIN_USER, EscidocClientTestBase.SYSTEM_ADMIN_PASSWORD);
        uahc = new UserAccountHandlerClient(auth.getServiceAddress());
        uahc.setHandle(auth.getHandle());
    }

    @After
    public void post() throws Exception {
        if (auth != null)
            auth.logout();
    }

    /**
     * Test to create and retrieve user account preferences.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testPreferences01() throws Exception {
        // create User Account
        UserAccount ua = new UserAccount();

        // user properties
        UserAccountProperties properties = new UserAccountProperties();
        String login = "login" + System.currentTimeMillis();
        properties.setName("Name " + login);
        properties.setLoginName(login);

        ua.setProperties(properties);
        UserAccount createdUa = uahc.create(ua);

        String objId = createdUa.getObjid();

        // create Preference
        Preference uaPref = new Preference("PreferenceName", "PreferenceValue");

        Preference createPref = uahc.createPreference(objId, uaPref);
        assertEquals("Preference name differs", uaPref.getName(),
            createPref.getName());
        assertEquals("Preference value differs", uaPref.getValue(),
            createPref.getValue());

        // retrieve
        Preferences preferences = uahc.retrievePreferences(objId);
        assertTrue("Wrong number of preferences", preferences.size() == 1);
        Preference p = preferences.iterator().next();
        assertEquals("Preference name differs", uaPref.getName(), p.getName());
        assertEquals("Preference value differs", uaPref.getValue(),
            p.getValue());
    }

    /**
     * Test to update an user account preferences.
     * 
     * @throws Exception
     *             If behavior is not as expected.
     */
    @Test
    public void testUpdatePreference01() throws Exception {
        // create User Account
        UserAccount ua = new UserAccount();

        // user properties
        UserAccountProperties properties = new UserAccountProperties();
        String login = "login" + System.currentTimeMillis();
        properties.setName("Name " + login);
        properties.setLoginName(login);

        ua.setProperties(properties);
        UserAccount createdUa = uahc.create(ua);

        // test marshalling
        String objId = createdUa.getObjid();

        // create Preference
        Preference uaPref = new Preference("PreferenceName", "PreferenceValue");

        Preference createPref = uahc.createPreference(objId, uaPref);
        assertEquals("Preference name differs", uaPref.getName(),
            createPref.getName());
        assertEquals("Preference value differs", uaPref.getValue(),
            createPref.getValue());

        // update Preference
        uaPref = new Preference("PreferenceName", "PreferenceValue2");
        // do not forget the last-modification-date
        uaPref.setLastModificationDate(createPref.getLastModificationDate());
        createPref = uahc.updatePreference(objId, uaPref);
        assertEquals("Preference name differs", uaPref.getName(),
            createPref.getName());
        assertEquals("Preference value differs", uaPref.getValue(),
            createPref.getValue());

        // retrieve
        Preferences preferences = uahc.retrievePreferences(objId);
        assertTrue("Wrong number of preferences", preferences.size() == 1);
        Preference p = preferences.iterator().next();
        assertEquals("Preference name differs", uaPref.getName(), p.getName());
        assertEquals("Preference value differs", uaPref.getValue(),
            p.getValue());
    }

    /**
     * Test to delete an user account preferences.
     * 
     * @throws Exception
     *             If behavior is not as expected.
     */
    @Test
    public void testDeletePreference01() throws Exception {
        // create User Account
        UserAccount ua = new UserAccount();

        // user properties
        UserAccountProperties properties = new UserAccountProperties();
        String login = "login" + System.currentTimeMillis();
        properties.setName("Name " + login);
        properties.setLoginName(login);

        ua.setProperties(properties);
        UserAccount createdUa = uahc.create(ua);

        // test marshalling
        String objId = createdUa.getObjid();

        // create Preference
        Preference uaPref = new Preference("PreferenceName", "PreferenceValue");

        Preference createPref = uahc.createPreference(objId, uaPref);
        assertEquals("Preference name differs", uaPref.getName(),
            createPref.getName());
        assertEquals("Preference value differs", uaPref.getValue(),
            createPref.getValue());

        uahc.deletePreference(objId, createPref.getName());

        // retrieve
        try {
            uahc.retrievePreference(objId, uaPref.getName());
            fail("Preference not deleted.");
        }
        catch (PreferenceNotFoundException e) {
            return;
        }
    }

}
