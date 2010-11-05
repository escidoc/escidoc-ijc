package de.escidoc.core.resources.sm;

import java.util.LinkedList;

import de.escidoc.core.resources.Resource;

/**
 * @author MRO
 * 
 */
public class AggregationDefinitionList extends Resource {
	
	private LinkedList<AggregationDefinition> aggregationDefinitions;

	/**
	 * Constructor for JiBX only.
	 */
	@SuppressWarnings("unused")
	private AggregationDefinitionList() {

	}
	
	public LinkedList<AggregationDefinition> getAggregationDefinitions() {
		return aggregationDefinitions;
	}

}
