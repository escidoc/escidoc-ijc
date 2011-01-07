/**
 * 
 */
package de.escidoc.core.common;

/**
 * @author MVO
 * 
 */
public class Precondition {

    private Precondition() {
        // no instance
    }

    /**
     * Checks if the specified argument <i>param</i> is null and throws an
     * IllegalArgumentException if and only if the <i>param</i> is null.
     * Otherwise the specified <i>param</i> will be returned.
     * 
     * @param <T>
     * @param param
     * @param errorMsg
     * @return The specified argument <i>param</i> if and only if the
     *         <i>param</i> is not null.
     */
    public static final <T> T checkNotNull(final T param) {
        if (param == null) {
            throw new IllegalArgumentException(
                "CheckNotNull failed: The specified argument must not be null.");
        }
        return param;
    }

    /**
     * Checks if the specified argument <i>param</i> is null and throws an
     * IllegalArgumentException if and only if the <i>param</i> is null using
     * the specified <i>errorMsg</i>. Otherwise the specified <i>param</i> will
     * be returned.
     * 
     * @param <T>
     * @param param
     * @param errorMsg
     * @return The specified argument <i>param</i> if and only if the
     *         <i>param</i> is not null.
     */
    public static final <T> T checkNotNull(final T param, final String errorMsg) {
        if (errorMsg == null || errorMsg.isEmpty())
            return checkNotNull(param);

        if (param == null) {
            throw new IllegalArgumentException(errorMsg);
        }
        return param;
    }
}
