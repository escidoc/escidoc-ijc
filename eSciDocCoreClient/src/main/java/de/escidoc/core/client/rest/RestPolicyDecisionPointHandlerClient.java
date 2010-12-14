/**
 * 
 */
package de.escidoc.core.client.rest;

import java.net.MalformedURLException;

import org.joda.time.DateTime;

import de.escidoc.core.aa.PolicyDecisionPoint;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.rest.serviceLocator.PolicyDecisionPointRestServiceLocator;

/**
 * @author MVO
 * 
 */
public class RestPolicyDecisionPointHandlerClient extends RestClientBase {

    private PolicyDecisionPoint restClient;

    /**
     * 
     * @throws InternalClientException
     */
    public RestPolicyDecisionPointHandlerClient()
        throws InternalClientException {
        super();
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public RestPolicyDecisionPointHandlerClient(final String serviceAddress)
        throws InternalClientException {
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
    public String evaluate(final String requestsXml) throws EscidocException,
        InternalClientException, TransportException {

        try {
            return getClient().evaluate(requestsXml);
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
    public PolicyDecisionPoint getClient() throws InternalClientException {
        if (restClient == null) {

            PolicyDecisionPointRestServiceLocator serviceLocator =
                new PolicyDecisionPointRestServiceLocator();
            serviceLocator.registerRestCallbackHandler(this);

            try {
                serviceLocator.setServiceAddress(getServiceAddress());
            }
            catch (MalformedURLException e) {
                throw new InternalClientException(e);
            }
            restClient = serviceLocator;
        }
        return this.restClient;
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
    public DateTime getLastModificationDate(String id) throws EscidocException,
        InternalClientException, TransportException {
        return null;
    }

}
