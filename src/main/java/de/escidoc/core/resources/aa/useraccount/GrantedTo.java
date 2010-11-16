package de.escidoc.core.resources.aa.useraccount;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.om.GenericResource;

@JiBX
public class GrantedTo extends GenericResource {

    private String resource;

    public GrantedTo() {
        setResourceType(ResourceType.UserAccount);
    }

    public String getResource() {
        return resource;
    }

    public void setResource(final String resource) {
        this.resource = resource;
    }
}
