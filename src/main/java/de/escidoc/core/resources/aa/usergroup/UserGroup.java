/**
 * 
 */
package de.escidoc.core.resources.aa.usergroup;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.GenericResource;
import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.common.reference.Referenceable;
import de.escidoc.core.resources.common.reference.UserGroupRef;

/**
 * @author MVO
 * 
 */
@JiBX
public class UserGroup extends GenericResource implements Referenceable<UserGroupRef> {

    private UserGroupProperties properties;

    private Selectors selectors;

    /**
     * 
     */
    public UserGroup() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.Resource#getResourceType()
     */
    @Override
    public ResourceType getResourceType() {
        return ResourceType.USERGROUP;
    }

    /**
     * @param properties
     *            the properties to set
     */
    public void setProperties(final UserGroupProperties properties) {
        this.properties = properties;
    }

    /**
     * @return the properties
     */
    public UserGroupProperties getProperties() {
        if (properties == null)
            properties = new UserGroupProperties();
        return properties;
    }

    /**
     * @param selectors
     *            the selectors to set
     */
    public void setSelectors(final Selectors selectors) {
        this.selectors = selectors;
    }

    /**
     * @return the selectors
     */
    public Selectors getSelectors() {
        if (selectors == null)
            selectors = new Selectors();
        return selectors;
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
        /*
         * Properties and selectors do not support xlink but each selector does
         * on its ownm therefore do nothing here.
         */
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.reference.Referenceable#getReference()
     */
    @Override
    public UserGroupRef getReference() {
        return new UserGroupRef(getObjid());
    }
}