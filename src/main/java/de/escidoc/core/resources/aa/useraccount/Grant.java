package de.escidoc.core.resources.aa.useraccount;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.XLinkAutonomous;
import de.escidoc.core.resources.common.reference.GrantRef;
import de.escidoc.core.resources.common.reference.Referenceable;
import de.escidoc.core.resources.om.GenericResource;

/**
 * Grant.
 * 
 * @author ROF, SWA
 * 
 */
@JiBX
public class Grant extends GenericResource
    implements XLinkAutonomous, Referenceable<GrantRef> {

    private GrantProperties grantProperties;

    /**
     * 
     */
    public Grant() {
        setResourceType(RESOURCE_TYPE.Grant);
    }

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

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.XLinkAutonomous#genXLink()
     */
    @Override
    public void genXLink() {
        genOwnXLinkHref();

        if (grantProperties != null) {

            genXLinkHref(grantProperties.getCreatedBy(),
                RESOURCE_TYPE.UserAccount, null);
            genXLinkHref(grantProperties.getModifiedBy(),
                RESOURCE_TYPE.UserAccount, null);
            genXLinkHref(grantProperties.getGrantedTo(),
                RESOURCE_TYPE.UserAccount, null);
            genXLinkHref(grantProperties.getRevokedBy(),
                RESOURCE_TYPE.UserAccount, null);
            genXLinkHref(grantProperties.getRole(), RESOURCE_TYPE.Role, null);

            /**
             * AssignedOn cannot be generated if ResourceType is null or not a
             * root resource.
             */
            if (grantProperties.getAssignedOn() != null
                && grantProperties.getAssignedOn().getObjid() != null
                && grantProperties.getAssignedOn().getResourceType() != null
                && grantProperties
                    .getAssignedOn().getResourceType().isRootResource()) {

                genXLinkHref(grantProperties.getAssignedOn(), grantProperties
                    .getAssignedOn().getResourceType(), null);
            }
        }
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
}
