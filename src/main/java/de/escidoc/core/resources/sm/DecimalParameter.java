package de.escidoc.core.resources.sm;

import de.escidoc.core.annotations.JiBX;

/**
 * @author MRO
 * 
 */
@JiBX
public class DecimalParameter extends Parameter<Float> {

    @JiBX
    @SuppressWarnings("unused")
    private DecimalParameter() {

    }

    /**
     * 
     * @param value
     */
    public DecimalParameter(final String name, final Float value) {
        super(name, value);
    }

    @Override
    public ParameterType getParameterType() {
        return ParameterType.decimal;
    }
}