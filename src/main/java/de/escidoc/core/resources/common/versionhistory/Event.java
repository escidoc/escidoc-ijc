package de.escidoc.core.resources.common.versionhistory;

public class Event {

    private String xmlID = null;

    private EventIdentifier eventIdentifier = null;

    private String eventType = null;

    private String eventDateTime = null;

    private String eventDetail = null;

    private LinkingAgentIdentifier linkingAgentIdentifier = null;

    private LinkingObjectIdentifier linkingObjectIdentifier = null;

    /**
     * @return the xmlID
     */
    public String getXmlID() {
        return xmlID;
    }

    /**
     * @param xmlID
     *            the xmlID to set
     */
    public void setXmlID(String xmlID) {
        this.xmlID = xmlID;
    }

    /**
     * @return the eventIdentifier
     */
    public EventIdentifier getEventIdentifier() {
        return eventIdentifier;
    }

    /**
     * @param eventIdentifier
     *            the eventIdentifier to set
     */
    public void setEventIdentifier(EventIdentifier eventIdentifier) {
        this.eventIdentifier = eventIdentifier;
    }

    /**
     * @return the eventType
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * @param eventType
     *            the eventType to set
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    /**
     * @return the eventDateTime
     */
    public String getEventDateTime() {
        return eventDateTime;
    }

    /**
     * @param eventDateTime
     *            the eventDateTime to set
     */
    public void setEventDateTime(String eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    /**
     * @return the eventDetail
     */
    public String getEventDetail() {
        return eventDetail;
    }

    /**
     * @param eventDetail
     *            the eventDetail to set
     */
    public void setEventDetail(String eventDetail) {
        this.eventDetail = eventDetail;
    }

    /**
     * @return the linkingAgentIdentifier
     */
    public LinkingAgentIdentifier getLinkingAgentIdentifier() {
        return linkingAgentIdentifier;
    }

    /**
     * @param linkingAgentIdentifier
     *            the linkingAgentIdentifier to set
     */
    public void setLinkingAgentIdentifier(
        LinkingAgentIdentifier linkingAgentIdentifier) {
        this.linkingAgentIdentifier = linkingAgentIdentifier;
    }

    /**
     * @return the linkingObjectIdentifier
     */
    public LinkingObjectIdentifier getLinkingObjectIdentifier() {
        return linkingObjectIdentifier;
    }

    /**
     * @param linkingObjectIdentifier
     *            the linkingObjectIdentifier to set
     */
    public void setLinkingObjectIdentifier(
        LinkingObjectIdentifier linkingObjectIdentifier) {
        this.linkingObjectIdentifier = linkingObjectIdentifier;
    }

}
