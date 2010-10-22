/**
 * 
 */
package de.escidoc.core.client.rest;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.ScanRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.net.MalformedURLException;
import java.rmi.Remote;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.SearchHandler;
import de.escidoc.core.client.rest.serviceLocator.SearchRestServiceLocator;
import de.escidoc.core.common.configuration.ConfigurationProvider;

/**
 * @author MVO
 * 
 */
public class RestSearchHandlerClient extends RestClientBase {

    private final Logger logger = Logger
        .getLogger(RestSearchHandlerClient.class.getName());

    private SearchHandler restClient = null;

    /**
     * 
     * @throws InternalClientException
     */
    public RestSearchHandlerClient() throws InternalClientException {
        super();
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public RestSearchHandlerClient(final String serviceAddress)
        throws InternalClientException {
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
    public String explain(
        final ExplainRequestType requestType, final String database)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getRestClient(database).explain(requestType);
        }
        catch (Exception e) {
            logger.debug(e);
            ExceptionMapper.map(e);
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
    public String search(
        final SearchRetrieveRequestType requestType, final String database)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getRestClient(database).search(requestType);
        }
        catch (Exception e) {
            logger.debug(e);
            ExceptionMapper.map(e);
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
    public String scan(final ScanRequestType requestType, final String database)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getRestClient(database).scan(requestType);
        }
        catch (Exception e) {
            logger.debug(e);
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * @param database
     * @return Returns the restClient.
     * @throws InternalClientException
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    public SearchHandler getRestClient(final String database)
        throws InternalClientException {

        if (restClient == null) {

            SearchRestServiceLocator serviceLocator =
                new SearchRestServiceLocator();
            serviceLocator.registerRestCallbackHandler(this);

            try {
                String serviceAddress =
                    "http://"
                        + getConfiguration().getProperty(
                            ConfigurationProvider.PROP_SEARCH_HOST)
                        + ":"
                        + getConfiguration().getProperty(
                            ConfigurationProvider.PROP_SEARCH_PORT);

                if (database == null) {
                    serviceAddress +=
                        getConfiguration().getProperty(
                            ConfigurationProvider.PROP_SEARCH_DATABASE);
                }
                else {
                    serviceAddress += database;
                }

                serviceLocator.setServiceAddress(serviceAddress);
            }
            catch (MalformedURLException e) {
                throw new InternalClientException(e);
            }
            restClient = serviceLocator;
        }
        return this.restClient;
    }

    @Deprecated
    public Remote getClient() throws InternalClientException {
        throw new InternalClientException("The method is not supported");
    }

    @Deprecated
    public DateTime getLastModificationDate(final String id)
        throws EscidocException, InternalClientException, TransportException {

        throw new InternalClientException("The method is not supported");
    }
}
