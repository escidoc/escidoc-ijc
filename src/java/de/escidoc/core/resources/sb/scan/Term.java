/**
 * 
 */
package de.escidoc.core.resources.sb.scan;

import gov.loc.www.zing.srw.TermType;

/**
 * @author MVO
 *
 */
public class Term {
	
	private int numberOfRecords;
	
	private String value;
	
	protected Term() {}
	
	protected Term(TermType type) {
		this.value = type.getValue();
		if(type.getNumberOfRecords() != null)
			this.numberOfRecords = type.getNumberOfRecords().intValue();
	}

	/**
	 * @return the numberOfRecords
	 */
	public int getNumberOfRecords() {
		return numberOfRecords;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
}
