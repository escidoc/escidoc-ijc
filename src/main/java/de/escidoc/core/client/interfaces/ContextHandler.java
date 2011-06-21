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
 * Extend the ContextHandler with methods with additional parameter types.
 * 
 * @author SWA
 * 
 */
public interface ContextHandler extends de.escidoc.core.om.ContextHandler {

    String retrieveContexts(final SearchRetrieveRequestType filter) throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException;

    String retrieveContexts(final ExplainRequestType filter) throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException;

    String retrieveMembers(final String contextId, final SearchRetrieveRequestType filter) throws RemoteException,
        SystemException, MissingMethodParameterException, AuthenticationException, AuthorizationException,
        InvalidXmlException;

    String retrieveMembers(final String contextId, final ExplainRequestType filter) throws RemoteException,
        SystemException, MissingMethodParameterException, AuthenticationException, AuthorizationException,
        InvalidXmlException;
}