package de.escidoc.core.resources.sm;

/**
 * @author MRO
 * 
 */
public class DecimalParameter extends Parameter<Float> {

    /**
     * Constructor for JiBX only.
     */
    @SuppressWarnings("unused")
    private DecimalParameter() {

    }

    /**
     * 
     * @param value
     */
    public DecimalParameter(final Float value) {
        super(value);
    }

    @Override
    public ParameterType getParameterType() {
        return ParameterType.decimal;
    }
}