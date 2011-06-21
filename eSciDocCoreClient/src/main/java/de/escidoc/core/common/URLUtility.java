/**
 * 
 */
package de.escidoc.core.common;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;

/**
 * @author MVO
 * 
 */
public final class URLUtility {

    private static final Logger LOG = Logger.getLogger(URLUtility.class);

    /**
     * No instance allowed.
     */
    private URLUtility() {

    }

    /**
     * 
     * @param url
     * @return
     */
    public static final URL unifyAddress(final URL url) {

        String tmpServUrl =
            url.getProtocol() + "://" + url.getHost() + (url.getPort() >= 0 ? ":" + url.getPort() : "") + url.getPath();
        tmpServUrl = tmpServUrl.replaceAll("[/]*$", "");

        URL result = null;
        try {
            result = new URL(tmpServUrl);
        }
        catch (MalformedURLException e) {
            // this should never ever happen.
            LOG.debug("An Exception occured while unifying the URL.", e);
        }
        return result;
    }
}
