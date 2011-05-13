/**
 * 
 */
package de.escidoc.core.resources;

/**
 * @author Marko Voß
 * 
 */
public abstract class ResourceList<E extends Resource> extends XLinkResourceList<E> {

    /**
     * 
     */
    private static final long serialVersionUID = 7304761699458136296L;

    /**
     * Returns the {@link Resource} from this list, which has the specified
     * objid or <code>null</code>, if and only if the specified objid is
     * <code>null</code> or there is no element in this list with this objid.
     * 
     * @param componentId
     *            The objid of the Component.
     * @return Component
     */
    public E get(final String objid) {
        if (objid != null) {
            for (E element : this) {
                if (objid.equals(element.getObjid())) {
                    return element;
                }
            }
        }
        return null;
    }

    /**
     * Delete a Component.
     * 
     * @param componentId
     *            The objid of the to delete Component.
     */
    public void del(final String objid) {
        if (objid != null) {
            for (E element : this) {
                if (objid.equals(element.getObjid())) {
                    remove(element);
                    break;
                }
            }
        }
    }
}