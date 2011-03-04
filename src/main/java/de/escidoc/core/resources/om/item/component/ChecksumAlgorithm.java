/**
 * 
 */
package de.escidoc.core.resources.om.item.component;

import de.escidoc.core.resources.interfaces.XmlCompatibleEnum;

/**
 * @author MVO
 * 
 */
public enum ChecksumAlgorithm implements XmlCompatibleEnum {

    MD5, SHA_1("SHA-1"), SHA_256("SHA-256"), SHA_384("SHA-384"), SHA_512(
        "SHA-512"), HAVAL, TIGER, WHIRLPOOL, DISABLED;

    private final String xmlValue;

    private ChecksumAlgorithm(final String xmlValue) {
        this.xmlValue = xmlValue;
    }

    private ChecksumAlgorithm() {
        this.xmlValue = name();
    }

    @Override
    public String getXmlValue() {
        return xmlValue;
    }
}