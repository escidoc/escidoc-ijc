package de.escidoc.core.resources.aa.useraccount;

/**
 * User Account Preferences.
 * 
 * @author SWA
 * 
 */
public class Preferences extends UserAccountElements<Preference> {

    private static final long serialVersionUID = -648566962791228436L;

    private static final String PREFERENCES_PATH = "/resources/preferences";

    /**
     * User Account Preferences.
     */
    public Preferences() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.XLinkResourceList#getListXLinkPath()
     */
    @Override
    protected String getListXLinkPath() {
        return PREFERENCES_PATH;
    }
}