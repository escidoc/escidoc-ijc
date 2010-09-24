package de.escidoc.core.resources.om.context;

import java.util.Iterator;
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

        Iterator<OrganizationalUnitRef> ouRefIter = this.iterator();
        while (ouRefIter.hasNext()) {
            OrganizationalUnitRef next = ouRefIter.next();
            if (next.getObjid().equals(objid)) {
                return next;
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

        Iterator<OrganizationalUnitRef> ouRefIter = this.iterator();
        while (ouRefIter.hasNext()) {
            OrganizationalUnitRef next = ouRefIter.next();
            if (next.getObjid().equals(objid)) {
                this.remove(objid);
                break;
            }
        }
    }

}
