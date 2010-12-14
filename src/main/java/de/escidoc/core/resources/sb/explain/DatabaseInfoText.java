/**
 * 
 */
package de.escidoc.core.resources.sb.explain;

/**
 * Read-only class.
 * 
 * This class is a representation of the configInfo of the response of an
 * explain request.
 * 
 * This class may be initialized either by a SOAP response instance or by JiBX
 * if and only if the REST protocol is being used for the explain request.
 * 
 * @author MVO
 * 
 */
public class DatabaseInfoText {

    private String lang;

    private boolean isPrimary;

    private String value;

    protected DatabaseInfoText() {

    }

    protected DatabaseInfoText(final String lang, final boolean isPrimary,
        final String value) {
        this.lang = lang;
        this.isPrimary = isPrimary;
        this.value = value;
    }

    /**
     * @return the lang
     */
    public String getLang() {
        return lang;
    }

    /**
     * @return the isPrimary
     */
    public boolean isPrimary() {
        return isPrimary;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }
}
