package de.escidoc.core.resources.sb.wrapper.search;

import gov.loc.www.zing.srw.RecordType;
import gov.loc.www.zing.srw.SearchRetrieveResponseType;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.resources.sb.search.SearchResultRecord;

public class SearchResponse {
    
    private SearchRetrieveResponseType response = null;

    private MyRecordSearchType[] records = null;
    
    private SearchResultRecord[] resultRecords = null;

    /**
     * 
     * @param response
     */
    public SearchResponse(final SearchRetrieveResponseType response) {
        this.response = response;
    }

    public MyRecordSearchType[] getRecords() {
        RecordType[] records = response.getRecords();
        if (records != null) {
            this.records = new MyRecordSearchType[records.length];
            for (int i = 0; i < records.length; i++) {

                this.records[i] = new MyRecordSearchType(records[i]);
            }
        }
        return this.records;
    }

    public SearchResultRecord[] getSearchResultRecords() throws Exception {
    	RecordType[] records = response.getRecords();
    	if(records != null) {
    		this.resultRecords = new SearchResultRecord[records.length];
    		Marshaller<SearchResultRecord> m = new Marshaller<SearchResultRecord>(SearchResultRecord.class);
    		
    		for(int i=0; i<records.length; i++) {
    			String data = decodeCharacters(records[i].getRecordData()
    					.get_any()[0].getAsString());
    			this.resultRecords[i] = m.unmarshalDocument(data);
    		}
    	}
    	return this.resultRecords;
    }
    
    private String decodeCharacters(String text) {

        String tmp = text;
        tmp = tmp.replaceAll("&lt;", "<");
        tmp = tmp.replaceAll("&gt;", ">");
        tmp = tmp.replaceAll("&quot;", "\"");
        tmp = tmp.replaceAll("&apos;", "'");
        // tmp = text.replaceAll("&amp;", "&");
        return tmp;
    }
    
    /**
     * 
     * @return
     */
    public org.apache.axis.types.NonNegativeInteger getNumberOfRecords() {
        return response.getNumberOfRecords();
    }

    /**
     * 
     * @return
     */
    public java.lang.String getResultSetId() {
        return response.getResultSetId();
    }

    public org.apache.axis.types.PositiveInteger getResultSetIdleTime() {
        return response.getResultSetIdleTime();
    }

    /**
     * 
     * @return
     */
    public org.apache.axis.types.PositiveInteger getNextRecordPosition() {
        return response.getNextRecordPosition();
    }

    /**
     * 
     * @return
     */
    public gov.loc.www.zing.srw.EchoedSearchRetrieveRequestType getEchoedSearchRetrieveRequest() {
        return response.getEchoedSearchRetrieveRequest();
    }

    /**
     * 
     * @return
     */
    public gov.loc.www.zing.srw.diagnostic.DiagnosticType[] getDiagnostics() {
        return response.getDiagnostics();
    }

    /**
     * 
     * @return
     */
    public gov.loc.www.zing.srw.ExtraDataType getExtraResponseData() {
        return response.getExtraResponseData();
    }
}
