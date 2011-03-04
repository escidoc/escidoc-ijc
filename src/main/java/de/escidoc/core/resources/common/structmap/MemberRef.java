/**
 * 
 */
package de.escidoc.core.resources.common.structmap;

import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.common.reference.Reference;

/**
 * @author SWA, MVO
 * 
 */
public abstract class MemberRef extends Reference {

    /**
     * @param objid
     * @param href
     * @param title
     */
    public MemberRef(final String objid, final String href, final String title) {
        super(objid, href, title);
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
     * 
     */
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