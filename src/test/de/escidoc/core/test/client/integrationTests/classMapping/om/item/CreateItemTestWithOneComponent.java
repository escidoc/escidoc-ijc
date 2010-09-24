package de.escidoc.core.test.client.integrationTests.classMapping.om.item;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ItemHandlerClient;
import de.escidoc.core.client.StagingHandlerClient;
import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.reference.ContentModelRef;
import de.escidoc.core.resources.common.reference.ContextRef;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.om.item.ItemProperties;
import de.escidoc.core.resources.om.item.component.Component;
import de.escidoc.core.resources.om.item.component.ComponentContent;
import de.escidoc.core.resources.om.item.component.ComponentProperties;
import de.escidoc.core.resources.om.item.component.Components;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

public class CreateItemTestWithOneComponent {

    private static final String TEST_FILE_PATH = "./resources/escidoc.jpeg";

    private static final Logger log = LoggerFactory
        .getLogger(CreateItemTestWithOneComponent.class);

    private static final String ESCIDOC = "escidoc";

    private final StagingHandlerClient stagingClient =
        new StagingHandlerClient();

    private final ItemHandlerClient client = new ItemHandlerClient();

    private Authentication auth;

    private final ItemProperties properties = new ItemProperties();

    private final Item item = new Item();

    private final Component component = new Component();

    private final ComponentContent content = new ComponentContent();

    private final ComponentProperties componentProperties =
        new ComponentProperties();

    private final Components components = new Components();

    private final MetadataRecords mdRecords = new MetadataRecords();

    private final MetadataRecord mdRecord = new MetadataRecord();

    private File file;

    private URL contentRef;

    @Before
    public void setUp() throws EscidocClientException,
        ParserConfigurationException, IOException {
        authentificate();
        createTestFile();
        initStagingClient();
        initItemClient();
        initItem();
    }

    private void createTestFile() {
        file = new File(TEST_FILE_PATH);
    }

    private void initStagingClient() throws EscidocException,
        InternalClientException, TransportException {
        stagingClient.setServiceAddress(auth.getServiceAddress());
        stagingClient.setHandle(auth.getHandle());

        contentRef = stagingClient.upload(file);
    }

    @Test
    public void shouldReturnNonEmptyContentXlinkHrefWhenItemIsRetrieved()
        throws EscidocException, InternalClientException, TransportException {

        final Item createdItem = client.create(item);
        final String objid = createdItem.getObjid();
        log.info("Object ID: " + objid);

        final Item retrievedItem = client.retrieve(objid);
        final Components components = retrievedItem.getComponents();
        assertTrue("component size should be bigger than 0",
            components.size() > 0);

        for (final Component component : components) {
            final ComponentContent content = component.getContent();
            final String xLinkHref = content.getXLinkHref();
            assertTrue("XLink HREF should not be null", xLinkHref.length() > 0);
        }
    }

    private void authentificate() throws EscidocClientException {
        auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);
    }

    private void initItemClient() {
        client.setServiceAddress(auth.getServiceAddress());
        client.setHandle(auth.getHandle());
    }

    private void initItem() throws ParserConfigurationException {
        item.setProperties(properties);
        setContext();
        setContentModel();
        setMetadataRecords();
        setComponents();
    }

    private void setContext() {
        properties.setContext(new ContextRef(Constants.EXAMPLE_CONTEXT_ID));
    }

    private void setContentModel() {
        properties.setContentModel(new ContentModelRef(
            Constants.EXAMPLE_CONTENT_MODEL_ID));
    }

    private void setMetadataRecords() throws ParserConfigurationException {
        mdRecord.setName(ESCIDOC);

        final DocumentBuilderFactory factory =
            DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document document = builder.newDocument();
        final Element element = document.createElementNS(null, "myMdRecord");
        mdRecord.setContent(element);

        mdRecords.add(mdRecord);
        item.setMetadataRecords(mdRecords);
    }

    private void setComponents() {
        setComponentProperties();
        setComponentContent();
        components.add(component);
        item.setComponents(components);
    }

    private void setComponentContent() {
        content.setXLinkHref(contentRef.toString());
        content.setStorage("internal-managed");
        component.setContent(content);
    }

    private void setComponentProperties() {
        componentProperties.setDescription("Random content");
        componentProperties.setFileName(contentRef.getFile());
        componentProperties.setVisibility("public");
        componentProperties.setContentCategory("pre-print");

        // componentProperties.setContentCategory("content-category");
        // componentProperties.setMimeType("text/xml");
        // componentProperties.setValidStatus("valid");
        // componentProperties.setVisibility("insitutional");
        component.setProperties(componentProperties);
    }
}