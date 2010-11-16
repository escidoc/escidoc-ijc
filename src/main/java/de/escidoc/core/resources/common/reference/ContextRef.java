/**
 * 
 */
package de.escidoc.core.resources.common.reference;

import de.escidoc.core.resources.ResourceType;


/**
 * @author MVO
 * 
 */
public class ContextRef extends Reference {

    public ContextRef() {
        this(null, null);
    }

    public ContextRef(String objid) {
        this(objid, null);
    }

    public ContextRef(String objid, String xLinkTitle) {
        super(objid, ResourceType.Context, xLinkTitle);
    }
}
