package de.escidoc.core.resources.sm;

import java.util.LinkedList;

import de.escidoc.core.resources.Resource;
import de.escidoc.core.resources.common.reference.Referenceable;
import de.escidoc.core.resources.common.reference.ReportDefinitionRef;
import de.escidoc.core.resources.common.reference.RoleRef;
import de.escidoc.core.resources.common.reference.ScopeRef;

/**
 * @author MRO
 * 
 */
public class ReportDefinition extends Resource implements
		Referenceable<ReportDefinitionRef> {

	private String name;
	private ScopeRef scope;
	private String sql;
	private LinkedList<RoleRef> allowedRoles;

	/**
	 * Constructor for JiBX only.
	 */
	@SuppressWarnings("unused")
	private ReportDefinition() {

	}

	public ReportDefinition(String name, ScopeRef scope, String sql,
			LinkedList<RoleRef> allowedRoles) {
		if (allowedRoles == null)
			throw new IllegalArgumentException("allowedRoles must not be null.");
		if (sql == null)
			throw new IllegalArgumentException("sql must not be null.");
		if (scope == null)
			throw new IllegalArgumentException("scope must not be null.");
		if (name == null)
			throw new IllegalArgumentException("name must not be null.");

		this.name = name;
		this.scope = scope;
		this.sql = sql;
		this.allowedRoles = allowedRoles;
	}

	public String getName() {
		return name;
	}

	public ScopeRef getScope() {
		return scope;
	}

	public String getSql() {
		return sql;
	}

	public LinkedList<RoleRef> getAllowedRoles() {
		return allowedRoles;
	}

	public ReportDefinitionRef getReference() {
		return new ReportDefinitionRef(this.getObjid());
	}

}
