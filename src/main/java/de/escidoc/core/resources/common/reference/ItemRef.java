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
public class ItemRef extends Reference {

    @SuppressWarnings("unused")
    @JiBX
    private ItemRef() {
        this(null);
    }

    public ItemRef(final String objid) {
        super(objid);
    }

    public ItemRef(final String xLinkHref, final String xLinkTitle) {
        super(xLinkHref, xLinkTitle);
    }

    @Override
    public ResourceType getResourceType() {
        return ResourceType.Item;
    }
}