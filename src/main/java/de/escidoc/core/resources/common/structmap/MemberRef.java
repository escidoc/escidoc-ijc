/**
 * 
 */
package de.escidoc.core.resources.common.structmap;

import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.common.reference.Reference;

/**
 * @author SWA, MVO
 *
 */
public abstract class MemberRef extends Reference {

    public MemberRef(String objid, ResourceType type) {
        super(objid, type);
    }

    public MemberRef(String objid, ResourceType type, String xLinkTitle) {
        super(objid, type, xLinkTitle);
    }
}
