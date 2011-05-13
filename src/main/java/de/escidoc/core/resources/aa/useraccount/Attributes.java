package de.escidoc.core.resources.aa.useraccount;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.XLinkResource;

/**
 * User Account Attributes.
 * 
 * @author SWA
 * 
 */
@JiBX
public class Attributes extends UserAccountElements<Attribute> {

    private static final long serialVersionUID = -3420698747347841414L;

    private static final String ATTRIBUTES_PATH = "/resources/attributes";

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.XLinkResourceList#getListXLinkPath()
     */
    @Override
    protected String getListXLinkPath() {
        return ATTRIBUTES_PATH;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.aa.useraccount.UserAccountElements#
     * generateXLinkHref()
     */
    @Override
    public void generateXLinkHref() {
        super.generateXLinkHref();

        if (getXLinkHref() != null) {
            for (XLinkResource resource : this) {
                resource.generateXLinkHref(getXLinkHref());
            }
        }
    }
}