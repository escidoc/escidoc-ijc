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

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.sm.AggregationDefinitionHandler;
import de.escidoc.core.sm.AggregationDefinitionHandlerServiceLocator;

/**
 * @author MVO
 * 
 */
public class SoapAggregationDefinitionHandlerClient extends SoapClientBase {

    private static final Logger LOG = Logger
        .getLogger(SoapAggregationDefinitionHandlerClient.class);

    private AggregationDefinitionHandler client;

    /**
     * @throws InternalClientException
     */
    public SoapAggregationDefinitionHandlerClient()
        throws InternalClientException {
        super();
    }

    /**
     * @param serviceAddress
     * @throws InternalClientException
     */
    public SoapAggregationDefinitionHandlerClient(final URL serviceAddress)
        throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * @param serviceAddress
     * @throws InternalClientException
     * @deprecated Use
     *             {@link SoapAggregationDefinitionHandlerClient#SoapAggregationDefinitionHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public SoapAggregationDefinitionHandlerClient(final String serviceAddress)
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
                LOG.debug(e.getMessage(), e);
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
                LOG.debug(e.getMessage(), e);
            ExceptionMapper.map(e);
        }
        return resultXml;
    }

    /**
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

        String resultXml = null;
        try {
            resultXml = getClient().retrieve(id);
        }
        catch (Exception e) {
            if (LOG.isDebugEnabled())
                LOG.debug(e.getMessage(), e);
            ExceptionMapper.map(e);
        }
        return resultXml;
    }

    /**
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveAggregationDefinitions(
        final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        evalRequest(request, true);
        return retrieveAggregationDefinitions(getEscidoc12Filter(request));
    }

    /**
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveAggregationDefinitions(
        final ExplainRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        evalRequest(request);
        return retrieveAggregationDefinitions(getEscidoc12Filter(request));
    }

    /**
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveAggregationDefinitions(
        final HashMap<String, String[]> filter) throws EscidocException,
        InternalClientException, TransportException {

        String resultXml = null;
        try {
            resultXml = getClient().retrieveAggregationDefinitions(filter);
        }
        catch (Exception e) {
            if (LOG.isDebugEnabled())
                LOG.debug(e.getMessage(), e);
            ExceptionMapper.map(e);
        }
        return resultXml;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    @Override
    public AggregationDefinitionHandler getClient()
        throws InternalClientException {
        if (client == null) {
            AggregationDefinitionHandlerServiceLocator serviceLocator =
                new AggregationDefinitionHandlerServiceLocator(
                    getEngineConfig());
            URL url =
                getHandlerServiceURL(serviceLocator
                    .getAggregationDefinitionHandlerServiceAddress());
            try {
                client =
                    serviceLocator.getAggregationDefinitionHandlerService(url);
            }
            catch (ServiceException e) {
                throw new InternalClientException(e.getMessage(), e);
            }
            registerPWCallback(client);
        }

        return client;
    }
}