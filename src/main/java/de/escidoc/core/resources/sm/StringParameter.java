package de.escidoc.core.resources.sm;

/**
 * @author MRO
 * 
 */
public class StringParameter extends Parameter<String> {

    /**
     * Constructor for JiBX only.
     */
    @SuppressWarnings("unused")
    private StringParameter() {
    }

    /**
     * @param value
     */
    public StringParameter(final String value) {
        super(value);
    }

    @Override
    public ParameterType getParameterType() {
        return ParameterType.string;
    }
}
