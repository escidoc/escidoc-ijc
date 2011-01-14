/**
 * 
 */
package de.escidoc.core.client.rest;

import java.net.URL;

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
     * @param serviceAddress
     * @throws InternalClientException
     */
    public RestAdminHandlerClient(final URL serviceAddress)
        throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     * @deprecated Use
     *             {@link RestAdminHandlerClient#RestAdminHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public RestAdminHandlerClient(final String serviceAddress)
        throws InternalClientException {
        super(serviceAddress);
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
    public String loadExamples(final String exampleSet)
        throws EscidocException, InternalClientException, TransportException {
        try {
            return getClient().loadExamples(exampleSet);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return null;
    }

    /**
     * 
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String getIndexConfiguration() throws EscidocException,
        InternalClientException, TransportException {
        try {
            return getClient().getIndexConfiguration();
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
    @Override
    public AdminHandler getClient() throws InternalClientException {
        if (this.restClient == null) {

            AdminRestServiceLocator serviceLocator =
                new AdminRestServiceLocator();
            serviceLocator.registerRestCallbackHandler(this);
            serviceLocator.setServiceAddress(getServiceAddress());
            this.restClient = serviceLocator;
        }
        return this.restClient;
    }
}