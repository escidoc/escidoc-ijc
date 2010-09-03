package de.escidoc.core.resources.sb.wrapper.explain;

import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.resources.sb.explain.ExplainData;
import gov.loc.www.zing.srw.ExplainResponseType;
import gov.loc.www.zing.srw.RecordType;
import gov.loc.www.zing.srw.StringOrXmlFragment;

public class ExplainResponse {
	
    private ExplainResponseType response;

    private MyRecordExplainType record;

    /**
     * 
     * @param response
     */
    public ExplainResponse(final ExplainResponseType response) {
        this.response = response;
    }

    /**
     * Gets the record value for this ExplainResponseType.
     * 
     * @return
     */
    public MyRecordExplainType getRecord() {
        RecordType record = response.getRecord();
        this.record = new MyRecordExplainType(record);
        return this.record;
    }

    public ExplainData test() {
    	try {
			Marshaller<ExplainData> m = new Marshaller<ExplainData>(ExplainData.class);
			if(this.response.getRecord()!=null) {
				String data = decodeCharacters(this.response.getRecord().getRecordData().get_any()[0].getAsString());
				String resource = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + data;
				return m.unmarshalDocument(resource);
			}
			
		} catch (InternalClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
        
    }
    
    private String decodeCharacters(final String text) {

        String tmp = text;
        tmp = tmp.replaceAll("&lt;", "<");
        tmp = tmp.replaceAll("&gt;", ">");
        tmp = tmp.replaceAll("&quot;", "\"");
        tmp = tmp.replaceAll("&apos;", "'");
        // text = text.replaceAll("&amp;", "&");
        return tmp;
    }
    
    /**
     * Gets the extraResponseData value for this ExplainResponseType.
     * 
     * @return
     */
    public gov.loc.www.zing.srw.ExtraDataType getExtraResponseData() {
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
