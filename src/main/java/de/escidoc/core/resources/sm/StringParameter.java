package de.escidoc.core.resources.sm;

import de.escidoc.core.annotations.JiBX;

/**
 * @author MRO
 * 
 */
@JiBX
public class StringParameter extends Parameter<String> {

    @JiBX
    @SuppressWarnings("unused")
    private StringParameter() {
    }

    /**
     * @param value
     */
    public StringParameter(final String name, final String value) {
        super(name, value);
    }

    @Override
    public ParameterType getParameterType() {
        return ParameterType.STRING;
    }
}
