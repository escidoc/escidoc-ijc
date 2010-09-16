package de.escidoc.core.common.jibx;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.resources.aa.actions.UnsecuredActions;
import de.escidoc.core.resources.aa.pdp.Requests;
import de.escidoc.core.resources.aa.pdp.RequestsResults;
import de.escidoc.core.resources.aa.role.Role;
import de.escidoc.core.resources.aa.role.Roles;
import de.escidoc.core.resources.aa.useraccount.Attribute;
import de.escidoc.core.resources.aa.useraccount.Attributes;
import de.escidoc.core.resources.aa.useraccount.Grant;
import de.escidoc.core.resources.aa.useraccount.Grants;
import de.escidoc.core.resources.aa.useraccount.Preference;
import de.escidoc.core.resources.aa.useraccount.Preferences;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.aa.useraccount.UserAccountProperties;
import de.escidoc.core.resources.aa.useraccount.UserAccounts;
import de.escidoc.core.resources.cmm.ContentModel;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.Relations;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.common.properties.Properties;
import de.escidoc.core.resources.common.structmap.StructMap;
import de.escidoc.core.resources.common.versionhistory.VersionHistory;
import de.escidoc.core.resources.om.MemberList;
import de.escidoc.core.resources.om.container.Container;
import de.escidoc.core.resources.om.container.ContainerList;
import de.escidoc.core.resources.om.contentRelation.ContentRelation;
import de.escidoc.core.resources.om.context.AdminDescriptor;
import de.escidoc.core.resources.om.context.AdminDescriptors;
import de.escidoc.core.resources.om.context.Context;
import de.escidoc.core.resources.om.context.ContextList;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.om.item.ItemList;
import de.escidoc.core.resources.om.item.component.Component;
import de.escidoc.core.resources.om.toc.Toc;
import de.escidoc.core.resources.oum.OrganizationalUnit;
import de.escidoc.core.resources.oum.OrganizationalUnitList;
import de.escidoc.core.resources.oum.Parents;
import de.escidoc.core.resources.oum.PathList;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.scan.ScanResponse;
import de.escidoc.core.resources.sb.search.SearchResultRecord;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;

/**
 * Marshaller Factory.
 * 
 * 
 */
public class MarshallerFactory {

    private static Marshaller<OrganizationalUnit> organizationalUnitMarshaller =
        null;

    private static Marshaller<OrganizationalUnitList> organizationalUnitListMarshaller =
        null;

    private static Marshaller<Context> contextMarshaller = null;

    private static Marshaller<ContentModel> contentModelMarshaller = null;

    private static Marshaller<MemberList> memberListMarshaller = null;

    private static Marshaller<Item> itemMarshaller = null;

    private static Marshaller<PathList> pathListMarshaller = null;

    private static Marshaller<Component> componentMarshaller = null;

    private static Marshaller<Container> containerMarshaller = null;

    private static Marshaller<ContentRelation> contentRelationMarshaller = null;

    private static Marshaller<UserAccount> userAccountMarshaller = null;

    private static Marshaller<Requests> requestsMarshaller = null;

    private static Marshaller<RequestsResults> requestsResultsMarshaller = null;

    private static Marshaller<Role> roleMarshaller = null;

    private static Marshaller<UserAccountProperties> userAccountPropertiesMarshaller =
        null;

    private static Marshaller<Grant> grantMarshaller = null;

    private static Marshaller<Grants> grantsMarshaller = null;

    private static Marshaller<Attribute> attributeMarshaller = null;

    private static Marshaller<Attributes> attributesMarshaller = null;

    private static Marshaller<Preference> preferenceMarshaller = null;

    private static Marshaller<Preferences> preferencesMarshaller = null;

    private static Marshaller<UserAccounts> userAccountListMarshaller = null;

    private static Marshaller<Roles> roleListMarshaller = null;

    private static Marshaller<UnsecuredActions> unsecuredActionsMarshaller =
        null;

    private static Marshaller<Toc> tocMarshaller = null;

    private static Marshaller<ItemList> itemListMarshaller = null;

    private static Marshaller<ContainerList> containerListMarshaller = null;

    private static Marshaller<ContextList> contextListMarshaller = null;

    private static Marshaller<TaskParam> taskParamMarshaller = null;

    private static Marshaller<Result> resultMarshaller = null;

    private static Marshaller<Parents> parentsMarshaller = null;

    /*
     * ========================================================================
     * Sub resources
     * ========================================================================
     */

    private static Marshaller<Properties> propertiesMarshaller = null;

    private static Marshaller<VersionHistory> versionHistoryMarshaller = null;

    private static Marshaller<StructMap> structMapMarshaller = null;

    private static Marshaller<AdminDescriptor> adminDescriptorMarshaller = null;

    private static Marshaller<AdminDescriptors> adminDescriptorListMarshaller =
        null;

    private static Marshaller<MetadataRecords> metadataRecordsMarshaller = null;

    private static Marshaller<Relations> relationsMarshaller = null;

    /*
     * ========================================================================
     * SRW/SRU
     * ========================================================================
     */

    private static Marshaller<SearchResultRecord> searchResultMarshaller = null;

    private static Marshaller<ExplainResponse> explainResponseMarshaller = null;

    private static Marshaller<ScanResponse> scanResponseMarshaller = null;

    private static Marshaller<SearchRetrieveResponse> searchRetrieveResponseMarshaller =
        null;

    private final TransportProtocol transport;

    protected MarshallerFactory(TransportProtocol transport) {
        this.transport = transport;
    }

    /**
     * @return the itemMarshaller
     * @throws InternalClientException
     */
    public Marshaller<Item> getItemMarshaller() throws InternalClientException {

        if (itemMarshaller == null) {
            itemMarshaller = new Marshaller<Item>(Item.class);
        }
        itemMarshaller.setBindingName(transport.name());

        return itemMarshaller;
    }

    /**
     * FIXME Filter and Search should be the same.
     * 
     * @return the searchResultMarshaller
     */
    public Marshaller<SearchResultRecord> getSearchResultMarshaller()
        throws InternalClientException {

        if (searchResultMarshaller == null) {
            searchResultMarshaller =
                new Marshaller<SearchResultRecord>(SearchResultRecord.class);
        }
        searchResultMarshaller.setBindingName(transport.name());

        return searchResultMarshaller;
    }

    /**
     * @return the componentMarshaller
     */
    public Marshaller<Component> getComponentMarshaller()
        throws InternalClientException {

        if (componentMarshaller == null) {
            componentMarshaller = new Marshaller<Component>(Component.class);
        }
        componentMarshaller.setBindingName(transport.name());

        return componentMarshaller;
    }

    /**
     * @return the itemListMarshaller
     */
    public Marshaller<ItemList> getItemListMarshaller()
        throws InternalClientException {

        if (itemListMarshaller == null) {
            itemListMarshaller = new Marshaller<ItemList>(ItemList.class);
        }
        itemListMarshaller.setBindingName(transport.name());

        return itemListMarshaller;
    }

    /**
     * @return the memberListMarshaller
     */
    public Marshaller<MemberList> getMemberListMarshaller()
        throws InternalClientException {

        if (memberListMarshaller == null) {
            memberListMarshaller = new Marshaller<MemberList>(MemberList.class);
        }
        memberListMarshaller.setBindingName(transport.name());

        return memberListMarshaller;
    }

    /**
     * @return the containerListMarshaller
     */
    public Marshaller<ContainerList> getContainerListMarshaller()
        throws InternalClientException {

        if (containerListMarshaller == null) {
            containerListMarshaller =
                new Marshaller<ContainerList>(ContainerList.class);
        }
        containerListMarshaller.setBindingName(transport.name());

        return containerListMarshaller;
    }

    /**
     * @return the containerMarshaller
     */
    public Marshaller<Container> getContainerMarshaller()
        throws InternalClientException {

        if (containerMarshaller == null) {
            containerMarshaller = new Marshaller<Container>(Container.class);
        }
        containerMarshaller.setBindingName(transport.name());

        return containerMarshaller;
    }

    /**
     * @return the contentRelationMarshaller
     */
    public Marshaller<ContentRelation> getContentRelationMarshaller()
        throws InternalClientException {

        if (contentRelationMarshaller == null) {
            contentRelationMarshaller =
                new Marshaller<ContentRelation>(ContentRelation.class);
        }
        contentRelationMarshaller.setBindingName(transport.name());

        return contentRelationMarshaller;
    }

    /**
     * @return the contextListMarshaller
     */
    public Marshaller<ContextList> getContextListMarshaller()
        throws InternalClientException {

        if (contextListMarshaller == null) {
            contextListMarshaller =
                new Marshaller<ContextList>(ContextList.class);
        }
        contextListMarshaller.setBindingName(transport.name());

        return contextListMarshaller;
    }

    /**
     * @return the contextMarshaller
     */
    public Marshaller<Context> getContextMarshaller()
        throws InternalClientException {

        if (contextMarshaller == null) {
            contextMarshaller = new Marshaller<Context>(Context.class);
        }
        contextMarshaller.setBindingName(transport.name());

        return contextMarshaller;
    }

    /**
     * @return the contentModelMarshaller
     */
    public Marshaller<ContentModel> getContentModelMarshaller()
        throws InternalClientException {

        if (contentModelMarshaller == null) {
            contentModelMarshaller =
                new Marshaller<ContentModel>(ContentModel.class);
        }
        contentModelMarshaller.setBindingName(transport.name());

        return contentModelMarshaller;
    }

    /**
     * @return the organizationalUnitMarshaller
     */
    public Marshaller<OrganizationalUnit> getOrganizationalUnitMarshaller()
        throws InternalClientException {

        if (organizationalUnitMarshaller == null) {
            organizationalUnitMarshaller =
                new Marshaller<OrganizationalUnit>(OrganizationalUnit.class);
        }
        organizationalUnitMarshaller.setBindingName(transport.name());

        return organizationalUnitMarshaller;
    }

    /**
     * @return the organizationalUnitListMarshaller
     */
    public Marshaller<OrganizationalUnitList> getOrganizationalUnitListMarshaller()
        throws InternalClientException {

        if (organizationalUnitListMarshaller == null) {
            organizationalUnitListMarshaller =
                new Marshaller<OrganizationalUnitList>(
                    OrganizationalUnitList.class);
        }
        organizationalUnitListMarshaller.setBindingName(transport.name());

        return organizationalUnitListMarshaller;
    }

    /**
     * @return the pathListMarshaller
     */
    public Marshaller<PathList> getPathListMarshaller()
        throws InternalClientException {

        if (pathListMarshaller == null) {
            pathListMarshaller = new Marshaller<PathList>(PathList.class);
        }
        pathListMarshaller.setBindingName(transport.name());

        return pathListMarshaller;
    }

    /**
     * @return the taskParamMarshaller
     */
    public Marshaller<TaskParam> getTaskParamMarshaller()
        throws InternalClientException {

        if (taskParamMarshaller == null) {
            taskParamMarshaller = new Marshaller<TaskParam>(TaskParam.class);
        }
        taskParamMarshaller.setBindingName(transport.name());

        return taskParamMarshaller;
    }

    /**
     * @return the resultMarshaller
     */
    public Marshaller<Result> getResultMarshaller()
        throws InternalClientException {

        if (resultMarshaller == null) {
            resultMarshaller = new Marshaller<Result>(Result.class);
        }
        resultMarshaller.setBindingName(transport.name());

        return resultMarshaller;
    }

    /**
     * @return the versionHistoryMarshaller
     */
    public Marshaller<VersionHistory> getVersionHistoryMarshaller()
        throws InternalClientException {
        if (versionHistoryMarshaller == null) {
            versionHistoryMarshaller =
                new Marshaller<VersionHistory>(VersionHistory.class);
        }
        versionHistoryMarshaller.setBindingName(transport.name());

        return versionHistoryMarshaller;
    }

    /**
     * @return the structMapMarshaller
     */
    public Marshaller<StructMap> getStructMapMarshaller()
        throws InternalClientException {
        if (structMapMarshaller == null) {
            structMapMarshaller = new Marshaller<StructMap>(StructMap.class);
        }
        structMapMarshaller.setBindingName(transport.name());

        return structMapMarshaller;
    }

    /**
     * @return the adminDescriptorMarshaller
     */
    public Marshaller<AdminDescriptor> getAdminDescriptorMarshaller()
        throws InternalClientException {
        if (adminDescriptorMarshaller == null) {
            adminDescriptorMarshaller =
                new Marshaller<AdminDescriptor>(AdminDescriptor.class);
        }
        adminDescriptorMarshaller.setBindingName(transport.name());

        return adminDescriptorMarshaller;
    }

    /**
     * @return the adminDescriptorListMarshaller
     */
    public Marshaller<AdminDescriptors> getAdminDescriptorListMarshaller()
        throws InternalClientException {
        if (adminDescriptorListMarshaller == null) {
            adminDescriptorListMarshaller =
                new Marshaller<AdminDescriptors>(AdminDescriptors.class);
        }
        adminDescriptorListMarshaller.setBindingName(transport.name());

        return adminDescriptorListMarshaller;
    }

    /**
     * @return the tocMarshaller
     */
    public Marshaller<Toc> getTocMarshaller() throws InternalClientException {
        if (tocMarshaller == null) {
            tocMarshaller = new Marshaller<Toc>(Toc.class);
        }
        tocMarshaller.setBindingName(transport.name());

        return tocMarshaller;
    }

    /* ********************************************************************** */

    /**
     * @return the propertiesMarshaller
     */
    public Marshaller<Properties> getPropertiesMarshaller()
        throws InternalClientException {
        if (propertiesMarshaller == null) {
            propertiesMarshaller = new Marshaller<Properties>(Properties.class);
        }
        propertiesMarshaller.setBindingName(transport.name());

        return propertiesMarshaller;
    }

    /**
     * @return the metadataRecordsMarshaller
     */
    public Marshaller<MetadataRecords> getMetadataRecordsMarshaller()
        throws InternalClientException {
        if (metadataRecordsMarshaller == null) {
            metadataRecordsMarshaller =
                new Marshaller<MetadataRecords>(MetadataRecords.class);
        }
        metadataRecordsMarshaller.setBindingName(transport.name());

        return metadataRecordsMarshaller;
    }

    /**
     * @return the relationsRecordsMarshaller
     */
    public Marshaller<Relations> getRelationsMarshaller()
        throws InternalClientException {
        if (relationsMarshaller == null) {
            relationsMarshaller = new Marshaller<Relations>(Relations.class);
        }
        relationsMarshaller.setBindingName(transport.name());

        return relationsMarshaller;
    }

    public Marshaller<UserAccount> getUserAccountMarshaller()
        throws InternalClientException {
        if (userAccountMarshaller == null) {
            userAccountMarshaller =
                new Marshaller<UserAccount>(UserAccount.class);

        }
        userAccountMarshaller.setBindingName(transport.name());

        return userAccountMarshaller;
    }

    public Marshaller<Requests> getRequestsMarshaller()
        throws InternalClientException {
        if (requestsMarshaller == null) {
            requestsMarshaller = new Marshaller<Requests>(Requests.class);

        }
        requestsMarshaller.setBindingName(transport.name());

        return requestsMarshaller;
    }

    public Marshaller<RequestsResults> getRequestsResultsMarshaller()
        throws InternalClientException {
        if (requestsResultsMarshaller == null) {
            requestsResultsMarshaller =
                new Marshaller<RequestsResults>(RequestsResults.class);

        }
        requestsResultsMarshaller.setBindingName(transport.name());

        return requestsResultsMarshaller;
    }

    public Marshaller<Role> getRoleMarshaller() throws InternalClientException {
        if (roleMarshaller == null) {
            roleMarshaller = new Marshaller<Role>(Role.class);

        }
        roleMarshaller.setBindingName(transport.name());

        return roleMarshaller;
    }

    public Marshaller<UserAccountProperties> getUserAccountPropertiesMarshaller()
        throws InternalClientException {
        if (userAccountPropertiesMarshaller == null) {
            userAccountPropertiesMarshaller =
                new Marshaller<UserAccountProperties>(UserAccount.class);

        }
        userAccountPropertiesMarshaller.setBindingName(transport.name());

        return userAccountPropertiesMarshaller;
    }

    public Marshaller<Grant> getGrantMarshaller()
        throws InternalClientException {
        if (grantMarshaller == null) {
            grantMarshaller = new Marshaller<Grant>(Grant.class);
        }
        grantMarshaller.setBindingName(transport.name());

        return grantMarshaller;
    }

    public Marshaller<Grants> getGrantsMarshaller()
        throws InternalClientException {
        if (grantsMarshaller == null) {
            grantsMarshaller = new Marshaller<Grants>(Grants.class);
        }
        grantsMarshaller.setBindingName(transport.name());

        return grantsMarshaller;
    }

    public Marshaller<Attribute> getAttributeMarshaller()
        throws InternalClientException {
        if (attributeMarshaller == null) {
            attributeMarshaller = new Marshaller<Attribute>(Attribute.class);
        }
        attributeMarshaller.setBindingName(transport.name());

        return attributeMarshaller;
    }

    public Marshaller<Attributes> getAttributesMarshaller()
        throws InternalClientException {
        if (attributesMarshaller == null) {
            attributesMarshaller = new Marshaller<Attributes>(Attributes.class);
        }
        attributesMarshaller.setBindingName(transport.name());

        return attributesMarshaller;
    }

    public Marshaller<Preference> getPreferenceMarshaller()
        throws InternalClientException {
        if (preferenceMarshaller == null) {
            preferenceMarshaller = new Marshaller<Preference>(Preference.class);
        }
        preferenceMarshaller.setBindingName(transport.name());

        return preferenceMarshaller;
    }

    public Marshaller<Preferences> getPreferencesMarshaller()
        throws InternalClientException {
        if (preferencesMarshaller == null) {
            preferencesMarshaller =
                new Marshaller<Preferences>(Preferences.class);
        }
        preferencesMarshaller.setBindingName(transport.name());

        return preferencesMarshaller;
    }

    public Marshaller<UserAccounts> getUserAccountListMarshaller()
        throws InternalClientException {
        if (userAccountListMarshaller == null) {
            userAccountListMarshaller =
                new Marshaller<UserAccounts>(UserAccounts.class);
        }
        userAccountListMarshaller.setBindingName(transport.name());

        return userAccountListMarshaller;
    }

    public Marshaller<UnsecuredActions> getUnsecuredActionsMarshaller()
        throws InternalClientException {
        if (unsecuredActionsMarshaller == null) {
            unsecuredActionsMarshaller =
                new Marshaller<UnsecuredActions>(UnsecuredActions.class);
        }
        unsecuredActionsMarshaller.setBindingName(transport.name());

        return unsecuredActionsMarshaller;
    }

    public Marshaller<Roles> getRoleListMarshaller()
        throws InternalClientException {
        if (roleListMarshaller == null) {
            roleListMarshaller = new Marshaller<Roles>(Roles.class);
        }
        roleListMarshaller.setBindingName(transport.name());

        return roleListMarshaller;
    }

    public Marshaller<Parents> getParentsMarshaller()
        throws InternalClientException {
        if (parentsMarshaller == null) {
            parentsMarshaller = new Marshaller<Parents>(Parents.class);
        }
        parentsMarshaller.setBindingName(transport.name());

        return parentsMarshaller;
    }

    /**
     * @return the explainResponseMarshaller
     * @throws InternalClientException
     */
    public Marshaller<ExplainResponse> getExplainResponseMarshaller()
        throws InternalClientException {
        if (explainResponseMarshaller == null) {
            explainResponseMarshaller =
                new Marshaller<ExplainResponse>(ExplainResponse.class);
        }
        explainResponseMarshaller.setBindingName(transport.name());

        return explainResponseMarshaller;
    }

    /**
     * 
     * @return
     * @throws InternalClientException
     */
    public Marshaller<ScanResponse> getScanResponseMarshaller()
        throws InternalClientException {
        if (scanResponseMarshaller == null) {
            scanResponseMarshaller =
                new Marshaller<ScanResponse>(ScanResponse.class);
        }
        scanResponseMarshaller.setBindingName(transport.name());

        return scanResponseMarshaller;
    }

    /**
     * 
     * @return
     * @throws InternalClientException
     */
    public Marshaller<SearchRetrieveResponse> getSearchRetrieveResponseMarshaller()
        throws InternalClientException {
        if (searchRetrieveResponseMarshaller == null) {
            searchRetrieveResponseMarshaller =
                new Marshaller<SearchRetrieveResponse>(
                    SearchRetrieveResponse.class);
        }
        searchRetrieveResponseMarshaller.setBindingName(transport.name());

        return searchRetrieveResponseMarshaller;
    }

}
