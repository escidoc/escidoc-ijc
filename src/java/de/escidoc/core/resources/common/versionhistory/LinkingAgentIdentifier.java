package de.escidoc.core.resources.common.versionhistory;

import de.escidoc.core.resources.XLinkResource;

/**
 * TODO: Not extending from ResourceRef?
 * 
 * @author ?
 *
 */
public class LinkingAgentIdentifier extends XLinkResource {
	
    private String linkingAgentIdentifierType = null;

    private String linkingAgentIdentifierValue = null;

	/**
     * @return the linkingAgentIdentifierType
     */
    public String getLinkingAgentIdentifierType() {
        return linkingAgentIdentifierType;
    }

    /**
     * @param linkingAgentIdentifierType
     *            the linkingAgentIdentifierType to set
     */
    public void setLinkingAgentIdentifierType(String linkingAgentIdentifierType) {
        this.linkingAgentIdentifierType = linkingAgentIdentifierType;
    }

    /**
     * @return the linkingAgentIdentifierValue
     */
    public String getLinkingAgentIdentifierValue() {
        return linkingAgentIdentifierValue;
    }

    /**
     * @param linkingAgentIdentifierValue
     *            the linkingAgentIdentifierValue to set
     */
    public void setLinkingAgentIdentifierValue(
        String linkingAgentIdentifierValue) {
        this.linkingAgentIdentifierValue = linkingAgentIdentifierValue;
    }

}
