package de.escidoc.core.resources.sm.report;

import java.util.Collection;
import java.util.LinkedList;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.sm.Parameter;

/**
 * @author MRO
 * 
 */
@JiBX
public class ReportRecord {

    private Collection<Parameter<?>> parameters;

    /**
     * 
     * @param name
     */
    public ReportRecord() {

    }

    /**
     * 
     * 
     * @return
     */
    public Collection<Parameter<?>> getParameters() {
        if (parameters == null) {
            parameters = new LinkedList<Parameter<?>>();
        }
        return parameters;
    }
}