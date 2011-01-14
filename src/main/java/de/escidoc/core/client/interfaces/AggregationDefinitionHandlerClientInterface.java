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
import de.escidoc.core.client.interfaces.base.Createable;
import de.escidoc.core.client.interfaces.base.Deletable;
import de.escidoc.core.client.interfaces.base.HandlerService;
import de.escidoc.core.client.interfaces.base.Retrievable;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;
import de.escidoc.core.resources.sm.ad.AggregationDefinition;

/**
 * @author MVO
 * 
 */
public interface AggregationDefinitionHandlerClientInterface
    extends HandlerService, Createable<AggregationDefinition>,
    Retrievable<AggregationDefinition>, Deletable<AggregationDefinition> {

    /**
     * 
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    SearchRetrieveResponse retrieveAggregationDefinitions(
        SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * 
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    ExplainResponse retrieveAggregationDefinitions(ExplainRequestType request)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    List<AggregationDefinition> retrieveAggregationDefinitionsAsList(
        SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException;
}