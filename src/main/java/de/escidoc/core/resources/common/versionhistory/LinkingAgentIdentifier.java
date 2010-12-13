package de.escidoc.core.resources.common.versionhistory;

import de.escidoc.core.annotations.JiBX;

/**
 * 
 * @author ?, MVO
 * 
 */
@JiBX
public class LinkingAgentIdentifier {

    private String linkingAgentIdentifierType;

    private String linkingAgentIdentifierValue;

    /**
     * @return the linkingAgentIdentifierType
     */
    public String getLinkingAgentIdentifierType() {
        return linkingAgentIdentifierType;
    }

    /**
     * @return the linkingAgentIdentifierValue
     */
    public String getLinkingAgentIdentifierValue() {
        return linkingAgentIdentifierValue;
    }
}