/**
 * 
 */
package de.escidoc.core.client.rest;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.net.MalformedURLException;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.ReportDefinitionHandler;
import de.escidoc.core.client.rest.serviceLocator.ReportDefinitionRestServiceLocator;

/**
 * @author MVO
 * 
 */
public class RestReportDefinitionHandlerClient
    extends RestClientBase {

    private static final Logger LOG = Logger
        .getLogger(RestReportDefinitionHandlerClient.class);
    
    private ReportDefinitionHandler client;

    /**
     * 
     * @throws InternalClientException
     */
    public RestReportDefinitionHandlerClient() throws InternalClientException {
        super();
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public RestReportDefinitionHandlerClient(final String serviceAddress)
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

        String xml = null;
        try {
            xml = getClient().retrieveReportDefinitions(request);
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
    public String retrieveReportDefinitions(final ExplainRequestType request)
        throws EscidocException, InternalClientException, TransportException {

        evalRequest(request);

        String xml = null;
        try {
            xml = getClient().retrieveReportDefinitions(request);
        }
        catch (Exception e) {
            if (LOG.isDebugEnabled())
                LOG.debug(e.getMessage());
            ExceptionMapper.map(e);
        }
        return xml;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.ClientBase#getLastModificationDate(java.lang.String
     * )
     */
    @Deprecated
    @Override
    public DateTime getLastModificationDate(final String id)
        throws EscidocException, InternalClientException, TransportException {

        throw new UnsupportedOperationException("Method no longer supported.");
    }

    /* (non-Javadoc)
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    @Override
    public ReportDefinitionHandler getClient() throws InternalClientException {
        if (this.client == null) {

            ReportDefinitionRestServiceLocator serviceLocator =
                new ReportDefinitionRestServiceLocator();
            try {
                serviceLocator.setServiceAddress(getServiceAddress());
            }
            catch (MalformedURLException e) {
                throw new InternalClientException(e);
            }
            serviceLocator.registerRestCallbackHandler(this);
            this.client = serviceLocator;
        }
        return this.client;
    }
}
