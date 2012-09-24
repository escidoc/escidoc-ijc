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
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.rest.RestItemHandlerClient;
import de.escidoc.core.common.XmlUtility;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.util.Template;

/**
 * Test create Item.
 * 
 * @author SWA
 * 
 */
public class ItemTestRest {

    private Authentication auth;

    private RestItemHandlerClient cc;

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.getDefaultInfrastructureURL(),
                EscidocClientTestBase.SYSTEM_ADMIN_USER, EscidocClientTestBase.SYSTEM_ADMIN_PASSWORD);
        cc = new RestItemHandlerClient(auth.getServiceAddress());
        cc.setHandle(auth.getHandle());
    }

    @After
    public void post() throws Exception {
        auth.logout();
    }

    /**
     * Test if retrieve Item via REST is successful.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testRetrieveItem01() throws Exception {
        cc.retrieve(EscidocClientTestBase.getStaticItemId());
    }

    /**
     * Test creating an Item by retrieving the example set Item.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateItem01() throws Exception {
        // retrieve a valid Item
        final String item = cc.retrieve(EscidocClientTestBase.getStaticItemId());

        // create a new Item (on basis of the valid)
        final String createdItemXml = cc.create(item);

        // asserts
        final Document itemTemplateDoc = XmlUtility.getDocument(item);
        final Document itemCreatedDoc = XmlUtility.getDocument(createdItemXml);

        // /item/@title == dc:title
        assertEquals("xlink:title not updated in root element", XPathAPI.selectSingleNode(itemTemplateDoc,
            "/item/md-records/md-record[@name='escidoc']/dc-md/title").getTextContent(), XPathAPI.selectSingleNode(
            itemCreatedDoc, "/item/@title").getTextContent());

        // dc:title == dc:title
        assertEquals("xlink:title not updated in root element", XPathAPI.selectSingleNode(itemTemplateDoc,
            "/item/md-records/md-record[@name='escidoc']/dc-md/title").getTextContent(), XPathAPI.selectSingleNode(
            itemCreatedDoc, "/item/md-records/md-record[@name='escidoc']/dc-md/title").getTextContent());

        // description
        assertEquals("xlink:title not updated in root element", XPathAPI.selectSingleNode(itemTemplateDoc,
            "/item/md-records/md-record[@name='escidoc']/dc-md/description").getTextContent(), XPathAPI
            .selectSingleNode(itemCreatedDoc, "/item/md-records/md-record[@name='escidoc']/dc-md/description")
            .getTextContent());
    }

    /**
     * Test creating an Item from template file.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    @Ignore("TODO: Replace fixed IDs of context and content-model in XML")
    public void testCreateItem02() throws Exception {
        final String itemXml = EscidocClientTestBase.getXmlFileAsString(Template.load("/rest/om/item/0.10/item01.xml"));

        // create a new Item (on basis of the valid)
        final String createdItemXml = cc.create(itemXml);

        // asserts
        final Document itemTemplateDoc = XmlUtility.getDocument(itemXml);
        final Document itemCreatedDoc = XmlUtility.getDocument(createdItemXml);

        // /item/@title == dc:title
        assertEquals("xlink:title not updated in root element", XPathAPI.selectSingleNode(itemTemplateDoc,
            "/item/md-records/md-record[@name='escidoc']/metadata/title").getTextContent(), XPathAPI.selectSingleNode(
            itemCreatedDoc, "/item/@title").getTextContent());

        // dc:title == dc:title
        assertEquals("xlink:title not updated in root element", XPathAPI.selectSingleNode(itemTemplateDoc,
            "/item/md-records/md-record[@name='escidoc']/metadata/title").getTextContent(), XPathAPI.selectSingleNode(
            itemCreatedDoc, "/item/md-records/md-record[@name='escidoc']/metadata/title").getTextContent());

        // description
        assertEquals("xlink:title not updated in root element", XPathAPI.selectSingleNode(itemTemplateDoc,
            "/item/md-records/md-record[@name='escidoc']/metadata/description").getTextContent(), XPathAPI
            .selectSingleNode(itemCreatedDoc, "/item/md-records/md-record[@name='escidoc']/metadata/description")
            .getTextContent());

    }

    /**
     * Test updating an Item.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    @Ignore("TODO: Replace fixed IDs of context and content-model in XML")
    public void testUpdateItem01() throws Exception {
        final String itemXml = EscidocClientTestBase.getXmlFileAsString(Template.load("/rest/om/item/0.10/item01.xml"));

        // create a new Item (on basis of the valid)
        final String createdItemXml = cc.create(itemXml);

        final Document itemDoc = XmlUtility.getDocument(createdItemXml);

        final String hrefPath = XPathAPI.selectSingleNode(itemDoc, "/item/@href").getTextContent();
        final String objid = hrefPath.substring(hrefPath.lastIndexOf("/") + 1);

        // assumtion, there is only one ds:title element
        final Node dcTitle = itemDoc.getElementsByTagName("dc:title").item(0);
        final String titleNew = "New Item Description " + System.nanoTime();
        dcTitle.setTextContent(titleNew);

        // updateItemXml
        final String updateItemXml = XmlUtility.xmlToString(itemDoc);
        final String updatedItemXml = cc.update(objid, updateItemXml);

        // asserts
        final Document itemUpdatedDoc = XmlUtility.getDocument(updatedItemXml);

        assertEquals("xlink:title not updated in root element", titleNew, XPathAPI.selectSingleNode(itemUpdatedDoc,
            "/item/@title").getTextContent());

        assertEquals("dc:title not updated in md-record", titleNew, itemUpdatedDoc
            .getElementsByTagName("dc:title").item(0).getTextContent());
    }

    /**
     * TODO: This is NOT a REST test.
     * 
     * @Test public void shouldReturnAllReleasedItems() throws
     *       EscidocClientException { final Authentication auth = new
     *       Authentication(EscidocClientTestBase.getDefaultInfrastructureURL(),
     *       Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);
     * 
     *       final ItemHandlerClientInterface cc = new ItemHandlerClient();
     *       cc.setTransport(TransportProtocol.REST);
     *       cc.setServiceAddress(EscidocClientTestBase
     *       .getDefaultInfrastructureURL()); cc.setHandle(auth.getHandle());
     * 
     *       final SearchRetrieveRequestType filter = new
     *       SearchRetrieveRequestType(); filter.setQuery("\"\""); final
     *       Collection<Item> retrieveItems = cc.retrieveItemsAsList(filter);
     * 
     *       assertNotNull("retrieveItems should not be null.", retrieveItems);
     *       assertTrue("retrieveItems should not be empty.",
     *       !retrieveItems.isEmpty()); }
     */
}
