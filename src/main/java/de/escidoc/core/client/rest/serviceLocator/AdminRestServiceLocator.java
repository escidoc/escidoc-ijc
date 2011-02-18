/**
 * 
 */
package de.escidoc.core.client.rest.serviceLocator;

import static de.escidoc.core.common.Precondition.checkNotNull;

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

    public static final String PATH = "/adm/admin";

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.adm.AdminHandler#deleteObjects(java.lang.String)
     */
    @Override
    public String deleteObjects(final String taskParam) throws RemoteException,
        SystemException, AuthorizationException, AuthenticationException,
        InvalidXmlException {
        return post(PATH + "/deleteobjects", taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.adm.AdminHandler#getPurgeStatus()
     */
    @Override
    public String getPurgeStatus() throws RemoteException, SystemException,
        AuthorizationException, AuthenticationException {
        return get(PATH + "/deleteobjects");
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.adm.AdminHandler#getReindexStatus()
     */
    @Override
    public String getReindexStatus() throws RemoteException, SystemException,
        AuthorizationException, AuthenticationException {
        return get(PATH + "/reindex");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.adm.AdminHandler#decreaseReindexStatus(java.lang.String)
     */
    @Override
    public void decreaseReindexStatus(final String taskParam)
        throws RemoteException, SystemException, AuthorizationException,
        AuthenticationException, InvalidXmlException {
        post(PATH + "/decrease-reindex-status", taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.adm.AdminHandler#reindex(java.lang.String,
     * java.lang.String)
     */
    @Override
    public String reindex(final String clearIndex, final String indexNamePrefix)
        throws RemoteException, SystemException, InvalidSearchQueryException,
        AuthorizationException, AuthenticationException {

        checkNotNull(clearIndex);
        checkNotNull(indexNamePrefix);

        return post(PATH + "/reindex/" + clearIndex + "/"
            + indexNamePrefix, "");
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.adm.AdminHandler#getRepositoryInfo()
     */
    @Override
    public String getRepositoryInfo() throws RemoteException, SystemException,
        AuthorizationException, AuthenticationException {
        return get(PATH + "/get-repository-info");
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.adm.AdminHandler#loadExamples(java.lang.String)
     */
    @Override
    public String loadExamples(final String exampleSet) throws RemoteException,
        SystemException, InvalidSearchQueryException, AuthorizationException,
        AuthenticationException {

        checkNotNull(exampleSet);

        return get(PATH + "/load-examples/" + exampleSet);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.adm.AdminHandler#getIndexConfiguration()
     */
    @Override
    public String getIndexConfiguration() throws RemoteException,
        SystemException, AuthorizationException, AuthenticationException {
        return get(PATH + "/get-index-configuration");
    }

}
