/**
 * 
 */
package de.escidoc.core.client.rest.serviceLocator;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import de.escidoc.core.client.interfaces.ScopeHandler;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException;
import de.escidoc.core.common.exceptions.remote.application.invalid.XmlCorruptedException;
import de.escidoc.core.common.exceptions.remote.application.invalid.XmlSchemaValidationException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ScopeNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException;
import de.escidoc.core.common.exceptions.remote.system.SystemException;

/**
 * @author MVO
 * 
 */
public class ScopeRestServiceLocator extends RestServiceMethod
    implements ScopeHandler {

    private static final String PATH_SCOPE = "/statistic/scope";

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.sm.ScopeHandler#delete(java.lang.String)
     */
    public void delete(final String id) throws RemoteException, SystemException,
        AuthorizationException, ScopeNotFoundException,
        AuthenticationException, MissingMethodParameterException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");

        del(PATH_SCOPE + "/" + id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.sm.ScopeHandler#create(java.lang.String)
     */
    public String create(final String xml) throws RemoteException,
        XmlSchemaValidationException, SystemException, XmlCorruptedException,
        AuthorizationException, AuthenticationException,
        MissingMethodParameterException {

        if (xml == null)
            throw new IllegalArgumentException("xml must not be null.");

        return put(PATH_SCOPE, xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.sm.ScopeHandler#update(java.lang.String,
     * java.lang.String)
     */
    public String update(final String id, final String xml) throws RemoteException,
        XmlSchemaValidationException, SystemException, XmlCorruptedException,
        AuthorizationException, ScopeNotFoundException,
        AuthenticationException, MissingMethodParameterException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");
        if (xml == null)
            throw new IllegalArgumentException("xml must not be null.");

        return put(PATH_SCOPE + "/" + id, xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.sm.ScopeHandler#retrieve(java.lang.String)
     */
    public String retrieve(final String id) throws RemoteException, SystemException,
        AuthorizationException, ScopeNotFoundException,
        AuthenticationException, MissingMethodParameterException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");

        return get(PATH_SCOPE + "/" + id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.sm.ScopeHandler#retrieveScopes(java.lang.String)
     */
    public String retrieveScopes(final String cqlQuery) throws RemoteException,
        SystemException, AuthorizationException, AuthenticationException,
        InvalidXmlException, MissingMethodParameterException {

        throw new UnsupportedOperationException(
            "This method is no longer supported.");
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.sm.ScopeHandler#retrieveScopes(java.util.HashMap)
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public String retrieveScopes(final HashMap filter) throws RemoteException,
        SystemException, InvalidSearchQueryException, AuthorizationException,
        AuthenticationException, MissingMethodParameterException {
        
        if (filter == null)
            throw new IllegalArgumentException("filter must not be null.");
        
        return get(PATH_SCOPE + "s", (Map<String, String[]>)filter);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.ScopeHandler#retrieveScopes(gov.loc
     * .www.zing.srw.SearchRetrieveRequestType)
     */
    public String retrieveScopes(final SearchRetrieveRequestType request)
        throws RemoteException, SystemException, AuthorizationException,
        AuthenticationException, InvalidXmlException,
        MissingMethodParameterException {
        
        if (request == null)
            throw new IllegalArgumentException("request must not be null.");
        
        return get(PATH_SCOPE + "s" + getEscidoc12Filter(request));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.ScopeHandler#retrieveScopes(gov.loc
     * .www.zing.srw.ExplainRequestType)
     */
    public String retrieveScopes(final ExplainRequestType request)
        throws RemoteException, SystemException, AuthorizationException,
        AuthenticationException, InvalidXmlException,
        MissingMethodParameterException {

        if (request == null)
            throw new IllegalArgumentException("request must not be null.");
        
        return get(PATH_SCOPE + "s" + getEscidoc12Filter(request));
    }
}