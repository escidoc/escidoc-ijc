package de.escidoc.core.resources.interfaces.common;

import de.escidoc.core.resources.common.reference.UserAccountRef;

public interface Version extends LatestRelease {
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

}
