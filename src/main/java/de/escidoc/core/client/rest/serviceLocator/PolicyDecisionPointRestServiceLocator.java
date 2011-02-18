/**
 * 
 */
package de.escidoc.core.client.rest.serviceLocator;

import static de.escidoc.core.common.Precondition.checkNotNull;

import java.rmi.RemoteException;

import de.escidoc.core.aa.PolicyDecisionPoint;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ResourceNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException;
import de.escidoc.core.common.exceptions.remote.system.SystemException;

/**
 * @author MVO
 * 
 */
public class PolicyDecisionPointRestServiceLocator extends RestServiceMethod
    implements PolicyDecisionPoint {

    public static final String PATH = "/aa/pdp";

    @Override
    public String evaluate(final String requestsXml) throws RemoteException,
        SystemException, AuthorizationException, AuthenticationException,
        ResourceNotFoundException, InvalidXmlException,
        MissingMethodParameterException {

        checkNotNull(requestsXml);

        return put(PATH, requestsXml);
    }

    @Override
    public void touch() throws RemoteException, SystemException {
        // do nothing
    }

}
