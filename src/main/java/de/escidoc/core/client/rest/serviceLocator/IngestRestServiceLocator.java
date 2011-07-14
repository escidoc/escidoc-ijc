package de.escidoc.core.client.rest.serviceLocator;

import static de.escidoc.core.common.Precondition.checkNotNull;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.application.invalid.InvalidStatusException;
import de.escidoc.core.client.exceptions.application.invalid.InvalidXmlException;
import de.escidoc.core.client.exceptions.application.missing.MissingMethodParameterException;
import de.escidoc.core.client.exceptions.application.notfound.ResourceNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.StreamNotFoundException;
import de.escidoc.core.client.exceptions.application.security.AuthenticationException;
import de.escidoc.core.client.exceptions.application.security.AuthorizationException;
import de.escidoc.core.client.exceptions.application.violated.LockingException;
import de.escidoc.core.client.exceptions.application.violated.OptimisticLockingException;
import de.escidoc.core.client.interfaces.handler.IngestHandler;

/**
 * REST Service Connector.
 * 
 * @author SWA
 * 
 */
public class IngestRestServiceLocator extends RestServiceMethod implements IngestHandler {

    public static final String PATH = "/ir/ingest";

    @Override
    public String ingest(final String resourceXml) throws EscidocException, OptimisticLockingException,
        MissingMethodParameterException, LockingException, InvalidStatusException, AuthenticationException,
        StreamNotFoundException, AuthorizationException, ResourceNotFoundException, InvalidXmlException {

        checkNotNull(resourceXml);

        return put(PATH, resourceXml);
    }
}
