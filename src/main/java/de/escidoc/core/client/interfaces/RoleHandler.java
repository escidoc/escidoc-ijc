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
 * Extend the RoleHandler with methods with additional parameter types.
 * 
 * @author SWA
 * 
 */
public interface RoleHandler extends de.escidoc.core.aa.RoleHandler {

    String retrieveRoles(final SearchRetrieveRequestType filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, InvalidXmlException;

    String retrieveRoles(final ExplainRequestType filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, InvalidXmlException;
}