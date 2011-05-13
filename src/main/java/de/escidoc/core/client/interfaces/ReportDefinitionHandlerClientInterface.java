/**
 * 
 */
package de.escidoc.core.client.interfaces;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.util.HashMap;
import java.util.List;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.base.CrudService;
import de.escidoc.core.client.interfaces.base.HandlerService;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;
import de.escidoc.core.resources.sm.report.ReportDefinition;
import de.escidoc.core.resources.sm.report.ReportDefinitionList;

/**
 * @author MVO
 * 
 */
public interface ReportDefinitionHandlerClientInterface extends HandlerService, CrudService<ReportDefinition> {

    /**
     * 
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    ExplainResponse retrieveReportDefinitions(final ExplainRequestType request) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * 
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    SearchRetrieveResponse retrieveReportDefinitions(final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * 
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    List<ReportDefinition> retrieveReportDefinitionsAsList(final SearchRetrieveRequestType request)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @SuppressWarnings("rawtypes")
    ReportDefinitionList retrieveReportDefinitions(final HashMap filter) throws EscidocException,
        InternalClientException, TransportException;
}
