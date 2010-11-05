package de.escidoc.core.resources.sm;

import java.util.LinkedList;

import de.escidoc.core.resources.Resource;
import de.escidoc.core.resources.common.reference.ReportDefinitionRef;

/**
 * @author MRO
 * 
 */
public class ReportParameters extends Resource {

	private ReportDefinitionRef reportDefinition;
	private LinkedList<Parameter> parameter;

	/**
	 * Constructor for JiBX only.
	 */
	@SuppressWarnings("unused")
	private ReportParameters() {

	}

	public ReportParameters(ReportDefinitionRef reportDefinition,
			LinkedList<Parameter> parameter) {
		if (parameter == null)
			throw new IllegalArgumentException("parameter must not be null.");
		if (reportDefinition == null)
			throw new IllegalArgumentException(
					"reportDefinition must not be null.");

		this.reportDefinition = reportDefinition;
		this.parameter = parameter;
	}

	public ReportDefinitionRef getReportDefinition() {
		return reportDefinition;
	}

	public LinkedList<Parameter> getParameter() {
		return parameter;
	}

}
