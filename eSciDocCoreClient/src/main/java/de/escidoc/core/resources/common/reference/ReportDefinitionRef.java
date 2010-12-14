package de.escidoc.core.resources.common.reference;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.ResourceType;

/**
 * @author MRO
 * 
 */
@JiBX
public class ReportDefinitionRef extends Reference {

    @SuppressWarnings("unused")
    @JiBX
    private ReportDefinitionRef() {
        this(null);
    }

    public ReportDefinitionRef(final String objid) {
        super(objid);
    }

    public ReportDefinitionRef(final String xLinkHref, final String xLinkTitle) {
        super(xLinkHref, xLinkTitle);
    }

    @Override
    public ResourceType getResourceType() {
        return ResourceType.ReportDefinition;
    }
}