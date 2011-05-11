package de.escidoc.core.resources.interfaces.common;

import org.joda.time.DateTime;

import de.escidoc.core.resources.XLinkType;

/**
 * LastestVersion Properties Interface.
 * 
 * @author SWA
 * 
 */
public interface LatestVersion {

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

    /**
     * @return
     */
    DateTime getDate();

    /**
     * Set the creation date of this version.
     * 
     * @param date
     *            The creation date of this version.
     */
    void setDate(final DateTime date);

    String getObjid();

    String getXLinkHref();

    String getXLinkTitle();

    XLinkType getXLinkType();
}
