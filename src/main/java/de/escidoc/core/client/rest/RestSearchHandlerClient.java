/**
 * 
 */
package de.escidoc.core.client.rest;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.ScanRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.net.URL;
import java.rmi.Remote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.handler.SearchHandler;
import de.escidoc.core.client.rest.serviceLocator.SearchRestServiceLocator;
import de.escidoc.core.common.configuration.ConfigurationProvider;

/**
 * @author MVO
 * 
 */
public class RestSearchHandlerClient extends RestClientBase {

    private static final Logger LOG = LoggerFactory.getLogger(RestSearchHandlerClient.class.getName());

    private SearchHandler restClient = null;

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public RestSearchHandlerClient(final URL serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param requestType
     * @param database
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String explain(final ExplainRequestType request, final String database) throws EscidocException,
        InternalClientException, TransportException {

        evalRequest(request);

        String result = null;
        try {
            result = getRestClient(database).explain(request);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * 
     * @param requestType
     * @param database
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String search(final SearchRetrieveRequestType request, final String database) throws EscidocException,
        InternalClientException, TransportException {

        evalRequest(request, false);

        String result = null;
        try {
            result = getRestClient(database).search(request);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * 
     * @param requestType
     * @param database
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String scan(final ScanRequestType request, final String database) throws EscidocException,
        InternalClientException, TransportException {

        evalRequest(request, false);

        String result = null;
        try {
            result = getRestClient(database).scan(request);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @param database
     * @return Returns the restClient.
     * @throws InternalClientException
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    public SearchHandler getRestClient(final String database) throws InternalClientException {

        if (restClient == null) {

            final SearchRestServiceLocator serviceLocator = new SearchRestServiceLocator();
            serviceLocator.setServiceAddress(getServiceAddress());

            String db = database;
            if (db == null) {
                db = ConfigurationProvider.getInstance().getProperty(ConfigurationProvider.PROP_SEARCH_DATABASE);
            }

            serviceLocator.setDatabase(db);
            serviceLocator.registerRestCallbackHandler(this);
            restClient = serviceLocator;
        }
        return this.restClient;
    }

    @Override
    @Deprecated
    public Remote getClient() throws InternalClientException {
        throw new InternalClientException("The method is not supported");
    }
}
