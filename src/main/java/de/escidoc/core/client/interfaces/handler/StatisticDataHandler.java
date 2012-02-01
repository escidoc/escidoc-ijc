/**
 * 
 */
package de.escidoc.core.client.interfaces.handler;

import java.rmi.Remote;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.application.missing.MissingMethodParameterException;
import de.escidoc.core.client.exceptions.application.security.AuthenticationException;
import de.escidoc.core.client.exceptions.application.security.AuthorizationException;

/**
 * @author Marko Voß
 * 
 */
public interface StatisticDataHandler extends Remote {

    /**
     * @param xml
     
     
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws MissingMethodParameterException
     */
    void create(final String xml) throws EscidocException, AuthorizationException, AuthenticationException,
        MissingMethodParameterException;
}
