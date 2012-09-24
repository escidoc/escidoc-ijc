/**
 * 
 */
package de.escidoc.core.client.soap;

import java.net.URL;

import javax.xml.rpc.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.sm.PreprocessingHandler;
import de.escidoc.core.sm.PreprocessingHandlerServiceLocator;

/**
 * @author MVO
 * 
 */
public class SoapPreprocessingHandlerClient extends SoapClientBase {

    private static final Logger LOG = LoggerFactory.getLogger(SoapPreprocessingHandlerClient.class);

    private PreprocessingHandler client;

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public SoapPreprocessingHandlerClient(final URL serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     * @deprecated Use
     *             {@link SoapPreprocessingHandlerClient#SoapPreprocessingHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public SoapPreprocessingHandlerClient(final String serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param aggregationDefinitionId
     *            id of the aggregation-definition to preprocess.
     * @param xmlData
     *            preprocessing-information as xml in statistic-data schema.
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

    @Override
    public PreprocessingHandler getClient() throws InternalClientException {
        if (client == null) {
            final PreprocessingHandlerServiceLocator serviceLocator =
                new PreprocessingHandlerServiceLocator(getEngineConfig());
            final URL url = getHandlerServiceURL(serviceLocator.getPreprocessingHandlerServiceAddress());
            try {
                client = serviceLocator.getPreprocessingHandlerService(url);
            }
            catch (final ServiceException e) {
                throw new InternalClientException(e.getMessage(), e);
            }
            registerPWCallback(client);
        }
        return client;
    }
}