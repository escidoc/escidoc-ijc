package de.escidoc.core.client;

import org.apache.log4j.Logger;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.rest.RestClientBase;
import de.escidoc.core.client.soap.SoapClientBase;
import de.escidoc.core.common.configuration.ConfigurationProvider;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.sb.Record;
import de.escidoc.core.resources.sb.search.records.ResourceRecord;

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
     * TODO put default transport loading into a static variable somewhere as a
     * constant value.
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
     * @param serviceAddress
     */
    public AbstractHandlerClient(final String serviceAddress) {
        this();
        this.serviceAddress = serviceAddress;
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
    public void setTransport(final TransportProtocol transport) {
        this.transport = transport;
    }

    /**
     * 
     * @param taskParam
     * @return
     * @throws InternalClientException
     */
    protected String marshalTaskParam(final TaskParam taskParam)
        throws InternalClientException {

        return MarshallerFactory
            .getInstance(getTransport())
            .getMarshaller(MarshallerFactory.CLASS_TASK_PARAM)
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
     * Generic convenience method to get the specific recordData content of a
     * SearchRetrieveResponse.
     * 
     * @param <T>
     * @param record
     * @param type
     *            The ResourceType to be used to perform a check against the
     *            record, if it contains the object of type T. If ResourceType
     *            does not fit to the type T, a ClassCastException will be
     *            thrown.
     * @return The content of the recordData of the SearchRetrieveResponse
     *         specified as type T.
     */
    @SuppressWarnings("unchecked")
    public <T> T getSRWResourceRecordData(
        final Record<?> record, final Class<T> resource) {

        if (record instanceof ResourceRecord<?>) {
            ResourceRecord<?> rRecord = (ResourceRecord<?>) record;

            if (rRecord.getRecordDataType() == resource) {
                return (T) record.getRecordData();
            }
        }
        return null;
    }

    /**
     * Get the SOAP handler.
     * 
     * @return SoapContentModelHandlerClient
     * @throws InternalClientException
     *             Thrown if creating instance of SoapContentModelHandlerClient
     *             failed.
     */
    protected soapType getSoapHandlerClient() throws InternalClientException {
        if (soapHandlerClient == null) {
            soapHandlerClient = getSoapHandlerClientInstance();
            if (handle != null)
                soapHandlerClient.setHandle(handle);
        }
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
    protected restType getRestHandlerClient() throws InternalClientException {
        if (restHandlerClient == null) {
            restHandlerClient = getRestHandlerClientInstance();
            if (handle != null)
                restHandlerClient.setHandle(handle);
        }
        return restHandlerClient;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.CrudHandlerInterface#login(java.lang
     * .String, java.lang.String, java.lang.String)
     */
    @Deprecated
    public String login(
        final String serviceAddress, final String username,
        final String password) throws EscidocException,
        InternalClientException, TransportException {

        if (getTransport() == TransportProtocol.SOAP) {
            return getSoapHandlerClient().login(serviceAddress, username,
                password);
        }
        else {
            return getRestHandlerClient().login(serviceAddress, username,
                password);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.CrudHandlerInterface#logout()
     */
    @Deprecated
    public void logout() throws EscidocException, InternalClientException,
        TransportException {

        setHandle("");
    }

    /**
     * 
     * @return The SOAP instance of the handler client.
     * @throws InternalClientException
     */
    protected abstract soapType getSoapHandlerClientInstance()
        throws InternalClientException;

    /**
     * 
     * @return The REST instance of the handler client.
     * @throws InternalClientException
     */
    protected abstract restType getRestHandlerClientInstance()
        throws InternalClientException;
}
