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
import de.escidoc.core.client.rest.serviceLocator.StatisticDataRestServiceLocator;
import de.escidoc.core.sm.StatisticDataHandler;

/**
 * @author MVO
 * 
 */
public class RestStatisticDataHandlerClient extends RestClientBase {

    private static final Logger LOG = LoggerFactory.getLogger(RestStatisticDataHandlerClient.class);

    private StatisticDataHandler client;

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public RestStatisticDataHandlerClient(final URL serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     * @deprecated Use
     *             {@link RestStatisticDataHandlerClient#RestStatisticDataHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public RestStatisticDataHandlerClient(final String serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param xml
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void create(final String xml) throws EscidocException, InternalClientException, TransportException {

        if (xml == null)
            throw new IllegalArgumentException("xml must not be null.");

        try {
            getClient().create(xml);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    @Override
    public StatisticDataHandler getClient() throws InternalClientException {
        if (this.client == null) {

            final StatisticDataRestServiceLocator serviceLocator = new StatisticDataRestServiceLocator();
            serviceLocator.setServiceAddress(getServiceAddress());
            serviceLocator.registerRestCallbackHandler(this);
            this.client = serviceLocator;
        }
        return this.client;
    }
}