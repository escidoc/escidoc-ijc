package de.escidoc.core.common.jibx;

import java.util.HashMap;
import java.util.Map;

import de.escidoc.core.client.exceptions.InternalClientException;
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
import de.escidoc.core.resources.common.Properties;
import de.escidoc.core.resources.common.Relations;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
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
import de.escidoc.core.resources.oum.OrganizationalUnit;
import de.escidoc.core.resources.oum.OrganizationalUnitList;
import de.escidoc.core.resources.oum.Parents;
import de.escidoc.core.resources.oum.PathList;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.scan.ScanResponse;
import de.escidoc.core.resources.sb.search.SearchResult;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;
import de.escidoc.core.resources.sm.scope.Scope;

/**
 * Marshaller Factory.
 * 
 */
public final class MarshallerFactory {

    // AA
    public static final Class<UserAccount> CLASS_USER_ACCOUNT = UserAccount.class;

    public static final Class<Requests> CLASS_PDP_REQUESTS = Requests.class;

    public static final Class<Results> CLASS_PDP_RESULTS = Results.class;

    public static final Class<Role> CLASS_ROLE = Role.class;

    public static final Class<UserAccountProperties> CLASS_USER_ACCOUNT_PROPERTIES = UserAccountProperties.class;

    public static final Class<Grant> CLASS_GRANT = Grant.class;

    public static final Class<Grants> CLASS_GRANTS = Grants.class;

    public static final Class<Attribute> CLASS_ATTRIBUTE = Attribute.class;

    public static final Class<Attributes> CLASS_ATTRIBUTES = Attributes.class;

    public static final Class<Preference> CLASS_PREFERENCE = Preference.class;

    public static final Class<Preferences> CLASS_PREFERENCES = Preferences.class;

    public static final Class<UserAccounts> CLASS_USER_ACCOUNTS = UserAccounts.class;

    public static final Class<Roles> CLASS_ROLES = Roles.class;

    // OM
    public static final Class<Context> CLASS_CONTEXT = Context.class;

    public static final Class<ContentModel> CLASS_CONTENT_MODEL = ContentModel.class;

    public static final Class<MemberList> CLASS_MEMBER_LIST = MemberList.class;

    public static final Class<Item> CLASS_ITEM = Item.class;

    public static final Class<Container> CLASS_CONTAINER = Container.class;

    public static final Class<Component> CLASS_COMPONENT = Component.class;

    public static final Class<ContentRelation> CLASS_CONTENT_RELATION = ContentRelation.class;

    public static final Class<ItemList> CLASS_ITEM_LIST = ItemList.class;

    public static final Class<ContainerList> CLASS_CONTAINER_LIST = ContainerList.class;

    public static final Class<ContextList> CLASS_CONTEXT_LIST = ContextList.class;

    // OUM
    public static final Class<OrganizationalUnit> CLASS_ORGANIZATIONAL_UNIT = OrganizationalUnit.class;

    public static final Class<OrganizationalUnitList> CLASS_ORGANIZATIONAL_UNIT_LIST = OrganizationalUnitList.class;

    public static final Class<PathList> CLASS_PATH_LIST = PathList.class;

    public static final Class<Parents> CLASS_PARENTS = Parents.class;

    // SM

    public static final Class<Scope> CLASS_SCOPE = Scope.class;

    // Sub resources

    public static final Class<Properties> CLASS_PROPERTIES = Properties.class;

    public static final Class<VersionHistory> CLASS_VERSION_HISTORY = VersionHistory.class;

    public static final Class<StructMap> CLASS_STRUCT_MAP = StructMap.class;

    public static final Class<AdminDescriptor> CLASS_ADMIN_DESCRIPTOR = AdminDescriptor.class;

    public static final Class<AdminDescriptors> CLASS_ADMIN_DESCRIPTORS = AdminDescriptors.class;

    public static final Class<MetadataRecords> CLASS_METADATA_RECORDS = MetadataRecords.class;

    public static final Class<MetadataRecord> CLASS_METADATA_RECORD = MetadataRecord.class;

    public static final Class<Relations> CLASS_RELATIONS = Relations.class;

    // SRW/U

    public static final Class<SearchResult> CLASS_SEARCH_RESULT_RECORD = SearchResult.class;

    public static final Class<ExplainResponse> CLASS_EXPLAIN_RESPONSE = ExplainResponse.class;

    public static final Class<ScanResponse> CLASS_SCAN_RESPONSE = ScanResponse.class;

    public static final Class<SearchRetrieveResponse> CLASS_SEARCH_RETRIEVE_RESPONSE = SearchRetrieveResponse.class;

    // COMMON

    public static final Class<de.escidoc.core.resources.common.Properties> CLASS_COMMON_PROPERTIES =
        de.escidoc.core.resources.common.Properties.class;

    public static final Class<Result> CLASS_RESULT = Result.class;

    public static final Class<TaskParam> CLASS_TASK_PARAM = TaskParam.class;

    private static MarshallerFactory instance;

    /**
     * The HashMap to store the Marshaller instances.
     */
    private final Map<Class<?>, Marshaller<?>> marshallers;

    /**
     * 
     * @param transport
     */
    private MarshallerFactory() {
        marshallers = new HashMap<Class<?>, Marshaller<?>>();
    }

    /**
     * 
     * @return The default instance of the MarshallerFactory using the default
     *         transport protocol REST since CLIB 1.3.
     * @throws InternalClientException
     */
    public static final MarshallerFactory getInstance() throws InternalClientException {

        if (instance == null) {
            instance = new MarshallerFactory();
        }
        return instance;
    }

    /**
     * This is a generic method to return a classified Marshaller instance
     * depending on the specified <i>clazz</i>. The Marshaller instance exists
     * only once within this factory instance.<br/>
     * <br/>
     * Example:<br/>
     * <br/>
     * <tt>MarshallerFactory.getInstance().getMarshaller(MarshallerFactory.SCOPE);</tt>
     * <br/>
     * <br/>
     * or<br/>
     * <br/>
     * <tt>MarshallerFactory.getInstance().getMarshaller(Scope.class);</tt><br/>
     * <br/>
     * will return the classified Marshaller:<br/>
     * <br/>
     * <code>Marshaller&lt;Scope&gt;</code><br/>
     * <br/>
     * for the Object:<br/>
     * <br/>
     * <tt>Scope</tt>.
     * 
     * @param <T>
     *            The type of the class to be used to initialize the Marshaller.
     *            &lt;T&gt; will be defined by <tt>clazz</tt>.
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
            marshaller = Marshaller.getMarshaller(clazz);
            marshallers.put(clazz, marshaller);

        }
        else {
            marshaller = (Marshaller<T>) marshallers.get(clazz);
        }
        return marshaller;
    }
}