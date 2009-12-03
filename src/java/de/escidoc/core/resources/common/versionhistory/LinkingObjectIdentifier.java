package de.escidoc.core.resources.common.versionhistory;

public class LinkingObjectIdentifier {

    private String linkingObjectIdentifierType = null;

    private String linkingObjectIdentifierValue = null;

    /**
     * @return the linkingObjectIdentifierType
     */
    public String getLinkingObjectIdentifierType() {
        return linkingObjectIdentifierType;
    }

    /**
     * @param linkingObjectIdentifierType
     *            the linkingObjectIdentifierType to set
     */
    public void setLinkingObjectIdentifierType(
        String linkingObjectIdentifierType) {
        this.linkingObjectIdentifierType = linkingObjectIdentifierType;
    }

    /**
     * @return the linkingObjectIdentifierValue
     */
    public String getLinkingObjectIdentifierValue() {
        return linkingObjectIdentifierValue;
    }

    /**
     * @param linkingObjectIdentifierValue
     *            the linkingObjectIdentifierValue to set
     */
    public void setLinkingObjectIdentifierValue(
        String linkingObjectIdentifierValue) {
        this.linkingObjectIdentifierValue = linkingObjectIdentifierValue;
    }

}
