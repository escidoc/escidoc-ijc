package de.escidoc.core.resources.sm;

/**
 * @author MRO
 * 
 */
public abstract class Parameter<T> {

    private T value;

    /**
     * Package private no-argument constructor for JiBX only.
     */
    Parameter() {

    }

    /**
     * 
     * @param value
     */
    public Parameter(final T value) {
        if (value == null)
            throw new IllegalArgumentException("value must not be null.");

        this.value = value;
    }

    /**
     * 
     * @return
     */
    public T getValue() {
        return value;
    }

    public abstract ParameterType getParameterType();
}
