/**
 * 
 */
package de.escidoc.core.client;

import static de.escidoc.core.common.Precondition.checkNotNull;
import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.net.URL;
import java.util.List;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.ScopeHandlerClientInterface;
import de.escidoc.core.client.rest.RestScopeHandlerClient;
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
    extends AbstractHandlerClient<RestScopeHandlerClient>
    implements ScopeHandlerClientInterface {

    /**
     * 
     */
    public ScopeHandlerClient() {
        super();
    }

    /**
     * 
     * @param serviceAddress
     */
    public ScopeHandlerClient(final URL serviceAddress) {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @deprecated Use {@link ScopeHandlerClient#ScopeHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public ScopeHandlerClient(final String serviceAddress) {
        super(serviceAddress);
    }

    /**
     * @param id
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @Override
    public void delete(final String id) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(id);

        getClient().delete(id);

    }

    /**
     * @param xml
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @Override
    public Scope create(final Scope scope) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(scope);

        Marshaller<Scope> m =
            MarshallerFactory.getInstance().getMarshaller(Scope.class);

        String xml = getClient().create(m.marshalDocument(scope));

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
    @Override
    public Scope update(final Scope scope) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(scope);

        Marshaller<Scope> m =
            MarshallerFactory.getInstance().getMarshaller(Scope.class);

        String xml =
            getClient().update(scope.getObjid(), m.marshalDocument(scope));

        return m.unmarshalDocument(xml);
    }

    /**
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @Override
    public Scope retrieve(final String id) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(id);

        String xml = getClient().retrieve(id);

        return MarshallerFactory
            .getInstance().getMarshaller(Scope.class).unmarshalDocument(xml);
    }

    /**
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @Override
    public SearchRetrieveResponse retrieveScopes(
        final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(request);

        String xml = getClient().retrieveScopes(request);

        return MarshallerFactory
            .getInstance().getMarshaller(SearchRetrieveResponse.class)
            .unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.ScopeHandlerClientInterface#
     * retrieveScopesAsList(gov.loc.www.zing.srw.SearchRetrieveRequestType)
     */
    @Override
    public List<Scope> retrieveScopesAsList(
        final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        return getSearchRetrieveResponseAsList(Scope.class,
            retrieveScopes(request));
    }

    /**
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @Override
    public ExplainResponse retrieveScopes(final ExplainRequestType request)
        throws EscidocException, InternalClientException, TransportException {

        checkNotNull(request);

        String xml = getClient().retrieveScopes(request);

        return MarshallerFactory
            .getInstance().getMarshaller(ExplainResponse.class)
            .unmarshalDocument(xml);
    }

    @Override
    protected RestScopeHandlerClient getRestHandlerClientInstance()
        throws InternalClientException {
        return new RestScopeHandlerClient(getServiceAddress());
    }
}