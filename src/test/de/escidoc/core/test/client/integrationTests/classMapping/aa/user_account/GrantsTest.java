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

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ItemHandlerClient;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.UserAccountHandlerClient;
import de.escidoc.core.client.exceptions.application.invalid.InvalidXmlException;
import de.escidoc.core.client.exceptions.application.notfound.GrantNotFoundException;
import de.escidoc.core.client.interfaces.ItemHandlerClientInterface;
import de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface;
import de.escidoc.core.resources.ResourceRef;
import de.escidoc.core.resources.ResourceRef.RESOURCE_TYPE;
import de.escidoc.core.resources.aa.useraccount.Grant;
import de.escidoc.core.resources.aa.useraccount.GrantProperties;
import de.escidoc.core.resources.aa.useraccount.Grants;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.aa.useraccount.UserAccountProperties;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.common.properties.ContentModelSpecific;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.integrationTests.classMapping.om.ResourceUtility;

/**
 * Test User Account Grants with class mapping.
 * 
 * @author SWA
 * 
 */
@RunWith(Parameterized.class)
public class GrantsTest {

    private TransportProtocol transport;

    public GrantsTest(TransportProtocol transport) {
        this.transport = transport;
    }

    @SuppressWarnings("rawtypes")
    @Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][] { { TransportProtocol.SOAP },
            { TransportProtocol.REST } });
    }

    /**
     * Test to create and retrieve a grant.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testCreateGrant01() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        UserAccountHandlerClientInterface uahc = new UserAccountHandlerClient();

        uahc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        uahc.setTransport(transport);
        uahc.setHandle(auth.getHandle());

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

        // create Grant
        Grant grant = new Grant();
        GrantProperties gProp = new GrantProperties();
        ResourceRef ref =
            new ResourceRef("escidoc:role-system-administrator",
                RESOURCE_TYPE.Role);
        gProp.setRole(ref);

        grant.setGrantProperties(gProp);

        Grant createdGrant = uahc.createGrant(objId, grant);

        assertEquals("Missing role in grant",
            "escidoc:role-system-administrator", createdGrant
                .getGrantProperties().getRole().getObjid());

        Grants grants = uahc.retrieveCurrentGrants(objId);
        assertTrue(grants.getGrants().size() > 0);
        if (uahc.getTransport() == TransportProtocol.REST) {
            for (Grant g : grants.getGrants()) {
                String xLinkTitle = g.getXLinkTitle();
                assertTrue(xLinkTitle != null && !xLinkTitle.isEmpty());
            }
        }
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

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        // create Item
        ItemHandlerClientInterface ihc = new ItemHandlerClient();
        ihc.setTransport(transport);
        ihc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        ihc.setHandle(auth.getHandle());

        Item item = new Item();

        item.getProperties()
            .setContext(
                new ResourceRef(Constants.EXAMPLE_CONTEXT_ID,
                    RESOURCE_TYPE.Context));
        item.getProperties().setContentModel(
            new ResourceRef(Constants.EXAMPLE_CONTENT_MODEL_ID,
                RESOURCE_TYPE.ContentModel));

        // Content-model
        ContentModelSpecific cms = ResourceUtility.getContentModelSpecific();
        item.getProperties().setContentModelSpecific(cms);

        // Metadata Record(s)
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdrecord = ResourceUtility.getMdRecord("escidoc");
        mdRecords.add(mdrecord);
        item.setMetadataRecords(mdRecords);

        // create
        Item createdItem = ihc.create(item);

        UserAccountHandlerClientInterface uahc = new UserAccountHandlerClient();
        uahc.setTransport(transport);
        uahc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        uahc.setHandle(auth.getHandle());

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

        // create Grant
        String roleId = "escidoc:role-audience";
        Grant grant = new Grant();
        GrantProperties gProp = new GrantProperties();
        gProp.setRole(new ResourceRef(roleId, RESOURCE_TYPE.Role));
        gProp.setAssignedOn(new ResourceRef(createdItem.getObjid(),
            RESOURCE_TYPE.Item));
        grant.setGrantProperties(gProp);

        Grant createdGrant = uahc.createGrant(objId, grant);

        assertEquals("Missing Role in Grant", roleId, createdGrant
            .getGrantProperties().getRole().getObjid());
        assertEquals("Missing Assinged-On", createdItem.getObjid(),
            createdGrant.getGrantProperties().getAssignedOn().getObjid());

        Grants grants = uahc.retrieveCurrentGrants(objId);
        assertTrue("No Grants", grants.getGrants().size() > 0);
        assertEquals("Missing Grant", createdGrant.getObjid(), grants
            .getGrants().iterator().next().getObjid());
        assertEquals("Missing Assigned-On", createdItem.getObjid(), grants
            .getGrants().iterator().next().getGrantProperties().getAssignedOn()
            .getObjid());
    }

    /**
     * Test to create and retrieve a grant with wrong assigned-on reference.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test(expected = InvalidXmlException.class)
    public void testCreateGrant03() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        UserAccountHandlerClientInterface uahc = new UserAccountHandlerClient();
        uahc.setTransport(transport);
        uahc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        uahc.setHandle(auth.getHandle());

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

        // create Grant
        String roleId = "escidoc:role-audience";
        Grant grant = new Grant();
        GrantProperties gProp = new GrantProperties();
        gProp.setRole(new ResourceRef(roleId, RESOURCE_TYPE.Role));
        gProp.setAssignedOn(new ResourceRef("escidoc:NON-exists",
            RESOURCE_TYPE.UserAccount));
        grant.setGrantProperties(gProp);

        uahc.createGrant(objId, grant);
    }

    /**
     * Test to revoke a grant.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testRevokeGrant01() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        UserAccountHandlerClientInterface uahc = new UserAccountHandlerClient();
        uahc.setTransport(transport);
        uahc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        uahc.setHandle(auth.getHandle());

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

        // create Grant
        Grant grant = new Grant();
        GrantProperties gProp = new GrantProperties();
        gProp.setRole(new ResourceRef("escidoc:role-system-administrator",
            RESOURCE_TYPE.Role));
        grant.setGrantProperties(gProp);

        Grant createdGrant = uahc.createGrant(objId, grant);

        // delete just created Grant
        TaskParam tp = new TaskParam();
        tp.setLastModificationDate(createdGrant.getLastModificationDate());
        tp.setComment("Just test revoke of a Grant");
        uahc.revokeGrant(objId, createdGrant.getObjid(), tp);

        Grant revokedGrant = uahc.retrieveGrant(objId, createdGrant.getObjid());
        assertEquals("Revoked by differs", uahc
            .retrieveCurrentUser().getObjid(), revokedGrant
            .getGrantProperties().getRevokedBy().getObjid());
    }

    /**
     * Test to revoke a non-existing Grant.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testRevokeGrant02() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        UserAccountHandlerClientInterface uahc = new UserAccountHandlerClient();
        uahc.setTransport(transport);
        uahc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        uahc.setHandle(auth.getHandle());

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

        // create Grant
        Grant grant = new Grant();
        GrantProperties gProp = new GrantProperties();
        gProp.setRole(new ResourceRef("escidoc:role-system-administrator",
            RESOURCE_TYPE.Role));
        grant.setGrantProperties(gProp);

        Grant createdGrant = uahc.createGrant(objId, grant);

        // delete just created Grant
        TaskParam tp = new TaskParam();
        tp.setLastModificationDate(createdGrant.getLastModificationDate());
        tp.setComment("Just test revoke of a Grant");

        try {
            uahc.revokeGrant(objId, "escidoc:NON-exists", tp);
            fail("Missing GrantNotFoundException.");
        }
        catch (GrantNotFoundException e) {
            return;
        }

    }

}
