package de.escidoc.core.resources.sm.ad;

import de.escidoc.core.annotations.JiBX;

/**
 * @author MRO
 * 
 */
@JiBX
public class DifferenceCumulationField extends StatisticsField {

    private String xPath;

    @JiBX
    @SuppressWarnings("unused")
    private DifferenceCumulationField() {

    }

    /**
     * @param name
     * @param feed
     * @param xPath
     */
    public DifferenceCumulationField(final String name, final String feed,
        final String xPath) {
        super(name, feed);

        if (xPath == null)
            throw new IllegalArgumentException("xPath must not be null.");
        this.xPath = xPath;
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
        return FieldType.DifferenceCumulationField;
    }
}
