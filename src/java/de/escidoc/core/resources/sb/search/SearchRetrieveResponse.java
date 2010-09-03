/**
 * 
 */
package de.escidoc.core.resources.sb.search;

import gov.loc.www.zing.srw.RecordType;
import gov.loc.www.zing.srw.SearchRetrieveResponseType;

import java.util.Collection;
import java.util.LinkedList;

import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.resources.sb.Record;
import de.escidoc.core.resources.sb.Response;

/**
 * @author MVO
 *
 */
public class SearchRetrieveResponse extends Response {

	private int numberOfRecords = -1;

    private final Collection<Record> records = 
    	new LinkedList<Record>();
    
    /**
	 * Constructor for REST response.
	 */
	protected SearchRetrieveResponse() {
		super();
	}
	
	/**
	 * Constructor for SOAP response.
	 * 
	 * @param zingResponseType
	 * @throws InternalClientException 
	 */
	private SearchRetrieveResponse(SearchRetrieveResponseType zingResponseType)
		throws InternalClientException {
		super(zingResponseType.getVersion());
		this.numberOfRecords = zingResponseType.getNumberOfRecords().intValue();
		RecordType[] records = zingResponseType.getRecords();
		if(records != null) {
			for(int i=0; i < records.length; i++) {
				this.records.add(new SearchRetrieveRecord(records[i]));
			}
		}
	}
    
    /**
     * 
     * @return
     */
    public int getNumberOfRecords() {
        return this.numberOfRecords;
    }

    /**
     * Collection of retrieve records.
     * 
     * @return records
     */
    public Collection<Record> getRecords() {
        return records;
    }
    
    /**
	 * 
	 * @param axisResponseType
	 * @return
	 * @throws InternalClientException 
	 */
	public static final SearchRetrieveResponse createSearchRetrieveResponse(
			SearchRetrieveResponseType axisResponseType)
		throws InternalClientException {
		return new SearchRetrieveResponse(axisResponseType);
	}
}
