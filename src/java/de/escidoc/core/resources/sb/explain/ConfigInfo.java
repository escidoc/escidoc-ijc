package de.escidoc.core.resources.sb.explain;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Read-only class.
 * 
 * This class is a representation of the configInfo of the response of an
 * explain request.
 * 
 * This class may be initialized either by a SOAP response instance or by JiBX
 * if and only if the REST protocol is being used for the explain request.
 * 
 * @author ?, MVO
 * 
 */
public class ConfigInfo {
    Collection<DefaultSetting> defaultSettings =
        new LinkedList<DefaultSetting>();

    /**
     * Constructor for JiBX
     */
    protected ConfigInfo() {

    }

    /**
     * 
     * @return
     */
    public Collection<DefaultSetting> getDefaultSettingss() {
        return this.defaultSettings;
    }
}
