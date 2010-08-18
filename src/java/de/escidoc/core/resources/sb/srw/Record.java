package de.escidoc.core.resources.sb.srw;

import org.w3c.dom.Element;

/**
 * Record of SearchRetrieveResponseType is created to allow JiBX to weave in
 * code.
 * 
 * @author SWA
 * 
 */
public class Record {

    private String recordSchema = null;

    private String recordPacking = null;

    private int recordPosition = 0;

    private Element recordData = null;

    public void setRecordSchema(final String recordSchema) {
        this.recordSchema = recordSchema;
    }

    public String getRecordSchema() {
        return recordSchema;
    }

    public void setRecordPacking(final String recordPacking) {
        this.recordPacking = recordPacking;
    }

    public String getRecordPacking() {
        return recordPacking;
    }

    public void setRecordPosition(final int recordPosition) {
        this.recordPosition = recordPosition;
    }

    public int getRecordPosition() {
        return recordPosition;
    }

    public void setRecordData(final Element recordData) {
        this.recordData = recordData;
    }

    public Element getRecordData() {
        return recordData;
    }

}
