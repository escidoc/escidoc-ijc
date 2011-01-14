package de.escidoc.core.client.rest.serviceLocator;

import static de.escidoc.core.common.Precondition.checkNotNull;
import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.rmi.RemoteException;
import java.util.HashMap;

import de.escidoc.core.client.interfaces.RoleHandler;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException;
import de.escidoc.core.common.exceptions.remote.application.notfound.RoleNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException;
import de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException;
import de.escidoc.core.common.exceptions.remote.application.violated.RoleInUseViolationException;
import de.escidoc.core.common.exceptions.remote.application.violated.UniqueConstraintViolationException;
import de.escidoc.core.common.exceptions.remote.system.SystemException;

/**
 * REST Service Connector.
 * 
 * @author SWA
 * 
 */
public class RoleRestServiceLocator extends RestServiceMethod
    implements RoleHandler {

    private static final String PATH_ROLE = "/aa/role";

    @Override
    public void delete(final String roleId) throws RemoteException,
        SystemException, RoleNotFoundException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, RoleInUseViolationException {

        checkNotNull(roleId);

        del(PATH_ROLE + "/" + roleId);

    }

    @Override
    public String create(final String roleXml) throws RemoteException,
        UniqueConstraintViolationException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        checkNotNull(roleXml);

        return put(PATH_ROLE, roleXml);
    }

    @Override
    public String update(final String roleId, final String roleXml)
        throws RemoteException, UniqueConstraintViolationException,
        OptimisticLockingException, SystemException, RoleNotFoundException,
        MissingAttributeValueException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException, InvalidXmlException {

        checkNotNull(roleId);
        checkNotNull(roleXml);

        return put(PATH_ROLE + "/" + roleId, roleXml);
    }

    @Override
    public String retrieve(final String roleId) throws RemoteException,
        SystemException, RoleNotFoundException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException {

        checkNotNull(roleId);

        return get(PATH_ROLE + "/" + roleId);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    @Deprecated
    public String retrieveRoles(final HashMap filter) throws RemoteException,
        SystemException, MissingMethodParameterException,
        InvalidSearchQueryException, AuthenticationException,
        AuthorizationException {

        return get(PATH_ROLE + "s", filter);
    }

    @Override
    public String retrieveRoles(final SearchRetrieveRequestType filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        checkNotNull(filter);

        return get(PATH_ROLE + "s" + getEscidoc12Filter(filter));
    }

    @Override
    public String retrieveRoles(final ExplainRequestType filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        checkNotNull(filter);

        return get(PATH_ROLE + "s" + getEscidoc12Filter(filter));
    }
}