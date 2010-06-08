/*
 * CDDL HEADER START
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License, Version 1.0 only
 * (the "License").  You may not use this file except in compliance
 * with the License.
 *
 * You can obtain a copy of the license at license/ESCIDOC.LICENSE
 * or http://www.escidoc.de/license.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at license/ESCIDOC.LICENSE.
 * If applicable, add the following below this CDDL HEADER, with the
 * fields enclosed by brackets "[]" replaced with your own identifying
 * information: Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 */

/*
 * Copyright 2006-2008 Fachinformationszentrum Karlsruhe Gesellschaft
 * fuer wissenschaftlich-technische Information mbH and Max-Planck-
 * Gesellschaft zur Foerderung der Wissenschaft e.V.  
 * All rights reserved.  Use is subject to license terms.
 */
package de.escidoc.core.test.client.integrationTests.classMapping.om.item.surrogateItem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ItemHandlerClient;
import de.escidoc.core.resources.ResourceRef;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.common.properties.ContentModelSpecific;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.integrationTests.classMapping.om.ResourceUtility;

/**
 * Test create a Surrogate Item.
 * 
 * @author SWA
 * 
 */
public class SurrogateItemCreateTest {

    /**
     * Test create of a surrogate Item.
     * 
     * @throws Exception
     *             Thrown if creation failed or non-volatile Item values differ.
     */
    @Test
    public void testCreateSurrogateItem01() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ItemHandlerClient ihc = new ItemHandlerClient();
        ihc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        ihc.setHandle(auth.getHandle());

        Item item = new Item();

        item.getProperties().setContext(
            new ResourceRef(Constants.EXAMPLE_CONTEXT_ID));
        item.getProperties().setContentModel(
            new ResourceRef(Constants.EXAMPLE_CONTENT_MODEL_ID));

        // Content-model
        ContentModelSpecific cms = ResourceUtility.getContentModelSpecific();
        item.getProperties().setContentModelSpecific(cms);

        // Metadata Record(s)
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdrecord = ResourceUtility.getMdRecord("escidoc");
        mdRecords.add(mdrecord);
        item.setMetadataRecords(mdRecords);

        // create
        Item createdItem = ihc.create(item);
        // submit --------------------------------------------------------------
        TaskParam taskParam = new TaskParam();
        taskParam
            .setLastModificationDate(createdItem.getLastModificationDate());
        taskParam.setComment("submitted as java client lib test");

        Result result = ihc.submit(createdItem, taskParam);

        // assign object PID ---------------------------------------------------
        taskParam = new TaskParam();
        taskParam.setLastModificationDate(result.getLastModificationDate());
        taskParam.setUrl("http://url.to.the.solution/path/for/this/resource/"
            + System.nanoTime());
        taskParam.setComment("Test Object PID");

        result = ihc.assignObjectPid(createdItem, taskParam);

        // assign version PID --------------------------------------------------
        taskParam = new TaskParam();
        taskParam.setLastModificationDate(result.getLastModificationDate());
        taskParam.setUrl("http://url.to.the.solution/path/for/this/resource/"
            + System.nanoTime());
        taskParam.setComment("Test Version PID");

        result = ihc.assignVersionPid(createdItem, taskParam);

        // release -------------------------------------------------------------
        taskParam = new TaskParam();
        taskParam.setLastModificationDate(result.getLastModificationDate());
        taskParam.setComment("Release as java client lib test");

        result = ihc.release(createdItem, taskParam);

        // ====================================================================
        // prepare a new Item as Surrogate Item
        Item surrogateItem = new Item();

        surrogateItem.getProperties().setContext(
            new ResourceRef(Constants.EXAMPLE_CONTEXT_ID));
        surrogateItem.getProperties().setContentModel(
            new ResourceRef(Constants.EXAMPLE_CONTENT_MODEL_ID));

        // Content-model
        surrogateItem.getProperties().setContentModelSpecific(cms);

        // Metadata Record(s)
        mdRecords = new MetadataRecords();
        mdrecord = ResourceUtility.getMdRecord("escidoc");
        mdRecords.add(mdrecord);
        surrogateItem.setMetadataRecords(mdRecords);

        surrogateItem.getProperties().setOrigin(
            new ResourceRef(createdItem.getObjid()));
        surrogateItem = ihc.create(surrogateItem);

        /*
         * compare the Surrogate Item with the origin Item
         */
        String objId = createdItem.getObjid();

        assertNotNull("Object id is null", objId);
        assertEquals(createdItem.getProperties().getContext().getObjid(),
            surrogateItem.getProperties().getContext().getObjid());
        assertEquals(createdItem.getProperties().getContentModel().getObjid(),
            surrogateItem.getProperties().getContentModel().getObjid());
        assertEquals(createdItem.getObjid(), surrogateItem
            .getProperties().getOrigin().getObjid());
    }
}
