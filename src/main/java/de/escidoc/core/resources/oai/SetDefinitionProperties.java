package de.escidoc.core.resources.oai;

import static de.escidoc.core.common.Precondition.checkNotNull;

import org.joda.time.DateTime;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.common.properties.interfaces.CreateProperties;
import de.escidoc.core.resources.common.properties.interfaces.DescriptionProperties;
import de.escidoc.core.resources.common.properties.interfaces.ModifyProperties;
import de.escidoc.core.resources.common.properties.interfaces.NameProperties;
import de.escidoc.core.resources.common.reference.UserAccountRef;

/**
 * @author Marko Voß
 * 
 */
@JiBX
public class SetDefinitionProperties
    implements CreateProperties, ModifyProperties, NameProperties,
    DescriptionProperties {

    private UserAccountRef createdBy;

    private DateTime creationDate;

    private UserAccountRef modifiedBy;

    private String name;

    private String description;

    /**
     * JiBX-Constructor
     */
    @SuppressWarnings("unused")
    @JiBX
    private SetDefinitionProperties() {
    }

    /**
     * @param name
     */
    public SetDefinitionProperties(final String name) {
        this(name, null);
    }

    /**
     * @param name
     * @param description
     */
    public SetDefinitionProperties(final String name, final String description) {
        setName(name);
        setDescription(description);
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
        checkNotNull(name);
        this.name = name;
    }

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
    public void setModifiedBy(final UserAccountRef ref) {
        this.modifiedBy = ref;
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
        return createdBy;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.CreateProperties
     * #setCreatedBy(de.escidoc.core.resources.common.reference.UserAccountRef)
     */
    @Override
    public void setCreatedBy(final UserAccountRef ref) {
        this.createdBy = ref;
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
        return creationDate;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.CreateProperties
     * #setCreationDate(org.joda.time.DateTime)
     */
    @Override
    public void setCreationDate(final DateTime date) {
        this.creationDate = date;
    }
}