package de.escidoc.core.resources.sb;

import org.w3c.dom.Element;

import de.escidoc.core.client.TransportProtocol;

/**
 * 
 * @author MVO
 * 
 */
public class RecordMetaData {
    private final String recordSchema;

    private final String packing;

    private final int recordPosition;

    private final Element dataDOM;

    private final String dataText;

    private final TransportProtocol transport;

    /**
     * TODO null-checks
     * 
     * @param recordSchema
     * @param packing
     * @param recordPosition
     * @param dataDOM
     * @param dataText
     * @param transport
     */
    public RecordMetaData(final String recordSchema, final String packing,
        final int recordPosition, final Element dataDOM, final String dataText,
        final TransportProtocol transport) {

        this.recordSchema = recordSchema;
        this.packing = packing;
        this.recordPosition = recordPosition;
        this.dataDOM = dataDOM;
        this.dataText = dataText;
        this.transport = transport;
    }

    /**
     * @return the recordSchema
     */
    public final String getRecordSchema() {
        return recordSchema;
    }

    /**
     * @return the packing
     */
    public final String getPacking() {
        return packing;
    }

    /**
     * @return the recordPosition
     */
    public final int getRecordPosition() {
        return recordPosition;
    }

    /**
     * @return the dataDOM
     */
    public final Element getDataDOM() {
        return dataDOM;
    }

    /**
     * @return the dataText
     */
    public final String getDataText() {
        return dataText;
    }

    /**
     * @return the transport
     */
    public final TransportProtocol getTransport() {
        return transport;
    }

}