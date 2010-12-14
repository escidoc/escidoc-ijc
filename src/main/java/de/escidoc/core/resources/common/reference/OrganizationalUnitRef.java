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
public class OrganizationalUnitRef extends Reference {

    @JiBX
    @SuppressWarnings("unused")
    private OrganizationalUnitRef() {
        this(null);
    }

    public OrganizationalUnitRef(final String objid) {
        super(objid);
    }

    public OrganizationalUnitRef(final String xLinkHref, final String xLinkTitle) {
        super(xLinkHref, xLinkTitle);
    }

    @Override
    public ResourceType getResourceType() {
        return ResourceType.OrganizationalUnit;
    }
}