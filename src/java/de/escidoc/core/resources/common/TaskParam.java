package de.escidoc.core.resources.common;

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

    private String url = null;

    private String password = null;

    private String username = null;

    private Collection<Filter> filters = new LinkedList<Filter>();

    /**
     * TaskParam.
     */
    public TaskParam() {
    }

    /**
     * TaskParam.
     * 
     * @param lastModificationDate
     *            The last-modification-date.
     * @param comment
     *            The comment.
     * @param pid
     *            The Persistent ID.
     * @param url
     *            The URL.
     * @param filters
     *            Filters.
     */
    public TaskParam(final DateTime lastModificationDate, final String comment,
        final String pid, final String url, final Collection<Filter> filters) {

        super();
        this.lastModificationDate = lastModificationDate;
        this.comment = comment;
        this.pid = pid;
        this.url = url;
        this.filters = filters;
    }

    /**
     * 
     * @return Linked List with all filters.
     */
    public static LinkedList<Filter> filtersFactory() {
        return new LinkedList<Filter>();
    }

    /**
     * @return the lastModificationDate
     */
    public DateTime getLastModificationDate() {
        return this.lastModificationDate;
    }

    /**
     * @return the lastModificationDate
     */
    public String getLastModificationDateAsString() {
        if (this.lastModificationDate != null) {
            return this.lastModificationDate.toString();
        }

        return null;
    }

    /**
     * @param lastModificationDate
     *            the lastModificationDate to set
     */
    public void setLastModificationDate(final DateTime lastModificationDate) {

        this.lastModificationDate = lastModificationDate;
    }

    /**
     * @param lastModificationDate
     *            the lastModificationDate to set
     */
    public void setLastModificationDateAsString(
        final String lastModificationDate) {

        this.lastModificationDate = new DateTime(lastModificationDate);
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
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(final String url) {
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
}
