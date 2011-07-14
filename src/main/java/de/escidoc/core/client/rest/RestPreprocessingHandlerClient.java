/**
 * 
 */
package de.escidoc.core.client.rest;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.handler.PreprocessingHandler;
import de.escidoc.core.client.rest.serviceLocator.PreprocessingRestServiceLocator;

/**
 * @author MVO
 * 
 */
public class RestPreprocessingHandlerClient extends RestClientBase {

    private static final Logger LOG = LoggerFactory.getLogger(RestPreprocessingHandlerClient.class);

    private PreprocessingHandler client;

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public RestPreprocessingHandlerClient(final URL serviceAddress) throws InternalClientException {
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
    public void preprocess(final String aggregationDefinitionId, final String xmlData) throws EscidocException,
        InternalClientException, TransportException {

        if (aggregationDefinitionId == null)
            throw new IllegalArgumentException("aggregationDefinitionId must not be null.");
        if (xmlData == null)
            throw new IllegalArgumentException("xmlData must not be null.");

        try {
            getClient().preprocess(aggregationDefinitionId, xmlData);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
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

            final PreprocessingRestServiceLocator serviceLocator = new PreprocessingRestServiceLocator();
            serviceLocator.setServiceAddress(getServiceAddress());
            serviceLocator.registerRestCallbackHandler(this);
            this.client = serviceLocator;
        }
        return this.client;
    }
}