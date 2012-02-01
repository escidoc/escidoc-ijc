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
import de.escidoc.core.client.exceptions.application.notfound.ScopeNotFoundException;
import de.escidoc.core.client.exceptions.application.security.AuthenticationException;
import de.escidoc.core.client.exceptions.application.security.AuthorizationException;
import de.escidoc.core.client.exceptions.system.SystemException;
import de.escidoc.core.client.interfaces.handler.ScopeHandler;

/**
 * @author MVO
 * 
 */
public class ScopeRestServiceLocator extends RestServiceMethod implements ScopeHandler {

    public static final String PATH = "/statistic/scope";

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.sm.ScopeHandler#delete(java.lang.String)
     */
    @Override
    public void delete(final String id) throws EscidocException, AuthorizationException, ScopeNotFoundException,
        AuthenticationException, MissingMethodParameterException {

        checkNotNull(id);

        del(PATH + "/" + id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.sm.ScopeHandler#create(java.lang.String)
     */
    @Override
    public String create(final String xml) throws EscidocException, XmlSchemaValidationException, SystemException,
        XmlCorruptedException, AuthorizationException, AuthenticationException, MissingMethodParameterException {

        checkNotNull(xml);

        return put(PATH, xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.sm.ScopeHandler#update(java.lang.String,
     * java.lang.String)
     */
    @Override
    public String update(final String id, final String xml) throws EscidocException, XmlSchemaValidationException,
        SystemException, XmlCorruptedException, AuthorizationException, ScopeNotFoundException,
        AuthenticationException, MissingMethodParameterException {

        checkNotNull(id);
        checkNotNull(xml);

        return put(PATH + "/" + id, xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.sm.ScopeHandler#retrieve(java.lang.String)
     */
    @Override
    public String retrieve(final String id) throws EscidocException, AuthorizationException, ScopeNotFoundException,
        AuthenticationException, MissingMethodParameterException {

        checkNotNull(id);

        return get(PATH + "/" + id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.ScopeHandler#retrieveScopes(gov.loc
     * .www.zing.srw.SearchRetrieveRequestType)
     */
    @Override
    public String retrieveScopes(final SearchRetrieveRequestType request) throws EscidocException,
        AuthorizationException, AuthenticationException, InvalidXmlException, MissingMethodParameterException {

        checkNotNull(request);

        return get(PATH + "s" + getEscidoc12Filter(request));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.ScopeHandler#retrieveScopes(gov.loc
     * .www.zing.srw.ExplainRequestType)
     */
    @Override
    public String retrieveScopes(final ExplainRequestType request) throws EscidocException, AuthorizationException,
        AuthenticationException, InvalidXmlException, MissingMethodParameterException {

        checkNotNull(request);

        return get(PATH + "s" + getEscidoc12Filter(request));
    }
}