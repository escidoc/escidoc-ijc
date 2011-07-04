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
package de.escidoc.core.test.client.integrationTests.classMapping.om.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ItemHandlerClient;
import de.escidoc.core.client.interfaces.ItemHandlerClientInterface;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.properties.ContentModelSpecific;
import de.escidoc.core.resources.common.reference.ContentModelRef;
import de.escidoc.core.resources.common.reference.ContextRef;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.om.item.ItemProperties;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.util.Asserts;

/**
 * Test update Item.
 * 
 * @author SWA
 * 
 */
public class ItemUpdateTest {

    private Authentication auth;

    private ItemHandlerClientInterface ihc;

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.getDefaultInfrastructureURL(),
                EscidocClientTestBase.SYSTEM_ADMIN_USER, EscidocClientTestBase.SYSTEM_ADMIN_PASSWORD);
        ihc = new ItemHandlerClient(auth.getServiceAddress());
        ihc.setHandle(auth.getHandle());
    }

    @After
    public void post() throws Exception {
        if (auth != null)
            auth.logout();
    }

    /**
     * Test update Item by adding one more MetadataRecord.
     * 
     * @throws Exception
     *             Thrown if updating failed.
     */
    @Test
    public void testUpdateItem01() throws Exception {

        final Item item = createItem();

        final MetadataRecord mdRecord2 = new MetadataRecord("md-record2");
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document doc = builder.newDocument();
        final Element element = doc.createElementNS(null, "myMdRecord");
        element.appendChild(doc.createTextNode("2222222222"));

        mdRecord2.setContent(element);

        item.getMetadataRecords().add(mdRecord2);
        final Item updatedItem = ihc.update(item);

        // compare the new created Item with the Item from the request
        final String objId = updatedItem.getObjid();
        assertNotNull("Object id is null", objId);
        assertEquals(item.getProperties().getContext().getObjid(), updatedItem.getProperties().getContext().getObjid());
        assertEquals(item.getProperties().getContentModel().getObjid(), updatedItem
            .getProperties().getContentModel().getObjid());

        // we operate with the same user, so the modifier has to be equal to the
        // creator.
        assertEquals(updatedItem.getProperties().getCreatedBy().getObjid(), updatedItem
            .getProperties().getVersion().getModifiedBy().getObjid());

        Asserts.assertMdRecords(item.getMetadataRecords(), updatedItem.getMetadataRecords());

    }

    /**
     * Test if values of requested Item compares to the values of the response.
     * 
     * @throws Exception
     *             Thrown if creation failed or non-volatile Item values differ.
     */
    private Item createItem() throws Exception {
        final Item item = new Item();
        final ItemProperties properties = new ItemProperties();
        properties.setContext(new ContextRef(EscidocClientTestBase.getStaticContextId()));
        properties.setContentModel(new ContentModelRef(EscidocClientTestBase.getStaticContentModelId()));
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document doc = builder.newDocument();
        final Element contentModelSpecific = doc.createElementNS(null, "cms");
        final Element element1 = doc.createElement("some-other-stuff1");
        element1.appendChild(doc.createTextNode("33333333333333333333"));

        final List<Element> cmsContent = new LinkedList<Element>();
        cmsContent.add(contentModelSpecific);
        cmsContent.add(element1);
        final ContentModelSpecific cms = new ContentModelSpecific();

        cms.setContent(cmsContent);

        properties.setContentModelSpecific(cms);
        item.setProperties(properties);

        final MetadataRecords mdRecords = new MetadataRecords();
        final MetadataRecord mdRecord = new MetadataRecord("escidoc");
        final Element element = doc.createElementNS(null, "myMdRecord");

        mdRecord.setContent(element);

        mdRecords.add(mdRecord);
        item.setMetadataRecords(mdRecords);

        return ihc.create(item);
    }
}