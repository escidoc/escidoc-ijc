package de.escidoc.core.resources.sm.report;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.Resource;
import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.common.reference.ReportDefinitionRef;

/**
 * @author MRO
 * 
 */
@JiBX
public class Report extends Resource {

    private ReportDefinitionRef reportDefinition;

    private ReportRecord reportRecord;

    @JiBX
    @SuppressWarnings("unused")
    private Report() {

    }

    /**
     * 
     * @param reportDefinition
     * @param reportRecord
     */
    public Report(final ReportDefinitionRef reportDefinition) {
        if (reportDefinition == null)
            throw new IllegalArgumentException(
                "reportDefinition must not be null.");
        this.reportDefinition = reportDefinition;
    }

    /**
     * 
     * @return
     */
    public ReportDefinitionRef getReportDefinition() {
        return reportDefinition;
    }

    /**
     * 
     * @return
     */
    public ReportRecord getReportRecord() {
        return reportRecord;
    }

    /**
     * 
     * @param reportRecord
     */
    public void setReportRecord(final ReportRecord reportRecord) {
        this.reportRecord = reportRecord;
    }

    @Override
    public ResourceType getResourceType() {
        return null;
    }
}