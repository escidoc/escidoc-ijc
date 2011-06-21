package de.escidoc.core.resources.sm;

import static de.escidoc.core.common.Precondition.checkNotNull;
import de.escidoc.core.annotations.JiBX;

/**
 * @author MRO
 * 
 */
@JiBX
public abstract class Parameter<T> {

    private String name;

    private T value;

    /**
     * 
     */
    @JiBX
    protected Parameter() {

    }

    /**
     * @param name
     * @param value
     */
    public Parameter(final String name, final T value) {
        this.name = checkNotNull(name);
        this.value = checkNotNull(value);
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

    /**
     * @return
     */
    public abstract ParameterType getParameterType();
}
