package de.escidoc.core.client.interfaces.handler;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.rmi.Remote;
import java.rmi.RemoteException;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.application.invalid.InvalidStatusException;
import de.escidoc.core.client.exceptions.application.invalid.InvalidXmlException;
import de.escidoc.core.client.exceptions.application.missing.MissingAttributeValueException;
import de.escidoc.core.client.exceptions.application.missing.MissingMethodParameterException;
import de.escidoc.core.client.exceptions.application.notfound.GrantNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.OrganizationalUnitNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.PreferenceNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.RoleNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.UserAccountNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.UserAttributeNotFoundException;
import de.escidoc.core.client.exceptions.application.security.AuthenticationException;
import de.escidoc.core.client.exceptions.application.security.AuthorizationException;
import de.escidoc.core.client.exceptions.application.violated.AlreadyActiveException;
import de.escidoc.core.client.exceptions.application.violated.AlreadyDeactiveException;
import de.escidoc.core.client.exceptions.application.violated.AlreadyExistsException;
import de.escidoc.core.client.exceptions.application.violated.AlreadyRevokedException;
import de.escidoc.core.client.exceptions.application.violated.OptimisticLockingException;
import de.escidoc.core.client.exceptions.application.violated.ReadonlyElementViolationException;
import de.escidoc.core.client.exceptions.application.violated.UniqueConstraintViolationException;
import de.escidoc.core.client.exceptions.system.SystemException;

/**
 * Extend the UserAccountHandler with methods with additional parameter types.
 * 
 * @author SWA
 * 
 */
public interface UserAccountHandler extends Remote {

    /**
     * @param filter
     * @return
     
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String retrieveUserAccounts(final SearchRetrieveRequestType filter) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException;

    /**
     * @param filter
     * @return
     
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String retrieveUserAccounts(final ExplainRequestType filter) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException;

    /**
     * @param filter
     * @return
     
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String retrieveGrants(final SearchRetrieveRequestType filter) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException;

    /**
     * @param filter
     * @return
     
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String retrieveGrants(final ExplainRequestType filter) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException, InvalidXmlException;

    /**
     * @param accountId
     
     * @throws UserAccountNotFoundException
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     */
    void delete(final String accountId) throws EscidocException, UserAccountNotFoundException, SystemException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException;

    /**
     * @param userAccountXml
     * @return
     
     * @throws UniqueConstraintViolationException
     
     * @throws OrganizationalUnitNotFoundException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String create(final String userAccountXml) throws EscidocException, UniqueConstraintViolationException,
        SystemException, OrganizationalUnitNotFoundException, MissingMethodParameterException, InvalidStatusException,
        AuthenticationException, AuthorizationException, InvalidXmlException;

    /**
     * @param accountId
     * @param userAccountXml
     * @return
     
     * @throws UserAccountNotFoundException
     * @throws UniqueConstraintViolationException
     * @throws OptimisticLockingException
     
     * @throws OrganizationalUnitNotFoundException
     * @throws MissingMethodParameterException
     * @throws MissingAttributeValueException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String update(final String accountId, final String userAccountXml) throws EscidocException,
        UserAccountNotFoundException, UniqueConstraintViolationException, OptimisticLockingException, SystemException,
        OrganizationalUnitNotFoundException, MissingMethodParameterException, MissingAttributeValueException,
        InvalidStatusException, AuthenticationException, AuthorizationException, InvalidXmlException;

    /**
     * @param accountId
     * @return
     
     * @throws UserAccountNotFoundException
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     */
    String retrieve(final String accountId) throws EscidocException, UserAccountNotFoundException, SystemException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException;

    /**
     * @param accountId
     * @param taskParam
     
     * @throws UserAccountNotFoundException
     * @throws OptimisticLockingException
     
     * @throws AlreadyActiveException
     * @throws MissingMethodParameterException
     * @throws MissingAttributeValueException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    void activate(final String accountId, final String taskParam) throws EscidocException,
        UserAccountNotFoundException, OptimisticLockingException, SystemException, AlreadyActiveException,
        MissingMethodParameterException, MissingAttributeValueException, AuthenticationException,
        AuthorizationException, InvalidXmlException;

    /**
     * @param accountId
     * @param taskParam
     
     * @throws UserAccountNotFoundException
     * @throws OptimisticLockingException
     
     * @throws MissingMethodParameterException
     * @throws MissingAttributeValueException
     * @throws AuthenticationException
     * @throws AlreadyDeactiveException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    void deactivate(final String accountId, final String taskParam) throws EscidocException,
        UserAccountNotFoundException, OptimisticLockingException, SystemException, MissingMethodParameterException,
        MissingAttributeValueException, AuthenticationException, AlreadyDeactiveException, AuthorizationException,
        InvalidXmlException;

    /**
     * @param accountId
     * @param taskParam
     
     * @throws UserAccountNotFoundException
     * @throws OptimisticLockingException
     
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    void updatePassword(final String accountId, final String taskParam) throws EscidocException,
        UserAccountNotFoundException, OptimisticLockingException, SystemException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException, AuthorizationException, InvalidXmlException;

    /**
     * @param accountId
     * @return
     
     * @throws UserAccountNotFoundException
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     */
    String retrieveCurrentGrants(final String accountId) throws EscidocException, UserAccountNotFoundException,
        SystemException, MissingMethodParameterException, AuthenticationException, AuthorizationException;

    /**
     * @param accountId
     * @param grantId
     * @return
     
     * @throws UserAccountNotFoundException
     
     * @throws MissingMethodParameterException
     * @throws GrantNotFoundException
     * @throws AuthenticationException
     * @throws AuthorizationException
     */
    String retrieveGrant(final String accountId, final String grantId) throws EscidocException,
        UserAccountNotFoundException, SystemException, MissingMethodParameterException, GrantNotFoundException,
        AuthenticationException, AuthorizationException;

    /**
     * @param objid
     * @param userAccountXml
     * @return
     
     * @throws UserAccountNotFoundException
     
     * @throws RoleNotFoundException
     * @throws MissingMethodParameterException
     * @throws AlreadyExistsException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String createGrant(final String objid, final String userAccountXml) throws EscidocException,
        UserAccountNotFoundException, SystemException, RoleNotFoundException, MissingMethodParameterException,
        AlreadyExistsException, AuthenticationException, AuthorizationException, InvalidXmlException;

    /**
     * @param accountId
     * @param grantId
     * @param taskParam
     
     * @throws UserAccountNotFoundException
     
     * @throws MissingAttributeValueException
     * @throws MissingMethodParameterException
     * @throws GrantNotFoundException
     * @throws AuthenticationException
     * @throws AlreadyRevokedException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    void revokeGrant(final String accountId, final String grantId, final String taskParam) throws EscidocException,
        UserAccountNotFoundException, SystemException, MissingAttributeValueException, MissingMethodParameterException,
        GrantNotFoundException, AuthenticationException, AlreadyRevokedException, AuthorizationException,
        InvalidXmlException;

    /**
     * @param accountId
     * @param taskParam
     
     * @throws UserAccountNotFoundException
     
     * @throws MissingAttributeValueException
     * @throws MissingMethodParameterException
     * @throws GrantNotFoundException
     * @throws AuthenticationException
     * @throws AlreadyRevokedException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    void revokeGrants(final String accountId, final String taskParam) throws EscidocException,
        UserAccountNotFoundException, SystemException, MissingAttributeValueException, MissingMethodParameterException,
        GrantNotFoundException, AuthenticationException, AlreadyRevokedException, AuthorizationException,
        InvalidXmlException;

    /**
     * @param accountId
     * @return
     
     * @throws UserAccountNotFoundException
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     */
    String retrievePreferences(final String accountId) throws EscidocException, UserAccountNotFoundException,
        SystemException, MissingMethodParameterException, AuthenticationException, AuthorizationException;

    /**
     * @param accountId
     * @param body
     * @return
     
     * @throws UserAccountNotFoundException
     * @throws PreferenceNotFoundException
     
     * @throws MissingMethodParameterException
     * @throws AlreadyExistsException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String createPreference(final String accountId, final String body) throws EscidocException,
        UserAccountNotFoundException, PreferenceNotFoundException, SystemException, MissingMethodParameterException,
        AlreadyExistsException, AuthenticationException, AuthorizationException, InvalidXmlException;

    /**
     * @param accountId
     * @param body
     * @return
     
     * @throws UserAccountNotFoundException
     * @throws OptimisticLockingException
     
     * @throws MissingMethodParameterException
     * @throws MissingAttributeValueException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String updatePreferences(final String accountId, final String body) throws EscidocException,
        UserAccountNotFoundException, OptimisticLockingException, SystemException, MissingMethodParameterException,
        MissingAttributeValueException, AuthenticationException, AuthorizationException, InvalidXmlException;

    /**
     * @param accountId
     * @param prefName
     * @param body
     * @return
     
     * @throws UserAccountNotFoundException
     * @throws PreferenceNotFoundException
     * @throws OptimisticLockingException
     
     * @throws MissingMethodParameterException
     * @throws MissingAttributeValueException
     * @throws AlreadyExistsException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String updatePreference(final String accountId, final String prefName, final String body) throws EscidocException,
        UserAccountNotFoundException, PreferenceNotFoundException, OptimisticLockingException, SystemException,
        MissingMethodParameterException, MissingAttributeValueException, AlreadyExistsException,
        AuthenticationException, AuthorizationException, InvalidXmlException;

    /**
     * @param accountId
     * @param prefName
     * @return
     
     * @throws UserAccountNotFoundException
     * @throws PreferenceNotFoundException
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     */
    String retrievePreference(final String accountId, final String prefName) throws EscidocException,
        UserAccountNotFoundException, PreferenceNotFoundException, SystemException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException;

    /**
     * @param accountId
     * @param prefName
     
     * @throws UserAccountNotFoundException
     * @throws PreferenceNotFoundException
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     */
    void deletePreference(final String accountId, final String prefName) throws EscidocException,
        UserAccountNotFoundException, PreferenceNotFoundException, SystemException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException;

    /**
     * @param accountId
     * @param body
     * @return
     
     * @throws UserAccountNotFoundException
     
     * @throws MissingMethodParameterException
     * @throws AlreadyExistsException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String createAttribute(final String accountId, final String body) throws EscidocException,
        UserAccountNotFoundException, SystemException, MissingMethodParameterException, AlreadyExistsException,
        AuthenticationException, AuthorizationException, InvalidXmlException;

    /**
     * @param accountId
     * @param attId
     
     * @throws UserAccountNotFoundException
     * @throws UserAttributeNotFoundException
     
     * @throws MissingMethodParameterException
     * @throws ReadonlyElementViolationException
     * @throws AuthenticationException
     * @throws AuthorizationException
     */
    void deleteAttribute(final String accountId, final String attId) throws EscidocException,
        UserAccountNotFoundException, UserAttributeNotFoundException, SystemException, MissingMethodParameterException,
        ReadonlyElementViolationException, AuthenticationException, AuthorizationException;

    /**
     * @param accountId
     * @param attId
     * @return
     
     * @throws UserAccountNotFoundException
     * @throws UserAttributeNotFoundException
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     */
    String retrieveAttribute(final String accountId, final String attId) throws EscidocException,
        UserAccountNotFoundException, UserAttributeNotFoundException, SystemException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException;

    /**
     * @param accountId
     * @return
     
     * @throws UserAccountNotFoundException
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     */
    String retrieveAttributes(final String accountId) throws EscidocException, UserAccountNotFoundException,
        SystemException, MissingMethodParameterException, AuthenticationException, AuthorizationException;

    /**
     * @param accountId
     * @param attName
     * @return
     
     * @throws UserAccountNotFoundException
     * @throws UserAttributeNotFoundException
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     */
    String retrieveNamedAttributes(final String accountId, final String attName) throws EscidocException,
        UserAccountNotFoundException, UserAttributeNotFoundException, SystemException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException;

    /**
     * @param accountId
     * @param attId
     * @param body
     * @return
     
     * @throws UserAccountNotFoundException
     * @throws OptimisticLockingException
     * @throws UserAttributeNotFoundException
     
     * @throws MissingMethodParameterException
     * @throws ReadonlyElementViolationException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String updateAttribute(final String accountId, final String attId, final String body) throws EscidocException,
        UserAccountNotFoundException, OptimisticLockingException, UserAttributeNotFoundException, SystemException,
        MissingMethodParameterException, ReadonlyElementViolationException, AuthenticationException,
        AuthorizationException, InvalidXmlException;

    /**
     * @return
     
     * @throws UserAccountNotFoundException
     
     * @throws AuthenticationException
     * @throws AuthorizationException
     */
    String retrieveCurrentUser() throws EscidocException, UserAccountNotFoundException, SystemException,
        AuthenticationException, AuthorizationException;
}