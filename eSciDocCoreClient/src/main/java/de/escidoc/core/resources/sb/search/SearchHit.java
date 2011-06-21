package de.escidoc.core.resources.sb.search;

import java.util.Collection;
import java.util.LinkedList;

public class SearchHit {

    private Collection<TextFragment> textFragments;

    private Type type;

    private String objid;

    /**
     * @return
     */
    public Collection<TextFragment> getTextFragments() {
        if (textFragments == null)
            textFragments = new LinkedList<TextFragment>();
        return textFragments;
    }

    /**
     * @param textFragments
     */
    public void setTextFragments(final Collection<TextFragment> textFragments) {
        this.textFragments = textFragments;
    }

    /**
     * @return
     */
    public Type getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(final Type type) {
        this.type = type;
    }

    /**
     * @return
     */
    public String getObjid() {
        return objid;
    }

    /**
     * @param objid
     */
    public void setObjid(final String objid) {
        this.objid = objid;
    }
}