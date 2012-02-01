/**
 * 
 */
package de.escidoc.core.client.rest.serviceLocator;

import static de.escidoc.core.common.Precondition.checkNotNull;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.application.missing.MissingMethodParameterException;
import de.escidoc.core.client.exceptions.application.security.AuthenticationException;
import de.escidoc.core.client.exceptions.application.security.AuthorizationException;
import de.escidoc.core.client.interfaces.handler.StatisticDataHandler;

/**
 * @author MVO
 * 
 */
public class StatisticDataRestServiceLocator extends RestServiceMethod implements StatisticDataHandler {

    public static final String PATH = "/statistic/statistic-data";

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.sm.StatisticDataHandler#create(java.lang.String)
     */
    @Override
    public void create(final String xml) throws EscidocException, AuthorizationException, AuthenticationException,
        MissingMethodParameterException {

        checkNotNull(xml);

        put(PATH, xml);
    }

}
