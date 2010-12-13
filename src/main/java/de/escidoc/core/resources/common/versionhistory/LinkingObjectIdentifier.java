package de.escidoc.core.resources.common.versionhistory;

import de.escidoc.core.annotations.JiBX;

/**
 * 
 * @author ?, MVO
 * 
 */
@JiBX
public class LinkingObjectIdentifier {

    private String linkingObjectIdentifierType;

    private String linkingObjectIdentifierValue;

    /**
     * @return the linkingObjectIdentifierType
     */
    public String getLinkingObjectIdentifierType() {
        return linkingObjectIdentifierType;
    }

    /**
     * @return the linkingObjectIdentifierValue
     */
    public String getLinkingObjectIdentifierValue() {
        return linkingObjectIdentifierValue;
    }
}