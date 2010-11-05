/**
 * 
 */
package de.escidoc.core.client;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.util.HashMap;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.rest.RestReportDefinitionHandlerClient;
import de.escidoc.core.client.soap.SoapReportDefinitionHandlerClient;

/**
 * @author MVO
 * 
 */
public class ReportDefinitionHandlerClient
    extends
    AbstractHandlerClient<SoapReportDefinitionHandlerClient, RestReportDefinitionHandlerClient> {

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
    public String create(final String xml) throws EscidocException,
        InternalClientException, TransportException {

        if (xml == null)
            throw new IllegalArgumentException("xml must not be null.");

        String resultXml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            resultXml = getSoapHandlerClient().create(xml);
        }
        else {
            resultXml = getRestHandlerClient().create(xml);
        }
        return resultXml;
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
     * 
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
    public String retrieveReportDefinitions(
        final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        String resultXml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            resultXml =
                getSoapHandlerClient().retrieveReportDefinitions(request);
        }
        else {
            resultXml =
                getRestHandlerClient().retrieveReportDefinitions(request);
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
    public String retrieveReportDefinitions(final ExplainRequestType request)
        throws EscidocException, InternalClientException, TransportException {

        String resultXml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            resultXml =
                getSoapHandlerClient().retrieveReportDefinitions(request);
        }
        else {
            resultXml =
                getRestHandlerClient().retrieveReportDefinitions(request);
        }
        return resultXml;
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
