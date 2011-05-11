/**
 * 
 */
package de.escidoc.core.common;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

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
        return date.toDateTime(DateTimeZone.UTC);
    }
}
