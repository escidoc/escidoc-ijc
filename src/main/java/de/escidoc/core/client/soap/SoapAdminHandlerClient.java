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

	private static final Logger log = Logger
			.getLogger(SoapAdminHandlerClient.class);

    private AdminHandler soapClient = null;

    /**
     * 
     * @throws InternalClientException
     */
    public SoapAdminHandlerClient() throws InternalClientException {
        super();
    }

    /**
     * 
     * @param serviceAdress
     * @throws InternalClientException
     */
    public SoapAdminHandlerClient(final URL serviceAdress)
        throws InternalClientException {
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
    public SoapAdminHandlerClient(final String serviceAdress)
        throws InternalClientException {
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
    public String deleteObjects(final String taskParam)
        throws EscidocException, InternalClientException, TransportException {
        try {
            return getClient().deleteObjects(taskParam);
        }
        catch (Exception e) {
            ExceptionMapper.map(e, log);
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
    public String getPurgeStatus() throws EscidocException,
        InternalClientException, TransportException {
        try {
            return getClient().getPurgeStatus();
        }
        catch (Exception e) {
            ExceptionMapper.map(e, log);
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
    public String getReindexStatus() throws EscidocException,
        InternalClientException, TransportException {
        try {
            return getClient().getReindexStatus();
        }
        catch (Exception e) {
            ExceptionMapper.map(e, log);
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
    public String reindex(final boolean clearIndex, final String indexNamePrefix)
        throws EscidocException, InternalClientException, TransportException {
        try {
            return getClient().reindex(clearIndex ? "true" : "false",
                indexNamePrefix);
        }
        catch (Exception e) {
            ExceptionMapper.map(e, log);
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
    public String getRepositoryInfo() throws EscidocException,
        InternalClientException, TransportException {
        try {
            return getClient().getRepositoryInfo();
        }
        catch (Exception e) {
            ExceptionMapper.map(e, log);
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
    public String loadExamples(final String exampleSet)
        throws EscidocException, InternalClientException, TransportException {
        try {
            return getClient().loadExamples(exampleSet);
        }
        catch (Exception e) {
            ExceptionMapper.map(e, log);
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
    public String getIndexConfiguration() throws EscidocException,
        InternalClientException, TransportException {
        try {
            return getClient().getIndexConfiguration();
        }
        catch (Exception e) {
            ExceptionMapper.map(e, log);
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
                AdminHandlerServiceLocator serviceLocator =
                    new AdminHandlerServiceLocator(getEngineConfig());
                URL url =
                    getHandlerServiceURL(serviceLocator
                        .getAdminHandlerServiceAddress());
                soapClient = serviceLocator.getAdminHandlerService(url);
                registerPWCallback(soapClient);
            }
        }
        catch (ServiceException e) {
            throw new InternalClientException(e.getMessage(), e);
        }
        return soapClient;
    }
}