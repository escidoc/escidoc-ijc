package de.escidoc.core.resources.sm.ad;

/**
 * @author MRO
 * 
 */
public class StatisticData {

    private StatisticTable statisticTable;

    /**
     * Constructor for JiBX only.
     */
    @SuppressWarnings("unused")
    private StatisticData() {

    }

    public StatisticData(final StatisticTable statisticTable) {
        if (statisticTable == null)
            throw new IllegalArgumentException("statisticTable must not be null.");

        this.statisticTable = statisticTable;
    }

    /**
     * 
     * @return
     */
    public StatisticTable getStatisticTable() {
        return statisticTable;
    }
}