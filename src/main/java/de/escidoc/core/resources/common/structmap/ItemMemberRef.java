/**
 * 
 */
package de.escidoc.core.resources.common.structmap;

import de.escidoc.core.resources.ResourceType;

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
        super(objid, ResourceType.Item, xLinkTitle);
    }
}
