/**
 * 
 */
package de.escidoc.core.client;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.AdminHandlerClientInterface;
import de.escidoc.core.client.rest.RestAdminHandlerClient;
import de.escidoc.core.client.soap.SoapAdminHandlerClient;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.adm.AdminStatus;
import de.escidoc.core.resources.adm.LoadExamplesResult;
import de.escidoc.core.resources.adm.LoadExamplesResult.Entry;
import de.escidoc.core.resources.adm.MessagesStatus;
import de.escidoc.core.resources.adm.RepositoryInfo;
import de.escidoc.core.resources.common.MessagesResult;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;

/**
 * @author MVO
 * 
 */
public class AdminHandlerClient
    extends
    AbstractHandlerClient<SoapAdminHandlerClient, RestAdminHandlerClient>
    implements AdminHandlerClientInterface {

    /**
     * 
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public MessagesStatus deleteObjects(final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String xml =
            Factory
                .getMarshallerFactory(getTransport()).getTaskParamMarshaller()
                .marshalDocument(taskParam);

        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().deleteObjects(xml);
        }
        else {
            xml = getRestHandlerClient().deleteObjects(xml);
        }
        Result result =
            Factory
                .getMarshallerFactory(getTransport()).getResultMarshaller()
                .unmarshalDocument(xml);
        return new MessagesStatus(result, AdminStatus.STATUS_IN_PROGRESS);
    }

    /**
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public MessagesStatus getPurgeStatus() throws EscidocException,
        InternalClientException, TransportException {
        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().getPurgeStatus();
        }
        else {
            xml = getRestHandlerClient().getPurgeStatus();
        }
        Result result =
            Factory
                .getMarshallerFactory(getTransport()).getResultMarshaller()
                .unmarshalDocument(xml);
        return new MessagesStatus(result);
    }

    /**
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public MessagesStatus getRecacheStatus() throws EscidocException,
        InternalClientException, TransportException {
        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().getRecacheStatus();
        }
        else {
            xml = getRestHandlerClient().getRecacheStatus();
        }
        Result result =
            Factory
                .getMarshallerFactory(getTransport()).getResultMarshaller()
                .unmarshalDocument(xml);
        return new MessagesStatus(result);
    }

    /**
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public MessagesStatus getReindexStatus() throws EscidocException,
        InternalClientException, TransportException {
        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().getReindexStatus();
        }
        else {
            xml = getRestHandlerClient().getReindexStatus();
        }
        Result result =
            Factory
                .getMarshallerFactory(getTransport()).getResultMarshaller()
                .unmarshalDocument(xml);
        return new MessagesStatus(result);
    }

    /**
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void decreaseReindexStatus(final String taskParam)
        throws EscidocException, InternalClientException, TransportException {
        if (getTransport() == TransportProtocol.SOAP) {
            getSoapHandlerClient().decreaseReindexStatus(taskParam);
        }
        else {
            getRestHandlerClient().decreaseReindexStatus(taskParam);
        }
    }

    /**
     * @param clearCache
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public MessagesStatus recache(final boolean clearCache)
        throws EscidocException, InternalClientException, TransportException {
        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().recache(clearCache);
        }
        else {
            xml = getRestHandlerClient().recache(clearCache);
        }
        Result result =
            Factory
                .getMarshallerFactory(getTransport()).getResultMarshaller()
                .unmarshalDocument(xml);
        return new MessagesStatus(result, AdminStatus.STATUS_IN_PROGRESS);
    }

    /**
     * @param clearIndex
     * @param indexNamePrefix
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public MessagesStatus reindex(
        final boolean clearIndex, final String indexNamePrefix)
        throws EscidocException, InternalClientException, TransportException {
        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().reindex(clearIndex, indexNamePrefix);
        }
        else {
            xml = getRestHandlerClient().reindex(clearIndex, indexNamePrefix);
        }
        Result result =
            Factory
                .getMarshallerFactory(getTransport()).getResultMarshaller()
                .unmarshalDocument(xml);
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
    public MessagesStatus reindexAll(final boolean clearIndex)
        throws EscidocException, InternalClientException, TransportException {
        return reindex(clearIndex, "all");
    }

    /**
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public RepositoryInfo getRepositoryInfo() throws EscidocException,
        InternalClientException, TransportException {
        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().getRepositoryInfo();
        }
        else {
            xml = getRestHandlerClient().getRepositoryInfo();
        }
        return new RepositoryInfo(Factory
            .getMarshallerFactory(getTransport())
            .getCommonPropertiesMarshaller().unmarshalDocument(xml));
    }

    /**
     * @param exampleSet
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public MessagesResult<Entry> loadExamples(final String exampleSet)
        throws EscidocException, InternalClientException, TransportException {
        String xml = null;
        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().loadExamples(exampleSet);
        }
        else {
            xml = getRestHandlerClient().loadExamples(exampleSet);
        }
        Result result =
            Factory
                .getMarshallerFactory(getTransport()).getResultMarshaller()
                .unmarshalDocument(xml);

        return new LoadExamplesResult(result);
    }

    /**
     * 
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public MessagesResult<Entry> loadExamples() throws EscidocException,
        InternalClientException, TransportException {
        return loadExamples(EXAMPLE_SET_COMMON);
    }

    @Override
    protected SoapAdminHandlerClient getSoapHandlerClientInstance()
        throws InternalClientException {
        return new SoapAdminHandlerClient();
    }

    @Override
    protected RestAdminHandlerClient getRestHandlerClientInstance()
        throws InternalClientException {
        return new RestAdminHandlerClient();
    }

}
