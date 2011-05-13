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
import de.escidoc.core.client.interfaces.SetDefinitionHandler;
import de.escidoc.core.client.rest.serviceLocator.SetDefinitionRestServiceLocator;

/**
 * @author MVO
 * 
 */
public class RestSetDefinitionHandlerClient extends RestClientBase {

    private SetDefinitionHandler client;

    private static final Logger LOG = Logger.getLogger(RestSetDefinitionHandlerClient.class);

    /**
     * @throws InternalClientException
     */
    public RestSetDefinitionHandlerClient() throws InternalClientException {
        super();
    }

    /**
     * @param serviceAddress
     * @throws InternalClientException
     */
    public RestSetDefinitionHandlerClient(final URL serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * @param serviceAddress
     * @throws InternalClientException
     * @deprecated Use
     *             {@link RestSetDefinitionHandlerClient#RestSetDefinitionHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public RestSetDefinitionHandlerClient(final String serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveSetDefinitions(final HashMap<String, String> filter) throws EscidocException,
        InternalClientException, TransportException {
        String result = null;
        try {
            result = getClient().retrieveSetDefinitions(filter);
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
    public String retrieveSetDefinitions(final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        evalRequest(request, true);

        String result = null;
        try {
            result = getClient().retrieveSetDefinitions(request);
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
    public String retrieveSetDefinitions(final ExplainRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        evalRequest(request);

        String result = null;
        try {
            result = getClient().retrieveSetDefinitions(request);
        }
        catch (Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @param setDefinitionId
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void delete(final String setDefinitionId) throws EscidocException, InternalClientException,
        TransportException {

        try {
            getClient().delete(setDefinitionId);
        }
        catch (Exception e) {
            ExceptionMapper.map(e, LOG);
        }
    }

    /**
     * @param xmlData
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String create(final String xmlData) throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().create(xmlData);
        }
        catch (Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @param setDefinitionId
     * @param xmlData
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String update(final String setDefinitionId, final String xmlData) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().update(setDefinitionId, xmlData);
        }
        catch (Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @param setDefinitionId
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieve(final String setDefinitionId) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().retrieve(setDefinitionId);
        }
        catch (Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    @Override
    public SetDefinitionHandler getClient() throws InternalClientException {
        if (client == null) {

            SetDefinitionRestServiceLocator serviceLocator = new SetDefinitionRestServiceLocator();
            serviceLocator.registerRestCallbackHandler(this);
            serviceLocator.setServiceAddress(getServiceAddress());
            client = serviceLocator;
        }
        return this.client;
    }

}
