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

    /**
     * 
     * @param reportParametersXml
     * @return
     * @throws EscidocException
     * @throws InvalidSqlException
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws ReportDefinitionNotFoundException
     * @throws InvalidXmlException
     * @throws MissingMethodParameterException
     */
    String retrieve(final String reportParametersXml) throws EscidocException, InvalidSqlException,
        AuthorizationException, AuthenticationException, ReportDefinitionNotFoundException, InvalidXmlException,
        MissingMethodParameterException;
}
