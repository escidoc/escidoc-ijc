package de.escidoc.core.common.jibx;

import de.escidoc.core.resources.aa.actions.UnsecuredActions;
import de.escidoc.core.resources.aa.pdp.Requests;
import de.escidoc.core.resources.aa.pdp.RequestsResults;
import de.escidoc.core.resources.aa.role.Role;
import de.escidoc.core.resources.aa.role.Roles;
import de.escidoc.core.resources.aa.useraccount.Grants;
import de.escidoc.core.resources.aa.useraccount.UserAccountProperties;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
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
import de.escidoc.core.resources.oum.PathList;
import de.escidoc.core.resources.sb.explain.ExplainRecord;
import de.escidoc.core.resources.sb.search.SearchResultRecord;

/**
 * Marshaller Factory.
 * 
 * 
 */
public class Factory {

    private static Marshaller<OrganizationalUnit> organizationalUnitMarshaller =
        null;

    private static Marshaller<OrganizationalUnitList> organizationalUnitListMarshaller =
        null;

    private static Marshaller<Context> contextMarshaller = null;

    private static Marshaller<ContentModel> contentModelMarshaller = null;

    private static Marshaller<MemberList> memberListMarshaller = null;

    private static Marshaller<Item> itemMarshaller = null;

    private static Marshaller<PathList> pathListMarshaller = null;

    private static Marshaller<SearchResultRecord> searchResultMarshaller = null;

    private static Marshaller<ExplainRecord> explainRecordMarshaller = null;

    private static Marshaller<Component> componentMarshaller = null;

    private static Marshaller<Container> containerMarshaller = null;

    private static Marshaller<UserAccount> userAccountMarshaller = null;

    private static Marshaller<Requests> requestsMarshaller = null;

    private static Marshaller<RequestsResults> requestsResultsMarshaller = null;

    private static Marshaller<Role> roleMarshaller = null;

    private static Marshaller<UserAccountProperties> userAccountPropertiesMarshaller =
        null;

    private static Marshaller<Grants> grantsMarshaller = null;

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

    /**
     * @return the itemMarshaller
     */
    public static Marshaller<Item> getItemMarshaller() {

        if (itemMarshaller == null) {
            itemMarshaller = new Marshaller<Item>(Item.class);
        }
        return itemMarshaller;
    }

    /**
     * @return the searchResultMarshaller
     */
    public static Marshaller<SearchResultRecord> getSearchResultMarshaller() {

        if (searchResultMarshaller == null) {
            searchResultMarshaller =
                new Marshaller<SearchResultRecord>(SearchResultRecord.class);
        }
        return searchResultMarshaller;
    }

    /**
     * @return the explainRecordMarshaller
     */
    public static Marshaller<ExplainRecord> getExplainRecordMarshaller() {

        if (explainRecordMarshaller == null) {
            explainRecordMarshaller =
                new Marshaller<ExplainRecord>(ExplainRecord.class);
        }
        return explainRecordMarshaller;
    }

    /**
     * @return the componentMarshaller
     */
    public static Marshaller<Component> getComponentMarshaller() {

        if (componentMarshaller == null) {
            componentMarshaller = new Marshaller<Component>(Component.class);
        }
        return componentMarshaller;
    }

    /**
     * @return the itemListMarshaller
     */
    public static Marshaller<ItemList> getItemListMarshaller() {

        if (itemListMarshaller == null) {
            itemListMarshaller = new Marshaller<ItemList>(ItemList.class);
        }
        return itemListMarshaller;
    }

    /**
     * @return the memberListMarshaller
     */
    public static Marshaller<MemberList> getMemberListMarshaller() {

        if (memberListMarshaller == null) {
            memberListMarshaller = new Marshaller<MemberList>(MemberList.class);
        }
        return memberListMarshaller;
    }

    /**
     * @return the containerListMarshaller
     */
    public static Marshaller<ContainerList> getContainerListMarshaller() {

        if (containerListMarshaller == null) {
            containerListMarshaller =
                new Marshaller<ContainerList>(ContainerList.class);
        }
        return containerListMarshaller;
    }

    /**
     * @return the containerMarshaller
     */
    public static Marshaller<Container> getContainerMarshaller() {

        if (containerMarshaller == null) {
            containerMarshaller = new Marshaller<Container>(Container.class);
        }
        return containerMarshaller;
    }

    /**
     * @return the contextListMarshaller
     */
    public static Marshaller<ContextList> getContextListMarshaller() {

        if (contextListMarshaller == null) {
            contextListMarshaller =
                new Marshaller<ContextList>(ContextList.class);
        }
        return contextListMarshaller;
    }

    /**
     * @return the contextMarshaller
     */
    public static Marshaller<Context> getContextMarshaller() {

        if (contextMarshaller == null) {
            contextMarshaller = new Marshaller<Context>(Context.class);
        }
        return contextMarshaller;
    }

    /**
     * @return the contentModelMarshaller
     */
    public static Marshaller<ContentModel> getContentModelMarshaller() {

        if (contentModelMarshaller == null) {
            contentModelMarshaller =
                new Marshaller<ContentModel>(ContentModel.class);
        }
        return contentModelMarshaller;
    }

    /**
     * @return the organizationalUnitMarshaller
     */
    public static Marshaller<OrganizationalUnit> getOrganizationalUnitMarshaller() {

        if (organizationalUnitMarshaller == null) {
            organizationalUnitMarshaller =
                new Marshaller<OrganizationalUnit>(OrganizationalUnit.class);
        }
        return organizationalUnitMarshaller;
    }

    /**
     * @return the organizationalUnitListMarshaller
     */
    public static Marshaller<OrganizationalUnitList> getOrganizationalUnitListMarshaller() {

        if (organizationalUnitListMarshaller == null) {
            organizationalUnitListMarshaller =
                new Marshaller<OrganizationalUnitList>(
                    OrganizationalUnitList.class);
        }
        return organizationalUnitListMarshaller;
    }

    /**
     * @return the pathListMarshaller
     */
    public static Marshaller<PathList> getPathListMarshaller() {

        if (pathListMarshaller == null) {
            pathListMarshaller = new Marshaller<PathList>(PathList.class);
        }
        return pathListMarshaller;
    }

    /**
     * @return the taskParamMarshaller
     */
    public static Marshaller<TaskParam> getTaskParamMarshaller() {

        if (taskParamMarshaller == null) {
            taskParamMarshaller = new Marshaller<TaskParam>(TaskParam.class);
        }
        return taskParamMarshaller;
    }

    /**
     * @return the resultMarshaller
     */
    public static Marshaller<Result> getResultMarshaller() {

        if (resultMarshaller == null) {
            resultMarshaller = new Marshaller<Result>(Result.class);
        }
        return resultMarshaller;
    }

    /**
     * @return the versionHistoryMarshaller
     */
    public static Marshaller<VersionHistory> getVersionHistoryMarshaller() {
        if (versionHistoryMarshaller == null) {
            versionHistoryMarshaller =
                new Marshaller<VersionHistory>(VersionHistory.class);
        }
        return versionHistoryMarshaller;
    }

    /**
     * @return the structMapMarshaller
     */
    public static Marshaller<StructMap> getStructMapMarshaller() {
        if (structMapMarshaller == null) {
            structMapMarshaller = new Marshaller<StructMap>(StructMap.class);
        }
        return structMapMarshaller;
    }

    /**
     * @return the adminDescriptorMarshaller
     */
    public static Marshaller<AdminDescriptor> getAdminDescriptorMarshaller() {
        if (adminDescriptorMarshaller == null) {
            adminDescriptorMarshaller =
                new Marshaller<AdminDescriptor>(AdminDescriptor.class);
        }
        return adminDescriptorMarshaller;
    }

    /**
     * @return the adminDescriptorListMarshaller
     */
    public static Marshaller<AdminDescriptors> getAdminDescriptorListMarshaller() {
        if (adminDescriptorListMarshaller == null) {
            adminDescriptorListMarshaller =
                new Marshaller<AdminDescriptors>(AdminDescriptors.class);
        }
        return adminDescriptorListMarshaller;
    }

    /**
     * @return the tocMarshaller
     */
    public static Marshaller<Toc> getTocMarshaller() {
        if (tocMarshaller == null) {
            tocMarshaller = new Marshaller<Toc>(Toc.class);
        }
        return tocMarshaller;
    }

    /* ********************************************************************** */

    /**
     * @return the propertiesMarshaller
     */
    public static Marshaller<Properties> getPropertiesMarshaller() {
        if (propertiesMarshaller == null) {
            propertiesMarshaller = new Marshaller<Properties>(Properties.class);
        }
        return propertiesMarshaller;
    }

    /**
     * @return the metadataRecordsMarshaller
     */
    public static Marshaller<MetadataRecords> getMetadataRecordsMarshaller() {
        if (metadataRecordsMarshaller == null) {
            metadataRecordsMarshaller =
                new Marshaller<MetadataRecords>(MetadataRecords.class);
        }
        return metadataRecordsMarshaller;
    }

    /**
     * @return the relationsRecordsMarshaller
     */
    public static Marshaller<Relations> getRelationsMarshaller() {
        if (relationsMarshaller == null) {
            relationsMarshaller = new Marshaller<Relations>(Relations.class);
        }
        return relationsMarshaller;
    }

    public static Marshaller<UserAccount> getUserAccountMarshaller() {
        if (userAccountMarshaller == null) {
            userAccountMarshaller =
                new Marshaller<UserAccount>(UserAccount.class);

        }
        return userAccountMarshaller;
    }

    public static Marshaller<Requests> getRequestsMarshaller() {
        if (requestsMarshaller == null) {
            requestsMarshaller = new Marshaller<Requests>(Requests.class);

        }
        return requestsMarshaller;
    }

    public static Marshaller<RequestsResults> getRequestsResultsMarshaller() {
        if (requestsResultsMarshaller == null) {
            requestsResultsMarshaller =
                new Marshaller<RequestsResults>(RequestsResults.class);

        }
        return requestsResultsMarshaller;
    }

    public static Marshaller<Role> getRoleMarshaller() {
        if (roleMarshaller == null) {
            roleMarshaller = new Marshaller<Role>(Role.class);

        }
        return roleMarshaller;
    }

    public static Marshaller<UserAccountProperties> getUserAccountPropertiesMarshaller() {
        if (userAccountPropertiesMarshaller == null) {
            userAccountPropertiesMarshaller =
                new Marshaller<UserAccountProperties>(UserAccount.class);

        }
        return userAccountPropertiesMarshaller;
    }

    public static Marshaller<Grants> getGrantsMarshaller() {
        if (grantsMarshaller == null) {
            grantsMarshaller = new Marshaller<Grants>(Grants.class);

        }
        return grantsMarshaller;
    }

    public static Marshaller<UserAccounts> getUserAccountListMarshaller() {
        if (userAccountListMarshaller == null) {
            userAccountListMarshaller =
                new Marshaller<UserAccounts>(UserAccounts.class);

        }
        return userAccountListMarshaller;
    }

    public static Marshaller<UnsecuredActions> getUnsecuredActionsMarshaller() {
        if (unsecuredActionsMarshaller == null) {
            unsecuredActionsMarshaller =
                new Marshaller<UnsecuredActions>(UnsecuredActions.class);

        }
        return unsecuredActionsMarshaller;
    }

    public static Marshaller<Roles> getRoleListMarshaller() {
        if (roleListMarshaller == null) {
            roleListMarshaller = new Marshaller<Roles>(Roles.class);

        }
        return roleListMarshaller;
    }

}
