package de.escidoc.core.resources.aa.useraccount;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.common.reference.UserAccountRef;

/**
 * 
 * @author Marko Voß
 * 
 */
@JiBX
public class GrantedTo extends UserAccountRef {

    private String resource;

    public GrantedTo() {
    }

    public String getResource() {
        return resource;
    }

    public void setResource(final String resource) {
        this.resource = resource;
    }
}