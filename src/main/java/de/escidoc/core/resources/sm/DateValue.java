package de.escidoc.core.resources.sm;

import org.joda.time.DateTime;

/**
 * @author MRO
 * 
 */
public abstract class DateValue extends Parameter {

	private DateTime dateValue;

	/**
	 * Constructor for JiBX only.
	 */
	@SuppressWarnings("unused")
	public DateValue() {

	}

	public DateTime getDateValue() {
		return dateValue;
	}

	public void setDateValue(DateTime dateValue) {
		this.dateValue = dateValue;
	}

}
