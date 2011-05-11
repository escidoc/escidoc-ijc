/**
 * 
 */
package de.escidoc.core.resources.sm.report;

import static de.escidoc.core.common.Precondition.checkNotNull;

import java.util.LinkedList;
import java.util.List;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.common.reference.ReportDefinitionRef;
import de.escidoc.core.resources.sm.Parameter;

/**
 * @author MVO
 * 
 */
@JiBX
public class ReportParameters {

    private ReportDefinitionRef reportDefinition;

    private List<Parameter<?>> parameters;

    /**
     * JiBX Constructor
     */
    @JiBX
    protected ReportParameters() {
    }

    /**
     * 
     * @param reportDefinition
     */
    public ReportParameters(final ReportDefinitionRef reportDefinition) {
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
     * 
     * @return
     */
    public List<Parameter<?>> getParameters() {
        if (parameters == null) {
            parameters = new LinkedList<Parameter<?>>();
        }
        return parameters;
    }
}