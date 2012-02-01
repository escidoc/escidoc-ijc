package de.escidoc.core.test.client.integrationTests.classMapping.om;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.properties.ContentModelSpecific;

/**
 * Utility for test classes to create resource specific elements.
 * 
 * @author SWA
 * 
 */
public class ResourceUtility {

    /**
     * Get md record with provided name.
     * 
     * @param name
     *            Name of md-record
     * @return md-record
     * @throws ParserConfigurationException
     *             Thrown if instance of DocumentBuiler failed to create.
     */
    public static MetadataRecord getMdRecord(final String name) throws ParserConfigurationException {

        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document doc = builder.newDocument();

        final MetadataRecord mdRecord = new MetadataRecord(name);

        final Element element = doc.createElementNS(null, "myMdRecord");
        mdRecord.setContent(element);

        return mdRecord;
    }

    /**
     * Prepare data for content model specific.
     * 
     * @return ContentModelSpecific with some content
     * @throws ParserConfigurationException
     */
    public static ContentModelSpecific getContentModelSpecific() throws ParserConfigurationException {

        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document doc = builder.newDocument();

        final Element contentModelSpecific = doc.createElementNS(null, "cms");
        final Element element1 = doc.createElement("some-other-stuff");
        element1.setTextContent("some content - " + System.nanoTime());

        final List<Element> cmsContent = new LinkedList<Element>();
        cmsContent.add(contentModelSpecific);
        cmsContent.add(element1);

        final ContentModelSpecific cms = new ContentModelSpecific();
        cms.setContent(cmsContent);

        return cms;
    }

    /**
     * Create temp file with random content.
     * 
     * @return
     * @throws IOException
     */
    public static File createFileWithRandomContent() throws IOException {

        final File temp = File.createTempFile("escidoc-binary-content-example", ".tmp");
        temp.deleteOnExit();

        // Write to temp file
        final BufferedWriter out = new BufferedWriter(new FileWriter(temp));

        out.write("A random String " + System.nanoTime());
        out.close();

        return temp;
    }

}
