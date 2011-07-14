package de.escidoc.core.client.interfaces.handler;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.rmi.Remote;
import java.rmi.RemoteException;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.application.invalid.InvalidXmlException;
import de.escidoc.core.client.exceptions.application.missing.MissingAttributeValueException;
import de.escidoc.core.client.exceptions.application.missing.MissingMethodParameterException;
import de.escidoc.core.client.exceptions.application.notfound.RoleNotFoundException;
import de.escidoc.core.client.exceptions.application.security.AuthenticationException;
import de.escidoc.core.client.exceptions.application.security.AuthorizationException;
import de.escidoc.core.client.exceptions.application.violated.OptimisticLockingException;
import de.escidoc.core.client.exceptions.application.violated.RoleInUseViolationException;
import de.escidoc.core.client.exceptions.application.violated.UniqueConstraintViolationException;
import de.escidoc.core.client.exceptions.system.SystemException;

/**
 * Extend the RoleHandler with methods with additional parameter types.
 * 
 * @author SWA
 * 
 */
/**
 * @author Marko Voß
 * 
 */
public interface RoleHandler extends Remote {

    /**
     * @param filter
     * @return
     
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String retrieveRoles(final SearchRetrieveRequestType filter) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException;

    /**
     * @param filter
     * @return
     
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String retrieveRoles(final ExplainRequestType filter) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException, InvalidXmlException;

    /**
     * @param roleId
     
     
     * @throws RoleNotFoundException
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws RoleInUseViolationException
     */
    void delete(final String roleId) throws EscidocException, RoleNotFoundException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException, RoleInUseViolationException;

    /**
     * @param roleXml
     * @return
     
     * @throws UniqueConstraintViolationException
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String create(final String roleXml) throws EscidocException, UniqueConstraintViolationException, SystemException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException;

    /**
     * @param roleId
     * @param roleXml
     * @return
     
     * @throws UniqueConstraintViolationException
     * @throws OptimisticLockingException
     
     * @throws RoleNotFoundException
     * @throws MissingAttributeValueException
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String update(final String roleId, final String roleXml) throws EscidocException,
        UniqueConstraintViolationException, OptimisticLockingException, SystemException, RoleNotFoundException,
        MissingAttributeValueException, MissingMethodParameterException, AuthenticationException,
        AuthorizationException, InvalidXmlException;

    /**
     * @param roleId
     * @return
     
     
     * @throws RoleNotFoundException
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     */
    String retrieve(final String roleId) throws EscidocException, RoleNotFoundException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException;
}