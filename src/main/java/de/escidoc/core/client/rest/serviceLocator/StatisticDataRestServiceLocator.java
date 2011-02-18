/**
 * 
 */
package de.escidoc.core.client.rest.serviceLocator;

import static de.escidoc.core.common.Precondition.checkNotNull;

import java.rmi.RemoteException;

import de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException;
import de.escidoc.core.common.exceptions.remote.system.SystemException;
import de.escidoc.core.sm.StatisticDataHandler;

/**
 * @author MVO
 * 
 */
public class StatisticDataRestServiceLocator extends RestServiceMethod
    implements StatisticDataHandler {

    public static final String PATH = "/statistic/statistic-data";

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.sm.StatisticDataHandler#create(java.lang.String)
     */
    @Override
    public void create(final String xml) throws RemoteException,
        SystemException, AuthorizationException, AuthenticationException,
        MissingMethodParameterException {

        checkNotNull(xml);

        put(PATH, xml);
    }

}
