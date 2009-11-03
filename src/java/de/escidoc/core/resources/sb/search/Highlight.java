package de.escidoc.core.resources.sb.search;

import java.util.Collection;
import java.util.LinkedList;

public class Highlight {
    private Collection<SearchHit> searchHits = new LinkedList<SearchHit>();

    public Collection<SearchHit> getSearchHits() {
        return searchHits;
    }

    public void setSearchHit(Collection<SearchHit> searchHits) {
        this.searchHits = searchHits;
    }
}
