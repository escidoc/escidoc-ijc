package de.escidoc.core.resources.sm;

import org.joda.time.DateTime;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.common.DateTimeUtility;

/**
 * @author MRO
 * 
 */
@JiBX
public class DateParameter extends Parameter<DateTime> {

    @JiBX
    @SuppressWarnings("unused")
    private DateParameter() {
    }

    /**
     * 
     * @param date
     */
    public DateParameter(final String name, final DateTime date) {
        super(name, DateTimeUtility.normalize(date));
    }

    @Override
    public ParameterType getParameterType() {
        return ParameterType.DATE;
    }
}