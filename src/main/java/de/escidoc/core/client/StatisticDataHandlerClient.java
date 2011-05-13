/**
 * 
 */
package de.escidoc.core.client;

import static de.escidoc.core.common.Precondition.checkNotNull;

import java.net.URL;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.StatisticDataHandlerClientInterface;
import de.escidoc.core.client.rest.RestStatisticDataHandlerClient;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.sm.sd.StatisticData;

/**
 * @author MVO
 * 
 */
public class StatisticDataHandlerClient extends AbstractHandlerClient<RestStatisticDataHandlerClient>
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
     * @param serviceAddress
     * @deprecated Use
     *             {@link StatisticDataHandlerClient#StatisticDataHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public StatisticDataHandlerClient(final String serviceAddress) {
        super(serviceAddress);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.StatisticDataHandlerClientInterface
     * #create(de.escidoc.core.resources.sm.sd.StatisticData)
     */
    @Override
    public void create(final StatisticData statisticData) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(statisticData);

        String xml = MarshallerFactory.getInstance().getMarshaller(StatisticData.class).marshalDocument(statisticData);

        getClient().create(xml);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.AbstractHandlerClient#getRestHandlerClientInstance
     * ()
     */
    @Override
    protected RestStatisticDataHandlerClient getRestHandlerClientInstance() throws InternalClientException {
        return new RestStatisticDataHandlerClient(getServiceAddress());
    }
}