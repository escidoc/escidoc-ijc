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

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.RecordType;
import gov.loc.www.zing.srw.ScanRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;
import gov.loc.www.zing.srw.SearchRetrieveResponseType;

import java.util.Collection;
import java.util.Iterator;

import org.apache.axis.types.NonNegativeInteger;
import org.apache.axis.types.PositiveInteger;
import org.joda.time.DateTime;
import org.junit.Test;

import de.escidoc.core.client.SearchHandlerClient;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.resources.ResourceRef;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.sb.Record;
import de.escidoc.core.resources.sb.explain.ExplainData;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.explain.ServerInfo;
import de.escidoc.core.resources.sb.scan.ScanResponse;
import de.escidoc.core.resources.sb.scan.Term;
import de.escidoc.core.resources.sb.search.SearchResultRecord;
import de.escidoc.core.resources.sb.search.SearchRetrieveRecord;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;
import de.escidoc.core.resources.sb.wrapper.search.MyRecordSearchType;
import de.escidoc.core.resources.sb.wrapper.search.MyStringFragmentSearch;
import de.escidoc.core.resources.sb.wrapper.search.SearchResponse;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test of search handler.
 * 
 * 
 */
public class SearchHandlerClientTest extends EscidocClientTestBase {

    /**
     * Test to search repository.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testSearch() throws Exception {

        SearchHandlerClient rc = new SearchHandlerClient();

        SearchRetrieveRequestType request = new SearchRetrieveRequestType();
        request.setVersion("1.1");
        request.setQuery("escidoc.metadata=escidoc*");
        SearchRetrieveResponseType response = rc.search(request, null);
        NonNegativeInteger zahl = response.getNumberOfRecords();

        RecordType[] records = response.getRecords();
        for (int i = 0; i < records.length; i++) {
            RecordType record = records[i];
            decodeCharacters(record.getRecordData().get_any()[0].getAsString());
        }

    }

    /**
     * Test to search2 repository.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testSearch2() throws Exception {

        SearchHandlerClient rc = new SearchHandlerClient();

        SearchRetrieveRequestType request = new SearchRetrieveRequestType();
        request.setVersion("1.1");
        request.setQuery("escidoc.metadata=escidoc*");
        SearchRetrieveResponse response = rc.search2(request, null);
        int zahl = response.getNumberOfRecords();

//        Collection<SearchRetrieveRecord> records = response.getRecords();
//        for (Iterator<SearchRetrieveRecord> it = records.iterator(); it.hasNext();) {
//			SearchRetrieveRecord searchRetrieveRecord = it.next();
//			SearchResultRecord record = searchRetrieveRecord.getRecordData();
//			
//			// TODO
//		}
        
        
//        for (int i = 0; i < records.length; i++) {
//            MyRecordSearchType record = records[i];
//            MyStringFragmentSearch stringFragment = record.getStringFragment();
//            SearchResultRecord resultrecord = stringFragment.getResultRecord();
//
//            String base = resultrecord.getBase();
//            ResourceRef content = resultrecord.getContent();
//            if (content instanceof Item) {
//                Item item = (Item) content;
//                DateTime lmd = item.getLastModificationDate();
//                String versionNumber =
//                    item.getProperties().getVersion().getNumber();
//
//                Marshaller<SearchResultRecord> m =
//                    new Marshaller<SearchResultRecord>(resultrecord.getClass(), TransportProtocol.SOAP);
//                m.marshalDocument(resultrecord);
//            }
//
//        }

    }

    /**
     * Test explain method.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testExplain() throws Exception {

        SearchHandlerClient rc = new SearchHandlerClient();

        ExplainRequestType request = new ExplainRequestType();
        request.setVersion("1.1");

        ExplainResponse response = rc.explain2(request, null);
        Record record = response.getRecord();
    }

    /**
     * Test explain method.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testExplain2() throws Exception {

        SearchHandlerClient rc = new SearchHandlerClient();

        ExplainRequestType request = new ExplainRequestType();
        request.setVersion("1.1");
        request.setRecordPacking("string");

        ExplainResponse response = rc.explain2(request, null);
        ExplainData resultData = response.getRecord().getRecordData();

        ServerInfo si = resultData.getServerInfo();
        String host = si.getHost();

        Marshaller<ExplainData> m =
            new Marshaller<ExplainData>(resultData.getClass(), TransportProtocol.SOAP);
        String test = m.marshalDocument(resultData);
    }

    /**
     * Test scan method.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testScan() throws Exception {

        SearchHandlerClient rc = new SearchHandlerClient();

        ScanRequestType request = new ScanRequestType();
        request.setVersion("1.1");

        request.setScanClause("escidoc.metadata=escidoc");
        request.setResponsePosition(new PositiveInteger("1"));

        ScanResponse response = rc.scan(request, null);
        Collection<Term> terms = response.getTerms();
        for (Iterator<Term> it = terms.iterator(); it.hasNext();) {
			it.next().getValue();
		}
        response.getVersion();
    }

    /**
     * Replaces special Characters..
     * 
     * @return String Replaced String
     * @param text
     *            String text to replace
     */
    private String decodeCharacters(String text) {
        text = text.replaceAll("&lt;", "<");
        text = text.replaceAll("&gt;", ">");
        text = text.replaceAll("&quot;", "\"");
        text = text.replaceAll("&apos;", "'");
        // text = text.replaceAll("&amp;", "&");
        return text;
    }
}
