package de.escidoc.core.client.interfaces.handler;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.rmi.Remote;
import java.rmi.RemoteException;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.application.invalid.InvalidSearchQueryException;
import de.escidoc.core.client.exceptions.application.invalid.InvalidXmlException;
import de.escidoc.core.client.exceptions.application.missing.MissingMethodParameterException;
import de.escidoc.core.client.exceptions.application.notfound.ResourceNotFoundException;
import de.escidoc.core.client.exceptions.application.security.AuthenticationException;
import de.escidoc.core.client.exceptions.application.security.AuthorizationException;
import de.escidoc.core.client.exceptions.application.violated.OptimisticLockingException;
import de.escidoc.core.client.exceptions.application.violated.UniqueConstraintViolationException;
import de.escidoc.core.client.exceptions.system.SystemException;

public interface SetDefinitionHandler extends Remote {

    /**
     * @param request
     * @return
     
     
     * @throws InvalidSearchQueryException
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws MissingMethodParameterException
     */
    public String retrieveSetDefinitions(final SearchRetrieveRequestType request) throws EscidocException,
        InvalidSearchQueryException, AuthorizationException, AuthenticationException, MissingMethodParameterException;

    /**
     * @param request
     * @return
     
     
     * @throws InvalidSearchQueryException
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws MissingMethodParameterException
     */
    public String retrieveSetDefinitions(final ExplainRequestType request) throws EscidocException,
        InvalidSearchQueryException, AuthorizationException, AuthenticationException, MissingMethodParameterException;

    /**
     * @param setDefinitionId
     
     
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws ResourceNotFoundException
     * @throws MissingMethodParameterException
     */
    void delete(final String setDefinitionId) throws EscidocException, AuthorizationException, AuthenticationException,
        ResourceNotFoundException, MissingMethodParameterException;

    /**
     * @param xmlData
     * @return
     
     
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws UniqueConstraintViolationException
     * @throws InvalidXmlException
     * @throws MissingMethodParameterException
     */
    String create(final String xmlData) throws EscidocException, AuthorizationException, AuthenticationException,
        UniqueConstraintViolationException, InvalidXmlException, MissingMethodParameterException;

    /**
     * @param setDefinitionId
     * @param xmlData
     * @return
     
     * @throws OptimisticLockingException
     
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws ResourceNotFoundException
     * @throws MissingMethodParameterException
     */
    String update(final String setDefinitionId, final String xmlData) throws EscidocException,
        OptimisticLockingException, SystemException, AuthorizationException, AuthenticationException,
        ResourceNotFoundException, MissingMethodParameterException;

    /**
     * @param setDefinitionId
     * @return
     
     
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws ResourceNotFoundException
     * @throws MissingMethodParameterException
     */
    String retrieve(final String setDefinitionId) throws EscidocException, AuthorizationException,
        AuthenticationException, ResourceNotFoundException, MissingMethodParameterException;
}