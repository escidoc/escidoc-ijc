package de.escidoc.core.resources.aa.pdp;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Request Results.
 * 
 * @author SWA
 * 
 */
public class RequestsResults {

    private Collection<Result> results = new LinkedList<Result>();

    /**
     * Get Results of Request.
     * 
     * @return Collection of request results.
     */
    public Collection<Result> getResults() {
        return this.results;
    }

    /**
     * Set regest results.
     * 
     * @param requestResults
     *            Request results.
     */
    public void setResults(final Collection<Result> requestResults) {
        this.results = requestResults;
    }
}
