/**
 * 
 */
package de.escidoc.core.resources;

import de.escidoc.core.client.TransportProtocol;

/**
 * This class represents a list, where the list itself as well as the elements
 * in this list can have xlink-attributes in their XML representation.
 * 
 * @author MVO
 * 
 */
public abstract class XLinkResourceList<E extends XLinkResource> extends XLinkList<E> {

    private static final long serialVersionUID = 8240386975522360182L;

    /**
     * A resource returned from the eSciDoc Infrastructure can be returned
     * either in {@link TransportProtocol.SOAP} or
     * {@link TransportProtocol.REST}. In both cases either the objid or the
     * xlink values have to be generated. JiBX will call this method as a
     * post-set-method. A resource implementing this method must ensure to
     * generate the identifiers for all sub-resources as well.
     */
    @Override
    public void generateXLinkHref(final String parentPath) {
        super.generateXLinkHref(parentPath);

        if (getXLinkHref() != null) {
            for (XLinkResource resource : this) {
                resource.generateXLinkHref(getXLinkHref());
            }
        }
    }
}