/**
 * 
 */
package de.escidoc.core.client.interfaces.handler;

import java.rmi.Remote;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.application.invalid.XmlCorruptedException;
import de.escidoc.core.client.exceptions.application.invalid.XmlSchemaValidationException;
import de.escidoc.core.client.exceptions.application.missing.MissingMethodParameterException;
import de.escidoc.core.client.exceptions.application.security.AuthenticationException;
import de.escidoc.core.client.exceptions.application.security.AuthorizationException;
import de.escidoc.core.client.exceptions.system.SystemException;

/**
 * @author Marko Voß
 * 
 */
public interface PreprocessingHandler extends Remote {

    /**
     * @param aggregationDefinitionId
     * @param xmlData
     * @throws EscidocException
     * @throws XmlSchemaValidationException
     * @throws SystemException
     * @throws XmlCorruptedException
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws MissingMethodParameterException
     */
    void preprocess(final String aggregationDefinitionId, final String xmlData) throws EscidocException,
        XmlSchemaValidationException, SystemException, XmlCorruptedException, AuthorizationException,
        AuthenticationException, MissingMethodParameterException;
}
