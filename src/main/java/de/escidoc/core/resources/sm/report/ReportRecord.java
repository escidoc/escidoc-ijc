package de.escidoc.core.resources.sm.report;

import java.util.LinkedList;
import java.util.List;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.sm.Parameter;

/**
 * @author MRO
 * 
 */
@JiBX
public class ReportRecord {

    private List<Parameter<?>> parameters;

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
    public List<Parameter<?>> getParameters() {
        if (parameters == null) {
            parameters = new LinkedList<Parameter<?>>();
        }
        return parameters;
    }
}