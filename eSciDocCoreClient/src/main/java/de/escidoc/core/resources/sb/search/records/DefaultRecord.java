/**
 * 
 */
package de.escidoc.core.resources.sb.search.records;

import de.escidoc.core.common.jibx.binding.SearchRetrieveResponseRecordMarshaller;
import de.escidoc.core.resources.sb.Record;
import de.escidoc.core.resources.sb.RecordMetaData;

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
    public DefaultRecord(final RecordMetaData data) {
        super(data);
    }

    /**
     * Always returns null. Since this record does not know, how to handle the
     * recordData content.
     */
    @Override
    protected String parseFragmentDOM() {
        return null;
    }

    /**
     * Always returns null. Since this record does not know, how to handle the
     * recordData content.
     */
    @Override
    protected String parseFragmentText() {
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
        return getRecordDataText();
    }
}
