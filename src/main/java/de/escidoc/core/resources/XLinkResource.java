/**
 * 
 */
package de.escidoc.core.resources;

import de.escidoc.core.annotations.JiBX;

/**
 * @author MVO
 * 
 */
@JiBX
public abstract class XLinkResource extends XLinkAutonomous {

    @JiBX
    protected String xLinkHref;

    @JiBX
    protected String xLinkTitle;

    @JiBX
    protected XLinkType xLinkType = XLinkType.simple;

    /**
     * Get the href of the resource. (May depend on transport protocol.)
     * 
     * @return href of resource.
     */
    public String getXLinkHref() {
        return this.xLinkHref;
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
     * Get XLink type. (Default {@link XLinkType.simple})
     * 
     * @return xlink:type if and only if this.href has been set. (REST protocol
     *         uses xlink:href. SOAP needs this.objid only.)
     */
    public XLinkType getXLinkType() {
        return this.xLinkType;
    }

    /**
     * This method is used by JiBX to test, if the title should be marshalled or
     * not.
     * 
     * @return <code>true</code> if and only if the title is not null and the
     *         href is not null, otherwise <code>false</code>.
     */
    protected boolean hasXLinkTitleForHref() {
        return getXLinkHref() != null && getXLinkTitle() != null;
    }

    /**
     * This method is used by JiBX to test, if the type should be marshalled or
     * not.
     * 
     * @return <code>true</code> if and only if the type is not null and the
     *         href is not null, otherwise <code>false</code>.
     */
    protected boolean hasXLinkTypeForHref() {
        return getXLinkHref() != null && getXLinkType() != null;
    }

    /**
     * Sets the xLinkHref. This method may be used only by implementations of
     * this class in order to generate the xLinkHref for their sub-resources.
     * 
     * @param xLinkHref
     *            The xLinkHref to set.
     */
    protected void setXLinkHref(final String xLinkHref) {
        this.xLinkHref = xLinkHref;
    }

    /**
     * @param type
     */
    protected void setXLinkType(final XLinkType type) {
        this.xLinkType = type;
    }

    /**
     * @param title
     */
    protected void setXLinkTitle(final String title) {
        this.xLinkTitle = title;
    }
}