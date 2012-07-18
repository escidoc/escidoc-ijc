package de.escidoc.core.client.rest.serviceLocator;

import static de.escidoc.core.common.Precondition.checkNotNull;

import java.rmi.RemoteException;

import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ResourceNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.StreamNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException;
import de.escidoc.core.common.exceptions.remote.application.violated.LockingException;
import de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException;
import de.escidoc.core.common.exceptions.remote.system.SystemException;
import de.escidoc.core.om.IngestHandler;

/**
 * REST Service Connector.
 * 
 * @author SWA
 * 
 */
public class IngestRestServiceLocator extends RestServiceMethod implements IngestHandler {

    public static final String PATH = "/ir/ingest";

    @Override
    public String ingest(final String resourceXml) throws RemoteException, OptimisticLockingException, SystemException,
        MissingMethodParameterException, LockingException, InvalidStatusException, AuthenticationException,
        StreamNotFoundException, AuthorizationException, ResourceNotFoundException, InvalidXmlException {

        checkNotNull(resourceXml);

        return put(PATH, resourceXml);
    }
}