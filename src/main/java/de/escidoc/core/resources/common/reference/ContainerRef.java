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
public class ContainerRef extends Reference {

    @JiBX
    @SuppressWarnings("unused")
    private ContainerRef() {
        this(null);
    }

    public ContainerRef(final String objid) {
        super(objid);
    }

    public ContainerRef(final String xLinkHref, final String xLinkTitle) {
        super(xLinkHref, xLinkTitle);
    }

    @Override
    public ResourceType getResourceType() {
        return ResourceType.Container;
    }
}