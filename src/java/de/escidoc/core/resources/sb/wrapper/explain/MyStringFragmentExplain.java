package de.escidoc.core.resources.sb.wrapper.explain;

import gov.loc.www.zing.srw.StringOrXmlFragment;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.resources.sb.explain.ExplainRecord;

public class MyStringFragmentExplain {
    StringOrXmlFragment stringOrXml;
    private static final  Marshaller<ExplainRecord> m =
        new Marshaller<ExplainRecord>((new ExplainRecord()).getClass());
    private ExplainRecord resultRecord;

    public MyStringFragmentExplain(StringOrXmlFragment stringOrXml) {
        this.stringOrXml = stringOrXml;
    }

    public ExplainRecord getResultRecord() throws Exception {
        org.apache.axis.message.MessageElement[] messages =
            this.stringOrXml.get_any();
            String recordData = decodeCharacters(messages[0].getAsString());
            String resource = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" 
                                           + recordData;
            this.resultRecord = m.unmarshalDocument(resource);
            return this.resultRecord;
    }

    public void setResultRecord(ExplainRecord resultRecord) {
        this.resultRecord = resultRecord;
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
