/**
 * 
 */
package de.escidoc.core.client.soap;

import java.net.URL;

import javax.xml.rpc.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.sm.ReportHandler;
import de.escidoc.core.sm.ReportHandlerServiceLocator;

/**
 * @author MVO
 * 
 */
public class SoapReportHandlerClient extends SoapClientBase {

    private static final Logger LOG = LoggerFactory.getLogger(SoapReportHandlerClient.class);

    private ReportHandler client;

    /**
     * @param serviceAddress
     * @throws InternalClientException
     */
    public SoapReportHandlerClient(final URL serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * @param serviceAddress
     * @throws InternalClientException
     * @deprecated Use
     *             {@link SoapReportHandlerClient#SoapReportHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public SoapReportHandlerClient(final String serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param xml
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieve(final String xml) throws EscidocException, InternalClientException, TransportException {
        if (xml == null)
            throw new IllegalArgumentException("xml must not be null.");

        String resultXml = null;
        try {
            resultXml = getClient().retrieve(xml);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return resultXml;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    @Override
    public ReportHandler getClient() throws InternalClientException {
        if (client == null) {
            final ReportHandlerServiceLocator serviceLocator = new ReportHandlerServiceLocator(getEngineConfig());
            final URL url = getHandlerServiceURL(serviceLocator.getReportHandlerServiceAddress());
            try {
                client = serviceLocator.getReportHandlerService(url);
            }
            catch (final ServiceException e) {
                throw new InternalClientException(e.getMessage(), e);
            }
            registerPWCallback(client);
        }
        return client;
    }
}