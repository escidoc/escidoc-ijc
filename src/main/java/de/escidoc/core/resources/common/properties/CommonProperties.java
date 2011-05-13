/**
 * 
 */
package de.escidoc.core.resources.common.properties;

import org.joda.time.DateTime;

import de.escidoc.core.common.DateTimeUtility;
import de.escidoc.core.resources.XLinkResource;
import de.escidoc.core.resources.common.properties.interfaces.CreateProperties;
import de.escidoc.core.resources.common.reference.UserAccountRef;

/**
 * @author MVO
 * 
 */
public abstract class CommonProperties extends XLinkResource implements CreateProperties {

    private DateTime creationDate;

    private UserAccountRef createdBy;

    private static final String PROPERTIES_PATH = "/properties";

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.CreateProperties
     * #getCreationDate()
     */
    @Override
    public DateTime getCreationDate() {
        return this.creationDate;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.CreateProperties
     * #setCreationDate(org.joda.time.DateTime)
     */
    @Override
    public void setCreationDate(final DateTime creationDate) {
        this.creationDate = DateTimeUtility.normalize(creationDate);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.CreateProperties
     * #getCreatedBy()
     */
    @Override
    public UserAccountRef getCreatedBy() {
        return this.createdBy;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.CreateProperties
     * #setCreatedBy(de.escidoc.core.resources.common.reference.UserAccountRef)
     */
    @Override
    public void setCreatedBy(final UserAccountRef createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @param parentPath
     */
    private void setPropertiesXLinkHref(final String parentPath) {
        if (getXLinkHref() == null && parentPath != null) {
            setXLinkHref(parentPath + PROPERTIES_PATH);
        }
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
        setPropertiesXLinkHref(parentPath);
    }
}