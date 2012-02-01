/**
 * 
 */
package de.escidoc.core.client.interfaces.handler;

import java.rmi.Remote;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.application.invalid.InvalidSearchQueryException;
import de.escidoc.core.client.exceptions.application.invalid.InvalidXmlException;
import de.escidoc.core.client.exceptions.application.security.AuthenticationException;
import de.escidoc.core.client.exceptions.application.security.AuthorizationException;

/**
 * @author Marko Voß
 * 
 */
public interface AdminHandler extends Remote {

    /**
     * @param taskParam
     * @return
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws InvalidXmlException
     * @throws EscidocException
     */
    String deleteObjects(final String taskParam) throws AuthorizationException, AuthenticationException,
        InvalidXmlException, EscidocException;

    /**
     * @return
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws EscidocException
     */
    String getPurgeStatus() throws EscidocException, AuthorizationException, AuthenticationException;

    /**
     * @return
     
     
     * @throws AuthorizationException
     * @throws AuthenticationException
     */
    String getReindexStatus() throws EscidocException, AuthorizationException, AuthenticationException;

    /**
     * @param clearIndex
     * @param indexNamePrefix
     * @return
     
     
     * @throws InvalidSearchQueryException
     * @throws AuthorizationException
     * @throws AuthenticationException
     */
    String reindex(final String clearIndex, final String indexNamePrefix) throws EscidocException,
        InvalidSearchQueryException, AuthorizationException, AuthenticationException;

    /**
     * @return
     
     
     * @throws AuthorizationException
     * @throws AuthenticationException
     */
    String getRepositoryInfo() throws EscidocException, AuthorizationException, AuthenticationException;

    /**
     * @param exampleSet
     * @return
     
     
     * @throws InvalidSearchQueryException
     * @throws AuthorizationException
     * @throws AuthenticationException
     */
    String loadExamples(final String exampleSet) throws EscidocException, InvalidSearchQueryException,
        AuthorizationException, AuthenticationException;

    /**
     * @return
     
     
     * @throws AuthorizationException
     * @throws AuthenticationException
     */
    String getIndexConfiguration() throws EscidocException, AuthorizationException, AuthenticationException;
}