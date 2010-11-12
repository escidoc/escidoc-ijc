package de.escidoc.core.resources.sm;

/**
 * @author MRO
 * 
 */
public class TimeReductionFieldType implements FieldType {

	public enum DateReductionTypes {
		year, month, day, weekday
	}

	private String name;
	private DateReductionTypes reduceTo;
	private String xpath;

	// attribute?
	private String feed;

	/**
	 * Constructor for JiBX only.
	 */
	@SuppressWarnings("unused")
	private TimeReductionFieldType() {

	}

	public TimeReductionFieldType(String name, DateReductionTypes reduceTo,
			String feed) {
		if (feed == null)
			throw new IllegalArgumentException("feed must not be null.");
		if (reduceTo == null)
			throw new IllegalArgumentException("reduceTo must not be null.");
		if (name == null)
			throw new IllegalArgumentException("name must not be null.");

		this.name = name;
		this.reduceTo = reduceTo;
		this.feed = feed;
	}

	public String getXpath() {
		return xpath;
	}

	public void setXpath(String xpath) {
		this.xpath = xpath;
	}

	public String getName() {
		return name;
	}

	public DateReductionTypes getReduceTo() {
		return reduceTo;
	}

	public String getFeed() {
		return feed;
	}

}
