/**
 * 
 */
package de.escidoc.core.resources.common.reference;

/**
 * @author MVO
 * 
 */
public class OrganizationalUnitRef extends Reference {

    public OrganizationalUnitRef() {
        this(null, null);
    }

    public OrganizationalUnitRef(String objid) {
        this(objid, null);
    }

    public OrganizationalUnitRef(String objid, String xLinkTitle) {
        super(objid, RESOURCE_TYPE.OrganizationalUnit, xLinkTitle);
    }
}
