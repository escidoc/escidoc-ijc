package de.escidoc.core.resources.sb.search;

import gov.loc.www.zing.srw.RecordType;

import javax.xml.transform.TransformerException;

import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.sb.Record;

/**
 * 
 * @author MVO
 * 
 */
public class SearchRetrieveRecord extends Record<SearchResultRecord> {

    private SearchResultRecord resultData;

    /**
	 * 
	 */
    public SearchRetrieveRecord() {
        super();
    }

    /**
     * @param axisRecord
     * @param type
     * @throws InternalClientException
     */
    public SearchRetrieveRecord(RecordType axisRecord)
        throws InternalClientException {
        super(axisRecord);
    }

    @Override
    protected SearchResultRecord decodeFragmentXML() {
        if (this.resultData != null)
            return this.resultData;
        else {
            try {
                String xml =
                    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                        + getRecordDataDOMAsString();
                resultData =
                    Factory
                        .getMarshallerFactory(getProtocol())
                        .getSearchResultMarshaller().unmarshalDocument(xml);
                return resultData;

            }
            catch (TransformerException e) {
                // ignore
                LOGGER.debug(e.getMessage(), e);
            }
            catch (InternalClientException e) {
                // ignore
                LOGGER.debug(e.getMessage(), e);
            }
            return null;
        }
    }

    @Override
    protected SearchResultRecord decodeFragmentString() {
        if (resultData != null)
            return resultData;
        else {
            try {
                resultData =
                    Factory
                        .getMarshallerFactory(getProtocol())
                        .getSearchResultMarshaller()
                        .unmarshalDocument(getRecordDataText());
                return resultData;
            }
            catch (InternalClientException e) {
                // ignore
                LOGGER.debug(e.getMessage(), e);
            }
        }
        return null;
    }

    @Override
    protected SearchResultRecord getDefaultInstance() {
        return null;
    }

}
