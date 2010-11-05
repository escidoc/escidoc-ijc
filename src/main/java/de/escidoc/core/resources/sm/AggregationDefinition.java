package de.escidoc.core.resources.sm;

import de.escidoc.core.resources.Resource;
import de.escidoc.core.resources.common.reference.ScopeRef;

/**
 * @author MRO
 * 
 */
public class AggregationDefinition extends Resource {

	private String name;
	private ScopeRef scope;
	private AggregationTableType aggregationTable;
	private StatisticDataType statisticData;

	/**
	 * Constructor for JiBX only.
	 */
	@SuppressWarnings("unused")
	private AggregationDefinition() {

	}

	public AggregationDefinition(String name, ScopeRef scope,
			AggregationTableType aggregationTable,
			StatisticDataType statisticData) {
		if (statisticData == null)
			throw new IllegalArgumentException(
					"statisticData must not be null.");
		if (aggregationTable == null)
			throw new IllegalArgumentException(
					"aggregationTable must not be null.");
		if (scope == null)
			throw new IllegalArgumentException("scope must not be null.");
		if (name == null)
			throw new IllegalArgumentException("name must not be null.");

		this.name = name;
		this.scope = scope;
		this.aggregationTable = aggregationTable;
		this.statisticData = statisticData;
	}

	public String getName() {
		return name;
	}

	public ScopeRef getScope() {
		return scope;
	}

	public AggregationTableType getAggregationTable() {
		return aggregationTable;
	}

	public StatisticDataType getStatisticData() {
		return statisticData;
	}

}
