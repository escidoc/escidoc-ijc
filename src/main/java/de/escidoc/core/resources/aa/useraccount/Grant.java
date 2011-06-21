package de.escidoc.core.resources.aa.useraccount;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.GenericResource;
import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.common.reference.GrantRef;
import de.escidoc.core.resources.common.reference.Referenceable;

/**
 * Grant.
 * 
 * @author ROF, SWA
 * 
 */
@JiBX
public class Grant extends GenericResource implements Referenceable<GrantRef> {

    private GrantProperties properties;

    /**
     * 
     */
    public Grant() {
    }

    /**
     * @return
     */
    public GrantProperties getProperties() {
        if (properties == null) {
            properties = new GrantProperties();
        }
        return properties;
    }

    /**
     * @param properties
     */
    public void setProperties(final GrantProperties properties) {
        this.properties = properties;
    }

    /**
     * Get Grant Properties.
     * 
     * @return GrantProperties
     * @deprecated Use {@link Grant#getProperties()} instead.
     */
    @Deprecated
    public GrantProperties getGrantProperties() {
        if (properties == null) {
            properties = new GrantProperties();
        }
        return properties;
    }

    /**
     * Set Grant Properties.
     * 
     * @param grantProperties
     *            GrantProperties
     * @deprecated Use {@link Grant#setProperties(GrantProperties)} instead.
     */
    @Deprecated
    public void setGrantProperties(final GrantProperties grantProperties) {
        this.properties = grantProperties;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.reference.Referenceable#getReference()
     */
    @Override
    public GrantRef getReference() {
        return new GrantRef(getObjid());
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.Resource#getResourceType()
     */
    @Override
    public ResourceType getResourceType() {
        return ResourceType.GRANT;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.XLinkAutonomousX#generateXLinkHref(java.lang
     * .String)
     */
    @Override
    public void generateXLinkHref(final String parentPath) {

        if (properties != null) {
            /**
             * AssignedOn cannot be generated if ResourceType is null or not a
             * root resource.
             */
            if (properties.getAssignedOn() != null && properties.getAssignedOn().getObjid() != null
                && properties.getAssignedOn().getResourceType() != null
                && properties.getAssignedOn().getResourceType().isRootResource()) {

                genXLinkHref(properties.getAssignedOn(), null);
            }
        }
    }
}