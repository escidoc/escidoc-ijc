/**
 * 
 */
package de.escidoc.core.resources.sb.search;

import java.util.LinkedList;
import java.util.List;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.common.types.PositiveInteger;
import de.escidoc.core.resources.sb.Response;

/**
 * @author MVO
 * 
 */
public class SearchRetrieveResponse extends Response {

    // required fields
    private int numberOfRecords = -1;

    // optional fields
    private String resultSetId;

    private PositiveInteger resultSetIdleTime;

    private PositiveInteger nextRecordPosition;

    // TODO
    private Object echoedSearchRetrieveRequest;

    // TODO
    private Object diagnostics;

    // TODO
    private Object extraResponseData;

    private final List<SearchResultRecord> records = new LinkedList<SearchResultRecord>();

    @JiBX
    protected SearchRetrieveResponse() {
        super();
    }

    /**
     * 
     * @return The number of records matching the query.
     */
    public final int getNumberOfMatchingRecords() {
        return this.numberOfRecords;
    }

    /**
     * 
     * @return The number of records matching the query <b>and</b> limited by
     *         maximumRecords or other parameters.
     */
    public final int getNumberOfResultingRecords() {
        return this.records.size();
    }

    /**
     * Collection of records.
     * 
     * @return records
     */
    public final List<SearchResultRecord> getRecords() {
        return records;
    }

    /**
     * @return the numberOfRecords
     */
    public final int getNumberOfRecords() {
        return numberOfRecords;
    }

    /**
     * @return the resultSetId
     */
    public final String getResultSetId() {
        return resultSetId;
    }

    /**
     * @return the resultSetIdleTime
     */
    public final PositiveInteger getResultSetIdleTime() {
        return resultSetIdleTime;
    }

    /**
     * @return the nextRecordPosition
     */
    public final PositiveInteger getNextRecordPosition() {
        return nextRecordPosition;
    }

    /**
     * @return the echoedSearchRetrieveRequest
     */
    public final Object getEchoedSearchRetrieveRequest() {
        return echoedSearchRetrieveRequest;
    }

    /**
     * @return the diagnostics
     */
    public final Object getDiagnostics() {
        return diagnostics;
    }

    /**
     * @return the extraResponseData
     */
    public final Object getExtraResponseData() {
        return extraResponseData;
    }
}