/**
 * 
 */
package de.escidoc.core.resources.sb.explain;

import gov.loc.www.zing.srw.ExplainResponseType;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.resources.sb.Record;
import de.escidoc.core.resources.sb.Response;

/**
 * Read-only class.
 * 
 * This class is a representation of the response of an explain request.
 * 
 * This class may be initialized either by a SOAP response instance
 * or by JiBX if and only if the REST protocol is being used for
 * the explain request.
 * 
 * @author MVO
 * 
 */
public class ExplainResponse extends Response {

	private ExplainRecord record;
	
	/**
	 * Constructor for REST response.
	 */
	protected ExplainResponse() {
		super();
	}
	
	/**
	 * Constructor for SOAP response.
	 * 
	 * @param zingResponseType
	 * @throws InternalClientException 
	 */
	private ExplainResponse(ExplainResponseType zingResponseType)
		throws InternalClientException {
		super(zingResponseType.getVersion());
		
		this.record = new ExplainRecord(zingResponseType.getRecord());
	}
	
	/**
	 * Convenience method to directly access data section of the record. 
	 * 
	 * @return
	 */
	public Record<ExplainData> getRecord() {
		return this.record;
	}
	
	/**
	 * 
	 * @param axisResponseType
	 * @return
	 * @throws InternalClientException 
	 */
	public static final ExplainResponse createExplainResponse(
			ExplainResponseType axisResponseType)
		throws InternalClientException {
		return new ExplainResponse(axisResponseType);
	}
}
