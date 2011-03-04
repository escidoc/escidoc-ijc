/**
 * 
 */
package de.escidoc.core.resources.common.properties.interfaces;

import de.escidoc.core.resources.common.properties.PublicStatus;

/**
 * @author MVO
 * 
 */
public interface PublicStatusProperties {

    /**
     * @return
     */
    PublicStatus getPublicStatus();

    /**
     * @param status
     */
    void setPublicStatus(PublicStatus status);

    /**
     * @return
     */
    String getPublicStatusComment();

    /**
     * @param comment
     */
    void setPublicStatusComment(String comment);
}
