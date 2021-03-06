/**
 * 
 */
package de.escidoc.core.client.rest;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.handler.ReportHandler;
import de.escidoc.core.client.rest.serviceLocator.ReportRestServiceLocator;

/**
 * @author MVO
 * 
 */
public class RestReportHandlerClient extends RestClientBase {

    private static final Logger LOG = LoggerFactory.getLogger(RestReportHandlerClient.class);

    private ReportHandler client;

    /**
     * @param serviceAddress
     * @throws InternalClientException
     */
    public RestReportHandlerClient(final URL serviceAddress) throws InternalClientException {
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
        catch (final Exception e) {
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

            final ReportRestServiceLocator serviceLocator = new ReportRestServiceLocator();
            serviceLocator.setServiceAddress(getServiceAddress());
            serviceLocator.registerRestCallbackHandler(this);
            this.client = serviceLocator;
        }
        return this.client;
    }
}