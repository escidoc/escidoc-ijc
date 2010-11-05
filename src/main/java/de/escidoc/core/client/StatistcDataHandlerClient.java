/**
 * 
 */
package de.escidoc.core.client;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.rest.RestStatisticDataHandlerClient;
import de.escidoc.core.client.soap.SoapStatisticDataHandlerClient;

/**
 * @author MVO
 * 
 */
public class StatistcDataHandlerClient
    extends
    AbstractHandlerClient<SoapStatisticDataHandlerClient, RestStatisticDataHandlerClient> {

    /**
     * 
     */
    public StatistcDataHandlerClient() {
        super();
    }

    /**
     * 
     * @param serviceAddress
     */
    public StatistcDataHandlerClient(final String serviceAddress) {
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
