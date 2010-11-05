package de.escidoc.core.resources.sm;

import java.util.LinkedList;

import de.escidoc.core.resources.Resource;

/**
 * @author MRO
 * 
 */
public class AggregationTableType extends Resource {

	private String name;
	private LinkedList<FieldType> field;
	private LinkedList<IndexType> index;

	/**
	 * Constructor for JiBX only.
	 */
	@SuppressWarnings("unused")
	private AggregationTableType() {

	}

	public AggregationTableType(String name, LinkedList<FieldType> field) {
		if (field == null)
			throw new IllegalArgumentException("field must not be null.");
		if (name == null)
			throw new IllegalArgumentException("name must not be null.");

		this.name = name;
		this.field = field;
	}

	public void setIndex(LinkedList<IndexType> index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public LinkedList<FieldType> getField() {
		return field;
	}

	public LinkedList<IndexType> getIndex() {
		return index;
	}

}
