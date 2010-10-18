/**
 * 
 */
package de.escidoc.core.client.rest;

import java.net.MalformedURLException;

import org.joda.time.DateTime;

import de.escidoc.core.adm.AdminHandler;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.rest.serviceLocator.AdminRestServiceLocator;

/**
 * @author MVO
 * 
 */
public class RestAdminHandlerClient extends RestClientBase {

    private AdminHandler restClient;

    /**
     * 
     * @throws InternalClientException
     */
    public RestAdminHandlerClient() throws InternalClientException {
        super();
    }

    /**
     * 
     * @param serviceAdress
     * @throws InternalClientException
     */
    public RestAdminHandlerClient(final String serviceAdress)
        throws InternalClientException {
        super(serviceAdress);
    }

    /**
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String deleteObjects(final String taskParam)
        throws EscidocException, InternalClientException, TransportException {
        try {
            return getClient().deleteObjects(taskParam);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return null;
    }

    /**
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String getPurgeStatus() throws EscidocException,
        InternalClientException, TransportException {
        try {
            return getClient().getPurgeStatus();
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return null;
    }

    /**
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String getRecacheStatus() throws EscidocException,
        InternalClientException, TransportException {
        try {
            return getClient().getRecacheStatus();
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return null;
    }

    /**
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String getReindexStatus() throws EscidocException,
        InternalClientException, TransportException {
        try {
            return getClient().getReindexStatus();
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return null;
    }

    /**
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void decreaseReindexStatus(final String taskParam)
        throws EscidocException, InternalClientException, TransportException {
        try {
            getClient().decreaseReindexStatus(taskParam);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
    }

    /**
     * @param clearCache
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String recache(final boolean clearCache) throws EscidocException,
        InternalClientException, TransportException {
        try {
            return getClient().recache(clearCache ? "true" : "false");
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return null;
    }

    /**
     * @param clearIndex
     * @param indexNamePrefix
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String reindex(final boolean clearIndex, final String indexNamePrefix)
        throws EscidocException, InternalClientException, TransportException {
        try {
            return getClient().reindex(clearIndex ? "true" : "false",
                indexNamePrefix);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return null;
    }

    /**
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String getRepositoryInfo() throws EscidocException,
        InternalClientException, TransportException {
        try {
            return getClient().getRepositoryInfo();
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return null;
    }

    /**
     * @param exampleSet
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String loadExamples(final String exampleSet) throws EscidocException,
        InternalClientException, TransportException {
        try {
            return getClient().loadExamples(exampleSet);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    public AdminHandler getClient() throws InternalClientException {
        if (this.restClient == null) {

            AdminRestServiceLocator serviceLocator =
                new AdminRestServiceLocator();
            serviceLocator.setFollowRedirects(followRedirects);

            try {
                serviceLocator.setServiceAddress(getServiceAddress());
            }
            catch (MalformedURLException e) {
                throw new InternalClientException(e);
            }
            this.restClient = serviceLocator;
        }
        return this.restClient;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.ClientBase#getLastModificationDate(java.lang.String
     * )
     */
    public DateTime getLastModificationDate(String id) throws EscidocException,
        InternalClientException, TransportException {
        return null;
    }

}
