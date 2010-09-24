/**
 * 
 */
package de.escidoc.core.resources.common.structmap;

import de.escidoc.core.resources.common.reference.Reference;

/**
 * @author SWA, MVO
 *
 */
public abstract class MemberRef extends Reference {

    public MemberRef(String objid, RESOURCE_TYPE type) {
        super(objid, type);
    }

    public MemberRef(String objid, RESOURCE_TYPE type, String xLinkTitle) {
        super(objid, type, xLinkTitle);
    }
}
