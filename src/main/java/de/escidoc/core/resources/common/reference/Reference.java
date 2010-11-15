/**
 * 
 */
package de.escidoc.core.resources.common.reference;

import de.escidoc.core.resources.Resource;

/**
 * This class represents a reference to a resource.
 * 
 * @author MVO
 * 
 */
public class Reference extends Resource {

    /**
     * Default constructor for JiBX.
     */
    Reference() {
    }

    /**
     * 
     * @param objid
     * @param type
     */
    public Reference(final String objid, final RESOURCE_TYPE type) {
        super(objid, type);
    }

    /**
     * 
     * @param objid
     * @param type
     * @param xLinkTitle
     */
    public Reference(final String objid, final RESOURCE_TYPE type,
        final String xLinkTitle) {
        super(objid, type, xLinkTitle);
    }

}
