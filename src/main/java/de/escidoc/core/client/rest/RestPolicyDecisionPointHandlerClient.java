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
import de.escidoc.core.client.interfaces.handler.PolicyDecisionPointHandler;
import de.escidoc.core.client.rest.serviceLocator.PolicyDecisionPointRestServiceLocator;

/**
 * @author MVO
 * 
 */
public class RestPolicyDecisionPointHandlerClient extends RestClientBase {

    private static final Logger LOG = LoggerFactory.getLogger(RestPolicyDecisionPointHandlerClient.class);

    private PolicyDecisionPointHandler restClient;

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public RestPolicyDecisionPointHandlerClient(final URL serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param requestsXml
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String evaluate(final String requestsXml) throws EscidocException, InternalClientException,
        TransportException {

        try {
            return getClient().evaluate(requestsXml);
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
    public PolicyDecisionPointHandler getClient() throws InternalClientException {
        if (restClient == null) {

            final PolicyDecisionPointRestServiceLocator serviceLocator = new PolicyDecisionPointRestServiceLocator();
            serviceLocator.registerRestCallbackHandler(this);
            serviceLocator.setServiceAddress(getServiceAddress());
            restClient = serviceLocator;
        }
        return this.restClient;
    }
}