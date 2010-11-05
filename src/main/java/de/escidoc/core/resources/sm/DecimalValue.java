package de.escidoc.core.resources.sm;

/**
 * @author MRO
 * 
 */
public abstract class DecimalValue extends Parameter {

	private String stringValue;

	/**
	 * Constructor for JiBX only.
	 */
	@SuppressWarnings("unused")
	public DecimalValue() {

	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

}
