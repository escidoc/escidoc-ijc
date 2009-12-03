package de.escidoc.core.resources.aa.useraccount;

import de.escidoc.core.resources.om.GenericResource;

public class Grant extends GenericResource {
    public Grant() {
        
    }
private GrantProperties grantProperties;


public GrantProperties getGrantProperties() {
    return grantProperties;
}

public void setGrantProperties(GrantProperties grantProperties) {
    this.grantProperties = grantProperties;
}
}
