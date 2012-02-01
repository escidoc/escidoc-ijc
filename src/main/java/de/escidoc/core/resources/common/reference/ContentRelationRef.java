/**
 * 
 */
package de.escidoc.core.resources.common.reference;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.ResourceType;

/**
 * @author Marko Voß
 * 
 */
public class ContentRelationRef extends Reference {

    /**
     * JiBX Constructor
     */
    @JiBX
    protected ContentRelationRef() {
    }

    /**
     * @param objid
     */
    public ContentRelationRef(final String objid) {
        super(objid);
    }

    /**
     * @param xLinkHref
     * @param xLinkTitle
     */
    public ContentRelationRef(final String xLinkHref, final String xLinkTitle) {
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
        return ResourceType.CONTENT_RELATION;
    }
}