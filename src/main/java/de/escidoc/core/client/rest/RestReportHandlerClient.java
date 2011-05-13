/**
 * 
 */
package de.escidoc.core.client.rest;

import java.net.URL;

import org.apache.log4j.Logger;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.rest.serviceLocator.ReportRestServiceLocator;
import de.escidoc.core.sm.ReportHandler;

/**
 * @author MVO
 * 
 */
public class RestReportHandlerClient extends RestClientBase {

    private static final Logger LOG = Logger.getLogger(RestReportHandlerClient.class);

    private ReportHandler client;

    /**
     * @throws InternalClientException
     */
    public RestReportHandlerClient() throws InternalClientException {
        super();
    }

    /**
     * @param serviceAddress
     * @throws InternalClientException
     */
    public RestReportHandlerClient(final URL serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * @param serviceAddress
     * @throws InternalClientException
     * @deprecated Use
     *             {@link RestReportHandlerClient#RestReportHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public RestReportHandlerClient(final String serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param xml
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieve(final String xml) throws EscidocException, InternalClientException, TransportException {
        if (xml == null)
            throw new IllegalArgumentException("xml must not be null.");

        String resultXml = null;
        try {
            resultXml = getClient().retrieve(xml);
        }
        catch (Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return resultXml;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    @Override
    public ReportHandler getClient() throws InternalClientException {
        if (this.client == null) {

            ReportRestServiceLocator serviceLocator = new ReportRestServiceLocator();
            serviceLocator.setServiceAddress(getServiceAddress());
            serviceLocator.registerRestCallbackHandler(this);
            this.client = serviceLocator;
        }
        return this.client;
    }
}