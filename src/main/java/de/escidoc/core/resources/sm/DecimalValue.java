package de.escidoc.core.resources.sm;

/**
 * @author MRO
 * 
 */
public class DecimalValue implements Parameter {

	private double decimalValue;

	/**
	 * Constructor for JiBX only.
	 */
	@SuppressWarnings("unused")
	public DecimalValue() {

	}

	public double getDecimalValue() {
		return decimalValue;
	}

	public void setDecimalValue(double decimalValue) {
		this.decimalValue = decimalValue;
	}

}
