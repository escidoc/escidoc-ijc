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
public class ContextRef extends Reference {

    @SuppressWarnings("unused")
    @JiBX
    private ContextRef() {
        this(null);
    }

    public ContextRef(final String objid) {
        super(objid);
    }

    public ContextRef(final String xLinkHref, final String xLinkTitle) {
        super(xLinkHref, xLinkTitle);
    }

    @Override
    public ResourceType getResourceType() {
        return ResourceType.CONTEXT;
    }
}