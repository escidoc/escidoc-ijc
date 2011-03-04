/**
 * 
 */
package de.escidoc.core.client;

import static de.escidoc.core.common.Precondition.checkNotNull;
import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.net.URL;
import java.util.HashMap;
import java.util.List;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.ReportDefinitionHandlerClientInterface;
import de.escidoc.core.client.rest.RestReportDefinitionHandlerClient;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;
import de.escidoc.core.resources.sm.report.ReportDefinition;
import de.escidoc.core.resources.sm.report.ReportDefinitionList;

/**
 * @author MVO
 * 
 */
public class ReportDefinitionHandlerClient
    extends AbstractHandlerClient<RestReportDefinitionHandlerClient>
    implements ReportDefinitionHandlerClientInterface {

    /**
     * 
     */
    public ReportDefinitionHandlerClient() {
        super();
    }

    /**
     * 
     * @param serviceAddress
     */
    public ReportDefinitionHandlerClient(final URL serviceAddress) {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @deprecated Use
     *             {@link ReportDefinitionHandlerClient#ReportDefinitionHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public ReportDefinitionHandlerClient(final String serviceAddress) {
        super(serviceAddress);
    }

    /**
     * 
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Createable#create(java.lang.Object
     * )
     */
    @Override
    public ReportDefinition create(final ReportDefinition reportDefinition)
        throws EscidocException, InternalClientException, TransportException {

        checkNotNull(reportDefinition);

        Marshaller<ReportDefinition> m =
            MarshallerFactory.getInstance().getMarshaller(
                ReportDefinition.class);

        String xml = getClient().create(m.marshalDocument(reportDefinition));

        return m.unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Updatable#update(java.lang.Object)
     */
    @Override
    public ReportDefinition update(final ReportDefinition reportDefinition)
        throws EscidocException, InternalClientException, TransportException {

        checkNotNull(reportDefinition);

        Marshaller<ReportDefinition> m =
            MarshallerFactory.getInstance().getMarshaller(
                ReportDefinition.class);

        String xml =
            getClient().update(reportDefinition.getObjid(),
                m.marshalDocument(reportDefinition));

        return m.unmarshalDocument(xml);
    }

    /**
     * 
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @Override
    public ReportDefinition retrieve(final String id) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(id);

        String xml = getClient().retrieve(id);

        return MarshallerFactory
            .getInstance().getMarshaller(ReportDefinition.class)
            .unmarshalDocument(xml);
    }

    /**
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * 
     * @deprecated Use
     *             {@link ReportDefinitionHandlerClient#retrieveReportDefinitions(SearchRetrieveRequestType)}
     *             instead.
     */
    @Override
    @Deprecated
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ReportDefinitionList retrieveReportDefinitions(final HashMap filter)
        throws EscidocException, InternalClientException, TransportException {

        checkNotNull(filter);

        String xml = getClient().retrieveReportDefinitions(filter);

        return MarshallerFactory
            .getInstance().getMarshaller(ReportDefinitionList.class)
            .unmarshalDocument(xml);
    }

    /**
     * 
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @Override
    public SearchRetrieveResponse retrieveReportDefinitions(
        final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(request);

        String xml = getClient().retrieveReportDefinitions(request);

        return MarshallerFactory
            .getInstance().getMarshaller(SearchRetrieveResponse.class)
            .unmarshalDocument(xml);
    }

    @Override
    public List<ReportDefinition> retrieveReportDefinitionsAsList(
        final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        return getSearchRetrieveResponseAsList(ReportDefinition.class,
            retrieveReportDefinitions(request));
    }

    /**
     * 
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @Override
    public ExplainResponse retrieveReportDefinitions(
        final ExplainRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(request);

        String xml = getClient().retrieveReportDefinitions(request);

        return MarshallerFactory
            .getInstance().getMarshaller(ExplainResponse.class)
            .unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.AbstractHandlerClient#getRestHandlerClientInstance
     * ()
     */
    @Override
    protected RestReportDefinitionHandlerClient getRestHandlerClientInstance()
        throws InternalClientException {
        return new RestReportDefinitionHandlerClient(getServiceAddress());
    }
}