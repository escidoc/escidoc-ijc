/**
 * 
 */
package de.escidoc.core.resources.common.structmap;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.ResourceType;

/**
 * @author SWA, MVO
 * 
 */
@JiBX
public class ItemMemberRef extends MemberRef {

    @SuppressWarnings("unused")
    @JiBX
    private ItemMemberRef() {
        this(null);
    }

    public ItemMemberRef(final String objid) {
        super(objid);
    }

    public ItemMemberRef(final String xLinkHref, final String xLinkTitle) {
        super(xLinkHref, xLinkTitle);
    }

    @Override
    public ResourceType getResourceType() {
        return ResourceType.Item;
    }
}