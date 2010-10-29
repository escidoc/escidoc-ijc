/**
 * 
 */
package de.escidoc.core.client.interfaces;

import java.rmi.RemoteException;

import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException;
import de.escidoc.core.common.exceptions.remote.system.SystemException;
import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

/**
 * @author MVO
 * 
 */
public interface AggregationDefinitionHandler
    extends de.escidoc.core.sm.AggregationDefinitionHandler {

    String retrieveAggregationDefinitions(SearchRetrieveRequestType request)
        throws RemoteException, SystemException, AuthorizationException,
        AuthenticationException, InvalidXmlException,
        MissingMethodParameterException;

    String retrieveAggregationDefinitions(ExplainRequestType request)
        throws RemoteException, SystemException, AuthorizationException,
        AuthenticationException, InvalidXmlException,
        MissingMethodParameterException;
}
