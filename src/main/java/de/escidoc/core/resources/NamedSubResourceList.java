/**
 * 
 */
package de.escidoc.core.resources;

/**
 * @author Marko Voß
 * 
 */
public abstract class NamedSubResourceList<E extends NamedSubResource> extends XLinkResourceList<E> {

    /**
     * 
     */
    private static final long serialVersionUID = -2260136681238295873L;

    /**
     * Get a AdminDescriptor.
     * 
     * @param name
     *            The name of the AdminDescriptor.
     * @return AdminDescriptor object.
     */
    public E get(final String name) {
        for (E element : this) {
            if (element.getName().equals(name)) {
                return element;
            }
        }
        return null;
    }

    /**
     * 
     * @param name
     *            The name of the AdminDescriptor.
     */
    public void del(final String name) {
        for (E element : this) {
            if (element.getName().equals(name)) {
                this.remove(element);
                break;
            }
        }
    }
}