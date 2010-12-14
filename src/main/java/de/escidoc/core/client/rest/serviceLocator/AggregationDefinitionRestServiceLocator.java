/**
 * 
 */
package de.escidoc.core.client.rest.serviceLocator;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.rmi.RemoteException;
import java.util.HashMap;

import de.escidoc.core.client.interfaces.AggregationDefinitionHandler;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException;
import de.escidoc.core.common.exceptions.remote.application.invalid.XmlCorruptedException;
import de.escidoc.core.common.exceptions.remote.application.invalid.XmlSchemaValidationException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException;
import de.escidoc.core.common.exceptions.remote.application.notfound.AggregationDefinitionNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ScopeNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException;
import de.escidoc.core.common.exceptions.remote.system.SystemException;

/**
 * @author MVO
 * 
 */
public class AggregationDefinitionRestServiceLocator extends RestServiceMethod
    implements AggregationDefinitionHandler {

    private static final String PATH_AD = "/statistic/aggregation-definition";

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.sm.AggregationDefinitionHandler#delete(java.lang.String)
     */
    public void delete(final String id) throws RemoteException,
        SystemException, AuthorizationException,
        AggregationDefinitionNotFoundException, AuthenticationException,
        MissingMethodParameterException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");

        del(PATH_AD + "/" + id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.sm.AggregationDefinitionHandler#create(java.lang.String)
     */
    public String create(final String xml) throws RemoteException,
        XmlSchemaValidationException, SystemException, XmlCorruptedException,
        AuthorizationException, ScopeNotFoundException,
        AuthenticationException, MissingMethodParameterException {

        if (xml == null)
            throw new IllegalArgumentException("xml must not be null.");

        return put(PATH_AD, xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.sm.AggregationDefinitionHandler#retrieve(java.lang.String
     * )
     */
    public String retrieve(final String id) throws RemoteException,
        SystemException, AuthorizationException,
        AggregationDefinitionNotFoundException, AuthenticationException,
        MissingMethodParameterException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");

        return get(PATH_AD + "/" + id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.sm.AggregationDefinitionHandler#
     * retrieveAggregationDefinitions(java.lang.String)
     */
    public String retrieveAggregationDefinitions(final String cqlQuery)
        throws RemoteException, SystemException, AuthorizationException,
        AuthenticationException, InvalidXmlException,
        MissingMethodParameterException {

        throw new UnsupportedOperationException(
            "This method is no longer supported.");
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.sm.AggregationDefinitionHandler#
     * retrieveAggregationDefinitions(java.util.HashMap)
     */
    @SuppressWarnings("rawtypes")
    @Deprecated
    public String retrieveAggregationDefinitions(final HashMap filter)
        throws RemoteException, SystemException, InvalidSearchQueryException,
        AuthorizationException, AuthenticationException,
        MissingMethodParameterException {

        throw new UnsupportedOperationException(
            "This method is no longer supported.");
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.AggregationDefinitionHandler#
     * retrieveAggregationDefinitions
     * (gov.loc.www.zing.srw.SearchRetrieveRequestType)
     */
    public String retrieveAggregationDefinitions(
        final SearchRetrieveRequestType request) throws RemoteException,
        SystemException, AuthorizationException, AuthenticationException,
        InvalidXmlException, MissingMethodParameterException {

        if (request == null)
            throw new IllegalArgumentException("request must not be null.");

        return get(PATH_AD + "s" + getEscidoc12Filter(request));
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.AggregationDefinitionHandler#
     * retrieveAggregationDefinitions(gov.loc.www.zing.srw.ExplainRequestType)
     */
    public String retrieveAggregationDefinitions(
        final ExplainRequestType request) throws RemoteException,
        SystemException, AuthorizationException, AuthenticationException,
        InvalidXmlException, MissingMethodParameterException {

        if (request == null)
            throw new IllegalArgumentException("request must not be null.");

        return get(PATH_AD + "s" + getEscidoc12Filter(request));
    }

}
