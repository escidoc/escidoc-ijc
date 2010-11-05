/**
 * 
 */
package de.escidoc.core.client.soap;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.net.URL;
import java.util.HashMap;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.sm.ReportDefinitionHandler;
import de.escidoc.core.sm.ReportDefinitionHandlerServiceLocator;

/**
 * @author MVO
 * 
 */
public class SoapReportDefinitionHandlerClient extends SoapClientBase {

    private static final Logger LOG = Logger
        .getLogger(SoapReportDefinitionHandlerClient.class);

    private ReportDefinitionHandler client;

    /**
     * 
     * @throws InternalClientException
     */
    public SoapReportDefinitionHandlerClient() throws InternalClientException {
        super();
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public SoapReportDefinitionHandlerClient(final String serviceAddress)
        throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param id
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void delete(final String id) throws EscidocException,
        InternalClientException, TransportException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");

        try {
            getClient().delete(id);
        }
        catch (Exception e) {
            if (LOG.isDebugEnabled())
                LOG.debug(e.getMessage());
            ExceptionMapper.map(e);
        }
    }

    /**
     * 
     * @param xml
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String create(final String xml) throws EscidocException,
        InternalClientException, TransportException {

        if (xml == null)
            throw new IllegalArgumentException("xml must not be null.");

        String resultXml = null;
        try {
            resultXml = getClient().create(xml);
        }
        catch (Exception e) {
            if (LOG.isDebugEnabled())
                LOG.debug(e.getMessage());
            ExceptionMapper.map(e);
        }
        return resultXml;
    }

    /**
     * 
     * @param id
     * @param xml
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String update(final String id, final String xml)
        throws EscidocException, InternalClientException, TransportException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");
        if (xml == null)
            throw new IllegalArgumentException("xml must not be null.");

        String resultXml = null;
        try {
            resultXml = getClient().update(id, xml);
        }
        catch (Exception e) {
            if (LOG.isDebugEnabled())
                LOG.debug(e.getMessage());
            ExceptionMapper.map(e);
        }
        return resultXml;
    }

    /**
     * 
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieve(final String id) throws EscidocException,
        InternalClientException, TransportException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");

        String xml = null;
        try {
            xml = getClient().retrieve(id);
        }
        catch (Exception e) {
            if (LOG.isDebugEnabled())
                LOG.debug(e.getMessage());
            ExceptionMapper.map(e);
        }
        return xml;
    }

    /**
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    @SuppressWarnings("rawtypes")
    public String retrieveReportDefinitions(final HashMap filter)
        throws EscidocException, InternalClientException, TransportException {

        if (filter == null)
            throw new IllegalArgumentException("filter must not be null.");

        String xml = null;
        try {
            xml = getClient().retrieveReportDefinitions(filter);
        }
        catch (Exception e) {
            if (LOG.isDebugEnabled())
                LOG.debug(e.getMessage());
            ExceptionMapper.map(e);
        }
        return xml;
    }

    /**
     * 
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveReportDefinitions(
        final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        evalRequest(request, true);
        return filterRoles(getEscidoc12Filter(request));
    }

    /**
     * 
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveReportDefinitions(final ExplainRequestType request)
        throws EscidocException, InternalClientException, TransportException {

        evalRequest(request);
        return filterRoles(getEscidoc12Filter(request));
    }

    /**
     * generic filter method request.
     * 
     * @param escidoc12Filter
     *            data structure for eSciDoc 1.2 filter
     * @return filter response
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    private String filterRoles(final HashMap<String, String[]> escidoc12Filter)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveReportDefinitions(escidoc12Filter);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    @Override
    public ReportDefinitionHandler getClient() throws InternalClientException {

        if (client == null) {
            ReportDefinitionHandlerServiceLocator serviceLocator =
                new ReportDefinitionHandlerServiceLocator(getEngineConfig());
            URL url =
                getHandlerServiceURL(serviceLocator
                    .getReportDefinitionHandlerServiceAddress());
            try {
                client = serviceLocator.getReportDefinitionHandlerService(url);
            }
            catch (ServiceException e) {
                throw new InternalClientException(e.getMessage(), e);
            }
            registerPWCallback(client);
        }

        return client;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.ClientBase#getLastModificationDate(java.lang.String
     * )
     */
    @Override
    public DateTime getLastModificationDate(String id) throws EscidocException,
        InternalClientException, TransportException {

        throw new UnsupportedOperationException("Method not supported.");
    }

}
