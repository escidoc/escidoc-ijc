/**
 * 
 */
package de.escidoc.core.client.rest.serviceLocator;

import java.rmi.RemoteException;

import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException;
import de.escidoc.core.common.exceptions.remote.system.SystemException;

/**
 * TODO Exceptions
 * 
 * @author MVO
 * 
 */
public class AdminRestServiceLocator extends RestServiceMethod
    implements de.escidoc.core.adm.AdminHandler {

    private static final String PATH_CONTEXT = "/adm/admin";

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.adm.AdminHandler#deleteObjects(java.lang.String)
     */
    public String deleteObjects(final String taskParam) throws RemoteException,
        SystemException, AuthorizationException, AuthenticationException,
        InvalidXmlException {
        return post(PATH_CONTEXT + "/deleteobjects", taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.adm.AdminHandler#getPurgeStatus()
     */
    public String getPurgeStatus() throws RemoteException, SystemException,
        AuthorizationException, AuthenticationException {
        return get(PATH_CONTEXT + "/deleteobjects");
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.adm.AdminHandler#getRecacheStatus()
     */
    public String getRecacheStatus() throws RemoteException, SystemException,
        AuthorizationException, AuthenticationException {
        return get(PATH_CONTEXT + "/recache");
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.adm.AdminHandler#getReindexStatus()
     */
    public String getReindexStatus() throws RemoteException, SystemException,
        AuthorizationException, AuthenticationException {
        return get(PATH_CONTEXT + "/reindex");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.adm.AdminHandler#decreaseReindexStatus(java.lang.String)
     */
    public void decreaseReindexStatus(final String taskParam)
        throws RemoteException, SystemException, AuthorizationException,
        AuthenticationException, InvalidXmlException {
        post(PATH_CONTEXT + "/decrease-reindex-status", taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.adm.AdminHandler#recache(java.lang.String)
     */
    public String recache(final String clearCache) throws RemoteException,
        SystemException, AuthorizationException, AuthenticationException {
        return post(PATH_CONTEXT + "/recache/" + clearCache, "");
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.adm.AdminHandler#reindex(java.lang.String,
     * java.lang.String)
     */
    public String reindex(final String clearIndex, final String indexNamePrefix)
        throws RemoteException, SystemException, InvalidSearchQueryException,
        AuthorizationException, AuthenticationException {
        return post(PATH_CONTEXT + "/reindex/" + clearIndex + "/"
            + indexNamePrefix, "");
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.adm.AdminHandler#getRepositoryInfo()
     */
    public String getRepositoryInfo() throws RemoteException, SystemException,
        AuthorizationException, AuthenticationException {
        return get(PATH_CONTEXT + "/get-repository-info");
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.adm.AdminHandler#loadExamples(java.lang.String)
     */
    public String loadExamples(final String exampleSet) throws RemoteException,
        SystemException, InvalidSearchQueryException, AuthorizationException,
        AuthenticationException {
        return get(PATH_CONTEXT + "/load-examples/" + exampleSet);
    }

}
