package de.escidoc.core.resources.om.context;

import java.util.Collection;
import java.util.LinkedList;

import de.escidoc.core.resources.ResourceRef;

/**
 * 
 * @author SWA
 * 
 */
public class OrganizationalUnitRefs {

    private Collection<ResourceRef> organizationalUnitRefs =
        new LinkedList<ResourceRef>();

    public void setOrganizationalUnitRef(
        final Collection<ResourceRef> organizationalUnitRef) {
        this.organizationalUnitRefs = organizationalUnitRef;
    }

    public void addOrganizationalUnitRef(final ResourceRef organizationalUnitRef) {
        this.organizationalUnitRefs.add(organizationalUnitRef);
    }

}
