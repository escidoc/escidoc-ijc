/**
 * 
 */
package de.escidoc.core.client.rest.serviceLocator;

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

    private static final String PATH = "/statistic/statistic-data";
    
    /* (non-Javadoc)
     * @see de.escidoc.core.sm.StatisticDataHandler#create(java.lang.String)
     */
    public void create(String xml) throws RemoteException, SystemException,
        AuthorizationException, AuthenticationException,
        MissingMethodParameterException {
        
        if (xml == null)
            throw new IllegalArgumentException("xml must not be null.");
        
        put(PATH, xml);
    }

}
