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
public class IndexName {
    private String set;

    private String name;

    public String getSet() {
        return set;
    }

    public void setSet(final String set) {
        this.set = set;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

}
