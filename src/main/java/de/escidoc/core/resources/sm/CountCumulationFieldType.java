package de.escidoc.core.resources.sm;

import de.escidoc.core.resources.Resource;

/**
 * @author MRO
 * 
 */
public class CountCumulationFieldType extends FieldType {

	private String name;

	/**
	 * Constructor for JiBX only.
	 */
	@SuppressWarnings("unused")
	private CountCumulationFieldType() {

	}

	public CountCumulationFieldType(String name) {
		if (name == null)
			throw new IllegalArgumentException("name must not be null.");

		this.name = name;
	}

	public String getName() {
		return name;
	}

}
