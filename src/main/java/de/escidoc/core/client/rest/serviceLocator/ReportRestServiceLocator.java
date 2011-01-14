/**
 * 
 */
package de.escidoc.core.client.rest.serviceLocator;

import static de.escidoc.core.common.Precondition.checkNotNull;

import java.rmi.RemoteException;

import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSqlException;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ReportDefinitionNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException;
import de.escidoc.core.common.exceptions.remote.system.SystemException;
import de.escidoc.core.sm.ReportHandler;

/**
 * @author MVO
 * 
 */
public class ReportRestServiceLocator extends RestServiceMethod
    implements ReportHandler {

    private static String PATH = "/statistic/report";

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.sm.ReportHandler#retrieve(java.lang.String)
     */
    @Override
    public String retrieve(final String xml) throws RemoteException,
        SystemException, InvalidSqlException, AuthorizationException,
        AuthenticationException, ReportDefinitionNotFoundException,
        InvalidXmlException, MissingMethodParameterException {

        checkNotNull(xml);

        return post(PATH, xml);
    }
}