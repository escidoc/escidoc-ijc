package de.escidoc.core.resources.common.properties;

import de.escidoc.core.resources.interfaces.XmlCompatibleEnum;

public enum PublicStatus implements XmlCompatibleEnum {

    PENDING, SUBMITTED, IN_REVISION("in-revision"), RELEASED, WITHDRAWN, CREATED, OPENED, CLOSED;

    private final String xmlValue;

    /**
     * @param xmlValue
     */
    private PublicStatus(final String xmlValue) {
        this.xmlValue = xmlValue;
    }

    /**
     * 
     */
    private PublicStatus() {
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
