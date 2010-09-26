/**
 * 
 */
package de.escidoc.core.resources.common.structmap;

/**
 * @author SWA, MVO
 * 
 */
public class ItemMemberRef extends MemberRef {

    public ItemMemberRef() {
        this(null, null);
    }

    public ItemMemberRef(String objid) {
        this(objid, null);
    }

    public ItemMemberRef(String objid, String xLinkTitle) {
        super(objid, RESOURCE_TYPE.Item, xLinkTitle);
    }
}
