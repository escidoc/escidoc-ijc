package de.escidoc.core.resources.sb.search;

import de.escidoc.core.resources.ResourceRef;

public class SearchResultRecord {
    private String score;

    private Highlight highlight;

    private ResourceRef content;

    private String base;

    public String getBase() {
        return base;
    }

    public void setBase(final String base) {
        this.base = base;
    }

    public Highlight getHighlight() {
        return highlight;
    }

    public void setHighlight(final Highlight highlight) {
        this.highlight = highlight;
    }

    public ResourceRef getContent() {
        return this.content;
    }

    public void setContent(final ResourceRef content) {
        this.content = content;
    }

    public String getScore() {
        return score;
    }

    public void setScore(final String score) {
        this.score = score;
    }
}
