package de.escidoc.core.resources.common.versionhistory;

public class LinkingAgentIdentifier {
    private String href = null;

    private String title = null;

    private String type = "simple";

    private String linkingAgentIdentifierType = null;

    private String linkingAgentIdentifierValue = null;

    /**
     * @return the href
     */
    public String getHref() {
        return href;
    }

    /**
     * @param href
     *            the href to set
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
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
