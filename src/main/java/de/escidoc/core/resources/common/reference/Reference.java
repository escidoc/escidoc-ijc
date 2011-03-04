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
 * This class should be abstract but since there exists a content-relation
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
     * @param objid
     * @param href
     * @param title
     */
    public Reference(final String objid, final String href, final String title) {
        super(objid, href, title);
    }

    /**
     * @param href
     * @param title
     */
    public Reference(final String href, final String title) {
        super(href, title);
    }

    /**
     * @param objid
     */
    public Reference(final String objid) {
        super(objid);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.Resource#getResourceType()
     */
    @Override
    public ResourceType getResourceType() {
        return null;
    }
}