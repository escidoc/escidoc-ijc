package de.escidoc.core.resources.sm;

import de.escidoc.core.annotations.JiBX;

/**
 * @author MRO
 * 
 */
@JiBX
public abstract class Parameter<T> {

    private String name;

    private T value;

    @JiBX
    Parameter() {

    }

    /**
     * 
     * @param value
     */
    public Parameter(final String name, final T value) {
        if (name == null)
            throw new IllegalArgumentException("name must not be null.");
        if (value == null)
            throw new IllegalArgumentException("value must not be null.");

        this.name = name;
        this.value = value;
    }

    /**
     * 
     * @return
     */
    public T getValue() {
        return value;
    }

    /**
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    public abstract ParameterType getParameterType();
}
