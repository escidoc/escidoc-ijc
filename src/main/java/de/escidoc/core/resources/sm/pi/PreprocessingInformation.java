package de.escidoc.core.resources.sm.pi;

import org.joda.time.DateTime;

import de.escidoc.core.resources.Resource;

/**
 * @author MRO
 * 
 */
public class PreprocessingInformation extends Resource {

    private DateTime endDate;

    private DateTime startDate;

    /**
     * Constructor for JiBX only also.
     */
    public PreprocessingInformation() {

    }

    /**
     * @return
     */
    public DateTime getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     */
    public void setEndDate(final DateTime endDate) {
        this.endDate = endDate;
    }

    /**
     * @return
     */
    public DateTime getStartDate() {
        return startDate;
    }

    /**
     * @param startDate
     */
    public void setStartDate(final DateTime startDate) {
        this.startDate = startDate;
    }
}