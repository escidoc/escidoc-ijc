package de.escidoc.core.resources.sb.explain;

/**
 * 
 * Read-only class.
 * 
 * This class is a representation of the configInfo of the response of an
 * explain request.
 * 
 * This class may be initialized either by a SOAP response instance or by JiBX
 * if and only if the REST protocol is being used for the explain request.
 * 
 * @author ?, MVO
 * 
 */
public class Schema {

    private boolean sort;

    private boolean retrieve;

    private String name;

    private String identifier;

    private String location;

    private String title;

    public boolean isSort() {
        return sort;
    }

    public boolean isRetrieve() {
        return retrieve;
    }

    public String getName() {
        return name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getLocation() {
        return location;
    }

    public String getTitle() {
        return title;
    }
}
