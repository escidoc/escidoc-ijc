package de.escidoc.core.client.rest.serviceLocator;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.rmi.RemoteException;
import java.util.HashMap;

import de.escidoc.core.client.interfaces.RoleHandler;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException;
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

    public void delete(final String roleId) throws RemoteException,
        SystemException, RoleNotFoundException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, RoleInUseViolationException {

        del(PATH_ROLE + "/" + roleId);

    }

    public String create(final String roleXml) throws RemoteException,
        UniqueConstraintViolationException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        return put(PATH_ROLE, roleXml);
    }

    public String update(final String roleId, final String roleXml)
        throws RemoteException, UniqueConstraintViolationException,
        OptimisticLockingException, SystemException, RoleNotFoundException,
        MissingAttributeValueException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException, InvalidXmlException {

        return put(PATH_ROLE + "/" + roleId, roleXml);
    }

    public String retrieve(final String roleId) throws RemoteException,
        SystemException, RoleNotFoundException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException {

        return get(PATH_ROLE + "/" + roleId);
    }

    public String retrieveRoles(final String filter) throws RemoteException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException, InvalidXmlException,
        InvalidContentException {

        return post(PATH_ROLE + "s/filter", filter);
    }

    @Deprecated
    public String retrieveRoles(final HashMap filter) throws RemoteException,
        SystemException, MissingMethodParameterException,
        InvalidSearchQueryException, AuthenticationException,
        AuthorizationException {

        return get(PATH_ROLE + "s/", filter);
    }

    public String retrieveRoles(final SearchRetrieveRequestType filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        return get(PATH_ROLE + "s" + getEscidoc12Filter(filter));
    }

    public String retrieveRoles(final ExplainRequestType filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        return get(PATH_ROLE + "s" + getEscidoc12Filter(filter));
    }

}
