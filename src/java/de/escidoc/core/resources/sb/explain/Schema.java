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
    public void setSort(boolean sort) {
        this.sort = sort;
    }
    public boolean isRetrieve() {
        return retrieve;
    }
    public void setRetrieve(boolean retrieve) {
        this.retrieve = retrieve;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIdentifier() {
        return identifier;
    }
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
   
}
