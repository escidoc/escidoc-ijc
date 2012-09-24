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
public class ScopeHandlerClient extends AbstractHandlerClient<RestScopeHandlerClient>
    implements ScopeHandlerClientInterface {

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

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Deletable#delete(java.lang.String)
     */
    @Override
    public void delete(final String id) throws EscidocException, InternalClientException, TransportException {

        checkNotNull(id);

        getClient().delete(id);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Createable#create(java.lang.Object
     * )
     */
    @Override
    public Scope create(final Scope scope) throws EscidocException, InternalClientException, TransportException {

        checkNotNull(scope);

        final Marshaller<Scope> m = MarshallerFactory.getInstance().getMarshaller(Scope.class);

        final String xml = getClient().create(m.marshalDocument(scope));

        return m.unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Updatable#update(java.lang.Object)
     */
    @Override
    public Scope update(final Scope scope) throws EscidocException, InternalClientException, TransportException {

        checkNotNull(scope);

        return update(scope.getObjid(), scope);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Updatable#update(java.lang.String,
     * java.lang.Object)
     */
    @Override
    public Scope update(final String id, final Scope scope) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);
        checkNotNull(scope);

        final Marshaller<Scope> m = MarshallerFactory.getInstance().getMarshaller(Scope.class);

        final String xml = getClient().update(id, m.marshalDocument(scope));

        return m.unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Retrievable#retrieve(java.lang
     * .String)
     */
    @Override
    public Scope retrieve(final String id) throws EscidocException, InternalClientException, TransportException {

        checkNotNull(id);

        final String xml = getClient().retrieve(id);

        return MarshallerFactory.getInstance().getMarshaller(Scope.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.ScopeHandlerClientInterface#retrieveScopes
     * (gov.loc.www.zing.srw.SearchRetrieveRequestType)
     */
    @Override
    public SearchRetrieveResponse retrieveScopes(final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(request);

        final String xml = getClient().retrieveScopes(request);

        return MarshallerFactory.getInstance().getMarshaller(SearchRetrieveResponse.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.ScopeHandlerClientInterface#
     * retrieveScopesAsList(gov.loc.www.zing.srw.SearchRetrieveRequestType)
     */
    @Override
    public List<Scope> retrieveScopesAsList(final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        return getSearchRetrieveResponseAsList(Scope.class, retrieveScopes(request));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.ScopeHandlerClientInterface#retrieveScopes
     * (gov.loc.www.zing.srw.ExplainRequestType)
     */
    @Override
    public ExplainResponse retrieveScopes(final ExplainRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(request);

        final String xml = getClient().retrieveScopes(request);

        return MarshallerFactory.getInstance().getMarshaller(ExplainResponse.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.AbstractHandlerClient#getRestHandlerClientInstance
     * ()
     */
    @Override
    protected RestScopeHandlerClient getRestHandlerClientInstance() throws InternalClientException {
        return new RestScopeHandlerClient(getServiceAddress());
    }
}