package de.escidoc.core.resources.sm;

import java.util.LinkedList;

import de.escidoc.core.resources.Resource;
import de.escidoc.core.resources.common.reference.ScopeRef;

/**
 * @author MRO
 * 
 */
public class StatisticData extends Resource {

	private ScopeRef scope;
	private LinkedList<ReportParameter> parameter;

	/**
	 * Constructor for JiBX only.
	 */
	@SuppressWarnings("unused")
	private StatisticData() {

	}

	public StatisticData(ScopeRef scope, LinkedList<ReportParameter> parameter) {
		if (scope == null)
			throw new IllegalArgumentException("scope must not be null.");
		if (parameter == null)
			throw new IllegalArgumentException("parameter must not be null.");

		this.scope = scope;
		this.parameter = parameter;
	}

	public ScopeRef getScope() {
		return scope;
	}

	public LinkedList<ReportParameter> getParameter() {
		return parameter;
	}

}
