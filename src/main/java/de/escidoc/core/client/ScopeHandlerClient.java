/**
 * 
 */
package de.escidoc.core.client;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.rest.RestScopeHandlerClient;
import de.escidoc.core.client.soap.SoapScopeHandlerClient;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.sm.Scope;

/**
 * @author MVO
 * 
 */
public class ScopeHandlerClient
    extends
    AbstractHandlerClient<SoapScopeHandlerClient, RestScopeHandlerClient> {

    /**
     * 
     * @param serviceAddress
     */
    public ScopeHandlerClient(final String serviceAddress) {
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
    public Scope create(final Scope scope) throws EscidocException,
        InternalClientException, TransportException {

        if (scope == null)
            throw new IllegalArgumentException("scope must not be null.");

        Marshaller<Scope> m =
            Factory.getMarshallerFactory(getTransport()).getMarshaller(
                MarshallerFactory.CLASS_SCOPE);
        String resultXml = m.marshalDocument(scope);

        if (getTransport() == TransportProtocol.SOAP) {
            resultXml = getSoapHandlerClient().create(resultXml);
        }
        else {
            resultXml = getRestHandlerClient().create(resultXml);
        }
        return m.unmarshalDocument(resultXml);
    }

    /**
     * @param id
     * @param xml
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String update(final String id, final String xml)
        throws EscidocException, InternalClientException, TransportException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");
        if (xml == null)
            throw new IllegalArgumentException("xml must not be null.");

        String resultXml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            resultXml = getSoapHandlerClient().update(id, xml);
        }
        else {
            resultXml = getRestHandlerClient().update(id, xml);
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
    public String retrieve(final String id) throws EscidocException,
        InternalClientException, TransportException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");

        String resultXml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            resultXml = getSoapHandlerClient().retrieve(id);
        }
        else {
            resultXml = getRestHandlerClient().retrieve(id);
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
    public String retrieveScopes(final SearchRetrieveRequestType request)
        throws EscidocException, InternalClientException, TransportException {

        if (request == null)
            throw new IllegalArgumentException("request must not be null.");

        String resultXml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            resultXml = getSoapHandlerClient().retrieveScopes(request);
        }
        else {
            resultXml = getRestHandlerClient().retrieveScopes(request);
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
    public String retrieveScopes(final ExplainRequestType request)
        throws EscidocException, InternalClientException, TransportException {

        if (request == null)
            throw new IllegalArgumentException("request must not be null.");

        String resultXml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            resultXml = getSoapHandlerClient().retrieveScopes(request);
        }
        else {
            resultXml = getRestHandlerClient().retrieveScopes(request);
        }
        return resultXml;
    }

    @Override
    protected SoapScopeHandlerClient getSoapHandlerClientInstance()
        throws InternalClientException {
        return new SoapScopeHandlerClient(getServiceAddress());
    }

    @Override
    protected RestScopeHandlerClient getRestHandlerClientInstance()
        throws InternalClientException {
        return new RestScopeHandlerClient(getServiceAddress());
    }

}
