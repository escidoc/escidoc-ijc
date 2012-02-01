/**
 * 
 */
package de.escidoc.core.client.rest.serviceLocator;

import static de.escidoc.core.common.Precondition.checkNotNull;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.application.invalid.InvalidXmlException;
import de.escidoc.core.client.exceptions.application.missing.MissingMethodParameterException;
import de.escidoc.core.client.exceptions.application.notfound.ResourceNotFoundException;
import de.escidoc.core.client.exceptions.application.security.AuthenticationException;
import de.escidoc.core.client.exceptions.application.security.AuthorizationException;
import de.escidoc.core.client.interfaces.handler.PolicyDecisionPointHandler;

/**
 * @author MVO
 * 
 */
public class PolicyDecisionPointRestServiceLocator extends RestServiceMethod implements PolicyDecisionPointHandler {

    public static final String PATH = "/aa/pdp";

    @Override
    public String evaluate(final String requestsXml) throws EscidocException, AuthorizationException,
        AuthenticationException, ResourceNotFoundException, InvalidXmlException, MissingMethodParameterException {

        checkNotNull(requestsXml);

        return put(PATH, requestsXml);
    }
}