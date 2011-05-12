/**
 * 
 */
package de.escidoc.core.common;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.ISODateTimeFormat;

/**
 * @author Marko Voß
 * 
 */
public class DateTimeUtility {

    /**
     * @param date
     * @return
     */
    public static final DateTime normalize(final DateTime date) {
        if (date == null)
            return null;
        return ISODateTimeFormat
            .dateTime().withZone(DateTimeZone.UTC)
            .parseDateTime(date.toString());
    }
}