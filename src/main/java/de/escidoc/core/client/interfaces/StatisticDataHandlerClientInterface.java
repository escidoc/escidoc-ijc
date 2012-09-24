/**
 * 
 */
package de.escidoc.core.client.interfaces;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.base.HandlerService;
import de.escidoc.core.resources.sm.sd.StatisticData;

/**
 * @author MVO
 * 
 */
public interface StatisticDataHandlerClientInterface extends HandlerService {

    /**
     * 
     * @param statisticData
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    void create(final StatisticData statisticData) throws EscidocException, InternalClientException, TransportException;
}
