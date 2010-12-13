/**
 * 
 */
package de.escidoc.core.resources.common.reference;

/**
 * @author MVO
 * 
 */
public interface Referenceable<T extends Reference> {

    T getReference();
}
