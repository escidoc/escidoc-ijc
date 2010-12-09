package de.escidoc.core.resources.aa.useraccount;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.om.GenericResource;

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
public class GrantedTo extends GenericResource {

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
