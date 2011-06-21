/**
 * 
 */
package de.escidoc.core.client.rest.serviceLocator;

import static de.escidoc.core.common.Precondition.checkNotNull;
import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.rmi.RemoteException;
import java.util.HashMap;

import de.escidoc.core.client.interfaces.UserGroupHandler;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidScopeException;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException;
import de.escidoc.core.common.exceptions.remote.application.invalid.XmlCorruptedException;
import de.escidoc.core.common.exceptions.remote.application.invalid.XmlSchemaValidationException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException;
import de.escidoc.core.common.exceptions.remote.application.notfound.GrantNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.OrganizationalUnitNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.RoleNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.UserAccountNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.UserGroupNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException;
import de.escidoc.core.common.exceptions.remote.application.violated.AlreadyActiveException;
import de.escidoc.core.common.exceptions.remote.application.violated.AlreadyDeactiveException;
import de.escidoc.core.common.exceptions.remote.application.violated.AlreadyExistsException;
import de.escidoc.core.common.exceptions.remote.application.violated.AlreadyRevokedException;
import de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException;
import de.escidoc.core.common.exceptions.remote.application.violated.UniqueConstraintViolationException;
import de.escidoc.core.common.exceptions.remote.application.violated.UserGroupHierarchyViolationException;
import de.escidoc.core.common.exceptions.remote.system.SystemException;

/**
 * @author MVO
 * 
 */
public class UserGroupRestServiceLocator extends RestServiceMethod implements UserGroupHandler {

    public static final String PATH = "/aa/user-group";

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.aa.UserGroupHandler#retrieveResources(java.lang.String)
     */
    @Override
    public String retrieveResources(final String groupId) throws RemoteException, SystemException,
        UserGroupNotFoundException {
        // TODO
        throw new UnsupportedOperationException("Method retrieveResources not supported.");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.aa.UserGroupHandler#retrieveCurrentGrants(java.lang.String
     * )
     */
    @Override
    public String retrieveCurrentGrants(final String userGroupId) throws RemoteException, SystemException,
        AuthorizationException, AuthenticationException, UserGroupNotFoundException, MissingMethodParameterException {

        checkNotNull(userGroupId);

        return get(PATH + "/" + userGroupId + "/resources/current-grants");
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.aa.UserGroupHandler#createGrant(java.lang.String,
     * java.lang.String)
     */
    @Override
    public String createGrant(final String groupId, final String grantXML) throws RemoteException,
        RoleNotFoundException, XmlSchemaValidationException, SystemException, XmlCorruptedException,
        AuthorizationException, AuthenticationException, AlreadyExistsException, UserGroupNotFoundException,
        InvalidScopeException, MissingMethodParameterException {

        checkNotNull(groupId);
        checkNotNull(grantXML);

        return put(PATH + "/" + groupId + "/resources/grants/grant", grantXML);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.aa.UserGroupHandler#retrieveGrant(java.lang.String,
     * java.lang.String)
     */
    @Override
    public String retrieveGrant(final String groupId, final String grantId) throws RemoteException, SystemException,
        AuthorizationException, AuthenticationException, GrantNotFoundException, UserGroupNotFoundException,
        MissingMethodParameterException {

        checkNotNull(groupId);
        checkNotNull(grantId);
        return get(PATH + "/" + groupId + "/resources/grants/grant/" + grantId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.aa.UserGroupHandler#revokeGrant(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    @Override
    public void revokeGrant(final String groupId, final String grantId, final String taskParam) throws RemoteException,
        MissingAttributeValueException, SystemException, XmlCorruptedException, AlreadyRevokedException,
        AuthorizationException, AuthenticationException, GrantNotFoundException, UserGroupNotFoundException,
        MissingMethodParameterException {

        checkNotNull(groupId);
        checkNotNull(grantId);
        checkNotNull(taskParam);

        post(PATH + "/" + groupId + "/resources/grants/grant/" + grantId + "/revoke-grant", taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.aa.UserGroupHandler#revokeGrants(java.lang.String,
     * java.lang.String)
     */
    @Override
    public void revokeGrants(final String groupId, final String taskParam) throws RemoteException,
        MissingAttributeValueException, SystemException, XmlCorruptedException, AlreadyRevokedException,
        AuthorizationException, AuthenticationException, GrantNotFoundException, UserGroupNotFoundException,
        MissingMethodParameterException {

        checkNotNull(groupId);
        checkNotNull(taskParam);

        post(PATH + "/" + groupId + "/resources/grants/revoke-grants", taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.aa.UserGroupHandler#retrieveUserGroups(java.util.HashMap)
     */
    @SuppressWarnings( { "rawtypes", "unchecked" })
    @Override
    public String retrieveUserGroups(final HashMap filter) throws RemoteException, SystemException,
        InvalidSearchQueryException, AuthorizationException, AuthenticationException, MissingMethodParameterException {

        return get(PATH + "s", filter);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.UserGroupHandler#retrieveUserGroups
     * (gov.loc.www.zing.srw.SearchRetrieveRequestType)
     */
    @Override
    public String retrieveUserGroups(final SearchRetrieveRequestType request) throws RemoteException, SystemException,
        InvalidSearchQueryException, AuthorizationException, AuthenticationException, MissingMethodParameterException {

        checkNotNull(request);

        return get(PATH + "s" + getEscidoc12Filter(request));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.UserGroupHandler#retrieveUserGroups
     * (gov.loc.www.zing.srw.ExplainRequestType)
     */
    @Override
    public String retrieveUserGroups(final ExplainRequestType request) throws RemoteException, SystemException,
        InvalidSearchQueryException, AuthorizationException, AuthenticationException, MissingMethodParameterException {

        checkNotNull(request);

        return get(PATH + "s" + getEscidoc12Filter(request));
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.aa.UserGroupHandler#addSelectors(java.lang.String,
     * java.lang.String)
     */
    @Override
    public String addSelectors(final String groupId, final String taskParam) throws RemoteException,
        InvalidContentException, SystemException, OptimisticLockingException, XmlSchemaValidationException,
        XmlCorruptedException, UserAccountNotFoundException, AuthorizationException,
        OrganizationalUnitNotFoundException, AuthenticationException, UserGroupHierarchyViolationException,
        UserGroupNotFoundException, MissingMethodParameterException {

        checkNotNull(groupId);
        checkNotNull(taskParam);

        return post(PATH + "/" + groupId + "/selectors/add", taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.aa.UserGroupHandler#removeSelectors(java.lang.String,
     * java.lang.String)
     */
    @Override
    public String removeSelectors(final String groupId, final String taskParam) throws RemoteException,
        XmlSchemaValidationException, SystemException, OptimisticLockingException, XmlCorruptedException,
        UserAccountNotFoundException, AuthorizationException, OrganizationalUnitNotFoundException,
        AuthenticationException, UserGroupNotFoundException, MissingMethodParameterException {

        checkNotNull(groupId);
        checkNotNull(taskParam);
        return post(PATH + "/" + groupId + "/selectors/remove", taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.aa.UserGroupHandler#delete(java.lang.String)
     */
    @Override
    public void delete(final String groupId) throws RemoteException, SystemException, AuthorizationException,
        AuthenticationException, UserGroupNotFoundException, MissingMethodParameterException {

        checkNotNull(groupId);

        del(PATH + "/" + groupId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.aa.UserGroupHandler#create(java.lang.String)
     */
    @Override
    public String create(final String xmlData) throws RemoteException, XmlSchemaValidationException, SystemException,
        XmlCorruptedException, AuthorizationException, AuthenticationException, UniqueConstraintViolationException,
        MissingMethodParameterException {

        checkNotNull(xmlData);

        return put(PATH, xmlData);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.aa.UserGroupHandler#update(java.lang.String,
     * java.lang.String)
     */
    @Override
    public String update(final String groupId, final String xmlData) throws RemoteException,
        XmlSchemaValidationException, MissingAttributeValueException, OptimisticLockingException, SystemException,
        XmlCorruptedException, AuthorizationException, AuthenticationException, UniqueConstraintViolationException,
        UserGroupNotFoundException, MissingMethodParameterException {

        checkNotNull(groupId);
        checkNotNull(xmlData);

        return put(PATH + "/" + groupId, xmlData);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.aa.UserGroupHandler#activate(java.lang.String,
     * java.lang.String)
     */
    @Override
    public void activate(final String groupId, final String taskParam) throws RemoteException, AlreadyActiveException,
        MissingAttributeValueException, OptimisticLockingException, SystemException, XmlCorruptedException,
        AuthorizationException, AuthenticationException, UserGroupNotFoundException, MissingMethodParameterException {

        checkNotNull(groupId);
        checkNotNull(taskParam);

        post(PATH + "/" + groupId + "/activate", taskParam);

    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.aa.UserGroupHandler#retrieve(java.lang.String)
     */
    @Override
    public String retrieve(final String groupId) throws RemoteException, SystemException, AuthorizationException,
        AuthenticationException, UserGroupNotFoundException, MissingMethodParameterException {

        checkNotNull(groupId);

        return get(PATH + "/" + groupId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.aa.UserGroupHandler#deactivate(java.lang.String,
     * java.lang.String)
     */
    @Override
    public void deactivate(final String groupId, final String taskParam) throws RemoteException,
        AlreadyDeactiveException, MissingAttributeValueException, OptimisticLockingException, SystemException,
        XmlCorruptedException, AuthorizationException, AuthenticationException, UserGroupNotFoundException,
        MissingMethodParameterException {

        checkNotNull(groupId);
        checkNotNull(taskParam);
        post(PATH + "/" + groupId + "/deactivate", taskParam);
    }
}