/**
 * 
 */
package de.escidoc.core.resources;

import static de.escidoc.core.common.Precondition.checkNotNull;

import org.joda.time.DateTime;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.common.DateTimeUtility;
import de.escidoc.core.resources.Resource.Identifier;

/**
 * @author Marko Vo�
 * 
 */
public abstract class NamedSubResource extends XLinkResource {

    NameIdentifier identifier;

    /**
     * The time stamp for optimistic locking.
     */
    private DateTime lastModificationDate;

    /**
     * Create an instance of a named sub resource.
     */
    public NamedSubResource(final String name) {
        identifier = new NameIdentifier(name);
    }

    /**
     * JiBX Constructor
     */
    @JiBX
    protected NamedSubResource() {
    }

    /**
     * @return
     */
    public String getName() {
        return identifier == null ? null : identifier.getName();
    }

    /**
     * @param name
     */
    public void setName(final String name) {
        /*
         * overwrite the identifier if exists because a new name will result in
         * a new xlink from the infrastructure
         */
        identifier = new NameIdentifier(name);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.XLinkResource#getXLinkHref()
     */
    @Override
    public String getXLinkHref() {
        return identifier == null ? null : identifier.getHref();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.XLinkResource#getXLinkTitle()
     */
    @Override
    public String getXLinkTitle() {
        return identifier == null ? null : identifier.getTitle();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.XLinkResource#getXLinkType()
     */
    @Override
    public XLinkType getXLinkType() {
        return identifier == null ? null : identifier.getType();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.XLinkResource#setXLinkHref(java.lang.String)
     */
    @Override
    protected void setXLinkHref(final String xLinkHref) {
        if (identifier == null)
            identifier = new NameIdentifier(xLinkHref, null, null);
        else
            identifier.setHref(xLinkHref);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.XLinkResource#setXLinkType(de.escidoc.core.
     * resources.XLinkType)
     */
    @Override
    protected void setXLinkType(final XLinkType type) {
        if (identifier != null)
            identifier.setType(type);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.XLinkResource#setXLinkTitle(java.lang.String)
     */
    @Override
    protected void setXLinkTitle(final String title) {
        if (identifier != null)
            identifier.setTitle(title);
    }

    /**
     * @param lastModificationDate
     *            the lastModificationDate to set
     */
    public void setLastModificationDate(final DateTime lastModificationDate) {
        this.lastModificationDate = DateTimeUtility.normalize(lastModificationDate);
    }

    /**
     * @return the lastModificationDate
     */
    public DateTime getLastModificationDate() {
        return lastModificationDate;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.XLinkAutonomousX#generateXLinkHref(java.lang
     * .String)
     */
    @Override
    public void generateXLinkHref(final String parentPath) {
        if (parentPath != null && getXLinkHref() == null && getName() != null) {
            setXLinkHref(parentPath + getSubRecourcePath() + '/' + getName());
        }
    }

    /**
     * This method must return the sub resource path of this sub resource.<br/>
     * <br/>
     * For example a MetadataRecords of an Item:<br/>
     * <br/>
     * /ir/item/{itemid}/md-records/md-record/{md-record-name}<br/>
     * <br/>
     * where <code>/md-record</code> will be the result of this method.
     * 
     * @return
     */
    protected abstract String getSubRecourcePath();

    /**
     * Factory method for JiBX to create an instance of {@link Identifier}.
     * 
     * @param obj
     * @return
     */
    protected static final NameIdentifier createIdentifier(final Object obj) {
        if (obj instanceof NamedSubResource)
            return ((NamedSubResource) obj).new NameIdentifier();
        throw new IllegalArgumentException();
    }

    /**
     * @return
     */
    protected NameIdentifier getNameIdentifierInstance() {
        return this.new NameIdentifier();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (identifier == null ? 0 : identifier.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        NamedSubResource other = (NamedSubResource) obj;
        if (identifier == null) {
            if (other.identifier != null)
                return false;
        }
        else if (!identifier.equals(other.identifier))
            return false;
        return true;
    }

    /**
     * 
     */
    protected void validateIdentifier() {
        if (identifier != null && identifier.name == null) {
            throw new IllegalStateException("No name present in NameIdentifier.");
        }
    }

    /**
     * 
     * @author Marko Voß
     * 
     */
    @JiBX
    protected class NameIdentifier {

        protected String name;

        protected String href;

        protected XLinkType type = XLinkType.simple;

        protected String title;

        /**
         * @param id
         */
        NameIdentifier() {
        }

        /**
         * @param objid
         */
        NameIdentifier(final String name) {
            checkNotNull(name);
            this.name = name;
        }

        /**
         * @param href
         * @param title
         * @param type
         */
        NameIdentifier(final String href, final String title, final XLinkType type) {
            checkNotNull(href);
            this.href = href;
            this.title = title;
            this.type = type == null ? XLinkType.simple : type;
        }

        /**
         * @param objid
         *            the objid to set
         */
        void setName(final String name) {
            this.name = name;
        }

        /**
         * @param href
         *            the href to set
         */
        void setHref(final String href) {
            this.href = href;
        }

        /**
         * @param type
         *            the type to set
         */
        void setType(final XLinkType type) {
            if (type != null)
                this.type = type;
        }

        /**
         * @param title
         *            the title to set
         */
        void setTitle(final String title) {
            this.title = title;
        }

        /**
         * @return the objid
         */
        String getName() {
            if (name != null)
                return name;
            if (href != null)
                setName(genName());
            return name;
        }

        /**
         * @return the href
         */
        String getHref() {
            // it is not possible to generate XLinkHrefs for sub resources
            return href;
        }

        /**
         * @return the type
         */
        XLinkType getType() {
            return type;
        }

        /**
         * @return the title
         */
        String getTitle() {
            return title;
        }

        /**
         * @return
         */
        boolean hasTitleForHref() {
            return getHref() != null && getTitle() != null;
        }

        /**
         * @return
         */
        boolean hasTypeForHref() {
            return getHref() != null && getType() != null;
        }

        /**
         * @return
         */
        protected String genName() {
            return href.substring(href.lastIndexOf('/') + 1);
        }
    }
}