package de.escidoc.core.resources.aa.useraccount;

import java.util.Collection;
import java.util.LinkedList;

/**
 * User Account Preferences.
 * 
 * @author SWA
 * 
 */
public class Preferences {

    private Collection<Preference> preferences = new LinkedList<Preference>();

    /**
     * ItemList Factory.
     * 
     * @return ItemList
     */
    public static LinkedList<Preference> preferencesFactory() {
        return new LinkedList<Preference>();
    }

    /**
     * 
     */
    public Preferences() {
    }


    /**
     * 
     * @param key
     * @param value
     */
    public void addPreference(final Preference preference) {

        // TODO check uniqueness
        this.preferences.add(preference);
    }

    /**
     * 
     * @param key
     * @return
     */
    public Collection<Preference> getPreferences() {
        return this.preferences;
    }

    public void deletePreference(final String key) {
        this.preferences.remove(key);
    }

    /**
     * 
     * @param preferences
     */
    public void setPreferences(final Collection<Preference> preferences) {
        this.preferences = preferences;
    }
}
