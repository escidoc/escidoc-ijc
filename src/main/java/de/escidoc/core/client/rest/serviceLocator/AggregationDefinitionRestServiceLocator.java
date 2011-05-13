/**
 * 
 */
package de.escidoc.core.client.rest.serviceLocator;

import static de.escidoc.core.common.Precondition.checkNotNull;
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
public class AggregationDefinitionRestServiceLocator extends RestServiceMethod implements AggregationDefinitionHandler {

    public static final String PATH = "/statistic/aggregation-definition";

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.sm.AggregationDefinitionHandler#delete(java.lang.String)
     */
    @Override
    public void delete(final String id) throws RemoteException, SystemException, AuthorizationException,
        AggregationDefinitionNotFoundException, AuthenticationException, MissingMethodParameterException {

        checkNotNull(id);

        del(PATH + "/" + id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.sm.AggregationDefinitionHandler#create(java.lang.String)
     */
    @Override
    public String create(final String xml) throws RemoteException, XmlSchemaValidationException, SystemException,
        XmlCorruptedException, AuthorizationException, ScopeNotFoundException, AuthenticationException,
        MissingMethodParameterException {

        checkNotNull(xml);

        return put(PATH, xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.sm.AggregationDefinitionHandler#retrieve(java.lang.String
     * )
     */
    @Override
    public String retrieve(final String id) throws RemoteException, SystemException, AuthorizationException,
        AggregationDefinitionNotFoundException, AuthenticationException, MissingMethodParameterException {

        checkNotNull(id);

        return get(PATH + "/" + id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.sm.AggregationDefinitionHandler#
     * retrieveAggregationDefinitions(java.util.HashMap)
     */
    @Override
    @SuppressWarnings( { "rawtypes", "unchecked" })
    @Deprecated
    public String retrieveAggregationDefinitions(final HashMap filter) throws RemoteException, SystemException,
        InvalidSearchQueryException, AuthorizationException, AuthenticationException, MissingMethodParameterException {

        return get(PATH + "s", filter);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.AggregationDefinitionHandler#
     * retrieveAggregationDefinitions
     * (gov.loc.www.zing.srw.SearchRetrieveRequestType)
     */
    @Override
    public String retrieveAggregationDefinitions(final SearchRetrieveRequestType request) throws RemoteException,
        SystemException, AuthorizationException, AuthenticationException, InvalidXmlException,
        MissingMethodParameterException {

        checkNotNull(request);

        return get(PATH + "s" + getEscidoc12Filter(request));
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.AggregationDefinitionHandler#
     * retrieveAggregationDefinitions(gov.loc.www.zing.srw.ExplainRequestType)
     */
    @Override
    public String retrieveAggregationDefinitions(final ExplainRequestType request) throws RemoteException,
        SystemException, AuthorizationException, AuthenticationException, InvalidXmlException,
        MissingMethodParameterException {

        checkNotNull(request);

        return get(PATH + "s" + getEscidoc12Filter(request));
    }
}