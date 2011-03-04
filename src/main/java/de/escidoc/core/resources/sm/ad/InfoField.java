package de.escidoc.core.resources.sm.ad;

import de.escidoc.core.annotations.JiBX;

/**
 * @author MRO
 * 
 */
@JiBX
public class InfoField extends StatisticsField {

    private InfoFieldType type;

    private String xPath;

    @JiBX
    @SuppressWarnings("unused")
    private InfoField() {
    }

    /**
     * 
     * @param name
     * @param feed
     * @param type
     * @param xPath
     */
    public InfoField(final String name, final String feed,
        final InfoFieldType type, final String xPath) {
        super(name, feed);

        if (type == null)
            throw new IllegalArgumentException("type must not be null.");
        if (xPath == null)
            throw new IllegalArgumentException("xPath must not be null.");
        this.type = type;
        this.xPath = xPath;
    }

    /**
     * @return the type
     */
    public final InfoFieldType getInfoFieldType() {
        return type;
    }

    /**
     * @return the xpath
     */
    public final String getXPath() {
        return xPath;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.sm.ad.Field#getType()
     */
    @Override
    public FieldType getType() {
        return FieldType.INFO;
    }
}