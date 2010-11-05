package de.escidoc.core.resources.sm;

import java.util.LinkedList;

import de.escidoc.core.resources.Resource;
import de.escidoc.core.resources.common.reference.ReportDefinitionRef;

/**
 * @author MRO
 * 
 */
public class Report extends Resource {

	private ReportDefinitionRef reportDefinition;
	private LinkedList<ReportRecordType> reportRecord;

	/**
	 * Constructor for JiBX only.
	 */
	@SuppressWarnings("unused")
	private Report() {

	}

	public Report(ReportDefinitionRef reportDefinition,
			LinkedList<ReportRecordType> reportRecord) {

		if (reportDefinition == null)
			throw new IllegalArgumentException(
					"reportDefinition must not be null.");
		if (reportRecord == null)
			throw new IllegalArgumentException("reportRecord must not be null.");

		this.reportDefinition = reportDefinition;
		this.reportRecord = reportRecord;
	}

	public ReportDefinitionRef getReportDefinition() {
		return reportDefinition;
	}

	public LinkedList<ReportRecordType> getReportRecord() {
		return reportRecord;
	}

}
