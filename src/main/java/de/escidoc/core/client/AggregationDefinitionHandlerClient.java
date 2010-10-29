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
    public AggregationDefinitionHandlerClient(String serviceAddress) {
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
    public String create(final String xml) throws EscidocException,
        InternalClientException, TransportException {

        if (xml == null)
            throw new IllegalArgumentException("xml must not be null.");

        String xmlResult = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xmlResult = getSoapHandlerClient().create(xml);
        }
        else {
            xmlResult = getRestHandlerClient().create(xml);
        }
        return xmlResult;
    }

    /**
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieve(final String id) throws EscidocException,
        InternalClientException, TransportException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieve(id);
        }
        else {
            xml = getRestHandlerClient().retrieve(id);
        }
        return xml;
    }

    /**
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveAggregationDefinitions(
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
        return xml;
    }

    /**
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveAggregationDefinitions(
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
        return xml;
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
