/**
 * 
 */
package de.escidoc.core.client.soap;

import java.net.URL;

import javax.xml.rpc.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.sm.StatisticDataHandler;
import de.escidoc.core.sm.StatisticDataHandlerServiceLocator;

/**
 * @author MVO
 * 
 */
public class SoapStatisticDataHandlerClient extends SoapClientBase {

    private static final Logger LOG = LoggerFactory.getLogger(SoapStatisticDataHandlerClient.class);

    private StatisticDataHandler client;

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public SoapStatisticDataHandlerClient(final URL serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     * @deprecated Use
     *             {@link SoapStatisticDataHandlerClient#SoapStatisticDataHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public SoapStatisticDataHandlerClient(final String serviceAddress) throws InternalClientException {
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
        if (client == null) {
            final StatisticDataHandlerServiceLocator serviceLocator =
                new StatisticDataHandlerServiceLocator(getEngineConfig());
            final URL url = getHandlerServiceURL(serviceLocator.getStatisticDataHandlerServiceAddress());
            try {
                client = serviceLocator.getStatisticDataHandlerService(url);
            }
            catch (final ServiceException e) {
                throw new InternalClientException(e.getMessage(), e);
            }
            registerPWCallback(client);
        }
        return client;
    }
}