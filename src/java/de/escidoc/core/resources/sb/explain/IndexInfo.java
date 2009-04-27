package de.escidoc.core.resources.sb.explain;

import java.util.Collection;
import java.util.LinkedList;

public class IndexInfo {
Collection<MySet> sets = new LinkedList<MySet>();
Collection<Index> indexes = new LinkedList<Index>();
Collection <String> sortKeyWords = new LinkedList<String>();
public Collection<MySet> getSets() {
    return sets;
}
public void setSets(Collection<MySet> sets) {
    this.sets = sets;
}
public Collection<Index> getIndexes() {
    return indexes;
}
public void setIndexes(Collection<Index> indexes) {
    this.indexes = indexes;
}
public Collection<String> getSortKeyWords() {
    return sortKeyWords;
}
public void setSortKeyWords(Collection<String> sortKeyWords) {
    this.sortKeyWords = sortKeyWords;
}



}
