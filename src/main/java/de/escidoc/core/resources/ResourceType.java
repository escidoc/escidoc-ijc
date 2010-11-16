package de.escidoc.core.resources;

/**
 * Types of eSciDoc resources.
 * 
 */
public enum ResourceType {
    // root resources
    Context(true, "context"), Item(true, "item"), Container(true,
        "container"), OrganizationalUnit(true, "organizational-unit"), UserAccount(
        true, "user-account"), ContentModel(true, "content-model"), Grant(
        true, "grant"), Role(true, "role"), ContentRelation(true,
        "content-relation"), Scope(true, "scope"), ReportDefinition(true,
        "report-definition"), AggregationDefinition(true,
        "aggregation-definition"),
    // sub resources
    Component(false, "component"), Toc(false, null), UserAccountAttribute(
        false, null);

    final boolean isRootResource;

    final String tagName;

    ResourceType(final boolean isRootResource, final String tagName) {
        this.isRootResource = isRootResource;
        this.tagName = tagName;
    }

    public boolean isRootResource() {
        return isRootResource;
    }

    public String getTagName() {
        return tagName;
    }

    public static final ResourceType valueByTagName(final String tagName) {
        if (tagName == null)
            return null;

        for (int i = 0; i < values().length; i++) {
            if (values()[i].tagName.equals(tagName))
                return values()[i];
        }
        return null;
    }
}