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

import java.io.ByteArrayInputStream;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.rest.RestItemHandlerClient;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.util.XmlUtil;

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

        // FIXME asserts
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

        File templItem = new File("./templates/rest/item/0.9/item01.xml");
        String itemXml = EscidocClientTestBase.getXmlFileAsString(templItem);

        // create a new Item (on basis of the valid)
        String createdItemXml = cc.create(itemXml);

        // FIXME asserts
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

        File templItem = new File("./templates/rest/item/0.9/item01.xml");
        String itemXml = EscidocClientTestBase.getXmlFileAsString(templItem);

        // create a new Item (on basis of the valid)
        String createdItemXml = cc.create(itemXml);

        DocumentBuilderFactory docBuilderFactory =
            DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

        Document itemDoc =
            docBuilder.parse(new ByteArrayInputStream(createdItemXml
                .getBytes("UTF-8")));
        itemDoc.getDocumentElement().normalize();

        Node rootNode = itemDoc.getFirstChild().getNextSibling();
        System.out.println(rootNode.getNodeName());
        Node href =
            rootNode.getAttributes().getNamedItemNS(
                "http://www.w3.org/1999/xlink", "href");
        System.out.println(href);
        String objid = ""; // href.substring(href.lastIndexOf("/"));
        Node dcTitle = itemDoc.getElementsByTagName("dc:title").item(0);

        String newDescription = "New Item Description " + System.nanoTime();
        dcTitle.setTextContent(newDescription);

        // updateItemXml
        String updateItemXml = XmlUtil.xmlToString(itemDoc);
        String updatedItemXml = cc.update(objid, updateItemXml);

        // FIXME asserts
    }


}
