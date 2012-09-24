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
import de.escidoc.core.client.interfaces.AggregationDefinitionHandlerClientInterface;
import de.escidoc.core.client.rest.RestAggregationDefinitionHandlerClient;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;
import de.escidoc.core.resources.sm.ad.AggregationDefinition;

/**
 * @author MVO
 * 
 */
public class AggregationDefinitionHandlerClient extends AbstractHandlerClient<RestAggregationDefinitionHandlerClient>
    implements AggregationDefinitionHandlerClientInterface {

    /**
     * 
     * @param serviceAddress
     */
    public AggregationDefinitionHandlerClient(final URL serviceAddress) {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @deprecated Use
     *             {@link AggregationDefinitionHandlerClient#AggregationDefinitionHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public AggregationDefinitionHandlerClient(final String serviceAddress) {
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
    public AggregationDefinition create(final AggregationDefinition agDefinition) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(agDefinition);

        final Marshaller<AggregationDefinition> m =
            MarshallerFactory.getInstance(getTransport()).getMarshaller(AggregationDefinition.class);

        final String xml = getClient().create(m.marshalDocument(agDefinition));
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
    public AggregationDefinition retrieve(final String id) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(id);

        final String xml = getClient().retrieve(id);
        return MarshallerFactory.getInstance().getMarshaller(AggregationDefinition.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.AggregationDefinitionHandlerClientInterface
     * #
     * retrieveAggregationDefinitions(gov.loc.www.zing.srw.SearchRetrieveRequestType
     * )
     */
    @Override
    public SearchRetrieveResponse retrieveAggregationDefinitions(final SearchRetrieveRequestType request)
        throws EscidocException, InternalClientException, TransportException {

        final String xml = getClient().retrieveAggregationDefinitions(request);
        return MarshallerFactory.getInstance().getMarshaller(SearchRetrieveResponse.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.AggregationDefinitionHandlerClientInterface
     * #retrieveAggregationDefinitionsAsList(gov.loc.www.zing.srw.
     * SearchRetrieveRequestType)
     */
    @Override
    public List<AggregationDefinition> retrieveAggregationDefinitionsAsList(final SearchRetrieveRequestType request)
        throws EscidocException, InternalClientException, TransportException {

        return getSearchRetrieveResponseAsList(AggregationDefinition.class, retrieveAggregationDefinitions(request));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.AggregationDefinitionHandlerClientInterface
     * #retrieveAggregationDefinitions(gov.loc.www.zing.srw.ExplainRequestType)
     */
    @Override
    public ExplainResponse retrieveAggregationDefinitions(final ExplainRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        final String xml = getClient().retrieveAggregationDefinitions(request);
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
    protected RestAggregationDefinitionHandlerClient getRestHandlerClientInstance() throws InternalClientException {
        return new RestAggregationDefinitionHandlerClient(getServiceAddress());
    }
}