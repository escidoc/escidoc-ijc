package de.escidoc.core.resources.common.versionhistory;

import de.escidoc.core.annotations.JiBX;

/**
 * 
 * @author ?, MVO
 * 
 */
@JiBX
public class EventIdentifier {

    private String eventIdentifierType;

    private String eventIdentifierValue;

    /**
     * @return the eventIdentifierType
     */
    public String getEventIdentifierType() {
        return eventIdentifierType;
    }

    /**
     * @return the eventIdentifierValue
     */
    public String getEventIdentifierValue() {
        return eventIdentifierValue;
    }
}