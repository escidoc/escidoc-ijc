package de.escidoc.core.resources.sb.search;

import java.util.Collection;
import java.util.LinkedList;

public class SearchHit {
    private Collection<TextFragment> textFragments =
        new LinkedList<TextFragment>();

    private Type type;

    private String objid;

    public Collection<TextFragment> getTextFragments() {
        return textFragments;
    }

    public void setTextFragment(Collection<TextFragment> textFragments) {
        this.textFragments = textFragments;
    }

    public Type getType() {
        return type;
    }

    public void setType(final Type type) {
        this.type = type;
    }

    public String getObjid() {
        return objid;
    }

    public void setObjid(final String objid) {
        this.objid = objid;
    }
}
