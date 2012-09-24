/**
 * 
 */
package de.escidoc.core.client.rest.serviceLocator;

import static de.escidoc.core.common.Precondition.checkNotNull;
import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.rmi.RemoteException;
import java.util.HashMap;

import de.escidoc.core.client.interfaces.SetDefinitionHandler;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ResourceNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException;
import de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException;
import de.escidoc.core.common.exceptions.remote.application.violated.UniqueConstraintViolationException;
import de.escidoc.core.common.exceptions.remote.system.SystemException;

/**
 * @author MVO
 * 
 */
public class SetDefinitionRestServiceLocator extends RestServiceMethod implements SetDefinitionHandler {

    public static final String PATH = "/oai/set-definition";

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.oai.SetDefinitionHandler#retrieveSetDefinitions(java.
     * util.HashMap)
     */
    @SuppressWarnings( { "unchecked", "rawtypes" })
    @Override
    public String retrieveSetDefinitions(final HashMap filter) throws RemoteException, SystemException,
        InvalidSearchQueryException, AuthorizationException, AuthenticationException, MissingMethodParameterException {

        return get(PATH + "s", filter);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.SetDefinitionHandler#retrieveSetDefinitions
     * (gov.loc.www.zing.srw.SearchRetrieveRequestType)
     */
    @Override
    public String retrieveSetDefinitions(final SearchRetrieveRequestType request) throws RemoteException,
        SystemException, InvalidSearchQueryException, AuthorizationException, AuthenticationException,
        MissingMethodParameterException {

        checkNotNull(request);

        return get(PATH + "s" + getEscidoc12Filter(request));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.SetDefinitionHandler#retrieveSetDefinitions
     * (gov.loc.www.zing.srw.ExplainRequestType)
     */
    @Override
    public String retrieveSetDefinitions(final ExplainRequestType request) throws RemoteException, SystemException,
        InvalidSearchQueryException, AuthorizationException, AuthenticationException, MissingMethodParameterException {

        checkNotNull(request);

        return get(PATH + "s" + getEscidoc12Filter(request));
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.oai.SetDefinitionHandler#delete(java.lang.String)
     */
    @Override
    public void delete(final String setDefinitionId) throws RemoteException, SystemException, AuthorizationException,
        AuthenticationException, ResourceNotFoundException, MissingMethodParameterException {

        checkNotNull(setDefinitionId);

        del(PATH + "/" + setDefinitionId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.oai.SetDefinitionHandler#create(java.lang.String)
     */
    @Override
    public String create(final String xmlData) throws RemoteException, SystemException, AuthorizationException,
        AuthenticationException, UniqueConstraintViolationException, InvalidXmlException,
        MissingMethodParameterException {

        checkNotNull(xmlData);

        return put(PATH, xmlData);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.oai.SetDefinitionHandler#update(java.lang.String,
     * java.lang.String)
     */
    @Override
    public String update(final String setDefinitionId, final String xmlData) throws RemoteException,
        OptimisticLockingException, SystemException, AuthorizationException, AuthenticationException,
        ResourceNotFoundException, MissingMethodParameterException {

        checkNotNull(setDefinitionId);
        checkNotNull(xmlData);
        return put(PATH + "/" + setDefinitionId, xmlData);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.oai.SetDefinitionHandler#retrieve(java.lang.String)
     */
    @Override
    public String retrieve(final String setDefinitionId) throws RemoteException, SystemException,
        AuthorizationException, AuthenticationException, ResourceNotFoundException, MissingMethodParameterException {

        checkNotNull(setDefinitionId);
        return get(PATH + "/" + setDefinitionId);
    }
}