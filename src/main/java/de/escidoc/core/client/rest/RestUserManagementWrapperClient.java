/**
 * 
 */
package de.escidoc.core.client.rest;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.UserManagementWrapper;
import de.escidoc.core.client.rest.serviceLocator.UserManagementWrapperRestServiceLocator;

/**
 * @author MVO
 * 
 */
public class RestUserManagementWrapperClient extends RestClientBase {

    private static final Logger LOG = LoggerFactory.getLogger(RestUserManagementWrapperClient.class);

    private UserManagementWrapper restClient = null;

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public RestUserManagementWrapperClient(final URL serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     * @deprecated Use
     *             {@link RestUserManagementWrapperClient#RestUserManagementWrapperClient(URL)}
     *             instead.
     */
    @Deprecated
    public RestUserManagementWrapperClient(final String serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void logout() throws EscidocException, InternalClientException, TransportException {
        try {
            getClient().logout();
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
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
            final UserManagementWrapperRestServiceLocator serviceLocator =
                new UserManagementWrapperRestServiceLocator();
            serviceLocator.registerRestCallbackHandler(this);
            serviceLocator.setServiceAddress(getServiceAddress());
            restClient = serviceLocator;
        }
        return restClient;
    }
}