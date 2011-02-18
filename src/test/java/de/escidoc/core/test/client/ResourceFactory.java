/**
 * 
 */
package de.escidoc.core.test.client;

import static de.escidoc.core.common.Precondition.checkNotNull;

import java.util.Date;

import de.escidoc.core.client.Authentication;

/**
 * @author MVO
 * 
 */
public abstract class ResourceFactory<T> {

    protected final Authentication auth;

    public ResourceFactory(final Authentication auth) {
        checkNotNull(auth);
        this.auth = auth;
    }

    public abstract T createValidInstance() throws Exception;

    protected static String getCurrentTimestamp() {
        return " [" + new Date().toString() + "]";
    }
}
