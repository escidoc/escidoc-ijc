package de.escidoc.core.resources.common.reference;

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
        super(objid, RESOURCE_TYPE.ReportDefinition, xLinkTitle);
    }
}
