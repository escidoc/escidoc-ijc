package de.escidoc.core.client;

import static de.escidoc.core.common.Precondition.checkNotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.rest.RestClientBase;
import de.escidoc.core.client.soap.SoapClientBase;
import de.escidoc.core.common.configuration.ConfigurationProvider;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.sb.Record;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;
import de.escidoc.core.resources.sb.search.records.ResourceRecord;

public abstract class AbstractHandlerClient<soapType extends SoapClientBase, restType extends RestClientBase> {

    private static final Logger LOG = Logger
        .getLogger(AbstractHandlerClient.class);

    private String handle;

    private URL serviceAddress;

    protected soapType soapHandlerClient;

    protected restType restHandlerClient;

    /**
     * 
     */
    public AbstractHandlerClient() {

    }

    /**
     * 
     * @param serviceAddress
     */
    public AbstractHandlerClient(final URL serviceAddress) {
        this();
        this.serviceAddress = serviceAddress;
    }

    /**
     * 
     * @param serviceAddress
     * @deprecated Use {@link AbstractHandlerClient#AbstractHandlerClient(URL)}
     *             instead. If the specified serviceAddress is an invalid URL,
     *             the Exception will be logged and not thrown, because this
     *             would cause compilation errors for CLIB users.
     */
    @Deprecated
    public AbstractHandlerClient(final String serviceAddress) {
        this();
        try {
            this.serviceAddress = new URL(serviceAddress);
        }
        catch (MalformedURLException e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Invalid serviceAddress.", e);
            }
        }
    }

    /**
     * 
     * @return
     */
    public TransportProtocol getTransport() {
        return ConfigurationProvider.DEFAULT_TRANSPORT_PROTOCOL;
    }

    /**
     * 
     * @param transport
     * @deprecated The main handler clients will use the REST transport protocol
     *             only since eSciDoc infrastructure 1.3. Therefore this method
     *             will not do anything.
     */
    @Deprecated
    public void setTransport(final TransportProtocol transport) {
        // do nothing
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
    public final URL getServiceAddress() {
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
    protected <T> T getSRWResourceRecordData(
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
     * 
     * @param <T>
     * @param resource
     * @param response
     * @return
     */
    protected <T> List<T> getSearchRetrieveResponseAsList(
        final Class<T> resource, final SearchRetrieveResponse response) {

        checkNotNull(resource);
        checkNotNull(response);

        List<T> results = new LinkedList<T>();

        for (Record<?> record : response.getRecords()) {
            T element = getSRWResourceRecordData(record, resource);
            if (element != null) {
                results.add(element);
            }
        }
        return results;
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
