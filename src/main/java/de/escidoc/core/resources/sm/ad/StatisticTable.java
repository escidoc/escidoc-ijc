package de.escidoc.core.resources.sm.ad;

/**
 * @author MRO
 * 
 */
public class StatisticTable {

    private String xPath;

    /**
     * Constructor for JiBX also.
     * 
     */
    public StatisticTable() {

    }

    /**
     * If xPath is null, all statistic data will be included in the aggregation
     * table.
     * 
     * @param xPath
     */
    public void setXPath(final String xPath) {
        this.xPath = xPath;
    }

    /**
     * 
     * @return
     */
    public String getXPath() {
        return xPath;
    }
}