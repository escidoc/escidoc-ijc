/**
 * 
 */
package de.escidoc.core.client.soap;

import java.net.URL;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import de.escidoc.core.adm.AdminHandler;
import de.escidoc.core.adm.AdminHandlerServiceLocator;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;

/**
 * @author MVO
 * 
 */
public class SoapAdminHandlerClient extends SoapClientBase {

    private static final Logger LOG = Logger.getLogger(SoapAdminHandlerClient.class);

    private AdminHandler soapClient = null;

    /**
     * 
     * @param serviceAdress
     * @throws InternalClientException
     */
    public SoapAdminHandlerClient(final URL serviceAdress) throws InternalClientException {
        super(serviceAdress);
    }

    /**
     * 
     * @param serviceAdress
     * @throws InternalClientException
     * @deprecated Use
     *             {@link SoapAdminHandlerClient#SoapAdminHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public SoapAdminHandlerClient(final String serviceAdress) throws InternalClientException {
        super(serviceAdress);
    }

    /**
     * 
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String deleteObjects(final String taskParam) throws EscidocException, InternalClientException,
        TransportException {
        try {
            return getClient().deleteObjects(taskParam);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return null;
    }

    /**
     * 
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String getPurgeStatus() throws EscidocException, InternalClientException, TransportException {
        try {
            return getClient().getPurgeStatus();
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return null;
    }

    /**
     * 
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String getReindexStatus() throws EscidocException, InternalClientException, TransportException {
        try {
            return getClient().getReindexStatus();
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return null;
    }

    /**
     * 
     * @param clearIndex
     * @param indexNamePrefix
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String reindex(final boolean clearIndex, final String indexNamePrefix) throws EscidocException,
        InternalClientException, TransportException {
        try {
            return getClient().reindex(clearIndex ? "true" : "false", indexNamePrefix);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return null;
    }

    /**
     * 
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String getRepositoryInfo() throws EscidocException, InternalClientException, TransportException {
        try {
            return getClient().getRepositoryInfo();
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return null;
    }

    /**
     * 
     * @param exampleSet
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String loadExamples(final String exampleSet) throws EscidocException, InternalClientException,
        TransportException {
        try {
            return getClient().loadExamples(exampleSet);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return null;
    }

    /**
     * 
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String getIndexConfiguration() throws EscidocException, InternalClientException, TransportException {
        try {
            return getClient().getIndexConfiguration();
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
    public AdminHandler getClient() throws InternalClientException {
        try {
            if (soapClient == null) {
                final AdminHandlerServiceLocator serviceLocator = new AdminHandlerServiceLocator(getEngineConfig());
                final URL url = getHandlerServiceURL(serviceLocator.getAdminHandlerServiceAddress());
                soapClient = serviceLocator.getAdminHandlerService(url);
                registerPWCallback(soapClient);
            }
        }
        catch (final ServiceException e) {
            throw new InternalClientException(e.getMessage(), e);
        }
        return soapClient;
    }
}