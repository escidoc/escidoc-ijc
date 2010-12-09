package de.escidoc.core.common.jibx;

import java.util.HashMap;
import java.util.Map;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.common.configuration.ConfigurationProvider;
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
import de.escidoc.core.resources.sm.scope.Scope;

/**
 * Marshaller Factory.
 * 
 * TODO: Remove comments and/or old code after review if accepted.
 */
public class MarshallerFactory {

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

    private static final Map<TransportProtocol, MarshallerFactory> marshallerFactoryMap =
        new HashMap<TransportProtocol, MarshallerFactory>();

    private static TransportProtocol defaultTransport = null;

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
    private MarshallerFactory(final TransportProtocol transport) {
        if (transport == null)
            throw new IllegalArgumentException("transport must not be null.");

        this.transport = transport;
    }

    /**
     * 
     * @return
     * @throws InternalClientException
     */
    public static final MarshallerFactory getInstance()
        throws InternalClientException {

        if (defaultTransport == null) {
            defaultTransport =
                TransportProtocol.valueOf(ConfigurationProvider
                    .getInstance().getProperty(
                        ConfigurationProvider.PROP_SERVICE_PROTOCOL));
        }
        if (defaultTransport == null)
            throw new InternalClientException(
                "Unable to load default transport protocol from configuration.");
        if (marshallerFactoryMap.get(defaultTransport) == null) {
            MarshallerFactory resultFactory =
                new MarshallerFactory(defaultTransport);
            marshallerFactoryMap.put(defaultTransport, resultFactory);
            return resultFactory;
        }
        return marshallerFactoryMap.get(defaultTransport);
    }

    /**
     * 
     * @param transport
     * @return
     * @throws InternalClientException
     */
    public static final MarshallerFactory getInstance(
        final TransportProtocol transport) throws InternalClientException {

        if (marshallerFactoryMap.get(transport) == null) {
            MarshallerFactory resultFactory = new MarshallerFactory(transport);
            marshallerFactoryMap.put(transport, resultFactory);
            return resultFactory;
        }
        return marshallerFactoryMap.get(transport);
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
}