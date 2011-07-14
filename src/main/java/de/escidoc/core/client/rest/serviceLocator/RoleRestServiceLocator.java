package de.escidoc.core.client.rest.serviceLocator;

import static de.escidoc.core.common.Precondition.checkNotNull;
import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;
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
import de.escidoc.core.client.interfaces.handler.RoleHandler;

/**
 * REST Service Connector.
 * 
 * @author SWA
 * 
 */
public class RoleRestServiceLocator extends RestServiceMethod implements RoleHandler {

    public static final String PATH = "/aa/role";

    @Override
    public void delete(final String roleId) throws EscidocException, RoleNotFoundException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, RoleInUseViolationException {

        checkNotNull(roleId);

        del(PATH + "/" + roleId);

    }

    @Override
    public String create(final String roleXml) throws EscidocException, UniqueConstraintViolationException,
        SystemException, MissingMethodParameterException, AuthenticationException, AuthorizationException,
        InvalidXmlException {

        checkNotNull(roleXml);

        return put(PATH, roleXml);
    }

    @Override
    public String update(final String roleId, final String roleXml) throws EscidocException,
        UniqueConstraintViolationException, OptimisticLockingException, SystemException, RoleNotFoundException,
        MissingAttributeValueException, MissingMethodParameterException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        checkNotNull(roleId);
        checkNotNull(roleXml);

        return put(PATH + "/" + roleId, roleXml);
    }

    @Override
    public String retrieve(final String roleId) throws EscidocException, RoleNotFoundException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException {

        checkNotNull(roleId);

        return get(PATH + "/" + roleId);
    }

    @Override
    public String retrieveRoles(final SearchRetrieveRequestType filter) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException {

        checkNotNull(filter);

        return get(PATH + "s" + getEscidoc12Filter(filter));
    }

    @Override
    public String retrieveRoles(final ExplainRequestType filter) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException {

        checkNotNull(filter);

        return get(PATH + "s" + getEscidoc12Filter(filter));
    }
}