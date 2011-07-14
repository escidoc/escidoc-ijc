/**
 * 
 */
package de.escidoc.core.client.interfaces.handler;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.rmi.Remote;
import java.rmi.RemoteException;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.application.invalid.InvalidContentException;
import de.escidoc.core.client.exceptions.application.invalid.InvalidScopeException;
import de.escidoc.core.client.exceptions.application.invalid.InvalidSearchQueryException;
import de.escidoc.core.client.exceptions.application.invalid.XmlCorruptedException;
import de.escidoc.core.client.exceptions.application.invalid.XmlSchemaValidationException;
import de.escidoc.core.client.exceptions.application.missing.MissingAttributeValueException;
import de.escidoc.core.client.exceptions.application.missing.MissingMethodParameterException;
import de.escidoc.core.client.exceptions.application.notfound.GrantNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.OrganizationalUnitNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.RoleNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.UserAccountNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.UserGroupNotFoundException;
import de.escidoc.core.client.exceptions.application.security.AuthenticationException;
import de.escidoc.core.client.exceptions.application.security.AuthorizationException;
import de.escidoc.core.client.exceptions.application.violated.AlreadyActiveException;
import de.escidoc.core.client.exceptions.application.violated.AlreadyDeactiveException;
import de.escidoc.core.client.exceptions.application.violated.AlreadyExistsException;
import de.escidoc.core.client.exceptions.application.violated.AlreadyRevokedException;
import de.escidoc.core.client.exceptions.application.violated.OptimisticLockingException;
import de.escidoc.core.client.exceptions.application.violated.UniqueConstraintViolationException;
import de.escidoc.core.client.exceptions.application.violated.UserGroupHierarchyViolationException;
import de.escidoc.core.client.exceptions.system.SystemException;

/**
 * @author MVO
 * 
 */
public interface UserGroupHandler extends Remote {

    /**
     * @param request
     * @return
     
     
     * @throws InvalidSearchQueryException
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws MissingMethodParameterException
     */
    String retrieveUserGroups(final SearchRetrieveRequestType request) throws EscidocException,
        InvalidSearchQueryException, AuthorizationException, AuthenticationException, MissingMethodParameterException;

    /**
     * @param request
     * @return
     
     
     * @throws InvalidSearchQueryException
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws MissingMethodParameterException
     */
    String retrieveUserGroups(final ExplainRequestType request) throws EscidocException, InvalidSearchQueryException,
        AuthorizationException, AuthenticationException, MissingMethodParameterException;

    /**
     * @param userGroupId
     * @return
     
     
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws UserGroupNotFoundException
     * @throws MissingMethodParameterException
     */
    String retrieveCurrentGrants(final String userGroupId) throws EscidocException, AuthorizationException,
        AuthenticationException, UserGroupNotFoundException, MissingMethodParameterException;

    /**
     * @param groupId
     * @param grantXML
     * @return
     
     * @throws RoleNotFoundException
     * @throws XmlSchemaValidationException
     
     * @throws XmlCorruptedException
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws AlreadyExistsException
     * @throws UserGroupNotFoundException
     * @throws InvalidScopeException
     * @throws MissingMethodParameterException
     */
    String createGrant(final String groupId, final String grantXML) throws EscidocException, RoleNotFoundException,
        XmlSchemaValidationException, SystemException, XmlCorruptedException, AuthorizationException,
        AuthenticationException, AlreadyExistsException, UserGroupNotFoundException, InvalidScopeException,
        MissingMethodParameterException;

    /**
     * @param groupId
     * @param grantId
     * @return
     
     
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws GrantNotFoundException
     * @throws UserGroupNotFoundException
     * @throws MissingMethodParameterException
     */
    String retrieveGrant(final String groupId, final String grantId) throws EscidocException, AuthorizationException,
        AuthenticationException, GrantNotFoundException, UserGroupNotFoundException, MissingMethodParameterException;

    /**
     * @param groupId
     * @param grantId
     * @param taskParam
     
     * @throws MissingAttributeValueException
     
     * @throws XmlCorruptedException
     * @throws AlreadyRevokedException
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws GrantNotFoundException
     * @throws UserGroupNotFoundException
     * @throws MissingMethodParameterException
     */
    void revokeGrant(final String groupId, final String grantId, final String taskParam) throws EscidocException,
        MissingAttributeValueException, SystemException, XmlCorruptedException, AlreadyRevokedException,
        AuthorizationException, AuthenticationException, GrantNotFoundException, UserGroupNotFoundException,
        MissingMethodParameterException;

    /**
     * @param groupId
     * @param taskParam
     
     * @throws MissingAttributeValueException
     
     * @throws XmlCorruptedException
     * @throws AlreadyRevokedException
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws GrantNotFoundException
     * @throws UserGroupNotFoundException
     * @throws MissingMethodParameterException
     */
    void revokeGrants(final String groupId, final String taskParam) throws EscidocException,
        MissingAttributeValueException, SystemException, XmlCorruptedException, AlreadyRevokedException,
        AuthorizationException, AuthenticationException, GrantNotFoundException, UserGroupNotFoundException,
        MissingMethodParameterException;

    /**
     * @param groupId
     * @param taskParam
     * @return
     
     * @throws InvalidContentException
     
     * @throws OptimisticLockingException
     * @throws XmlSchemaValidationException
     * @throws XmlCorruptedException
     * @throws UserAccountNotFoundException
     * @throws AuthorizationException
     * @throws OrganizationalUnitNotFoundException
     * @throws AuthenticationException
     * @throws UserGroupHierarchyViolationException
     * @throws UserGroupNotFoundException
     * @throws MissingMethodParameterException
     */
    String addSelectors(final String groupId, final String taskParam) throws EscidocException, InvalidContentException,
        SystemException, OptimisticLockingException, XmlSchemaValidationException, XmlCorruptedException,
        UserAccountNotFoundException, AuthorizationException, OrganizationalUnitNotFoundException,
        AuthenticationException, UserGroupHierarchyViolationException, UserGroupNotFoundException,
        MissingMethodParameterException;

    /**
     * @param groupId
     * @param taskParam
     * @return
     
     * @throws XmlSchemaValidationException
     
     * @throws OptimisticLockingException
     * @throws XmlCorruptedException
     * @throws UserAccountNotFoundException
     * @throws AuthorizationException
     * @throws OrganizationalUnitNotFoundException
     * @throws AuthenticationException
     * @throws UserGroupNotFoundException
     * @throws MissingMethodParameterException
     */
    String removeSelectors(final String groupId, final String taskParam) throws EscidocException,
        XmlSchemaValidationException, SystemException, OptimisticLockingException, XmlCorruptedException,
        UserAccountNotFoundException, AuthorizationException, OrganizationalUnitNotFoundException,
        AuthenticationException, UserGroupNotFoundException, MissingMethodParameterException;

    /**
     * @param groupId
     
     
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws UserGroupNotFoundException
     * @throws MissingMethodParameterException
     */
    void delete(final String groupId) throws EscidocException, AuthorizationException, AuthenticationException,
        UserGroupNotFoundException, MissingMethodParameterException;

    /**
     * @param xmlData
     * @return
     
     * @throws XmlSchemaValidationException
     
     * @throws XmlCorruptedException
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws UniqueConstraintViolationException
     * @throws MissingMethodParameterException
     */
    String create(final String xmlData) throws EscidocException, XmlSchemaValidationException, SystemException,
        XmlCorruptedException, AuthorizationException, AuthenticationException, UniqueConstraintViolationException,
        MissingMethodParameterException;

    /**
     * @param groupId
     * @param xmlData
     * @return
     
     * @throws XmlSchemaValidationException
     * @throws MissingAttributeValueException
     * @throws OptimisticLockingException
     
     * @throws XmlCorruptedException
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws UniqueConstraintViolationException
     * @throws UserGroupNotFoundException
     * @throws MissingMethodParameterException
     */
    String update(final String groupId, final String xmlData) throws EscidocException, XmlSchemaValidationException,
        MissingAttributeValueException, OptimisticLockingException, SystemException, XmlCorruptedException,
        AuthorizationException, AuthenticationException, UniqueConstraintViolationException,
        UserGroupNotFoundException, MissingMethodParameterException;

    /**
     * @param groupId
     * @param taskParam
     
     * @throws AlreadyActiveException
     * @throws MissingAttributeValueException
     * @throws OptimisticLockingException
     
     * @throws XmlCorruptedException
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws UserGroupNotFoundException
     * @throws MissingMethodParameterException
     */
    void activate(final String groupId, final String taskParam) throws EscidocException, AlreadyActiveException,
        MissingAttributeValueException, OptimisticLockingException, SystemException, XmlCorruptedException,
        AuthorizationException, AuthenticationException, UserGroupNotFoundException, MissingMethodParameterException;

    /**
     * @param groupId
     * @return
     
     
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws UserGroupNotFoundException
     * @throws MissingMethodParameterException
     */
    String retrieve(final String groupId) throws EscidocException, AuthorizationException, AuthenticationException,
        UserGroupNotFoundException, MissingMethodParameterException;

    /**
     * @param groupId
     * @param taskParam
     
     * @throws AlreadyDeactiveException
     * @throws MissingAttributeValueException
     * @throws OptimisticLockingException
     
     * @throws XmlCorruptedException
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws UserGroupNotFoundException
     * @throws MissingMethodParameterException
     */
    void deactivate(final String groupId, final String taskParam) throws EscidocException, AlreadyDeactiveException,
        MissingAttributeValueException, OptimisticLockingException, SystemException, XmlCorruptedException,
        AuthorizationException, AuthenticationException, UserGroupNotFoundException, MissingMethodParameterException;
}