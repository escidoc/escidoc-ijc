/**
 * 
 */
package de.escidoc.core.client;

import static de.escidoc.core.common.Precondition.checkNotNull;

import java.net.URL;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.AdminHandlerClientInterface;
import de.escidoc.core.client.rest.RestAdminHandlerClient;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.adm.AdminStatus;
import de.escidoc.core.resources.adm.LoadExamplesResult;
import de.escidoc.core.resources.adm.LoadExamplesResult.Entry;
import de.escidoc.core.resources.adm.MessagesStatus;
import de.escidoc.core.resources.adm.RepositoryInfo;
import de.escidoc.core.resources.common.MessagesResult;
import de.escidoc.core.resources.common.Properties;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;

/**
 * @author MVO
 * 
 */
public class AdminHandlerClient extends AbstractHandlerClient<RestAdminHandlerClient>
    implements AdminHandlerClientInterface {

    /**
     * 
     * @param serviceAddress
     */
    public AdminHandlerClient(final URL serviceAddress) {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @deprecated Use {@link AdminHandlerClient#AdminHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public AdminHandlerClient(final String serviceAddress) {
        super(serviceAddress);
    }

    /**
     * 
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @Override
    public MessagesStatus deleteObjects(final TaskParam taskParam) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(taskParam);

        final String xml = getClient().deleteObjects(marshalTaskParam(taskParam));

        final Result result = MarshallerFactory.getInstance().getMarshaller(Result.class).unmarshalDocument(xml);
        return new MessagesStatus(result, AdminStatus.STATUS_IN_PROGRESS);
    }

    /**
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @Override
    public MessagesStatus getPurgeStatus() throws EscidocException, InternalClientException, TransportException {
        final String xml = getClient().getPurgeStatus();
        final Result result = MarshallerFactory.getInstance().getMarshaller(Result.class).unmarshalDocument(xml);
        return new MessagesStatus(result);
    }

    /**
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @Override
    public MessagesStatus getReindexStatus() throws EscidocException, InternalClientException, TransportException {
        final String xml = getClient().getReindexStatus();
        final Result result = MarshallerFactory.getInstance().getMarshaller(Result.class).unmarshalDocument(xml);
        return new MessagesStatus(result);
    }

    /**
     * @param clearIndex
     * @param indexNamePrefix
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @Override
    public MessagesStatus reindex(final boolean clearIndex, final String indexNamePrefix) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(indexNamePrefix);

        final String xml = getClient().reindex(clearIndex, indexNamePrefix);
        final Result result = MarshallerFactory.getInstance().getMarshaller(Result.class).unmarshalDocument(xml);
        return new MessagesStatus(result, AdminStatus.STATUS_IN_PROGRESS);
    }

    /**
     * 
     * @param clearIndex
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @Override
    public MessagesStatus reindexAll(final boolean clearIndex) throws EscidocException, InternalClientException,
        TransportException {
        return reindex(clearIndex, "all");
    }

    /**
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @Override
    public RepositoryInfo getRepositoryInfo() throws EscidocException, InternalClientException, TransportException {
        final String xml = getClient().getRepositoryInfo();
        return new RepositoryInfo(MarshallerFactory
            .getInstance().getMarshaller(Properties.class).unmarshalDocument(xml));
    }

    /**
     * @param exampleSet
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @Override
    public MessagesResult<Entry> loadExamples(final String exampleSet) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(exampleSet);

        final String xml = getClient().loadExamples(exampleSet);
        final Result result = MarshallerFactory.getInstance().getMarshaller(Result.class).unmarshalDocument(xml);

        return new LoadExamplesResult(result);
    }

    /**
     * 
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @Override
    public String getIndexConfiguration() throws EscidocException, InternalClientException, TransportException {

        return getClient().getIndexConfiguration();
    }

    /**
     * 
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @Override
    public MessagesResult<Entry> loadExamples() throws EscidocException, InternalClientException, TransportException {
        return loadExamples(EXAMPLE_SET_COMMON);
    }

    @Override
    protected RestAdminHandlerClient getRestHandlerClientInstance() throws InternalClientException {
        return new RestAdminHandlerClient(getServiceAddress());
    }
}