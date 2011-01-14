/**
 * 
 */
package de.escidoc.core.client.soap;

import java.net.URL;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

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

    private static final Logger LOG = Logger
        .getLogger(SoapStatisticDataHandlerClient.class);

    private StatisticDataHandler client;

    /**
     * 
     * @throws InternalClientException
     */
    public SoapStatisticDataHandlerClient() throws InternalClientException {
        super();
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public SoapStatisticDataHandlerClient(final URL serviceAddress)
        throws InternalClientException {
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
    public SoapStatisticDataHandlerClient(final String serviceAddress)
        throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param xml
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void create(final String xml) throws EscidocException,
        InternalClientException, TransportException {

        if (xml == null)
            throw new IllegalArgumentException("xml must not be null.");

        try {
            getClient().create(xml);
        }
        catch (Exception e) {
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
            StatisticDataHandlerServiceLocator serviceLocator =
                new StatisticDataHandlerServiceLocator(getEngineConfig());
            URL url =
                getHandlerServiceURL(serviceLocator
                    .getStatisticDataHandlerServiceAddress());
            try {
                client = serviceLocator.getStatisticDataHandlerService(url);
            }
            catch (ServiceException e) {
                throw new InternalClientException(e.getMessage(), e);
            }
            registerPWCallback(client);
        }
        return client;
    }
}