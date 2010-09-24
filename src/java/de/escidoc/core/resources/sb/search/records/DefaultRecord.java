/**
 * 
 */
package de.escidoc.core.resources.sb.search.records;

import org.w3c.dom.Element;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.resources.sb.Record;

/**
 * @author MVO
 * 
 */
public class DefaultRecord extends Record<String> {

    /**
     * @param recordSchema
     * @param recordPacking
     * @param recordPosition
     * @param recordDataDOM
     * @param recordDataText
     * @param protocol
     */
    public DefaultRecord(String recordSchema, String recordPacking,
        int recordPosition, Element recordDataDOM, String recordDataText,
        TransportProtocol protocol) {
        super(recordSchema, recordPacking, recordPosition, recordDataDOM,
            recordDataText, protocol);
    }

    /**
     * Always returns null. Since this record does not know, how to handle the
     * recordData content.
     */
    @Override
    protected String decodeFragmentXML() {
        return null;
    }

    /**
     * Always returns null. Since this record does not know, how to handle the
     * recordData content.
     */
    @Override
    protected String decodeFragmentString() {
        return null;
    }

    /**
     * Returns the value of this.recordDataText by default because
     * {@link SearchRetrieveResponseRecordMarshaller} maps the content of
     * recordData as String by default. This means, that getRecordData() always
     * returns the value of this.recordDataText.
     * 
     * Use this.getRecordDataTextAsDom() to get the DOM for the text value.
     */
    @Override
    protected String getDefaultInstance() {
        return recordDataText;
    }
}
