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
import static org.junit.Assert.assertTrue;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ItemHandlerClient;
import de.escidoc.core.client.interfaces.ItemHandlerClientInterface;
import de.escidoc.core.resources.common.Filter;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.common.reference.ContentModelRef;
import de.escidoc.core.resources.common.reference.ContextRef;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.om.item.ItemProperties;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test the Item Filter.
 * 
 * TODO delete file and include new versions of these filter requests in
 * ItemFilter12Test
 * 
 * @author SWA
 * 
 */
public class ItemFilterTest {

    private Authentication auth;

    private ItemHandlerClientInterface ihc;

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);
        ihc = new ItemHandlerClient(auth.getServiceAddress());
        ihc.setHandle(auth.getHandle());
    }

    @After
    public void post() throws Exception {
        if (auth != null)
            auth.logout();
    }

    /**
     * Test binding filter.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testFilterBinding() throws Exception {

        TaskParam tp = new TaskParam();
        tp.setLastModificationDate(new DateTime(System.currentTimeMillis()));
        tp.setComment("Lalalala!");
        tp.setPid("pid");

        Collection<Filter> filters = tp.getFilters();
        Collection<String> ids = new LinkedList<String>();
        ids.add("escidoc:7043");
        ids.add("escidoc:7044");
        ids.add(EscidocClientTestBase.getStaticItemId());
        filters.add(new Filter("http://purl.org/dc/elements/1.1/identifier",
            null, ids));
        filters.add(new Filter(
            "http://escidoc.de/core/01/properties/public-status", "pending",
            null));
        filters.add(new Filter(
            "http://escidoc.de/core/01/structural-relations/created-by",
            "escidoc:user42", null));
        tp.setFilters(filters);
    }

    /**
     * Test retrieving Items through filter request.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testRetrieveItems() throws Exception {
        SearchRetrieveRequestType request = new SearchRetrieveRequestType();
        request
            .setQuery("\"http://escidoc.de/core/01/structural-relations/created-by\"=non-existing-user");

        List<Item> itemList = ihc.retrieveItemsAsList(request);

        assertEquals("Wrong number of elements in list", 0, itemList.size());
    }

    /**
     * Test retrieving Items through filter request.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testRetrieveItems02() throws Exception {

        // create an Item
        Item item = new Item();

        // Properties
        ItemProperties properties = new ItemProperties();
        properties.setContext(new ContextRef(EscidocClientTestBase.getStaticContextId()));
        properties.setContentModel(new ContentModelRef(
            EscidocClientTestBase.getStaticContentModelId()));
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

        Item createdItem = ihc.create(item);

        // now check if at least this Item is in the list
        SearchRetrieveRequestType request = new SearchRetrieveRequestType();
        request.setQuery("\"/properties/creation-date\"="
            + createdItem
                .getProperties().getCreationDate().withZone(DateTimeZone.UTC)
                .toString());

        List<Item> itemList = ihc.retrieveItemsAsList(request);

        assertTrue("Wrong number of elements in list", itemList.size() > 0);

        List<String> idList = new Vector<String>();

        Iterator<Item> it = itemList.iterator();
        while (it.hasNext()) {
            Item n = it.next();
            idList.add(n.getObjid());
        }

        assertTrue("Created Item missing in list",
            idList.contains(createdItem.getObjid()));

    }
}