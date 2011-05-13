package de.escidoc.core.resources.sm.ad;

import de.escidoc.core.resources.ResourceList;

/**
 * @author MRO
 * 
 */
public class AggregationDefinitionList extends ResourceList<AggregationDefinition> {

    private static final String AGGREGATION_DEFINITIONS_PATH = "/aggregation-definitions";

    /**
     * 
     */
    private static final long serialVersionUID = -6330314341358580858L;

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.XLinkResourceList#getListXLinkPath()
     */
    @Override
    protected String getListXLinkPath() {
        return AGGREGATION_DEFINITIONS_PATH;
    }
}