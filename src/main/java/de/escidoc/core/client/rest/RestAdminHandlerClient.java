/**
 * 
 */
package de.escidoc.core.client.rest;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger LOG = LoggerFactory.getLogger(RestAdminHandlerClient.class);

    private AdminHandler restClient;

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public RestAdminHandlerClient(final URL serviceAddress) throws InternalClientException {
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
    public RestAdminHandlerClient(final String serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String deleteObjects(final String taskParam) throws EscidocException, InternalClientException,
        TransportException {
        try {
            return getClient().deleteObjects(taskParam);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return null;
    }

    /**
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String getPurgeStatus() throws EscidocException, InternalClientException, TransportException {
        try {
            return getClient().getPurgeStatus();
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return null;
    }

    /**
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String getReindexStatus() throws EscidocException, InternalClientException, TransportException {
        try {
            return getClient().getReindexStatus();
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
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
    public String reindex(final boolean clearIndex, final String indexNamePrefix) throws EscidocException,
        InternalClientException, TransportException {
        try {
            return getClient().reindex(clearIndex ? "true" : "false", indexNamePrefix);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return null;
    }

    /**
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String getRepositoryInfo() throws EscidocException, InternalClientException, TransportException {
        try {
            return getClient().getRepositoryInfo();
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
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
    public String loadExamples(final String exampleSet) throws EscidocException, InternalClientException,
        TransportException {
        try {
            return getClient().loadExamples(exampleSet);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
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
    public String getIndexConfiguration() throws EscidocException, InternalClientException, TransportException {
        try {
            return getClient().getIndexConfiguration();
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
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

            final AdminRestServiceLocator serviceLocator = new AdminRestServiceLocator();
            serviceLocator.registerRestCallbackHandler(this);
            serviceLocator.setServiceAddress(getServiceAddress());
            this.restClient = serviceLocator;
        }
        return this.restClient;
    }
}