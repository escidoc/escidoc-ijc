/**
 * 
 */
package de.escidoc.core.common;

import org.joda.time.DateTime;

/**
 * @author Marko Voß
 * 
 */
public class DateTimeUtility {

    /**
     * No instance allowed.
     */
    private DateTimeUtility() {

    }

    /**
     * FIXME This method does not normalize the date, since there is wrong date
     * handling in the infrastructure, which has to be fixed first.
     * 
     * @param date
     * @return
     */
    public static final DateTime normalize(final DateTime date) {
        if (date == null)
            return null;
        // return
        // ISODateTimeFormat.dateTime().withZone(DateTimeZone.UTC).parseDateTime(date.toString());
        return date;
    }
}