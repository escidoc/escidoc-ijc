package de.escidoc.core.test.client.integrationTests.classMapping.om.item;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ItemHandlerClient;
import de.escidoc.core.client.StagingHandlerClient;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.ItemHandlerClientInterface;
import de.escidoc.core.client.interfaces.StagingHandlerClientInterface;
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
import de.escidoc.core.test.client.AbstractParameterizedTestBase;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

public class CreateItemTestWithOneComponent
    extends AbstractParameterizedTestBase {

    private static final String TEST_FILE_PATH = "./resources/escidoc.jpeg";

    private static final Logger log = LoggerFactory
        .getLogger(CreateItemTestWithOneComponent.class);

    private StagingHandlerClientInterface stagingClient;

    private ItemHandlerClientInterface itemClient;

    private Authentication auth;

    public CreateItemTestWithOneComponent(final TransportProtocol transport) {
        super(transport);
    }

    @Before
    public void init() throws Exception {
        authentificate();
        initStagingClient();
        initItemClient();
    }

    @After
    public void post() throws Exception {
        if (auth != null)
            auth.logout();
    }

    @Test
    public void shouldReturnNonEmptyContentXlinkHrefWhenItemIsRetrieved()
        throws Exception {

        // staging
        URL contentRef = stagingClient.upload(new File(TEST_FILE_PATH));

        Item item = initItem(contentRef);
        final Item createdItem = itemClient.create(item);
        final String objid = createdItem.getObjid();
        log.info("Object ID: " + objid);

        final Item retrievedItem = itemClient.retrieve(objid);
        final Components components = retrievedItem.getComponents();
        assertTrue("component size should be bigger than 0",
            components.size() > 0);

        for (final Component component : components) {
            final ComponentContent content = component.getContent();
            final String xLinkHref = content.getXLinkHref();

            assertNotNull("XLink HREF should not be null", xLinkHref);
            assertTrue("XLink HREF should not be empty", xLinkHref.length() > 0);
        }
    }

    private void authentificate() throws EscidocClientException {
        auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);
    }

    private void initStagingClient() throws EscidocException,
        InternalClientException, TransportException {
        stagingClient = new StagingHandlerClient(auth.getServiceAddress());
        stagingClient.setHandle(auth.getHandle());
    }

    private void initItemClient() throws InternalClientException {
        itemClient = new ItemHandlerClient(auth.getServiceAddress());
        itemClient.setHandle(auth.getHandle());
        itemClient.setTransport(transport);
    }

    private Item initItem(final URL contentRef)
        throws ParserConfigurationException {
        Item item = new Item();
        ItemProperties properties = item.getProperties();
        properties.setContext(new ContextRef(Constants.EXAMPLE_CONTEXT_ID));
        properties.setContentModel(new ContentModelRef(
            Constants.EXAMPLE_CONTENT_MODEL_ID));
        setMdRecords(item);
        setComponents(item, contentRef);
        return item;
    }

    private void setMdRecords(final Item item)
        throws ParserConfigurationException {
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");

        final DocumentBuilderFactory factory =
            DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document document = builder.newDocument();
        final Element element = document.createElementNS(null, "myMdRecord");
        mdRecord.setContent(element);

        MetadataRecords mdRecords = new MetadataRecords();
        mdRecords.add(mdRecord);
        item.setMetadataRecords(mdRecords);
    }

    private void setComponents(final Item item, final URL contentRef) {
        Component component = new Component();
        setComponentProperties(component, contentRef);
        setComponentContent(component, contentRef);
        Components components = new Components();
        components.add(component);
        item.setComponents(components);
    }

    private void setComponentContent(
        final Component component, final URL contentRef) {
        ComponentContent content = new ComponentContent();
        content.setXLinkHref(contentRef.toString());
        content.setStorage("internal-managed");
        component.setContent(content);
    }

    private void setComponentProperties(
        final Component component, final URL contentRef) {
        ComponentProperties properties = new ComponentProperties();
        properties.setDescription("Random content");
        properties.setFileName(contentRef.getFile());
        properties.setVisibility("public");
        properties.setContentCategory("pre-print");

        // properties.setContentCategory("content-category");
        // properties.setMimeType("text/xml");
        // properties.setValidStatus("valid");
        // properties.setVisibility("insitutional");
        component.setProperties(properties);
    }
}