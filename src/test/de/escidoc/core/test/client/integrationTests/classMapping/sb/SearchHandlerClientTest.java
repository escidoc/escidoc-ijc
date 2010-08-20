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
import gov.loc.www.zing.srw.ExplainResponseType;
import gov.loc.www.zing.srw.RecordType;
import gov.loc.www.zing.srw.ScanRequestType;
import gov.loc.www.zing.srw.ScanResponseType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;
import gov.loc.www.zing.srw.SearchRetrieveResponseType;
import gov.loc.www.zing.srw.TermType;

import org.apache.axis.types.NonNegativeInteger;
import org.apache.axis.types.PositiveInteger;
import org.joda.time.DateTime;
import org.junit.Test;

import de.escidoc.core.client.SearchHandlerClient;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.resources.ResourceRef;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.sb.explain.ExplainRecord;
import de.escidoc.core.resources.sb.explain.ServerInfo;
import de.escidoc.core.resources.sb.search.SearchResultRecord;
import de.escidoc.core.resources.sb.wrapper.explain.ExplainResponse;
import de.escidoc.core.resources.sb.wrapper.explain.MyRecordExplainType;
import de.escidoc.core.resources.sb.wrapper.explain.MyStringFragmentExplain;
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
        SearchResponse response = rc.search2(request, null);
        NonNegativeInteger zahl = response.getNumberOfRecords();

        MyRecordSearchType[] records = response.getRecords();
        for (int i = 0; i < records.length; i++) {
            MyRecordSearchType record = records[i];
            MyStringFragmentSearch stringFragment = record.getStringFragment();
            SearchResultRecord resultrecord = stringFragment.getResultRecord();

            String base = resultrecord.getBase();
            ResourceRef content = resultrecord.getContent();
            if (content instanceof Item) {
                Item item = (Item) content;
                DateTime lmd = item.getLastModificationDate();
                String versionNumber =
                    item.getProperties().getVersion().getNumber();

                Marshaller<SearchResultRecord> m =
                    new Marshaller<SearchResultRecord>(resultrecord.getClass(), TransportProtocol.SOAP);
                m.marshalDocument(resultrecord);
            }

        }

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

        ExplainResponseType response = rc.explain(request, null);
        RecordType record = response.getRecord();

        String recordData =
            decodeCharacters(record.getRecordData().get_any()[0].getAsString());

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

        ExplainResponse response = rc.explain2(request, null);
        MyRecordExplainType record = response.getRecord();
        MyStringFragmentExplain stringFragment = record.getStringFragment();
        ExplainRecord resultrecord = stringFragment.getResultRecord();

        ServerInfo si = resultrecord.getServerInfo();
        String host = si.getHost();

        Marshaller<ExplainRecord> m =
            new Marshaller<ExplainRecord>(resultrecord.getClass(), TransportProtocol.SOAP);
        m.marshalDocument(resultrecord);

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

        ScanResponseType response = rc.scan(request, null);
        TermType[] terms = response.getTerms();
        for (int i = 0; i < terms.length; i++) {
            terms[i].getValue();

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
