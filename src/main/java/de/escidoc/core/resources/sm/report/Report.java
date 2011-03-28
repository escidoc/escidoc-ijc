package de.escidoc.core.resources.sm.report;

import java.util.LinkedList;
import java.util.List;

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

    private List<ReportRecord> reportRecords;

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
    public List<ReportRecord> getReportRecords() {
        if (reportRecords == null)
            reportRecords = new LinkedList<ReportRecord>();
        return reportRecords;
    }

    /**
     * 
     * @param reportRecord
     */
    public void setReportRecords(final List<ReportRecord> reportRecords) {
        this.reportRecords = reportRecords;
    }

    @Override
    public ResourceType getResourceType() {
        return null;
    }
}