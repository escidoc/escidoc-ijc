/**
 * 
 */
package de.escidoc.core.client;

import org.w3c.dom.Element;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.AdminHandlerClientInterface;
import de.escidoc.core.client.rest.RestAdminHandlerClient;
import de.escidoc.core.client.soap.SoapAdminHandlerClient;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.Resource.RESOURCE_TYPE;
import de.escidoc.core.resources.adm.LoadExamplesResult;
import de.escidoc.core.resources.common.Result;

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
     */
    public AdminHandlerClient() {
    }

    /**
     * 
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String deleteObjects(final String taskParam)
        throws EscidocException, InternalClientException, TransportException {
        String result = null;
        if (getTransport() == TransportProtocol.SOAP) {
            result = getSoapHandlerClient().deleteObjects(taskParam);
        }
        else {
            result = getRestHandlerClient().deleteObjects(taskParam);
        }
        // TODO check result
        return result;
    }

    /**
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String getPurgeStatus() throws EscidocException,
        InternalClientException, TransportException {
        String result = null;
        if (getTransport() == TransportProtocol.SOAP) {
            result = getSoapHandlerClient().getPurgeStatus();
        }
        else {
            result = getRestHandlerClient().getPurgeStatus();
        }
        // TODO check result
        return result;
    }

    /**
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String getRecacheStatus() throws EscidocException,
        InternalClientException, TransportException {
        String result = null;
        if (getTransport() == TransportProtocol.SOAP) {
            result = getSoapHandlerClient().getRecacheStatus();
        }
        else {
            result = getRestHandlerClient().getRecacheStatus();
        }
        // TODO check result
        return result;
    }

    /**
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String getReindexStatus() throws EscidocException,
        InternalClientException, TransportException {
        String result = null;
        if (getTransport() == TransportProtocol.SOAP) {
            result = getSoapHandlerClient().getReindexStatus();
        }
        else {
            result = getRestHandlerClient().getReindexStatus();
        }
        // TODO check result
        return result;
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
    public String recache(final boolean clearCache) throws EscidocException,
        InternalClientException, TransportException {
        String result = null;
        if (getTransport() == TransportProtocol.SOAP) {
            result = getSoapHandlerClient().recache(clearCache);
        }
        else {
            result = getRestHandlerClient().recache(clearCache);
        }
        // TODO check result
        return result;
    }

    /**
     * @param clearIndex
     * @param indexNamePrefix
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String reindex(final boolean clearIndex, final String indexNamePrefix)
        throws EscidocException, InternalClientException, TransportException {
        String result = null;
        if (getTransport() == TransportProtocol.SOAP) {
            result =
                getSoapHandlerClient().reindex(clearIndex, indexNamePrefix);
        }
        else {
            result =
                getRestHandlerClient().reindex(clearIndex, indexNamePrefix);
        }
        // TODO check result
        return result;
    }

    /**
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String getRepositoryInfo() throws EscidocException,
        InternalClientException, TransportException {
        String result = null;
        if (getTransport() == TransportProtocol.SOAP) {
            result = getSoapHandlerClient().getRepositoryInfo();
        }
        else {
            result = getRestHandlerClient().getRepositoryInfo();
        }
        // TODO check result
        return result;
    }

    /**
     * @param exampleSet
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public LoadExamplesResult loadExamples(final String exampleSet)
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

        // evaluate result
        LoadExamplesResult leResult = new LoadExamplesResult();

        for (Element element : result) {
            String message = element.getTextContent();
            String type = message.substring(8, message.indexOf(':'));
            String objid = message.substring(message.indexOf(':') + 2);

            leResult.add(leResult.new Entry(
                objid, RESOURCE_TYPE.valueByTagName(type), message));
        }

        return leResult;
    }

    /**
     * 
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public LoadExamplesResult loadExamples() throws EscidocException,
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
