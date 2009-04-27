package de.escidoc.core.resources.aa.useraccount;

import java.util.Collection;
import java.util.LinkedList;

public class Grants {
    public Grants() {
        
    }
    private final Collection<Grant> grants = new LinkedList<Grant>();
    public Collection<Grant> getGrants() {
        return grants;
    }
    /**
     * The method is mandatory on create of grants, 
     * but not-allowed on update of grants.
     * @param 
     */
    public void add(final Grant grant) {
        this.grants.add(grant);
    }

    /**
     * The method is mandatory on create of grants, 
     * but not-allowed on update of grants.
     * @param 
     */
    public void del(final Grant grant) {
        this.grants.remove(grant);
    }
   
}
