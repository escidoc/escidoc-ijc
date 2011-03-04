/**
 * 
 */
package de.escidoc.core.resources.common.properties;

import de.escidoc.core.resources.interfaces.XmlCompatibleEnum;

/**
 * @author MVO
 * 
 */
public enum LockStatus implements XmlCompatibleEnum {
    LOCKED, UNLOCKED;

    private final String xmlValue;

    /**
     * @param xmlValue
     */
    private LockStatus(final String xmlValue) {
        this.xmlValue = xmlValue;
    }

    /**
     * 
     */
    private LockStatus() {
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