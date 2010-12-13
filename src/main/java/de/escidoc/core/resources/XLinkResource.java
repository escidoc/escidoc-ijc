/**
 * 
 */
package de.escidoc.core.resources;

/**
 * @author MVO
 * 
 */
public abstract class XLinkResource {

    /**
     * XLink Types.
     * 
     */
    public static enum XLINK_TYPE {
        simple, extended, locator, arc, resource, title
    }

    private String xLinkHref;

    private String xLinkTitle;

    private XLINK_TYPE xLinkType;

    /**
     * Get the href of the resource. (May depend on transport protocol.)
     * 
     * @return href of resource.
     */
    public String getXLinkHref() {
        return this.xLinkHref;
    }

    /**
     * Set the href of the resource. (May depend on transport protocol.)
     * 
     * @param href
     *            The href of the resource
     */
    public void setXLinkHref(final String href) {
        if (href != null && href.length() > 0) {
            this.xLinkHref = href;
            this.xLinkType = XLINK_TYPE.simple;
        }
    }

    /**
     * Get the title of the resource. (May depend on transport protocol.)
     * 
     * @return Resource title.
     */
    public String getXLinkTitle() {
        return this.xLinkTitle;
    }

    /**
     * Set the resource title. (May depend on transport protocol or is
     * discarded.)
     * 
     * @param title
     *            The title of the resource.
     */
    public void setXLinkTitle(final String title) {
        this.xLinkTitle = title;
    }

    /**
     * Get XLink type. (Default XLINK_TYPE.simple)
     * 
     * @return xlink:type if and only if this.href has been set. (REST protocol
     *         uses xlink:href. SOAP needs this.objid only.)
     */
    public XLINK_TYPE getXLinkType() {
        return this.xLinkType;
    }

    /**
     * Set Xlink:type.
     * 
     * @param type
     *            Type of xlink.
     */
    public void setXLinkType(final XLINK_TYPE type) {
        this.xLinkType = type;
    }
}
