/**
 * 
 */
package de.escidoc.core.resources.sm.ad;

import de.escidoc.core.annotations.JiBX;

/**
 * @author MVO
 * 
 */
@JiBX
public abstract class Field {

    /**
     * package-private for JiBX, to be able to access this field without using
     * setter method.
     */
    String name;

    @JiBX
    Field() {
    }

    public Field(final String name) {
        if (name == null)
            throw new IllegalArgumentException("name must not be null.");
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    public abstract FieldType getType();
}