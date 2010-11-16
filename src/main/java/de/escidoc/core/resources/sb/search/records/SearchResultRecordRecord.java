/**
 * 
 */
package de.escidoc.core.resources.sb.search.records;

import gov.loc.www.zing.srw.RecordType;

import javax.xml.transform.TransformerException;

import org.w3c.dom.Element;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.sb.Record;
import de.escidoc.core.resources.sb.search.SearchResultRecord;

/**
 * @author MVO
 * 
 */
public class SearchResultRecordRecord extends Record<SearchResultRecord> {

    /**
     * @param recordSchema
     * @param recordPacking
     * @param recordPosition
     * @param recordDataDOM
     * @param recordDataText
     * @param protocol
     */
    public SearchResultRecordRecord(final String recordSchema,
        final String recordPacking, final int recordPosition,
        final Element recordDataDOM, final String recordDataText,
        final TransportProtocol protocol) {
        super(recordSchema, recordPacking, recordPosition, recordDataDOM,
            recordDataText, protocol);
    }

    /**
     * 
     * @param recordType
     */
    public SearchResultRecordRecord(final RecordType recordType) {
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
                .getMarshaller(SearchResultRecord.class)
                .unmarshalDocument(xmlHeader + xml);
        }
        catch (InternalClientException e) {
            LOG.debug("Unable to unmarshal recordData.", e);
        }
        return null;
    }

    @Override
    protected SearchResultRecord decodeFragmentXML() {
        try {
            return decodeXMLString(getRecordDataDOMAsString());
        }
        catch (TransformerException e) {
            LOG.debug("Unable to unmarshal recordData.", e);
        }
        return null;
    }

    @Override
    protected SearchResultRecord decodeFragmentString() {
        return decodeXMLString(recordDataText);
    }

    @Override
    protected SearchResultRecord getDefaultInstance() {
        return new SearchResultRecord();
    }
}
