package de.escidoc.core.resources.sb.explain;

public class Schema {
    private boolean sort;

    private boolean retrieve;

    private String name;

    private String identifier;

    private String location;

    private String title;

    public boolean isSort() {
        return sort;
    }

    public void setSort(final boolean sort) {
        this.sort = sort;
    }

    public boolean isRetrieve() {
        return retrieve;
    }

    public void setRetrieve(final boolean retrieve) {
        this.retrieve = retrieve;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(final String identifier) {
        this.identifier = identifier;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

}
