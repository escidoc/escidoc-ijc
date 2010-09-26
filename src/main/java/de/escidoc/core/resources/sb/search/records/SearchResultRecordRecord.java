/**
 * 
 */
package de.escidoc.core.resources.sb.search.records;

import gov.loc.www.zing.srw.RecordType;

import org.w3c.dom.Element;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.sb.search.SearchResultRecord;

/**
 * @author MVO
 * 
 */
public class SearchResultRecordRecord
    extends AbstractRecord<SearchResultRecord> {

    /**
     * @param recordSchema
     * @param recordPacking
     * @param recordPosition
     * @param recordDataDOM
     * @param recordDataText
     * @param protocol
     */
    public SearchResultRecordRecord(String recordSchema, String recordPacking,
        int recordPosition, Element recordDataDOM, String recordDataText,
        TransportProtocol protocol) {
        super(recordSchema, recordPacking, recordPosition, recordDataDOM,
            recordDataText, protocol);
    }

    /**
     * 
     * @param recordType
     */
    public SearchResultRecordRecord(RecordType recordType) {
        super(recordType);
    }

    /**
     * Use SearchResultMarshaller to bind the content.
     * 
     * FIXME Use the transport protocol of the parent Marshaller as soon as SRW
     * returns the content in REST format, when request is done via REST
     * transport protocol.
     * 
     * @param xml
     * @return
     */
    final SearchResultRecord decodeXMLString(final String xml) {
        try {
            return Factory
                .getMarshallerFactory(TransportProtocol.SOAP)
                .getSearchResultMarshaller().unmarshalDocument(xmlHeader + xml);
        }
        catch (InternalClientException e) {
            LOGGER.debug("Unable to unmarshal recordData.", e);
        }
        return null;
    }
}
