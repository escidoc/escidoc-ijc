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

import de.escidoc.core.client.SearchHandlerClient;
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
    public void testSearch() throws Exception {

        SearchHandlerClient rc = new SearchHandlerClient();

        SearchRetrieveRequestType request = new SearchRetrieveRequestType();
        request.setVersion("1.1");
        request.setQuery("escidoc.metadata=escidoc*");
        SearchRetrieveResponseType response = rc.search(request, null);
        NonNegativeInteger zahl = response.getNumberOfRecords();
        System.out.println("response length " + zahl.toString());
        RecordType[] records = response.getRecords();
        for (int i = 0; i < records.length; i++) {
            RecordType record = records[i];
            String recordData =
                decodeCharacters(record.getRecordData().get_any()[0]
                    .getAsString());
            System.out.println("search record data " + recordData);

        }

    }

    /**
     * Test to search2 repository.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    public void testSearch2() throws Exception {

        SearchHandlerClient rc = new SearchHandlerClient();

        SearchRetrieveRequestType request = new SearchRetrieveRequestType();
        request.setVersion("1.1");
        request.setQuery("escidoc.metadata=escidoc*");
        SearchResponse response = rc.search2(request, null);
        NonNegativeInteger zahl = response.getNumberOfRecords();
        System.out.println("response length " + zahl.toString());
        MyRecordSearchType[] records = response.getRecords();
        for (int i = 0; i < records.length; i++) {
            MyRecordSearchType record = records[i];
            MyStringFragmentSearch stringFragment = record.getStringFragment();
            SearchResultRecord resultrecord = stringFragment.getResultRecord();

            String base = resultrecord.getBase();
            System.out
                .println("**************************************************");
            System.out.println("number " + i);
            System.out.println("base url " + base);
            ResourceRef content = resultrecord.getContent();
            if (content instanceof Item) {
                Item item = (Item) content;
                String lmd = item.getLastModificationDateAsString();
                String versionNumber =
                    item.getProperties().getVersion().getNumber();
                System.out.println("item last mod date" + lmd);
                System.out.println("item version number " + versionNumber);

                Marshaller<SearchResultRecord> m =
                    new Marshaller<SearchResultRecord>(resultrecord.getClass());
                String xml = m.marshalDocument(resultrecord);
                System.out.println(xml);

            }

        }

    }

    /**
     * Test explain method.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    public void testExplain() throws Exception {

        SearchHandlerClient rc = new SearchHandlerClient();

        ExplainRequestType request = new ExplainRequestType();
        request.setVersion("1.1");

        ExplainResponseType response = rc.explain(request, null);
        RecordType record = response.getRecord();

        String recordData =
            decodeCharacters(record.getRecordData().get_any()[0].getAsString());
        System.out.println("record data " + recordData);

    }

    /**
     * Test explain method.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
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
        System.out.println("host " + host);

        Marshaller<ExplainRecord> m =
            new Marshaller<ExplainRecord>(resultrecord.getClass());
        String xml = m.marshalDocument(resultrecord);
        System.out.println(xml);

    }

    /**
     * Test scan method.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    public void testScan() throws Exception {

        SearchHandlerClient rc = new SearchHandlerClient();

        ScanRequestType request = new ScanRequestType();
        request.setVersion("1.1");

        request.setScanClause("escidoc.metadata=escidoc");
        request.setResponsePosition(new PositiveInteger("1"));

        ScanResponseType response = rc.scan(request, null);
        TermType[] terms = response.getTerms();
        for (int i = 0; i < terms.length; i++) {
            System.out.println("term value " + terms[i].getValue());

        }
        System.out.println("version " + response.getVersion());
    }

    /**
     * Replaces special Characters..
     * 
     * @return String Replaced String
     * @param text
     *            String text to replace
     * 
     * @sb
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
