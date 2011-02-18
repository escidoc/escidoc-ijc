package de.escidoc.core.resources.sb.explain;

import java.util.LinkedList;
import java.util.List;

import de.escidoc.core.annotations.JiBX;

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
@JiBX
public class IndexInfo {

    List<IndexSet> sets = new LinkedList<IndexSet>();

    List<Index> indexes = new LinkedList<Index>();

    List<String> sortKeyWords = new LinkedList<String>();

    @JiBX
    private IndexInfo() {

    }

    public List<IndexSet> getSets() {
        return sets;
    }

    public List<Index> getIndexes() {
        return indexes;
    }

    public List<String> getSortKeyWords() {
        return sortKeyWords;
    }
}
