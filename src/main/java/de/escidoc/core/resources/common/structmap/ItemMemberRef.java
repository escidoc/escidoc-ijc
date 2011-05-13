/**
 * 
 */
package de.escidoc.core.resources.common.structmap;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.XLinkType;

/**
 * @author SWA, MVO
 * 
 */
@JiBX
public class ItemMemberRef extends MemberRef {

    /**
     * JiBX Constructor
     */
    @JiBX
    protected ItemMemberRef() {
    }

    /**
     * @param href
     * @param title
     * @param type
     */
    public ItemMemberRef(final String href, final String title, final XLinkType type) {
        super(href, title, type);
    }

    /**
     * @param href
     * @param title
     */
    public ItemMemberRef(final String href, final String title) {
        super(href, title);
    }

    /**
     * @param objid
     */
    public ItemMemberRef(final String objid) {
        super(objid);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.structmap.MemberRef#getResourceType()
     */
    @Override
    public ResourceType getResourceType() {
        return ResourceType.ITEM;
    }
}