/**
 * 
 */
package de.escidoc.core.client.rest.serviceLocator;

import static de.escidoc.core.common.Precondition.checkNotNull;
import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;
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
import de.escidoc.core.client.interfaces.handler.AggregationDefinitionHandler;

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
    public void delete(final String id) throws EscidocException, AuthorizationException,
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
    public String create(final String xml) throws EscidocException, XmlSchemaValidationException, SystemException,
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
    public String retrieve(final String id) throws EscidocException, AuthorizationException,
        AggregationDefinitionNotFoundException, AuthenticationException, MissingMethodParameterException {

        checkNotNull(id);

        return get(PATH + "/" + id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.AggregationDefinitionHandler#
     * retrieveAggregationDefinitions
     * (gov.loc.www.zing.srw.SearchRetrieveRequestType)
     */
    @Override
    public String retrieveAggregationDefinitions(final SearchRetrieveRequestType request) throws EscidocException,
        AuthorizationException, AuthenticationException, InvalidXmlException, MissingMethodParameterException {

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
    public String retrieveAggregationDefinitions(final ExplainRequestType request) throws EscidocException,
        AuthorizationException, AuthenticationException, InvalidXmlException, MissingMethodParameterException {

        checkNotNull(request);

        return get(PATH + "s" + getEscidoc12Filter(request));
    }
}