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
        this(null);
    }

    public ContainerMemberRef(final String objid) {
        super(objid);
    }

    public ContainerMemberRef(final String xLinkHref, final String xLinkTitle) {
        super(xLinkHref, xLinkTitle);
    }

    @Override
    public ResourceType getResourceType() {
        return ResourceType.Container;
    }
}