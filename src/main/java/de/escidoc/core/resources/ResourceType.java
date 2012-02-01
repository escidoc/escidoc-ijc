package de.escidoc.core.resources;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.LoggerFactory;

import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.common.configuration.ConfigurationProvider;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.aa.usergroup.UserGroup;
import de.escidoc.core.resources.interfaces.XmlCompatibleEnum;
import de.escidoc.core.resources.oum.OrganizationalUnit;

/**
 * Types of eSciDoc resources.
 */
public enum ResourceType implements XmlCompatibleEnum {

    // root resources
    CONTEXT(de.escidoc.core.resources.om.context.Context.class, "context", ConfigurationProvider.NS_IR_CONTEXT,
        "/ir/context"),
    //
    ITEM(de.escidoc.core.resources.om.item.Item.class, "item", ConfigurationProvider.NS_IR_ITEM, "/ir/item"),
    //
    CONTAINER(de.escidoc.core.resources.om.container.Container.class, "container",
        ConfigurationProvider.NS_IR_CONTAINER, "/ir/container"),
    //
    ORGANIZATIONAL_UNIT(OrganizationalUnit.class, "organizational-unit",
        ConfigurationProvider.NS_OUM_ORGANIZATIONAL_UNIT, "/oum/organizational-unit"),
    //
    USERACCOUNT(UserAccount.class, "user-account", ConfigurationProvider.NS_AA_USER_ACCOUNT, "/aa/user-account"),
    //
    USERGROUP(UserGroup.class, "user-group", ConfigurationProvider.NS_AA_USER_GROUP, "/aa/user-group"),
    //
    CONTENT_MODEL(de.escidoc.core.resources.cmm.ContentModel.class, "content-model",
        ConfigurationProvider.NS_CMM_CONTENT_MODEL, "/cmm/content-model"),
    //
    GRANT(de.escidoc.core.resources.aa.useraccount.Grant.class, "grant", ConfigurationProvider.NS_AA_GRANT, "/aa/grant"),
    //
    ROLE(de.escidoc.core.resources.aa.role.Role.class, "role", ConfigurationProvider.NS_AA_ROLE, "/aa/role"),
    //
    CONTENT_RELATION(de.escidoc.core.resources.om.contentRelation.ContentRelation.class, "content-relation",
        ConfigurationProvider.NS_IR_CONTENT_RELATION, "/ir/content-relation"),
    //
    SCOPE(de.escidoc.core.resources.sm.scope.Scope.class, "scope", ConfigurationProvider.NS_STATISTIC_SCOPE,
        "/statistic/scope"),
    //
    REPORT_DEFINITION(de.escidoc.core.resources.sm.report.ReportDefinition.class, "report-definition",
        ConfigurationProvider.NS_STATISTIC_REPORT_DEF, "/statistic/report-definition"),
    //
    AGGREGATION_DEFINITION(de.escidoc.core.resources.sm.ad.AggregationDefinition.class, "aggregation-definition",
        ConfigurationProvider.NS_STATISTIC_AGGREGATION_DEF, "/statistic/aggregation-definition"),
    //
    STATISTIC_DATA("statistic-data", "/statistic/statistic-data"),
    /**
     * A report is no real Resource since it has no objid or xlink-attributes
     * and is a generated result of statistics.
     */
    REPORT("report", "/statistic/report"),
    /**
     * Preprocessing is no real Resource since it does not exist as an object.
     */
    PREPROCESSING("preprocessing", "/statistic/preprocessing"),
    //
    SET_DEFINITION(de.escidoc.core.resources.oai.SetDefinition.class, "set-defintion",
        ConfigurationProvider.NS_OAI_SET_DEFINITION, "/oai/set-definition"),

    /**
     * Staging-File is no real Resource since it does not exist as an object.
     */
    STAGING_FILE("staging-file", "/st/staging-file"),

    // sub resources
    //
    COMPONENT(de.escidoc.core.resources.om.item.component.Component.class, "component",
        ConfigurationProvider.NS_IR_COMPONENTS, "/component", false),

    // TODO: delete?
    USERACCOUNT_ATTRIBUTE("user-account-attribute", "/attribute");

    private final boolean isRootResource;

    private final String xmlValue;

    private final URI namespace;

    private final Class<? extends Resource> clazz;

    private final String path;

    /**
     * @param clazz
     * @param xmlValue
     * @param nsConfig
     * @param path
     * @param isRootResource
     */
    ResourceType(final Class<? extends Resource> clazz, final String xmlValue, final String nsConfig,
        final String path, final boolean isRootResource) {

        this.clazz = clazz;
        this.isRootResource = isRootResource;
        this.xmlValue = xmlValue;
        this.path = path;

        if (nsConfig != null) {

            URI result = null;
            try {
                result = new URI(ConfigurationProvider.getInstance().getProperty(nsConfig));
            }
            catch (final InternalClientException e) {
                LoggerFactory.getLogger(ResourceType.class).debug(e.getMessage(), e);
            }
            catch (final URISyntaxException e) {
                LoggerFactory.getLogger(ResourceType.class).debug(e.getMessage(), e);
            }
            this.namespace = result;
        }
        else {
            this.namespace = null;
        }
    }

    /**
     * @param clazz
     * @param xmlValue
     * @param nsConfig
     * @param path
     */
    ResourceType(final Class<? extends Resource> clazz, final String xmlValue, final String nsConfig, final String path) {
        this(clazz, xmlValue, nsConfig, path, true);
    }

    /**
     * @param xmlValue
     * @param path
     */
    ResourceType(final String xmlValue, final String path) {
        this.clazz = null;
        this.isRootResource = false;
        this.xmlValue = xmlValue;
        this.namespace = null;
        this.path = path;
    }

    /**
     * @return
     */
    public boolean isRootResource() {
        return isRootResource;
    }

    /**
     * @return
     */
    public Class<? extends Resource> getResourceClass() {
        return this.clazz;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.enums.XmlCompatibleEnum#getXmlValue()
     */
    @Override
    public String getXmlValue() {
        return xmlValue;
    }

    /**
     * @return the namespace
     */
    public URI getNamespace() {
        return namespace;
    }

    /**
     * @return
     */
    public String getPath() {
        return path;
    }

    /**
     * @return The path of the resource including the objid or null if the objid
     *         is null.
     */
    public String getPath(final String objid) {
        if (objid != null)
            return path + '/' + objid;
        return null;
    }

    /**
     * @param value
     *            The value can be either the String representation, the XML
     *            value representation or the path of this ResourceType.
     * @return
     */
    public static final ResourceType getValue(final String value) {

        if (value == null)
            return null;

        for (int i = 0; i < ResourceType.values().length; i++) {
            final ResourceType type = ResourceType.values()[i];
            if (value.equals(type.name()) || value.equals(type.getXmlValue()) || value.equals(type.getPath()))
                return type;
        }
        return null;
    }
}