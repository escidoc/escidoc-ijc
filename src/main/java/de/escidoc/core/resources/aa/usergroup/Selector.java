/**
 * 
 */
package de.escidoc.core.resources.aa.usergroup;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.Resource;
import de.escidoc.core.resources.ResourceType;

/**
 * @author MVO
 * 
 */
@JiBX
public class Selector extends Resource {

    private String content;

    private String name;

    private SelectorType type;

    /**
     * @param content
     * @param name
     * @param type
     */
    public Selector(final String content, final String name,
        final SelectorType type) {
        this.content = content;
        this.name = name;
        this.type = type;
    }

    @SuppressWarnings("unused")
    @JiBX
    private Selector() {

    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(final String content) {
        this.content = content;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the type
     */
    public SelectorType getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(final SelectorType type) {
        this.type = type;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.Resource#getResourceType()
     */
    @Override
    public ResourceType getResourceType() {
        return null;
    }
}