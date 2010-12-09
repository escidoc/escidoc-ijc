/**
 * 
 */
package de.escidoc.core.resources.common.reference;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.Resource;
import de.escidoc.core.resources.ResourceType;

/**
 * This class represents a reference to a resource.
 * 
 * This class should be abstract but since there exist a content-relation
 * structure as XML (SOAP-only) using objids as subjects/objects without a type
 * definition, we have to enable a possibility to instantiate type-less
 * references.
 * 
 * @author MVO
 * 
 */
@JiBX
public class Reference extends Resource {

    @JiBX
    protected Reference() {
    }

    /**
     * 
     * @param objid
     * @param type
     */
    public Reference(final String objid) {
        super(objid);
    }

    /**
     * 
     * @param objid
     * @param type
     * @param xLinkTitle
     */
    public Reference(final String xLinkHref, final String xLinkTitle) {
        super(xLinkHref, xLinkTitle);
    }

    @Override
    public ResourceType getResourceType() {
        return null;
    }
}