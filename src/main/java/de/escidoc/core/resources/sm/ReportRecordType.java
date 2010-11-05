package de.escidoc.core.resources.sm;

import java.util.LinkedList;

import de.escidoc.core.resources.Resource;

/**
 * @author MRO
 * 
 */
public class ReportRecordType extends Resource {

	private LinkedList<Parameter> parameter;

	/**
	 * Constructor for JiBX only.
	 */
	@SuppressWarnings("unused")
	private ReportRecordType() {

	}

	public ReportRecordType(LinkedList<Parameter> parameter) {
		if (parameter == null)
			throw new IllegalArgumentException("parameter must not be null.");

		this.parameter = parameter;
	}

	public LinkedList<Parameter> getParameter() {
		return parameter;
	}

}
