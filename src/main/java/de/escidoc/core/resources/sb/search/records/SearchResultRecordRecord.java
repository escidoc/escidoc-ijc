/**
 * 
 */
package de.escidoc.core.resources.sb.search.records;

import gov.loc.www.zing.srw.RecordType;

import javax.xml.transform.TransformerException;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.common.XmlUtility;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.sb.Record;
import de.escidoc.core.resources.sb.RecordMetaData;
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
    public SearchResultRecordRecord(final RecordMetaData data) {
        super(data);
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
            return MarshallerFactory
                .getInstance(TransportProtocol.SOAP)
                .getMarshaller(SearchResultRecord.class)
                .unmarshalDocument(XmlUtility.XML_HEADER + xml);
        }
        catch (InternalClientException e) {
            LOG.debug("Unable to unmarshal recordData.", e);
        }
        return null;
    }

    @Override
    protected SearchResultRecord parseFragmentDOM() {
        try {
            return decodeXMLString(getRecordDataDOMAsString());
        }
        catch (TransformerException e) {
            LOG.debug("Unable to unmarshal recordData.", e);
        }
        return null;
    }

    @Override
    protected SearchResultRecord parseFragmentText() {
        return decodeXMLString(getRecordDataText());
    }

    @Override
    protected SearchResultRecord getDefaultInstance() {
        return new SearchResultRecord();
    }
}
