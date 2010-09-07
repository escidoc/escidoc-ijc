package de.escidoc.core.resources.common.versionhistory;

/**
 * TODO: Not extending from ResourceRef?
 * 
 * @author ?
 *
 */
public class LinkingAgentIdentifier {
	
    private String xLinkHref = null;

    private String xLinkTitle = null;

    private String xLinkType = "simple";

    private String linkingAgentIdentifierType = null;

    private String linkingAgentIdentifierValue = null;

    /**
	 * @return the xLinkHref
	 */
	public String getXLinkHref() {
		return xLinkHref;
	}

	/**
	 * @param xLinkHref the xLinkHref to set
	 */
	public void setXLinkHref(String xLinkHref) {
		this.xLinkHref = xLinkHref;
	}

	/**
	 * @return the xLinkTitle
	 */
	public String getXLinkTitle() {
		return xLinkTitle;
	}

	/**
	 * @param xLinkTitle the xLinkTitle to set
	 */
	public void setXLinkTitle(String xLinkTitle) {
		this.xLinkTitle = xLinkTitle;
	}

	/**
	 * @return the xLinkType
	 */
	public String getXLinkType() {
		return xLinkType;
	}

	/**
	 * @param xLinkType the xLinkType to set
	 */
	public void setXLinkType(String xLinkType) {
		this.xLinkType = xLinkType;
	}

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
