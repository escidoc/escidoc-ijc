package de.escidoc.core.resources.sm.report;

import java.util.Collection;
import java.util.LinkedList;

import de.escidoc.core.resources.sm.Parameter;

/**
 * @author MRO
 * 
 */
public class ReportRecord {

    private String name;

    private Collection<Parameter<?>> parameters =
        new LinkedList<Parameter<?>>();

    /**
     * Constructor for JiBX also.
     */
    @SuppressWarnings("unused")
    private ReportRecord() {
    }

    /**
     * 
     * @param name
     */
    public ReportRecord(final String name) {
        if (name == null)
            throw new IllegalArgumentException("name must not be null.");
        this.name = name;
    }

    /**
     * ,
     * 
     * @return
     */
    public Collection<Parameter<?>> getParameters() {
        return parameters;
    }

    /**
     * @return the name
     */
    public final String getName() {
        return name;
    }
}