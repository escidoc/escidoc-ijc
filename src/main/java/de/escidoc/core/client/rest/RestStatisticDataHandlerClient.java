/**
 * 
 */
package de.escidoc.core.client.rest;

import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.rest.serviceLocator.ReportDefinitionRestServiceLocator;
import de.escidoc.core.client.rest.serviceLocator.StatisticDataRestServiceLocator;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException;
import de.escidoc.core.common.exceptions.remote.system.SystemException;
import de.escidoc.core.sm.StatisticDataHandler;

/**
 * @author MVO
 * 
 */
public class RestStatisticDataHandlerClient extends RestClientBase {

    private static final Logger LOG =
        Logger.getLogger(RestStatisticDataHandlerClient.class);
    
    private StatisticDataHandler client;

    /**
     * 
     * @throws InternalClientException
     */
    public RestStatisticDataHandlerClient() throws InternalClientException {
        super();
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public RestStatisticDataHandlerClient(final String serviceAddress)
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
    public void create(String xml) throws EscidocException,
        InternalClientException, TransportException {

        if (xml == null)
            throw new IllegalArgumentException("xml must not be null.");

        try {
            getClient().create(xml);
        }
        catch (Exception e) {
            if (LOG.isDebugEnabled())
                LOG.debug(e.getMessage());
            ExceptionMapper.map(e);
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

            StatisticDataRestServiceLocator serviceLocator =
                new StatisticDataRestServiceLocator();
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
    public DateTime getLastModificationDate(String id) throws EscidocException,
        InternalClientException, TransportException {
        throw new UnsupportedOperationException("Method not supported.");
    }

}
