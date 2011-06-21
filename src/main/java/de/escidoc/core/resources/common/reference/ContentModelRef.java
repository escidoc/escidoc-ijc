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
public class ContentModelRef extends Reference {

    /**
     * JiBX Constructor
     */
    @JiBX
    protected ContentModelRef() {
    }

    /**
     * @param objid
     */
    public ContentModelRef(final String objid) {
        super(objid);
    }

    /**
     * @param xLinkHref
     * @param xLinkTitle
     */
    public ContentModelRef(final String xLinkHref, final String xLinkTitle) {
        super(xLinkHref, xLinkTitle);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.reference.Reference#getResourceType()
     */
    @Override
    public ResourceType getResourceType() {
        return ResourceType.CONTENT_MODEL;
    }
}