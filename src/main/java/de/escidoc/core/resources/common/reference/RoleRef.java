/**
 * 
 */
package de.escidoc.core.resources.common.reference;

import de.escidoc.core.resources.ResourceType;

/**
 * @author MVO
 * 
 */
public class RoleRef extends Reference {

    public RoleRef() {
        this(null, null);
    }

    public RoleRef(String objid) {
        this(objid, null);
    }

    public RoleRef(String objid, String xLinkTitle) {
        super(objid, ResourceType.Role, xLinkTitle);
    }
}
