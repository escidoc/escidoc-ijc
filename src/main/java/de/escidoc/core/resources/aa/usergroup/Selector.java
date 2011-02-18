/**
 * 
 */
package de.escidoc.core.resources.aa.usergroup;

import static de.escidoc.core.common.Precondition.checkNotNull;
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
     * Enum with mapping between Enum constant and xmlValue, which may be
     * invalid as an enum namen in Java.
     * 
     * @author MVO
     * 
     */
    public enum SelectorType {
        Internal("internal"), UserAttribute("user-attribute");

        private final String xmlName;

        /**
         * @param xmlName
         */
        private SelectorType(final String xmlName) {
            checkNotNull(xmlName);
            this.xmlName = xmlName;
        }

        /**
         * @param string
         * @return
         */
        public static SelectorType getValue(final String string) {
            for (int i = 0; i < SelectorType.values().length; i++) {
                if (SelectorType.values()[i].name().equals(string)
                    || SelectorType.values()[i].xmlName.equals(string))
                    return SelectorType.values()[i];
            }
            return null;
        }

        /**
         * @return
         */
        private String getXmlName() {
            return xmlName;
        }
    }

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

    /**
     * Method for JiBX to map XML values, which include illegal characters in
     * enum names.
     * 
     * @param type
     */
    @SuppressWarnings("unused")
    private void setXmlType(final String type) {
        this.type = SelectorType.getValue(type);
    }

    /**
     * Method used by JiBX, to get the XML representation of the enum value.
     * 
     * @return
     */
    @SuppressWarnings("unused")
    private String getXmlType() {
        return this.type.getXmlName();
    }

    @Override
    public ResourceType getResourceType() {
        return null;
    }
}