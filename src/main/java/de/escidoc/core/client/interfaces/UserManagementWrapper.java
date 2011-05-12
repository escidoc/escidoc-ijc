/**
 * 
 */
package de.escidoc.core.client.interfaces;

import java.rmi.RemoteException;

import de.escidoc.core.common.exceptions.remote.system.SystemException;

/**
 * @author Marko Voß
 * 
 */
public interface UserManagementWrapper
    extends de.escidoc.core.aa.UserManagementWrapper {

    /**
     * @param username
     * @param password
     * @return
     * @throws SystemException
     * @throws RemoteException
     */
    String login(final String username, final String password)
        throws SystemException, RemoteException;
}
