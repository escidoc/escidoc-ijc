package de.escidoc.core.resources.sb.wrapper.scan;

import gov.loc.www.zing.srw.TermType;

/**
 * 
 * @author MVO
 *
 */
public class MyTermType {

	private TermType termType = null;
	
	/**
	 * 
	 * @param termType
	 */
	public MyTermType(TermType termType) {
		this.termType = termType;
	}
	
	public java.lang.String getDisplayType() {
		return termType.getDisplayTerm();
	}
	
	public org.apache.axis.types.NonNegativeInteger getNumberOfRecords() {
		return termType.getNumberOfRecords();
	}
	
	public java.lang.String getValue() {
		return termType.getValue();
	}
	
	public gov.loc.www.zing.srw.TermTypeWhereInList getWhereInList() {
		return termType.getWhereInList();
	}
	
	public gov.loc.www.zing.srw.ExtraDataType getExtraTermData() {
		return termType.getExtraTermData();
	}
}
