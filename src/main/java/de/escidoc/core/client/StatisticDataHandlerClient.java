/**
 * 
 */
package de.escidoc.core.client;

import java.net.URL;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.StatisticDataHandlerClientInterface;
import de.escidoc.core.client.rest.RestStatisticDataHandlerClient;
import de.escidoc.core.client.soap.SoapStatisticDataHandlerClient;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.sm.sd.StatisticData;

/**
 * @author MVO
 * 
 */
public class StatisticDataHandlerClient
    extends
    AbstractHandlerClient<SoapStatisticDataHandlerClient, RestStatisticDataHandlerClient>
    implements StatisticDataHandlerClientInterface {

    /**
     * 
     */
    public StatisticDataHandlerClient() {
        super();
    }

    /**
     * 
     * @param serviceAddress
     */
    public StatisticDataHandlerClient(final URL serviceAddress) {
        super(serviceAddress);
    }

    /**
     * 
     * @param xml
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @Override
    public void create(final StatisticData statisticData)
        throws EscidocException, InternalClientException, TransportException {

        if (statisticData == null)
            throw new IllegalArgumentException("xml must not be null.");

        String xml =
            MarshallerFactory
                .getInstance(getTransport()).getMarshaller(StatisticData.class)
                .marshalDocument(statisticData);

        if (getTransport() == TransportProtocol.SOAP) {
            getSoapHandlerClient().create(xml);
        }
        else {
            getRestHandlerClient().create(xml);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.AbstractHandlerClient#getSoapHandlerClientInstance
     * ()
     */
    @Override
    protected SoapStatisticDataHandlerClient getSoapHandlerClientInstance()
        throws InternalClientException {
        return new SoapStatisticDataHandlerClient(getServiceAddress());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.AbstractHandlerClient#getRestHandlerClientInstance
     * ()
     */
    @Override
    protected RestStatisticDataHandlerClient getRestHandlerClientInstance()
        throws InternalClientException {
        return new RestStatisticDataHandlerClient(getServiceAddress());
    }

}
