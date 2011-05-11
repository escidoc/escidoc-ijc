package de.escidoc.core.resources.sm.report;

import de.escidoc.core.resources.ResourceList;

/**
 * @author MRO
 * 
 */
public class ReportDefinitionList extends ResourceList<ReportDefinition> {

    /**
     * 
     */
    private static final long serialVersionUID = 2063307230294396373L;

    private static final String REPORT_DEFINITIONS_PATH = "/report-definitions";

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.XLinkResourceList#getListXLinkPath()
     */
    @Override
    protected String getListXLinkPath() {
        return REPORT_DEFINITIONS_PATH;
    }
}