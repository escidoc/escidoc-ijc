package de.escidoc.core.client.rest.serviceLocator;

import static de.escidoc.core.common.Precondition.checkNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    public static final String PATH = "/st/staging-file";

    @Override
    public String upload(final File f) throws RemoteException,
        AuthenticationException, AuthorizationException, FileNotFoundException,
        IOException {

        checkNotNull(f);

        return put(PATH, f);
    }

    @Override
    public String upload(final InputStream ins) throws RemoteException,
        AuthenticationException, AuthorizationException, FileNotFoundException {

        checkNotNull(ins);

        return put(PATH, ins);
    }

}
