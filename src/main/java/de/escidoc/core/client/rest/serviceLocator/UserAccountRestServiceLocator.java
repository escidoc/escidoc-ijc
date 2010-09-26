package de.escidoc.core.client.rest.serviceLocator;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import de.escidoc.core.client.interfaces.UserAccountHandler;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException;
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
public class UserAccountRestServiceLocator extends RestServiceMethod
    implements UserAccountHandler {

    private static final String PATH_USER_ACCOUNT = "/aa/user-account";

    public void delete(final String accountId) throws RemoteException,
        UserAccountNotFoundException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException {

        del(PATH_USER_ACCOUNT + "/" + accountId);
    }

    public String create(final String userAccountXml) throws RemoteException,
        UniqueConstraintViolationException, SystemException,
        OrganizationalUnitNotFoundException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        return put(PATH_USER_ACCOUNT, userAccountXml);
    }

    public String update(final String accountId, final String userAccountXml)
        throws RemoteException, UserAccountNotFoundException,
        UniqueConstraintViolationException, OptimisticLockingException,
        SystemException, OrganizationalUnitNotFoundException,
        MissingMethodParameterException, MissingAttributeValueException,
        InvalidStatusException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        return put(PATH_USER_ACCOUNT + "/" + accountId, userAccountXml);
    }

    public String retrieve(final String accountId) throws RemoteException,
        UserAccountNotFoundException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException {

        return get(PATH_USER_ACCOUNT + "/" + accountId);
    }

    public void activate(final String accountId, final String taskParam)
        throws RemoteException, UserAccountNotFoundException,
        OptimisticLockingException, SystemException, AlreadyActiveException,
        MissingMethodParameterException, MissingAttributeValueException,
        AuthenticationException, AuthorizationException, InvalidXmlException {

        post(PATH_USER_ACCOUNT + "/" + accountId + "/activate", taskParam);
    }

    public void deactivate(final String accountId, final String taskParam)
        throws RemoteException, UserAccountNotFoundException,
        OptimisticLockingException, SystemException,
        MissingMethodParameterException, MissingAttributeValueException,
        AuthenticationException, AlreadyDeactiveException,
        AuthorizationException, InvalidXmlException {

        post(PATH_USER_ACCOUNT + "/" + accountId + "/deactivate", taskParam);
    }

    public void updatePassword(final String accountId, final String taskParam)
        throws RemoteException, UserAccountNotFoundException,
        OptimisticLockingException, SystemException,
        MissingMethodParameterException, InvalidStatusException,
        AuthenticationException, AuthorizationException, InvalidXmlException {

        post(PATH_USER_ACCOUNT + "/" + accountId + "/update-password",
            taskParam);
    }

    public String retrieveCurrentGrants(final String accountId)
        throws RemoteException, UserAccountNotFoundException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException {

        return get(PATH_USER_ACCOUNT + "/" + accountId
            + "/resources/current-grants");
    }

    public String retrieveGrant(final String accountId, final String grantId)
        throws RemoteException, UserAccountNotFoundException, SystemException,
        MissingMethodParameterException, GrantNotFoundException,
        AuthenticationException, AuthorizationException {

        return get(PATH_USER_ACCOUNT + "/" + accountId
            + "/resources/grants/grant/" + grantId);
    }

    public String createGrant(final String objid, final String userAccountXml)
        throws RemoteException, UserAccountNotFoundException, SystemException,
        RoleNotFoundException, MissingMethodParameterException,
        AlreadyExistsException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        return put(PATH_USER_ACCOUNT + "/" + objid + "/resources/grants/grant",
            userAccountXml);
    }

    public void revokeGrant(
        final String accountId, final String grantId, final String taskParam)
        throws RemoteException, UserAccountNotFoundException, SystemException,
        MissingAttributeValueException, MissingMethodParameterException,
        GrantNotFoundException, AuthenticationException,
        AlreadyRevokedException, AuthorizationException, InvalidXmlException {

        post(PATH_USER_ACCOUNT + "/" + accountId + "/resources/grants/grant/"
            + grantId + "/revoke-grant", taskParam);
    }

    public void revokeGrants(final String accountId, final String taskParam)
        throws RemoteException, UserAccountNotFoundException, SystemException,
        MissingAttributeValueException, MissingMethodParameterException,
        GrantNotFoundException, AuthenticationException,
        AlreadyRevokedException, AuthorizationException, InvalidXmlException {

        post(PATH_USER_ACCOUNT + "/" + accountId
            + "/resources/grants/revoke-grants", taskParam);
    }

    public String retrieveGrants(final String filter) throws RemoteException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException, InvalidXmlException,
        InvalidContentException {

        return post("/aa/grants/filter", filter);
    }

    public String retrieveGrants(final SearchRetrieveRequestType filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        return get("/aa/grants" + getEscidoc12Filter(filter));
    }

    public String retrieveGrants(final ExplainRequestType filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        return get("/aa/grants" + getEscidoc12Filter(filter));
    }

    public String retrieveUserAccounts(final String filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, InvalidXmlException, InvalidContentException {

        return post(PATH_USER_ACCOUNT + "s/filter", filter);
    }

    public String retrievePreferences(final String accountId)
        throws RemoteException, UserAccountNotFoundException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException {

        return get(PATH_USER_ACCOUNT + "/" + accountId
            + "/resources/preferences");
    }

    public String createPreference(final String accountId, final String body)
        throws RemoteException, UserAccountNotFoundException,
        PreferenceNotFoundException, SystemException,
        MissingMethodParameterException, AlreadyExistsException,
        AuthenticationException, AuthorizationException, InvalidXmlException {

        return put(PATH_USER_ACCOUNT + "/" + accountId
            + "/resources/preferences/preference", body);
    }

    public String updatePreferences(final String accountId, final String body)
        throws RemoteException, UserAccountNotFoundException,
        OptimisticLockingException, SystemException,
        MissingMethodParameterException, MissingAttributeValueException,
        AuthenticationException, AuthorizationException, InvalidXmlException {

        return put(PATH_USER_ACCOUNT + "/" + accountId
            + "/resources/preferences", body);
    }

    public String updatePreference(
        final String accountId, final String prefName, final String body)
        throws RemoteException, UserAccountNotFoundException,
        PreferenceNotFoundException, OptimisticLockingException,
        SystemException, MissingMethodParameterException,
        MissingAttributeValueException, AlreadyExistsException,
        AuthenticationException, AuthorizationException, InvalidXmlException {

        return put(PATH_USER_ACCOUNT + "/" + accountId
            + "/resources/preferences/preference/" + prefName, body);
    }

    public String retrievePreference(
        final String accountId, final String prefName) throws RemoteException,
        UserAccountNotFoundException, PreferenceNotFoundException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException {

        return get(PATH_USER_ACCOUNT + "/" + accountId
            + "/resources/preferences/preference/" + prefName);
    }

    public void deletePreference(final String accountId, final String prefName)
        throws RemoteException, UserAccountNotFoundException,
        PreferenceNotFoundException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException {

        del(PATH_USER_ACCOUNT + "/" + accountId
            + "/resources/preferences/preference/" + prefName);
    }

    public String createAttribute(final String accountId, final String body)
        throws RemoteException, UserAccountNotFoundException, SystemException,
        MissingMethodParameterException, AlreadyExistsException,
        AuthenticationException, AuthorizationException, InvalidXmlException {

        return put(PATH_USER_ACCOUNT + "/" + accountId
            + "/resources/attributes/attribute", body);
    }

    public void deleteAttribute(final String accountId, final String attId)
        throws RemoteException, UserAccountNotFoundException,
        UserAttributeNotFoundException, SystemException,
        MissingMethodParameterException, ReadonlyElementViolationException,
        AuthenticationException, AuthorizationException {

        del(PATH_USER_ACCOUNT + "/" + accountId
            + "/resources/attributes/attribute/" + attId);
    }

    public String retrieveAttribute(final String accountId, final String attId)
        throws RemoteException, UserAccountNotFoundException,
        UserAttributeNotFoundException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException {

        return get(PATH_USER_ACCOUNT + "/" + accountId
            + "/resources/attributes/attribute/" + attId);
    }

    public String retrieveAttributes(final String accountId)
        throws RemoteException, UserAccountNotFoundException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException {

        return get(PATH_USER_ACCOUNT + "/" + accountId
            + "/resources/attributes");
    }

    @Deprecated
    public String retrieveGrants(final HashMap filter) throws RemoteException,
        SystemException, MissingMethodParameterException,
        InvalidSearchQueryException, AuthenticationException,
        AuthorizationException {

        return get("/aa/grants/filter", (Map<String, String[]>) filter);
    }

    public String retrieveNamedAttributes(
        final String accountId, final String attName) throws RemoteException,
        UserAccountNotFoundException, UserAttributeNotFoundException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException {

        return get(PATH_USER_ACCOUNT + "/" + accountId
            + "/resources/attributes/" + attName);
    }

    @Deprecated
    public String retrieveUserAccounts(final HashMap filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, InvalidSearchQueryException,
        AuthenticationException, AuthorizationException {

        return get(PATH_USER_ACCOUNT + "s/filter",
            (Map<String, String[]>) filter);
    }

    public String retrieveUserAccounts(final SearchRetrieveRequestType filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        return get(PATH_USER_ACCOUNT + "s" + getEscidoc12Filter(filter));
    }

    public String retrieveUserAccounts(final ExplainRequestType filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        return get(PATH_USER_ACCOUNT + "s" + getEscidoc12Filter(filter));
    }

    public String updateAttribute(
        final String accountId, final String attId, final String body)
        throws RemoteException, UserAccountNotFoundException,
        OptimisticLockingException, UserAttributeNotFoundException,
        SystemException, MissingMethodParameterException,
        ReadonlyElementViolationException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        return put(PATH_USER_ACCOUNT + "/" + accountId
            + "/resources/attributes/attribute/" + attId, body);
    }

    public String retrieveCurrentUser() throws RemoteException,
        UserAccountNotFoundException, SystemException, AuthenticationException,
        AuthorizationException {

        return get("/aa/user-account/current");
    }

}
