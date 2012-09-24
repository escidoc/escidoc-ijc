package de.escidoc.core.resources.common.versionhistory;

import de.escidoc.core.annotations.JiBX;

/**
 * 
 * @author ?, MVO
 * 
 */
@JiBX
public class Event {

    private String xmlID;

    private EventIdentifier eventIdentifier;

    private String eventType;

    private String eventDateTime;

    private String eventDetail;

    private LinkingAgentIdentifier linkingAgentIdentifier;

    private LinkingObjectIdentifier linkingObjectIdentifier;

    /**
     * @return the xmlID
     */
    public String getXmlID() {
        return xmlID;
    }

    /**
     * @return the eventIdentifier
     */
    public EventIdentifier getEventIdentifier() {
        return eventIdentifier;
    }

    /**
     * @return the eventType
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * @return the eventDateTime
     */
    public String getEventDateTime() {
        return eventDateTime;
    }

    /**
     * @return the eventDetail
     */
    public String getEventDetail() {
        return eventDetail;
    }

    /**
     * @return the linkingAgentIdentifier
     */
    public LinkingAgentIdentifier getLinkingAgentIdentifier() {
        return linkingAgentIdentifier;
    }

    /**
     * @return the linkingObjectIdentifier
     */
    public LinkingObjectIdentifier getLinkingObjectIdentifier() {
        return linkingObjectIdentifier;
    }
}