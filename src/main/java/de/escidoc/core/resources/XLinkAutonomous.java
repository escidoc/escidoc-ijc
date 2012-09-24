/**
 * 
 */
package de.escidoc.core.resources;

import de.escidoc.core.client.TransportProtocol;

/**
 * @author Marko Voß
 * 
 */
public abstract class XLinkAutonomous {

    /**
     * A resource returned from the eSciDoc Infrastructure can be returned
     * either in {@link TransportProtocol.SOAP} or
     * {@link TransportProtocol.REST}. In both cases either the objid or the
     * xlink values have to be generated. JiBX will call this method as a
     * post-set-method. A resource implementing this method must ensure to
     * generate the identifiers for all sub-resources as well.
     */
    public abstract void generateXLinkHref(String parentPath);

    /**
     * 
     */
    public void generateXLinkHref() {
        generateXLinkHref(null);
    }
}