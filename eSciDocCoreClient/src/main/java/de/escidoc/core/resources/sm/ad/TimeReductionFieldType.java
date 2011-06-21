/**
 * 
 */
package de.escidoc.core.resources.sm.ad;

import de.escidoc.core.resources.interfaces.XmlCompatibleEnum;

/**
 * @author MVO
 * 
 */
public enum TimeReductionFieldType implements XmlCompatibleEnum {

    YEAR, MONTH, DAY, WEEKDAY;

    private final String xmlValue;

    /**
     * @param xmlValue
     */
    private TimeReductionFieldType(final String xmlValue) {
        this.xmlValue = xmlValue;
    }

    /**
     * 
     */
    private TimeReductionFieldType() {
        this.xmlValue = name().toLowerCase();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.interfaces.XmlCompatibleEnum#getXmlValue()
     */
    @Override
    public String getXmlValue() {
        return xmlValue;
    }
}