package de.escidoc.core.resources.sm;

/**
 * @author MRO
 * 
 */
public class InfoFieldType implements FieldType {

	public enum FieldTypes {
		text, numeric, date
	}

	private String name;
	private FieldTypes type;
	private String xpath;

	// attribute?
	private String feed;

	/**
	 * Constructor for JiBX only.
	 */
	@SuppressWarnings("unused")
	private InfoFieldType() {

	}

	public InfoFieldType(String name, FieldTypes type, String xpath,
			String feed) {
		if (feed == null)
			throw new IllegalArgumentException("feed must not be null.");
		if (xpath == null)
			throw new IllegalArgumentException("xpath must not be null.");
		if (type == null)
			throw new IllegalArgumentException("type must not be null.");
		if (name == null)
			throw new IllegalArgumentException("name must not be null.");

		this.name = name;
		this.type = type;
		this.xpath = xpath;
		this.feed = feed;
	}

	public String getName() {
		return name;
	}

	public FieldTypes getType() {
		return type;
	}

	public String getXpath() {
		return xpath;
	}

	public String getFeed() {
		return feed;
	}

}
