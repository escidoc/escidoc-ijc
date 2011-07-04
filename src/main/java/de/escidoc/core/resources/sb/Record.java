package de.escidoc.core.resources.sb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    protected static final Logger LOG = LoggerFactory.getLogger(Record.class);

    protected String recordSchema;

    protected RecordPacking recordPacking;

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
        return recordPacking;
    }

    /**
     * @return
     */
    public PositiveInteger getRecordPosition() {
        return recordPosition;
    }
}