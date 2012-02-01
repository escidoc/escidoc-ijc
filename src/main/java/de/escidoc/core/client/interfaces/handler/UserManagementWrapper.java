/**
 * 
 */
package de.escidoc.core.client.interfaces.handler;

import java.rmi.Remote;
import java.rmi.RemoteException;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.application.security.AuthenticationException;
import de.escidoc.core.client.exceptions.system.SystemException;

/**
 * @author Marko Voß
 * 
 */
public interface UserManagementWrapper extends Remote {

    /**
     * @param username
     * @param password
     * @return
     
     
     * @throws AuthenticationException
     */
    String login(final String username, final String password) throws SystemException, RemoteException,
        AuthenticationException;

    /**
     
     
     * @throws AuthenticationException
     */
    void logout() throws EscidocException, AuthenticationException;
}
