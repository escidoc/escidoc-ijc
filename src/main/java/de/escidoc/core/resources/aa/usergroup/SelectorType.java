package de.escidoc.core.resources.aa.usergroup;

import de.escidoc.core.resources.interfaces.XmlCompatibleEnum;

/**
 * 
 * @author MVO
 * 
 */
public enum SelectorType implements XmlCompatibleEnum {

    INTERNAL, USER_ATTRIBUTE("user-attribute");

    private final String xmlName;

    /**
     * @param xmlName
     */
    private SelectorType(final String xmlName) {
        this.xmlName = xmlName;
    }

    /**
     * 
     */
    private SelectorType() {
        this.xmlName = name().toLowerCase();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.interfaces.XmlCompatibleEnum#getXmlValue()
     */
    @Override
    public String getXmlValue() {
        return xmlName;
    }
}