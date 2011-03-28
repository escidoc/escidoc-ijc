/**
 * 
 */
package de.escidoc.core.resources.common.reference;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.ResourceType;

/**
 * @author MVO
 * 
 */
@JiBX
public class RoleRef extends Reference {

    @SuppressWarnings("unused")
    @JiBX
    private RoleRef() {
        this(null);
    }

    public RoleRef(final String objid) {
        super(objid);
    }

    public RoleRef(final String xLinkHref, final String xLinkTitle) {
        super(xLinkHref, xLinkTitle);
    }

    @Override
    public ResourceType getResourceType() {
        return ResourceType.ROLE;
    }
}