package de.escidoc.core.resources.common.reference;

import de.escidoc.core.resources.ResourceType;

/**
 * @author MRO
 * 
 */
public class ReportDefinitionRef extends Reference {

    public ReportDefinitionRef() {
        this(null, null);
    }

    public ReportDefinitionRef(String objid) {
        this(objid, null);
    }

    public ReportDefinitionRef(String objid, String xLinkTitle) {
        super(objid, ResourceType.ReportDefinition, xLinkTitle);
    }
}
