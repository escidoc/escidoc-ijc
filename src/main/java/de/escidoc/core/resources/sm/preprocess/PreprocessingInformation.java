/**
 * 
 */
package de.escidoc.core.resources.sm.preprocess;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

import de.escidoc.core.annotations.JiBX;

/**
 * @author MVO
 * 
 */
@JiBX
public class PreprocessingInformation {

    private DateTime startDate;

    private DateTime endDate;

    private static final DateTimeFormatter formatter =
        new DateTimeFormatterBuilder()
            .appendYear(4, 4).appendLiteral('-').appendMonthOfYear(2).appendLiteral('-').appendDayOfMonth(2)
            .toFormatter();

    /**
     * Default constructor.
     */
    public PreprocessingInformation() {
    }

    /**
     * 
     * @param startDate
     */
    public PreprocessingInformation(final DateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * 
     * @param startDate
     * @param endDate
     */
    public PreprocessingInformation(final DateTime startDate, final DateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * @return the startDate
     */
    public final DateTime getStartDate() {
        return startDate;
    }

    /**
     * @param startDate
     *            the startDate to set
     */
    public final void setStartDate(final DateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public final DateTime getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     *            the endDate to set
     */
    public final void setEndDate(final DateTime endDate) {
        this.endDate = endDate;
    }

    /**
     * Serialization method for JiBX.
     * 
     * @param date
     * @return
     */
    public static final String serialize(final DateTime value) {
        if (value == null)
            return "";
        return formatter.print(value);
    }

    /**
     * Deserialization method for JiBX.
     * 
     * @param date
     * @return
     */
    public static final DateTime deserialize(final String text) {
        if (text == null || text.isEmpty())
            return null;

        return formatter.parseDateTime(text);
    }
}