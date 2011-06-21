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
import de.escidoc.core.sm.ScopeHandler;
import de.escidoc.core.sm.ScopeHandlerServiceLocator;

/**
 * @author MVO
 * 
 */
public class SoapScopeHandlerClient extends SoapClientBase {

    private static final Logger LOG = Logger.getLogger(SoapScopeHandlerClient.class);

    private ScopeHandler client;

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public SoapScopeHandlerClient(final URL serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     * @deprecated Use
     *             {@link SoapScopeHandlerClient#SoapScopeHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public SoapScopeHandlerClient(final String serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * @param id
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void delete(final String id) throws EscidocException, InternalClientException, TransportException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");

        try {
            getClient().delete(id);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
    }

    /**
     * @param xml
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String create(final String xml) throws EscidocException, InternalClientException, TransportException {

        if (xml == null)
            throw new IllegalArgumentException("xml must not be null.");

        String result = null;
        try {
            result = getClient().create(xml);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @param id
     * @param xml
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String update(final String id, final String xml) throws EscidocException, InternalClientException,
        TransportException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");
        if (xml == null)
            throw new IllegalArgumentException("xml must not be null.");

        String result = null;
        try {
            result = getClient().update(id, xml);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieve(final String id) throws EscidocException, InternalClientException, TransportException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");

        String result = null;
        try {
            result = getClient().retrieve(id);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveScopes(final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        evalRequest(request, true);
        return retrieveScopes(getEscidoc12Filter(request));
    }

    /**
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveScopes(final HashMap<String, String[]> filter) throws EscidocException,
        InternalClientException, TransportException {

        String resultXml = null;
        try {
            resultXml = getClient().retrieveScopes(filter);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
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
    public String retrieveScopes(final ExplainRequestType request) throws EscidocException, InternalClientException,
        TransportException {

        evalRequest(request);
        return retrieveScopes(getEscidoc12Filter(request));
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    @Override
    public ScopeHandler getClient() throws InternalClientException {
        if (client == null) {
            final ScopeHandlerServiceLocator serviceLocator = new ScopeHandlerServiceLocator(getEngineConfig());
            final URL url = getHandlerServiceURL(serviceLocator.getScopeHandlerServiceAddress());
            try {
                client = serviceLocator.getScopeHandlerService(url);
            }
            catch (final ServiceException e) {
                throw new InternalClientException(e.getMessage(), e);
            }
            registerPWCallback(client);
        }

        return client;
    }
}