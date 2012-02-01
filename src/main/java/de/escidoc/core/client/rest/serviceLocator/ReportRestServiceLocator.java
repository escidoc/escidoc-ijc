/**
 * 
 */
package de.escidoc.core.client.rest.serviceLocator;

import static de.escidoc.core.common.Precondition.checkNotNull;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.application.invalid.InvalidSqlException;
import de.escidoc.core.client.exceptions.application.invalid.InvalidXmlException;
import de.escidoc.core.client.exceptions.application.missing.MissingMethodParameterException;
import de.escidoc.core.client.exceptions.application.notfound.ReportDefinitionNotFoundException;
import de.escidoc.core.client.exceptions.application.security.AuthenticationException;
import de.escidoc.core.client.exceptions.application.security.AuthorizationException;
import de.escidoc.core.client.interfaces.handler.ReportHandler;

/**
 * @author MVO
 * 
 */
public class ReportRestServiceLocator extends RestServiceMethod implements ReportHandler {

    private static final String PATH = "/statistic/report";

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.sm.ReportHandler#retrieve(java.lang.String)
     */
    @Override
    public String retrieve(final String xml) throws EscidocException, InvalidSqlException, AuthorizationException,
        AuthenticationException, ReportDefinitionNotFoundException, InvalidXmlException,
        MissingMethodParameterException {

        checkNotNull(xml);

        return post(PATH, xml);
    }
}