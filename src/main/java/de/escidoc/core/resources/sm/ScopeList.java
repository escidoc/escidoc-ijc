package de.escidoc.core.resources.sm;

import java.util.LinkedList;

import de.escidoc.core.resources.Resource;

/**
 * @author MRO
 * 
 */
public class ScopeList extends Resource {

	private LinkedList<Scope> scopes;

	/**
	 * Constructor for JiBX only.
	 */
	@SuppressWarnings("unused")
	public ScopeList() {

	}

	public LinkedList<Scope> getScopes() {
		return scopes;
	}

	public void setScopes(LinkedList<Scope> scopes) {
		this.scopes = scopes;
	}

}
