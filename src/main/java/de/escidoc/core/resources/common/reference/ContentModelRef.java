/**
 * 
 */
package de.escidoc.core.resources.common.reference;

import de.escidoc.core.resources.ResourceType;

/**
 * @author MVO
 * 
 */
public class ContentModelRef extends Reference {

    public ContentModelRef() {
        this(null, null);
    }

    public ContentModelRef(String objid) {
        this(objid, null);
    }

    public ContentModelRef(String objid, String xLinkTitle) {
        super(objid, ResourceType.ContentModel, xLinkTitle);
    }
}
