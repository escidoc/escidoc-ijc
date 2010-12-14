package de.escidoc.core.resources.sm.ad;

import de.escidoc.core.annotations.JiBX;

/**
 * @author MRO
 * 
 */
@JiBX
public class TimeReductionField extends StatisticsField {

    private TimeReductionFieldType reduceTo;

    // optional
    private String xPath;

    @JiBX
    @SuppressWarnings("unused")
    private TimeReductionField() {

    }

    /**
     * Default constructor.
     * 
     * @param name
     * @param feed
     * @param reduceTo
     */
    public TimeReductionField(final String name, final String feed,
        final TimeReductionFieldType reduceTo) {

        super(name, feed);

        if (reduceTo == null)
            throw new IllegalArgumentException("reduceTo must not be null.");
        this.reduceTo = reduceTo;
    }

    /**
     * Constructor for optional xPath.
     * 
     * @param name
     * @param feed
     * @param reduceTo
     * @param xPath
     */
    public TimeReductionField(final String name, final String feed,
        final TimeReductionFieldType reduceTo, final String xPath) {

        this(name, feed, reduceTo);

        this.xPath = xPath;
    }

    /**
     * @return the reduceTo
     */
    public final TimeReductionFieldType getReduceTo() {
        return reduceTo;
    }

    /**
     * @return the xpath
     */
    public final String getXPath() {
        return xPath;
    }

    /**
     * @param xpath
     *            the xpath to set
     */
    public final void setXPath(final String xPath) {
        this.xPath = xPath;
    }

    @Override
    public FieldType getType() {
        return FieldType.TimeReductionField;
    }
}