/**
 * 
 */
package de.escidoc.core.resources.sb;

import de.escidoc.core.client.TransportProtocol;

/**
 * Read-only class.
 * 
 * This class is a representation of the configInfo of the response
 * of an explain request.
 * 
 * This class may be initialized either by a SOAP response instance
 * or by JiBX if and only if the REST protocol is being used for
 * the explain request.
 * 
 * @author MVO
 *
 */
public abstract class Response {

	protected String version;
	
	protected final TransportProtocol protocol;
	
	/**
	 * Constructor for REST response. This constructor will be used by JiBX only.
	 */
	protected Response() {
		this.protocol = TransportProtocol.REST;
	}
	
	/**
	 * Constructor for SOAP response.
	 * 
	 * @param version
	 */
	protected Response(String version) {
		this.version = version;
		this.protocol = TransportProtocol.SOAP;
	}
	
	public String getVersion() {
		return this.version;
	}
	
	/**
	 * @return the protocol
	 */
	public TransportProtocol getProtocol() {
		return protocol;
	}
}
