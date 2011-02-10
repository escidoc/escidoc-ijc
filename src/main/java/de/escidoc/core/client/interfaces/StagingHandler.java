package de.escidoc.core.client.interfaces;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;

import de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException;

/**
 * Staging Handler Interface.
 * 
 * @author SWA
 * 
 */
public interface StagingHandler extends java.rmi.Remote {

    /**
     * Uploading local file to Staging Service.
     * 
     * @param file
     *            Local file
     * @return framework response
     * 
     * @throws RemoteException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws FileNotFoundException
     * @throws IOException
     *             Thrown if handling of file stream failed
     */
    String upload(File file) throws RemoteException, AuthenticationException,
        AuthorizationException;

    /**
     * Uploading stream to Staging Service.
     * 
     * @param ins
     *            Input Stream
     * @return framework response
     * 
     * @throws RemoteException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws FileNotFoundException
     */
    String upload(InputStream ins) throws RemoteException,
        AuthenticationException, AuthorizationException, FileNotFoundException;

}
