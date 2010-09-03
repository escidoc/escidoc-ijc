package de.escidoc.core.resources.sb.wrapper.scan;

import gov.loc.www.zing.srw.ScanResponseType;

/**
 * 
 * @author MVO
 *
 */
public class ScanResponse {
	
	private ScanResponseType scanResponseType = null;
	
	public ScanResponse(ScanResponseType scanResponseType) {
		this.scanResponseType = scanResponseType;
	}
	
	public gov.loc.www.zing.srw.diagnostic.DiagnosticType[] getDiagnostics() {
		return scanResponseType.getDiagnostics();
	}
	
    public gov.loc.www.zing.srw.TermType[] getTerms() {
    	return scanResponseType.getTerms();
    }
    
    public gov.loc.www.zing.srw.EchoedScanRequestType getEchoedScanRequestType() {
    	return scanResponseType.getEchoedScanRequest();
    }
    
    public gov.loc.www.zing.srw.ExtraDataType getExtraResponseData() {
    	return scanResponseType.getExtraResponseData();
    }
}
