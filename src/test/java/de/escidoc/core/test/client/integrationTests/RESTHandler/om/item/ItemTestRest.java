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
package de.escidoc.core.test.client.integrationTests.RESTHandler.om.item;

import static org.junit.Assert.assertEquals;

import org.apache.xpath.XPathAPI;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.rest.RestItemHandlerClient;
import de.escidoc.core.common.XmlUtility;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.util.Template;

/**
 * Test create Item.
 * 
 * @author SWA
 * 
 */
public class ItemTestRest {

    /**
     * Test if retrieve Item via REST is successful.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testRetrieveItem01() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        RestItemHandlerClient cc = new RestItemHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());

        cc.retrieve(Constants.EXAMPLE_ITEM_ID);
    }

    /**
     * Test creating an Item by retrieving the example set Item.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateItem01() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        RestItemHandlerClient cc = new RestItemHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());

        // retrieve a valid Item
        String item = cc.retrieve(Constants.EXAMPLE_ITEM_ID);

        // create a new Item (on basis of the valid)
        String createdItemXml = cc.create(item);

        // asserts
        Document itemTemplateDoc = XmlUtility.getDocument(item);
        Document itemCreatedDoc = XmlUtility.getDocument(createdItemXml);

        // /item/@title == dc:title
        assertEquals(
            "xlink:title not updated in root element",
            XPathAPI
                .selectSingleNode(itemTemplateDoc,
                    "/item/md-records/md-record[@name='escidoc']/dc-md/title")
                .getTextContent(),
            XPathAPI
                .selectSingleNode(itemCreatedDoc, "/item/@title")
                .getTextContent());

        // dc:title == dc:title
        assertEquals(
            "xlink:title not updated in root element",
            XPathAPI
                .selectSingleNode(itemTemplateDoc,
                    "/item/md-records/md-record[@name='escidoc']/dc-md/title")
                .getTextContent(),
            XPathAPI
                .selectSingleNode(itemCreatedDoc,
                    "/item/md-records/md-record[@name='escidoc']/dc-md/title")
                .getTextContent());

        // description
        assertEquals(
            "xlink:title not updated in root element",
            XPathAPI
                .selectSingleNode(itemTemplateDoc,
                    "/item/md-records/md-record[@name='escidoc']/dc-md/description")
                .getTextContent(),
            XPathAPI
                .selectSingleNode(itemCreatedDoc,
                    "/item/md-records/md-record[@name='escidoc']/dc-md/description")
                .getTextContent());
    }

    /**
     * Test creating an Item from template file.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateItem02() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        RestItemHandlerClient cc = new RestItemHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());

        String itemXml =
            EscidocClientTestBase.getXmlFileAsString(Template
                .load("/rest/om/item/0.9/item01.xml"));

        // create a new Item (on basis of the valid)
        String createdItemXml = cc.create(itemXml);

        // asserts
        Document itemTemplateDoc = XmlUtility.getDocument(itemXml);
        Document itemCreatedDoc = XmlUtility.getDocument(createdItemXml);

        // /item/@title == dc:title
        assertEquals(
            "xlink:title not updated in root element",
            XPathAPI
                .selectSingleNode(itemTemplateDoc,
                    "/item/md-records/md-record[@name='escidoc']/metadata/title")
                .getTextContent(),
            XPathAPI
                .selectSingleNode(itemCreatedDoc, "/item/@title")
                .getTextContent());

        // dc:title == dc:title
        assertEquals(
            "xlink:title not updated in root element",
            XPathAPI
                .selectSingleNode(itemTemplateDoc,
                    "/item/md-records/md-record[@name='escidoc']/metadata/title")
                .getTextContent(),
            XPathAPI
                .selectSingleNode(itemCreatedDoc,
                    "/item/md-records/md-record[@name='escidoc']/metadata/title")
                .getTextContent());

        // description
        assertEquals(
            "xlink:title not updated in root element",
            XPathAPI
                .selectSingleNode(itemTemplateDoc,
                    "/item/md-records/md-record[@name='escidoc']/metadata/description")
                .getTextContent(),
            XPathAPI
                .selectSingleNode(itemCreatedDoc,
                    "/item/md-records/md-record[@name='escidoc']/metadata/description")
                .getTextContent());

    }

    /**
     * Test updating an Item.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testUpdateItem01() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        RestItemHandlerClient cc = new RestItemHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());

        String itemXml =
            EscidocClientTestBase.getXmlFileAsString(Template
                .load("/rest/om/item/0.9/item01.xml"));

        // create a new Item (on basis of the valid)
        String createdItemXml = cc.create(itemXml);

        Document itemDoc = XmlUtility.getDocument(createdItemXml);

        String hrefPath =
            XPathAPI.selectSingleNode(itemDoc, "/item/@href").getTextContent();
        String objid = hrefPath.substring(hrefPath.lastIndexOf("/") + 1);

        // assumtion, there is only one ds:title element
        Node dcTitle = itemDoc.getElementsByTagName("dc:title").item(0);
        String titleNew = "New Item Description " + System.nanoTime();
        dcTitle.setTextContent(titleNew);

        // updateItemXml
        String updateItemXml = XmlUtility.xmlToString(itemDoc);
        String updatedItemXml = cc.update(objid, updateItemXml);

        // asserts
        Document itemUpdatedDoc = XmlUtility.getDocument(updatedItemXml);

        assertEquals("xlink:title not updated in root element", titleNew,
            XPathAPI
                .selectSingleNode(itemUpdatedDoc, "/item/@title")
                .getTextContent());

        assertEquals("dc:title not updated in md-record", titleNew,
            itemUpdatedDoc
                .getElementsByTagName("dc:title").item(0).getTextContent());

    }

}
