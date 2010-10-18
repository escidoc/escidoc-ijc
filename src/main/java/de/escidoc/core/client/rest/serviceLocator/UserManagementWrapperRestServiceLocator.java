/**
 * 
 */
package de.escidoc.core.client.rest.serviceLocator;

import java.rmi.RemoteException;

import de.escidoc.core.aa.UserManagementWrapper;
import de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException;
import de.escidoc.core.common.exceptions.remote.system.SystemException;

/**
 * @author MVO
 * 
 */
public class UserManagementWrapperRestServiceLocator extends RestServiceMethod
    implements UserManagementWrapper {

    /* (non-Javadoc)
     * @see de.escidoc.core.aa.UserManagementWrapper#logout()
     */
    public void logout() throws RemoteException, SystemException,
        AuthenticationException {
        get("/aa/logout");
    }

    /* (non-Javadoc)
     * @see de.escidoc.core.aa.UserManagementWrapper#initHandleExpiryTimestamp(java.lang.String)
     */
    public void initHandleExpiryTimestamp(String in0) throws RemoteException,
        SystemException, AuthenticationException {
        // TODO
    }
}
