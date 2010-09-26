package de.escidoc.core.resources.common.versionhistory;

public class EventIdentifier {

    private String eventIdentifierType = null;

    private String eventIdentifierValue = null;

    /**
     * @return the eventIdentifierType
     */
    public String getEventIdentifierType() {
        return eventIdentifierType;
    }

    /**
     * @param eventIdentifierType
     *            the eventIdentifierType to set
     */
    public void setEventIdentifierType(String eventIdentifierType) {
        this.eventIdentifierType = eventIdentifierType;
    }

    /**
     * @return the eventIdentifierValue
     */
    public String getEventIdentifierValue() {
        return eventIdentifierValue;
    }

    /**
     * @param eventIdentifierValue
     *            the eventIdentifierValue to set
     */
    public void setEventIdentifierValue(String eventIdentifierValue) {
        this.eventIdentifierValue = eventIdentifierValue;
    }
}
