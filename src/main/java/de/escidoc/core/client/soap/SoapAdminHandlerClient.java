/**
 * 
 */
package de.escidoc.core.client.soap;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Remote;

import javax.xml.rpc.ServiceException;

import org.joda.time.DateTime;

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
            return soapClient.deleteObjects(taskParam);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
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
            return soapClient.getPurgeStatus();
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
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
    public String getRecacheStatus() throws EscidocException,
        InternalClientException, TransportException {
        try {
            return soapClient.getRecacheStatus();
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
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
            return soapClient.getReindexStatus();
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return null;
    }

    /**
     * 
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void decreaseReindexStatus(final String taskParam)
        throws EscidocException, InternalClientException, TransportException {
        try {
            soapClient.decreaseReindexStatus(taskParam);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
    }

    /**
     * 
     * @param clearCache
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String recache(final boolean clearCache) throws EscidocException,
        InternalClientException, TransportException {
        try {
            return soapClient.recache(clearCache ? "true" : "false");
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
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
            return soapClient.reindex(clearIndex ? "true" : "false",
                indexNamePrefix);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
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
            return soapClient.getRepositoryInfo();
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return null;
    }

    /**
     * 
     * @param type
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String loadExamples(final String type) throws EscidocException,
        InternalClientException, TransportException {
        try {
            return soapClient.loadExamples(type);
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
    public Remote getClient() throws InternalClientException {
        try {
            if (soapClient == null) {
                AdminHandlerServiceLocator serviceLocator =
                    new AdminHandlerServiceLocator(getEngineConfig());
                String adress = serviceLocator.getAdminHandlerServiceAddress();
                URL url = null;
                try {
                    url = new URL(adress);
                }
                catch (MalformedURLException e) {
                    throw new InternalClientException(e);
                }
                String path = url.getFile();
                adress = getServiceAddress() + path;

                try {
                    url = new URL(adress);
                }
                catch (MalformedURLException e) {
                    throw new ServiceException(e);
                }
                soapClient = serviceLocator.getAdminHandlerService(url);
            }
        }
        catch (ServiceException e) {
            throw new InternalClientException(e.getMessage(), e);
        }
        return soapClient;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.ClientBase#getLastModificationDate(java.lang.String
     * )
     */
    public DateTime getLastModificationDate(String id) throws EscidocException,
        InternalClientException, TransportException {
        return null;
    }

}
