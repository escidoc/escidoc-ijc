/**
 * 
 */
package de.escidoc.core.client.rest.serviceLocator;

import static de.escidoc.core.common.Precondition.checkNotNull;
import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;
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
import de.escidoc.core.client.interfaces.handler.UserGroupHandler;

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
     * de.escidoc.core.aa.UserGroupHandler#retrieveCurrentGrants(java.lang.String
     * )
     */
    @Override
    public String retrieveCurrentGrants(final String userGroupId) throws EscidocException, AuthorizationException,
        AuthenticationException, UserGroupNotFoundException, MissingMethodParameterException {

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
    public String createGrant(final String groupId, final String grantXML) throws EscidocException,
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
    public String retrieveGrant(final String groupId, final String grantId) throws EscidocException,
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
    public void revokeGrant(final String groupId, final String grantId, final String taskParam)
        throws EscidocException, MissingAttributeValueException, SystemException, XmlCorruptedException,
        AlreadyRevokedException, AuthorizationException, AuthenticationException, GrantNotFoundException,
        UserGroupNotFoundException, MissingMethodParameterException {

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
    public void revokeGrants(final String groupId, final String taskParam) throws EscidocException,
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
     * de.escidoc.core.client.interfaces.UserGroupHandler#retrieveUserGroups
     * (gov.loc.www.zing.srw.SearchRetrieveRequestType)
     */
    @Override
    public String retrieveUserGroups(final SearchRetrieveRequestType request) throws EscidocException,
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
    public String retrieveUserGroups(final ExplainRequestType request) throws EscidocException,
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
    public String addSelectors(final String groupId, final String taskParam) throws EscidocException,
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
    public String removeSelectors(final String groupId, final String taskParam) throws EscidocException,
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
    public void delete(final String groupId) throws EscidocException, AuthorizationException, AuthenticationException,
        UserGroupNotFoundException, MissingMethodParameterException {

        checkNotNull(groupId);

        del(PATH + "/" + groupId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.aa.UserGroupHandler#create(java.lang.String)
     */
    @Override
    public String create(final String xmlData) throws EscidocException, XmlSchemaValidationException, SystemException,
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
    public String update(final String groupId, final String xmlData) throws EscidocException,
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
    public void activate(final String groupId, final String taskParam) throws EscidocException, AlreadyActiveException,
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
    public String retrieve(final String groupId) throws EscidocException, AuthorizationException,
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
    public void deactivate(final String groupId, final String taskParam) throws EscidocException,
        AlreadyDeactiveException, MissingAttributeValueException, OptimisticLockingException, SystemException,
        XmlCorruptedException, AuthorizationException, AuthenticationException, UserGroupNotFoundException,
        MissingMethodParameterException {

        checkNotNull(groupId);
        checkNotNull(taskParam);
        post(PATH + "/" + groupId + "/deactivate", taskParam);
    }
}