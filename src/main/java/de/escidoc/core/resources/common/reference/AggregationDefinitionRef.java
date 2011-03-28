/**
 * 
 */
package de.escidoc.core.resources.common.reference;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.ResourceType;

/**
 * @author MVO
 * 
 */
@JiBX
public class AggregationDefinitionRef extends Reference {

    @JiBX
    @SuppressWarnings("unused")
    private AggregationDefinitionRef() {
        this(null);
    }

    public AggregationDefinitionRef(final String objid) {
        super(objid);
    }

    public AggregationDefinitionRef(final String xLinkHref,
        final String xLinkTitle) {
        super(xLinkHref, xLinkTitle);
    }

    @Override
    public ResourceType getResourceType() {
        return ResourceType.AGGREGATION_DEFINITION;
    }
}
