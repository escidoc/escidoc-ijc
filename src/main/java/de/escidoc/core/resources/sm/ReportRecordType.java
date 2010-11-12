package de.escidoc.core.resources.sm;

import java.util.LinkedList;

import de.escidoc.core.resources.Resource;

/**
 * @author MRO
 * 
 */
public class ReportRecordType extends Resource {

	private LinkedList<ReportParameter> parameter;

	/**
	 * Constructor for JiBX only.
	 */
	@SuppressWarnings("unused")
	private ReportRecordType() {

	}

	public ReportRecordType(LinkedList<ReportParameter> parameter) {
		if (parameter == null)
			throw new IllegalArgumentException("parameter must not be null.");

		this.parameter = parameter;
	}

	public LinkedList<ReportParameter> getParameter() {
		return parameter;
	}

}
