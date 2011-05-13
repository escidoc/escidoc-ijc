package de.escidoc.core.client.rest.serviceLocator;

import static de.escidoc.core.common.Precondition.checkNotNull;
import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.rmi.RemoteException;
import java.util.HashMap;

import de.escidoc.core.client.interfaces.UserAccountHandler;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException;
import de.escidoc.core.common.exceptions.remote.application.notfound.GrantNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.OrganizationalUnitNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.PreferenceNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.RoleNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.UserAccountNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.UserAttributeNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException;
import de.escidoc.core.common.exceptions.remote.application.violated.AlreadyActiveException;
import de.escidoc.core.common.exceptions.remote.application.violated.AlreadyDeactiveException;
import de.escidoc.core.common.exceptions.remote.application.violated.AlreadyExistsException;
import de.escidoc.core.common.exceptions.remote.application.violated.AlreadyRevokedException;
import de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException;
import de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyElementViolationException;
import de.escidoc.core.common.exceptions.remote.application.violated.UniqueConstraintViolationException;
import de.escidoc.core.common.exceptions.remote.system.SystemException;

/**
 * REST Service Connector.
 * 
 * @author SWA
 * 
 */
/**
 * @author Marko Voß
 * 
 */
public class UserAccountRestServiceLocator extends RestServiceMethod
    implements UserAccountHandler {

    public static final String PATH = "/aa/user-account";

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.aa.UserAccountHandler#delete(java.lang.String)
     */
    @Override
    public void delete(final String accountId) throws RemoteException,
        UserAccountNotFoundException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException {

        checkNotNull(accountId);

        del(PATH + "/" + accountId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.aa.UserAccountHandler#create(java.lang.String)
     */
    @Override
    public String create(final String userAccountXml) throws RemoteException,
        UniqueConstraintViolationException, SystemException,
        OrganizationalUnitNotFoundException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        checkNotNull(userAccountXml);

        return put(PATH, userAccountXml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.aa.UserAccountHandler#update(java.lang.String,
     * java.lang.String)
     */
    @Override
    public String update(final String accountId, final String userAccountXml)
        throws RemoteException, UserAccountNotFoundException,
        UniqueConstraintViolationException, OptimisticLockingException,
        SystemException, OrganizationalUnitNotFoundException,
        MissingMethodParameterException, MissingAttributeValueException,
        InvalidStatusException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        return put(PATH + "/" + accountId, userAccountXml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.aa.UserAccountHandler#retrieve(java.lang.String)
     */
    @Override
    public String retrieve(final String accountId) throws RemoteException,
        UserAccountNotFoundException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException {

        checkNotNull(accountId);

        return get(PATH + "/" + accountId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.aa.UserAccountHandler#activate(java.lang.String,
     * java.lang.String)
     */
    @Override
    public void activate(final String accountId, final String taskParam)
        throws RemoteException, UserAccountNotFoundException,
        OptimisticLockingException, SystemException, AlreadyActiveException,
        MissingMethodParameterException, MissingAttributeValueException,
        AuthenticationException, AuthorizationException, InvalidXmlException {

        checkNotNull(accountId);

        post(PATH + "/" + accountId + "/activate", taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.aa.UserAccountHandler#deactivate(java.lang.String,
     * java.lang.String)
     */
    @Override
    public void deactivate(final String accountId, final String taskParam)
        throws RemoteException, UserAccountNotFoundException,
        OptimisticLockingException, SystemException,
        MissingMethodParameterException, MissingAttributeValueException,
        AuthenticationException, AlreadyDeactiveException,
        AuthorizationException, InvalidXmlException {

        checkNotNull(accountId);

        post(PATH + "/" + accountId + "/deactivate", taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.aa.UserAccountHandler#updatePassword(java.lang.String,
     * java.lang.String)
     */
    @Override
    public void updatePassword(final String accountId, final String taskParam)
        throws RemoteException, UserAccountNotFoundException,
        OptimisticLockingException, SystemException,
        MissingMethodParameterException, InvalidStatusException,
        AuthenticationException, AuthorizationException, InvalidXmlException {

        checkNotNull(accountId);

        post(PATH + "/" + accountId + "/update-password", taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.aa.UserAccountHandler#retrieveCurrentGrants(java.lang
     * .String)
     */
    @Override
    public String retrieveCurrentGrants(final String accountId)
        throws RemoteException, UserAccountNotFoundException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException {

        checkNotNull(accountId);

        return get(PATH + "/" + accountId + "/resources/current-grants");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.aa.UserAccountHandler#retrieveGrant(java.lang.String,
     * java.lang.String)
     */
    @Override
    public String retrieveGrant(final String accountId, final String grantId)
        throws RemoteException, UserAccountNotFoundException, SystemException,
        MissingMethodParameterException, GrantNotFoundException,
        AuthenticationException, AuthorizationException {

        checkNotNull(accountId);
        checkNotNull(grantId);

        return get(PATH + "/" + accountId + "/resources/grants/grant/"
            + grantId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.aa.UserAccountHandler#createGrant(java.lang.String,
     * java.lang.String)
     */
    @Override
    public String createGrant(final String objid, final String userAccountXml)
        throws RemoteException, UserAccountNotFoundException, SystemException,
        RoleNotFoundException, MissingMethodParameterException,
        AlreadyExistsException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        checkNotNull(objid);

        return put(PATH + "/" + objid + "/resources/grants/grant",
            userAccountXml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.aa.UserAccountHandler#revokeGrant(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    @Override
    public void revokeGrant(
        final String accountId, final String grantId, final String taskParam)
        throws RemoteException, UserAccountNotFoundException, SystemException,
        MissingAttributeValueException, MissingMethodParameterException,
        GrantNotFoundException, AuthenticationException,
        AlreadyRevokedException, AuthorizationException, InvalidXmlException {

        checkNotNull(accountId);
        checkNotNull(grantId);

        post(PATH + "/" + accountId + "/resources/grants/grant/" + grantId
            + "/revoke-grant", taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.aa.UserAccountHandler#revokeGrants(java.lang.String,
     * java.lang.String)
     */
    @Override
    public void revokeGrants(final String accountId, final String taskParam)
        throws RemoteException, UserAccountNotFoundException, SystemException,
        MissingAttributeValueException, MissingMethodParameterException,
        GrantNotFoundException, AuthenticationException,
        AlreadyRevokedException, AuthorizationException, InvalidXmlException {

        checkNotNull(accountId);

        post(PATH + "/" + accountId + "/resources/grants/revoke-grants",
            taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.UserAccountHandler#retrieveGrants(gov
     * .loc.www.zing.srw.SearchRetrieveRequestType)
     */
    @Override
    public String retrieveGrants(final SearchRetrieveRequestType filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        checkNotNull(filter);

        return get("/aa/grants" + getEscidoc12Filter(filter));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.UserAccountHandler#retrieveGrants(gov
     * .loc.www.zing.srw.ExplainRequestType)
     */
    @Override
    public String retrieveGrants(final ExplainRequestType filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        checkNotNull(filter);

        return get("/aa/grants" + getEscidoc12Filter(filter));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.aa.UserAccountHandler#retrievePreferences(java.lang.String
     * )
     */
    @Override
    public String retrievePreferences(final String accountId)
        throws RemoteException, UserAccountNotFoundException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException {

        checkNotNull(accountId);

        return get(PATH + "/" + accountId + "/resources/preferences");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.aa.UserAccountHandler#createPreference(java.lang.String,
     * java.lang.String)
     */
    @Override
    public String createPreference(final String accountId, final String body)
        throws RemoteException, UserAccountNotFoundException,
        PreferenceNotFoundException, SystemException,
        MissingMethodParameterException, AlreadyExistsException,
        AuthenticationException, AuthorizationException, InvalidXmlException {

        checkNotNull(accountId);

        return put(
            PATH + "/" + accountId + "/resources/preferences/preference", body);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.aa.UserAccountHandler#updatePreferences(java.lang.String,
     * java.lang.String)
     */
    @Override
    public String updatePreferences(final String accountId, final String body)
        throws RemoteException, UserAccountNotFoundException,
        OptimisticLockingException, SystemException,
        MissingMethodParameterException, MissingAttributeValueException,
        AuthenticationException, AuthorizationException, InvalidXmlException {

        checkNotNull(accountId);

        return put(PATH + "/" + accountId + "/resources/preferences", body);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.aa.UserAccountHandler#updatePreference(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    @Override
    public String updatePreference(
        final String accountId, final String prefName, final String body)
        throws RemoteException, UserAccountNotFoundException,
        PreferenceNotFoundException, OptimisticLockingException,
        SystemException, MissingMethodParameterException,
        MissingAttributeValueException, AlreadyExistsException,
        AuthenticationException, AuthorizationException, InvalidXmlException {

        checkNotNull(accountId);

        return put(PATH + "/" + accountId
            + "/resources/preferences/preference/" + prefName, body);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.aa.UserAccountHandler#retrievePreference(java.lang.String
     * , java.lang.String)
     */
    @Override
    public String retrievePreference(
        final String accountId, final String prefName) throws RemoteException,
        UserAccountNotFoundException, PreferenceNotFoundException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException {

        checkNotNull(accountId);

        return get(PATH + "/" + accountId
            + "/resources/preferences/preference/" + prefName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.aa.UserAccountHandler#deletePreference(java.lang.String,
     * java.lang.String)
     */
    @Override
    public void deletePreference(final String accountId, final String prefName)
        throws RemoteException, UserAccountNotFoundException,
        PreferenceNotFoundException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException {

        checkNotNull(accountId);

        del(PATH + "/" + accountId + "/resources/preferences/preference/"
            + prefName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.aa.UserAccountHandler#createAttribute(java.lang.String,
     * java.lang.String)
     */
    @Override
    public String createAttribute(final String accountId, final String body)
        throws RemoteException, UserAccountNotFoundException, SystemException,
        MissingMethodParameterException, AlreadyExistsException,
        AuthenticationException, AuthorizationException, InvalidXmlException {

        checkNotNull(accountId);

        return put(PATH + "/" + accountId + "/resources/attributes/attribute",
            body);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.aa.UserAccountHandler#deleteAttribute(java.lang.String,
     * java.lang.String)
     */
    @Override
    public void deleteAttribute(final String accountId, final String attId)
        throws RemoteException, UserAccountNotFoundException,
        UserAttributeNotFoundException, SystemException,
        MissingMethodParameterException, ReadonlyElementViolationException,
        AuthenticationException, AuthorizationException {

        checkNotNull(accountId);
        checkNotNull(attId);

        del(PATH + "/" + accountId + "/resources/attributes/attribute/" + attId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.aa.UserAccountHandler#retrieveAttribute(java.lang.String,
     * java.lang.String)
     */
    @Override
    public String retrieveAttribute(final String accountId, final String attId)
        throws RemoteException, UserAccountNotFoundException,
        UserAttributeNotFoundException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException {

        checkNotNull(accountId);
        checkNotNull(attId);

        return get(PATH + "/" + accountId + "/resources/attributes/attribute/"
            + attId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.aa.UserAccountHandler#retrieveAttributes(java.lang.String
     * )
     */
    @Override
    public String retrieveAttributes(final String accountId)
        throws RemoteException, UserAccountNotFoundException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException {

        checkNotNull(accountId);

        return get(PATH + "/" + accountId + "/resources/attributes");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.aa.UserAccountHandler#retrieveGrants(java.util.HashMap)
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    @Deprecated
    public String retrieveGrants(final HashMap filter) throws RemoteException,
        SystemException, MissingMethodParameterException,
        InvalidSearchQueryException, AuthenticationException,
        AuthorizationException {

        return get("/aa/grants/filter", filter);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.aa.UserAccountHandler#retrieveNamedAttributes(java.lang
     * .String, java.lang.String)
     */
    @Override
    public String retrieveNamedAttributes(
        final String accountId, final String attName) throws RemoteException,
        UserAccountNotFoundException, UserAttributeNotFoundException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException {

        checkNotNull(accountId);
        checkNotNull(attName);

        return get(PATH + "/" + accountId + "/resources/attributes/" + attName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.aa.UserAccountHandler#retrieveUserAccounts(java.util.
     * HashMap)
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    @Deprecated
    public String retrieveUserAccounts(final HashMap filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, InvalidSearchQueryException,
        AuthenticationException, AuthorizationException {

        return get(PATH + "s/filter", filter);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.UserAccountHandler#retrieveUserAccounts
     * (gov.loc.www.zing.srw.SearchRetrieveRequestType)
     */
    @Override
    public String retrieveUserAccounts(final SearchRetrieveRequestType filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        checkNotNull(filter);

        return get(PATH + "s" + getEscidoc12Filter(filter));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.UserAccountHandler#retrieveUserAccounts
     * (gov.loc.www.zing.srw.ExplainRequestType)
     */
    @Override
    public String retrieveUserAccounts(final ExplainRequestType filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        checkNotNull(filter);

        return get(PATH + "s" + getEscidoc12Filter(filter));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.aa.UserAccountHandler#updateAttribute(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    @Override
    public String updateAttribute(
        final String accountId, final String attId, final String body)
        throws RemoteException, UserAccountNotFoundException,
        OptimisticLockingException, UserAttributeNotFoundException,
        SystemException, MissingMethodParameterException,
        ReadonlyElementViolationException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        checkNotNull(accountId);
        checkNotNull(attId);

        return put(PATH + "/" + accountId + "/resources/attributes/attribute/"
            + attId, body);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.aa.UserAccountHandler#retrieveCurrentUser()
     */
    @Override
    public String retrieveCurrentUser() throws RemoteException,
        UserAccountNotFoundException, SystemException, AuthenticationException,
        AuthorizationException {

        return get(PATH + "/current");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.aa.UserAccountHandler#retrievePermissionFilterQuery(java
     * .util.HashMap)
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public String retrievePermissionFilterQuery(final HashMap parameters)
        throws RemoteException, SystemException, InvalidSearchQueryException,
        AuthorizationException, AuthenticationException {

        return get(PATH + "/retrievePermissionFilterQuery", parameters);
    }
}