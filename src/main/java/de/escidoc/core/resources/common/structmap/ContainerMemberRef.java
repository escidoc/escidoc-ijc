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
public class ContainerMemberRef extends MemberRef {

    /**
     * JiBX Constructor
     */
    @JiBX
    protected ContainerMemberRef() {
    }

    /**
     * @param href
     * @param title
     * @param type
     */
    public ContainerMemberRef(final String href, final String title,
        final XLinkType type) {
        super(href, title, type);
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
        return ResourceType.CONTAINER;
    }
}