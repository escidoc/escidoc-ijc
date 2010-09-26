/**
 * 
 */
package de.escidoc.core.resources.sb.search.records;

import gov.loc.www.zing.srw.RecordType;

import javax.xml.transform.TransformerException;

import org.w3c.dom.Element;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.common.XmlUtility;
import de.escidoc.core.resources.sb.Record;

/**
 * @author MVO
 * 
 */
abstract class AbstractRecord<T> extends Record<T> {

    /**
     * @param recordSchema
     * @param recordPacking
     * @param recordPosition
     * @param recordDataDOM
     * @param recordDataText
     * @param protocol
     */
    public AbstractRecord(String recordSchema, String recordPacking,
        int recordPosition, Element recordDataDOM, String recordDataText,
        TransportProtocol protocol) {
        super(recordSchema, recordPacking, recordPosition, recordDataDOM,
            recordDataText, protocol);
    }

    /**
     * Constructor for SOAP Axis.
     * 
     * @param recordType
     */
    public AbstractRecord(RecordType recordType) {
        this.recordSchema = recordType.getRecordSchema();
        this.recordPacking = recordType.getRecordPacking();
        if (recordType.getRecordPosition() != null) {
            this.recordPosition = recordType.getRecordPosition().intValue();
        }
        if (recordType.getRecordData() != null
            && recordType.getRecordData().get_any() != null
            && recordType.getRecordData().get_any()[0] != null) {
            try {
                this.recordDataText =
                    XmlUtility.unescapeForbiddenXmlCharacters(recordType
                        .getRecordData().get_any()[0].getAsString());
            }
            catch (Exception e) {
                LOGGER.debug("Unable to get RecordData from Axis type.", e);
            }
        }
        this.protocol = TransportProtocol.SOAP;
    }

    @Override
    protected T decodeFragmentXML() {
        try {
            return decodeXMLString(getRecordDataDOMAsString());
        }
        catch (TransformerException e) {
            LOGGER.debug("Unable to unmarshal recordData.", e);
        }
        return null;
    }

    @Override
    protected T decodeFragmentString() {
        return decodeXMLString(recordDataText);
    }

    /**
     * Use ItemMarshaller to bind the content.
     * 
     * @param xml
     * @return
     */
    abstract T decodeXMLString(final String xml);

    @Override
    protected T getDefaultInstance() {
        return null;
    }
}
