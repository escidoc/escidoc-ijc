package de.escidoc.core.resources.interfaces.common;

import org.joda.time.DateTime;

import de.escidoc.core.resources.ResourceRef;

/**
 * Interface for Component Properties.
 * 
 * @author SWA
 * 
 */
public interface ComponentPropertiesInterface {

    // FIXME incomplete!

    /**
     * @return The creation date.
     */
    DateTime getCreationDate();

    /**
     * Set the creation date.
     * 
     * @param creationDate
     *            The new creation date.
     */
    void setCreationDate(final DateTime creationDate);

    /**
     * @return The creator.
     */
    ResourceRef getCreatedBy();

    /**
     * Set the creator.
     * 
     * @param createdBy
     *            The link to the new creator.
     */
    void setCreatedBy(final ResourceRef createdBy);

    /**
     * @return The creation date.
     */
    DateTime getLastModificationDate();

    /**
     * Set the last modification date.
     * 
     * @param lastModificationDate
     *            The new last modification date.
     */
    void setLastModificationDate(final DateTime lastModificationDate);

    /**
     * @return The creator.
     */
    ResourceRef getModifiedBy();

    /**
     * Set the creator.
     * 
     * @param createdBy
     *            The link to the new creator.
     */
    void setModifiedBy(final ResourceRef createdBy);

    /**
     * Get the name of the Context.
     * 
     * @return The Context name.
     */
    String getName();

    /**
     * Set the name of the Context.
     * 
     * @param name
     *            The new name of the Context.
     */
    void setName(final String name);
}
