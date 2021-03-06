package de.escidoc.core.resources.aa.useraccount;

import org.joda.time.DateTime;

import de.escidoc.core.common.DateTimeUtility;

/**
 * User Account Preference.
 * 
 * @author SWA
 */
public class Preference {

    private String name;

    private String value;

    private DateTime lastModificationDate;

    /**
     * User Account Preference.
     */
    public Preference() {

    }

    /**
     * User Account Preference.
     * 
     * @param name
     *            Name/key of preference
     * @param value
     *            Preference value
     */
    public Preference(final String name, final String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Set name.
     * 
     * @param name
     *            Name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Get name.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set value.
     * 
     * @param value
     *            value
     */
    public void setValue(final String value) {
        this.value = value;
    }

    /**
     * Get value
     * 
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * Get last modification date of the resource.
     * 
     * @return last-modification-date
     */
    public DateTime getLastModificationDate() {
        return this.lastModificationDate;
    }

    /**
     * Set last-modification-date.
     * 
     * @param lmd
     *            last-modification-date
     */
    public void setLastModificationDate(final DateTime lmd) {
        this.lastModificationDate = DateTimeUtility.normalize(lmd);
    }
}