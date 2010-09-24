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
public class IndexInfo {
    Collection<MySet> sets = new LinkedList<MySet>();

    Collection<Index> indexes = new LinkedList<Index>();

    Collection<String> sortKeyWords = new LinkedList<String>();

    public Collection<MySet> getSets() {
        return sets;
    }

    public Collection<Index> getIndexes() {
        return indexes;
    }

    public Collection<String> getSortKeyWords() {
        return sortKeyWords;
    }
}
