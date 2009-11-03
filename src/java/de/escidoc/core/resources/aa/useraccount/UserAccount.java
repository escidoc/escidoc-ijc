package de.escidoc.core.resources.aa.useraccount;

import de.escidoc.core.resources.om.GenericResource;

public class UserAccount extends GenericResource {
    private PropertiesUserAccount properties;

    public PropertiesUserAccount getProperties() {
        return properties;
    }

    public void setProperties(PropertiesUserAccount properties) {
        this.properties = properties;
    }
    
}

