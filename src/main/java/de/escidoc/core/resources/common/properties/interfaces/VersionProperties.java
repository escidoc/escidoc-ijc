/**
 * 
 */
package de.escidoc.core.resources.common.properties.interfaces;

import de.escidoc.core.resources.interfaces.common.LatestRelease;
import de.escidoc.core.resources.interfaces.common.LatestVersion;
import de.escidoc.core.resources.interfaces.common.Version;

/**
 * @author MVO
 * 
 */
public interface VersionProperties {

    /**
     * @return
     */
    Version getVersion();

    /**
     * @param version
     */
    void setVersion(Version version);

    /**
     * @return
     */
    LatestVersion getLatestVersion();

    /**
     * @param version
     */
    void setLatestVersion(LatestVersion version);

    /**
     * @return
     */
    LatestRelease getLatestRelease();

    /**
     * @param version
     */
    void setLatestRelease(LatestRelease version);
}