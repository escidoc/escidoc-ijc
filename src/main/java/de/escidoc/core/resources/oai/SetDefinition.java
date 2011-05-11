/**
 * 
 */
package de.escidoc.core.resources.oai;

import static de.escidoc.core.common.Precondition.checkNotNull;
import static de.escidoc.core.common.Precondition.validateString;

import java.util.regex.Pattern;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.GenericResource;
import de.escidoc.core.resources.ResourceType;

/**
 * @author Marko Voß
 * 
 */
@JiBX
public class SetDefinition extends GenericResource {

    private SetDefinitionProperties properties;

    private String specification;

    private String query;

    private static final Pattern SET_SPEC_TYPE = Pattern
        .compile("[A-Za-z0-9\\-_\\.!~\\*'\\(\\)]+");

    /**
     * JiBX-Constructor
     */
    @SuppressWarnings("unused")
    @JiBX
    private SetDefinition() {

    }

    /**
     * @param specification
     * @param query
     */
    public SetDefinition(final String specification, final String query) {
        setSpecification(specification);
        setQuery(query);
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
        return properties;
    }

    /**
     * @param specification
     *            the specification to set
     */
    public void setSpecification(final String specification) {
        this.specification = validateString(SET_SPEC_TYPE, specification);
    }

    /**
     * @return the specification
     */
    public String getSpecification() {
        return specification;
    }

    /**
     * @param query
     *            the query to set
     */
    public void setQuery(final String query) {
        this.query = checkNotNull(query);
    }

    /**
     * @return the query
     */
    public String getQuery() {
        return query;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.Resource#getResourceType()
     */
    @Override
    public ResourceType getResourceType() {
        return ResourceType.SET_DEFINITION;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.XLinkAutonomousX#generateXLinkHref(java.lang
     * .String)
     */
    @Override
    public void generateXLinkHref(final String parentPath) {
        // if (getXLinkHref() != null) {
        //
        // }
    }
}