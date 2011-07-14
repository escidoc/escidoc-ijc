/**
 * 
 */
package de.escidoc.core.client.interfaces.handler;

import java.rmi.Remote;

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

/**
 * @author Marko Voß
 * 
 */
public interface IngestHandler extends Remote {

    /**
     * @param resourceXml
     * @return
     * 
     * @throws OptimisticLockingException
     * @throws MissingMethodParameterException
     * @throws LockingException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws StreamNotFoundException
     * @throws AuthorizationException
     * @throws ResourceNotFoundException
     * @throws InvalidXmlException
     * @throws EscidocException
     */
    String ingest(final String resourceXml) throws EscidocException, OptimisticLockingException,
        MissingMethodParameterException, LockingException, InvalidStatusException, AuthenticationException,
        StreamNotFoundException, AuthorizationException, ResourceNotFoundException, InvalidXmlException;
}
