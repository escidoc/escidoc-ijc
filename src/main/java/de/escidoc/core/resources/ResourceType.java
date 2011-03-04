package de.escidoc.core.resources;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.log4j.Logger;

import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.common.configuration.ConfigurationProvider;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.interfaces.XmlCompatibleEnum;
import de.escidoc.core.resources.oum.OrganizationalUnit;

/**
 * Types of eSciDoc resources.
 */
public enum ResourceType implements XmlCompatibleEnum {

    // root resources
    Context(de.escidoc.core.resources.om.context.Context.class, "context",
        ConfigurationProvider.NS_IR_CONTEXT, "/ir/context"),
    //
    Item(de.escidoc.core.resources.om.item.Item.class, "item",
        ConfigurationProvider.NS_IR_ITEM, "/ir/item"),
    //
    Container(de.escidoc.core.resources.om.container.Container.class,
        "container", ConfigurationProvider.NS_IR_CONTAINER, "/ir/container"),
    //
    OrganizationalUnit(OrganizationalUnit.class, "organizational-unit",
        ConfigurationProvider.NS_OUM_ORGANIZATIONAL_UNIT,
        "/oum/organizational-unit"),
    //
    UserAccount(UserAccount.class, "user-account",
        ConfigurationProvider.NS_AA_USER_ACCOUNT, "/aa/user-account"),
    //
    UserGroup(UserAccount.class, "user-group",
        ConfigurationProvider.NS_AA_USER_GROUP, "/aa/user-group"),
    //
    ContentModel(de.escidoc.core.resources.cmm.ContentModel.class,
        "content-model", ConfigurationProvider.NS_CMM_CONTENT_MODEL,
        "/cmm/content-model"),
    //
    Grant(de.escidoc.core.resources.aa.useraccount.Grant.class, "grant",
        ConfigurationProvider.NS_AA_GRANT, "/aa/grant"),
    //
    Role(de.escidoc.core.resources.aa.role.Role.class, "role",
        ConfigurationProvider.NS_AA_ROLE, "/aa/role"),
    //
    ContentRelation(
        de.escidoc.core.resources.om.contentRelation.ContentRelation.class,
        "content-relation", ConfigurationProvider.NS_IR_CONTENT_RELATION,
        "/ir/content-relation"),
    //
    Scope(de.escidoc.core.resources.sm.scope.Scope.class, "scope",
        ConfigurationProvider.NS_STATISTIC_SCOPE, "/statistic/scope"),
    //
    ReportDefinition(
        de.escidoc.core.resources.sm.report.ReportDefinition.class,
        "report-definition", ConfigurationProvider.NS_STATISTIC_REPORT_DEF,
        "/statistic/report-definition"),
    //
    AggregationDefinition(
        de.escidoc.core.resources.sm.ad.AggregationDefinition.class,
        "aggregation-definition",
        ConfigurationProvider.NS_STATISTIC_AGGREGATION_DEF,
        "/statistic/aggregation-definition"),
    // sub resources
    Component(de.escidoc.core.resources.om.item.component.Component.class,
        "component", ConfigurationProvider.NS_IR_COMPONENTS,
        "/components/component"),
    //
    SetDefinition(de.escidoc.core.resources.oai.SetDefinition.class,
        "set-defintion", ConfigurationProvider.NS_OAI_SET_DEFINITION,
        "/oai/set-definition"),
    //
    UserAccountAttribute("user-account-attribute",
        "/resources/attributes/attribute");

    private final boolean isRootResource;

    private final String xmlValue;

    private final URI namespace;

    private final Class<? extends Resource> clazz;

    private final String path;

    /**
     * @param isRootResource
     * @param xmlValue
     */
    ResourceType(final Class<? extends Resource> clazz, final String xmlValue,
        final String nsConfig, final String path) {

        this.clazz = clazz;
        this.isRootResource = true;
        this.xmlValue = xmlValue;
        this.path = path;

        if (nsConfig != null) {

            URI result = null;
            try {
                result =
                    new URI(ConfigurationProvider.getInstance().getProperty(
                        nsConfig));
            }
            catch (InternalClientException e) {
                Logger.getLogger(ResourceType.class).debug(e.getMessage(), e);
            }
            catch (URISyntaxException e) {
                Logger.getLogger(ResourceType.class).debug(e.getMessage(), e);
            }
            this.namespace = result;
        }
        else {
            this.namespace = null;
        }
    }

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

    public String getPath() {
        return path;
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
            ResourceType type = ResourceType.values()[i];
            if (value.equals(type.name()) || value.equals(type.getXmlValue())
                || value.equals(type.getPath()))
                return type;
        }
        return null;
    }
}