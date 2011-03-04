package de.escidoc.core.resources.sb.search;

import de.escidoc.core.annotations.JiBX;

@JiBX
public class HitWord {

    private float startIndex;

    private float endIndex;

    public float getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(final float startIndex) {
        this.startIndex = startIndex;
    }

    public float getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(final float endIndex) {
        this.endIndex = endIndex;
    }

}
