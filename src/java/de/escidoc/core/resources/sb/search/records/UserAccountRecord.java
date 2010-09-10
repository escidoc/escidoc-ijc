/**
 * 
 */
package de.escidoc.core.resources.sb.search.records;

import org.w3c.dom.Element;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.aa.useraccount.UserAccount;

/**
 * @author MVO
 *
 */
public class UserAccountRecord extends AbstractRecord<UserAccount> {

	public UserAccountRecord(String recordSchema, String recordPacking,
			int recordPosition, Element recordDataDOM, String recordDataText,
			TransportProtocol protocol) {
		super(recordSchema, recordPacking, recordPosition, recordDataDOM,
				recordDataText, protocol);
	}

	@Override
	UserAccount decodeXMLString(String xml) {
		try {
			return Factory.getMarshallerFactory(getProtocol())
				.getUserAccountMarshaller().unmarshalDocument(xmlHeader + xml);
		} catch (InternalClientException e) {
			LOGGER.debug("Unable to unmarshal recordData.", e);
		}
		return null;
	}
	
	
}
