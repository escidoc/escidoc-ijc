package de.escidoc.core.resources.sb.explain;

import java.util.Collection;
import java.util.LinkedList;

public class ConfigInfo {
    Collection<DefaultSetting> defaultSettings =
        new LinkedList<DefaultSetting>();

    public Collection<DefaultSetting> getDefaultSettingss() {
        return this.defaultSettings;
    }

    public void setDefaultSettings(final Collection<DefaultSetting> defaults) {
        this.defaultSettings = defaults;
    }

    public void addDefaultSetting(final DefaultSetting defaultSetting) {
        this.defaultSettings.add(defaultSetting);
    }

}
