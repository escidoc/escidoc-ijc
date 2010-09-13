package de.escidoc.core.client;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import org.apache.log4j.Logger;

import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.rest.RestClientBase;
import de.escidoc.core.client.soap.SoapClientBase;
import de.escidoc.core.common.configuration.ConfigurationProvider;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.common.TaskParam;

public abstract class AbstractHandlerClient<soapType extends SoapClientBase, restType extends RestClientBase> {

    private static final Logger LOGGER = Logger
        .getLogger(AbstractHandlerClient.class);

    //
    private TransportProtocol transport = TransportProtocol.SOAP;

    private String handle;

    private String serviceAddress;

    protected soapType soapHandlerClient;

    protected restType restHandlerClient;

    /**
	 * 
	 */
    public AbstractHandlerClient() {
        try {
            this.transport =
                TransportProtocol.valueOf(ConfigurationProvider
                    .getInstance().getProperty(
                        ConfigurationProvider.PROP_SERVICE_PROTOCOL));
        }
        catch (InternalClientException e) {
            LOGGER.debug("Unable to get the default transport protocol from "
                + "configuration. Using SOAP protocol instead.", e);
        }
    }

    /**
     * 
     * @return
     */
    public TransportProtocol getTransport() {
        return this.transport;
    }

    /**
     * 
     * @param transport
     */
    public void setTransport(TransportProtocol transport) {
        this.transport = transport;
    }

    /**
     * This method validates the specified filter and sets default values to the
     * filter if and only if the tested values are null or empty.
     * 
     * Currently checked values:
     * <ul>
     * <li>version (default: "1.1")</li>
     * <li>recordPacking (default: "string")</li>
     * </ul>
     * 
     * @param filter
     */
    protected void evalRequest(final SearchRetrieveRequestType filter) {
        if (filter.getVersion() == null || filter.getVersion().isEmpty()) {
            filter.setVersion("1.1");
        }
        if (filter.getRecordPacking() == null
            || filter.getRecordPacking().isEmpty()) {
            filter.setRecordPacking("string");
        }
        if (filter.getQuery() == null) {
            filter.setQuery("");
        }
    }
    
    /**
     * This method validates the specified filter and sets default values to the
     * filter if and only if the tested values are null or empty.
     * 
     * Currently checked values:
     * <ul>
     * <li>version (default: "1.1")</li>
     * <li>recordPacking (default: "string")</li>
     * </ul>
     * 
     * @param explain
     */
    protected void evalRequest(final ExplainRequestType explain) {
        if (explain.getVersion() == null || explain.getVersion().isEmpty()) {
            explain.setVersion("1.1");
        }
        if (explain.getRecordPacking() == null
            || explain.getRecordPacking().isEmpty()) {
            explain.setRecordPacking("string");
        }
    }

    /**
     * 
     * @param taskParam
     * @return
     * @throws InternalClientException
     */
    protected String marshalTaskParam(final TaskParam taskParam)
        throws InternalClientException {

        return Factory
            .getMarshallerFactory(getTransport()).getTaskParamMarshaller()
            .marshalDocument(taskParam);
    }

    /**
     * Set Login-Handle.
     * 
     * @param handle
     *            Login-Handle
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     */
    public final void setHandle(final String handle) {
        this.handle = handle;
        if (this.soapHandlerClient != null)
            soapHandlerClient.setHandle(handle);
        if (this.restHandlerClient != null)
            restHandlerClient.setHandle(handle);
    }

    /**
     * Set the service endpoint address.
     * 
     * @param address
     *            URL of the service endpoint.
     * @throws InternalClientException
     *             Thrown if URL is not valid.
     */
    public final void setServiceAddress(final String address) {
        this.serviceAddress = address;
    }

    /**
     * @return the handle
     */
    public final String getHandle() {
        return handle;
    }

    /**
     * @return the serviceAddress
     */
    public final String getServiceAddress() {
        return serviceAddress;
    }

    /**
     * Get the SOAP handler.
     * 
     * @return SoapContentModelHandlerClient
     * @throws InternalClientException
     *             Thrown if creating instance of SoapContentModelHandlerClient
     *             failed.
     */
    public soapType getSoapHandlerClient() throws InternalClientException {
        if (soapHandlerClient == null) {
            soapHandlerClient = getSoapHandlerClientInstance();
            if (handle != null)
                soapHandlerClient.setHandle(handle);
        }
        soapHandlerClient.setServiceAddress(serviceAddress);
        return soapHandlerClient;
    }

    /**
     * Get the REST handler.
     * 
     * @return RestContentModelHandlerClient
     * @throws InternalClientException
     *             Thrown if creating instance of RestContentModelHandlerClient
     *             failed.
     */
    public restType getRestHandlerClient() throws InternalClientException {
        if (restHandlerClient == null) {
            restHandlerClient = getRestHandlerClientInstance();
            if (handle != null)
                restHandlerClient.setHandle(handle);
        }
        restHandlerClient.setServiceAddress(serviceAddress);
        // restHandlerClient.setHandle(handle);
        return restHandlerClient;
    }

    protected abstract soapType getSoapHandlerClientInstance()
        throws InternalClientException;

    protected abstract restType getRestHandlerClientInstance()
        throws InternalClientException;
}
