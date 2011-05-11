/**
 * 
 */
package de.escidoc.core.resources;

import java.util.LinkedList;

import org.joda.time.DateTime;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.common.DateTimeUtility;

/**
 * This class represents a list, which can has xlink-attributes in its XML
 * representation.
 * 
 * @author Marko Voß
 * 
 */
public abstract class XLinkList<E> extends LinkedList<E> {

    private static final long serialVersionUID = 1021483050958136810L;

    private String xLinkHref;

    private String xLinkTitle;

    private XLinkType xLinkType;

    /**
     * The time stamp for optimistic locking.
     */
    private DateTime lastModificationDate;

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
            this.xLinkType = XLinkType.simple;
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
    public XLinkType getXLinkType() {
        return this.xLinkType;
    }

    /**
     * Set Xlink:type.
     * 
     * @param type
     *            Type of xlink.
     */
    public void setXLinkType(final XLinkType type) {
        this.xLinkType = type;
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
     * @param lastModificationDate
     *            the lastModificationDate to set
     */
    public void setLastModificationDate(final DateTime lastModificationDate) {
        this.lastModificationDate =
            DateTimeUtility.normalize(lastModificationDate);
    }

    /**
     * @return the lastModificationDate
     */
    public DateTime getLastModificationDate() {
        return lastModificationDate;
    }

    /**
     * A resource returned from the eSciDoc Infrastructure can be returned
     * either in {@link TransportProtocol.SOAP} or
     * {@link TransportProtocol.REST}. In both cases either the objid or the
     * xlink values have to be generated. JiBX will call this method as a
     * post-set-method. A resource implementing this method must ensure to
     * generate the identifiers for all sub-resources as well.
     */
    public void generateXLinkHref(final String parentPath) {
        if (parentPath != null && getListXLinkPath() != null) {
            if (getXLinkHref() == null) {
                setXLinkHref(parentPath + getListXLinkPath());
            }
        }
    }

    /**
     * This method must return the list path.<br/>
     * <br/>
     * For example a MetadataRecords of an Item:<br/>
     * <br/>
     * /ir/item/{itemid}/md-records/md-record/{md-record-name}<br/>
     * <br/>
     * where <code>/md-records</code> will be the result of this method.
     * 
     * @return
     */
    protected abstract String getListXLinkPath();
}