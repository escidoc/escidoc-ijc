/**
 * 
 */
package de.escidoc.core.resources.aa.usergroup;

import static de.escidoc.core.common.Precondition.checkNotNull;
import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.aa.useraccount.Attribute;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.common.reference.Reference;

/**
 * @author MVO
 * 
 */
@JiBX
public class Selector extends Reference {

    private String content;

    private String name;

    private SelectorType type;

    /**
     * Use this value for the selector name, if the selector type equals
     * {@link SelectorType#INTERNAL}
     */
    public static final String NAME_INTERNAL_USER_ACCOUNT = "user-account";

    /**
     * Use this value for the selector name, if the selector type equals
     * {@link SelectorType#INTERNAL}
     */
    public static final String NAME_INTERNAL_USER_GROUP = "user-group";

    /**
     * @param content
     * @param name
     * @param type
     */
    public Selector(final String content, final String name, final SelectorType type) {
        setContent(content);
        setName(name);
        setType(type);
    }

    /**
     * JiBX Constructor
     */
    @JiBX
    protected Selector() {

    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * If the selector type equals {@link SelectorType#INTERNAL} the following
     * values are valid:<br>
     * <ul>
     * <li>The ID of a {@link UserAccount} if and only if the name of the
     * selector equals {@link Selector#NAME_INTERNAL_USER_ACCOUNT}</li>
     * <li>The ID of a {@link UserGroup} if and only if the name of the selector
     * equals {@link Selector#NAME_INTERNAL_USER_GROUP}</li>
     * </ul>
     * <br>
     * If the selector type equals {@link SelectorType#USER_ATTRIBUTE} the value
     * has to be the value of an existing {@link Attribute}. Not that multiple
     * user attributes can have the same name but different values.
     * 
     * @param content
     *            the value to set
     */
    public void setContent(final String content) {
        checkNotNull(content);
        this.content = content;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * If the selector type equals {@link SelectorType#INTERNAL} the following
     * names are valid:<br>
     * <ul>
     * <li>{@link Selector#NAME_INTERNAL_USER_ACCOUNT}</li>
     * <li>{@link Selector#NAME_INTERNAL_USER_GROUP}</li>
     * </ul>
     * <br>
     * If the selector type equals {@link SelectorType#USER_ATTRIBUTE} the name
     * has to equal the name of an existing {@link Attribute}.
     * 
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        checkNotNull(name);
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
        checkNotNull(type);
        this.type = type;
    }
}