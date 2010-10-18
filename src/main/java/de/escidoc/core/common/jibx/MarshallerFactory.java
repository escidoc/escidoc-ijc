package de.escidoc.core.common.jibx;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.resources.aa.actions.UnsecuredActions;
import de.escidoc.core.resources.aa.pdp.Requests;
import de.escidoc.core.resources.aa.pdp.Results;
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
import de.escidoc.core.resources.common.MetadataRecord;
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

    private static Marshaller<Requests> pdpRequestsMarshaller = null;

    private static Marshaller<Results> pdpResultsMarshaller = null;

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

    private static Marshaller<MetadataRecord> metadataRecordMarshaller = null;

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

    private static Marshaller<de.escidoc.core.resources.common.Properties> commonPropertiesMarshaller;

    private final TransportProtocol transport;

    /**
     * 
     * @param transport
     */
    protected MarshallerFactory(TransportProtocol transport) {
        this.transport = transport;
    }

    /**
     * @return the itemMarshaller
     */
    public Marshaller<Item> getItemMarshaller() {

        if (itemMarshaller == null) {
            itemMarshaller = new Marshaller<Item>(Item.class);
        }
        itemMarshaller.setBindingName(transport.name());

        return itemMarshaller;
    }

    /**
     * 
     * @return the searchResultMarshaller
     */
    public Marshaller<SearchResultRecord> getSearchResultMarshaller() {

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
    public Marshaller<Component> getComponentMarshaller() {

        if (componentMarshaller == null) {
            componentMarshaller = new Marshaller<Component>(Component.class);
        }
        componentMarshaller.setBindingName(transport.name());

        return componentMarshaller;
    }

    /**
     * @return the itemListMarshaller
     */
    public Marshaller<ItemList> getItemListMarshaller() {

        if (itemListMarshaller == null) {
            itemListMarshaller = new Marshaller<ItemList>(ItemList.class);
        }
        itemListMarshaller.setBindingName(transport.name());

        return itemListMarshaller;
    }

    /**
     * @return the memberListMarshaller
     */
    public Marshaller<MemberList> getMemberListMarshaller() {

        if (memberListMarshaller == null) {
            memberListMarshaller = new Marshaller<MemberList>(MemberList.class);
        }
        memberListMarshaller.setBindingName(transport.name());

        return memberListMarshaller;
    }

    /**
     * @return the containerListMarshaller
     */
    public Marshaller<ContainerList> getContainerListMarshaller() {

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
    public Marshaller<Container> getContainerMarshaller() {

        if (containerMarshaller == null) {
            containerMarshaller = new Marshaller<Container>(Container.class);
        }
        containerMarshaller.setBindingName(transport.name());

        return containerMarshaller;
    }

    /**
     * @return the contentRelationMarshaller
     */
    public Marshaller<ContentRelation> getContentRelationMarshaller() {

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
    public Marshaller<ContextList> getContextListMarshaller() {

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
    public Marshaller<Context> getContextMarshaller() {

        if (contextMarshaller == null) {
            contextMarshaller = new Marshaller<Context>(Context.class);
        }
        contextMarshaller.setBindingName(transport.name());

        return contextMarshaller;
    }

    /**
     * @return the contentModelMarshaller
     */
    public Marshaller<ContentModel> getContentModelMarshaller() {

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
    public Marshaller<OrganizationalUnit> getOrganizationalUnitMarshaller() {

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
    public Marshaller<OrganizationalUnitList> getOrganizationalUnitListMarshaller() {

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
    public Marshaller<PathList> getPathListMarshaller() {

        if (pathListMarshaller == null) {
            pathListMarshaller = new Marshaller<PathList>(PathList.class);
        }
        pathListMarshaller.setBindingName(transport.name());

        return pathListMarshaller;
    }

    /**
     * @return the taskParamMarshaller
     */
    public Marshaller<TaskParam> getTaskParamMarshaller() {

        if (taskParamMarshaller == null) {
            taskParamMarshaller = new Marshaller<TaskParam>(TaskParam.class);
        }
        taskParamMarshaller.setBindingName(transport.name());

        return taskParamMarshaller;
    }

    /**
     * @return the resultMarshaller
     */
    public Marshaller<Result> getResultMarshaller() {

        if (resultMarshaller == null) {
            resultMarshaller = new Marshaller<Result>(Result.class);
        }
        resultMarshaller.setBindingName(transport.name());

        return resultMarshaller;
    }

    /**
     * @return the versionHistoryMarshaller
     */
    public Marshaller<VersionHistory> getVersionHistoryMarshaller() {
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
    public Marshaller<StructMap> getStructMapMarshaller() {
        if (structMapMarshaller == null) {
            structMapMarshaller = new Marshaller<StructMap>(StructMap.class);
        }
        structMapMarshaller.setBindingName(transport.name());

        return structMapMarshaller;
    }

    /**
     * @return the adminDescriptorMarshaller
     */
    public Marshaller<AdminDescriptor> getAdminDescriptorMarshaller() {
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
    public Marshaller<AdminDescriptors> getAdminDescriptorListMarshaller() {
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
    public Marshaller<Toc> getTocMarshaller() {
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
    public Marshaller<Properties> getPropertiesMarshaller() {
        if (propertiesMarshaller == null) {
            propertiesMarshaller = new Marshaller<Properties>(Properties.class);
        }
        propertiesMarshaller.setBindingName(transport.name());

        return propertiesMarshaller;
    }

    /**
     * @return the metadataRecordsMarshaller
     */
    public Marshaller<MetadataRecords> getMetadataRecordsMarshaller() {
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
    public Marshaller<Relations> getRelationsMarshaller() {
        if (relationsMarshaller == null) {
            relationsMarshaller = new Marshaller<Relations>(Relations.class);
        }
        relationsMarshaller.setBindingName(transport.name());

        return relationsMarshaller;
    }

    public Marshaller<UserAccount> getUserAccountMarshaller() {
        if (userAccountMarshaller == null) {
            userAccountMarshaller =
                new Marshaller<UserAccount>(UserAccount.class);

        }
        userAccountMarshaller.setBindingName(transport.name());

        return userAccountMarshaller;
    }

    public Marshaller<Requests> getPDPRequestsMarshaller() {
        if (pdpRequestsMarshaller == null) {
            pdpRequestsMarshaller = new Marshaller<Requests>(Requests.class);

        }
        pdpRequestsMarshaller.setBindingName(transport.name());

        return pdpRequestsMarshaller;
    }

    public Marshaller<Results> getPDPResultsMarshaller() {
        if (pdpResultsMarshaller == null) {
            pdpResultsMarshaller = new Marshaller<Results>(Results.class);

        }
        pdpResultsMarshaller.setBindingName(transport.name());
        return pdpResultsMarshaller;
    }

    public Marshaller<Role> getRoleMarshaller() {
        if (roleMarshaller == null) {
            roleMarshaller = new Marshaller<Role>(Role.class);

        }
        roleMarshaller.setBindingName(transport.name());

        return roleMarshaller;
    }

    public Marshaller<UserAccountProperties> getUserAccountPropertiesMarshaller() {
        if (userAccountPropertiesMarshaller == null) {
            userAccountPropertiesMarshaller =
                new Marshaller<UserAccountProperties>(UserAccount.class);

        }
        userAccountPropertiesMarshaller.setBindingName(transport.name());

        return userAccountPropertiesMarshaller;
    }

    public Marshaller<Grant> getGrantMarshaller() {
        if (grantMarshaller == null) {
            grantMarshaller = new Marshaller<Grant>(Grant.class);
        }
        grantMarshaller.setBindingName(transport.name());

        return grantMarshaller;
    }

    public Marshaller<Grants> getGrantsMarshaller() {
        if (grantsMarshaller == null) {
            grantsMarshaller = new Marshaller<Grants>(Grants.class);
        }
        grantsMarshaller.setBindingName(transport.name());

        return grantsMarshaller;
    }

    public Marshaller<Attribute> getAttributeMarshaller() {
        if (attributeMarshaller == null) {
            attributeMarshaller = new Marshaller<Attribute>(Attribute.class);
        }
        attributeMarshaller.setBindingName(transport.name());

        return attributeMarshaller;
    }

    public Marshaller<Attributes> getAttributesMarshaller() {
        if (attributesMarshaller == null) {
            attributesMarshaller = new Marshaller<Attributes>(Attributes.class);
        }
        attributesMarshaller.setBindingName(transport.name());

        return attributesMarshaller;
    }

    public Marshaller<Preference> getPreferenceMarshaller() {
        if (preferenceMarshaller == null) {
            preferenceMarshaller = new Marshaller<Preference>(Preference.class);
        }
        preferenceMarshaller.setBindingName(transport.name());

        return preferenceMarshaller;
    }

    public Marshaller<Preferences> getPreferencesMarshaller() {
        if (preferencesMarshaller == null) {
            preferencesMarshaller =
                new Marshaller<Preferences>(Preferences.class);
        }
        preferencesMarshaller.setBindingName(transport.name());

        return preferencesMarshaller;
    }

    public Marshaller<UserAccounts> getUserAccountListMarshaller() {
        if (userAccountListMarshaller == null) {
            userAccountListMarshaller =
                new Marshaller<UserAccounts>(UserAccounts.class);
        }
        userAccountListMarshaller.setBindingName(transport.name());

        return userAccountListMarshaller;
    }

    public Marshaller<UnsecuredActions> getUnsecuredActionsMarshaller() {
        if (unsecuredActionsMarshaller == null) {
            unsecuredActionsMarshaller =
                new Marshaller<UnsecuredActions>(UnsecuredActions.class);
        }
        unsecuredActionsMarshaller.setBindingName(transport.name());

        return unsecuredActionsMarshaller;
    }

    public Marshaller<Roles> getRoleListMarshaller() {
        if (roleListMarshaller == null) {
            roleListMarshaller = new Marshaller<Roles>(Roles.class);
        }
        roleListMarshaller.setBindingName(transport.name());

        return roleListMarshaller;
    }

    public Marshaller<Parents> getParentsMarshaller() {
        if (parentsMarshaller == null) {
            parentsMarshaller = new Marshaller<Parents>(Parents.class);
        }
        parentsMarshaller.setBindingName(transport.name());

        return parentsMarshaller;
    }

    public Marshaller<MetadataRecord> getMetadataRecordMarshaller() {
        if (metadataRecordMarshaller == null) {
            metadataRecordMarshaller =
                new Marshaller<MetadataRecord>(MetadataRecord.class);
        }
        metadataRecordMarshaller.setBindingName(transport.name());

        return metadataRecordMarshaller;
    }

    /**
     * @return the explainResponseMarshaller
     */
    public Marshaller<ExplainResponse> getExplainResponseMarshaller() {
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
     */
    public Marshaller<ScanResponse> getScanResponseMarshaller() {
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
     */
    public Marshaller<SearchRetrieveResponse> getSearchRetrieveResponseMarshaller() {
        if (searchRetrieveResponseMarshaller == null) {
            searchRetrieveResponseMarshaller =
                new Marshaller<SearchRetrieveResponse>(
                    SearchRetrieveResponse.class);
        }
        searchRetrieveResponseMarshaller.setBindingName(transport.name());

        return searchRetrieveResponseMarshaller;
    }

    /**
     * Returns a marshaller to be used for marshalling/unmarshalling of common
     * properties. For example:<br/><br/>
     * <code>
     * &lt;properties&gt;<br/>
     * &lt;entry key="keyName"&gt;value&lt;/entry&gt;<br/>
     * &lt;entry key="keyName"&gt;value&lt;/entry&gt;<br/>
     * &lt;entry key="keyName"&gt;value&lt;/entry&gt;<br/>
     * &lt;/properties&gt;<br/>
     * </code>
     * 
     * @return a marshaller for the common properties.
     */
    public Marshaller<de.escidoc.core.resources.common.Properties> getCommonPropertiesMarshaller() {
        if (commonPropertiesMarshaller == null) {
            commonPropertiesMarshaller =
                new Marshaller<de.escidoc.core.resources.common.Properties>(
                    de.escidoc.core.resources.common.Properties.class);
        }
        commonPropertiesMarshaller.setBindingName(transport.name());

        return commonPropertiesMarshaller;
    }

}
