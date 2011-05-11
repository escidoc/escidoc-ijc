/**
 * 
 */
package de.escidoc.core.resources.common.reference;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.XLinkType;

/**
 * @author MVO
 * 
 */
@JiBX
public class ItemRef extends Reference {

    /**
     * JiBX Constructor
     */
    @JiBX
    protected ItemRef() {
    }

    /**
     * @param href
     * @param title
     * @param type
     */
    public ItemRef(final String href, final String title, final XLinkType type) {
        super(href, title, type);
    }

    /**
     * @param href
     * @param title
     */
    public ItemRef(final String href, final String title) {
        super(href, title);
    }

    /**
     * @param objid
     */
    public ItemRef(final String objid) {
        super(objid);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.reference.Reference#getResourceType()
     */
    @Override
    public ResourceType getResourceType() {
        return ResourceType.ITEM;
    }
}