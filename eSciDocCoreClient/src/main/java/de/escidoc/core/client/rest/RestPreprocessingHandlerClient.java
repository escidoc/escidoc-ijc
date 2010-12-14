/**
 * 
 */
package de.escidoc.core.client.rest;

import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

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
    public RestPreprocessingHandlerClient(final String serviceAddress)
        throws InternalClientException {
        super(serviceAddress);
    }

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
            try {
                serviceLocator.setServiceAddress(getServiceAddress());
            }
            catch (MalformedURLException e) {
                throw new InternalClientException(e);
            }
            serviceLocator.registerRestCallbackHandler(this);
            this.client = serviceLocator;
        }
        return this.client;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.ClientBase#getLastModificationDate(java.lang.String
     * )
     */
    @Override
    @Deprecated
    public DateTime getLastModificationDate(final String id)
        throws EscidocException, InternalClientException, TransportException {
        return null;
    }

}
