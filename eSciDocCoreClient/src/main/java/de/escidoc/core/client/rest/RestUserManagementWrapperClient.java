/**
 * 
 */
package de.escidoc.core.client.rest;

import java.net.MalformedURLException;

import org.joda.time.DateTime;

import de.escidoc.core.aa.UserManagementWrapper;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.rest.serviceLocator.UserManagementWrapperRestServiceLocator;

/**
 * @author MVO
 * 
 */
public class RestUserManagementWrapperClient extends RestClientBase {

    private UserManagementWrapper restClient = null;

    /**
     * 
     * @throws InternalClientException
     */
    public RestUserManagementWrapperClient() throws InternalClientException {
        super();
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public RestUserManagementWrapperClient(final String serviceAddress)
        throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void logout() throws EscidocException, InternalClientException,
        TransportException {
        try {
            getClient().logout();
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
    }

    /**
     * @return Returns the soapClient.
     * @throws InternalClientException
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    @Override
    public UserManagementWrapper getClient() throws InternalClientException {

        if (restClient == null) {
            UserManagementWrapperRestServiceLocator serviceLocator =
                new UserManagementWrapperRestServiceLocator();
            serviceLocator.registerRestCallbackHandler(this);

            try {
                serviceLocator.setServiceAddress(getServiceAddress());
            }
            catch (MalformedURLException e) {
                throw new InternalClientException(e);
            }
            restClient = serviceLocator;
        }
        return restClient;
    }

    /**
     * 
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @Override
    @Deprecated
    public DateTime getLastModificationDate(final String id)
        throws EscidocException, InternalClientException, TransportException {
        return null;
    }
}
