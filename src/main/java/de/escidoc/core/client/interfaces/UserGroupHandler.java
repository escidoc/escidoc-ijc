/**
 * 
 */
package de.escidoc.core.client.interfaces;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.rmi.RemoteException;

import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException;
import de.escidoc.core.common.exceptions.remote.system.SystemException;

/**
 * @author MVO
 * 
 */
public interface UserGroupHandler extends de.escidoc.core.aa.UserGroupHandler {

    /**
     * @param request
     * @return
     * @throws RemoteException
     * @throws SystemException
     * @throws InvalidSearchQueryException
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws MissingMethodParameterException
     */
    String retrieveUserGroups(final SearchRetrieveRequestType request)
        throws RemoteException, SystemException, InvalidSearchQueryException,
        AuthorizationException, AuthenticationException,
        MissingMethodParameterException;

    /**
     * @param request
     * @return
     * @throws RemoteException
     * @throws SystemException
     * @throws InvalidSearchQueryException
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws MissingMethodParameterException
     */
    String retrieveUserGroups(final ExplainRequestType request)
        throws RemoteException, SystemException, InvalidSearchQueryException,
        AuthorizationException, AuthenticationException,
        MissingMethodParameterException;
}
