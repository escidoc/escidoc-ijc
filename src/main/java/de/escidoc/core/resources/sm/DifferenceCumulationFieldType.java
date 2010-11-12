package de.escidoc.core.resources.sm;

/**
 * @author MRO
 * 
 */
public class DifferenceCumulationFieldType implements FieldType {
	
	private String name;
	private String xpath;
	
	// attribute?
	private String feed;
	

	/**
	 * Constructor for JiBX only.
	 */
	@SuppressWarnings("unused")
	private DifferenceCumulationFieldType() {

	}

	public DifferenceCumulationFieldType(String name, String xpath, String feed) {
		if (feed == null)
			throw new IllegalArgumentException("feed must not be null.");
		if (xpath == null)
			throw new IllegalArgumentException("xpath must not be null.");
		if (name == null)
			throw new IllegalArgumentException("name must not be null.");
		
		this.name = name;
		this.xpath = xpath;
		this.feed = feed;
	}

	public String getName() {
		return name;
	}

	public String getXpath() {
		return xpath;
	}

	public String getFeed() {
		return feed;
	}
	
}
