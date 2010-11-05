package de.escidoc.core.resources.sm;

import de.escidoc.core.resources.Resource;

/**
 * @author MRO
 * 
 */
public class InfoFieldType extends FieldType {
	
	public enum InfoFieldTypeType {
		text, numeric, date
	}
	
	private String name;
	private InfoFieldTypeType type;
	private String xpath;
	
	// attribute?
	private String feed;
	

	/**
	 * Constructor for JiBX only.
	 */
	@SuppressWarnings("unused")
	private InfoFieldType() {

	}
	
}
