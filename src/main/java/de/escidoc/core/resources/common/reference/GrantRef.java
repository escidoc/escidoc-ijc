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
public class GrantRef extends Reference {

    @SuppressWarnings("unused")
    @JiBX
    private GrantRef() {
        this(null);
    }

    public GrantRef(final String objid) {
        super(objid);
    }

    public GrantRef(final String xLinkHref, final String xLinkTitle) {
        super(xLinkHref, xLinkTitle);
    }

    @Override
    public ResourceType getResourceType() {
        return ResourceType.GRANT;
    }
}