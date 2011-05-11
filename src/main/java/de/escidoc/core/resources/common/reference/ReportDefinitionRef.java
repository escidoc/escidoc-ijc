package de.escidoc.core.resources.common.reference;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.XLinkType;

/**
 * @author MRO
 * 
 */
@JiBX
public class ReportDefinitionRef extends Reference {

    /**
     * JiBX Constructor
     */
    @JiBX
    protected ReportDefinitionRef() {
    }

    /**
     * @param href
     * @param title
     * @param type
     */
    public ReportDefinitionRef(final String href, final String title,
        final XLinkType type) {
        super(href, title, type);
    }

    /**
     * @param href
     * @param title
     */
    public ReportDefinitionRef(final String href, final String title) {
        super(href, title);
    }

    /**
     * @param objid
     */
    public ReportDefinitionRef(final String objid) {
        super(objid);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.reference.Reference#getResourceType()
     */
    @Override
    public ResourceType getResourceType() {
        return ResourceType.REPORT_DEFINITION;
    }
}