package de.escidoc.core.resources.sm;

import org.joda.time.DateTime;

/**
 * @author MRO
 * 
 */
public class DateParameter extends Parameter<DateTime> {

    private static final ParameterType TYPE = ParameterType.date;

    /**
     * Constructor for JiBX only.
     */
    @SuppressWarnings("unused")
    private DateParameter() {
    }

    /**
     * 
     * @param date
     */
    public DateParameter(final DateTime date) {
        super(date);
    }

    @Override
    public ParameterType getParameterType() {
        return ParameterType.date;
    }
}