/**
 * 
 */
package de.escidoc.core.client;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.rest.RestPreprocessingHandlerClient;
import de.escidoc.core.client.soap.SoapPreprocessingHandlerClient;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.sm.preprocess.PreprocessingInformation;

/**
 * @author MVO
 * 
 */
public class PreprocessingHandlerClient
    extends
    AbstractHandlerClient<SoapPreprocessingHandlerClient, RestPreprocessingHandlerClient> {

    /**
     * 
     */
    public PreprocessingHandlerClient() {
        super();
    }

    /**
     * 
     * @param serviceAddress
     */
    public PreprocessingHandlerClient(final String serviceAddress) {
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
        final String aggregationDefinitionId,
        final PreprocessingInformation info) throws EscidocException,
        InternalClientException, TransportException {

        if (aggregationDefinitionId == null)
            throw new IllegalArgumentException(
                "aggregationDefinitionId must not be null.");

        Marshaller<PreprocessingInformation> m =
            MarshallerFactory.getInstance(getTransport()).getMarshaller(
                PreprocessingInformation.class);

        String xml;
        if (info == null) {
            xml = m.marshalDocument(new PreprocessingInformation());
        }
        else {
            xml = m.marshalDocument(info);
        }

        if (getTransport() == TransportProtocol.SOAP) {
            getSoapHandlerClient().preprocess(aggregationDefinitionId, xml);
        }
        else {
            getRestHandlerClient().preprocess(aggregationDefinitionId, xml);
        }
    }

    @Override
    protected SoapPreprocessingHandlerClient getSoapHandlerClientInstance()
        throws InternalClientException {
        return new SoapPreprocessingHandlerClient(getServiceAddress());
    }

    @Override
    protected RestPreprocessingHandlerClient getRestHandlerClientInstance()
        throws InternalClientException {
        return new RestPreprocessingHandlerClient(getServiceAddress());
    }

}
