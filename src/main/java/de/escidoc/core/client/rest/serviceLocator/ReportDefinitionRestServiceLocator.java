/**
 * 
 */
package de.escidoc.core.client.rest.serviceLocator;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import de.escidoc.core.client.interfaces.ReportDefinitionHandler;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSearchQueryException;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidSqlException;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException;
import de.escidoc.core.common.exceptions.remote.application.invalid.XmlCorruptedException;
import de.escidoc.core.common.exceptions.remote.application.invalid.XmlSchemaValidationException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ReportDefinitionNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ScopeNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException;
import de.escidoc.core.common.exceptions.remote.application.violated.ScopeContextViolationException;
import de.escidoc.core.common.exceptions.remote.system.SystemException;

/**
 * @author MVO
 * 
 */
public class ReportDefinitionRestServiceLocator extends RestServiceMethod
    implements ReportDefinitionHandler {

    private static final String PATH = "/statistic/report-definition";

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.sm.ReportDefinitionHandler#delete(java.lang.String)
     */
    public void delete(final String id) throws RemoteException,
        SystemException, AuthorizationException, AuthenticationException,
        ReportDefinitionNotFoundException, MissingMethodParameterException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");

        del(PATH + "/" + id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.sm.ReportDefinitionHandler#create(java.lang.String)
     */
    public String create(final String xml) throws RemoteException,
        XmlSchemaValidationException, SystemException, XmlCorruptedException,
        InvalidSqlException, AuthorizationException, ScopeNotFoundException,
        AuthenticationException, ScopeContextViolationException,
        MissingMethodParameterException {

        if (xml == null)
            throw new IllegalArgumentException("xml must not be null.");

        return put(PATH, xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.sm.ReportDefinitionHandler#update(java.lang.String,
     * java.lang.String)
     */
    public String update(final String id, final String xml)
        throws RemoteException, XmlSchemaValidationException, SystemException,
        XmlCorruptedException, InvalidSqlException, AuthorizationException,
        ScopeNotFoundException, AuthenticationException,
        ReportDefinitionNotFoundException, ScopeContextViolationException,
        MissingMethodParameterException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");
        if (xml == null)
            throw new IllegalArgumentException("xml must not be null.");

        return put(PATH + "/" + id, xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.sm.ReportDefinitionHandler#retrieve(java.lang.String)
     */
    public String retrieve(final String id) throws RemoteException,
        SystemException, AuthorizationException, AuthenticationException,
        ReportDefinitionNotFoundException, MissingMethodParameterException {

        if (id == null)
            throw new IllegalArgumentException("id must not be null.");

        return get(PATH + "/" + id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.sm.ReportDefinitionHandler#retrieveReportDefinitions(
     * java.util.HashMap)
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public String retrieveReportDefinitions(final HashMap filter)
        throws RemoteException, SystemException, InvalidSearchQueryException,
        AuthorizationException, AuthenticationException,
        MissingMethodParameterException {

        if (filter == null)
            throw new IllegalArgumentException("filter must not be null.");

        return get(PATH + "s", (Map<String, String[]>) filter);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.ReportDefinitionHandler#
     * retrieveReportDefinitions(gov.loc.www.zing.srw.SearchRetrieveRequestType)
     */
    public String retrieveReportDefinitions(
        final SearchRetrieveRequestType request) throws RemoteException,
        SystemException, AuthorizationException, AuthenticationException,
        InvalidXmlException, MissingMethodParameterException {

        if (request == null)
            throw new IllegalArgumentException("request must not be null.");

        return get(PATH + "s" + getEscidoc12Filter(request));
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.ReportDefinitionHandler#
     * retrieveReportDefinitions(gov.loc.www.zing.srw.ExplainRequestType)
     */
    public String retrieveReportDefinitions(final ExplainRequestType request)
        throws RemoteException, SystemException, AuthorizationException,
        AuthenticationException, InvalidXmlException,
        MissingMethodParameterException {

        if (request == null)
            throw new IllegalArgumentException("request must not be null.");

        return get(PATH + "s" + getEscidoc12Filter(request));
    }

}
