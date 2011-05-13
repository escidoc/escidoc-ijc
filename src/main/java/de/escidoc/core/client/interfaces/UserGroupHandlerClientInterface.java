/**
 * 
 */
package de.escidoc.core.client.interfaces;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.util.List;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.base.Activatable;
import de.escidoc.core.client.interfaces.base.CrudService;
import de.escidoc.core.client.interfaces.base.HandlerService;
import de.escidoc.core.resources.aa.useraccount.Grant;
import de.escidoc.core.resources.aa.useraccount.Grants;
import de.escidoc.core.resources.aa.usergroup.UserGroup;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;

/**
 * @author MVO
 * 
 */
public interface UserGroupHandlerClientInterface extends HandlerService, CrudService<UserGroup>, Activatable<UserGroup> {

    /**
     * @param groupId
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Grants retrieveCurrentGrants(final String groupId) throws EscidocException, InternalClientException,
        TransportException;

    /**
     * @param group
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Grants retrieveCurrentGrants(final UserGroup group) throws EscidocException, InternalClientException,
        TransportException;

    /**
     * @param groupId
     * @param grantXML
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Grant createGrant(final String groupId, final Grant grant) throws EscidocException, InternalClientException,
        TransportException;

    /**
     * @param group
     * @param grantXML
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Grant createGrant(final UserGroup group, final Grant grant) throws EscidocException, InternalClientException,
        TransportException;

    /**
     * @param groupId
     * @param grantId
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Grant retrieveGrant(final String groupId, final String grantId) throws EscidocException, InternalClientException,
        TransportException;

    /**
     * @param group
     * @param grantId
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Grant retrieveGrant(final UserGroup group, final String grantId) throws EscidocException, InternalClientException,
        TransportException;

    /**
     * @param groupId
     * @param grantId
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    void revokeGrant(final String groupId, final String grantId, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * @param group
     * @param grantId
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    void revokeGrant(final UserGroup group, final String grantId, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * @param groupId
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    void revokeGrants(final String groupId, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * @param group
     * @param taskParam
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    void revokeGrants(final UserGroup group, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException;

    // /**
    // * @param filter
    // * @return
    // * @throws EscidocException
    // * @throws InternalClientException
    // * @throws TransportException
    // */
    // String retrieveUserGroups(final HashMap<String, String> filter)
    // throws EscidocException, InternalClientException, TransportException;

    /**
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    SearchRetrieveResponse retrieveUserGroups(final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    List<UserGroup> retrieveUserGroupsAsList(final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * @param request
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    ExplainResponse retrieveUserGroups(final ExplainRequestType request) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * @param groupId
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    UserGroup addSelectors(final String groupId, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * @param group
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    UserGroup addSelectors(final UserGroup group, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * @param groupId
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    UserGroup removeSelectors(final String groupId, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * @param group
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    UserGroup removeSelectors(final UserGroup group, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException;
}