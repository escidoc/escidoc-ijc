/**
 * 
 */
package de.escidoc.core.client.rest;

import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

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

    private static final Logger LOG = Logger
        .getLogger(RestReportHandlerClient.class);

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
    public RestReportHandlerClient(final String serviceAddress)
        throws InternalClientException {
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
    public String retrieve(final String xml) throws EscidocException,
        InternalClientException, TransportException {
        if (xml == null)
            throw new IllegalArgumentException("xml must not be null.");

        String resultXml = null;
        try {
            resultXml = getClient().retrieve(xml);
        }
        catch (Exception e) {
            if (LOG.isDebugEnabled())
                LOG.debug(e.getMessage());
            ExceptionMapper.map(e);
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

            ReportRestServiceLocator serviceLocator =
                new ReportRestServiceLocator();
            try {
                serviceLocator.setServiceAddress(getServiceAddress());
            }
            catch (MalformedURLException e) {
                throw new InternalClientException(e);
            }
            serviceLocator.registerRestCallbackHandler(this);
            this.client = serviceLocator;
        }
        return this.client;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.ClientBase#getLastModificationDate(java.lang.String
     * )
     */
    @Override
    public DateTime getLastModificationDate(final String id)
        throws EscidocException, InternalClientException, TransportException {
        throw new UnsupportedOperationException("Method not supported.");
    }

}
