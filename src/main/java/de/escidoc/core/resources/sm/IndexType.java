package de.escidoc.core.resources.sm;

import java.util.LinkedList;

import de.escidoc.core.resources.Resource;

/**
 * @author MRO
 * 
 */
public class IndexType extends Resource {

	private String name;
	private LinkedList<String> field;

	/**
	 * Constructor for JiBX only.
	 */
	@SuppressWarnings("unused")
	private IndexType() {

	}

	public IndexType(String name, LinkedList<String> field) {
		if (field == null)
			throw new IllegalArgumentException("field must not be null.");
		if (name == null)
			throw new IllegalArgumentException("name must not be null.");

		this.name = name;
		this.field = field;
	}

	public String getName() {
		return name;
	}

	public LinkedList<String> getField() {
		return field;
	}

}
