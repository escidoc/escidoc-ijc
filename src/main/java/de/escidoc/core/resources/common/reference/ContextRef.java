/**
 * 
 */
package de.escidoc.core.resources.common.reference;


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
        super(objid, RESOURCE_TYPE.Context, xLinkTitle);
    }
}
