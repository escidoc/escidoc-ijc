/**
 * 
 */
package de.escidoc.core.client.interfaces.handler;

import java.rmi.Remote;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.application.invalid.InvalidSqlException;
import de.escidoc.core.client.exceptions.application.invalid.InvalidXmlException;
import de.escidoc.core.client.exceptions.application.missing.MissingMethodParameterException;
import de.escidoc.core.client.exceptions.application.notfound.ReportDefinitionNotFoundException;
import de.escidoc.core.client.exceptions.application.security.AuthenticationException;
import de.escidoc.core.client.exceptions.application.security.AuthorizationException;

/**
 * @author Marko Voß
 * 
 */
public interface ReportHandler extends Remote {

    String retrieve(final String xml) throws EscidocException, InvalidSqlException, AuthorizationException,
        AuthenticationException, ReportDefinitionNotFoundException, InvalidXmlException,
        MissingMethodParameterException;
}
