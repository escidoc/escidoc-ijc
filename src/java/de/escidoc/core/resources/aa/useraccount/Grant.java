package de.escidoc.core.resources.aa.useraccount;

import de.escidoc.core.resources.om.GenericResource;

/**
 * Grant.
 * 
 * @author ROF, SWA
 * 
 */
public class Grant extends GenericResource {

    private GrantProperties grantProperties;

    /**
     * Get Grant Properties.
     * 
     * @return GrantProperties
     */
    public GrantProperties getGrantProperties() {
        return grantProperties;
    }

    /**
     * Set Grant Properties.
     * 
     * @param grantProperties
     *            GrantProperties
     */
    public void setGrantProperties(final GrantProperties grantProperties) {
        this.grantProperties = grantProperties;
    }
}
