package de.escidoc.core.common.jibx;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Set of JiBX de-/serializer for custom data types.
 * 
 * @author SWA
 * 
 */
public class CustomConverter {

    /**
     * Serialize URI to string.
     * 
     * @param uri
     *            The URI
     * @return string representation of URI
     */
    public static String serializeURI(final URI uri) {
        return uri.toString();
    }

    /**
     * Deserialize text to URI.
     * 
     * @param text
     *            The string representation of URI.
     * @return URI
     */
    public static URI deserializeURI(final String text) {

        if (text == null) {
            return null;
        }

        return URI.create(text);
    }

    /**
     * Serialize URL to string.
     * 
     * @param url
     *            The URL
     * @return string representation of URL
     */
    public static String serializeURL(final URL url) {
        return url.toString();
    }

    /**
     * Deserialize text to URL.
     * 
     * @param text
     *            The string representation of URL.
     * @return URL
     * @throws MalformedURLException
     *             Thrown if text is not a valid URL
     */
    public static URL deserializeURL(final String text)
        throws MalformedURLException {

        if (text == null) {
            return null;
        }

        return new URL(text);
    }

}
