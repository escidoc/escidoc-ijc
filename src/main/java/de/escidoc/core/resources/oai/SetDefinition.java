/**
 * 
 */
package de.escidoc.core.resources.oai;

import de.escidoc.core.resources.GenericResource;
import de.escidoc.core.resources.ResourceType;

/**
 * @author MVO
 * 
 */
public class SetDefinition extends GenericResource {

    private SetDefinitionProperties properties;

    /**
     * 
     */
    public SetDefinition() {
    }

    /**
     * @param objid
     */
    public SetDefinition(final String objid) {
        super(objid);
    }

    /**
     * @param href
     * @param title
     */
    public SetDefinition(final String href, final String title) {
        super(href, title);
    }

    /**
     * @param objid
     * @param href
     * @param title
     */
    public SetDefinition(final String objid, final String href,
        final String title) {
        super(objid, href, title);
    }

    /**
     * @param properties
     *            the properties to set
     */
    public void setProperties(final SetDefinitionProperties properties) {
        this.properties = properties;
    }

    /**
     * @return the properties
     */
    public SetDefinitionProperties getProperties() {
        if (properties == null)
            properties = new SetDefinitionProperties();
        return properties;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.Resource#getResourceType()
     */
    @Override
    public ResourceType getResourceType() {
        return ResourceType.SetDefinition;
    }
}