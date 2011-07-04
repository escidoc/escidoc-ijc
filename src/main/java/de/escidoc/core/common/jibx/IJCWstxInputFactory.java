/**
 * 
 */
package de.escidoc.core.common.jibx;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLResolver;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

import org.apache.xpath.XPathAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ctc.wstx.stax.WstxInputFactory;

/**
 * This {@link WstxInputFactory} extension will try to redirect DTD references
 * occurring while parsing XML using the StAXParser implementation to a local
 * file system or to other URLs using the catalog.xml from the class-path. If a
 * redirect fails for ANY reason, NO exceptions thrown by methods of this class
 * or any sub-class shall interrupt the parsing mechanism.
 * 
 * @author Marko Voß
 */
public class IJCWstxInputFactory extends WstxInputFactory {

    private static final Logger LOG = LoggerFactory.getLogger(IJCWstxInputFactory.class);

    private final Map<Key, URI> catalog = new HashMap<Key, URI>();

    private static final String FILE_CATALOG = "catalog.xml";

    private static final String NODE_PUBLIC = "public";

    private static final String NODE_SYSTEM = "system";

    private static final String XPATH_ENTRIES = "/catalog/" + NODE_SYSTEM + " | /catalog/" + NODE_PUBLIC;

    private static final String ATTR_SYSTEM_ID = "systemId";

    private static final String ATTR_PUBLIC_ID = "publicId";

    private static final String ATTR_URI = "uri";

    private static final String SCHEME_IJC = "ijc";

    /**
     * 
     */
    public IJCWstxInputFactory() {
        super();
        getConfig().setDtdResolver(new IJCDTDResolver());
        getConfig().setConfigFlag(CFG_SUPPORT_DTD | CFG_VALIDATE_AGAINST_DTD | CFG_CACHE_DTDS);

        // read catalog
        try {
            parseCatalog();
        }
        catch (final Exception e) {
            // ignore ALL exceptions!
            if (LOG.isWarnEnabled()) {
                LOG.warn("An error occured while trying to parse the calalog.xml: " + e.getMessage(), e);
            }
        }
    }

    /**
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws TransformerException
     * @throws DOMException
     * @throws URISyntaxException
     */
    private final void parseCatalog() throws SAXException, IOException, ParserConfigurationException,
        TransformerException, DOMException, URISyntaxException {
        final InputStream in = getClass().getClassLoader().getResourceAsStream(FILE_CATALOG);

        if (in != null) {
            final DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            docBuilderFactory.setNamespaceAware(false);
            final DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            final Document doc = docBuilder.parse(in);
            final NodeList nodes = XPathAPI.selectNodeList(doc, XPATH_ENTRIES);
            if (nodes != null) {
                for (int i = 0; i < nodes.getLength(); i++) {
                    final Node node = nodes.item(i);
                    if (node.getAttributes() != null) {
                        URI uri = null;
                        Key key = null;
                        Node attr = null;

                        if ((attr = node.getAttributes().getNamedItem(ATTR_URI)) != null) {
                            uri = new URI(attr.getNodeValue());
                        }

                        if (NODE_SYSTEM.equals(node.getNodeName())) {
                            if ((attr = node.getAttributes().getNamedItem(ATTR_SYSTEM_ID)) != null) {
                                key = new Key(null, attr.getNodeValue());
                            }
                        }
                        else if (NODE_PUBLIC.equals(node.getNodeName())) {
                            if ((attr = node.getAttributes().getNamedItem(ATTR_PUBLIC_ID)) != null) {
                                key = new Key(attr.getNodeValue(), null);
                            }
                        }

                        if (uri != null && key != null) {
                            catalog.put(key, uri);
                        }
                    }
                }
            }
        }
    }

    /**
     * @author Marko Voß
     * 
     */
    private final class Key {
        private final String publicID;

        private final String systemID;

        /**
         * @param publicID
         * @param systemID
         */
        public Key(final String publicID, final String systemID) {
            this.publicID = publicID;
            this.systemID = systemID;
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
            result = prime * result + getOuterType().hashCode();
            result = prime * result + (publicID == null ? 0 : publicID.hashCode());
            result = prime * result + (systemID == null ? 0 : systemID.hashCode());
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
            final Key other = (Key) obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;

            // either publicId OR systemId have to be equals
            if (publicID != null) {
                return publicID.equals(other.publicID);
            }
            if (systemID != null) {
                return systemID.equals(other.systemID);
            }

            return true;
        }

        private IJCWstxInputFactory getOuterType() {
            return IJCWstxInputFactory.this;
        }

    }

    /**
     * @author Marko Voß
     * 
     */
    private final class IJCDTDResolver implements XMLResolver {

        /*
         * (non-Javadoc)
         * 
         * @see javax.xml.stream.XMLResolver#resolveEntity(java.lang.String,
         * java.lang.String, java.lang.String, java.lang.String)
         */
        @Override
        public Object resolveEntity(
            final String publicID, final String systemID, final String baseURI, final String namespace)
            throws XMLStreamException {

            // baseURI and namespace are irrelevant

            final Key key = new Key(publicID, systemID);
            final URI uri = catalog.get(key);
            if (uri != null) {
                /*
                 * if the URI is an IJC ClassPath reference, try to load the
                 * resource via the ClassLoader.
                 */
                if (SCHEME_IJC.equals(uri.getScheme())) {
                    final InputStream in = getClass().getClassLoader().getResourceAsStream(uri.getSchemeSpecificPart());
                    if (in == null && LOG.isWarnEnabled()) {
                        LOG.warn("No resource found for publicID: " + publicID + " or systemID: " + systemID + ": URI["
                            + uri.toString() + "]");
                        // avoid future redundant checks
                        /*
                         * TODO: Could resources become available at runtime
                         * later like in auto-deploy of Tomcat or
                         * plugin-technology of Eclipse? If so, do not remove
                         * the entry from the catalog. In case that the DTD has
                         * been loaded from the external source, this DTD will
                         * be cached as configured in the constructor.
                         * Therefore, this resolver may no longer be called to
                         * resolve the DTD. Because of this, it may be
                         * appropriate to delete the entry though.
                         */
                        catalog.remove(key);
                    }
                    return in;
                }
                else {
                    /*
                     * Otherwise assume that the URI is a valid URL. Ignore this
                     * entry if the URI is no valid URL.
                     */
                    try {
                        return uri.toURL();
                    }
                    catch (final Exception e) {
                        if (LOG.isWarnEnabled()) {
                            LOG.warn("The URI for publicID: " + publicID + " or systemID: " + systemID
                                + " is no valid URL: " + e.getMessage(), e);
                        }
                        // avoid future redundant checks
                        catalog.remove(key);
                    }
                }
            }
            return null;
        }
    }
}