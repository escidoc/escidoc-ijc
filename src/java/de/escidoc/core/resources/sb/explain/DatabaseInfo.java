package de.escidoc.core.resources.sb.explain;

public class DatabaseInfo {

    private String title;

    private String description;

    private String contact;

    private Implementation implementation;

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(final String contact) {
        this.contact = contact;
    }

    public Implementation getImplementation() {
        return implementation;
    }

    public void setImplementation(final Implementation implementation) {
        this.implementation = implementation;
    }

}
