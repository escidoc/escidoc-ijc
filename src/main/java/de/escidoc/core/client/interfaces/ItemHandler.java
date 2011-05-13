package de.escidoc.core.client.interfaces;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.rmi.RemoteException;
import java.util.Map;

import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ComponentNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ContentStreamNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ItemNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException;
import de.escidoc.core.common.exceptions.remote.system.SystemException;
import de.escidoc.core.resources.HttpInputStream;

/**
 * Extend the ItemHandler with methods with additional parameter types.
 * 
 * @author SWA
 * 
 */
public interface ItemHandler extends de.escidoc.core.om.ItemHandler {

    String retrieveItems(final SearchRetrieveRequestType filter) throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException;

    String retrieveItems(final ExplainRequestType filter) throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException;

    HttpInputStream retrieveContentStreamContent(final String itemId, final String contentStreamId)
        throws RemoteException, SystemException, ContentStreamNotFoundException, MissingMethodParameterException,
        AuthenticationException, ItemNotFoundException, AuthorizationException;

    HttpInputStream retrieveContent(final String itemId, final String componentId) throws RemoteException,
        SystemException, ComponentNotFoundException, MissingMethodParameterException, AuthenticationException,
        ItemNotFoundException, AuthorizationException;

    HttpInputStream retrieveContent(
        final String itemId, final String componentId, final String transformer, final Map<String, String[]> transParams)
        throws RemoteException, SystemException, ComponentNotFoundException, MissingMethodParameterException,
        AuthenticationException, ItemNotFoundException, AuthorizationException;
}