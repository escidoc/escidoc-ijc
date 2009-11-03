package de.escidoc.core.client.rest.serviceLocator;

import java.rmi.RemoteException;
import java.util.HashMap;

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
import de.escidoc.core.um.UserAccountHandler;

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

    public String update(final String in0, final String in1)
        throws RemoteException, UserAccountNotFoundException,
        UniqueConstraintViolationException, OptimisticLockingException,
        SystemException, OrganizationalUnitNotFoundException,
        MissingMethodParameterException, MissingAttributeValueException,
        InvalidStatusException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        throw new SystemException(500, "Method not yet supported", "");
    }

    public String retrieve(final String in0) throws RemoteException,
        UserAccountNotFoundException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException {

        throw new SystemException(500, "Method not yet supported", "");
    }

    public void activate(final String in0, final String in1)
        throws RemoteException, UserAccountNotFoundException,
        OptimisticLockingException, SystemException, AlreadyActiveException,
        MissingMethodParameterException, MissingAttributeValueException,
        AuthenticationException, AuthorizationException, InvalidXmlException {

        throw new SystemException(500, "Method not yet supported", "");
    }

    public void deactivate(final String in0, final String in1)
        throws RemoteException, UserAccountNotFoundException,
        OptimisticLockingException, SystemException,
        MissingMethodParameterException, MissingAttributeValueException,
        AuthenticationException, AlreadyDeactiveException,
        AuthorizationException, InvalidXmlException {

        throw new SystemException(500, "Method not yet supported", "");
    }

    public void updatePassword(final String in0, final String in1)
        throws RemoteException, UserAccountNotFoundException,
        OptimisticLockingException, SystemException,
        MissingMethodParameterException, InvalidStatusException,
        AuthenticationException, AuthorizationException, InvalidXmlException {

        throw new SystemException(500, "Method not yet supported", "");
    }

    public String retrieveCurrentGrants(final String in0)
        throws RemoteException, UserAccountNotFoundException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException {

        throw new SystemException(500, "Method not yet supported", "");
    }

    public String retrieveGrant(final String in0, final String in1)
        throws RemoteException, UserAccountNotFoundException, SystemException,
        MissingMethodParameterException, GrantNotFoundException,
        AuthenticationException, AuthorizationException {

        throw new SystemException(500, "Method not yet supported", "");
    }

    public String retrieveGrants(final String in0) throws RemoteException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException, InvalidXmlException,
        InvalidContentException {

        throw new SystemException(500, "Method not yet supported", "");
    }

    public String createGrant(final String objid, final String userAccountXml)
        throws RemoteException, UserAccountNotFoundException, SystemException,
        RoleNotFoundException, MissingMethodParameterException,
        AlreadyExistsException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        return put(PATH_USER_ACCOUNT + "/" + objid + "/resources/grants/grant",
            userAccountXml);
    }

    public void revokeGrant(final String in0, final String in1, final String in2)
        throws RemoteException, UserAccountNotFoundException, SystemException,
        MissingAttributeValueException, MissingMethodParameterException,
        GrantNotFoundException, AuthenticationException,
        AlreadyRevokedException, AuthorizationException, InvalidXmlException {

        throw new SystemException(500, "Method not yet supported", "");
    }

    public void revokeGrants(final String in0, final String in1)
        throws RemoteException, UserAccountNotFoundException, SystemException,
        MissingAttributeValueException, MissingMethodParameterException,
        GrantNotFoundException, AuthenticationException,
        AlreadyRevokedException, AuthorizationException, InvalidXmlException {

        throw new SystemException(500, "Method not yet supported", "");
    }

    public String retrieveUserAccounts(final String in0)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, InvalidXmlException, InvalidContentException {

        throw new SystemException(500, "Method not yet supported", "");
    }

    public String retrievePreferences(final String in0) throws RemoteException,
        UserAccountNotFoundException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException {

        throw new SystemException(500, "Method not yet supported", "");
    }

    public String createPreference(final String in0, final String in1)
        throws RemoteException, UserAccountNotFoundException,
        PreferenceNotFoundException, SystemException,
        MissingMethodParameterException, AlreadyExistsException,
        AuthenticationException, AuthorizationException, InvalidXmlException {

        throw new SystemException(500, "Method not yet supported", "");
    }

    public String updatePreferences(final String in0, final String in1)
        throws RemoteException, UserAccountNotFoundException,
        OptimisticLockingException, SystemException,
        MissingMethodParameterException, MissingAttributeValueException,
        AuthenticationException, AuthorizationException, InvalidXmlException {

        throw new SystemException(500, "Method not yet supported", "");
    }

    public String updatePreference(
        final String in0, final String in1, final String in2)
        throws RemoteException, UserAccountNotFoundException,
        PreferenceNotFoundException, OptimisticLockingException,
        SystemException, MissingMethodParameterException,
        MissingAttributeValueException, AlreadyExistsException,
        AuthenticationException, AuthorizationException, InvalidXmlException {

        throw new SystemException(500, "Method not yet supported", "");
    }

    public String retrievePreference(final String in0, final String in1)
        throws RemoteException, UserAccountNotFoundException,
        PreferenceNotFoundException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException {

        throw new SystemException(500, "Method not yet supported", "");
    }

    public void deletePreference(final String in0, final String in1)
        throws RemoteException, UserAccountNotFoundException,
        PreferenceNotFoundException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException {

        throw new SystemException(500, "Method not yet supported", "");
    }

    public String createAttribute(final String in0, final String in1)
        throws RemoteException, UserAccountNotFoundException, SystemException,
        MissingMethodParameterException, AlreadyExistsException,
        AuthenticationException, AuthorizationException, InvalidXmlException {

        throw new SystemException(500, "Method not yet supported", "");
    }

    public void deleteAttribute(final String in0, final String in1)
        throws RemoteException, UserAccountNotFoundException,
        UserAttributeNotFoundException, SystemException,
        MissingMethodParameterException, ReadonlyElementViolationException,
        AuthenticationException, AuthorizationException {

        throw new SystemException(500, "Method not yet supported", "");
    }

    public String retrieveAttribute(final String in0, final String in1)
        throws RemoteException, UserAccountNotFoundException,
        UserAttributeNotFoundException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException {

        throw new SystemException(500, "Method not yet supported", "");
    }

    public String retrieveAttributes(final String in0) throws RemoteException,
        UserAccountNotFoundException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException {

        throw new SystemException(500, "Method not yet supported", "");
    }

    public String retrieveGrants(final HashMap in0) throws RemoteException,
        SystemException, MissingMethodParameterException,
        InvalidSearchQueryException, AuthenticationException,
        AuthorizationException {

        throw new SystemException(500, "Method not yet supported", "");
    }

    public String retrieveNamedAttributes(final String in0, final String in1)
        throws RemoteException, UserAccountNotFoundException,
        UserAttributeNotFoundException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException {

        throw new SystemException(500, "Method not yet supported", "");
    }

    public String retrieveUserAccounts(final HashMap in0)
        throws RemoteException, SystemException,
        MissingMethodParameterException, InvalidSearchQueryException,
        AuthenticationException, AuthorizationException {

        throw new SystemException(500, "Method not yet supported", "");
    }

    public String updateAttribute(
        final String in0, final String in1, final String in2)
        throws RemoteException, UserAccountNotFoundException,
        OptimisticLockingException, UserAttributeNotFoundException,
        SystemException, MissingMethodParameterException,
        ReadonlyElementViolationException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        throw new SystemException(500, "Method not yet supported", "");
    }
}
