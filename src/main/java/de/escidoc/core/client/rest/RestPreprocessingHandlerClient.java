/**
 * 
 */
package de.escidoc.core.client.rest;

import java.net.URL;

import org.apache.log4j.Logger;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.rest.serviceLocator.PreprocessingRestServiceLocator;
import de.escidoc.core.sm.PreprocessingHandler;

/**
 * @author MVO
 * 
 */
public class RestPreprocessingHandlerClient extends RestClientBase {

    private static final Logger LOG = Logger
        .getLogger(RestReportDefinitionHandlerClient.class);

    private PreprocessingHandler client;

    /**
     * 
     * @throws InternalClientException
     */
    public RestPreprocessingHandlerClient() throws InternalClientException {
        super();
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public RestPreprocessingHandlerClient(final URL serviceAddress)
        throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     * @deprecated Use
     *             {@link RestPreprocessingHandlerClient#RestPreprocessingHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public RestPreprocessingHandlerClient(final String serviceAddress)
        throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param aggregationDefinitionId
     * @param xmlData
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void preprocess(
        final String aggregationDefinitionId, final String xmlData)
        throws EscidocException, InternalClientException, TransportException {

        if (aggregationDefinitionId == null)
            throw new IllegalArgumentException(
                "aggregationDefinitionId must not be null.");
        if (xmlData == null)
            throw new IllegalArgumentException("xmlData must not be null.");

        try {
            getClient().preprocess(aggregationDefinitionId, xmlData);
        }
        catch (Exception e) {
            if (LOG.isDebugEnabled())
                LOG.debug(e.getMessage(), e);
            ExceptionMapper.map(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    @Override
    public PreprocessingHandler getClient() throws InternalClientException {
        if (this.client == null) {

            PreprocessingRestServiceLocator serviceLocator =
                new PreprocessingRestServiceLocator();
            serviceLocator.setServiceAddress(getServiceAddress());
            serviceLocator.registerRestCallbackHandler(this);
            this.client = serviceLocator;
        }
        return this.client;
    }
}