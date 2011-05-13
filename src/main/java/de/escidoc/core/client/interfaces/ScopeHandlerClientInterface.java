/**
 * 
 */
package de.escidoc.core.client.interfaces;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.util.List;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.base.CrudService;
import de.escidoc.core.client.interfaces.base.HandlerService;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;
import de.escidoc.core.resources.sm.scope.Scope;

/**
 * @author MVO
 * 
 */
public interface ScopeHandlerClientInterface extends HandlerService, CrudService<Scope> {

    /**
     * 
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    SearchRetrieveResponse retrieveScopes(final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * 
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    List<Scope> retrieveScopesAsList(final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * 
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    ExplainResponse retrieveScopes(final ExplainRequestType request) throws EscidocException, InternalClientException,
        TransportException;
}