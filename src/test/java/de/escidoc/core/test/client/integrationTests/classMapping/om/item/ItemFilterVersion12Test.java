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
import static org.junit.Assert.assertTrue;
import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.joda.time.DateTimeZone;
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
import de.escidoc.core.resources.common.reference.ContentModelRef;
import de.escidoc.core.resources.common.reference.ContextRef;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.om.item.ItemProperties;
import de.escidoc.core.resources.sb.explain.Explain;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.search.SearchResult;
import de.escidoc.core.resources.sb.search.SearchResultRecord;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test the CQL Item Filter.
 * 
 * @author SWA
 * 
 */
public class ItemFilterVersion12Test {

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
     * Test explain for items (filter for version 1.2).
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testExplain() throws Exception {
        ExplainResponse response = ihc.retrieveItems(new ExplainRequestType());
        Explain explain = response.getRecord().getRecordData();

        assertEquals("Wrong version number", "1.1", response.getVersion());
        assertNotNull("No index definitions found", explain.getIndexInfo());
        assertNotNull("No index definitions found", explain.getIndexInfo().getIndexes());
        assertTrue("No index definitions found", explain.getIndexInfo().getIndexes().size() > 0);
    }

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
        properties.setContext(new ContextRef(EscidocClientTestBase.getStaticContextId()));
        properties.setContentModel(new ContentModelRef(EscidocClientTestBase.getStaticContentModelId()));
        // properties.setContentModelSpecific(getContentModelSpecific());
        item.setProperties(properties);

        // Md-Record
        MetadataRecord mdRecord = new MetadataRecord("escidoc");

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

        SearchRetrieveRequestType srwFilter = new SearchRetrieveRequestType();
        srwFilter.setQuery("\"/properties/creation-date\"=\""
            + createdItem.getLastModificationDate().withZone(DateTimeZone.UTC).toString() + "\"");

        SearchRetrieveResponse response = ihc.retrieveItems(srwFilter);

        assertEquals("Wrong version number", "1.1", response.getVersion());
        assertTrue("Wrong number of matching records", response.getNumberOfMatchingRecords() >= 1);
        assertTrue("Wrong number of resulting records", response.getNumberOfResultingRecords() >= 1);
        assertEquals("Wrong record position", 1, response.getRecords().iterator().next().getRecordPosition().intValue());

        Collection<String> itemIds = new ArrayList<String>(response.getNumberOfResultingRecords());
        for (SearchResultRecord record : response.getRecords()) {
            SearchResult result = record.getRecordData();

            if (result.getContent() instanceof Item) {

                Item data = (Item) result.getContent();
                if (data != null)
                    itemIds.add(data.getObjid());
            }
        }

        assertTrue("Created Item missing in list", itemIds.contains(createdItem.getObjid()));
    }
}
