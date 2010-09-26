package de.escidoc.core.resources.interfaces.common;

public interface LatestReleaseInterface extends LatestVersionInterface {

    /**
     * @return The release pid.
     */
    String getPid();

    /**
     * Set the new release pid.
     * 
     * @param pid
     *            The new release pid.
     */
    void setPid(String pid);

}
