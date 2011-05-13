/**
 * 
 */
package de.escidoc.core.client.rest;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.net.URL;
import java.util.HashMap;

import org.apache.log4j.Logger;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.ScopeHandler;
import de.escidoc.core.client.rest.serviceLocator.ScopeRestServiceLocator;

/**
 * @author MVO
 * 
 */
public class RestScopeHandlerClient extends RestClientBase {

    private static final Logger LOG = Logger.getLogger(RestScopeHandlerClient.class);

    private ScopeHandler client;

    /**
     * 
     * @throws InternalClientException
     */
    public RestScopeHandlerClient() throws InternalClientException {
        super();
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     */
    public RestScopeHandlerClient(final URL serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     * @deprecated Use
     *             {@link RestScopeHandlerClient#RestScopeHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public RestScopeHandlerClient(final String serviceAddress) throws InternalClientException {
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
        catch (Exception e) {
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
        catch (Exception e) {
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
        catch (Exception e) {
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
        catch (Exception e) {
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

        String resultXml = null;
        try {
            resultXml = getClient().retrieveScopes(request);
        }
        catch (Exception e) {
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
    @Deprecated
    public String retrieveScopes(final HashMap<String, String[]> request) throws EscidocException,
        InternalClientException, TransportException {

        String resultXml = null;
        try {
            resultXml = getClient().retrieveScopes(request);
        }
        catch (Exception e) {
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

        String resultXml = null;
        try {
            resultXml = getClient().retrieveScopes(request);
        }
        catch (Exception e) {
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
    public ScopeHandler getClient() throws InternalClientException {
        if (this.client == null) {

            ScopeRestServiceLocator serviceLocator = new ScopeRestServiceLocator();
            serviceLocator.setServiceAddress(getServiceAddress());
            serviceLocator.registerRestCallbackHandler(this);
            this.client = serviceLocator;
        }
        return this.client;
    }
}