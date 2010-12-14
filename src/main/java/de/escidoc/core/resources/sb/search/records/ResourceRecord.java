/**
 * 
 */
package de.escidoc.core.resources.sb.search.records;

import javax.xml.transform.TransformerException;

import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.common.XmlUtility;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.Resource;
import de.escidoc.core.resources.sb.Record;
import de.escidoc.core.resources.sb.RecordMetaData;

/**
 * @author MVO
 * 
 */
public class ResourceRecord<T extends Resource> extends Record<T> {

    private final Class<T> recordDataType;

    /**
     * 
     * @param clazz
     * @param recordSchema
     * @param recordPacking
     * @param recordPosition
     * @param recordDataDOM
     * @param recordDataText
     * @param transport
     */
    private ResourceRecord(final Class<T> recordDataType,
        final RecordMetaData data) {
        super(data);

        if (recordDataType == null)
            throw new IllegalArgumentException("resource must not be null.");
        this.recordDataType = recordDataType;
    }

    /**
     * 
     * @param <T>
     * @param clazz
     * @param recordSchema
     * @param recordPacking
     * @param recordPosition
     * @param recordDataDOM
     * @param recordDataText
     * @param transport
     * @return
     */
    public static final <T extends Resource> ResourceRecord<T> createResourceRecord(
        final Class<T> clazz, final RecordMetaData data) {

        return new ResourceRecord<T>(clazz, data);
    }

    @Override
    protected T parseFragmentDOM() {
        try {
            return decodeXMLString(getRecordDataDOMAsString());
        }
        catch (TransformerException e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Unable to unmarshal recordData.", e);
            }
        }
        return null;
    }

    @Override
    protected T parseFragmentText() {
        return decodeXMLString(getRecordDataText());
    }

    /**
     * 
     * @param xml
     * @return
     */
    private T decodeXMLString(final String xml) {
        try {
            return MarshallerFactory
                .getInstance(getTransport()).getMarshaller(recordDataType)
                .unmarshalDocument(XmlUtility.XML_HEADER + xml);
        }
        catch (InternalClientException e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Unable to initialize resource.", e);
            }
        }
        return null;
    }

    /**
     * 
     * @return
     */
    public Class<T> getRecordDataType() {
        return recordDataType;
    }

    @Override
    protected T getDefaultInstance() {
        return null;
    }
}