/**
 * 
 */
package de.escidoc.core.resources.sm.scope;

import de.escidoc.core.resources.interfaces.XmlCompatibleEnum;

/**
 * @author MVO
 * 
 */
public enum ScopeType implements XmlCompatibleEnum {

    NORMAL, ADMIN;

    private final String xmlValue;

    /**
     * @param xmlValue
     */
    private ScopeType(final String xmlValue) {
        this.xmlValue = xmlValue;
    }

    /**
     * 
     */
    private ScopeType() {
        this.xmlValue = name().toLowerCase();
    }

    @Override
    public String getXmlValue() {
        return xmlValue;
    }
}