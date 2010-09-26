package de.escidoc.core.resources.interfaces.common;

import org.joda.time.DateTime;

/**
 * LastestVersion Properties Interface.
 * 
 * @author SWA
 * 
 */
public interface LatestVersionInterface {

    /**
     * @return Get the number of this version.
     */
    String getNumber();

    /**
     * Set the number of this version.
     * 
     * @param number
     *            The new number for this version.
     */
    void setNumber(final String number);

    DateTime getDate();

    /**
     * Set the creation date of this version.
     * 
     * @param date
     *            The creation date of this version.
     */
    void setDate(final DateTime date);

}
