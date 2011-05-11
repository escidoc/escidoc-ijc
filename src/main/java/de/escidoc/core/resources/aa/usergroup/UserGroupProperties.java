/**
 * 
 */
package de.escidoc.core.resources.aa.usergroup;

import org.joda.time.DateTime;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.common.DateTimeUtility;
import de.escidoc.core.resources.common.properties.interfaces.ActiveProperties;
import de.escidoc.core.resources.common.properties.interfaces.CreateProperties;
import de.escidoc.core.resources.common.properties.interfaces.DescriptionProperties;
import de.escidoc.core.resources.common.properties.interfaces.ModifyProperties;
import de.escidoc.core.resources.common.properties.interfaces.NameProperties;
import de.escidoc.core.resources.common.properties.interfaces.TypeProperties;
import de.escidoc.core.resources.common.reference.UserAccountRef;

/**
 * @author MVO
 * 
 */
@JiBX
public class UserGroupProperties
    implements CreateProperties, ModifyProperties, NameProperties,
    DescriptionProperties, TypeProperties, ActiveProperties {

    private DateTime creationDate;

    private UserAccountRef createdBy;

    /**
     * optional
     */
    private UserAccountRef modifiedBy;

    /**
     * optional
     */
    private String email;

    private String name;

    private String label;

    /**
     * optional
     */
    private String description;

    /**
     * optional
     */
    private String type;

    /**
     * optional
     */
    private boolean active = true;

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.ModifyProperties
     * #getModifiedBy()
     */
    @Override
    public UserAccountRef getModifiedBy() {
        return modifiedBy;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.ModifyProperties
     * #setModifiedBy(de.escidoc.core.resources.common.reference.UserAccountRef)
     */
    @Override
    public void setModifiedBy(final UserAccountRef modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.NameProperties
     * #getName()
     */
    @Override
    public String getName() {
        return name;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.NameProperties
     * #setName(java.lang.String)
     */
    @Override
    public void setName(final String name) {
        this.name = name;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.DescriptionProperties
     * #getDescription()
     */
    @Override
    public String getDescription() {
        return description;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.DescriptionProperties
     * #setDescription(java.lang.String)
     */
    @Override
    public void setDescription(final String description) {
        this.description = description;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.TypeProperties
     * #getType()
     */
    @Override
    public String getType() {
        return type;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.TypeProperties
     * #setType(java.lang.String)
     */
    @Override
    public void setType(final String type) {
        this.type = type;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.ActiveProperties
     * #isActive()
     */
    @Override
    public boolean isActive() {
        return active;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.ActiveProperties
     * #setActive(boolean)
     */
    @Override
    public void setActive(final boolean active) {
        this.active = active;
    }

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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label
     *            the label to set
     */
    public void setLabel(final String label) {
        this.label = label;
    }
}