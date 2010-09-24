package de.escidoc.core.resources.sb.explain;

/**
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
public class Implementation {
    private String version;

    private String title;

    public String getVersion() {
        return version;
    }

    public String getTitle() {
        return title;
    }
}
