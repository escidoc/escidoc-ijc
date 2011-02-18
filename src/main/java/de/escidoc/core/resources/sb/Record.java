package de.escidoc.core.resources.sb;

import org.apache.log4j.Logger;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.resources.common.types.PositiveInteger;

/**
 * 
 * 
 * @author SWA, MVO
 * 
 */
@JiBX
public abstract class Record<T> {

    protected static final Logger LOG = Logger.getLogger(Record.class);

    public static enum RecordPacking {
        string, xml
    };

    protected String recordSchema;

    protected String recordPacking;

    protected PositiveInteger recordPosition;

    protected T recordData;

    // TODO
    protected Object extraRecordData;

    // TODO remove?
    protected TransportProtocol transport;

    @JiBX
    protected Record() {
        this.transport = TransportProtocol.REST;
    }

    /**
     * @return the recordData
     */
    public T getRecordData() {
        return recordData;
    }

    /**
     * @return the protocol
     */
    public TransportProtocol getTransport() {
        return transport;
    }

    /**
     * @return
     */
    public String getRecordSchema() {
        return recordSchema;
    }

    /**
     * @return
     */
    public RecordPacking getRecordPacking() {
        if (recordPacking == null)
            return null;
        return RecordPacking.valueOf(recordPacking.toLowerCase());
    }

    /**
     * @return
     */
    public PositiveInteger getRecordPosition() {
        return recordPosition;
    }
}