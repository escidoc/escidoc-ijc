package de.escidoc.core.resources.sb.explain;

import java.util.Collection;
import java.util.LinkedList;

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

    private Collection<DatabaseInfoText> titles =
        new LinkedList<DatabaseInfoText>();

    private Collection<DatabaseInfoText> descriptions =
        new LinkedList<DatabaseInfoText>();;

    private String contact;

    private Implementation implementation;

    protected DatabaseInfo() {
    }

    public Collection<DatabaseInfoText> getTitles() {
        return titles;
    }

    public Collection<DatabaseInfoText> getDescriptions() {
        return descriptions;
    }

    public String getContact() {
        return contact;
    }

    public Implementation getImplementation() {
        return implementation;
    }
}
