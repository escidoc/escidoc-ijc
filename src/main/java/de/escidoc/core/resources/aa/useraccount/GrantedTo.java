package de.escidoc.core.resources.aa.useraccount;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.Resource;
import de.escidoc.core.resources.ResourceType;

/**
 * FIXME:
 * <ul>
 * <li>variable resource ?</li>
 * <li>extends GenericResource ?</li>
 * </ul>
 * 
 * 
 * @author ?
 * 
 */
@JiBX
public class GrantedTo extends Resource {

    private String resource;

    public GrantedTo() {
    }

    public String getResource() {
        return resource;
    }

    public void setResource(final String resource) {
        this.resource = resource;
    }

    @Override
    public ResourceType getResourceType() {
        return ResourceType.UserAccount;
    }
}
