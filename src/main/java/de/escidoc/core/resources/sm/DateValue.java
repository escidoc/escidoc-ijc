package de.escidoc.core.resources.sm;

import org.joda.time.DateTime;

/**
 * @author MRO
 * 
 */
public class DateValue implements Parameter {

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
