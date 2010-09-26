/**
 * 
 */
package de.escidoc.core.resources.common.reference;

/**
 * @author MVO
 * 
 */
public class GrantRef extends Reference {

    public GrantRef() {
        this(null);
    }

    public GrantRef(String objid) {
        super(objid, RESOURCE_TYPE.Grant);
    }

    public GrantRef(String objid, String xLinkTitle) {
        super(objid, RESOURCE_TYPE.Grant);
    }
}