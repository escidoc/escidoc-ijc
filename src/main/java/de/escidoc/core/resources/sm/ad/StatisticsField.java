/**
 * 
 */
package de.escidoc.core.resources.sm.ad;

import de.escidoc.core.annotations.JiBX;

/**
 * @author MVO
 * 
 */
@JiBX
public abstract class StatisticsField extends Field {

    /**
     * package-private for JiBX, to be able to access this field without using
     * setter method.
     */
    String feed;

    @JiBX
    StatisticsField() {

    }

    /**
     * 
     * @param name
     * @param feed
     */
    public StatisticsField(final String name, final String feed) {
        super(name);

        if (feed == null)
            throw new IllegalArgumentException("feed must not be null.");
        this.feed = feed;
    }

    /**
     * @return the feed
     */
    public final String getFeed() {
        return feed;
    }
}