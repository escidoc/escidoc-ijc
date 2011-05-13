/**
 * 
 */
package de.escidoc.core.client.rest.serviceLocator;

import java.rmi.RemoteException;

import de.escidoc.core.client.interfaces.UserManagementWrapper;
import de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException;
import de.escidoc.core.common.exceptions.remote.system.SystemException;

/**
 * @author MVO
 * 
 */
public class UserManagementWrapperRestServiceLocator extends RestServiceMethod implements UserManagementWrapper {

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.aa.UserManagementWrapper#logout()
     */
    @Override
    public void logout() throws RemoteException, SystemException, AuthenticationException {
        get("/aa/logout");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.UserManagementWrapper#login(java.lang
     * .String, java.lang.String)
     */
    @Override
    public String login(final String username, final String password) throws SystemException, RemoteException,
        AuthenticationException {
        return authenticate(username, password);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.aa.UserManagementWrapper#initHandleExpiryTimestamp(java
     * .lang.String)
     */
    @Override
    public void initHandleExpiryTimestamp(final String handle) throws RemoteException, SystemException,
        AuthenticationException {

        throw new UnsupportedOperationException("Method should not be supported for client applications.");
    }
}
