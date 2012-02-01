package de.escidoc.core.resources.sb.explain;

import de.escidoc.core.annotations.JiBX;

/**
 * 
 * Read-only class.
 * 
 * This class is a representation of the configInfo of the response of an
 * explain request.
 * 
 * @author ?, MVO
 * 
 */
@JiBX
public class IndexSet {

    private String identifier;

    private String name;

    @JiBX
    private IndexSet() {

    }

    public String getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }
}
