/**
 * 
 */
package de.escidoc.core.client;

import static de.escidoc.core.common.Precondition.checkNotNull;
import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.net.URL;
import java.util.List;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.UserGroupHandlerClientInterface;
import de.escidoc.core.client.rest.RestUserGroupHandlerClient;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.common.jibx.MarshallerFactory;
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
public class UserGroupHandlerClient extends AbstractHandlerClient<RestUserGroupHandlerClient>
    implements UserGroupHandlerClientInterface {

    /**
     * 
     */
    public UserGroupHandlerClient() {
        super();
    }

    /**
     * @param serviceAddress
     */
    public UserGroupHandlerClient(final URL serviceAddress) {
        super(serviceAddress);
    }

    /**
     * @param serviceAddress
     * @deprecated Use
     *             {@link UserGroupHandlerClient#UserGroupHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public UserGroupHandlerClient(final String serviceAddress) {
        super(serviceAddress);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.AbstractHandlerClient#getRestHandlerClientInstance
     * ()
     */
    @Override
    protected RestUserGroupHandlerClient getRestHandlerClientInstance() throws InternalClientException {
        return new RestUserGroupHandlerClient(getServiceAddress());
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserGroupHandlerClientInterface#
     * retrieveCurrentGrants(java.lang.String)
     */
    @Override
    public Grants retrieveCurrentGrants(final String userGroupId) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(userGroupId);

        Marshaller<Grants> m = MarshallerFactory.getInstance().getMarshaller(Grants.class);

        return m.unmarshalDocument(getClient().retrieveCurrentGrants(userGroupId));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Createable#create(java.lang.Object
     * )
     */
    @Override
    public UserGroup create(final UserGroup resource) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(resource);

        Marshaller<UserGroup> m = MarshallerFactory.getInstance().getMarshaller(UserGroup.class);

        return m.unmarshalDocument(getClient().create(m.marshalDocument(resource)));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Retrievable#retrieve(java.lang
     * .String)
     */
    @Override
    public UserGroup retrieve(final String id) throws EscidocException, InternalClientException, TransportException {

        checkNotNull(id);

        return MarshallerFactory.getInstance().getMarshaller(UserGroup.class).unmarshalDocument(
            getClient().retrieve(id));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Deletable#delete(java.lang.String)
     */
    @Override
    public void delete(final String id) throws EscidocException, InternalClientException, TransportException {

        checkNotNull(id);

        getClient().delete(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Updatable#update(java.lang.Object)
     */
    @Override
    public UserGroup update(final UserGroup resource) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(resource);

        Marshaller<UserGroup> m = MarshallerFactory.getInstance().getMarshaller(UserGroup.class);

        return m.unmarshalDocument(getClient().update(resource.getObjid(), m.marshalDocument(resource)));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Activatable#activate(java.lang
     * .String, java.lang.String)
     */
    @Override
    public void activate(final String groupId, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(groupId);
        checkNotNull(taskParam);

        getClient().activate(groupId, marshalTaskParam(taskParam));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Activatable#activate(java.lang
     * .Object, java.lang.String)
     */
    @Override
    public void activate(final UserGroup resource, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(resource);

        activate(resource.getObjid(), taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Activatable#deactivate(java.lang
     * .String, java.lang.String)
     */
    @Override
    public void deactivate(final String groupId, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(groupId);
        checkNotNull(taskParam);

        getClient().deactivate(groupId, marshalTaskParam(taskParam));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.base.Activatable#deactivate(java.lang
     * .Object, java.lang.String)
     */
    @Override
    public void deactivate(final UserGroup resource, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(resource);

        deactivate(resource.getObjid(), taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserGroupHandlerClientInterface#
     * retrieveCurrentGrants(de.escidoc.core.resources.aa.usergroup.UserGroup)
     */
    @Override
    public Grants retrieveCurrentGrants(final UserGroup group) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(group);

        return retrieveCurrentGrants(group.getObjid());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.UserGroupHandlerClientInterface#createGrant
     * (java.lang.String, java.lang.String)
     */
    @Override
    public Grant createGrant(final String groupId, final Grant grant) throws EscidocException, InternalClientException,
        TransportException {

        checkNotNull(groupId);
        checkNotNull(grant);

        Marshaller<Grant> m = MarshallerFactory.getInstance().getMarshaller(Grant.class);

        return m.unmarshalDocument(getClient().createGrant(groupId, m.marshalDocument(grant)));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.UserGroupHandlerClientInterface#createGrant
     * (de.escidoc.core.resources.aa.usergroup.UserGroup, java.lang.String)
     */
    @Override
    public Grant createGrant(final UserGroup group, final Grant grant) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(group);

        return createGrant(group.getObjid(), grant);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserGroupHandlerClientInterface#
     * retrieveGrant(java.lang.String, java.lang.String)
     */
    @Override
    public Grant retrieveGrant(final String groupId, final String grantId) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(groupId);
        checkNotNull(grantId);

        Marshaller<Grant> m = MarshallerFactory.getInstance().getMarshaller(Grant.class);

        return m.unmarshalDocument(getClient().retrieveGrant(groupId, grantId));
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserGroupHandlerClientInterface#
     * retrieveGrant(de.escidoc.core.resources.aa.usergroup.UserGroup,
     * java.lang.String)
     */
    @Override
    public Grant retrieveGrant(final UserGroup group, final String grantId) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(group);

        return retrieveGrant(group.getObjid(), grantId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.UserGroupHandlerClientInterface#revokeGrant
     * (java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void revokeGrant(final String groupId, final String grantId, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        checkNotNull(groupId);
        checkNotNull(grantId);
        checkNotNull(taskParam);

        getClient().revokeGrant(groupId, grantId, marshalTaskParam(taskParam));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.UserGroupHandlerClientInterface#revokeGrant
     * (de.escidoc.core.resources.aa.usergroup.UserGroup, java.lang.String,
     * java.lang.String)
     */
    @Override
    public void revokeGrant(final UserGroup group, final String grantId, final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException {

        checkNotNull(group);

        revokeGrant(group.getObjid(), grantId, taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserGroupHandlerClientInterface#
     * revokeGrants(java.lang.String, java.lang.String)
     */
    @Override
    public void revokeGrants(final String groupId, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(groupId);
        checkNotNull(taskParam);

        getClient().revokeGrants(groupId, marshalTaskParam(taskParam));
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserGroupHandlerClientInterface#
     * revokeGrants(de.escidoc.core.resources.aa.usergroup.UserGroup,
     * java.lang.String)
     */
    @Override
    public void revokeGrants(final UserGroup group, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(group);

        revokeGrants(group.getObjid(), taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserGroupHandlerClientInterface#
     * retrieveUserGroups(gov.loc.www.zing.srw.SearchRetrieveRequestType)
     */
    @Override
    public SearchRetrieveResponse retrieveUserGroups(final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(request);

        Marshaller<SearchRetrieveResponse> m =
            MarshallerFactory.getInstance().getMarshaller(SearchRetrieveResponse.class);

        return m.unmarshalDocument(getClient().retrieveUserGroups(request));
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserGroupHandlerClientInterface#
     * retrieveUserGroupsAsList(gov.loc.www.zing.srw.SearchRetrieveRequestType)
     */
    @Override
    public List<UserGroup> retrieveUserGroupsAsList(final SearchRetrieveRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        return getSearchRetrieveResponseAsList(UserGroup.class, retrieveUserGroups(request));
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserGroupHandlerClientInterface#
     * retrieveUserGroups(gov.loc.www.zing.srw.ExplainRequestType)
     */
    @Override
    public ExplainResponse retrieveUserGroups(final ExplainRequestType request) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(request);

        Marshaller<ExplainResponse> m = MarshallerFactory.getInstance().getMarshaller(ExplainResponse.class);

        return m.unmarshalDocument(getClient().retrieveUserGroups(request));
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserGroupHandlerClientInterface#
     * addSelectors(java.lang.String, java.lang.String)
     */
    @Override
    public UserGroup addSelectors(final String groupId, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(groupId);
        checkNotNull(taskParam);

        Marshaller<UserGroup> m = MarshallerFactory.getInstance().getMarshaller(UserGroup.class);

        return m.unmarshalDocument(getClient().addSelectors(groupId, marshalTaskParam(taskParam)));
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserGroupHandlerClientInterface#
     * addSelectors(de.escidoc.core.resources.aa.usergroup.UserGroup,
     * java.lang.String)
     */
    @Override
    public UserGroup addSelectors(final UserGroup group, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(group);

        return addSelectors(group.getObjid(), taskParam);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserGroupHandlerClientInterface#
     * removeSelectors(java.lang.String, java.lang.String)
     */
    @Override
    public UserGroup removeSelectors(final String groupId, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(groupId);
        checkNotNull(taskParam);

        Marshaller<UserGroup> m = MarshallerFactory.getInstance().getMarshaller(UserGroup.class);

        return m.unmarshalDocument(getClient().removeSelectors(groupId, marshalTaskParam(taskParam)));
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.UserGroupHandlerClientInterface#
     * removeSelectors(de.escidoc.core.resources.aa.usergroup.UserGroup,
     * java.lang.String)
     */
    @Override
    public UserGroup removeSelectors(final UserGroup group, final TaskParam taskParam) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(group);

        return removeSelectors(group.getObjid(), taskParam);
    }
}