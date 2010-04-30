package de.escidoc.core.resources.sb.wrapper.search;

import gov.loc.www.zing.srw.StringOrXmlFragment;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.resources.sb.search.SearchResultRecord;

public class MyStringFragmentSearch {
    StringOrXmlFragment stringOrXml;

    private static final Marshaller<SearchResultRecord> m =
        new Marshaller<SearchResultRecord>((new SearchResultRecord())
            .getClass());

    private SearchResultRecord resultRecord;

    public MyStringFragmentSearch(StringOrXmlFragment stringOrXml) {
        this.stringOrXml = stringOrXml;
    }

    public SearchResultRecord getResultRecord() throws Exception {
        org.apache.axis.message.MessageElement[] messages =
            this.stringOrXml.get_any();
        String recordData = decodeCharacters(messages[0].getAsString());
        String resource =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + recordData;
        this.resultRecord = m.unmarshalDocument(resource);
        return this.resultRecord;
    }

    public void setResultRecord(SearchResultRecord resultRecord) {
        this.resultRecord = resultRecord;
    }

    /**
     * Replaces special Characters..
     * 
     * @return String Replaced String
     * @param text
     *            String text to replace
     */
    private String decodeCharacters(String text) {

        String tmp = text;
        tmp = tmp.replaceAll("&lt;", "<");
        tmp = tmp.replaceAll("&gt;", ">");
        tmp = tmp.replaceAll("&quot;", "\"");
        tmp = tmp.replaceAll("&apos;", "'");
        // tmp = text.replaceAll("&amp;", "&");
        return tmp;
    }
}
