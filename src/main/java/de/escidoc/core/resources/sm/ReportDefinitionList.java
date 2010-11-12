package de.escidoc.core.resources.sm;

import java.util.LinkedList;

import de.escidoc.core.resources.Resource;

/**
 * @author MRO
 * 
 */
public class ReportDefinitionList extends Resource {

	private LinkedList<ReportDefinition> reportDefinitions;

	/**
	 * Constructor for JiBX only.
	 */
	@SuppressWarnings("unused")
	private ReportDefinitionList() {

	}

	public LinkedList<ReportDefinition> getReportDefinitions() {
		return reportDefinitions;
	}

	public void setReportDefinitions(
			LinkedList<ReportDefinition> reportDefinitions) {
		this.reportDefinitions = reportDefinitions;
	}

}
