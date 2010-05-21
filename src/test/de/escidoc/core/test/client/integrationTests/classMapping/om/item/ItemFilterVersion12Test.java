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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;
import de.escidoc.core.resources.sb.srw.SearchRetrieveResponseType;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.axis.types.NonNegativeInteger;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ItemHandlerClient;
import de.escidoc.core.client.UserAccountHandlerClient;
import de.escidoc.core.resources.ResourceRef;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.om.item.ItemList;
import de.escidoc.core.resources.om.item.ItemProperties;
import de.escidoc.core.resources.sb.search.SearchResultRecord;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test the CQL Item Filter.
 * 
 * @author SWA
 * 
 */
public class ItemFilterVersion12Test {

    public static final String FILTER_PARAMETER_QUERY = "query";

    /**
     * Test retrieving Items through filter request (filter for version 1.2).
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testRetrieveItems01() throws Exception {

        // create an Item
        Item item = new Item();

        // Properties
        ItemProperties properties = new ItemProperties();
        properties.setContext(new ResourceRef(Constants.EXAMPLE_CONTEXT_ID));
        properties.setContentModel(new ResourceRef(
            Constants.EXAMPLE_CONTENT_MODEL_ID));
        // properties.setContentModelSpecific(getContentModelSpecific());
        item.setProperties(properties);

        // Md-Record
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element element = doc.createElementNS(null, "myMdRecord");
        mdRecord.setContent(element);

        MetadataRecords mdRecords = new MetadataRecords();
        mdRecords.add(mdRecord);
        item.setMetadataRecords(mdRecords);

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ItemHandlerClient ic = new ItemHandlerClient();
        ic.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        ic.setHandle(auth.getHandle());

        Item createdItem = ic.create(item);

        // now check if at least this Item is in the list

        UserAccountHandlerClient uac = new UserAccountHandlerClient();
        uac.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        uac.setHandle(auth.getHandle());
        UserAccount me = uac.retrieveCurrentUser();

        SearchRetrieveRequestType srwFilter = new SearchRetrieveRequestType();
        srwFilter.setQuery("\"/properties/created-by/id\"=" + me.getObjid());
        srwFilter.setMaximumRecords(new NonNegativeInteger("1"));

        SearchRetrieveResponseType itemList = ic.retrieveItems(srwFilter);

        assertEquals("Wrong version number", "1.1", itemList.getVersion());
        assertTrue("Wrong number of records",
            itemList.getNumberOfRecords() >= 1);
        assertEquals("Wrong record position", 1, itemList
            .getRecords().iterator().next().getRecordPosition());

        // assertTrue("Wrong number of elements in list", itemList.
        // .getItems().size() > 0);
        //
        // List<String> idList = new Vector<String>();
        //
        // Iterator<Item> it = itemList.getItems().iterator();
        // while (it.hasNext()) {
        // Item n = it.next();
        // idList.add(n.getObjid());
        // }
        //
        // assertTrue("Created Item missing in list",
        // idList.contains(createdItem
        // .getObjid()));

    }

}