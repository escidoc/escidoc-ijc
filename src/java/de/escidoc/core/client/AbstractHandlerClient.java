package de.escidoc.core.client;

import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import org.apache.log4j.Logger;

import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.common.configuration.ConfigurationProvider;

public abstract class AbstractHandlerClient {

	private static final Logger LOGGER = 
		Logger.getLogger(AbstractHandlerClient.class);
	
	//
	private TransportProtocol transport = TransportProtocol.SOAP;
	
	/**
	 * TODO: catch exception or throw? -> LOGGER
	 */
	public AbstractHandlerClient() {
		try {
			this.transport = TransportProtocol.valueOf(
					ConfigurationProvider.getInstance().getProperty(
							ConfigurationProvider.PROP_SERVICE_PROTOCOL));
		}
		catch (InternalClientException e) {
			LOGGER.debug("Unable to get the default transport protocol from " +
					"configuration. Using SOAP protocol instead.", e);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public final TransportProtocol getTransport() {
		return this.transport;
	}
	
	/**
	 * 
	 * @param transport
	 */
	public final void setTransport(TransportProtocol transport) {
		this.transport = transport;
	}
	
	/**
	 * This method validates the specified filter and sets default values to
	 * the filter if and only if the tested values are null or empty.
	 * 
	 * Currently checked values:
	 * <ul>
	 * <li>version (default: "1.1")</li>
	 * <li>recordPacking (default: "string")</li>
	 * </ul>
	 * 
	 * @param filter
	 */
	protected void evalFilter(final SearchRetrieveRequestType filter) {
		if(filter.getVersion() == null || filter.getVersion().isEmpty())
			filter.setVersion("1.1");
		if(filter.getRecordPacking() == null || 
				filter.getRecordPacking().isEmpty())
			filter.setRecordPacking("string");
	}
}
