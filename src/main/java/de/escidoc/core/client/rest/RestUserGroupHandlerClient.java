/**
 * 
 */
package de.escidoc.core.client.rest;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.handler.UserGroupHandler;
import de.escidoc.core.client.rest.serviceLocator.UserGroupRestServiceLocator;

/**
 * @author MVO
 * 
 */
public class RestUserGroupHandlerClient extends RestClientBase {

    private UserGroupHandler client;

    private static final Logger LOG = LoggerFactory.getLogger(RestUserGroupHandlerClient.class);

    /**
     * @param serviceAddress
     * @throws InternalClientException
     */
    public RestUserGroupHandlerClient(final URL serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * @param userGroupId
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveCurrentGrants(final String userGroupId) throws EscidocException, InternalClientException,
        TransportException {
        String result = null;
        try {
            result = getClient().retrieveCurrentGrants(userGroupId);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @param groupId
     * @param grantXML
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String createGrant(final String groupId, final String grantXML) throws EscidocException,
        InternalClientException, TransportException {
        String result = null;
        try {
            result = getClient().createGrant(groupId, grantXML);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @param groupId
     * @param grantId
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveGrant(final String groupId, final String grantId) throws EscidocException,
        InternalClientException, TransportException {
        String result = null;
        try {
            result = getClient().retrieveGrant(groupId, grantId);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @param groupId
     * @param grantId
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void revokeGrant(final String groupId, final String grantId, final String taskParam)
        throws EscidocException, InternalClientException, TransportException {
        try {
            getClient().revokeGrant(groupId, grantId, taskParam);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
    }

    /**
     * @param groupId
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void revokeGrants(final String groupId, final String taskParam) throws EscidocException,
        InternalClientException, TransportException {
        try {
            getClient().revokeGrants(groupId, taskParam);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
    }

    /**
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveUserGroups(final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        evalRequest(request, true);

        String result = null;
        try {
            result = getClient().retrieveUserGroups(request);
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
    public String retrieveUserGroups(final ExplainRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        evalRequest(request);

        String result = null;
        try {
            result = getClient().retrieveUserGroups(request);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @param groupId
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String addSelectors(final String groupId, final String taskParam) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().addSelectors(groupId, taskParam);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @param groupId
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String removeSelectors(final String groupId, final String taskParam) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().removeSelectors(groupId, taskParam);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @param groupId
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void delete(final String groupId) throws EscidocException, InternalClientException, TransportException {
        try {
            getClient().delete(groupId);
        }
        catch (final Exception e) {
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
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @param groupId
     * @param xmlData
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String update(final String groupId, final String xmlData) throws EscidocException, InternalClientException,
        TransportException {

        String result = null;
        try {
            result = getClient().update(groupId, xmlData);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @param groupId
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void activate(final String groupId, final String taskParam) throws EscidocException,
        InternalClientException, TransportException {

        try {
            getClient().activate(groupId, taskParam);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
    }

    /**
     * @param groupId
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieve(final String groupId) throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieve(groupId);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
        return result;
    }

    /**
     * @param groupId
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void deactivate(final String groupId, final String taskParam) throws EscidocException,
        InternalClientException, TransportException {

        try {
            getClient().deactivate(groupId, taskParam);
        }
        catch (final Exception e) {
            ExceptionMapper.map(e, LOG);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    @Override
    public UserGroupHandler getClient() throws InternalClientException {

        if (client == null) {

            final UserGroupRestServiceLocator serviceLocator = new UserGroupRestServiceLocator();
            serviceLocator.registerRestCallbackHandler(this);
            serviceLocator.setServiceAddress(getServiceAddress());
            client = serviceLocator;
        }
        return this.client;
    }
}