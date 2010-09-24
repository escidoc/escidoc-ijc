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

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ItemHandlerClient;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.interfaces.ItemHandlerClientInterface;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.properties.ContentModelSpecific;
import de.escidoc.core.resources.common.reference.ContentModelRef;
import de.escidoc.core.resources.common.reference.ContextRef;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.om.item.ItemProperties;
import de.escidoc.core.test.client.AbstractParameterizedTestBase;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.util.Asserts;

/**
 * Test update Item.
 * 
 * @author SWA
 * 
 */
public class ItemUpdateTest extends AbstractParameterizedTestBase {

    public ItemUpdateTest(TransportProtocol transport) {
        super(transport);
    }

    /**
     * Test update Item by adding one more MetadataRecord.
     * 
     * @throws Exception
     *             Thrown if updating failed.
     */
    @Test
    public void testUpdateItem01() throws Exception {

        Item item = createItem();

        ItemHandlerClientInterface cc = new ItemHandlerClient();
        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);
        cc.setHandle(auth.getHandle());
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setTransport(transport);

        MetadataRecord mdRecord2 = new MetadataRecord();
        mdRecord2.setName("md-record2");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element element = doc.createElementNS(null, "myMdRecord");
        element.setTextContent("2222222222");

        mdRecord2.setContent(element);

        item.getMetadataRecords().add(mdRecord2);
        Item updatedItem = cc.update(item);

        // compare the new created Item with the Item from the request
        String objId = updatedItem.getObjid();
        assertNotNull("Object id is null", objId);
        assertEquals(item.getProperties().getContext().getObjid(), updatedItem
            .getProperties().getContext().getObjid());
        assertEquals(item.getProperties().getContentModel().getObjid(),
            updatedItem.getProperties().getContentModel().getObjid());

        // we operate with the same user, so the modifier has to be equal to the
        // creator.
        assertEquals(updatedItem.getProperties().getCreatedBy().getObjid(),
            updatedItem.getProperties().getVersion().getModifiedBy().getObjid());

        Asserts.assertMdRecords(item.getMetadataRecords(),
            updatedItem.getMetadataRecords());

    }

    /**
     * Test if values of requested Item compares to the values of the response.
     * 
     * @throws Exception
     *             Thrown if creation failed or non-volatile Item values differ.
     */
    private Item createItem() throws Exception {

        ItemHandlerClient cc = new ItemHandlerClient();
        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);
        cc.setHandle(auth.getHandle());
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setTransport(transport);

        Item item = new Item();
        ItemProperties properties = new ItemProperties();
        properties.setContext(new ContextRef("escidoc:ex1"));
        properties.setContentModel(new ContentModelRef("escidoc:ex4"));
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element contentModelSpecific = doc.createElementNS(null, "cms");
        Element element1 = doc.createElement("some-other-stuff1");
        element1.setTextContent("33333333333333333333");

        List<Element> cmsContent = new LinkedList<Element>();
        cmsContent.add(contentModelSpecific);
        cmsContent.add(element1);
        ContentModelSpecific cms = new ContentModelSpecific();

        cms.setContent(cmsContent);

        properties.setContentModelSpecific(cms);
        item.setProperties(properties);

        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");
        Element element = doc.createElementNS(null, "myMdRecord");

        mdRecord.setContent(element);

        mdRecords.add(mdRecord);
        item.setMetadataRecords(mdRecords);

        return cc.create(item);

    }
}
