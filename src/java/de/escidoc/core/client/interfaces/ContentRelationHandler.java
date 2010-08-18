package de.escidoc.core.client.interfaces;

import java.rmi.RemoteException;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException;
import de.escidoc.core.common.exceptions.remote.system.SystemException;

/**
 * Extend the ContentRelationHandler with methods with additional parameter
 * types.
 * 
 * @author SWA
 * 
 */
public interface ContentRelationHandler
    extends de.escidoc.core.om.ContentRelationHandler {

    String retrieveContentRelations(final SearchRetrieveRequestType filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, InvalidXmlException;

    String retrieveContentRelations(final ExplainRequestType filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, InvalidXmlException;

}