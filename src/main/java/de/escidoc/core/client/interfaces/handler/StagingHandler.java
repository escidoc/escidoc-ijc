package de.escidoc.core.client.interfaces.handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.Remote;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.application.security.AuthenticationException;
import de.escidoc.core.client.exceptions.application.security.AuthorizationException;
import de.escidoc.core.client.exceptions.system.SystemException;

/**
 * Staging Handler Interface.
 * 
 * @author SWA
 * 
 */
public interface StagingHandler extends Remote {

    /**
     * Uploading local file to Staging Service.
     * 
     * @param file
     *            Local file
     * @return framework response
     * 
     * 
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws FileNotFoundException
     * @throws IOException
     *             Thrown if handling of file stream failed
     */
    String upload(File file) throws EscidocException, AuthenticationException, AuthorizationException,
        FileNotFoundException, IOException;

    /**
     * Uploading stream to Staging Service.
     * 
     * @param ins
     *            Input Stream
     * @return framework response
     * 
     * 
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws FileNotFoundException
     */
    String upload(InputStream ins) throws EscidocException, AuthenticationException, AuthorizationException,
        FileNotFoundException, SystemException;

}
