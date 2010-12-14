/**
 * 
 */
package de.escidoc.core.resources.sm.report;

import java.util.Collection;
import java.util.LinkedList;

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

    private Collection<Parameter<?>> parameters;

    @JiBX
    @SuppressWarnings("unused")
    private ReportParameters() {

    }

    /**
     * 
     * @param reportDefinition
     */
    public ReportParameters(final ReportDefinitionRef reportDefinition) {
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
     * 
     * @return
     */
    public Collection<Parameter<?>> getParameters() {
        if (parameters == null) {
            parameters = new LinkedList<Parameter<?>>();
        }
        return parameters;
    }
}
