/**
 * 
 */
package de.escidoc.core.client.rest;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.AggregationDefinitionHandler;
import de.escidoc.core.client.rest.serviceLocator.AggregationDefinitionRestServiceLocator;

/**
 * @author MVO
 * 
 */
public class RestAggregationDefinitionHandlerClient extends RestClientBase {

    private static final Logger LOG = LoggerFactory.getLogger(RestAggregationDefinitionHandlerClient.class);

    private AggregationDefinitionHandler client;

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public RestAggregationDefinitionHandlerClient(final URL serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     * @deprecated Use
     *             {@link RestAggregationDefinitionHandlerClient#RestAggregationDefinitionHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public RestAggregationDefinitionHandlerClient(final String serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param id
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void delete(final String id) throws EscidocException, InternalClientException, TransportException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");

        try {
            getClient().delete(id);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
    }

    /**
     * 
     * @param xml
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String create(final String xml) throws EscidocException, InternalClientException, TransportException {

        if (xml == null)
            throw new IllegalArgumentException("xml must not be null.");

        String resultXml = null;
        try {
            resultXml = getClient().create(xml);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return resultXml;
    }

    /**
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieve(final String id) throws EscidocException, InternalClientException, TransportException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");

        String resultXml = null;
        try {
            resultXml = getClient().retrieve(id);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return resultXml;
    }

    /**
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveAggregationDefinitions(final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        evalRequest(request, true);

        String resultXml = null;
        try {
            resultXml = getClient().retrieveAggregationDefinitions(request);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return resultXml;
    }

    /**
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveAggregationDefinitions(final ExplainRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        evalRequest(request);

        String resultXml = null;
        try {
            resultXml = getClient().retrieveAggregationDefinitions(request);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return resultXml;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    @Override
    public AggregationDefinitionHandler getClient() throws InternalClientException {
        if (this.client == null) {

            final AggregationDefinitionRestServiceLocator serviceLocator =
                new AggregationDefinitionRestServiceLocator();
            serviceLocator.setServiceAddress(getServiceAddress());
            serviceLocator.registerRestCallbackHandler(this);
            this.client = serviceLocator;
        }
        return this.client;
    }
}