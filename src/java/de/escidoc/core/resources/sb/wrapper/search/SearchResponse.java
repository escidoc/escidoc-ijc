package de.escidoc.core.resources.sb.wrapper.search;

import gov.loc.www.zing.srw.RecordType;
import gov.loc.www.zing.srw.SearchRetrieveResponseType;

public class SearchResponse  {
private SearchRetrieveResponseType response;

private MyRecordSearchType [] records;
/**
 * 
 * @param response
 */
public SearchResponse(SearchRetrieveResponseType response) {
   this.response = response;
}

public MyRecordSearchType [] getRecords() {
   RecordType [] records = response.getRecords();
   this.records = new MyRecordSearchType [records.length];
   for (int i=0; i < records.length; i++) {
       
       this.records[i] = new MyRecordSearchType(records[i]);
   }
   return this.records;
}

/**
 * 
 */
public org.apache.axis.types.NonNegativeInteger getNumberOfRecords() {
    return response.getNumberOfRecords();
}
/**
 * 
 */
public java.lang.String getResultSetId() {
    return response.getResultSetId();
}
/**
 * 
 */
public org.apache.axis.types.PositiveInteger getResultSetIdleTime() {
    return response.getResultSetIdleTime();
}
/**
 * 
 */
public org.apache.axis.types.PositiveInteger getNextRecordPosition() {
    return response.getNextRecordPosition();
}
/**
 * 
 */
public gov.loc.www.zing.srw.EchoedSearchRetrieveRequestType getEchoedSearchRetrieveRequest() {
    return response.getEchoedSearchRetrieveRequest();
}
/**
 * 
 */
public gov.loc.www.zing.srw.diagnostic.DiagnosticType[] getDiagnostics() {
    return response.getDiagnostics();
}
/**
 * 
 */
public gov.loc.www.zing.srw.ExtraDataType getExtraResponseData() {
    return response.getExtraResponseData();
}
}
