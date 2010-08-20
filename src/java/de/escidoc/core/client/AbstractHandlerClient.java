package de.escidoc.core.client;

import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.common.configuration.ConfigurationProvider;

public abstract class AbstractHandlerClient {

	//
	private TransportProtocol transport = TransportProtocol.SOAP;
	
	/**
	 * TODO: catch exception or throw? -> LOGGER
	 */
	public AbstractHandlerClient() {
		try {
			this.transport = TransportProtocol.valueOf(
					ConfigurationProvider.getInstance().getProperty(
							ConfigurationProvider.SERVICE_PROTOCOL));
		}
		catch (InternalClientException e) {
			e.printStackTrace();
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
}
