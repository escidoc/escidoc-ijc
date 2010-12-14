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
package de.escidoc.core.test.client.integrationTests.classMapping.cmm;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ContentModelHandlerClient;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.UserAccountHandlerClient;
import de.escidoc.core.client.exceptions.application.invalid.XmlSchemaValidationException;
import de.escidoc.core.client.interfaces.ContentModelHandlerClientInterface;
import de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.cmm.ContentModel;
import de.escidoc.core.resources.cmm.MetadataRecordDefinition;
import de.escidoc.core.resources.cmm.MetadataRecordDefinitions;
import de.escidoc.core.resources.cmm.ResourceDefinition;
import de.escidoc.core.resources.cmm.ResourceDefinitions;
import de.escidoc.core.test.client.AbstractParameterizedTestBase;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test create Content Model.
 * 
 * @author SWA
 * 
 */
public class ContentModelCreateTest extends AbstractParameterizedTestBase {

    private Authentication auth;

    private ContentModelHandlerClientInterface cc;

    public ContentModelCreateTest(final TransportProtocol transport) {
        super(transport);
    }

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);
        cc = new ContentModelHandlerClient(auth.getServiceAddress());
        cc.setHandle(auth.getHandle());
        cc.setTransport(transport);
    }

    @After
    public void post() throws Exception {
        if (auth != null)
            auth.logout();
    }

    /**
     * Test if the right exception is thrown if calling create with null.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createContentModelNullPointerCheck() throws Exception {
        cc.create(null);
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Content Model.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = XmlSchemaValidationException.class)
    public void createContentModelFail01() throws Exception {
        ContentModel cmm = new ContentModel();
        cc.create(cmm);
    }

    /**
     * Test creation of a minimal Content Model.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void createContentModel01() throws Exception {
        ContentModel cmm = new ContentModel();
        cmm.getProperties().setName("Name-" + System.nanoTime());
        cmm.getProperties().setDescription("Description-" + System.nanoTime());

        ContentModel cmmCreated = cc.create(cmm);

        // asserts
        UserAccountHandlerClientInterface uac =
            new UserAccountHandlerClient(auth.getServiceAddress());
        uac.setTransport(transport);
        uac.setHandle(auth.getHandle());
        UserAccount me = uac.retrieveCurrentUser();

        assertEquals("Wrong name", cmm.getProperties().getName(), cmmCreated
            .getProperties().getName());
        assertEquals("Wrong description", cmm.getProperties().getDescription(),
            cmmCreated.getProperties().getDescription());

        assertEquals("Wrong number of metadata record definition schema", null,
            cmm.getMetadataRecordDefinitions());

        assertEquals("Wrong creator", me.getObjid(), cmmCreated
            .getProperties().getCreatedBy().getObjid());

    }

    /**
     * Test creation of a minimal Content Model.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void createContentModel02() throws Exception {
        ContentModel cmm = new ContentModel();
        cmm.getProperties().setName("Name-" + System.nanoTime());
        cmm.getProperties().setDescription("Description-" + System.nanoTime());

        MetadataRecordDefinition mdRecordDef = new MetadataRecordDefinition();
        mdRecordDef.setName("Name-" + System.nanoTime());
        mdRecordDef
            .setSchema(new URI(
                "http://localhost:8080/xsd/rest/content-model/0.1/content-model.xsd"));
        MetadataRecordDefinitions mdrds = new MetadataRecordDefinitions();
        mdrds.add(mdRecordDef);
        cmm.setMetadataRecordDefinitions(mdrds);

        ContentModel cmmCreated = cc.create(cmm);

        // asserts
        UserAccountHandlerClient uac =
            new UserAccountHandlerClient(auth.getServiceAddress());
        uac.setTransport(transport);
        uac.setHandle(auth.getHandle());
        UserAccount me = uac.retrieveCurrentUser();

        assertEquals("Wrong name", cmm.getProperties().getName(), cmmCreated
            .getProperties().getName());
        assertEquals("Wrong description", cmm.getProperties().getDescription(),
            cmmCreated.getProperties().getDescription());

        assertEquals("Wrong number of metadata record definition schema", 1,
            cmmCreated.getMetadataRecordDefinitions().size());
        assertEquals("Wrong metadata record definition name", cmm
            .getMetadataRecordDefinitions().iterator().next().getName(),
            cmmCreated
                .getMetadataRecordDefinitions().iterator().next().getName());
        assertEquals("Wrong metadata record definition schema",
            "/cmm/content-model/"
                + cmmCreated.getObjid()
                + "/md-record-definitions/md-record-definition/"
                + cmm
                    .getMetadataRecordDefinitions().iterator().next().getName()
                + "/schema/content", cmmCreated
                .getMetadataRecordDefinitions().iterator().next().getSchema()
                .toString());

        assertEquals("Wrong creator", me.getObjid(), cmmCreated
            .getProperties().getCreatedBy().getObjid());
    }

    /**
     * Test creation of a Content Model with multiple metadata record
     * definitions and resource definitions.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void createContentModel03() throws Exception {
        ContentModel cmm = new ContentModel();
        cmm.getProperties().setName("Name-" + System.nanoTime());
        cmm.getProperties().setDescription("Description-" + System.nanoTime());

        // metadata record definitions
        MetadataRecordDefinition mdRecordDef = new MetadataRecordDefinition();
        mdRecordDef.setName("Name-" + System.nanoTime());
        mdRecordDef
            .setSchema(new URI(
                "http://localhost:8080/xsd/rest/content-model/0.1/content-model.xsd"));
        MetadataRecordDefinitions mdrds = new MetadataRecordDefinitions();
        mdrds.add(mdRecordDef);
        cmm.setMetadataRecordDefinitions(mdrds);

        MetadataRecordDefinition mdRecordDef2 = new MetadataRecordDefinition();
        mdRecordDef2.setName("Name-" + System.nanoTime());
        mdRecordDef2
            .setSchema(new URI(
                "http://localhost:8080/xsd/rest/content-model/0.1/content-model.xsd"));
        cmm.getMetadataRecordDefinitions().add(mdRecordDef2);

        // resource definitions
        ResourceDefinition rd1 = new ResourceDefinition();
        rd1.setName("transX" + System.nanoTime());
        rd1.setMetadataRecordName("escidoc");
        rd1.setXslt(new URI(
            "http://localhost:8080/xsl/mapping-unknown2dc-onlyMD.xsl"));
        ResourceDefinitions rds = new ResourceDefinitions();
        rds.add(rd1);
        cmm.setResourceDefinitions(rds);

        ResourceDefinition rd2 = new ResourceDefinition();
        rd2.setName("transX" + System.nanoTime());
        rd2.setMetadataRecordName("blafasel");
        rd2.setXslt(new URI("http://localhost:8080/xsl/copy.xsl"));
        cmm.getResourceDefinitions().add(rd2);

        ContentModel cmmCreated = cc.create(cmm);

        // asserts
        UserAccountHandlerClient uac =
            new UserAccountHandlerClient(auth.getServiceAddress());
        uac.setTransport(transport);
        uac.setHandle(auth.getHandle());

        UserAccount me = uac.retrieveCurrentUser();

        assertEquals("Wrong name", cmm.getProperties().getName(), cmmCreated
            .getProperties().getName());
        assertEquals("Wrong description", cmm.getProperties().getDescription(),
            cmmCreated.getProperties().getDescription());

        assertEquals("Wrong number of metadata record definition schema", 2,
            cmmCreated.getMetadataRecordDefinitions().size());

        // assert metadata record definitions
        Iterator<MetadataRecordDefinition> it =
            cmm.getMetadataRecordDefinitions().iterator();

        while (it.hasNext()) {
            MetadataRecordDefinition mrd = it.next();
            MetadataRecordDefinition mrdCreated =
                cmmCreated.getMetadataRecordDefinitions().get(mrd.getName());

            assertEquals("Wrong metadata record definition name",
                mrd.getName(), mrdCreated.getName());

            assertEquals(
                "Wrong metadata record definition schema",
                "/cmm/content-model/" + cmmCreated.getObjid()
                    + "/md-record-definitions/md-record-definition/"
                    + mrd.getName() + "/schema/content", mrdCreated
                    .getSchema().toString());
        }

        // assert resource definitions
        Iterator<ResourceDefinition> it2 =
            cmm.getResourceDefinitions().iterator();

        while (it2.hasNext()) {
            ResourceDefinition rd = it2.next();
            ResourceDefinition rdCreated =
                cmmCreated.getResourceDefinitions().get(rd.getName());

            assertEquals("Wrong metadata record definition name", rd.getName(),
                rdCreated.getName());

            assertEquals("Wrong metadata record name",
                rd.getMetadataRecordName(), rdCreated.getMetadataRecordName());

            assertEquals(
                "Wrong metadata record definition schema",
                "/cmm/content-model/" + cmmCreated.getObjid()
                    + "/resource-definitions/resource-definition/"
                    + rd.getName() + "/xslt/content", rdCreated
                    .getXslt().toString());
        }

        assertEquals("Wrong creator", me.getObjid(), cmmCreated
            .getProperties().getCreatedBy().getObjid());

    }

    /**
     * Test update of a Content Model.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void updateContentModel01() throws Exception {
        ContentModel cmm = new ContentModel();
        cmm.getProperties().setName("Name-" + System.nanoTime());
        cmm.getProperties().setDescription("Description-" + System.nanoTime());

        ResourceDefinition rd1 = new ResourceDefinition();
        rd1.setName("transX" + System.nanoTime());
        rd1.setMetadataRecordName("escidoc");
        rd1.setXslt(new URI(
            "http://localhost:8080/xsl/mapping-unknown2dc-onlyMD.xsl"));
        ResourceDefinitions rds = new ResourceDefinitions();
        rds.add(rd1);
        cmm.setResourceDefinitions(rds);

        ContentModel cmmCreated = cc.create(cmm);

        // change value
        cmmCreated
            .getResourceDefinitions().get(0)
            .setName(String.valueOf("UpdatedName" + System.nanoTime()));

        // update
        ContentModel cmmUpdated = cc.update(cmmCreated);

        // asserts
        UserAccountHandlerClient uac =
            new UserAccountHandlerClient(auth.getServiceAddress());
        uac.setTransport(transport);
        uac.setHandle(auth.getHandle());
        UserAccount me = uac.retrieveCurrentUser();

        assertEquals("Wrong name", cmm.getProperties().getName(), cmmCreated
            .getProperties().getName());

        assertEquals("Wrong description", cmm.getProperties().getDescription(),
            cmmCreated.getProperties().getDescription());

        assertEquals("Wrong number of metadata record definition", null,
            cmmCreated.getMetadataRecordDefinitions());

        assertEquals("Wrong number of resource definition", 1, cmmCreated
            .getResourceDefinitions().size());

        assertEquals("Wrong name", cmm.getProperties().getName(), cmmUpdated
            .getProperties().getName());

        assertEquals("Wrong description", cmm.getProperties().getDescription(),
            cmmUpdated.getProperties().getDescription());

        assertEquals("Wrong number of metadata record definition", null,
            cmmUpdated.getMetadataRecordDefinitions());

        assertEquals("Wrong number of resource definition", 1, cmmUpdated
            .getResourceDefinitions().size());

        assertEquals("Name of resource definition not updated", cmmCreated
            .getResourceDefinitions().get(0).getName(), cmmUpdated
            .getResourceDefinitions().get(0).getName());

        assertEquals("Wrong creator", me.getObjid(), cmmCreated
            .getProperties().getCreatedBy().getObjid());

    }

}
