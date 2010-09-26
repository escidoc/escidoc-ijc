/**
 * 
 */
package de.escidoc.core.resources.common.reference;

/**
 * @author MVO
 * 
 */
public class ItemRef extends Reference {

    public ItemRef() {
        this(null, null);
    }

    public ItemRef(String objid) {
        this(objid, null);
    }

    public ItemRef(String objid, String xLinkTitle) {
        super(objid, RESOURCE_TYPE.Item, xLinkTitle);
    }
}
