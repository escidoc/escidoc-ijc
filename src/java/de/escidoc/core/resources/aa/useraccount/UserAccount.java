package de.escidoc.core.resources.aa.useraccount;

import de.escidoc.core.resources.om.GenericResource;

public class UserAccount extends GenericResource {
    private UserAccountProperties properties;

    public UserAccountProperties getProperties() {
        return properties;
    }

    public void setProperties(UserAccountProperties properties) {
        this.properties = properties;
    }
    
}

