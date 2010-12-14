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
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;
import de.escidoc.core.resources.sm.scope.Scope;

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
            MarshallerFactory.getInstance(getTransport()).getMarshaller(
                MarshallerFactory.CLASS_SCOPE);
        String xml = m.marshalDocument(scope);

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
     * @param xml
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public Scope update(final Scope scope) throws EscidocException,
        InternalClientException, TransportException {

        if (scope == null)
            throw new IllegalArgumentException("scope must not be null.");

        Marshaller<Scope> m =
            MarshallerFactory.getInstance(getTransport()).getMarshaller(
                Scope.class);
        String xml = m.marshalDocument(scope);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().update(scope.getObjid(), xml);
        }
        else {
            xml = getRestHandlerClient().update(scope.getObjid(), xml);
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
    public Scope retrieve(final String id) throws EscidocException,
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
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Scope.class)
            .unmarshalDocument(xml);
    }

    /**
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public SearchRetrieveResponse retrieveScopes(
        final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        if (request == null)
            throw new IllegalArgumentException("request must not be null.");

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveScopes(request);
        }
        else {
            xml = getRestHandlerClient().retrieveScopes(request);
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
    public ExplainResponse retrieveScopes(final ExplainRequestType request)
        throws EscidocException, InternalClientException, TransportException {

        if (request == null)
            throw new IllegalArgumentException("request must not be null.");

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveScopes(request);
        }
        else {
            xml = getRestHandlerClient().retrieveScopes(request);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(ExplainResponse.class)
            .unmarshalDocument(xml);
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
