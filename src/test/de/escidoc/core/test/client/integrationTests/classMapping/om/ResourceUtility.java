package de.escidoc.core.test.client.integrationTests.classMapping.om;

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
    public static MetadataRecord getMdRecord(final String name)
        throws ParserConfigurationException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName(name);

        Element element = doc.createElementNS(null, "myMdRecord");
        mdRecord.setContent(element);

        return mdRecord;
    }

    /**
     * Prepare data for content model specific.
     * 
     * @return ContentModelSpecific with some content
     * @throws ParserConfigurationException
     */
    public static ContentModelSpecific getContentModelSpecific()
        throws ParserConfigurationException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element contentModelSpecific = doc.createElementNS(null, "cms");
        Element element1 = doc.createElement("some-other-stuff");
        element1.setTextContent("some content - " + System.nanoTime());

        List<Element> cmsContent = new LinkedList<Element>();
        cmsContent.add(contentModelSpecific);
        cmsContent.add(element1);

        ContentModelSpecific cms = new ContentModelSpecific();
        cms.setContent(cmsContent);

        return cms;
    }

}
