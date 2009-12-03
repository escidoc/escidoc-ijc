package de.escidoc.core.resources.aa.role;

import java.util.Collection;
import java.util.LinkedList;

public class Roles {
public Roles() {
        
    }
    private final Collection<Role> roles = new LinkedList<Role>();
    public Collection<Role> getRoles() {
        return this.roles;
    }
}
