package de.escidoc.core.resources.interfaces.common;

import de.escidoc.core.resources.common.reference.UserAccountRef;

public interface VersionInterface extends LatestReleaseInterface {
    /**
     * @return The status.
     */
    String getStatus();

    /**
     * Set the new status.
     * 
     * @param status
     *            The new status.
     */
    void setStatus(String status);

    /**
     * @return The modifier.
     */
    UserAccountRef getModifiedBy();

    /**
     * Set the modifier.
     * 
     * @param modifiedBy
     *            The link to the new modifier.
     */
    void setModifiedBy(UserAccountRef modifiedBy);

    /**
     * @return The comment.
     */
    String getComment();

    /**
     * Set the new comment.
     * 
     * @param comment
     *            The new comment.
     */
    void setComment(String comment);

    /**
     * @return The object pid.
     */
    String getPid();

    /**
     * Set the new object pid.
     * 
     * @param pid
     *            The new object pid.
     */
    void setPid(String pid);

}