/**
 * 
 */
package de.escidoc.core.resources.sm.ad;

import de.escidoc.core.resources.interfaces.XmlCompatibleEnum;

/**
 * @author MVO
 * 
 */
public enum InfoFieldType implements XmlCompatibleEnum {
    TEXT, NUMERIC, DATE;

    private final String xmlValue;

    /**
     * @param xmlValue
     */
    private InfoFieldType(final String xmlValue) {
        this.xmlValue = xmlValue;
    }

    /**
     * 
     */
    private InfoFieldType() {
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