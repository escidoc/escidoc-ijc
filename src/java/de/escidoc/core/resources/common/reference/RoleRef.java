/**
 * 
 */
package de.escidoc.core.resources.common.reference;

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
        super(objid, RESOURCE_TYPE.Role, xLinkTitle);
    }
}
