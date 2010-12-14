/**
 * 
 */
package de.escidoc.core.client.rest.serviceLocator;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.ScanRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.rmi.RemoteException;

import de.escidoc.core.client.interfaces.SearchHandler;

/**
 * @author MVO
 * 
 */
public class SearchRestServiceLocator extends RestServiceMethod
    implements SearchHandler {

    private static final String PATH_SRW = "/srw/search";

    private String database;

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.SearchHandler#explain(gov.loc.www.zing
     * .srw.ExplainRequestType, java.lang.String)
     */
    public String explain(final ExplainRequestType explainRequestType)
        throws RemoteException {
        return get(PATH_SRW + "/" + ((this.database == null) ? "" : database)
            + "/" + getExplainRequest(explainRequestType));
    }

    public String search(final SearchRetrieveRequestType searchRequestType)
        throws RemoteException, UnsupportedEncodingException {
        return get(PATH_SRW + "/" + ((this.database == null) ? "" : database)
            + "/" + getSearchRequest(searchRequestType));
    }

    public String scan(final ScanRequestType scanRequestType)
        throws RemoteException, UnsupportedEncodingException {
        return get(PATH_SRW + "/" + ((this.database == null) ? "" : database)
            + "/" + getScanRequest(scanRequestType));
    }

    /**
     * 
     * @param request
     * @return
     */
    private String getExplainRequest(final ExplainRequestType request) {
        String result = "?operation=explain";

        if (request.getVersion() != null) {
            result += "&version=" + request.getVersion();
        }
        if (request.getRecordPacking() != null) {
            result += "&recordPacking=" + request.getRecordPacking();
        }
        if (request.getStylesheet() != null) {
            result += "&stylesheet=" + String.valueOf(request.getStylesheet());
        }
        // According to the documentation extraRecordData will be ignored.
        return result;
    }

    /**
     * 
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    private String getSearchRequest(final SearchRetrieveRequestType request)
        throws UnsupportedEncodingException {
        String result = "?operation=searchRetrieve";

        if (request.getQuery() != null) {
            result +=
                "&query=" + URLEncoder.encode(request.getQuery(), "UTF-8");
        }
        if (request.getStartRecord() != null) {
            result +=
                "&startRecord=" + String.valueOf(request.getStartRecord());
        }
        if (request.getMaximumRecords() != null) {
            result +=
                "&maximumRecords="
                    + String.valueOf(request.getMaximumRecords());
        }
        if (request.getRecordPacking() != null) {
            result += "&recordPacking=" + request.getRecordPacking();
        }
        if (request.getRecordSchema() != null) {
            result += "&recordSchema=" + request.getRecordSchema();
        }
        if (request.getRecordXPath() != null) {
            result += "&recordXPath=" + request.getRecordXPath();
        }
        if (request.getResultSetTTL() != null) {
            result +=
                "&resultSetTTL=" + String.valueOf(request.getResultSetTTL());
        }
        if (request.getSortKeys() != null) {
            result += "&sortKeys=" + request.getSortKeys();
        }
        if (request.getStylesheet() != null) {
            result += "&stylesheet=" + String.valueOf(request.getStylesheet());
        }

        return result;
    }

    /**
     * 
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    private String getScanRequest(final ScanRequestType request)
        throws UnsupportedEncodingException {
        String result = "?operation=scan";

        if (request.getScanClause() != null) {
            result +=
                "&scanClause="
                    + URLEncoder.encode(request.getScanClause(), "UTF-8");
        }
        if (request.getResponsePosition() != null) {
            result +=
                "&responsePosition="
                    + String.valueOf(request.getResponsePosition());
        }
        if (request.getMaximumTerms() != null) {
            result +=
                "&maximumTerms=" + String.valueOf(request.getMaximumTerms());
        }
        if (request.getStylesheet() != null) {
            result += "&stylesheet=" + String.valueOf(request.getStylesheet());
        }

        return result;
    }

    /**
     * 
     * @param db
     */
    public void setDatabase(String database) {
        this.database = database;
    }
}
