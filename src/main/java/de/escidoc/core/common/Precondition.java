/**
 * 
 */
package de.escidoc.core.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jibx.runtime.JiBXException;

/**
 * @author MVO
 * 
 */
public final class Precondition {

    protected static final String EX_INVALID_OBJ_TYPE = "Unexpected object type. Expected: ";

    /**
     * No instance allowed.
     */
    private Precondition() {

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
        return checkNotNull(param, null);
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

        if (param == null) {
            if (errorMsg == null || errorMsg.isEmpty())
                throw new IllegalArgumentException("CheckNotNull failed: The specified argument must not be null.");
            else
                throw new IllegalArgumentException(errorMsg);
        }
        return param;
    }

    /**
     * @param value
     * @return
     */
    public static final String checkNotEmpty(final String value) {
        return checkNotEmpty(value, null);
    }

    /**
     * @param value
     * @param errorMsg
     * @return
     */
    public static final String checkNotEmpty(final String value, final String errorMsg) {

        if (checkNotNull(value).isEmpty()) {
            if (errorMsg == null || errorMsg.isEmpty())
                throw new IllegalArgumentException(
                    "checkNotEmpty failed: The specified java.lang.String should not be empty.");
            else
                throw new IllegalArgumentException(errorMsg);
        }
        return value;
    }

    /**
     * @param <T>
     * @param type
     * @param obj
     * @return
     * @throws JiBXException
     */
    public static final <T> T checkObject(final Class<T> type, final Object obj) {
        return checkObject(type, obj, null);
    }

    /**
     * @param <T>
     * @param type
     * @param obj
     * @param msg
     * @return
     */
    @SuppressWarnings("unchecked")
    public static final <T> T checkObject(final Class<T> type, final Object obj, final String errorMsg) {

        checkNotNull(type);
        checkNotNull(obj);

        if (!type.isAssignableFrom(obj.getClass())) {
            if (errorMsg == null)
                throw new IllegalArgumentException(EX_INVALID_OBJ_TYPE + type);
            else
                throw new IllegalArgumentException(errorMsg);
        }

        return (T) obj;
    }

    /**
     * @param pattern
     * @param value
     */
    public static final String validateString(final Pattern pattern, final String value) {

        checkNotNull(pattern);
        checkNotNull(value);

        Matcher m = pattern.matcher(value);
        if (m.find()) {
            return value;
        }
        else {
            throw new IllegalArgumentException("The string does not match the pattern: " + pattern.toString());
        }
    }
}
