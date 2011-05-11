/**
 * 
 */
package de.escidoc.core.resources.common.structmap;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.XLinkType;
import de.escidoc.core.resources.common.reference.Reference;

/**
 * @author SWA, MVO
 * 
 */
public abstract class MemberRef extends Reference {

    /**
     * @param href
     * @param title
     * @param type
     */
    public MemberRef(final String href, final String title, final XLinkType type) {
        super(href, title, type);
    }

    /**
     * @param href
     * @param title
     */
    public MemberRef(final String href, final String title) {
        super(href, title);
    }

    /**
     * @param objid
     */
    public MemberRef(final String objid) {
        super(objid);
    }

    /**
     * JiBX Constructor
     */
    @JiBX
    protected MemberRef() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.reference.Reference#getResourceType()
     */
    @Override
    public abstract ResourceType getResourceType();
}