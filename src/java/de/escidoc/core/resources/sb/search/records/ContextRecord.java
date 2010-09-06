/**
 * 
 */
package de.escidoc.core.resources.sb.search.records;

import org.w3c.dom.Element;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.om.context.Context;

/**
 * @author MVO
 *
 */
public class ContextRecord extends AbstractRecord<Context> {

	/**
	 * 
	 * @param recordSchema
	 * @param recordPacking
	 * @param recordPosition
	 * @param recordDataDOM
	 * @param recordDataText
	 * @param protocol
	 */
	public ContextRecord(String recordSchema, String recordPacking,
			int recordPosition, Element recordDataDOM, String recordDataText,
			TransportProtocol protocol) {
		super(recordSchema, recordPacking, recordPosition, recordDataDOM,
				recordDataText, protocol);
	}

	@Override
	Context decodeXMLString(String xml) {
		try {
			return Factory.getMarshallerFactory(getProtocol())
				.getContextMarshaller().unmarshalDocument(xmlHeader + xml);
		} catch (InternalClientException e) {
			LOGGER.debug("Unable to unmarshal recordData.", e);
		}
		return null;
	}
}
