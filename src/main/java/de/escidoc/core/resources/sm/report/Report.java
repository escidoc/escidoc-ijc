package de.escidoc.core.resources.sm.report;

import static de.escidoc.core.common.Precondition.checkNotNull;
import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.common.reference.ReportDefinitionRef;

/**
 * @author MRO
 * 
 */
@JiBX
public class Report {

    private ReportDefinitionRef reportDefinition;

    private ReportRecord reportRecord;

    /**
     * JiBX Constructor
     */
    @JiBX
    protected Report() {

    }

    /**
     * 
     * @param reportDefinition
     * @param reportRecord
     */
    public Report(final ReportDefinitionRef reportDefinition) {
        checkNotNull(reportDefinition);
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
        if (reportRecord == null) {
            reportRecord = new ReportRecord();
        }
        return reportRecord;
    }

    /**
     * 
     * @param reportRecord
     */
    public void setReportRecord(final ReportRecord reportRecord) {
        this.reportRecord = reportRecord;
    }
}