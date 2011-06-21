package de.escidoc.core.resources.sm.ad;

import java.util.Collection;
import java.util.LinkedList;

import de.escidoc.core.annotations.JiBX;

/**
 * @author MRO
 * 
 */
@JiBX
public class Index {

    private String name;

    private Collection<String> fieldNames;

    @JiBX
    @SuppressWarnings("unused")
    private Index() {

    }

    /**
     * 
     * @param name
     * @param field
     */
    public Index(final String name, final Collection<String> fieldNames) {
        if (name == null)
            throw new IllegalArgumentException("name must not be null.");
        if (fieldNames == null)
            throw new IllegalArgumentException("fieldNames must not be null.");

        this.name = name;
        this.fieldNames = fieldNames;
    }

    /**
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return
     */
    public Collection<String> getFieldNames() {
        if (fieldNames == null) {
            fieldNames = new LinkedList<String>();
        }
        return fieldNames;
    }
}
