package de.escidoc.core.resources.sb.explain;

import java.util.LinkedList;
import java.util.List;

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
public class DatabaseInfo {

    private List<DatabaseInfoText> titles = new LinkedList<DatabaseInfoText>();

    private List<DatabaseInfoText> descriptions =
        new LinkedList<DatabaseInfoText>();;

    private String contact;

    private Implementation implementation;

    protected DatabaseInfo() {
    }

    public List<DatabaseInfoText> getTitles() {
        return titles;
    }

    public List<DatabaseInfoText> getDescriptions() {
        return descriptions;
    }

    public String getContact() {
        return contact;
    }

    public Implementation getImplementation() {
        return implementation;
    }
}
