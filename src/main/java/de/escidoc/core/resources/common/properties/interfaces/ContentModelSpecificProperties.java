/**
 * 
 */
package de.escidoc.core.resources.common.properties.interfaces;

import de.escidoc.core.resources.common.properties.ContentModelSpecific;

/**
 * @author MVO
 * 
 */
public interface ContentModelSpecificProperties {

    /**
     * @return
     */
    ContentModelSpecific getContentModelSpecific();

    /**
     * @param cms
     */
    void setContentModelSpecific(ContentModelSpecific cms);
}