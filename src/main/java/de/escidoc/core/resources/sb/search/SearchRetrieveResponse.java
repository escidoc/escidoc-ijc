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
import de.escidoc.core.resources.sb.search.records.SearchResultRecordRecord;

/**
 * @author MVO
 * 
 */
public class SearchRetrieveResponse extends Response {

    private int numberOfRecords = -1;

    private final Collection<Record<?>> records = new LinkedList<Record<?>>();

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
    private SearchRetrieveResponse(
        final SearchRetrieveResponseType axisResponseType)
        throws InternalClientException {
        super(axisResponseType.getVersion());
        this.numberOfRecords = axisResponseType.getNumberOfRecords().intValue();

        if (axisResponseType.getRecords() != null) {
            RecordType[] records = axisResponseType.getRecords().getRecord();
            if (records != null) {
                for (int i = 0; i < records.length; i++) {
                    this.records.add(new SearchResultRecordRecord(records[i]));
                }
            }
        }
    }

    /**
     * 
     * @return The number of records matching the query.
     */
    public int getNumberOfMatchingRecords() {
        return this.numberOfRecords;
    }

    /**
     * 
     * @return The number of records matching the query <b>and</b> limited by
     *         maximumRecords or other parameters.
     */
    public int getNumberOfResultingRecords() {
        return this.records.size();
    }

    /**
     * Collection of retrieve records.
     * 
     * @return records
     */
    public Collection<Record<?>> getRecords() {
        return records;
    }

    /**
     * 
     * @param axisResponseType
     * @return
     * @throws InternalClientException
     */
    public static final SearchRetrieveResponse createSearchRetrieveResponse(
        final SearchRetrieveResponseType axisResponseType)
        throws InternalClientException {
        return new SearchRetrieveResponse(axisResponseType);
    }
}
