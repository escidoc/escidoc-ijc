/**
 * 
 */
package de.escidoc.core.client;

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
import de.escidoc.core.client.soap.SoapReportDefinitionHandlerClient;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;
import de.escidoc.core.resources.sm.report.ReportDefinition;

/**
 * @author MVO
 * 
 */
public class ReportDefinitionHandlerClient
    extends
    AbstractHandlerClient<SoapReportDefinitionHandlerClient, RestReportDefinitionHandlerClient>
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
     * @param id
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @Override
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
     * 
     * @param xml
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @Override
    public ReportDefinition create(final ReportDefinition reportDefinition)
        throws EscidocException, InternalClientException, TransportException {

        if (reportDefinition == null)
            throw new IllegalArgumentException(
                "reportDefinition must not be null.");

        Marshaller<ReportDefinition> m =
            MarshallerFactory.getInstance(getTransport()).getMarshaller(
                ReportDefinition.class);

        String xml = m.marshalDocument(reportDefinition);
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().create(xml);
        }
        else {
            xml = getRestHandlerClient().create(xml);
        }
        return m.unmarshalDocument(xml);
    }

    /**
     * 
     * @param id
     * @param xml
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @Override
    public ReportDefinition update(final ReportDefinition reportDefinition)
        throws EscidocException, InternalClientException, TransportException {

        if (reportDefinition == null)
            throw new IllegalArgumentException(
                "reportDefinition must not be null.");

        Marshaller<ReportDefinition> m =
            MarshallerFactory.getInstance(getTransport()).getMarshaller(
                ReportDefinition.class);

        String xml = m.marshalDocument(reportDefinition);
        if (getTransport() == TransportProtocol.SOAP) {
            xml =
                getSoapHandlerClient().update(reportDefinition.getObjid(), xml);
        }
        else {
            xml =
                getRestHandlerClient().update(reportDefinition.getObjid(), xml);
        }
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
            .getInstance(getTransport()).getMarshaller(ReportDefinition.class)
            .unmarshalDocument(xml);
    }

    /**
     * FIXME: implement
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @SuppressWarnings("rawtypes")
    public String retrieveReportDefinitions(final HashMap filter)
        throws EscidocException, InternalClientException, TransportException {

        if (filter == null)
            throw new IllegalArgumentException("filter must not be null.");

        String resultXml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            resultXml =
                getSoapHandlerClient().retrieveReportDefinitions(filter);
        }
        else {
            resultXml =
                getRestHandlerClient().retrieveReportDefinitions(filter);
        }
        return resultXml;
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

        if (request == null)
            throw new IllegalArgumentException("request must not be null.");

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveReportDefinitions(request);
        }
        else {
            xml = getRestHandlerClient().retrieveReportDefinitions(request);
        }
        return MarshallerFactory
            .getInstance(getTransport())
            .getMarshaller(SearchRetrieveResponse.class).unmarshalDocument(xml);
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

        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieveReportDefinitions(request);
        }
        else {
            xml = getRestHandlerClient().retrieveReportDefinitions(request);
        }
        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(ExplainResponse.class)
            .unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.AbstractHandlerClient#getSoapHandlerClientInstance
     * ()
     */
    @Override
    protected SoapReportDefinitionHandlerClient getSoapHandlerClientInstance()
        throws InternalClientException {
        return new SoapReportDefinitionHandlerClient(getServiceAddress());
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