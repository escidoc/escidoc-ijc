package de.escidoc.core.client;

import static de.escidoc.core.common.Precondition.checkNotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.rest.RestClientBase;
import de.escidoc.core.common.configuration.ConfigurationProvider;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.sb.search.SearchResult;
import de.escidoc.core.resources.sb.search.SearchResultRecord;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;

public abstract class AbstractHandlerClient<T extends RestClientBase> {

    private static final Logger LOG = Logger
        .getLogger(AbstractHandlerClient.class);

    private String handle;

    private URL serviceAddress;

    protected T restHandlerClient;

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
            .getInstance().getMarshaller(TaskParam.class)
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
     * 
     * @param <T>
     * @param resource
     * @param response
     * @return
     */
    @SuppressWarnings("unchecked")
    protected <U> List<U> getSearchRetrieveResponseAsList(
        final Class<U> resource, final SearchRetrieveResponse response) {

        checkNotNull(resource);
        checkNotNull(response);

        List<U> results = new LinkedList<U>();

        for (SearchResultRecord record : response.getRecords()) {
            SearchResult searchResult = record.getRecordData();
            Object content = searchResult.getContent();
            if (resource.isAssignableFrom(content.getClass())) {
                results.add((U) content);
            }
        }
        return results;
    }

    /**
     * Get the REST handler.
     * 
     * @return RestContentModelHandlerClient
     * @throws InternalClientException
     *             Thrown if creating instance of RestContentModelHandlerClient
     *             failed.
     */
    protected T getClient() throws InternalClientException {
        if (restHandlerClient == null) {
            restHandlerClient = getRestHandlerClientInstance();
            if (handle != null)
                restHandlerClient.setHandle(handle);
        }
        return restHandlerClient;
    }

    /**
     * 
     * @return The REST instance of the handler client.
     * @throws InternalClientException
     */
    protected abstract T getRestHandlerClientInstance()
        throws InternalClientException;
}