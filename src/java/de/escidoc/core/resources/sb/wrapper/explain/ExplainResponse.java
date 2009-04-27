package de.escidoc.core.resources.sb.wrapper.explain;

import gov.loc.www.zing.srw.ExplainResponseType;
import gov.loc.www.zing.srw.RecordType;

public class ExplainResponse  {
private ExplainResponseType response;

private MyRecordExplainType record;
/**
 * 
 * @param response
 */
public ExplainResponse(ExplainResponseType response) {
   this.response = response;
}
/**
 * Gets the record value for this ExplainResponseType.
 * @return
 */
public MyRecordExplainType getRecord() {
   RecordType record = response.getRecord();
   this.record = new MyRecordExplainType(record);
   return this.record;
}
/**
 * Gets the extraResponseData value for this ExplainResponseType.
 * @return
 */
public gov.loc.www.zing.srw.ExtraDataType getExtraResponseData(){
   return response.getExtraResponseData(); 
}

/**
 * Gets the echoedExplainRequest value for this ExplainResponseType.
 * 
 * @return echoedExplainRequest
 */
public gov.loc.www.zing.srw.ExplainRequestType getEchoedExplainRequest() {
    return response.getEchoedExplainRequest();
}


/**
 * Gets the diagnostics value for this ExplainResponseType.
 * 
 * @return diagnostics
 */
public gov.loc.www.zing.srw.diagnostic.DiagnosticType[] getDiagnostics() {
    return response.getDiagnostics();
}
}
