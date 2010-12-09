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

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.transform.TransformerException;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.escidoc.core.client.ItemHandlerClient;
import de.escidoc.core.client.SearchHandlerClient;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.interfaces.ItemHandlerClientInterface;
import de.escidoc.core.client.interfaces.SearchHandlerClientInterface;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.common.reference.ContainerRef;
import de.escidoc.core.resources.common.reference.ItemRef;
import de.escidoc.core.resources.common.reference.Reference;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.sb.Record;
import de.escidoc.core.resources.sb.RecordMetaData;
import de.escidoc.core.resources.sb.Record.RecordPacking;
import de.escidoc.core.resources.sb.explain.ExplainData;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.scan.ScanResponse;
import de.escidoc.core.resources.sb.scan.Term;
import de.escidoc.core.resources.sb.search.SearchDescriptor;
import de.escidoc.core.resources.sb.search.SearchResultRecord;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;
import de.escidoc.core.resources.sb.search.records.ResourceRecord;
import de.escidoc.core.resources.sb.search.records.SearchResultRecordRecord;
import de.escidoc.core.resources.sb.search.records.resolver.RecordResolver;
import de.escidoc.core.resources.sb.search.records.resolver.TagEntry;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

@RunWith(Parameterized.class)
public class SearchHandlerClientTest {

    private static final Logger LOG = Logger
        .getLogger(SearchHandlerClientTest.class);

    private static final StringBuilder out = new StringBuilder();

    private final TransportProtocol transport;

    private final RecordPacking packing;

    private SearchHandlerClientInterface c;

    public SearchHandlerClientTest(final TransportProtocol transport,
        final RecordPacking packing) {
        this.transport = transport;
        this.packing = packing;
    }

    @Parameters
    public static Collection<?> data() {
        return Arrays.asList(new Object[][] {
            { TransportProtocol.REST, RecordPacking.xml },
            { TransportProtocol.REST, RecordPacking.string },
            { TransportProtocol.SOAP, RecordPacking.xml },
            { TransportProtocol.SOAP, RecordPacking.string } });
    }

    @Before
    public void init() throws Exception {
        // No authentication required for SB
        c = new SearchHandlerClient(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        c.setTransport(transport);
    }

    @After
    public void post() throws Exception {
        LOG.debug(out.toString());
        out.delete(0, out.length());
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testSRWExplain() throws Exception {

        ExplainRequestType request = new ExplainRequestType();
        request.setRecordPacking(packing.name());
        request.setVersion("1.1");

        ExplainResponse response = c.explain(request, null);
        Record<ExplainData> record = response.getRecord();
        ExplainData data = record.getRecordData();

        out.append("\n=========================\n");
        out.append("testSRWExplain: ");
        out.append("Protocol: ");
        out.append(transport.name());
        out.append(", RecordPacking: ");
        out.append(packing);
        out.append("\n");
        out.append(data.toString());
        out.append("\n");

        Assert.assertNotNull(data);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testFilterExplain() throws Exception {

        ItemHandlerClientInterface c = new ItemHandlerClient();
        c.setTransport(transport);

        ExplainRequestType request = new ExplainRequestType();
        request.setVersion("1.1");
        request.setRecordPacking(packing.name());

        ExplainResponse response = c.retrieveItems(request);
        Record<ExplainData> record = response.getRecord();
        ExplainData data = record.getRecordData();

        out.append("\n=========================\n");
        out.append("testFilterExplain: ");
        out.append("Protocol: ");
        out.append(transport.name());
        out.append(", RecordPacking: ");
        out.append(packing);
        out.append("\n");
        out.append(data.toString());
        out.append("\n");

        Assert.assertNotNull(data);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testSRWSearch() throws Exception {

        String query = "escidoc.objecttype=item";
        // "escidoc.objid=" + Constants.EXAMPLE_ITEM_ID;

        SearchRetrieveResponse response = c.search(query, null);

        out.append("\n=========================\n");
        out.append("testSRWSearch: query=");
        out.append(query);
        out.append(" [Protocol: ");
        out.append(transport.name());
        out.append(", RecordPacking: ");
        out.append(packing);
        out.append("]\n");
        out.append("Results: ");
        out.append(response.getNumberOfResultingRecords());
        out.append("\n");

        for (Record<?> record : response.getRecords()) {
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
     * Test SRW Search with null query.
     * 
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNullSearch() throws Exception {
        String query = null;
        SearchRetrieveResponse response = c.search(query, null);

        assertNotNull("Response should not be null.", response);
        assertTrue("", response.getNumberOfMatchingRecords() > 0);

        response = c.search(new SearchRetrieveRequestType(), null);

        assertNotNull("Response should not be null.", response);
        assertTrue("", response.getNumberOfMatchingRecords() > 0);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testFilterSearch() throws Exception {

        ItemHandlerClientInterface c = new ItemHandlerClient();
        c.setTransport(transport);

        String query = "\"/id\"=" + Constants.EXAMPLE_ITEM_ID;

        SearchRetrieveRequestType request = new SearchRetrieveRequestType();
        request.setQuery(query);
        request.setRecordPacking(packing.name());

        SearchRetrieveResponse response = c.retrieveItems(request);

        out.append("\n=========================\n");
        out.append("testFilterSearch [1]: query=");
        out.append(query);
        out.append(" [Protocol: ");
        out.append(transport.name());
        out.append(", RecordPacking: ");
        out.append(packing);
        out.append("]\n");
        out.append("Results: ");
        out.append(response.getNumberOfResultingRecords());
        out.append("\n");

        for (Record<?> record : response.getRecords()) {
            if (record instanceof ResourceRecord<?>
                && ((ResourceRecord<?>) record).getRecordDataType() == Item.class) {
                Item item = (Item) record.getRecordData();
                out.append("Item: ID[" + item.getObjid() + "], Href["
                    + item.getXLinkHref() + "]\n");
            }
        }

        Collection<Item> items = c.retrieveItemsAsList(request);

        out.append("\ntestFilterSearch [2]:\n");
        out.append("Results: ");
        out.append(items.size());
        out.append("\n");

        for (Item item : items) {
            out.append("Item: ID[");
            out.append(item.getObjid());
            out.append("], Href[");
            out.append(item.getXLinkHref());
            out.append("]\n");
        }

        assertEquals("Binding of all items within records failed.",
            response.getNumberOfResultingRecords(), items.size());
    }

    /**
     * Test Filter with null query.
     * 
     * @throws Exception
     */
    @Test
    public void testNullFilterSearch() throws Exception {
        ItemHandlerClientInterface c = new ItemHandlerClient();
        c.setTransport(transport);

        SearchRetrieveRequestType request = new SearchRetrieveRequestType();
        request.setRecordPacking(packing.name());

        SearchRetrieveResponse response = c.retrieveItems(request);

        assertNotNull("Response should not be null.", response);
        assertTrue("Filter should return ALL entries on empty search.",
            response.getNumberOfMatchingRecords() > 0);
    }

    /**
     * TODO
     * 
     * @throws Exception
     */
    // @Test
    public void testSearchScan() throws Exception {

        ScanRequestType request = new ScanRequestType();
        request.setScanClause("escidoc.metadata=escidoc");

        ScanResponse response = c.scan(request, null);

        assertNotNull(response.getTerms());

        out.append("\n=========================\n");
        out.append("ScanRequest: clause=");
        out.append(request.getScanClause());
        out.append(" [Protocol: ");
        out.append(transport.name());
        out.append("]\n");
        out.append("Results: ");

        for (Term term : response.getTerms()) {

            assertTrue(term.getNumberOfRecords() > 0);
            assertNotNull(term.getValue());

            out.append("Term: ");
            out.append(term.getValue());
            out.append(" [");
            out.append(term.getNumberOfRecords());
            out.append("]");
        }
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testUserdefindedSRWResult() throws Exception {
        final String objid = "escidoc:264161";
        final String srwResponse =
            "<?xml version=\"1.0\" ?> "
                + "<searchRetrieveResponse xmlns=\"http://www.loc.gov/zing/srw/\">"
                + "\t<version>1.1</version>"
                + "\t<numberOfRecords>1</numberOfRecords>"
                + "\t<records xmlns:ns1=\"http://www.loc.gov/zing/srw/\">"
                + "\t\t<record>"
                + "\t\t\t<recordSchema>default</recordSchema>"
                + "\t\t\t<recordPacking>xml</recordPacking>"
                + "\t\t\t<recordData>"
                + "\t\t\t\t<escidocItem:item xmlns:escidocItem=\"http://www.escidoc.de/schemas/item/0.9\">"
                + objid + "</escidocItem:item>" + "\t\t\t</recordData>"
                + "\t\t\t<recordPosition>1</recordPosition>" + "\t\t</record>"
                + "\t</records>" + "</searchRetrieveResponse>";

        // register new resolver
        SearchDescriptor.registerResolver(new MyResolver());

        // simulate request
        SearchRetrieveResponse response =
            MarshallerFactory.getInstance(transport)
                .getMarshaller(MarshallerFactory.CLASS_SEARCH_RETRIEVE_RESPONSE)
                .unmarshalDocument(srwResponse);

        assertTrue(response.getNumberOfResultingRecords() == 1);

        for (Record<?> record : response.getRecords()) {

            assertTrue(record instanceof MyRefRecord);

            Reference ref = (Reference) record.getRecordData();

            assertEquals(ref.getObjid(), objid);
            assertTrue(ref.getResourceType() == ResourceType.Item);
        }
    }

    /**
     * An implementation of RecordResolver for testing purposes.
     * 
     * @author MVO
     * 
     */
    private class MyResolver extends RecordResolver<MyRefRecord, Class<?>> {

        public MyResolver() {
            getResolvableRecordDefinitions().put(new TagEntry("item", null),
                ItemRef.class);
            getResolvableRecordDefinitions().put(
                new TagEntry("container", null), ContainerRef.class);
            // ingore namespaces
            setIgnoreNS(true);
        }

        @Override
        public MyRefRecord getRecordInstance(
            final Class<?> value, final RecordMetaData data) {
            // we do not need to check for value, since we only return one
            // record type but we could return different record types.
            if (value == ItemRef.class) {
                return new MyRefRecord(data);
            }
            else if (value == ContainerRef.class) {
                return new MyRefRecord(data);
            }
            return null;
        }
    }

    private class MyRefRecord extends Record<Reference> {

        public MyRefRecord(final RecordMetaData data) {
            super(data);
        }

        @Override
        protected Reference parseFragmentDOM() {
            Reference ref = null;
            try {
                ref = decode(getRecordDataDOMAsString());
            }
            catch (TransformerException e) {
                // do something
            }
            return ref;
        }

        @Override
        protected Reference parseFragmentText() {
            return decode(getRecordDataText());
        }

        /**
         * Some simple XML parsing here for test purposes.
         * 
         * @param xml
         * @return
         */
        private Reference decode(final String xml) {
            if (xml == null)
                return null;

            Pattern tagNameWithPrefix =
                Pattern
                    .compile("<(?:([^>^:^\\s]*):)?([^>^\\s]*?)(?:\\s[^<]*)?>");
            Matcher m = tagNameWithPrefix.matcher(xml);
            if (m.find()) {
                String id =
                    xml.substring(m.group(0).length(),
                        xml.indexOf('<', m.group(0).length()));

                if ("item".equals(m.group(2))) {
                    return new ItemRef(id);
                }
                else if ("container".equals(m.group(2))) {
                    return new ContainerRef(id);
                }
            }
            return null;
        }

        @Override
        protected ItemRef getDefaultInstance() {
            return null;
        }
    }
}