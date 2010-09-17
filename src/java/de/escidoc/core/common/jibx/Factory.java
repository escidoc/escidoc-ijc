package de.escidoc.core.common.jibx;

import java.util.HashMap;
import java.util.Map;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.common.configuration.ConfigurationProvider;

public class Factory {

    private static final Map<TransportProtocol, MarshallerFactory> marshallerFactoryMap =
        new HashMap<TransportProtocol, MarshallerFactory>();

    private static TransportProtocol defaultTransport = null;

    /**
     * 
     * @return
     * @throws InternalClientException
     */
    public static final MarshallerFactory getMarshallerFactory()
        throws InternalClientException {

        if (defaultTransport == null) {
            defaultTransport =
                TransportProtocol.valueOf(ConfigurationProvider
                    .getInstance().getProperty(
                        ConfigurationProvider.PROP_SERVICE_PROTOCOL));
        }
        if (defaultTransport == null)
            throw new InternalClientException(
                "Unable to load default transport protocol from configuration.");
        if (marshallerFactoryMap.get(defaultTransport) == null) {
            MarshallerFactory resultFactory =
                new MarshallerFactory(defaultTransport);
            marshallerFactoryMap.put(defaultTransport, resultFactory);
            return resultFactory;
        }
        return marshallerFactoryMap.get(defaultTransport);
    }

    /**
     * 
     * @param transport
     * @return
     * @throws InternalClientException
     */
    public static final MarshallerFactory getMarshallerFactory(
        TransportProtocol transport) throws InternalClientException {

        if (marshallerFactoryMap.get(transport) == null) {
            MarshallerFactory resultFactory = new MarshallerFactory(transport);
            marshallerFactoryMap.put(transport, resultFactory);
            return resultFactory;
        }
        return marshallerFactoryMap.get(transport);
    }
}
