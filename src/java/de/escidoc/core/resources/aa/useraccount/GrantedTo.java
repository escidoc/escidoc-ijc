package de.escidoc.core.resources.aa.useraccount;

import de.escidoc.core.resources.om.GenericResource;

public class GrantedTo extends GenericResource {

    private String resource;

    public String getResource() {
        return resource;
    }

    public void setResource(final String resource) {
        this.resource = resource;
    }
}
