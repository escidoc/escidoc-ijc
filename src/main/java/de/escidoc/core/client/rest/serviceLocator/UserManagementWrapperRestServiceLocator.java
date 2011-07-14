/**
 * 
 */
package de.escidoc.core.client.rest.serviceLocator;

import java.rmi.RemoteException;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.application.security.AuthenticationException;
import de.escidoc.core.client.exceptions.system.SystemException;
import de.escidoc.core.client.interfaces.handler.UserManagementWrapper;

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
    public void logout() throws EscidocException, AuthenticationException {
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
}
