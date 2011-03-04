/**
 * 
 */
package de.escidoc.core.resources.common.properties.interfaces;

import de.escidoc.core.resources.common.reference.ContentModelRef;
import de.escidoc.core.resources.common.reference.ContextRef;

/**
 * @author MVO
 * 
 */
public interface ContextProperties {

    /**
     * @return
     */
    ContextRef getContext();

    /**
     * @param context
     */
    void setContext(ContextRef context);

    /**
     * @return
     */
    ContentModelRef getContentModel();

    /**
     * @param contentModel
     */
    void setContentModel(ContentModelRef contentModel);
}
