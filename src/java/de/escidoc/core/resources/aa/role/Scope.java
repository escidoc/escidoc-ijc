package de.escidoc.core.resources.aa.role;

import java.util.Collection;
import java.util.LinkedList;


public class Scope {
    private Collection<ScopeDef>  scopeDefinitions = new LinkedList<ScopeDef>();
    private boolean unlimited = false;
    public Scope() {
        
    }
    public Collection<ScopeDef> getScopeDefinitions() {
        return scopeDefinitions;
    }
    public void setScopeDefinitions(Collection<ScopeDef> scopeDefinitions) {
        this.scopeDefinitions = scopeDefinitions;
    }
    public boolean isUnlimited() {
        return unlimited;
    }
    public void setUnlimited(boolean unlimited) {
        this.unlimited = unlimited;
    }
   
}
