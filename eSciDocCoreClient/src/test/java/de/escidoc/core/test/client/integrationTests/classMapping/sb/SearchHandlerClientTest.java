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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import de.escidoc.core.client.interfaces.ItemHandlerClientInterface;
import de.escidoc.core.client.interfaces.SearchHandlerClientInterface;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.Resource;
import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.sb.Record;
import de.escidoc.core.resources.sb.RecordPacking;
import de.escidoc.core.resources.sb.explain.Explain;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.scan.ScanResponse;
import de.escidoc.core.resources.sb.scan.Term;
import de.escidoc.core.resources.sb.search.SearchDescriptor;
import de.escidoc.core.resources.sb.search.SearchResult;
import de.escidoc.core.resources.sb.search.SearchResultRecord;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;
import de.escidoc.core.resources.sb.search.resolver.ContentResolver;
import de.escidoc.core.resources.sb.search.resolver.TagEntry;
import de.escidoc.core.test.client.EscidocClientTestBase;

@RunWith(Parameterized.class)
public class SearchHandlerClientTest {

    private static final Logger LOG = Logger.getLogger(SearchHandlerClientTest.class);

    private static final StringBuilder out = new StringBuilder();

    private final RecordPacking packing;

    private SearchHandlerClientInterface c;

    public SearchHandlerClientTest(final RecordPacking packing) {
        this.packing = packing;
    }

    @Parameters
    public static Collection<?> data() {
        return Arrays.asList(new Object[][] { { RecordPacking.XML }, { RecordPacking.STRING } });
    }

    @Before
    public void init() throws Exception {
        // No authentication required for SB
        c = new SearchHandlerClient(EscidocClientTestBase.getDefaultInfrastructureURL());
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

        final ExplainRequestType request = new ExplainRequestType();
        request.setRecordPacking(packing.name());
        request.setVersion("1.1");

        final ExplainResponse response = c.explain(request, null);
        final Record<Explain> record = response.getRecord();
        final Explain data = record.getRecordData();

        out.append("\n=========================\n");
        out.append("testSRWExplain: ");
        out.append("RecordPacking: ");
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

        final ItemHandlerClientInterface c = new ItemHandlerClient(EscidocClientTestBase.getDefaultInfrastructureURL());

        final ExplainRequestType request = new ExplainRequestType();
        request.setVersion("1.1");
        request.setRecordPacking(packing.name());

        final ExplainResponse response = c.retrieveItems(request);
        final Record<Explain> record = response.getRecord();
        final Explain data = record.getRecordData();

        out.append("\n=========================\n");
        out.append("testFilterExplain: ");
        out.append("RecordPacking: ");
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

        final String query = "escidoc.objecttype=item";
        // "escidoc.objid=" + EscidocClientTestBase.getStaticItemId();

        final SearchRetrieveResponse response = c.search(query, null);

        out.append("\n=========================\n");
        out.append("testSRWSearch: query=");
        out.append(query);
        out.append(" [RecordPacking: ");
        out.append(packing);
        out.append("]\n");
        out.append("Results: ");
        out.append(response.getNumberOfResultingRecords());
        out.append("\n");

        for (final SearchResultRecord record : response.getRecords()) {

            final SearchResult data = record.getRecordData();

            assertNotNull(data);
            assertNotNull(data.getContent());

            if (data.getContent() instanceof Resource) {

                final Resource res = (Resource) data.getContent();
                assertNotNull(res.getObjid());

                out.append(res.getResourceType().name() + ": ID[" + res.getObjid() + "], Href[" + res.getXLinkHref()
                    + "], Score[" + data.getScore() + "]\n");
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
        final String query = null;
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

        final ItemHandlerClientInterface c = new ItemHandlerClient(EscidocClientTestBase.getDefaultInfrastructureURL());

        final String query = "\"/id\"=" + EscidocClientTestBase.getStaticItemId();

        final SearchRetrieveRequestType request = new SearchRetrieveRequestType();
        request.setQuery(query);
        request.setRecordPacking(packing.name());

        final SearchRetrieveResponse response = c.retrieveItems(request);

        out.append("\n=========================\n");
        out.append("testFilterSearch [1]: query=");
        out.append(query);
        out.append(" [RecordPacking: ");
        out.append(packing);
        out.append("]\n");
        out.append("Results: ");
        out.append(response.getNumberOfResultingRecords());
        out.append("\n");

        for (final SearchResultRecord record : response.getRecords()) {
            final SearchResult result = record.getRecordData();

            if (result.getContent() instanceof Item) {
                final Item item = (Item) result.getContent();
                out.append("Item: ID[" + item.getObjid() + "], Href[" + item.getXLinkHref() + "]\n");
            }
        }

        final Collection<Item> items = c.retrieveItemsAsList(request);

        out.append("\ntestFilterSearch [2]:\n");
        out.append("Results: ");
        out.append(items.size());
        out.append("\n");

        for (final Item item : items) {
            out.append("Item: ID[");
            out.append(item.getObjid());
            out.append("], Href[");
            out.append(item.getXLinkHref());
            out.append("]\n");
        }

        assertEquals("Binding of all items within records failed.", response.getNumberOfResultingRecords(), items
            .size());
    }

    /**
     * Test Filter with null query.
     * 
     * @throws Exception
     */
    @Test
    public void testNullFilterSearch() throws Exception {
        final ItemHandlerClientInterface c = new ItemHandlerClient(EscidocClientTestBase.getDefaultInfrastructureURL());

        final SearchRetrieveRequestType request = new SearchRetrieveRequestType();
        request.setRecordPacking(packing.name());

        final SearchRetrieveResponse response = c.retrieveItems(request);

        assertNotNull("Response should not be null.", response);
        assertTrue("Filter should return ALL entries on empty search.", response.getNumberOfMatchingRecords() > 0);
    }

    /**
     * TODO
     * 
     * @throws Exception
     */
    // @Test
    public void testSearchScan() throws Exception {

        final ScanRequestType request = new ScanRequestType();
        request.setScanClause("escidoc.metadata=escidoc");

        final ScanResponse response = c.scan(request, null);

        assertNotNull(response.getTerms());

        out.append("\n=========================\n");
        out.append("ScanRequest: clause=");
        out.append(request.getScanClause());
        out.append("\n");
        out.append("Results: ");

        for (final Term term : response.getTerms()) {

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
            "<?xml version=\"1.0\" ?> " + "<searchRetrieveResponse xmlns=\"http://www.loc.gov/zing/srw/\">"
                + "\t<version>1.1</version>" + "\t<numberOfRecords>1</numberOfRecords>"
                + "\t<records xmlns:ns1=\"http://www.loc.gov/zing/srw/\">" + "\t\t<record>"
                + "\t\t\t<recordSchema>default</recordSchema>" + "\t\t\t<recordPacking>xml</recordPacking>"
                + "\t\t\t<recordData>"
                + "\t\t\t\t<sr:search-result-record xmlns:sr=\"http://www.escidoc.de/schemas/searchresult/0.8\">"
                + "\t\t\t\t\t<my:item xmlns:my=\"http://mytest.com\">" + objid + "</my:item>"
                + "\t\t\t\t</sr:search-result-record>" + "\t\t\t</recordData>"
                + "\t\t\t<recordPosition>1</recordPosition>" + "\t\t</record>" + "\t</records>"
                + "</searchRetrieveResponse>";

        // register new resolver
        SearchDescriptor.registerResolver(new MyResolver());

        // simulate request
        final SearchRetrieveResponse response =
            MarshallerFactory.getInstance().getMarshaller(SearchRetrieveResponse.class).unmarshalDocument(srwResponse);

        assertTrue(response.getNumberOfResultingRecords() == 1);

        for (final SearchResultRecord record : response.getRecords()) {

            final SearchResult result = record.getRecordData();

            assertNotNull(result.getContent());
            assertTrue(result.getContent() instanceof MyRef);

            final MyRef ref = (MyRef) result.getContent();

            assertEquals(ref.getId(), objid);
            assertTrue(ref.getResourceType() == ResourceType.ITEM);
        }
        // remove registered resolver to avoid other tests to fail.
        SearchDescriptor.getResolvers().pop();
    }

    /**
     * An implementation of RecordResolver for testing purposes.
     * 
     * @author MVO
     * 
     */
    private class MyResolver extends ContentResolver<MyRef> {

        /**
         * @throws URISyntaxException
         */
        private MyResolver() throws URISyntaxException {
            getTagEntries().put(new TagEntry("item", new URI("http://mytest.com")), MyRef.class);
        }

        @Override
        public MyRef getContentInstance(final Class<? extends MyRef> clazz, final String xmlTextFragment) {

            /**
             * Since we only have one type of MyRef, we can ignore the first
             * argument. There are no sub types to differ between in here. <br/>
             * Map the XML to a MyRef instance here (done with some primitive
             * regexp)
             */
            final Pattern tagNameWithPrefix = Pattern.compile("<(?:([^>^:^\\s]*):)?([^>^\\s]*?)(?:\\s[^<]*)?>");
            final Matcher m = tagNameWithPrefix.matcher(xmlTextFragment);
            if (m.find()) {
                final String id =
                    xmlTextFragment.substring(m.group(0).length(), xmlTextFragment.indexOf('<', m.group(0).length()));

                if ("item".equals(m.group(2))) {
                    return new MyRef(id, ResourceType.ITEM);
                }
                else if ("container".equals(m.group(2))) {
                    return new MyRef(id, ResourceType.CONTAINER);
                }
            }
            return null;
        }

    }

    private class MyRef {

        private final String id;

        private final ResourceType type;

        /**
         * @param id
         * @param type
         */
        public MyRef(final String id, final ResourceType type) {
            this.id = id;
            this.type = type;
        }

        public ResourceType getResourceType() {
            return type;
        }

        /**
         * @return the id
         */
        public final String getId() {
            return id;
        }
    }
}