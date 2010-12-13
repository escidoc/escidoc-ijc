package de.escidoc.core.resources.common;

import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;

import org.joda.time.DateTime;

/**
 * 
 * @author SWA
 * 
 */
public class TaskParam {

    private DateTime lastModificationDate = null;

    private String comment = null;

    private String pid = null;

    private URL url = null;

    private String password = null;

    private String username = null;

    private Collection<Filter> filters = new LinkedList<Filter>();

    private Collection<String> ids = new LinkedList<String>();

    private boolean keepInSync = false;

    /**
     * @return the lastModificationDate
     */
    public DateTime getLastModificationDate() {
        return this.lastModificationDate;
    }

    /**
     * @param lastModificationDate
     *            the lastModificationDate to set
     */
    public void setLastModificationDate(final DateTime lastModificationDate) {

        this.lastModificationDate = lastModificationDate;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment
     *            the comment to set
     */
    public void setComment(final String comment) {
        this.comment = comment;
    }

    /**
     * @return the url
     */
    public URL getUrl() {
        return url;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(final URL url) {
        this.url = url;
    }

    /**
     * @return the pid
     */
    public String getPid() {
        return pid;
    }

    /**
     * @param pid
     *            the pid to set
     */
    public void setPid(final String pid) {
        this.pid = pid;
    }

    /**
     * @return the filters
     */
    public Collection<Filter> getFilters() {
        return filters;
    }

    /**
     * @param filters
     *            the filters to set
     */
    public void setFilters(final Collection<Filter> filters) {
        this.filters = filters;
    }

    /**
     * Get password.
     * 
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set Password.
     * 
     * @param password
     *            Password.
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Set Username.
     * 
     * @param username
     *            Name of user.
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Get Username.
     * 
     * @return Username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get collection of resource references.
     * 
     * @return resource references
     */
    public Collection<String> getResourceRefs() {
        return this.ids;
    }

    /**
     * Add resource reference.
     * 
     * @param objid
     *            The objid of the new resoure reference
     */
    public void addResourceRef(final String objid) {
        this.ids.add(objid);
    }

    /**
     * @param keepInSync
     *            the keepInSync to set
     */
    public void setKeepInSync(boolean keepInSync) {
        this.keepInSync = keepInSync;
    }

    /**
     * @return the keepInSync
     */
    public boolean isKeepInSync() {
        return keepInSync;
    }
}
