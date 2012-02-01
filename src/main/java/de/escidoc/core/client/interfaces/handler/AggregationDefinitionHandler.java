/**
 * 
 */
package de.escidoc.core.client.interfaces.handler;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.rmi.Remote;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.application.invalid.InvalidXmlException;
import de.escidoc.core.client.exceptions.application.invalid.XmlCorruptedException;
import de.escidoc.core.client.exceptions.application.invalid.XmlSchemaValidationException;
import de.escidoc.core.client.exceptions.application.missing.MissingMethodParameterException;
import de.escidoc.core.client.exceptions.application.notfound.AggregationDefinitionNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.ScopeNotFoundException;
import de.escidoc.core.client.exceptions.application.security.AuthenticationException;
import de.escidoc.core.client.exceptions.application.security.AuthorizationException;
import de.escidoc.core.client.exceptions.system.SystemException;

/**
 * @author MVO
 * 
 */
public interface AggregationDefinitionHandler extends Remote {

    /**
     * @param request
     * @return
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws InvalidXmlException
     * @throws MissingMethodParameterException
     */
    String retrieveAggregationDefinitions(SearchRetrieveRequestType request) throws EscidocException,
        AuthorizationException, AuthenticationException, InvalidXmlException, MissingMethodParameterException;

    /**
     * @param request
     * @return
     * 
     * 
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws InvalidXmlException
     * @throws MissingMethodParameterException
     */
    String retrieveAggregationDefinitions(ExplainRequestType request) throws EscidocException, AuthorizationException,
        AuthenticationException, InvalidXmlException, MissingMethodParameterException;

    /**
     * @param id
     * 
     * 
     * @throws AuthorizationException
     * @throws AggregationDefinitionNotFoundException
     * @throws AuthenticationException
     * @throws MissingMethodParameterException
     */
    void delete(final String id) throws EscidocException, AuthorizationException,
        AggregationDefinitionNotFoundException, AuthenticationException, MissingMethodParameterException;

    /**
     * @param xml
     * @return
     * 
     * @throws XmlSchemaValidationException
     * @throws XmlCorruptedException
     * @throws AuthorizationException
     * @throws ScopeNotFoundException
     * @throws AuthenticationException
     * @throws MissingMethodParameterException
     * @throws EscidocException
     */
    String create(final String xml) throws EscidocException, XmlSchemaValidationException, SystemException,
        XmlCorruptedException, AuthorizationException, ScopeNotFoundException, AuthenticationException,
        MissingMethodParameterException, EscidocException;

    /**
     * @param id
     * @return
     * 
     * 
     * @throws AuthorizationException
     * @throws AggregationDefinitionNotFoundException
     * @throws AuthenticationException
     * @throws MissingMethodParameterException
     */
    String retrieve(final String id) throws EscidocException, AuthorizationException,
        AggregationDefinitionNotFoundException, AuthenticationException, MissingMethodParameterException;
}
