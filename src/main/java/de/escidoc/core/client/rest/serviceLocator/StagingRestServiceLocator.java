package de.escidoc.core.client.rest.serviceLocator;

import java.io.File;
import java.io.InputStream;
import java.rmi.RemoteException;

import de.escidoc.core.client.interfaces.StagingHandler;
import de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException;

/**
 * REST Service Connector.
 * 
 * @author SWA
 * 
 */
public class StagingRestServiceLocator extends RestServiceMethod
    implements StagingHandler {

    private static final String PATH_STAGING = "/st/staging-file";

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.StagingHandler#upload(java.io.File)
     */
    @Override
    public String upload(final File f) throws RemoteException,
        AuthenticationException, AuthorizationException {

        return put(PATH_STAGING, f);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.StagingHandler#upload(java.io.InputStream
     * )
     */
    @Override
    public String upload(final InputStream ins) throws RemoteException,
        AuthenticationException, AuthorizationException {

        return put(PATH_STAGING, ins);
    }

}
