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
package de.escidoc.core.test.client.integrationTests.classMapping.sb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.ScanRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.escidoc.core.client.ItemHandlerClient;
import de.escidoc.core.client.SearchHandlerClient;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.interfaces.ItemHandlerClientInterface;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.sb.Record;
import de.escidoc.core.resources.sb.Record.RecordPacking;
import de.escidoc.core.resources.sb.explain.ExplainData;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.scan.ScanResponse;
import de.escidoc.core.resources.sb.search.SearchResultRecord;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;
import de.escidoc.core.resources.sb.search.records.ItemRecord;
import de.escidoc.core.resources.sb.search.records.SearchResultRecordRecord;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

@SuppressWarnings({ "rawtypes", "unused" })
@RunWith(Parameterized.class)
public class SearchHandlerClientTest extends EscidocClientTestBase {

    private TransportProtocol protocol;

    private RecordPacking packing;

    private static final StringBuilder out = new StringBuilder();

    public SearchHandlerClientTest(TransportProtocol protocol,
        RecordPacking packing) {
        this.protocol = protocol;
        this.packing = packing;
    }

    @Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][] {
            { TransportProtocol.REST, RecordPacking.xml },
            { TransportProtocol.REST, RecordPacking.string },
            { TransportProtocol.SOAP, RecordPacking.xml },
            { TransportProtocol.SOAP, RecordPacking.string } });
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println(out.toString());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testSRWExplain() throws Exception {

        SearchHandlerClient c = new SearchHandlerClient();
        c.setTransport(protocol);

        ExplainRequestType request = new ExplainRequestType();
        request.setRecordPacking(packing.name());
        request.setVersion("1.1");

        ExplainResponse response = c.explain2(request, null);
        Record<ExplainData> record = response.getRecord();
        ExplainData data = record.getRecordData();

        out.append("\n=========================\n");
        out.append("testSRWExplain: ");
        out.append("Protocol: " + protocol.name() + ", RecordPacking: "
            + packing + "\n");
        data.toString(out);
        out.append("\n");

        Assert.assertNotNull(data);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testFilterExplain() throws Exception {

        ItemHandlerClient c = new ItemHandlerClient();
        c.setTransport(protocol);

        ExplainRequestType request = new ExplainRequestType();
        request.setVersion("1.1");
        request.setRecordPacking(packing.name());

        ExplainResponse response = c.retrieveItems(request);
        Record<ExplainData> record = response.getRecord();
        ExplainData data = record.getRecordData();

        out.append("\n=========================\n");
        out.append("testFilterExplain: ");
        out.append("Protocol: " + protocol.name() + ", RecordPacking: "
            + packing + "\n");
        data.toString(out);
        out.append("\n");

        Assert.assertNotNull(data);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testSRWSearch() throws Exception {

        SearchHandlerClient c = new SearchHandlerClient();
        c.setTransport(protocol);
        String query = "escidoc.objid=" + Constants.EXAMPLE_ITEM_ID;

        SearchRetrieveResponse response =
            c.search(URLEncoder.encode(query, "UTF-8"), null);

        out.append("\n=========================\n");
        out.append("testSRWSearch: query=" + query);
        out.append(" [Protocol: " + protocol.name() + ", RecordPacking: "
            + packing + "]\n");
        out.append("Results: " + response.getNumberOfResultingRecords() + "\n");

        for (Iterator<Record> it = response.getRecords().iterator(); it
            .hasNext();) {
            Record record = it.next();

            assertTrue(record instanceof SearchResultRecordRecord);

            if (record instanceof SearchResultRecordRecord) {
                SearchResultRecordRecord result =
                    (SearchResultRecordRecord) record;

                SearchResultRecord data = result.getRecordData();

                assertNotNull(data);
                assertNotNull(data.getContent());

                out.append(data.getContent().getResourceType().name() + ": ID["
                    + data.getContent().getObjid() + "], Href["
                    + data.getContent().getXLinkHref() + "], Score["
                    + data.getScore() + "]\n");
                assertNotNull(data.getContent().getObjid());
            }
        }
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testFilterSearch() throws Exception {

        ItemHandlerClientInterface c = new ItemHandlerClient();
        c.setTransport(protocol);
        String query = "\"/id\"=" + Constants.EXAMPLE_ITEM_ID;

        SearchRetrieveRequestType request = new SearchRetrieveRequestType();
        request.setQuery(query);
        request.setRecordPacking(packing.name());

        SearchRetrieveResponse response = c.retrieveItems(request);

        out.append("\n=========================\n");
        out.append("testFilterSearch [1]: query=" + query);
        out.append(" [Protocol: " + protocol.name() + ", RecordPacking: "
            + packing + "]\n");
        out.append("Results: " + response.getNumberOfResultingRecords() + "\n");

        for (Iterator it = response.getRecords().iterator(); it.hasNext();) {
            ItemRecord record = (ItemRecord) it.next();
            Item item = record.getRecordData();
            out.append("Item: ID[" + item.getObjid() + "], Href["
                + item.getXLinkHref() + "]\n");
        }

        Collection<Item> items = c.retrieveItemsAsList(request);

        out.append("\ntestFilterSearch [2]:\n");
        out.append("Results: " + items.size() + "\n");

        for (Item item : items) {
            out.append("Item: ID[" + item.getObjid() + "], Href["
                + item.getXLinkHref() + "]\n");
        }

        assertEquals("Binding of all items within records failed.",
            response.getNumberOfResultingRecords(), items.size());
    }

    /**
     * TODO
     * 
     * @throws Exception
     */
    // @Test
    public void testSearchScanREST() throws Exception {

        SearchHandlerClient c = new SearchHandlerClient();
        c.setTransport(TransportProtocol.REST);

        ScanRequestType request = new ScanRequestType();
        request.setVersion("1.1");
        request.setScanClause("escidoc.metadata=escidoc");

        ScanResponse response = c.scan(request, null);
    }
}
