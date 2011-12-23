/**
 * 
 */
package de.escidoc.core.test.client.integrationTests.classMapping.om.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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
            if (c.getContent().getStorageType() == StorageType.INTERNAL_MANAGED) {

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
     * FIXME Fix component creation for version 1.3.1
     * 
     * @throws Exception
     */
    @Ignore("EsciDoc v1.3 seems to be buggy.")
    @Test
    public void retrieveContentExternalManaged() throws Exception {
        final String itemId = EscidocClientTestBase.getStaticItemId();

        Item item = ihc.retrieve(itemId);

        /*
         * Due to a bug in EsciDoc v1.3 we cannot use the createComponent
         * sub-resource-method, which would be neat, because that way we would
         * get the component id directly, instead of storing the IDs as done in
         * this test. The implementation here is not save, since there could be
         * another user creating a component at the same time and therefore this
         * test could fail. This bug has been fixed for EsciDoc v1.3.1 and v1.4.
         */
        final List<String> alreadyExistingIds = new ArrayList<String>();
        for (final Component c : item.getComponents()) {
            alreadyExistingIds.add(c.getObjid());
        }

        final Component newComponent = new Component();
        final ComponentContent content = new ComponentContent();
        content.setStorageType(StorageType.EXTERNAL_MANAGED);
        content.setXLinkHref(EscidocClientTestBase.getDefaultInfrastructureURL().toString() + "/images/head-v0.1.png");
        content.setXLinkTitle("External resource");
        newComponent.setContent(content);

        final ComponentProperties properties = new ComponentProperties();
        properties.setDescription("External resource description");
        properties.setFileName("FIZ_logo_en.gif");
        properties.setVisibility("public");
        properties.setContentCategory("pre-print");
        newComponent.setProperties(properties);

        item.getComponents().add(newComponent);

        item = ihc.update(item);

        for (final Component c : item.getComponents()) {
            if (!alreadyExistingIds.contains(c.getObjid())) {

                assertEquals(c.getContent().getStorageType(), StorageType.EXTERNAL_MANAGED);
                // assertEquals(properties.getDescription(),
                // c.getProperties().getDescription());
                // assertEquals(properties.getFileName(),
                // c.getProperties().getFileName());
                assertEquals(properties.getVisibility(), c.getProperties().getVisibility());
                assertEquals(properties.getContentCategory(), c.getProperties().getContentCategory());

                assertEquals("/ir/item/" + itemId + "/components/component/" + c.getObjid() + "/content", c
                    .getContent().getXLinkHref());
                // assertEquals(content.getXLinkTitle(),
                // c.getContent().getXLinkTitle());
                assertEquals(content.getXLinkType(), c.getContent().getXLinkType());

                final HttpInputStream stream = ihc.retrieveContent(itemId, c.getObjid());

                assertNull(stream.getContentEncoding());
                assertTrue(stream.getContentLength() > 0);

                // FIXME uncomment as soon as this bug [INFR-1171] has been
                // fixed.
                // assertEquals("image/gif", stream.getContentType());

                stream.close();

                break;
            }
        }
    }

    /**
     * FIXME Fix component creation for version 1.3.1
     * 
     * @throws Exception
     */
    @Ignore("EsciDoc v1.3 seems to be buggy.")
    @Test
    public void retrieveContentExternalUrl() throws Exception {
        final String itemId = EscidocClientTestBase.getStaticItemId();

        Item item = ihc.retrieve(itemId);

        /*
         * Due to a bug in EsciDoc v1.3 we cannot use the createComponent
         * sub-resource-method, which would be neat, because that way we would
         * get the component id directly, instead of storing the IDs as done in
         * this test. The implementation here is not save, since there could be
         * another user creating a component at the same time and therefore this
         * test could fail. This bug has been fixed for EsciDoc v1.3.1 and v1.4.
         */
        final List<String> alreadyExistingIds = new ArrayList<String>();
        for (final Component c : item.getComponents()) {
            alreadyExistingIds.add(c.getObjid());
        }

        final Component newComponent = new Component();
        final ComponentContent content = new ComponentContent();
        content.setStorageType(StorageType.EXTERNAL_URL);
        content.setXLinkHref(EscidocClientTestBase.getDefaultInfrastructureURL().toString() + "/images/head-v0.1.png");
        content.setXLinkTitle("External resource");
        newComponent.setContent(content);

        final ComponentProperties properties = new ComponentProperties();
        properties.setDescription("External resource description");
        properties.setFileName("image1234.jpg");
        properties.setVisibility("public");
        properties.setContentCategory("pre-print");
        newComponent.setProperties(properties);

        item.getComponents().add(newComponent);

        item = ihc.update(item);

        for (final Component c : item.getComponents()) {
            if (!alreadyExistingIds.contains(c.getObjid())) {

                assertEquals(c.getContent().getStorageType(), StorageType.EXTERNAL_URL);
                // assertEquals(properties.getDescription(),
                // c.getProperties().getDescription());
                // assertEquals(properties.getFileName(),
                // c.getProperties().getFileName());
                assertEquals(properties.getVisibility(), c.getProperties().getVisibility());
                assertEquals(properties.getContentCategory(), c.getProperties().getContentCategory());
                assertEquals(content.getXLinkHref(), c.getContent().getXLinkHref());
                // assertEquals(content.getXLinkTitle(),
                // c.getContent().getXLinkTitle());
                assertEquals(content.getXLinkType(), c.getContent().getXLinkType());

                final HttpInputStream stream = ihc.retrieveContent(itemId, c.getObjid());

                assertNull(stream.getContentEncoding());
                assertTrue(stream.getContentLength() > 0);
                assertEquals("image/gif", stream.getContentType());

                stream.close();

                break;
            }
        }
    }
}