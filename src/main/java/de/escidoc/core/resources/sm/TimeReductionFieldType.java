package de.escidoc.core.resources.sm;

import de.escidoc.core.resources.Resource;

/**
 * @author MRO
 * 
 */
public class TimeReductionFieldType extends FieldType {
	
	public enum TimeReductionFieldTypeType {
		year, month, day, weekday
	}
	
	private String name;
	private TimeReductionFieldTypeType reduceTo;
	private String xpath;
	
	// attribute?
	private String feed;
	

	/**
	 * Constructor for JiBX only.
	 */
	@SuppressWarnings("unused")
	private TimeReductionFieldType() {

	}
	
}
