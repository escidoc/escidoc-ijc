/**
 * 
 */
package de.escidoc.core.resources.common.reference;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.Resource;
import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.XLinkType;

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
    public Reference(final String objid) {
        super(objid);
    }

    /**
     * @param href
     * @param title
     */
    public Reference(final String href, final String title) {
        super(href, title, null);
    }

    /**
     * @param href
     * @param title
     * @param type
     */
    public Reference(final String href, final String title, final XLinkType type) {
        super(href, title, type);
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

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.XLinkAutonomousX#generateXLinkHref()
     */
    @Override
    public void generateXLinkHref(final String parentPath) {
        /*
         * references do not have sub resources and therefore there is nothing
         * to do here.
         */
    }
}