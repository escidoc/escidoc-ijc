package de.escidoc.core.client.interfaces;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.rmi.RemoteException;

import de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException;

public interface StagingHandler extends java.rmi.Remote {

    String upload(File i) throws RemoteException, AuthenticationException,
        AuthorizationException, FileNotFoundException;

    String upload(InputStream ins) throws RemoteException,
        AuthenticationException, AuthorizationException, FileNotFoundException;

}
