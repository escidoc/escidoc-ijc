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

    @JiBX
    @SuppressWarnings("unused")
    private ContentModelRef() {
        this(null);
    }

    public ContentModelRef(final String objid) {
        super(objid);
    }

    public ContentModelRef(final String xLinkHref, final String xLinkTitle) {
        super(xLinkHref, xLinkTitle);
    }

    @Override
    public ResourceType getResourceType() {
        return ResourceType.CONTENT_MODEL;
    }
}