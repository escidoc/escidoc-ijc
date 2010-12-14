/**
 * 
 */
package de.escidoc.core.client;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.rest.RestAggregationDefinitionHandlerClient;
import de.escidoc.core.client.soap.SoapAggregationDefinitionHandlerClient;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;
import de.escidoc.core.resources.sm.ad.AggregationDefinition;

/**
 * @author MVO
 * 
 */
public class AggregationDefinitionHandlerClient
    extends
    AbstractHandlerClient<SoapAggregationDefinitionHandlerClient, RestAggregationDefinitionHandlerClient> {

    /**
     * 
     * @param serviceAddress
     */
    public AggregationDefinitionHandlerClient(final String serviceAddress) {
        super(serviceAddress);
    }

    /**
     * @param id
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void delete(final String id) throws EscidocException,
        InternalClientException, TransportException {
        if (id == null)
            throw new IllegalArgumentException("id must not be null.");

        if (getTransport() == TransportProtocol.SOAP) {
            getSoapHandlerClient().delete(id);
        }
        else {
            getRestHandlerClient().delete(id);
        }
    }

    /**
     * @param xml
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public AggregationDefinition create(final AggregationDefinition agDefinition)
        throws EscidocException, InternalClientException, TransportException {

        if (agDefinition == null)
            throw new IllegalArgumentException("agDefinition must not be null.");

        Marshaller<AggregationDefinition> m =
            MarshallerFactory.getInstance(getTransport()).getMarshaller(
                AggregationDefinition.class);

        String xml = m.marshalDocument(agDefinition);

        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().create(xml);
        }
        else {
            xml = getRestHandlerClient().create(xml);
        }
        return m.unmarshalDocument(xml);
    }

    /**
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public AggregationDefinition retrieve(final String id)
        throws EscidocException, InternalClientException, TransportException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieve(id);
        }
        else {
            xml = getRestHandlerClient().retrieve(id);
        }
        return Marshaller
            .getMarshaller(AggregationDefinition.class).unmarshalDocument(xml);
    }

    /**
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public SearchRetrieveResponse retrieveAggregationDefinitions(
        final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        if (request == null)
            throw new IllegalArgumentException("request must not be null.");

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapHandlerClient().retrieveAggregationDefinitions(request);
        }
        else {
            xml =
                getRestHandlerClient().retrieveAggregationDefinitions(request);
        }
        return MarshallerFactory
            .getInstance(getTransport())
            .getMarshaller(SearchRetrieveResponse.class).unmarshalDocument(xml);
    }

    /**
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public ExplainResponse retrieveAggregationDefinitions(
        final ExplainRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        if (request == null)
            throw new IllegalArgumentException("request must not be null.");

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapHandlerClient().retrieveAggregationDefinitions(request);
        }
        else {
            xml =
                getRestHandlerClient().retrieveAggregationDefinitions(request);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(ExplainResponse.class)
            .unmarshalDocument(xml);
    }

    @Override
    protected SoapAggregationDefinitionHandlerClient getSoapHandlerClientInstance()
        throws InternalClientException {
        return new SoapAggregationDefinitionHandlerClient(getServiceAddress());
    }

    @Override
    protected RestAggregationDefinitionHandlerClient getRestHandlerClientInstance()
        throws InternalClientException {
        return new RestAggregationDefinitionHandlerClient(getServiceAddress());
    }

}
