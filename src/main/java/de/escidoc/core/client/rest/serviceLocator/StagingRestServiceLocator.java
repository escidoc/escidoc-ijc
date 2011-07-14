package de.escidoc.core.client.rest.serviceLocator;

import static de.escidoc.core.common.Precondition.checkNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.application.security.AuthenticationException;
import de.escidoc.core.client.exceptions.application.security.AuthorizationException;
import de.escidoc.core.client.exceptions.system.SystemException;
import de.escidoc.core.client.interfaces.handler.StagingHandler;

/**
 * REST Service Connector.
 * 
 * @author SWA
 * 
 */
public class StagingRestServiceLocator extends RestServiceMethod implements StagingHandler {

    public static final String PATH = "/st/staging-file";

    @Override
    public String upload(final File f) throws EscidocException, AuthenticationException, AuthorizationException,
        FileNotFoundException, IOException, SystemException {

        checkNotNull(f);

        return put(PATH, f);
    }

    @Override
    public String upload(final InputStream ins) throws EscidocException, AuthenticationException,
        AuthorizationException, FileNotFoundException, SystemException {

        checkNotNull(ins);

        return put(PATH, ins);
    }
}