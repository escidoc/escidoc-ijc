package de.escidoc.core.common.jibx;

import java.util.HashMap;

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
import de.escidoc.core.resources.sm.Scope;

/**
 * Marshaller Factory.
 * 
 * TODO: Remove comments and/or old code after review if accepted.
 */
public class MarshallerFactory {

    /*
     * ################ START OF NEW IMPL ##################
     */

    // AA
    public static final Class<UserAccount> CLASS_USER_ACCOUNT =
        UserAccount.class;

    public static final Class<Requests> CLASS_PDP_REQUESTS = Requests.class;

    public static final Class<Results> CLASS_PDP_RESULTS = Results.class;

    public static final Class<Role> CLASS_ROLE = Role.class;

    public static final Class<UserAccountProperties> CLASS_USER_ACCOUNT_PROPERTIES =
        UserAccountProperties.class;

    public static final Class<Grant> CLASS_GRANT = Grant.class;

    public static final Class<Grants> CLASS_GRANTS = Grants.class;

    public static final Class<Attribute> CLASS_ATTRIBUTE = Attribute.class;

    public static final Class<Attributes> CLASS_ATTRIBUTES = Attributes.class;

    public static final Class<Preference> CLASS_PREFERENCE = Preference.class;

    public static final Class<Preferences> CLASS_PREFERENCES =
        Preferences.class;

    public static final Class<UserAccounts> CLASS_USER_ACCOUNTS =
        UserAccounts.class;

    public static final Class<Roles> CLASS_ROLES = Roles.class;

    public static final Class<UnsecuredActions> CLASS_UNSECURED_ACTIONS =
        UnsecuredActions.class;

    // OM
    public static final Class<Context> CLASS_CONTEXT = Context.class;

    public static final Class<ContentModel> CLASS_CONTENT_MODEL =
        ContentModel.class;

    public static final Class<MemberList> CLASS_MEMBER_LIST = MemberList.class;

    public static final Class<Item> CLASS_ITEM = Item.class;

    public static final Class<Container> CLASS_CONTAINER = Container.class;

    public static final Class<Component> CLASS_COMPONENT = Component.class;

    public static final Class<ContentRelation> CLASS_CONTENT_RELATION =
        ContentRelation.class;

    public static final Class<Toc> CLASS_TOC = Toc.class;

    public static final Class<ItemList> CLASS_ITEM_LIST = ItemList.class;

    public static final Class<ContainerList> CLASS_CONTAINER_LIST =
        ContainerList.class;

    public static final Class<ContextList> CLASS_CONTEXT_LIST =
        ContextList.class;

    // OUM
    public static final Class<OrganizationalUnit> CLASS_ORGANIZATIONAL_UNIT =
        OrganizationalUnit.class;

    public static final Class<OrganizationalUnitList> CLASS_ORGANIZATIONAL_UNIT_LIST =
        OrganizationalUnitList.class;

    // FIXME: Not implemented?
    public static final Class<PathList> CLASS_PATH_LIST = PathList.class;

    public static final Class<Parents> CLASS_PARENTS = Parents.class;

    // SM

    public static final Class<Scope> CLASS_SCOPE = Scope.class;

    // Sub resources

    public static final Class<Properties> CLASS_PROPERTIES = Properties.class;

    public static final Class<VersionHistory> CLASS_VERSION_HISTORY =
        VersionHistory.class;

    public static final Class<StructMap> CLASS_STRUCT_MAP = StructMap.class;

    public static final Class<AdminDescriptor> CLASS_ADMIN_DESCRIPTOR =
        AdminDescriptor.class;

    public static final Class<AdminDescriptors> CLASS_ADMIN_DESCRIPTORS =
        AdminDescriptors.class;

    public static final Class<MetadataRecords> CLASS_METADATA_RECORDS =
        MetadataRecords.class;

    public static final Class<MetadataRecord> CLASS_METADATA_RECORD =
        MetadataRecord.class;

    public static final Class<Relations> CLASS_RELATIONS = Relations.class;

    // SRW/U

    public static final Class<SearchResultRecord> CLASS_SEARCH_RESULT_RECORD =
        SearchResultRecord.class;

    public static final Class<ExplainResponse> CLASS_EXPLAIN_RESPONSE =
        ExplainResponse.class;

    public static final Class<ScanResponse> CLASS_SCAN_RESPONSE =
        ScanResponse.class;

    public static final Class<SearchRetrieveResponse> CLASS_SEARCH_RETRIEVE_RESPONSE =
        SearchRetrieveResponse.class;

    // COMMON

    public static final Class<de.escidoc.core.resources.common.Properties> CLASS_COMMON_PROPERTIES =
        de.escidoc.core.resources.common.Properties.class;

    public static final Class<Result> CLASS_RESULT = Result.class;

    public static final Class<TaskParam> CLASS_TASK_PARAM = TaskParam.class;

    /**
     * The TransportProtocol used for this MarshallerFactory instance.
     */
    private final TransportProtocol transport;

    /**
     * The HashMap to store the Marshaller instances.
     */
    private HashMap<Class<?>, Marshaller<?>> marshallers =
        new HashMap<Class<?>, Marshaller<?>>();

    /**
     * 
     * @param transport
     */
    protected MarshallerFactory(final TransportProtocol transport) {
        if (transport == null)
            throw new IllegalArgumentException("transport must not be null.");

        this.transport = transport;
    }

    /**
     * This is a generic method to return a classified Marshaller instance
     * depending on the specified <i>clazz</i>. The Marshaller instance exists
     * only once within this factory instance.<br/>
     * <br/>
     * Example:<br/>
     * <br/>
     * <code>Factory.getMarshallerFactory(TransportProtocol.REST).getMarshaller(
     * MarshallerFactory.SCOPE);</code><br/>
     * <br/>
     * or<br/>
     * <br/>
     * <code>Factory.getMarshallerFactory(TransportProtocol.REST).getMarshaller(
     * Scope.class);</code><br/>
     * <br/>
     * will return the classified Marshaller:<br/>
     * <br/>
     * <code>Marshaller&lt;Scope&gt;</code><br/>
     * <br/>
     * for the Object:<br/>
     * <br/>
     * <code>Scope</code>.
     * 
     * @param <T>
     *            The type of the class to be used to initialize the Marshaller.
     *            <T> will be defined by <i>clazz</i>.
     * @param clazz
     *            The class object of the Object to return a Marshaller for. All
     *            existing classes are declared within this factory as static
     *            variables.
     * @return The classified Marshaller instance depending on the specified
     *         clazz.
     */
    @SuppressWarnings("unchecked")
    public <T> Marshaller<T> getMarshaller(final Class<T> clazz) {

        if (clazz == null)
            throw new IllegalArgumentException("clazz must not be null.");

        Marshaller<T> marshaller;

        if (!marshallers.containsKey(clazz)) {
            marshaller = Marshaller.getMarshaller(clazz, transport.name());
            marshallers.put(clazz, marshaller);

        }
        else {
            marshaller = (Marshaller<T>) marshallers.get(clazz);
        }
        return marshaller;
    }

    /*
     * ################ END OF NEW IMPL ##################
     */
    @Deprecated
    private Marshaller<OrganizationalUnit> organizationalUnitMarshaller = null;

    @Deprecated
    private Marshaller<OrganizationalUnitList> organizationalUnitListMarshaller =
        null;

    @Deprecated
    private Marshaller<Context> contextMarshaller = null;

    @Deprecated
    private Marshaller<ContentModel> contentModelMarshaller = null;

    @Deprecated
    private Marshaller<MemberList> memberListMarshaller = null;

    @Deprecated
    private Marshaller<Item> itemMarshaller = null;

    @Deprecated
    private Marshaller<PathList> pathListMarshaller = null;

    @Deprecated
    private Marshaller<Component> componentMarshaller = null;

    @Deprecated
    private Marshaller<Container> containerMarshaller = null;

    @Deprecated
    private Marshaller<ContentRelation> contentRelationMarshaller = null;

    @Deprecated
    private Marshaller<UserAccount> userAccountMarshaller = null;

    @Deprecated
    private Marshaller<Requests> pdpRequestsMarshaller = null;

    @Deprecated
    private Marshaller<Results> pdpResultsMarshaller = null;

    @Deprecated
    private Marshaller<Role> roleMarshaller = null;

    @Deprecated
    private Marshaller<UserAccountProperties> userAccountPropertiesMarshaller =
        null;

    @Deprecated
    private Marshaller<Grant> grantMarshaller = null;

    @Deprecated
    private Marshaller<Grants> grantsMarshaller = null;

    @Deprecated
    private Marshaller<Attribute> attributeMarshaller = null;

    @Deprecated
    private Marshaller<Attributes> attributesMarshaller = null;

    @Deprecated
    private Marshaller<Preference> preferenceMarshaller = null;

    @Deprecated
    private Marshaller<Preferences> preferencesMarshaller = null;

    @Deprecated
    private Marshaller<UserAccounts> userAccountListMarshaller = null;

    @Deprecated
    private Marshaller<Roles> roleListMarshaller = null;

    @Deprecated
    private Marshaller<UnsecuredActions> unsecuredActionsMarshaller = null;

    @Deprecated
    private Marshaller<Toc> tocMarshaller = null;

    @Deprecated
    private Marshaller<ItemList> itemListMarshaller = null;

    @Deprecated
    private Marshaller<ContainerList> containerListMarshaller = null;

    @Deprecated
    private Marshaller<ContextList> contextListMarshaller = null;

    @Deprecated
    private Marshaller<TaskParam> taskParamMarshaller = null;

    @Deprecated
    private Marshaller<Result> resultMarshaller = null;

    @Deprecated
    private Marshaller<Parents> parentsMarshaller = null;

    @Deprecated
    private Marshaller<Scope> scopeMarshaller = null;

    /*
     * ========================================================================
     * Sub resources
     * ========================================================================
     */

    @Deprecated
    private Marshaller<Properties> propertiesMarshaller = null;

    @Deprecated
    private Marshaller<VersionHistory> versionHistoryMarshaller = null;

    @Deprecated
    private Marshaller<StructMap> structMapMarshaller = null;

    @Deprecated
    private Marshaller<AdminDescriptor> adminDescriptorMarshaller = null;

    @Deprecated
    private Marshaller<AdminDescriptors> adminDescriptorListMarshaller = null;

    @Deprecated
    private Marshaller<MetadataRecords> metadataRecordsMarshaller = null;

    @Deprecated
    private Marshaller<MetadataRecord> metadataRecordMarshaller = null;

    @Deprecated
    private Marshaller<Relations> relationsMarshaller = null;

    /*
     * ========================================================================
     * SRW/SRU
     * ========================================================================
     */

    @Deprecated
    private Marshaller<SearchResultRecord> searchResultMarshaller = null;

    @Deprecated
    private Marshaller<ExplainResponse> explainResponseMarshaller = null;

    @Deprecated
    private Marshaller<ScanResponse> scanResponseMarshaller = null;

    @Deprecated
    private Marshaller<SearchRetrieveResponse> searchRetrieveResponseMarshaller =
        null;

    @Deprecated
    private Marshaller<de.escidoc.core.resources.common.Properties> commonPropertiesMarshaller;

    /**
     * @return the itemMarshaller
     */
    @Deprecated
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
    @Deprecated
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
    @Deprecated
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
    @Deprecated
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
    @Deprecated
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
    @Deprecated
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
    @Deprecated
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
    @Deprecated
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
    @Deprecated
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
    @Deprecated
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
    @Deprecated
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
    @Deprecated
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
    @Deprecated
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
    @Deprecated
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
    @Deprecated
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
    @Deprecated
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
    @Deprecated
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
    @Deprecated
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
    @Deprecated
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
    @Deprecated
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
    @Deprecated
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
    @Deprecated
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
    @Deprecated
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
    @Deprecated
    public Marshaller<Relations> getRelationsMarshaller() {
        if (relationsMarshaller == null) {
            relationsMarshaller = new Marshaller<Relations>(Relations.class);
        }
        relationsMarshaller.setBindingName(transport.name());

        return relationsMarshaller;
    }

    @Deprecated
    public Marshaller<UserAccount> getUserAccountMarshaller() {
        if (userAccountMarshaller == null) {
            userAccountMarshaller =
                new Marshaller<UserAccount>(UserAccount.class);

        }
        userAccountMarshaller.setBindingName(transport.name());

        return userAccountMarshaller;
    }

    @Deprecated
    public Marshaller<Requests> getPDPRequestsMarshaller() {
        if (pdpRequestsMarshaller == null) {
            pdpRequestsMarshaller = new Marshaller<Requests>(Requests.class);

        }
        pdpRequestsMarshaller.setBindingName(transport.name());

        return pdpRequestsMarshaller;
    }

    @Deprecated
    public Marshaller<Results> getPDPResultsMarshaller() {
        if (pdpResultsMarshaller == null) {
            pdpResultsMarshaller = new Marshaller<Results>(Results.class);

        }
        pdpResultsMarshaller.setBindingName(transport.name());
        return pdpResultsMarshaller;
    }

    @Deprecated
    public Marshaller<Role> getRoleMarshaller() {
        if (roleMarshaller == null) {
            roleMarshaller = new Marshaller<Role>(Role.class);

        }
        roleMarshaller.setBindingName(transport.name());

        return roleMarshaller;
    }

    @Deprecated
    public Marshaller<UserAccountProperties> getUserAccountPropertiesMarshaller() {
        if (userAccountPropertiesMarshaller == null) {
            userAccountPropertiesMarshaller =
                new Marshaller<UserAccountProperties>(
                    UserAccountProperties.class);

        }
        userAccountPropertiesMarshaller.setBindingName(transport.name());

        return userAccountPropertiesMarshaller;
    }

    @Deprecated
    public Marshaller<Grant> getGrantMarshaller() {
        if (grantMarshaller == null) {
            grantMarshaller = new Marshaller<Grant>(Grant.class);
        }
        grantMarshaller.setBindingName(transport.name());

        return grantMarshaller;
    }

    @Deprecated
    public Marshaller<Grants> getGrantsMarshaller() {
        if (grantsMarshaller == null) {
            grantsMarshaller = new Marshaller<Grants>(Grants.class);
        }
        grantsMarshaller.setBindingName(transport.name());

        return grantsMarshaller;
    }

    @Deprecated
    public Marshaller<Attribute> getAttributeMarshaller() {
        if (attributeMarshaller == null) {
            attributeMarshaller = new Marshaller<Attribute>(Attribute.class);
        }
        attributeMarshaller.setBindingName(transport.name());

        return attributeMarshaller;
    }

    @Deprecated
    public Marshaller<Attributes> getAttributesMarshaller() {
        if (attributesMarshaller == null) {
            attributesMarshaller = new Marshaller<Attributes>(Attributes.class);
        }
        attributesMarshaller.setBindingName(transport.name());

        return attributesMarshaller;
    }

    @Deprecated
    public Marshaller<Preference> getPreferenceMarshaller() {
        if (preferenceMarshaller == null) {
            preferenceMarshaller = new Marshaller<Preference>(Preference.class);
        }
        preferenceMarshaller.setBindingName(transport.name());

        return preferenceMarshaller;
    }

    @Deprecated
    public Marshaller<Preferences> getPreferencesMarshaller() {
        if (preferencesMarshaller == null) {
            preferencesMarshaller =
                new Marshaller<Preferences>(Preferences.class);
        }
        preferencesMarshaller.setBindingName(transport.name());

        return preferencesMarshaller;
    }

    @Deprecated
    public Marshaller<UserAccounts> getUserAccountListMarshaller() {
        if (userAccountListMarshaller == null) {
            userAccountListMarshaller =
                new Marshaller<UserAccounts>(UserAccounts.class);
        }
        userAccountListMarshaller.setBindingName(transport.name());

        return userAccountListMarshaller;
    }

    @Deprecated
    public Marshaller<UnsecuredActions> getUnsecuredActionsMarshaller() {
        if (unsecuredActionsMarshaller == null) {
            unsecuredActionsMarshaller =
                new Marshaller<UnsecuredActions>(UnsecuredActions.class);
        }
        unsecuredActionsMarshaller.setBindingName(transport.name());

        return unsecuredActionsMarshaller;
    }

    @Deprecated
    public Marshaller<Roles> getRoleListMarshaller() {
        if (roleListMarshaller == null) {
            roleListMarshaller = new Marshaller<Roles>(Roles.class);
        }
        roleListMarshaller.setBindingName(transport.name());

        return roleListMarshaller;
    }

    @Deprecated
    public Marshaller<Parents> getParentsMarshaller() {
        if (parentsMarshaller == null) {
            parentsMarshaller = new Marshaller<Parents>(Parents.class);
        }
        parentsMarshaller.setBindingName(transport.name());

        return parentsMarshaller;
    }

    @Deprecated
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
    @Deprecated
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
    @Deprecated
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
    @Deprecated
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
     * 
     * @return
     */
    @Deprecated
    public Marshaller<Scope> getScopeMarshaller() {
        if (scopeMarshaller == null) {
            scopeMarshaller = new Marshaller<Scope>(Scope.class);
        }
        scopeMarshaller.setBindingName(transport.name());

        return scopeMarshaller;
    }

    /**
     * Returns a marshaller to be used for marshalling/unmarshalling of common
     * properties. For example:<br/>
     * <br/>
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
    @Deprecated
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
