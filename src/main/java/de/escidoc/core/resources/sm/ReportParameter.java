package de.escidoc.core.resources.sm;

/**
 * @author MRO
 * 
 */
public class ReportParameter {

	private Parameter parameter;
	private String name;

	public ReportParameter(Parameter parameter, String name) {
		if (name == null)
			throw new IllegalArgumentException("name must not be null.");
		if (parameter == null)
			throw new IllegalArgumentException("parameter must not be null.");

		this.parameter = parameter;
		this.name = name;
	}

	public Parameter getParameter() {
		return parameter;
	}

	public String getName() {
		return name;
	}

}
