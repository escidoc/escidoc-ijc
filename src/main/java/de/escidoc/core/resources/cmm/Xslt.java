/**
 * 
 */
package de.escidoc.core.resources.cmm;

import static de.escidoc.core.common.Precondition.checkNotNull;
import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.XLinkResource;
import de.escidoc.core.resources.XLinkType;

/**
 * @author Marko Voß
 * 
 */
public class Xslt extends XLinkResource {

    /**
     * JiBX Constructor
     */
    @JiBX
    protected Xslt() {

    }

    /**
     * @param xLinkHref
     */
    public Xslt(final String xLinkHref) {
        this(xLinkHref, null, null);
    }

    /**
     * @param xLinkHref
     * @param xLinkTitle
     * @param xLinkType
     */
    public Xslt(final String xLinkHref, final String xLinkTitle, final XLinkType xLinkType) {

        checkNotNull(xLinkHref);

        setXLinkHref(xLinkHref);
        setXLinkTitle(xLinkTitle);
        setXLinkType(xLinkType);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.XLinkAutonomous#generateXLinkHref(java.lang
     * .String)
     */
    @Override
    public void generateXLinkHref(final String parentPath) {
        // do nothing
    }
}