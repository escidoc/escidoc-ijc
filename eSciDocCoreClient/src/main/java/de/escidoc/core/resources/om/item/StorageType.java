package de.escidoc.core.resources.om.item;

import de.escidoc.core.resources.interfaces.XmlCompatibleEnum;

public enum StorageType implements XmlCompatibleEnum {

    INTERNAL_MANAGED("internal-managed"), EXTERNAL_URL("external-url"), EXTERNAL_MANAGED("external-managed");

    private final String xmlValue;

    /**
     * @param xmlValue
     */
    private StorageType(final String xmlValue) {
        this.xmlValue = xmlValue;
    }

    /**
     * 
     */
    private StorageType() {
        this.xmlValue = name().toLowerCase();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.om.item.XmlCompatibleEnum#getXmlValue()
     */
    @Override
    public String getXmlValue() {
        return xmlValue;
    }
}