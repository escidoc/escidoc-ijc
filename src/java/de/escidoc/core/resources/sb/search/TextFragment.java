package de.escidoc.core.resources.sb.search;

import java.util.List;

public class TextFragment {

    private String textFragmentData;

    private List<HitWord> hitWords;

    public String getTextFragmentData() {
        return textFragmentData;
    }

    public void setTextFragmentData(final String textFragmentData) {
        this.textFragmentData = textFragmentData;
    }

    public List<HitWord> getHitWords() {
        return hitWords;
    }

    public void setHitWords(final List<HitWord> hitWords) {
        this.hitWords = hitWords;
    }

}
