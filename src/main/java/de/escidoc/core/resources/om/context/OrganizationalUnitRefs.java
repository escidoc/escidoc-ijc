package de.escidoc.core.resources.om.context;

import java.util.LinkedList;

import de.escidoc.core.resources.common.reference.OrganizationalUnitRef;

/**
 * References of Organizational Units.
 * 
 * @author SWA
 * 
 */
public class OrganizationalUnitRefs extends LinkedList<OrganizationalUnitRef> {

    /**
     * 
     */
    private static final long serialVersionUID = 6831971140769577016L;

    /**
     * Get an Organizational Unit reference.
     * 
     * @param objid
     *            The objid of the Organizational Unit.
     * @return Organizational Unit reference
     */
    public OrganizationalUnitRef get(final String objid) {
        if (objid != null) {
            for (OrganizationalUnitRef element : this) {
                if (objid.equals(element.getObjid())) {
                    return element;
                }
            }
        }
        return null;
    }

    /**
     * Delete an Organizational Unit reference.
     * 
     * @param objid
     *            The objid of the Organizational Unit.
     */
    public void del(final String objid) {
        if (objid != null) {
            for (OrganizationalUnitRef element : this) {
                if (objid.equals(element.getObjid())) {
                    remove(element);
                    break;
                }
            }
        }
    }
}