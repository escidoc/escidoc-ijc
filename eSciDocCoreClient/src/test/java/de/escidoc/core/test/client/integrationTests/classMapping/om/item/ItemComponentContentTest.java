/**
 * 
 */
package de.escidoc.core.test.client.integrationTests.classMapping.om.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ItemHandlerClient;
import de.escidoc.core.client.interfaces.ItemHandlerClientInterface;
import de.escidoc.core.resources.HttpInputStream;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.om.item.StorageType;
import de.escidoc.core.resources.om.item.component.Component;
import de.escidoc.core.resources.om.item.component.ComponentContent;
import de.escidoc.core.resources.om.item.component.ComponentProperties;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * @author Marko Voß
 * 
 */
public class ItemComponentContentTest {

    private Authentication auth;

    private ItemHandlerClientInterface ihc;

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.getDefaultInfrastructureURL(),
                EscidocClientTestBase.SYSTEM_ADMIN_USER, EscidocClientTestBase.SYSTEM_ADMIN_PASSWORD);
        ihc = new ItemHandlerClient(auth.getServiceAddress());
        ihc.setHandle(auth.getHandle());
    }

    @After
    public void post() throws Exception {
        if (auth != null)
            auth.logout();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void retrieveContentInternalManaged() throws Exception {
        final String itemId = EscidocClientTestBase.getStaticItemId();

        final Item item = ihc.retrieve(itemId);
        for (final Component c : item.getComponents()) {
            // get the origin component from loadExamples
            if (c.getContent().getStorage() == StorageType.INTERNAL_MANAGED) {

                final HttpInputStream stream = ihc.retrieveContent(itemId, c.getObjid());

                assertNull(stream.getContentEncoding());
                assertTrue(stream.getContentLength() > 0);
                assertEquals("image/jpeg", stream.getContentType());

                stream.close();

                // end test
                break;
            }
        }
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void retrieveContentExternalManaged() throws Exception {
        final String itemId = EscidocClientTestBase.getStaticItemId();

        final DateTime itemLMD = ihc.retrieve(itemId).getLastModificationDate();

        Component component = new Component();
        final ComponentContent content = new ComponentContent();
        content.setStorage(StorageType.EXTERNAL_MANAGED);
        content.setXLinkHref(EscidocClientTestBase.getDefaultInfrastructureURL().toString() + "/images/head-v0.1.png");
        content.setXLinkTitle("External resource");
        component.setContent(content);
        component.setLastModificationDate(itemLMD);

        final ComponentProperties properties = new ComponentProperties();
        properties.setDescription("External resource description");
        properties.setFileName("FIZ_logo_en.gif");
        properties.setVisibility("public");
        properties.setContentCategory("pre-print");
        component.setProperties(properties);

        component = ihc.createComponent(itemId, component);

        assertEquals(component.getContent().getStorage(), StorageType.EXTERNAL_MANAGED);
        // assertEquals(properties.getDescription(),
        // c.getProperties().getDescription());
        // assertEquals(properties.getFileName(),
        // c.getProperties().getFileName());
        assertEquals(properties.getVisibility(), component.getProperties().getVisibility());
        assertEquals(properties.getContentCategory(), component.getProperties().getContentCategory());

        assertEquals("/ir/item/" + itemId + "/components/component/" + component.getObjid() + "/content", component
            .getContent().getXLinkHref());
        // assertEquals(content.getXLinkTitle(),
        // c.getContent().getXLinkTitle());
        assertEquals(content.getXLinkType(), component.getContent().getXLinkType());

        final HttpInputStream stream = ihc.retrieveContent(itemId, component.getObjid());

        assertNull(stream.getContentEncoding());
        assertTrue("ContentLength not greater than 0.", stream.getContentLength() > 0);

        // FIXME uncomment as soon as this bug [INFR-1171] has been
        // fixed.
        // assertEquals("image/gif", stream.getContentType());

        stream.close();

    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void retrieveContentExternalUrlHTTPS() throws Exception {
        final String itemId = EscidocClientTestBase.getStaticItemId();
        final DateTime itemLMD = ihc.retrieve(itemId).getLastModificationDate();

        Component component = new Component();
        final ComponentContent content = new ComponentContent();
        content.setStorage(StorageType.EXTERNAL_URL);
        content.setXLinkHref("https://www.escidoc.org/images/escidoc-logo.jpg");
        content.setXLinkTitle("External resource");
        component.setContent(content);
        component.setLastModificationDate(itemLMD);

        final ComponentProperties properties = new ComponentProperties();
        properties.setDescription("External resource description");
        properties.setFileName("image1234.jpg");
        properties.setVisibility("public");
        properties.setContentCategory("pre-print");
        component.setProperties(properties);

        component = ihc.createComponent(itemId, component);

        assertEquals(component.getContent().getStorage(), StorageType.EXTERNAL_URL);
        // assertEquals(properties.getDescription(),
        // c.getProperties().getDescription());
        // assertEquals(properties.getFileName(),
        // c.getProperties().getFileName());
        assertEquals(properties.getVisibility(), component.getProperties().getVisibility());
        assertEquals(properties.getContentCategory(), component.getProperties().getContentCategory());
        assertEquals(content.getXLinkHref(), component.getContent().getXLinkHref());
        // assertEquals(content.getXLinkTitle(),
        // c.getContent().getXLinkTitle());
        assertEquals(content.getXLinkType(), component.getContent().getXLinkType());

        final HttpInputStream stream = ihc.retrieveContent(itemId, component.getObjid());

        assertNull(stream.getContentEncoding());
        assertTrue("ContentLength not greater than 0.", stream.getContentLength() > 0);
        assertEquals("image/jpeg", stream.getContentType());

        stream.close();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void retrieveContentExternalUrlHTTP() throws Exception {
        final String itemId = EscidocClientTestBase.getStaticItemId();
        final DateTime itemLMD = ihc.retrieve(itemId).getLastModificationDate();

        Component component = new Component();
        final ComponentContent content = new ComponentContent();
        content.setStorage(StorageType.EXTERNAL_URL);
        content.setXLinkHref(EscidocClientTestBase.getDefaultInfrastructureURL().toString() + "/images/head-v0.1.png");
        content.setXLinkTitle("External resource");
        component.setContent(content);
        component.setLastModificationDate(itemLMD);

        final ComponentProperties properties = new ComponentProperties();
        properties.setDescription("External resource description");
        properties.setFileName("image1234.jpg");
        properties.setVisibility("public");
        properties.setContentCategory("pre-print");
        component.setProperties(properties);

        component = ihc.createComponent(itemId, component);

        assertEquals(component.getContent().getStorage(), StorageType.EXTERNAL_URL);
        // assertEquals(properties.getDescription(),
        // c.getProperties().getDescription());
        // assertEquals(properties.getFileName(),
        // c.getProperties().getFileName());
        assertEquals(properties.getVisibility(), component.getProperties().getVisibility());
        assertEquals(properties.getContentCategory(), component.getProperties().getContentCategory());
        assertEquals(content.getXLinkHref(), component.getContent().getXLinkHref());
        // assertEquals(content.getXLinkTitle(),
        // c.getContent().getXLinkTitle());
        assertEquals(content.getXLinkType(), component.getContent().getXLinkType());

        final HttpInputStream stream = ihc.retrieveContent(itemId, component.getObjid());

        assertNull(stream.getContentEncoding());
        assertTrue("ContentLength not greater than 0.", stream.getContentLength() > 0);
        assertEquals("image/jpeg", stream.getContentType());

        stream.close();
    }
}