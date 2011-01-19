/**
 * 
 */
package de.escidoc.core.client;

import static de.escidoc.core.common.Precondition.checkNotNull;

import java.net.URL;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.PreprocessingHandlerClientInterface;
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
    AbstractHandlerClient<SoapPreprocessingHandlerClient, RestPreprocessingHandlerClient>
    implements PreprocessingHandlerClientInterface {

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
    public PreprocessingHandlerClient(final URL serviceAddress) {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @deprecated Use
     *             {@link PreprocessingHandlerClient#PreprocessingHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public PreprocessingHandlerClient(final String serviceAddress) {
        super(serviceAddress);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.PreprocessingHandlerClientInterface
     * #preprocess(java.lang.String,
     * de.escidoc.core.resources.sm.preprocess.PreprocessingInformation)
     */
    @Override
    public void preprocess(
        final String aggregationDefinitionId,
        final PreprocessingInformation info) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(aggregationDefinitionId);

        Marshaller<PreprocessingInformation> m =
            MarshallerFactory.getInstance().getMarshaller(
                PreprocessingInformation.class);

        String xml;
        if (info == null) {
            xml = m.marshalDocument(new PreprocessingInformation());
        }
        else {
            xml = m.marshalDocument(info);
        }

        getRestHandlerClient().preprocess(aggregationDefinitionId, xml);
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