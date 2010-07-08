package de.escidoc.core.resources.om.context;

import java.util.Iterator;
import java.util.LinkedList;

import de.escidoc.core.resources.ResourceRef;

/**
 * References of Organizational Units.
 * 
 * @author SWA
 * 
 */
public class OrganizationalUnitRefs extends LinkedList<ResourceRef> {

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
    public ResourceRef get(final String objid) {

        ResourceRef result = null;
        Iterator<ResourceRef> ouRefIter = this.iterator();
        while (ouRefIter.hasNext()) {
            ResourceRef next = ouRefIter.next();
            if (next.getObjid().equals(objid)) {
                result = next;
                break;
            }
        }
        return result;
    }
    
    /**
     * Delete an Organizational Unit reference.
     * 
     * @param objid
     *            The objid of the Organizational Unit.
     */
    public void del(final String objid) {

        Iterator<ResourceRef> ouRefIter = this.iterator();
        while (ouRefIter.hasNext()) {
            ResourceRef next = ouRefIter.next();
            if (next.getObjid().equals(objid)) {
                this.remove(objid);
                break;
            }
        }
    }


}
