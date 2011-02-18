package de.escidoc.core.resources;

import java.net.URI;
import java.net.URISyntaxException;

import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.common.configuration.ConfigurationProvider;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.oum.OrganizationalUnit;

/**
 * Types of eSciDoc resources.
 * 
 * TODO: Do we need ReportDefinition, AggregationDefinition, Toc and
 * UserAccountAttribute as types?
 */
public enum ResourceType {
    // root resources
    Context(de.escidoc.core.resources.om.context.Context.class, "context",
        ConfigurationProvider.NS_IR_CONTEXT),
    //
    Item(de.escidoc.core.resources.om.item.Item.class, "item",
        ConfigurationProvider.NS_IR_ITEM),
    //
    Container(de.escidoc.core.resources.om.container.Container.class,
        "container", ConfigurationProvider.NS_IR_CONTAINER),
    //
    OrganizationalUnit(OrganizationalUnit.class, "organizational-unit",
        ConfigurationProvider.NS_OUM_ORGANIZATIONAL_UNIT),
    //
    UserAccount(UserAccount.class, "user-account",
        ConfigurationProvider.NS_AA_USER_ACCOUNT),
    //
    UserGroup(UserAccount.class, "user-group",
        ConfigurationProvider.NS_AA_USER_GROUP),
    //
    ContentModel(de.escidoc.core.resources.cmm.ContentModel.class,
        "content-model", ConfigurationProvider.NS_CMM_CONTENT_MODEL),
    //
    Grant(de.escidoc.core.resources.aa.useraccount.Grant.class, "grant",
        ConfigurationProvider.NS_AA_GRANT),
    //
    Role(de.escidoc.core.resources.aa.role.Role.class, "role",
        ConfigurationProvider.NS_AA_ROLE),
    //
    ContentRelation(
        de.escidoc.core.resources.om.contentRelation.ContentRelation.class,
        "content-relation", ConfigurationProvider.NS_IR_CONTENT_RELATION),
    //
    Scope(de.escidoc.core.resources.sm.scope.Scope.class, "scope",
        ConfigurationProvider.NS_STATISTIC_SCOPE),
    //
    ReportDefinition(
        de.escidoc.core.resources.sm.report.ReportDefinition.class,
        "report-definition", ConfigurationProvider.NS_STATISTIC_REPORT_DEF),
    //
    AggregationDefinition(
        de.escidoc.core.resources.sm.ad.AggregationDefinition.class,
        "aggregation-definition",
        ConfigurationProvider.NS_STATISTIC_AGGREGATION_DEF),
    // sub resources
    Component(de.escidoc.core.resources.om.item.component.Component.class,
        "component", ConfigurationProvider.NS_IR_COMPONENTS),
    //
    Toc("toc"),
    //
    UserAccountAttribute("user-account-attribute");

    private final boolean isRootResource;

    private final String xmlValue;

    private final URI namespace;

    private final Class<? extends Resource> clazz;

    /**
     * @param isRootResource
     * @param xmlValue
     */
    ResourceType(final Class<? extends Resource> clazz, final String xmlValue,
        final String nsConfig) {

        this.clazz = clazz;
        this.isRootResource = true;
        this.xmlValue = xmlValue;

        if (nsConfig != null) {

            URI result = null;
            try {
                result =
                    new URI(ConfigurationProvider.getInstance().getProperty(
                        nsConfig));
            }
            catch (InternalClientException e) {
                e.printStackTrace();
            }
            catch (URISyntaxException e) {
                e.printStackTrace();
            }
            this.namespace = result;
        }
        else {
            this.namespace = null;
        }
    }

    ResourceType(final String xmlValue) {
        this.clazz = null;
        this.isRootResource = false;
        this.xmlValue = xmlValue;
        this.namespace = null;
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

    /**
     * @param value
     * @return
     */
    public static final ResourceType getValue(final String value) {

        if (value == null)
            return null;

        ResourceType result = null;
        for (int i = 0; i < ResourceType.values().length; i++) {
            if (ResourceType.values()[i].name().equals(value)
                || ResourceType.values()[i].getXmlValue().equals(value))
                result = ResourceType.values()[i];
        }
        return result;
    }

    /**
     * @return the xmlValue
     */
    public String getXmlValue() {
        return xmlValue;
    }

    /**
     * @return the namespace
     */
    public URI getNamespace() {
        return namespace;
    }
}