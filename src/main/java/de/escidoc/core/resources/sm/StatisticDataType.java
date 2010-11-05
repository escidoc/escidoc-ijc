package de.escidoc.core.resources.sm;

import de.escidoc.core.resources.Resource;

/**
 * @author MRO
 * 
 */
public class StatisticDataType extends Resource {

	private StatisticTable statisticTable;

	/**
	 * Constructor for JiBX only.
	 */
	@SuppressWarnings("unused")
	private StatisticDataType() {

	}

	public StatisticDataType(StatisticTable statisticTable) {
		if (statisticTable == null)
			throw new IllegalArgumentException(
					"statisticTable must not be null.");

		this.statisticTable = statisticTable;
	}

	public StatisticTable getStatisticTable() {
		return statisticTable;
	}

}
