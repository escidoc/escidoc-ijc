/**
 * 
 */
package de.escidoc.core.client.interfaces.handler;

import java.rmi.Remote;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.application.invalid.InvalidXmlException;
import de.escidoc.core.client.exceptions.application.missing.MissingMethodParameterException;
import de.escidoc.core.client.exceptions.application.notfound.ResourceNotFoundException;
import de.escidoc.core.client.exceptions.application.security.AuthenticationException;
import de.escidoc.core.client.exceptions.application.security.AuthorizationException;

/**
 * @author Marko Voß
 * 
 */
public interface PolicyDecisionPointHandler extends Remote {

    /**
     * @param requestsXml
     * @return
     
     
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws ResourceNotFoundException
     * @throws InvalidXmlException
     * @throws MissingMethodParameterException
     */
    String evaluate(final String requestsXml) throws EscidocException, AuthorizationException, AuthenticationException,
        ResourceNotFoundException, InvalidXmlException, MissingMethodParameterException;

    // void touch() throws EscidocException, SystemException;
}
