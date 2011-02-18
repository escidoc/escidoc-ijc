/**
 * 
 */
package de.escidoc.core.test.client.integrationTests.classMapping.om.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ItemHandlerClient;
import de.escidoc.core.client.interfaces.ItemHandlerClientInterface;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.common.reference.ContentModelRef;
import de.escidoc.core.resources.common.reference.ContextRef;
import de.escidoc.core.resources.common.versionhistory.VersionHistory;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.integrationTests.classMapping.om.ResourceUtility;

/**
 * @author MVO
 * 
 */
public class VersionHistoryTest {

    private Authentication auth;

    private ItemHandlerClientInterface ihc;

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);
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
    public void testRetrieve() throws Exception {
        // create an item
        Item item = new Item();

        item.getProperties().setContext(
            new ContextRef(EscidocClientTestBase.getStaticContextId()));
        item.getProperties().setContentModel(
            new ContentModelRef(EscidocClientTestBase.getStaticContentModelId()));
        item.setXLinkTitle("TEST");

        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdrecord = ResourceUtility.getMdRecord("escidoc");
        mdRecords.add(mdrecord);
        item.setMetadataRecords(mdRecords);

        Item createdItem = ihc.create(item);

        // test version history
        VersionHistory vh = ihc.retrieveVersionHistory(createdItem.getObjid());

        assertNotNull("", vh);
        assertEquals("", vh.getLastModificationDate(),
            createdItem.getLastModificationDate());
        assertTrue("", vh.getVersions().size() == 1);

        // change item content
        Document doc =
            createdItem
                .getMetadataRecords().get("escidoc").getContent()
                .getOwnerDocument();

        Node child =
            doc.createElement("foo").appendChild(doc.createElement("bar"));

        createdItem
            .getMetadataRecords().get("escidoc").getContent()
            .appendChild(child);

        Item updatedItem = ihc.update(createdItem);

        // test version history
        vh = ihc.retrieveVersionHistory(updatedItem.getObjid());

        assertNotNull("", vh);
        assertEquals("", vh.getLastModificationDate(),
            updatedItem.getLastModificationDate());
        assertTrue("", vh.getVersions().size() == 2);

        // change status
        TaskParam p = new TaskParam();
        p.setLastModificationDate(updatedItem.getLastModificationDate());
        p.setComment("foo");
        Result result = ihc.submit(updatedItem.getObjid(), p);

        // test version history
        vh = ihc.retrieveVersionHistory(updatedItem.getObjid());

        assertNotNull("", vh);
        assertEquals("", vh.getLastModificationDate(),
            result.getLastModificationDate());
        assertTrue("", vh.getVersions().size() == 2);
    }
}
