package de.escidoc.core.resources.sm;

import org.joda.time.DateTime;

import de.escidoc.core.resources.Resource;

/**
 * @author MRO
 * 
 */
public class PreprocessingInformation extends Resource {

	private DateTime endDate;
	private DateTime startDate;

	/**
	 * Constructor for JiBX only.
	 */
	@SuppressWarnings("unused")
	public PreprocessingInformation() {

	}

	public DateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}

	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

}
