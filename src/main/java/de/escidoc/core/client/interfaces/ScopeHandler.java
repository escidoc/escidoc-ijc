/**
 * 
 */
package de.escidoc.core.client.interfaces;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.rmi.RemoteException;

import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException;
import de.escidoc.core.common.exceptions.remote.system.SystemException;

/**
 * @author MVO
 * 
 */
public interface ScopeHandler extends de.escidoc.core.sm.ScopeHandler {

    /**
     * 
     * @param request
     * @return
     * @throws RemoteException
     * @throws SystemException
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws InvalidXmlException
     * @throws MissingMethodParameterException
     */
    public String retrieveScopes(SearchRetrieveRequestType request) throws RemoteException, SystemException,
        AuthorizationException, AuthenticationException, InvalidXmlException, MissingMethodParameterException;

    /**
     * 
     * @param request
     * @return
     * @throws RemoteException
     * @throws SystemException
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws InvalidXmlException
     * @throws MissingMethodParameterException
     */
    public String retrieveScopes(ExplainRequestType request) throws RemoteException, SystemException,
        AuthorizationException, AuthenticationException, InvalidXmlException, MissingMethodParameterException;
}
