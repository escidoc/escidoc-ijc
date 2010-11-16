/**
 * 
 */
package de.escidoc.core.resources.common.reference;

import de.escidoc.core.resources.ResourceType;

/**
 * @author MVO
 * 
 */
public class AggregationDefinitionRef extends Reference {

    public AggregationDefinitionRef() {
        this(null, null);
    }

    public AggregationDefinitionRef(final String objid) {
        this(objid, null);
    }

    public AggregationDefinitionRef(final String objid, final String xLinkTitle) {
        super(objid, ResourceType.AggregationDefinition, xLinkTitle);
    }
}
