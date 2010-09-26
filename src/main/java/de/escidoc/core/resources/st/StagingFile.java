package de.escidoc.core.resources.st;

import java.net.MalformedURLException;
import java.net.URL;

import org.joda.time.DateTime;

/**
 * Staging File.
 * 
 * @author SWA
 * 
 */
public class StagingFile {

    private String href;

    private String baseUrl;

    private DateTime lastModificationDate;

    public URL getURL() throws MalformedURLException {
        return new URL(this.baseUrl + this.href);
    }

    public void setHref(final String href) {
        this.href = href;
    }

    public String getHref() {
        return href;
    }

    public void setBaseUrl(final String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setLastModificationDate(final DateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public DateTime getLastModificationDate() {
        return lastModificationDate;
    }
}
