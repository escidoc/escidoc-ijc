package de.escidoc.core.resources.sb.search.records;

import org.w3c.dom.Element;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.om.item.Item;

/**
 * @author MVO
 * 
 */
public class ItemRecord extends AbstractRecord<Item> {

    /**
     * @param recordSchema
     * @param recordPacking
     * @param recordPosition
     * @param recordDataDOM
     * @param recordDataText
     * @param protocol
     */
    public ItemRecord(String recordSchema, String recordPacking,
        int recordPosition, Element recordDataDOM, String recordDataText,
        TransportProtocol protocol) {
        super(recordSchema, recordPacking, recordPosition, recordDataDOM,
            recordDataText, protocol);
    }

    /**
     * Use ItemMarshaller to bind the content.
     * 
     * @param xml
     * @return
     */
    Item decodeXMLString(final String xml) {
        try {
            return Factory
                .getMarshallerFactory(getProtocol()).getItemMarshaller()
                .unmarshalDocument(xmlHeader + xml);
        }
        catch (InternalClientException e) {
            LOGGER.debug("Unable to unmarshal recordData.", e);
        }
        return null;
    }
}
