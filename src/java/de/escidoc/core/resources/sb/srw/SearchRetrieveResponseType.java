package de.escidoc.core.resources.sb.srw;

import java.util.Collection;
import java.util.LinkedList;

/**
 * SearchRetrieveResponseType is more or less a functional copy of
 * gov.loc.www.zing.srw.SearchRetrieveResponseType to allow JiBX to weave in
 * code.
 * 
 * @author SWA
 * 
 */
public class SearchRetrieveResponseType {

    private String version = null;

    private int numberOfRecords = -1;

    private final Collection<Record> records = new LinkedList<Record>();

    /**
     * Get version.
     * 
     * @return version
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * Set version
     * 
     * @param version
     */
    public void setVersion(final String version) {
        this.version = version;
    }

    public void setNumberOfRecords(final int numberOfRecords) {
        this.numberOfRecords = numberOfRecords;
    }

    public int getNumberOfRecords() {
        return this.numberOfRecords;
    }

    /**
     * Collection of retrieve records.
     * 
     * @return records
     */
    public Collection<Record> getRecords() {
        return records;
    }
}
