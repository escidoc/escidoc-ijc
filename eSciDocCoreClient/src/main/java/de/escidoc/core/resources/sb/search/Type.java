package de.escidoc.core.resources.sb.search;

import de.escidoc.core.resources.interfaces.XmlCompatibleEnum;

public enum Type implements XmlCompatibleEnum {

    FULLTEXT, METADATA;

    private String xmlValue;

    /**
     * @param xmlValue
     */
    private Type(final String xmlValue) {
        this.xmlValue = xmlValue;
    }

    /**
     * 
     */
    private Type() {
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