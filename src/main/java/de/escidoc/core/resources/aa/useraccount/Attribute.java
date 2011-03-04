package de.escidoc.core.resources.aa.useraccount;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.GenericResource;
import de.escidoc.core.resources.ResourceType;

/**
 * User Account Attribute.
 * 
 * @author SWA
 * 
 */
@JiBX
public class Attribute extends GenericResource {

    private String name;

    private String value;

    private boolean internal = false;

    /**
     * User Account Attribute.
     */
    public Attribute() {
    }

    /**
     * User Account Attribute.
     * 
     * @param name
     *            Name/key of preference
     * @param value
     *            Attribute value
     */
    public Attribute(final String name, final String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Set name.
     * 
     * @param name
     *            Name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Get name.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set value.
     * 
     * @param value
     *            value
     */
    public void setValue(final String value) {
        this.value = value;
    }

    /**
     * Get value
     * 
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * Set internal.
     * 
     * An attribute is internal if it is not obtained from external
     * authentication provider (Shibboleth, LDAP).
     * 
     * External attributes obtained are overridden with each login. Internal
     * attributes survive login sessions.
     * 
     * @param internal
     *            Set true if internal, false otherwise
     */
    public void setInternal(final boolean internal) {
        this.internal = internal;
    }

    /**
     * Is attribute internal.
     * 
     * @return true if internal, false otherwise
     */
    public boolean isInternal() {
        return internal;
    }

    @Override
    public ResourceType getResourceType() {
        return ResourceType.UserAccountAttribute;
    }
}