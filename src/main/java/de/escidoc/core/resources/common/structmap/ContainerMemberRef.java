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
public class ContainerMemberRef extends MemberRef {

    @SuppressWarnings("unused")
    @JiBX
    private ContainerMemberRef() {
    }

    /**
     * @param objid
     * @param href
     * @param title
     */
    public ContainerMemberRef(final String objid, final String href,
        final String title) {
        super(objid, href, title);
    }

    /**
     * @param href
     * @param title
     */
    public ContainerMemberRef(final String href, final String title) {
        super(href, title);
    }

    /**
     * @param objid
     */
    public ContainerMemberRef(final String objid) {
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
        return ResourceType.Container;
    }
}