package de.escidoc.core.resources.sm;

import de.escidoc.core.resources.Resource;
import de.escidoc.core.resources.common.reference.Referenceable;
import de.escidoc.core.resources.common.reference.ScopeRef;

/**
 * @author MVO
 * 
 */
public class Scope extends Resource implements Referenceable<ScopeRef> {

	public enum ScopeType {
		normal, admin
	}

	private String name;
	private ScopeType type;

	/**
	 * Constructor for JiBX only.
	 */
	@SuppressWarnings("unused")
	private Scope() {

	}

	/**
	 * 
	 * @param name
	 * @param type
	 */
	public Scope(final String name, final ScopeType type) {
		if (name == null)
			throw new IllegalArgumentException("name must not be null.");
		if (type == null)
			throw new IllegalArgumentException("type must not be null.");

		this.name = name;
		this.type = type;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @return the scopeType
	 */
	public final ScopeType getType() {
		return type;
	}

	public ScopeRef getReference() {
		return new ScopeRef(this.getObjid());
	}

}
