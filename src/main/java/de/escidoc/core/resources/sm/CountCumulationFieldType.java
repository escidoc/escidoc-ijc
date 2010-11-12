package de.escidoc.core.resources.sm;

/**
 * @author MRO
 * 
 */
public class CountCumulationFieldType implements FieldType {

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
