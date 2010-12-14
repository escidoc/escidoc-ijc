package de.escidoc.core.resources;

/**
 * Types of eSciDoc resources.
 * 
 */
public enum ResourceType {
    // root resources
    Context(true), Item(true), Container(true), OrganizationalUnit(true), UserAccount(
        true), ContentModel(true), Grant(true), Role(true), ContentRelation(
        true), Scope(true), ReportDefinition(true), AggregationDefinition(true),
    // sub resources
    Component(false), Toc(false), UserAccountAttribute(false);

    private final boolean isRootResource;

    ResourceType(final boolean isRootResource) {
        this.isRootResource = isRootResource;
    }

    public boolean isRootResource() {
        return isRootResource;
    }
}