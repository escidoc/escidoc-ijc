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
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.UserAccountHandlerClient;
import de.escidoc.core.client.exceptions.application.notfound.UserAttributeNotFoundException;
import de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface;
import de.escidoc.core.resources.aa.useraccount.Attribute;
import de.escidoc.core.resources.aa.useraccount.Attributes;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.aa.useraccount.UserAccountProperties;
import de.escidoc.core.test.client.AbstractParameterizedTestBase;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test User Account Attributes with class mapping.
 * 
 * @author SWA
 * 
 */
public class UserAccountAttributesTest extends AbstractParameterizedTestBase {

    private Authentication auth;

    private UserAccountHandlerClientInterface uahc;

    public UserAccountAttributesTest(final TransportProtocol transport) {
        super(transport);
    }

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);
        uahc = new UserAccountHandlerClient(auth.getServiceAddress());
        uahc.setHandle(auth.getHandle());
        uahc.setTransport(transport);
    }

    @After
    public void post() throws Exception {
        if (auth != null)
            auth.logout();
    }

    /**
     * Test to create and retrieve user account attributes.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void createAttributes01() throws Exception {
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

        // create Attribute
        Attribute uaAttrib = new Attribute("AttributeName", "AttributeValue");

        Attribute createAttrib = uahc.createAttribute(objId, uaAttrib);
        assertEquals("Attribute name differs", uaAttrib.getName(),
            createAttrib.getName());
        assertEquals("Attribute value differs", uaAttrib.getValue(),
            createAttrib.getValue());

        // retrieve
        Attribute attribute =
            uahc.retrieveAttribute(objId, createAttrib.getObjid());
        assertEquals("Attribute name differs", uaAttrib.getName(),
            attribute.getName());
        assertEquals("Attribute value differs", uaAttrib.getValue(),
            attribute.getValue());
        assertEquals("Objid differs", createAttrib.getObjid(),
            attribute.getObjid());
    }

    /**
     * Test to update an user account attributes.
     * 
     * @throws Exception
     *             If behavior is not as expected.
     */
    @Test
    public void updateAttribute01() throws Exception {
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

        // create Attribute
        Attribute uaAttrib = new Attribute("AttributeName", "AttributeValue");

        Attribute createAttrib = uahc.createAttribute(objId, uaAttrib);
        assertEquals("Attribute name differs", uaAttrib.getName(),
            createAttrib.getName());
        assertEquals("Attribute value differs", uaAttrib.getValue(),
            createAttrib.getValue());

        // update Attribute
        uaAttrib = new Attribute("AttributeName", "AttributeValue2");
        uaAttrib.setObjid(createAttrib.getObjid());
        uaAttrib
            .setLastModificationDate(createAttrib.getLastModificationDate());

        createAttrib = uahc.updateAttribute(objId, uaAttrib);
        assertEquals("Attribute name differs", uaAttrib.getObjid(),
            createAttrib.getObjid());
        assertEquals("Attribute name differs", uaAttrib.getName(),
            createAttrib.getName());
        assertEquals("Attribute value differs", uaAttrib.getValue(),
            createAttrib.getValue());

        // retrieve
        Attributes attributes = uahc.retrieveAttributes(objId);
        assertTrue("Wrong number of attributes", attributes.size() == 1);
        Attribute p = attributes.iterator().next();
        assertEquals("Attribute name differs", uaAttrib.getName(), p.getName());
        assertEquals("Attribute value differs", uaAttrib.getValue(),
            p.getValue());
    }

    /**
     * Test to delete an user account attribute.
     * 
     * @throws Exception
     *             If behavior is not as expected.
     */
    @Test
    public void testDeleteAttribute01() throws Exception {
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

        // create Attribute
        Attribute uaAttrib = new Attribute("AttributeName", "AttributeValue");

        Attribute createAttrib = uahc.createAttribute(objId, uaAttrib);
        assertEquals("Attribute name differs", uaAttrib.getName(),
            createAttrib.getName());
        assertEquals("Attribute value differs", uaAttrib.getValue(),
            createAttrib.getValue());

        // delete Attribute
        uaAttrib = new Attribute("AttributeName", "AttributeValue2");
        uaAttrib.setObjid(createAttrib.getObjid());
        uaAttrib
            .setLastModificationDate(createAttrib.getLastModificationDate());

        uahc.deleteAttribute(objId, uaAttrib);

        // retrieve
        try {
            uahc.retrieveAttribute(objId, uaAttrib.getObjid());
            fail("Prefence not deleted.");
        }
        catch (UserAttributeNotFoundException e) {
            return;
        }
    }

}
