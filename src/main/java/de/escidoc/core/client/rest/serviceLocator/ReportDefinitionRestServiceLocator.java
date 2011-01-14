/**
 * 
 */
package de.escidoc.core.client.rest.serviceLocator;

import static de.escidoc.core.common.Precondition.checkNotNull;
import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.rmi.RemoteException;
import java.util.HashMap;

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
    @Override
    public void delete(final String id) throws RemoteException,
        SystemException, AuthorizationException, AuthenticationException,
        ReportDefinitionNotFoundException, MissingMethodParameterException {

        checkNotNull(id);

        del(PATH + "/" + id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.sm.ReportDefinitionHandler#create(java.lang.String)
     */
    @Override
    public String create(final String xml) throws RemoteException,
        XmlSchemaValidationException, SystemException, XmlCorruptedException,
        InvalidSqlException, AuthorizationException, ScopeNotFoundException,
        AuthenticationException, ScopeContextViolationException,
        MissingMethodParameterException {

        checkNotNull(xml);

        return put(PATH, xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.sm.ReportDefinitionHandler#update(java.lang.String,
     * java.lang.String)
     */
    @Override
    public String update(final String id, final String xml)
        throws RemoteException, XmlSchemaValidationException, SystemException,
        XmlCorruptedException, InvalidSqlException, AuthorizationException,
        ScopeNotFoundException, AuthenticationException,
        ReportDefinitionNotFoundException, ScopeContextViolationException,
        MissingMethodParameterException {

        checkNotNull(id);
        checkNotNull(xml);

        return put(PATH + "/" + id, xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.sm.ReportDefinitionHandler#retrieve(java.lang.String)
     */
    @Override
    public String retrieve(final String id) throws RemoteException,
        SystemException, AuthorizationException, AuthenticationException,
        ReportDefinitionNotFoundException, MissingMethodParameterException {

        checkNotNull(id);

        return get(PATH + "/" + id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.sm.ReportDefinitionHandler#retrieveReportDefinitions(
     * java.util.HashMap)
     */
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Deprecated
    public String retrieveReportDefinitions(final HashMap filter)
        throws RemoteException, SystemException, InvalidSearchQueryException,
        AuthorizationException, AuthenticationException,
        MissingMethodParameterException {

        return get(PATH + "s", filter);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.ReportDefinitionHandler#
     * retrieveReportDefinitions(gov.loc.www.zing.srw.SearchRetrieveRequestType)
     */
    @Override
    public String retrieveReportDefinitions(
        final SearchRetrieveRequestType request) throws RemoteException,
        SystemException, AuthorizationException, AuthenticationException,
        InvalidXmlException, MissingMethodParameterException {

        checkNotNull(request);

        return get(PATH + "s" + getEscidoc12Filter(request));
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.ReportDefinitionHandler#
     * retrieveReportDefinitions(gov.loc.www.zing.srw.ExplainRequestType)
     */
    @Override
    public String retrieveReportDefinitions(final ExplainRequestType request)
        throws RemoteException, SystemException, AuthorizationException,
        AuthenticationException, InvalidXmlException,
        MissingMethodParameterException {

        checkNotNull(request);

        return get(PATH + "s" + getEscidoc12Filter(request));
    }
}